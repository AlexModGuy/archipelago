package com.github.alexthe666.archipelago.block;

import java.lang.reflect.InvocationTargetException;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.github.alexthe666.archipelago.Archipelago;
import com.github.alexthe666.archipelago.util.PlantEntry;
import com.github.alexthe666.archipelago.world.WorldGeneratorArchipelago;

public class BlockShortCoral extends BlockBush{

	public BlockShortCoral(String name, int chance, BiomeGenBase[] biomes){
		super(Material.coral);
		this.setHardness(0.0F);
		this.setStepSound(SoundType.PLANT);
		this.setUnlocalizedName("archipelago.plant." + name);
		this.setCreativeTab(Archipelago.tab);
		this.setLightOpacity(0);
		this.useNeighborBrightness = true;
		GameRegistry.registerBlock(this, name);
		Archipelago.PROXY.addItemRender(Item.getItemFromBlock(this), name);
		PlantEntry entry = new PlantEntry(this, chance, false);
		for(BiomeGenBase biome : biomes){
			entry.addBiome(BiomeGenBase.getIdForBiome(biome));
		}
		WorldGeneratorArchipelago.coralsEntries.add(entry);
	}
	
	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn){
		if((worldIn.getBlockState(new BlockPos(entityIn).down()).getMaterial() == Material.water || worldIn.getBlockState(new BlockPos(entityIn).down()).getMaterial() == Material.coral) && worldIn.getBlockState(pos.down()).getMaterial() == Material.water && entityIn.getRidingEntity() == null){
			if(entityIn instanceof EntityLivingBase && !(entityIn instanceof EntityPlayer)){
				EntityLivingBase living = (EntityLivingBase)entityIn;
				try {
					ReflectionHelper.findMethod(Entity.class, entityIn, new String[]{"setFlag", "func_70052_a"}, int.class, boolean.class).invoke(living, 7, true);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
			if(entityIn instanceof EntityPlayer){
				EntityPlayer player = (EntityPlayer)entityIn;
				if(!player.capabilities.isFlying){
					try {
						ReflectionHelper.findMethod(Entity.class, entityIn, new String[]{"setFlag", "func_70052_a"}, int.class, boolean.class).invoke(player, 7, true);
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
					entityIn.motionX *= 1.02;
					entityIn.motionZ *= 1.02;
				}
			}
		}
	}

    public boolean canPlaceBlockAt(World worldIn, BlockPos pos){
        return checkCanStay(worldIn.getBlockState(pos.down()), worldIn.getBlockState(pos.up())) && worldIn.getBlockState(pos).getMaterial() == Material.water;
    }
    
	public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state){
		return this.checkCanStay(worldIn.getBlockState(pos.down()), worldIn.getBlockState(pos.up()));
	}

	protected boolean checkCanStay(IBlockState state, IBlockState state2)
	{
		return (state.getMaterial() == Material.sand || state.getMaterial() == Material.ground) && state2.getBlock() instanceof BlockTropicalWater;
	}
	
    @SideOnly(Side.CLIENT)
    public Block.EnumOffsetType getOffsetType(){
        return Block.EnumOffsetType.XZ;
    }
}
