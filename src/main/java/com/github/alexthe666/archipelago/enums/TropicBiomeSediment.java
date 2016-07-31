package com.github.alexthe666.archipelago.enums;

import com.github.alexthe666.archipelago.core.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

import java.util.function.Supplier;

public enum TropicBiomeSediment {
    SANDY(() -> Blocks.SAND, () -> Blocks.SAND),
    GRASSY(() -> Blocks.GRASS, () -> Blocks.DIRT),
    VOLCANIC(() -> ModBlocks.black_sand, () -> ModBlocks.black_sand);

    public Block topBlock;
    public Block bottomBlock;
    private Supplier<Block> topBlockSupplier;
    private Supplier<Block> bottomBlockSupplier;

    TropicBiomeSediment(Supplier<Block> topBlock, Supplier<Block> bottomBlock) {
        this.topBlockSupplier = topBlock;
        this.bottomBlockSupplier = bottomBlock;
    }

    public static void init() {
        for (TropicBiomeSediment sediment : values()) {
            sediment.topBlock = sediment.topBlockSupplier.get();
            sediment.bottomBlock = sediment.bottomBlockSupplier.get();
        }
    }
}
