package com.github.alexthe666.archipelago.enums;

import com.github.alexthe666.archipelago.block.BlockArchipelagoLeaves;
import com.github.alexthe666.archipelago.block.BlockArchipelagoLog;
import com.github.alexthe666.archipelago.block.BlockArchipelagoPlanks;
import com.github.alexthe666.archipelago.block.BlockArchipelagoSapling;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFire;
import net.minecraft.init.Blocks;

public enum TropicTreeType {
    CANARY_ISLAND_DATE_PALM(false),
    COCONUT_PALM(false),
    CALOPHYLLUM(false),
    HISPANIOLAN_PINE(false),
    CANARY_MADRONE(false),
    TAMBALACOQUE(false),
    CORRIOSA(true),
    GALAPAGOS_MICONIA(true),
    KAPOK(false),
    TABERNAEMONTANA_CERIFERA(true),
    MANGROVE(false);

    public Block log;
    public Block leaves;
    public Block planks;
    public Block sapling;
    public boolean isShrub;

    TropicTreeType(boolean isShrub) {
        this.isShrub = isShrub;
    }

    public static void init() {
        for (TropicTreeType tree : TropicTreeType.values()) {
            if (!tree.isShrub) {
                tree.log = new BlockArchipelagoLog(tree);
                tree.planks = new BlockArchipelagoPlanks(tree);
                Blocks.FIRE.setFireInfo(tree.planks, 5, 20);
                Blocks.FIRE.setFireInfo(tree.log, 5, 5);
            }
            tree.leaves = new BlockArchipelagoLeaves(tree);
            tree.sapling = new BlockArchipelagoSapling(tree);
            Blocks.FIRE.setFireInfo(tree.leaves, 30, 60);
            Blocks.FIRE.setFireInfo(tree.sapling, 30, 60);
        }
    }
}
