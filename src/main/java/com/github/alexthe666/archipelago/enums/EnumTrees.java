package com.github.alexthe666.archipelago.enums;

import net.minecraft.block.Block;
import net.minecraft.world.gen.feature.WorldGenerator;

import com.github.alexthe666.archipelago.block.BlockArchipelagoLeaves;
import com.github.alexthe666.archipelago.block.BlockArchipelagoLog;
import com.github.alexthe666.archipelago.block.BlockArchipelagoPlanks;
import com.github.alexthe666.archipelago.block.BlockArchipelagoSapling;

public enum EnumTrees {

CANARY_ISLAND_DATE_PALM(null);

public Block sapling;
public Block log;
public Block leaves;
public Block planks;
public WorldGenerator structure;

private EnumTrees(WorldGenerator structure) {
	this.structure = structure;
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
