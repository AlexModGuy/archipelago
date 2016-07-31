package com.github.alexthe666.archipelago.world.tree;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.IPlantable;

import java.util.Random;

public abstract class BasicTreeGen extends WorldGenAbstractTree {
    public IBlockState logBlock;
    public IBlockState leavesBlock;
    public int rotation;
    private BlockPos center;

    public BasicTreeGen() {
        super(false);
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos position) {
        BlockPos down = position.down();
        IBlockState state = world.getBlockState(down);
        if (state.getBlock().canSustainPlant(state, world, down, EnumFacing.UP, (IPlantable) Blocks.SAPLING)) {
            this.center = position;
            this.rotation = rand.nextInt(4);
            return this.generateTree(world, rand, position);
        }
        return false;
    }

    public abstract boolean generateTree(World world, Random rand, BlockPos position);

    public void setBlockState(World world, BlockPos position, IBlockState state) {
        world.setBlockState(this.getRotatedPosition(position), state);
    }

    protected BlockPos getRotatedPosition(BlockPos position) {
        BlockPos offset = position.subtract(this.center);
        if (offset.getX() == 0 && offset.getZ() == 0) {
            return position;
        }
        switch (this.rotation) {
            case 0:
                break;
            case 1:
                offset = new BlockPos(offset.getZ(), offset.getY(), -offset.getX());
                break;
            case 2:
                offset = new BlockPos(-offset.getX(), offset.getY(), offset.getZ());
                break;
            case 3:
                offset = new BlockPos(-offset.getZ(), offset.getY(), -offset.getX());
                break;
        }
        return this.center.add(offset);
    }

    protected void generateLeafClump(World world, BlockPos pos, double size) {
        int blockRadius = (int) Math.ceil(size);
        for (int x = -blockRadius; x < blockRadius; x++) {
            for (int y = -blockRadius; y < blockRadius; y++) {
                for (int z = -blockRadius; z < blockRadius; z++) {
                    if (Math.abs(x * x + y * y + z * z) <= size) {
                        BlockPos leafPos = pos.add(x, y, z);
                        if (world.isAirBlock(this.getRotatedPosition(leafPos))) {
                            this.setBlockState(world, leafPos, this.leavesBlock);
                        }
                    }
                }
            }
        }
    }

    protected void generateLeafClump(World world, BlockPos pos, double size, double sizeY) {
        int blockRadius = (int) Math.ceil(size);
        int yRadius = (int) Math.ceil(sizeY);
        for (int x = -blockRadius; x < blockRadius; x++) {
            for (int y = -yRadius; y < yRadius; y++) {
                for (int z = -blockRadius; z < blockRadius; z++) {
                    if (Math.abs(x * x + z * z) <= size && Math.abs(x * x + y * y + z * z) <= size * sizeY) {
                        BlockPos leafPos = pos.add(x, y, z);
                        if (world.isAirBlock(this.getRotatedPosition(leafPos))) {
                            this.setBlockState(world, leafPos, this.leavesBlock);
                        }
                    }
                }
            }
        }
    }
}