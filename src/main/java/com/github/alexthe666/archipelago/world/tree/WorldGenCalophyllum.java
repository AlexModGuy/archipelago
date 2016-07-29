package com.github.alexthe666.archipelago.world.tree;

import com.github.alexthe666.archipelago.enums.EnumTrees;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class WorldGenCalophyllum extends BasicTreeGen {
    public WorldGenCalophyllum() {
        this.leavesBlock = EnumTrees.CALOPHYLLUM.leaves.getDefaultState();
        this.logBlock = EnumTrees.CALOPHYLLUM.log.getDefaultState();
    }

    @Override
    public boolean generateTree(World world, Random rand, BlockPos position) {
        position = position.up();
        int trunkHeight = rand.nextInt(2) + 2;
        int rightBranchHeight = rand.nextInt(3) + 1;
        int leftBranchHeight = rand.nextInt(3) + 1;
        for (int y = 0; y < trunkHeight; y++) {
            this.setBlockState(world, position.up(y), this.logBlock);
        }
        BlockPos topTrunk = position.add(0, trunkHeight, 0);
        BlockPos leftBranch = topTrunk.add(1, 0, 0);
        BlockPos rightBranch = topTrunk.add(-1, 0, 0);
        this.setBlockState(world, leftBranch, this.logBlock);
        for (int y = 0; y < leftBranchHeight; y++) {
            this.setBlockState(world, leftBranch.add(1, y + 1, 0), this.logBlock);
        }
        this.setBlockState(world, rightBranch.add(-1, rightBranchHeight, 0), this.logBlock);
        for (int y = 0; y < rightBranchHeight; y++) {
            this.setBlockState(world, rightBranch.add(0, y, 0), this.logBlock);
        }
        this.generateLeafClump(world, rightBranch.add(-1, rightBranchHeight + 1, 0), 5.0);
        this.generateLeafClump(world, rightBranch.add(rand.nextInt(4) - 2, rightBranchHeight + 1, rand.nextInt(4) - 2), 5.5);

        this.generateLeafClump(world, leftBranch.add(1, leftBranchHeight + 1, 0), 5.0);
        this.generateLeafClump(world, leftBranch.add(rand.nextInt(4) - 2, leftBranchHeight + 1, rand.nextInt(4) - 2), 5.5);
        return true;
    }
}
