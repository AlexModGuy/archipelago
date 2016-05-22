package com.github.alexthe666.archipelago.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.github.alexthe666.archipelago.Archipelago;
import com.github.alexthe666.archipelago.block.BlockTallPlant.EnumBlockHalf;
import com.github.alexthe666.archipelago.util.PlantEntry;
import com.github.alexthe666.archipelago.world.WorldGeneratorArchipelago;

public class BlockGrowingSeaweed extends BlockBush{

	public static final PropertyEnum<BlockGrowingSeaweed.EnumBlockPart> PART = PropertyEnum.<BlockGrowingSeaweed.EnumBlockPart>create("part", BlockGrowingSeaweed.EnumBlockPart.class);
	private int height;
	public BlockGrowingSeaweed(String name, int chance, int height, BiomeGenBase[] biomes){
		super(Material.coral);
		this.setHardness(0.0F);
		this.setStepSound(SoundType.PLANT);
		this.setDefaultState(this.blockState.getBaseState().withProperty(PART, BlockGrowingSeaweed.EnumBlockPart.LOWER));
		this.setUnlocalizedName("archipelago.plant." + name);
		this.setCreativeTab(Archipelago.tab);
		this.setLightOpacity(0);
		this.useNeighborBrightness = true;
		this.height = height;
		this.setTickRandomly(true);
		GameRegistry.registerBlock(this, name);
		Archipelago.PROXY.addItemRender(Item.getItemFromBlock(this), name);
		PlantEntry entry = new PlantEntry(this, chance, false);
		for(BiomeGenBase biome : biomes){
			entry.addBiome(BiomeGenBase.getIdForBiome(biome));
		}
		//WorldGeneratorArchipelago.coralsEntries.add(entry);
	}

	//public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack){
	//	worldIn.setBlockState(pos, this.getDefaultState().withProperty(HALF, BlockTallPlant.EnumBlockHalf.LOWER), 2);
	//	worldIn.setBlockState(pos.up(), this.getDefaultState().withProperty(HALF, BlockTallPlant.EnumBlockHalf.UPPER), 2);
	//}
	
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos){
		return new AxisAlignedBB(0.30000001192092896D, 0.0D, 0.30000001192092896D, 0.699999988079071D, 1D, 0.699999988079071D);
	}

	public boolean canPlaceBlockAt(World worldIn, BlockPos pos){
		return super.canPlaceBlockAt(worldIn, pos) && canGrow(worldIn, pos);
	}

	public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state){
		return canGrow(worldIn, pos);
	}
	
	public boolean canGrow(World world, BlockPos pos){
		if(world.getBlockState(pos.up()).getMaterial() != Material.water){
			return false;
		}
		if(world.getBlockState(pos).getValue(PART) == BlockGrowingSeaweed.EnumBlockPart.LOWER && world.getBlockState(pos.down()).getMaterial() != Material.sand){
			return false;
		}
		return true;
	}

	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
    {
		IBlockState blockstate = world.getBlockState(pos);
		IBlockState blockstate1 = world.getBlockState(pos.down());
		if(blockstate.getValue(PART) == EnumBlockPart.UPPER && world.getBlockState(pos.up()).getBlock() == this){
			world.setBlockState(pos, blockstate.withProperty(PART, EnumBlockPart.MIDDLE), 2);
		}
		if(blockstate.getValue(PART) != EnumBlockPart.UPPER && world.getBlockState(pos.up()).getBlock() != this){
			world.setBlockState(pos, blockstate.withProperty(PART, EnumBlockPart.UPPER), 2);
		}
		if(canGrow(world, pos) && world.getBlockState(pos.up()).getBlock() != this){
			world.setBlockState(pos.up(), blockstate.withProperty(PART, EnumBlockPart.MIDDLE), 2);
		}
    }
	
	protected void checkAndDropBlock(World worldIn, BlockPos pos, IBlockState state){
		if (state.getBlock() == this && !this.canBlockStay(worldIn, pos, state))
		{
			boolean flag = state.getValue(PART) == BlockGrowingSeaweed.EnumBlockPart.LOWER;

			BlockPos blockpos = !flag ? pos : pos.up();
			BlockPos blockpos1 = !flag ? pos.down() : pos;
			Block block = (Block)(!flag ? this : worldIn.getBlockState(blockpos).getBlock());
			Block block1 = (Block)(!flag ? worldIn.getBlockState(blockpos1).getBlock() : this);

			if (flag) this.dropBlockAsItem(worldIn, pos, state, 0);

			if (block == this)
			{
				worldIn.setBlockState(blockpos, Blocks.air.getDefaultState(), 2);
			}

			if (block1 == this)
			{
				worldIn.setBlockState(blockpos1, Blocks.air.getDefaultState(), 3);
			}
		}
	}

	public IBlockState getStateFromMeta(int meta){
		return this.getDefaultState().withProperty(PART, EnumBlockPart.UPPER);
	}

	public int getMetaFromState(IBlockState state){
		return state.getValue(PART) == EnumBlockPart.UPPER ? state.getValue(PART) == EnumBlockPart.MIDDLE ? 1 : 2 : 0;
	}

	protected boolean checkCanStay(IBlockState state, IBlockState state2){
		return (state.getBlock() == this || state.getMaterial() == Material.sand || state.getMaterial() == Material.ground) && state2.getBlock() instanceof BlockTropicalWater;
	}

	protected BlockStateContainer createBlockState(){
		return new BlockStateContainer(this, new IProperty[] {PART});
	}

	@Override
	public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player, boolean willHarvest){
		for(int y = 0; y < height; y++){
			if (state.getBlock() ==  this && state.getValue(PART) != EnumBlockPart.LOWER && world.getBlockState(pos.up(y)).getBlock() == this)
				world.setBlockToAir(pos.up(y));
		}
		return world.setBlockToAir(pos);
	}

	public static enum EnumBlockPart implements IStringSerializable
	{
		UPPER,
		MIDDLE,
		LOWER;

		public String toString()
		{
			return this.toString().toLowerCase();
		}

		public String getName()
		{
			return this.toString();
		}
	}
}
