package com.github.alexthe666.archipelago.world.tree;

import com.github.alexthe666.archipelago.enums.EnumTrees;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class WorldGenHispaniolan extends BasicTreeGen {
    public WorldGenHispaniolan() {
        this.leavesBlock = EnumTrees.HISPANIOLAN_PINE.leaves.getDefaultState();
        this.logBlock = EnumTrees.HISPANIOLAN_PINE.log.getDefaultState();
    }

    @Override
    public boolean generateTree(World world, Random rand, BlockPos position) {
        int trunkHeight = rand.nextInt(7) + 10;
        for (int y = 0; y < trunkHeight + 2; y++) {
            BlockPos logPosition = position.up(y);
            this.setBlockState(world, logPosition, this.logBlock);
            if (y > 2 && y % 2 == 1 && y < trunkHeight - 2) {
                EnumFacing branchFacing = EnumFacing.getHorizontal(rand.nextInt(4));
                BlockPos branchEnd = logPosition.offset(branchFacing);
                this.setBlockState(world, branchEnd, this.logBlock);
                if (rand.nextBoolean()) {
                    this.setBlockState(world, branchEnd = branchEnd.offset(branchFacing).up(), this.logBlock);
                }
                this.generateLeafClump(world, branchEnd.offset(branchFacing).up(), 1.2);
                if (rand.nextBoolean()) {
                    this.generateLeafClump(world, branchEnd.offset(branchFacing, 2).up(2), 1.2);
                }
            }
        }
        this.generateLeafClump(world, position.up(trunkHeight + 1), 4.5);
        this.generateLeafClump(world, position.up(trunkHeight + 3), 4.0);
        this.generateLeafClump(world, position.add(2, trunkHeight + 1, 0), 1);
        this.generateLeafClump(world, position.add(0, trunkHeight + 1, 2), 1);
        this.generateLeafClump(world, position.add(-2, trunkHeight + 1, 0), 1);
        this.generateLeafClump(world, position.add(0, trunkHeight + 1, -2), 1);
        return true;
    }
}
