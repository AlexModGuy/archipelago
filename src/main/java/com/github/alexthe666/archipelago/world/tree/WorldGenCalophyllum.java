package com.github.alexthe666.archipelago.world.tree;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class WorldGenCalophyllum extends BasicTreeGen{
    @Override
    public boolean generateTree(World worldIn, Random rand, BlockPos position) {
        int trunkHeight = 1 + rand.nextInt(2);
        int rBranch = 1 + rand.nextInt(2);
        int lBranch = 1 + rand.nextInt(2);
        return true;
    }
}
