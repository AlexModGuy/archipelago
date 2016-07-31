package com.github.alexthe666.archipelago.world.tree;

import com.github.alexthe666.archipelago.enums.TropicTreeType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class WorldGenTambalocoque extends BasicTreeGen {
    public WorldGenTambalocoque() {
        this.leavesBlock = TropicTreeType.TAMBALACOQUE.leaves.getDefaultState();
        this.logBlock = TropicTreeType.TAMBALACOQUE.log.getDefaultState();
    }

    @Override
    public boolean generateTree(World world, Random rand, BlockPos position) {
        int trunkHeight = rand.nextInt(7) + 8;
        for (int y = 0; y < trunkHeight + 5; y++) {
            this.setBlockState(world, position.add(0, y, 0), this.logBlock);
            this.setBlockState(world, position.add(1, y, 0), this.logBlock);
            this.setBlockState(world, position.add(0, y, 1), this.logBlock);
            if (y < trunkHeight / 1.5) {
                this.setBlockState(world, position.add(1, y, 1), this.logBlock);
            }
            if (y >= trunkHeight / 3 && y % 3 == 0) {
                boolean topSection = y >= trunkHeight;
                for (int branchIndex = 0; branchIndex < (topSection ? 4 : 1); branchIndex++) {
                    EnumFacing facing = EnumFacing.getHorizontal(topSection ? branchIndex : rand.nextInt(4));
                    BlockPos branch = position.up(y).offset(facing, facing == EnumFacing.EAST || facing == EnumFacing.SOUTH ? 1 : 0);
                    int branchLength = rand.nextInt(3) + (topSection ? 6 : 4);
                    for (int i = 0; i < branchLength; i++) {
                        this.setBlockState(world, branch = branch.offset(i >= branchLength / 2 ? rand.nextBoolean() ? facing.rotateY() : facing.rotateYCCW() : facing).up(topSection || rand.nextBoolean() ? 1 : 0), this.logBlock);
                    }
                    this.generateLeafClump(world, branch.offset(facing, 1), topSection ? 5.5 : 2);
                    this.generateLeafClump(world, branch.add(rand.nextInt(2) - 1, 0, rand.nextInt(2) - 1), topSection ? 5.5 : 2);
                }
            }
        }
        for (int rotation = 0; rotation < 4; rotation++) {
            EnumFacing facing = EnumFacing.getHorizontal(rotation);
            int height = rand.nextInt(2) + 2;
            for (int y = 0; y < height; y++) {
                this.setBlockState(world, position.offset(facing, facing == EnumFacing.EAST || facing == EnumFacing.SOUTH ? 2 : 1).up(y), this.logBlock);
            }
        }
        this.generateLeafClump(world, position.up(trunkHeight + 5), 10.0);
        return true;
    }
}
