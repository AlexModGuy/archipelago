package com.github.alexthe666.archipelago.world.tree;

import com.github.alexthe666.archipelago.enums.TropicTreeType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class WorldGenCanaryMadrone extends BasicTreeGen {
    public WorldGenCanaryMadrone() {
        this.leavesBlock = TropicTreeType.CANARY_MADRONE.leaves.getDefaultState();
        this.logBlock = TropicTreeType.CANARY_MADRONE.log.getDefaultState();
    }

    @Override
    public boolean generateTree(World world, Random rand, BlockPos position) {
        int trunkHeight = rand.nextInt(2) + 2;
        for (int y = 0; y < trunkHeight; y++) {
            this.setBlockState(world, position.up(y), this.logBlock);
        }
        BlockPos trunkTop = position.up(trunkHeight);
        int branchLeftHeight = rand.nextInt(2) + 2;
        BlockPos branch = trunkTop.down();
        int secondaryBranch = rand.nextInt(branchLeftHeight - 1) + 1;
        for (int y = 0; y < branchLeftHeight; y++) {
            this.setBlockState(world, branch = branch.add(rand.nextBoolean() ? 1 : 0, 1, rand.nextBoolean() ? 1 : 0), this.logBlock);
            if (y == secondaryBranch) {
                BlockPos secondaryBranchPos = branch;
                for (int i = 0; i < rand.nextInt(2) + 1; i++) {
                    this.setBlockState(world, secondaryBranchPos = secondaryBranchPos.add(rand.nextBoolean() ? 1 : -1, 0, rand.nextBoolean() ? 1 : -1), this.logBlock);
                }
                this.generateLeafClump(world, secondaryBranchPos, 1.5);
            }
        }
        this.generateLeafClump(world, branch.up(), 4.0);
        this.generateLeafClump(world, branch.add(rand.nextInt(2) - 1, 1, rand.nextInt(2) - 1), 4.0);
        int branchRightHeight = rand.nextInt(2) + 2;
        branch = trunkTop.down();
        secondaryBranch = rand.nextInt(branchLeftHeight - 1) + 1;
        for (int y = 0; y < branchRightHeight; y++) {
            this.setBlockState(world, branch = branch.add(rand.nextBoolean() ? -1 : 0, 1, rand.nextBoolean() ? -1 : 0), this.logBlock);
            if (y == secondaryBranch) {
                BlockPos secondaryBranchPos = branch;
                for (int i = 0; i < rand.nextInt(2) + 1; i++) {
                    this.setBlockState(world, secondaryBranchPos = secondaryBranchPos.add(rand.nextBoolean() ? 1 : -1, 0, rand.nextBoolean() ? 1 : -1), this.logBlock);
                }
                this.generateLeafClump(world, secondaryBranchPos, 1.5);
            }
        }
        this.generateLeafClump(world, branch.up(), 4.0);
        this.generateLeafClump(world, branch.add(rand.nextInt(2) - 1, 1, rand.nextInt(2) - 1), 4.0);
        return true;
    }
}
