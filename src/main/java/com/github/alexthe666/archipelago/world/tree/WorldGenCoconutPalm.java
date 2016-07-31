package com.github.alexthe666.archipelago.world.tree;

import com.github.alexthe666.archipelago.enums.TropicTreeType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class WorldGenCoconutPalm extends BasicTreeGen {

    public WorldGenCoconutPalm() {
        this.leavesBlock = TropicTreeType.COCONUT_PALM.leaves.getDefaultState();
        this.logBlock = TropicTreeType.COCONUT_PALM.log.getDefaultState();
    }

    @Override
    public boolean generateTree(World world, Random rand, BlockPos position) {
        int maxHeight = 7 + rand.nextInt(2);
        int additiveheight = 0;
        for (int height = 0; height < maxHeight; height++) {
            if (additiveheight < 3) {
                if (height == 4 && additiveheight == 0) {
                    additiveheight++;
                } else if (height == 6 && additiveheight == 1) {
                    additiveheight++;
                } else if (height == 7 && additiveheight == 2) {
                    additiveheight++;
                }
            }
            this.setBlockState(world, position.up(height).north(additiveheight), this.logBlock);
        }
        BlockPos top = position.up(maxHeight).north(additiveheight);
        this.setBlockState(world, top, this.logBlock);
        this.setBlockState(world, top.up(), this.leavesBlock);
        this.setBlockState(world, top.add(1, 0, 0), this.leavesBlock);
        this.setBlockState(world, top.add(-1, 0, 0), this.leavesBlock);
        this.setBlockState(world, top.add(2, 0, 0), this.leavesBlock);
        this.setBlockState(world, top.add(-2, 0, 0), this.leavesBlock);
        this.setBlockState(world, top.add(0, 0, 1), this.leavesBlock);
        this.setBlockState(world, top.add(0, 0, -1), this.leavesBlock);
        this.setBlockState(world, top.add(0, 0, 2), this.leavesBlock);
        this.setBlockState(world, top.add(0, 0, -2), this.leavesBlock);
        this.setBlockState(world, top.add(0, -1, 3), this.leavesBlock);
        this.setBlockState(world, top.add(0, -1, -3), this.leavesBlock);
        this.setBlockState(world, top.add(3, -1, 0), this.leavesBlock);
        this.setBlockState(world, top.add(-3, -1, 0), this.leavesBlock);
        if (rand.nextBoolean()) {
            this.setBlockState(world, top.add(0, -2, 3), this.leavesBlock);
            this.setBlockState(world, top.add(0, -2, -3), this.leavesBlock);
            this.setBlockState(world, top.add(3, -2, 0), this.leavesBlock);
            this.setBlockState(world, top.add(-3, -2, 0), this.leavesBlock);
        }
        this.setBlockState(world, top.add(-1, 1, -1), this.leavesBlock);
        this.setBlockState(world, top.add(1, 1, 1), this.leavesBlock);
        this.setBlockState(world, top.add(-1, 1, 1), this.leavesBlock);
        this.setBlockState(world, top.add(1, 1, -1), this.leavesBlock);
        this.setBlockState(world, top.add(-2, 1, -2), this.leavesBlock);
        this.setBlockState(world, top.add(2, 1, 2), this.leavesBlock);
        this.setBlockState(world, top.add(-2, 1, 2), this.leavesBlock);
        this.setBlockState(world, top.add(2, 1, -2), this.leavesBlock);
        this.setBlockState(world, top.add(-3, 0, -3), this.leavesBlock);
        this.setBlockState(world, top.add(3, 0, 3), this.leavesBlock);
        this.setBlockState(world, top.add(-3, 0, 3), this.leavesBlock);
        this.setBlockState(world, top.add(3, 0, -3), this.leavesBlock);

        this.setBlockState(world, top.add(1, 2, 0), this.leavesBlock);
        this.setBlockState(world, top.add(2, 2, 0), this.leavesBlock);
        this.setBlockState(world, top.add(3, 3, 0), this.leavesBlock);
        this.setBlockState(world, top.add(4, 3, 0), this.leavesBlock);
        this.setBlockState(world, top.add(-1, 2, 0), this.leavesBlock);
        this.setBlockState(world, top.add(-2, 2, 0), this.leavesBlock);
        this.setBlockState(world, top.add(-3, 3, 0), this.leavesBlock);
        this.setBlockState(world, top.add(-4, 3, 0), this.leavesBlock);
        this.setBlockState(world, top.add(0, 2, 1), this.leavesBlock);
        this.setBlockState(world, top.add(0, 2, 2), this.leavesBlock);
        this.setBlockState(world, top.add(0, 3, 3), this.leavesBlock);
        this.setBlockState(world, top.add(0, 3, 4), this.leavesBlock);
        this.setBlockState(world, top.add(0, 2, -1), this.leavesBlock);
        this.setBlockState(world, top.add(0, 2, -2), this.leavesBlock);
        this.setBlockState(world, top.add(0, 3, -3), this.leavesBlock);
        this.setBlockState(world, top.add(0, 3, -4), this.leavesBlock);
        return true;
    }
}
