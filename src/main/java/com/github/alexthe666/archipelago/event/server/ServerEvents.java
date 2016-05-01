package com.github.alexthe666.archipelago.event.server;

import com.github.alexthe666.archipelago.Archipelago;
import com.github.alexthe666.archipelago.core.ModConfig;
import com.github.alexthe666.archipelago.core.ModFluids;
import com.github.alexthe666.archipelago.enums.EnumParticle;
import com.github.alexthe666.archipelago.properties.ArchipelagoEntityProperties;
import com.github.alexthe666.archipelago.world.TeleporterArchipelago;
import net.ilexiconn.llibrary.server.entity.EntityPropertiesHandler;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Random;

public class ServerEvents {

    @SubscribeEvent
    public void onEntityUpdate(LivingUpdateEvent event) {
        if (event.getEntity() instanceof EntityPlayer) {
            ArchipelagoEntityProperties properties = EntityPropertiesHandler.INSTANCE.getProperties(((EntityPlayer) event.getEntity()), ArchipelagoEntityProperties.class);
            if (properties.teleportTime > 0 && properties.teleportTime <= 300) {
                properties.teleportTime++;
                Random rand = new Random();
                Archipelago.PROXY.spawnParticle(EnumParticle.TELEPORT, event.getEntity().worldObj, (float) (event.getEntity().posX + (rand.nextDouble() - 0.5D) * (double) event.getEntity().width), (float) (event.getEntity().posY + rand.nextDouble() * (double) event.getEntity().height), (float) (event.getEntity().posZ + (rand.nextDouble() - 0.5D) * (double) event.getEntity().width), 0, 0, 0);
                event.getEntity().worldObj.spawnParticle(EnumParticleTypes.END_ROD, (float) (event.getEntity().posX + (rand.nextDouble() - 0.5D) * (double) event.getEntity().width), (float) (event.getEntity().posY + rand.nextDouble() * (double) event.getEntity().height), (float) (event.getEntity().posZ + (rand.nextDouble() - 0.5D) * (double) event.getEntity().width), 0, 0, 0, new int[0]);
            }
            if (properties.teleportTime == 300 && !event.getEntity().worldObj.isRemote) {
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
                properties.teleportTime = 0;
            }
        }
    }


    @SubscribeEvent
    public void onBucketFill(FillBucketEvent event) {
        IBlockState iBlockState = event.getEntityPlayer().worldObj.getBlockState(new BlockPos(event.getTarget().hitVec.xCoord, event.getTarget().hitVec.yCoord, event.getTarget().hitVec.zCoord));
        if (iBlockState.getBlock() == ModFluids.tropical_water && iBlockState.getValue(((BlockFluidClassic) ModFluids.tropical_water).LEVEL) == 0) {
            System.out.println("i");
            event.getEntityPlayer().worldObj.setBlockToAir(new BlockPos(event.getTarget().hitVec.xCoord, event.getTarget().hitVec.yCoord, event.getTarget().hitVec.zCoord));
            event.setFilledBucket(new ItemStack(Items.water_bucket));
            event.setResult(Result.ALLOW);
        }
    }

    @SubscribeEvent
    public void onPlayerInteract(PlayerInteractEvent.RightClickBlock e) {
        //	if(e.getEntityPlayer().worldObj.provider.getDimension() == ModConfig.ARCHIPELAGO_DIMENSION_ID)
        //tryPlaceContainedLiquid(e.getEntityPlayer(), e.getEntityLiving().worldObj, new BlockPos(e.getHitVec()), e.getItemStack());
    }

    public boolean tryPlaceContainedLiquid(EntityPlayer worldIn, World pos, BlockPos blockPos, ItemStack stack) {
        IBlockState iblockstate = pos.getBlockState(blockPos);
        Material material = iblockstate.getMaterial();
        boolean flag = !material.isSolid();
        boolean flag1 = iblockstate.getBlock().isReplaceable(pos, blockPos);
        if (stack != null && stack.getItem() != null && stack.getItem() == Items.water_bucket)
            if (!pos.isAirBlock(blockPos) && !flag && !flag1) {
                return false;
            } else {
                if (pos.provider.doesWaterVaporize()) {
                    int l = blockPos.getX();
                    int i = blockPos.getY();
                    int j = blockPos.getZ();
                    pos.playSound(worldIn, blockPos, SoundEvents.block_fire_extinguish, SoundCategory.BLOCKS, 0.5F, 2.6F + (pos.rand.nextFloat() - pos.rand.nextFloat()) * 0.8F);

                    for (int k = 0; k < 8; ++k) {
                        pos.spawnParticle(EnumParticleTypes.SMOKE_LARGE, (double) l + Math.random(), (double) i + Math.random(), (double) j + Math.random(), 0.0D, 0.0D, 0.0D, new int[0]);
                    }
                } else {
                    if (!pos.isRemote && (flag || flag1) && !material.isLiquid()) {
                        pos.destroyBlock(blockPos, true);
                    }

                    SoundEvent soundevent = SoundEvents.item_bucket_empty;
                    pos.playSound(worldIn, blockPos, soundevent, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    pos.setBlockState(blockPos, ModFluids.tropical_water.getDefaultState(), 11);
                }

                return true;
            }
        return false;
    }
}
