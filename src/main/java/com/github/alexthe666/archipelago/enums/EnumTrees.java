package com.github.alexthe666.archipelago.enums;

import net.minecraft.block.Block;
import net.minecraft.world.gen.feature.WorldGenerator;

import com.github.alexthe666.archipelago.block.BlockArchipelagoLeaves;
import com.github.alexthe666.archipelago.block.BlockArchipelagoLog;
import com.github.alexthe666.archipelago.block.BlockArchipelagoPlanks;
import com.github.alexthe666.archipelago.block.BlockArchipelagoSapling;

public enum EnumTrees {

CANARY_ISLAND_DATE_PALM(null, false),
COCONUT_PALM(null, false),
CALOPHYLLUM(null, false),
HISPANIOLAN_PINE(null, false);


public Block sapling;
public Block log;
public Block leaves;
public Block planks;
public WorldGenerator structure;
public boolean isShrub;

private EnumTrees(WorldGenerator structure, boolean isShrub) {
	this.structure = structure;
	this.isShrub = isShrub;
}

public static void init() {
	for (EnumTrees tree : EnumTrees.values()) {
		tree.sapling = new BlockArchipelagoSapling(tree);
		tree.log = new BlockArchipelagoLog(tree);
		tree.leaves = new BlockArchipelagoLeaves(tree);
		tree.planks = new BlockArchipelagoPlanks(tree);
	}
}
}
