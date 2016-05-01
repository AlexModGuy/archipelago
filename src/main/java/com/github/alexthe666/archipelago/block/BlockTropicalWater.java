package com.github.alexthe666.archipelago.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockTropicalWater extends BlockFluidClassic{

	public BlockTropicalWater(Fluid fluid, Material material) {
		super(fluid, material);
	}


    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
    	 IBlockState neighbor = blockAccess.getBlockState(pos.offset(side));
         if (neighbor.getMaterial() == blockState.getMaterial()){
             return false;
         }else{
             return side == EnumFacing.UP ? true : super.shouldSideBeRendered(blockState, blockAccess, pos, side);
         }
    }
}
