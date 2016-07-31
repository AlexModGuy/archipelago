package com.github.alexthe666.archipelago.enums;

import com.github.alexthe666.archipelago.core.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public enum TropicBiomeSediment {

    SANDY(Blocks.SAND, Blocks.SAND), GRASSY(Blocks.GRASS, Blocks.DIRT), VOLCANIC(ModBlocks.black_sand, ModBlocks.black_sand);

    public Block topBlock;
    public Block bottomBlock;

    TropicBiomeSediment(Block topBlock, Block bottomBlock) {
        this.topBlock = topBlock;
        this.bottomBlock = bottomBlock;
    }
}
