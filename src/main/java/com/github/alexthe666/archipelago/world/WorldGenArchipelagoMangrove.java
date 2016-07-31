package com.github.alexthe666.archipelago.world;

import com.github.alexthe666.archipelago.core.ModWorld;
import com.github.alexthe666.archipelago.world.tree.WorldGenMangrove;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenArchipelagoMangrove extends WorldGenerator {
    private WorldGenMangrove mangroveGenerator = new WorldGenMangrove();

    @Override
    public boolean generate(World world, Random rand, BlockPos position) {
        boolean generated = false;
        for (int i = 0; i < 2; ++i) {
            BlockPos pos = position.add(rand.nextInt(8) - rand.nextInt(8), 0, rand.nextInt(8) - rand.nextInt(8));
            if (world.getBiomeGenForCoords(pos) == ModWorld.mangroveSwamp) {
                if (world.isBlockLoaded(pos)) {
                    if (this.mangroveGenerator.generate(world, rand, pos.up())) {
                        generated = true;
                    }
                }
            }
        }
        return generated;
    }
}