package com.github.alexthe666.archipelago.world.tree;

import com.github.alexthe666.archipelago.enums.EnumTrees;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class WorldGenMangrove extends BasicTreeGen {
    public WorldGenMangrove() {
        this.leavesBlock = EnumTrees.MANGROVE.leaves.getDefaultState();
        this.logBlock = EnumTrees.MANGROVE.log.getDefaultState();
    }

    @Override
    public boolean generateTree(World world, Random rand, BlockPos position) {
        this.setBlockState(world, position, Blocks.AIR.getDefaultState());
        position = position.up(4);
        int trunkHeight = rand.nextInt(2) + 3;
        for (int y = 0; y <= trunkHeight; y++) {
            this.setBlockState(world, position.up(y), this.logBlock);
        }
        this.setBlockState(world, position.up(trunkHeight).add(1, 0, 0), this.logBlock);
        BlockPos leafPosition = position.up(trunkHeight + 1);
        this.generateLeafClump(world, leafPosition, 4.5);
        for (int i = 0; i < rand.nextInt(2) + 4; i++) {
            this.generateLeafClump(world, leafPosition.add(rand.nextInt(4) - 2, 0, rand.nextInt(4) - 2), 2);
        }
        for (int i = 0; i < 8; i++) {
            EnumFacing facing = EnumFacing.getHorizontal(i % 4);
            BlockPos rootPos = position.offset(facing).down();
            int offset = 0;
            while (true) {
                this.setBlockState(world, rootPos, this.logBlock);
                rootPos = rootPos.down();
                if (rand.nextInt(10) < 9 && offset < 3) {
                    rootPos = rootPos.offset(facing, rand.nextBoolean() ? 1 : 0).offset(facing.rotateYCCW(), rand.nextBoolean() ? 1 : 0);
                    offset++;
                }
                if (rootPos.getY() < 0 || world.getBlockState(this.getRotatedPosition(rootPos)).isFullBlock()) {
                    break;
                }
            }
        }
        return true;
    }
}
