package com.github.alexthe666.archipelago.block;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
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
	private byte[] adjacentTreeBlocks;

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

	@Nullable
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Item.getItemFromBlock(tree.sapling);
	}

	@Override
	public List<ItemStack> onSheared(ItemStack item, net.minecraft.world.IBlockAccess world, BlockPos pos, int fortune) {
		return Collections.singletonList(new ItemStack(this));
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(DECAYABLE, (meta & 4) == 0).withProperty(CHECK_DECAY, (meta & 8) > 0);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int i = 0;
		if (!(Boolean) state.getValue(DECAYABLE)) {
			i |= 4;
		}

		if ((Boolean) state.getValue(CHECK_DECAY)) {
			i |= 8;
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

	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		if (!world.isRemote) {
			if (((Boolean) state.getValue(CHECK_DECAY)).booleanValue() && ((Boolean) state.getValue(DECAYABLE)).booleanValue()) {
				byte scanArea = 4;
				int i = scanArea + 1;
				byte size = 32;
				int sizeSquared = size * size;
				int center = size / 2;
				if (this.adjacentTreeBlocks == null) {
					this.adjacentTreeBlocks = new byte[size * size * size];
				}

				if (world.isAreaLoaded(pos.add(-i, -i, -i), pos.add(i, i, i))) {
					for (int offsetX = -scanArea; offsetX <= scanArea; ++offsetX) {
						for (int offsetY = -scanArea; offsetY <= scanArea; ++offsetY) {
							for (int offsetZ = -scanArea; offsetZ <= scanArea; ++offsetZ) {
								Block block = world.getBlockState(pos.add(offsetX, offsetY, offsetZ)).getBlock();
								if (!block.canSustainLeaves(world.getBlockState(pos.add(offsetX, offsetY, offsetZ)), world, pos.add(offsetX, offsetY, offsetZ))) {
									if (block.isLeaves(world.getBlockState(pos.add(offsetX, offsetY, offsetZ)), world, pos.add(offsetX, offsetY, offsetZ))) {
										this.adjacentTreeBlocks[(offsetX + center) * sizeSquared + (offsetY + center) * size + offsetZ + center] = -2;
									} else {
										this.adjacentTreeBlocks[(offsetX + center) * sizeSquared + (offsetY + center) * size + offsetZ + center] = -1;
									}
								} else {
									this.adjacentTreeBlocks[(offsetX + center) * sizeSquared + (offsetY + center) * size + offsetZ + center] = 0;
								}
							}
						}
					}
					for (int blockType = 1; blockType <= 4; ++blockType) {
						for (int offsetX = -scanArea; offsetX <= scanArea; ++offsetX) {
							for (int offsetY = -scanArea; offsetY <= scanArea; ++offsetY) {
								for (int offsetZ = -scanArea; offsetZ <= scanArea; ++offsetZ) {
									if (this.adjacentTreeBlocks[(offsetX + center) * sizeSquared + (offsetY + center) * size + offsetZ + center] == blockType - 1) {
										for (int adjacentX = -1; adjacentX <= 1; adjacentX++) {
											for (int adjacentY = -1; adjacentY <= 1; adjacentY++) {
												for (int adjacentZ = -1; adjacentZ <= 1; adjacentZ++) {
													int index = (offsetX + center + adjacentX) * sizeSquared + (offsetY + center + adjacentY) * size + offsetZ + center + adjacentZ;
													if (this.adjacentTreeBlocks[index] == -2) {
														this.adjacentTreeBlocks[index] = (byte) blockType;
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
				if (this.adjacentTreeBlocks[center * sizeSquared + center * size + center] >= 0) {
					world.setBlockState(pos, state.withProperty(CHECK_DECAY, Boolean.valueOf(false)), 4);
				} else {
					this.dropBlockAsItem(world, pos, world.getBlockState(pos), 0);
					world.setBlockToAir(pos);
					if(new Random().nextInt(5) == 0){
						spawnAsEntity(world, pos, new ItemStack(Items.STICK));
					}
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
