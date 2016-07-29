package com.github.alexthe666.archipelago.event.server;

import java.util.Random;

import net.ilexiconn.llibrary.server.entity.EntityPropertiesHandler;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import com.github.alexthe666.archipelago.Archipelago;
import com.github.alexthe666.archipelago.core.ModConfig;
import com.github.alexthe666.archipelago.enums.EnumParticle;
import com.github.alexthe666.archipelago.properties.ArchipelagoEntityProperties;
import com.github.alexthe666.archipelago.world.TeleporterArchipelago;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.oredict.OreDictionary;

public class ServerEvents {

	@SubscribeEvent
	public void onEntityUpdate(LivingUpdateEvent event) {
		handleMaterialAcceleration(event.getEntity().getEntityBoundingBox(), Material.CORAL, event.getEntity());

		if (event.getEntity() instanceof EntityPlayer) {
			ArchipelagoEntityProperties properties = EntityPropertiesHandler.INSTANCE.getProperties((event.getEntity()), ArchipelagoEntityProperties.class);
			if (properties.teleportTime > 0 && properties.teleportTime <= 300) {
				properties.teleportTime++;
				event.getEntity().motionY = (0.05D - event.getEntity().motionY) * 0.4D;
				event.getEntity().motionX = 0;
				event.getEntity().motionZ = 0;
				Random rand = new Random();
				float sub = (float) (Math.random() * 0.30000001192092896D);
				for (int i = 0; i < 5; i++) {
					float radius = 0.4F * i;
					float angle = (0.01745329251F * (event.getEntity().ticksExisted * 16 * i) + 3.15F * 0.05F);
					float extraX = (float) (radius * MathHelper.sin((float) (Math.PI + angle)));
					float extraZ = (float) (radius * MathHelper.cos(angle));
					float extraY = (1.5F * -i * radius) + 2;
					event.getEntity().worldObj.spawnParticle(EnumParticleTypes.END_ROD, (float) event.getEntity().posX + extraX, (float) event.getEntity().posY + extraY, (float) event.getEntity().posZ + extraZ, 0, 0, 0);
					event.getEntity().worldObj.spawnParticle(EnumParticleTypes.SPELL_MOB, (float) event.getEntity().posX + extraX, (float) event.getEntity().posY + extraY, (float) event.getEntity().posZ + extraZ, 0.95F - sub, 0.8439F - sub, 0.3135F - sub);
					Archipelago.PROXY.spawnParticle(EnumParticle.TELEPORT, event.getEntity().worldObj, (float) event.getEntity().posX + extraX, (float) event.getEntity().posY + extraY, (float) event.getEntity().posZ + extraZ, 0, 0, 0);
				}
			}
			if (properties.teleportTime >= 300 && !event.getEntity().worldObj.isRemote) {
				if (!event.getEntityLiving().worldObj.isRemote) {
					EntityPlayerMP player = (EntityPlayerMP) event.getEntityLiving();
					if (player.timeUntilPortal > 0) {
						player.timeUntilPortal = 10;
					} else if (player.dimension != ModConfig.ARCHIPELAGO_DIMENSION_ID) {
						player.timeUntilPortal = 10;
						player.mcServer.getPlayerList().transferPlayerToDimension(player, ModConfig.ARCHIPELAGO_DIMENSION_ID, new TeleporterArchipelago(player.mcServer.worldServerForDimension(ModConfig.ARCHIPELAGO_DIMENSION_ID)));
					} else if (player.dimension == ModConfig.ARCHIPELAGO_DIMENSION_ID) {
						player.timeUntilPortal = 10;
						player.mcServer.getPlayerList().transferPlayerToDimension(player, 0, new TeleporterArchipelago(player.mcServer.worldServerForDimension(0)));
					}
				}
				properties.teleportTime = 0;
			}
		}
	}

	@SubscribeEvent
	public void onReedsDecorate(DecorateBiomeEvent.Decorate event) {
		if (event.getType() == EventType.REED && event.getWorld().provider.getDimension() == ModConfig.ARCHIPELAGO_DIMENSION_ID) {
			event.setCanceled(true);
		}
	}

	@SubscribeEvent
	public void onPlayerCraft(PlayerEvent.ItemCraftedEvent event){
		if(event.player.dimension == ModConfig.ARCHIPELAGO_DIMENSION_ID && (event.crafting.getItem() == Item.getItemFromBlock(Blocks.CRAFTING_TABLE) || OreDictionary.itemMatches(event.crafting, new ItemStack(Blocks.PLANKS), true))){
			event.setResult(Event.Result.DENY);
			event.player.closeScreen();
			event.player.playSound(SoundEvents.ENTITY_ZOMBIE_BREAK_DOOR_WOOD, 1, 1);
		}
	}

