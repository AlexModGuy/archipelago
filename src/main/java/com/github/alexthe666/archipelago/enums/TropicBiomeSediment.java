package com.github.alexthe666.archipelago.enums;

import com.github.alexthe666.archipelago.core.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

import java.util.function.Supplier;

public enum TropicBiomeSediment {
    SANDY(() -> Blocks.SAND, () -> Blocks.SAND),
    GRASSY(() -> Blocks.GRASS, () -> Blocks.DIRT),
    VOLCANIC(() -> ModBlocks.black_sand, () -> ModBlocks.black_sand);

    public Supplier<Block> topBlock;
    public Supplier<Block> bottomBlock;

    TropicBiomeSediment(Supplier<Block> topBlock, Supplier<Block> bottomBlock) {
        this.topBlock = topBlock;
        this.bottomBlock = bottomBlock;
    }
}
