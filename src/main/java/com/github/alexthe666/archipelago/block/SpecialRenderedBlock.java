package com.github.alexthe666.archipelago.block;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface SpecialRenderedBlock {
    @SideOnly(Side.CLIENT)
    void render(IBlockAccess world, BlockPos pos);
}
