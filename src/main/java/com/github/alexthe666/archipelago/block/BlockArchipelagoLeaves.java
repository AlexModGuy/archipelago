package com.github.alexthe666.archipelago.block;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.github.alexthe666.archipelago.Archipelago;
import com.github.alexthe666.archipelago.enums.EnumTrees;

public class BlockArchipelagoLeaves extends BlockLeaves {
	private EnumTrees tree;
	private int[] surroundings;

	public BlockArchipelagoLeaves(EnumTrees tree) {
		super();
		this.tree = tree;
		this.setCreativeTab(Archipelago.tab);
		this.setUnlocalizedName("archipelago." + tree.name().toLowerCase() + "_leaves");
		this.setCreativeTab(Archipelago.tab);
		GameRegistry.registerBlock(this, tree.name().toLowerCase() + "_leaves");
		Archipelago.PROXY.addItemRender(Item.getItemFromBlock(this), tree.name().toLowerCase() + "_leaves");
	}

	@Override
	public EnumType getWoodType(int meta) {
		return EnumType.OAK;
	}

	@Override
	public List<ItemStack> onSheared(ItemStack item, net.minecraft.world.IBlockAccess world, BlockPos pos, int fortune) {
		return Collections.singletonList(new ItemStack(this));
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(DECAYABLE, (meta & 1) == 0).withProperty(CHECK_DECAY, (meta & 2) > 0);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int i = 0;
		if (!(Boolean) state.getValue(DECAYABLE)) {
			i |= 1;
		}

		if ((Boolean) state.getValue(CHECK_DECAY)) {
			i |= 2;
		}

		return i;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, DECAYABLE, CHECK_DECAY);
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return Blocks.LEAVES.isOpaqueCube(state);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public boolean shouldSideBeRendered(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
		return Blocks.LEAVES.shouldSideBeRendered(state, world, pos, side);
	}

	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if (!worldIn.isRemote) {
			if (((Boolean) state.getValue(CHECK_DECAY)).booleanValue() && ((Boolean) state.getValue(DECAYABLE)).booleanValue()) {
				int i = 4;
				int j = 5;
				int k = pos.getX();
				int l = pos.getY();
				int i1 = pos.getZ();
				int j1 = 32;
				int k1 = 1024;
				int l1 = 16;

				if (this.surroundings == null) {
					this.surroundings = new int[32768];
				}

				if (worldIn.isAreaLoaded(new BlockPos(k - 9, l - 9, i1 - 9), new BlockPos(k + 9, l + 9, i1 + 9))) {
					BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

					for (int i2 = -8; i2 <= 8; ++i2) {
						for (int j2 = -8; j2 <= 8; ++j2) {
							for (int k2 = -8; k2 <= 8; ++k2) {
								IBlockState iblockstate = worldIn.getBlockState(blockpos$mutableblockpos.setPos(k + i2, l + j2, i1 + k2));
								Block block = iblockstate.getBlock();

								if (!block.canSustainLeaves(iblockstate, worldIn, blockpos$mutableblockpos.setPos(k + i2, l + j2, i1 + k2))) {
									if (block.isLeaves(iblockstate, worldIn, blockpos$mutableblockpos.setPos(k + i2, l + j2, i1 + k2))) {
										this.surroundings[(i2 + 16) * 1024 + (j2 + 16) * 32 + k2 + 16] = -2;
									} else {
										this.surroundings[(i2 + 16) * 1024 + (j2 + 16) * 32 + k2 + 16] = -1;
									}
								} else {
									this.surroundings[(i2 + 16) * 1024 + (j2 + 16) * 32 + k2 + 16] = 0;
								}
							}
						}
					}

					for (int i3 = 1; i3 <= 8; ++i3) {
						for (int j3 = -8; j3 <= 8; ++j3) {
							for (int k3 = -8; k3 <= 8; ++k3) {
								for (int l3 = -8; l3 <= 8; ++l3) {
									if (this.surroundings[(j3 + 16) * 1024 + (k3 + 16) * 32 + l3 + 16] == i3 - 1) {
										if (this.surroundings[(j3 + 16 - 1) * 1024 + (k3 + 16) * 32 + l3 + 16] == -2) {
											this.surroundings[(j3 + 16 - 1) * 1024 + (k3 + 16) * 32 + l3 + 16] = i3;
										}

										if (this.surroundings[(j3 + 16 + 1) * 1024 + (k3 + 16) * 32 + l3 + 16] == -2) {
											this.surroundings[(j3 + 16 + 1) * 1024 + (k3 + 16) * 32 + l3 + 16] = i3;
										}

										if (this.surroundings[(j3 + 16) * 1024 + (k3 + 16 - 1) * 32 + l3 + 16] == -2) {
											this.surroundings[(j3 + 16) * 1024 + (k3 + 16 - 1) * 32 + l3 + 16] = i3;
										}

										if (this.surroundings[(j3 + 16) * 1024 + (k3 + 16 + 1) * 32 + l3 + 16] == -2) {
											this.surroundings[(j3 + 16) * 1024 + (k3 + 16 + 1) * 32 + l3 + 16] = i3;
										}

										if (this.surroundings[(j3 + 16) * 1024 + (k3 + 16) * 32 + (l3 + 16 - 1)] == -2) {
											this.surroundings[(j3 + 16) * 1024 + (k3 + 16) * 32 + (l3 + 16 - 1)] = i3;
										}

										if (this.surroundings[(j3 + 16) * 1024 + (k3 + 16) * 32 + l3 + 16 + 1] == -2) {
											this.surroundings[(j3 + 16) * 1024 + (k3 + 16) * 32 + l3 + 16 + 1] = i3;
										}
									}
								}
							}
						}
					}
				}

				int l2 = this.surroundings[16912];

				if (l2 >= 0) {
					worldIn.setBlockState(pos, state.withProperty(CHECK_DECAY, Boolean.valueOf(false)), 4);
				} else {
					this.dropBlockAsItem(worldIn, pos, worldIn.getBlockState(pos), 0);
					worldIn.setBlockToAir(pos);
				}
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return Blocks.LEAVES.getBlockLayer();
	}

	@Override
	public boolean isVisuallyOpaque() {
		return true;
	}

}