	public boolean tryPlaceContainedLiquid(EntityPlayer worldIn, World pos, BlockPos blockPos, ItemStack stack) {
		IBlockState iblockstate = pos.getBlockState(blockPos);
		Material material = iblockstate.getMaterial();
		boolean flag = !material.isSolid();
		boolean flag1 = iblockstate.getBlock().isReplaceable(pos, blockPos);
		if (stack != null && stack.getItem() != null && stack.getItem() == Items.WATER_BUCKET)
			if (!pos.isAirBlock(blockPos) && !flag && !flag1) {
				return false;
			} else {
				if (pos.provider.doesWaterVaporize()) {
					int l = blockPos.getX();
					int i = blockPos.getY();
					int j = blockPos.getZ();
					pos.playSound(worldIn, blockPos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.5F, 2.6F + (pos.rand.nextFloat() - pos.rand.nextFloat()) * 0.8F);

					for (int k = 0; k < 8; ++k) {
						pos.spawnParticle(EnumParticleTypes.SMOKE_LARGE, l + Math.random(), i + Math.random(), j + Math.random(), 0.0D, 0.0D, 0.0D);
					}
				} else {
					if (!pos.isRemote && (flag || flag1) && !material.isLiquid()) {
						pos.destroyBlock(blockPos, true);
					}

					SoundEvent soundevent = SoundEvents.ITEM_BUCKET_EMPTY;
					pos.playSound(worldIn, blockPos, soundevent, SoundCategory.BLOCKS, 1.0F, 1.0F);
					// pos.setBlockState(blockPos,
					// ModFluids.tropical_water.getDefaultState(), 11);
				}

				return true;
			}
		return false;
	}

	public boolean handleMaterialAcceleration(AxisAlignedBB bb, Material materialIn, Entity entityIn) {
		int i = MathHelper.floor_double(bb.minX);
		int j = MathHelper.ceiling_double_int(bb.maxX);
		int k = MathHelper.floor_double(bb.minY);
		int l = MathHelper.ceiling_double_int(bb.maxY);
		int i1 = MathHelper.floor_double(bb.minZ);
		int j1 = MathHelper.ceiling_double_int(bb.maxZ);

		if (!entityIn.worldObj.isAreaLoaded(new BlockPos(i, k, i1), new BlockPos(j, 1, j1), true)) {
			return false;
		} else {
			boolean flag = false;
			Vec3d vec3d = Vec3d.ZERO;
			BlockPos.PooledMutableBlockPos blockpos$pooledmutableblockpos = BlockPos.PooledMutableBlockPos.retain();

			for (int k1 = i; k1 < j; ++k1) {
				for (int l1 = k; l1 < l; ++l1) {
					for (int i2 = i1; i2 < j1; ++i2) {
						blockpos$pooledmutableblockpos.setPos(k1, l1, i2);
						IBlockState iblockstate = entityIn.worldObj.getBlockState(blockpos$pooledmutableblockpos);
						Block block = iblockstate.getBlock();

						Boolean result = block.isEntityInsideMaterial(entityIn.worldObj, blockpos$pooledmutableblockpos, iblockstate, entityIn, l, materialIn, false);
						if (result != null && result) {
							// Forge: When requested call blocks
							// modifyAcceleration method, and more importantly
							// cause entityIn method to return true, which
							// results in an entity being "inWater"
							flag = true;
							vec3d = block.modifyAcceleration(entityIn.worldObj, blockpos$pooledmutableblockpos, entityIn, vec3d);
							continue;
						} else if (result != null && !result)
							continue;

						if (iblockstate.getMaterial() == materialIn) {
							double d0 = (float) (l1 + 1) - 8;

							if (l >= d0) {
								flag = true;
								vec3d = block.modifyAcceleration(entityIn.worldObj, blockpos$pooledmutableblockpos, entityIn, vec3d);
							}
						}
					}
				}
			}

			blockpos$pooledmutableblockpos.release();

			if (vec3d.lengthVector() > 0.0D && entityIn.isPushedByWater()) {
				vec3d = vec3d.normalize();
				double d1 = 0.014D;
				entityIn.motionX += vec3d.xCoord * d1;
				entityIn.motionY += vec3d.yCoord * d1;
				entityIn.motionZ += vec3d.zCoord * d1;
			}

			return flag;
		}
	}
}
