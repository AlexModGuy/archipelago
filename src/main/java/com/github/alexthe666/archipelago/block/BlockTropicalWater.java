package com.github.alexthe666.archipelago.block;

import java.lang.reflect.InvocationTargetException;
import java.util.EnumSet;
import java.util.Random;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.github.alexthe666.archipelago.core.ModFluids;

public class BlockTropicalWater extends BlockFluidClassic{

	int adjacentSourceBlocks;

	public BlockTropicalWater(Fluid fluid, Material material) {
		super(fluid, material);
		this.setLightOpacity(1);
	}

	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
	{
		IBlockState neighbor = blockAccess.getBlockState(pos.offset(side));
		if (neighbor.getMaterial() == blockState.getMaterial() || (!neighbor.isOpaqueCube() && neighbor.getBlock() != Blocks.air)){
			return false;
		}else{
			return  side == EnumFacing.UP ? true : super.shouldSideBeRendered(blockState, blockAccess, pos, side);
		}
	}

	@Override
	public int getQuantaValue(IBlockAccess world, BlockPos pos)
	{
		if(world != null){
			if(world.getBlockState(pos) != null){
				return super.getQuantaValue(world, pos);
			}
		}
		return 0;
	}
	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn){
		if(worldIn.getBlockState(new BlockPos(entityIn).down()).getMaterial() == Material.water && worldIn.getBlockState(pos.down()).getMaterial() == Material.water && entityIn.getRidingEntity() == null){
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

	protected int checkAdjacentBlock(World worldIn, BlockPos pos, int currentMinLevel)
	{
		int i = this.getLevel(worldIn, pos);

		if (i < 0)
		{
			return currentMinLevel;
		}
		else
		{
			if (i == 0)
			{
				++this.adjacentSourceBlocks;
			}

			if (i >= 8)
			{
				i = 0;
			}

			return currentMinLevel >= 0 && i >= currentMinLevel ? currentMinLevel : i;
		}
	}


	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
	{
		super.updateTick(worldIn, pos, state, rand);
		int i = ((Integer)state.getValue(LEVEL)).intValue();
		int j = 1;

		if (this.blockMaterial == Material.lava && !worldIn.provider.doesWaterVaporize())
		{
			j = 2;
		}

		int k = this.tickRate(worldIn);

		if (i > 0)
		{
			int l = -100;
			this.adjacentSourceBlocks = 0;

			for (EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL)
			{
				l = this.checkAdjacentBlock(worldIn, pos.offset(enumfacing), l);
			}

			int i1 = l + j;

			if (i1 >= 8 || l < 0)
			{
				i1 = -1;
			}

			if (this.getLevel(worldIn, pos.up()) >= 0)
			{
				int j1 = this.getLevel(worldIn, pos.up());

				if (j1 >= 8)
				{
					i1 = j1;
				}
				else
				{
					i1 = j1 + 8;
				}
			}

			if (this.adjacentSourceBlocks >= 2 && this.blockMaterial == Material.water)
			{
				IBlockState iblockstate1 = worldIn.getBlockState(pos.down());

				if (iblockstate1.getMaterial().isSolid())
				{
					i1 = 0;
				}
				else if (iblockstate1.getMaterial() == this.blockMaterial && ((Integer)iblockstate1.getValue(LEVEL)).intValue() == 0)
				{
					i1 = 0;
				}
			}

			if (i1 == i)
			{
				this.placeStaticBlock(worldIn, pos, state);
			}
			else
			{
				i = i1;

				if (i1 < 0)
				{
					worldIn.setBlockToAir(pos);
				}
				else
				{
					state = state.withProperty(LEVEL, Integer.valueOf(i1));
					worldIn.setBlockState(pos, state, 2);
					worldIn.scheduleUpdate(pos, this, k);
					worldIn.notifyNeighborsOfStateChange(pos, this);
				}
			}
		}
		else
		{
			this.placeStaticBlock(worldIn, pos, state);
		}

		IBlockState iblockstate = worldIn.getBlockState(pos.down());

		if (this.canFlowInto(worldIn, pos.down(), iblockstate))
		{
			if (this.blockMaterial == Material.lava && worldIn.getBlockState(pos.down()).getMaterial() == Material.water)
			{
				worldIn.setBlockState(pos.down(), Blocks.stone.getDefaultState());
				this.triggerMixEffects(worldIn, pos.down());
				return;
			}

			if (i >= 8)
			{
				this.tryFlowInto(worldIn, pos.down(), iblockstate, i);
			}
			else
			{
				this.tryFlowInto(worldIn, pos.down(), iblockstate, i + 8);
			}
		}
		else if (i >= 0 && (i == 0 || this.isBlocked(worldIn, pos.down(), iblockstate)))
		{
			Set<EnumFacing> set = this.getPossibleFlowDirections(worldIn, pos);
			int k1 = i + j;

			if (i >= 8)
			{
				k1 = 1;
			}

			if (k1 >= 8)
			{
				return;
			}

			for (EnumFacing enumfacing1 : set)
			{
				this.tryFlowInto(worldIn, pos.offset(enumfacing1), worldIn.getBlockState(pos.offset(enumfacing1)), k1);
			}
		}
	}

	private void tryFlowInto(World worldIn, BlockPos pos, IBlockState state, int level)
	{
		if (this.canFlowInto(worldIn, pos, state))
		{
			if (state.getBlock() != Blocks.air)
			{
				state.getBlock().dropBlockAsItem(worldIn, pos, state, 0);
			}

			worldIn.setBlockState(pos, this.getDefaultState().withProperty(LEVEL, Integer.valueOf(level)), 3);
		}
	}

	protected void triggerMixEffects(World worldIn, BlockPos pos)
	{
		double d0 = (double)pos.getX();
		double d1 = (double)pos.getY();
		double d2 = (double)pos.getZ();
		worldIn.playSound((EntityPlayer)null, pos, SoundEvents.block_lava_extinguish, SoundCategory.BLOCKS, 0.5F, 2.6F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.8F);

		for (int i = 0; i < 8; ++i)
		{
			worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d0 + Math.random(), d1 + 1.2D, d2 + Math.random(), 0.0D, 0.0D, 0.0D, new int[0]);
		}
	}

	private boolean canFlowInto(World worldIn, BlockPos pos, IBlockState state)
	{
		Material material = state.getMaterial();
		return material != this.blockMaterial && material != Material.lava && !this.isBlocked(worldIn, pos, state);
	}

	private Set<EnumFacing> getPossibleFlowDirections(World worldIn, BlockPos pos)
	{
		int i = 1000;
		Set<EnumFacing> set = EnumSet.<EnumFacing>noneOf(EnumFacing.class);

		for (EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL)
		{
			BlockPos blockpos = pos.offset(enumfacing);
			IBlockState iblockstate = worldIn.getBlockState(blockpos);

			if (!this.isBlocked(worldIn, blockpos, iblockstate) && (iblockstate.getMaterial() != this.blockMaterial || ((Integer)iblockstate.getValue(LEVEL)).intValue() > 0))
			{
				int j;

				if (this.isBlocked(worldIn, blockpos.down(), worldIn.getBlockState(blockpos.down())))
				{
					j = this.func_176374_a(worldIn, blockpos, 1, enumfacing.getOpposite());
				}
				else
				{
					j = 0;
				}

				if (j < i)
				{
					set.clear();
				}

				if (j <= i)
				{
					set.add(enumfacing);
					i = j;
				}
			}
		}

		return set;
	}

	private boolean isBlocked(World worldIn, BlockPos pos, IBlockState state)
	{
		Block block = worldIn.getBlockState(pos).getBlock();
		return !(block instanceof BlockDoor) && block != Blocks.standing_sign && block != Blocks.ladder && block != Blocks.reeds ? (block.getMaterial(state) == Material.portal ? true : block.getMaterial(state).blocksMovement()) : true;
	}

	protected int getLevel(IBlockAccess worldIn, BlockPos pos)
	{
		return worldIn.getBlockState(pos).getMaterial() == this.blockMaterial ? ((Integer)worldIn.getBlockState(pos).getValue(LEVEL)).intValue() : -1;
	}

	private int func_176374_a(World worldIn, BlockPos pos, int distance, EnumFacing calculateFlowCost)
	{
		int i = 1000;
		for (EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL)
		{
			if (enumfacing != calculateFlowCost)
			{
				BlockPos blockpos = pos.offset(enumfacing);
				IBlockState iblockstate = worldIn.getBlockState(blockpos);

				if (!this.isBlocked(worldIn, blockpos, iblockstate) && (iblockstate.getMaterial() != this.blockMaterial || ((Integer)iblockstate.getValue(LEVEL)).intValue() > 0))
				{
					if (!this.isBlocked(worldIn, blockpos.down(), iblockstate))
					{
						return distance;
					}
					if (distance < 2)
					{
						int j = this.func_176374_a(worldIn, blockpos, distance + 1, enumfacing.getOpposite());

						if (j < i)
						{
							i = j;
						}
					}
				}
			}
		}
		return i;
	}

	private void placeStaticBlock(World worldIn, BlockPos pos, IBlockState currentState)
	{
		worldIn.setBlockState(pos, ModFluids.tropical_water.getDefaultState().withProperty(LEVEL, currentState.getValue(LEVEL)), 2);
	}

	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState worldIn, World pos, BlockPos state, Random rand)
	{
		double d0 = (double)state.getX();
		double d1 = (double)state.getY();
		double d2 = (double)state.getZ();
		int i = ((Integer)worldIn.getValue(LEVEL)).intValue();

		if (i > 0 && i < 8)
		{
			if (rand.nextInt(64) == 0)
			{
				pos.playSound(d0 + 0.5D, d1 + 0.5D, d2 + 0.5D, SoundEvents.block_water_ambient, SoundCategory.BLOCKS, rand.nextFloat() * 0.25F + 0.75F, rand.nextFloat() + 0.5F, false);
			}
		}
		else if (rand.nextInt(10) == 0)
		{
			pos.spawnParticle(EnumParticleTypes.SUSPENDED, d0 + (double)rand.nextFloat(), d1 + (double)rand.nextFloat(), d2 + (double)rand.nextFloat(), 0.0D, 0.0D, 0.0D, new int[0]);
		}

		if (rand.nextInt(10) == 0 && pos.getBlockState(state.down()).isFullyOpaque())
		{
			Material material = pos.getBlockState(state.down(2)).getMaterial();

			if (!material.blocksMovement() && !material.isLiquid())
			{
				double d3 = d0 + (double)rand.nextFloat();
				double d5 = d1 - 1.05D;
				double d7 = d2 + (double)rand.nextFloat();
				pos.spawnParticle(EnumParticleTypes.DRIP_WATER, d3, d5, d7, 0.0D, 0.0D, 0.0D, new int[0]);
			}
		}
	}

	@SideOnly(Side.CLIENT)
	public int getPackedLightmapCoords(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		int i = source.getCombinedLight(pos, 0);
		int j = source.getCombinedLight(pos.up(), 0);
		int k = i & 255;
		int l = j & 255;
		int i1 = i >> 16 & 255;
		int j1 = j >> 16 & 255;
				return (k > l ? k : l) | (i1 > j1 ? i1 : j1) << 16;
	}
}
