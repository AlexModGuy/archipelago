package com.github.alexthe666.archipelago.item.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockBlackSandstone extends ItemBlock {

    public ItemBlockBlackSandstone(Block block) {
        super(block);
    }

    public String getUnlocalizedName(ItemStack stack) {
        switch (stack.getMetadata()) {
            case 1:
                return "tile.archipelago.black_sandstone_chiseled";
            case 2:
                return "tile.archipelago.black_sandstone_smooth";
        }
        return this.block.getUnlocalizedName();
    }
}
