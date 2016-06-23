package com.github.alexthe666.archipelago.block;

import java.util.List;

import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.registry.GameRegistry;

import com.github.alexthe666.archipelago.Archipelago;
import com.github.alexthe666.archipelago.enums.EnumTrees;

public class BlockArchipelagoLeaves extends BlockLeaves {

	public BlockArchipelagoLeaves(EnumTrees tree) {
		super();
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
		return java.util.Arrays.asList(new ItemStack(this));
	}

	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(DECAYABLE, Boolean.valueOf((meta & 4) == 0)).withProperty(CHECK_DECAY, Boolean.valueOf((meta & 8) > 0));
	}

	public int getMetaFromState(IBlockState state) {
		int i = 0;
		if (!((Boolean) state.getValue(DECAYABLE)).booleanValue()) {
			i |= 4;
		}

		if (((Boolean) state.getValue(CHECK_DECAY)).booleanValue()) {
			i |= 8;
		}

		return i;
	}

	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { DECAYABLE, CHECK_DECAY });
	}
}
