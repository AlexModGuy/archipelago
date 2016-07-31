package com.github.alexthe666.archipelago.world.tree;

import com.github.alexthe666.archipelago.enums.TropicTreeType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class WorldGenCanaryIslandDatePalm extends BasicTreeGen {
    public WorldGenCanaryIslandDatePalm() {
        this.leavesBlock = TropicTreeType.CANARY_ISLAND_DATE_PALM.leaves.getDefaultState();
        this.logBlock = TropicTreeType.CANARY_ISLAND_DATE_PALM.log.getDefaultState();
    }

    @Override
    public boolean generateTree(World world, Random rand, BlockPos position) {
        int maxHeight = 7 + rand.nextInt(6);
        for (int height = 0; height < maxHeight; height++) {
            this.setBlockState(world, position.up(height), this.logBlock);
        }
        for (int palmX = -1; palmX < 2; palmX++) {
            for (int palmY = -1; palmY < 1; palmY++) {
                for (int palmZ = -1; palmZ < 2; palmZ++) {
                    this.setBlockState(world, position.add(palmX, maxHeight + 1 + palmY, palmZ), this.leavesBlock);
                }
            }
        }
        this.setBlockState(world, position.up(maxHeight + 2), this.leavesBlock);
        this.setBlockState(world, position.up(maxHeight + 3), this.leavesBlock);
        this.setBlockState(world, position.up(maxHeight + 4), this.leavesBlock);

        this.setBlockState(world, position.add(1, maxHeight + 2, 0), this.leavesBlock);
        this.setBlockState(world, position.add(-1, maxHeight + 2, 0), this.leavesBlock);
        this.setBlockState(world, position.add(0, maxHeight + 2, 1), this.leavesBlock);
        this.setBlockState(world, position.add(0, maxHeight + 2, -1), this.leavesBlock);

        this.setBlockState(world, position.add(2, maxHeight + 2, 0), this.leavesBlock);
        this.setBlockState(world, position.add(-2, maxHeight + 2, 0), this.leavesBlock);
        this.setBlockState(world, position.add(0, maxHeight + 2, 2), this.leavesBlock);
        this.setBlockState(world, position.add(0, maxHeight + 2, -2), this.leavesBlock);

        this.setBlockState(world, position.add(-2, maxHeight + 3, 0), this.leavesBlock);
        this.setBlockState(world, position.add(-3, maxHeight + 3, 0), this.leavesBlock);
        this.setBlockState(world, position.add(-4, maxHeight + 2, 0), this.leavesBlock);

        this.setBlockState(world, position.add(2, maxHeight + 3, 0), this.leavesBlock);
        this.setBlockState(world, position.add(3, maxHeight + 3, 0), this.leavesBlock);
        this.setBlockState(world, position.add(4, maxHeight + 2, 0), this.leavesBlock);

        this.setBlockState(world, position.add(0, maxHeight + 3, 2), this.leavesBlock);
        this.setBlockState(world, position.add(0, maxHeight + 3, 3), this.leavesBlock);
        this.setBlockState(world, position.add(0, maxHeight + 2, 4), this.leavesBlock);

        this.setBlockState(world, position.add(0, maxHeight + 3, -2), this.leavesBlock);
        this.setBlockState(world, position.add(0, maxHeight + 3, -3), this.leavesBlock);
        this.setBlockState(world, position.add(0, maxHeight + 2, -4), this.leavesBlock);

        this.setBlockState(world, position.add(2, maxHeight, 0), this.leavesBlock);
        this.setBlockState(world, position.add(-2, maxHeight, 0), this.leavesBlock);
        this.setBlockState(world, position.add(0, maxHeight, 2), this.leavesBlock);
        this.setBlockState(world, position.add(0, maxHeight, -2), this.leavesBlock);

        this.setBlockState(world, position.add(3, maxHeight - 1, 0), this.leavesBlock);
        this.setBlockState(world, position.add(-3, maxHeight - 1, 0), this.leavesBlock);
        this.setBlockState(world, position.add(0, maxHeight - 1, 3), this.leavesBlock);
        this.setBlockState(world, position.add(0, maxHeight - 1, -3), this.leavesBlock);
        this.setBlockState(world, position.add(3, maxHeight - 2, 0), this.leavesBlock);
        this.setBlockState(world, position.add(-3, maxHeight - 2, 0), this.leavesBlock);
        this.setBlockState(world, position.add(0, maxHeight - 2, 3), this.leavesBlock);
        this.setBlockState(world, position.add(0, maxHeight - 2, -3), this.leavesBlock);
        if (rand.nextBoolean()) {
            this.setBlockState(world, position.add(3, maxHeight - 3, 0), this.leavesBlock);
            this.setBlockState(world, position.add(-3, maxHeight - 3, 0), this.leavesBlock);
            this.setBlockState(world, position.add(0, maxHeight - 3, 3), this.leavesBlock);
            this.setBlockState(world, position.add(0, maxHeight - 3, -3), this.leavesBlock);
        }
        this.setBlockState(world, position.add(2, maxHeight + 1, 2), this.leavesBlock);
        this.setBlockState(world, position.add(2, maxHeight + 2, 2), this.leavesBlock);
        this.setBlockState(world, position.add(-2, maxHeight + 1, 2), this.leavesBlock);
        this.setBlockState(world, position.add(-2, maxHeight + 2, 2), this.leavesBlock);
        this.setBlockState(world, position.add(2, maxHeight + 1, -2), this.leavesBlock);
        this.setBlockState(world, position.add(2, maxHeight + 2, -2), this.leavesBlock);
        this.setBlockState(world, position.add(-2, maxHeight + 1, -2), this.leavesBlock);
        this.setBlockState(world, position.add(-2, maxHeight + 2, -2), this.leavesBlock);

        this.setBlockState(world, position.add(3, maxHeight - 1, 3), this.leavesBlock);
        this.setBlockState(world, position.add(3, maxHeight, 3), this.leavesBlock);
        this.setBlockState(world, position.add(3, maxHeight + 1, 3), this.leavesBlock);

        this.setBlockState(world, position.add(-3, maxHeight - 1, 3), this.leavesBlock);
        this.setBlockState(world, position.add(-3, maxHeight, 3), this.leavesBlock);
        this.setBlockState(world, position.add(-3, maxHeight + 1, 3), this.leavesBlock);

        this.setBlockState(world, position.add(3, maxHeight - 1, -3), this.leavesBlock);
        this.setBlockState(world, position.add(3, maxHeight, -3), this.leavesBlock);
        this.setBlockState(world, position.add(3, maxHeight + 1, -3), this.leavesBlock);

        this.setBlockState(world, position.add(-3, maxHeight - 1, -3), this.leavesBlock);
        this.setBlockState(world, position.add(-3, maxHeight, -3), this.leavesBlock);
        this.setBlockState(world, position.add(-3, maxHeight + 1, -3), this.leavesBlock);
        return true;
    }
}
