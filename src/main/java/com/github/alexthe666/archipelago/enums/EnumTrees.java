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
HISPANIOLAN_PINE(null, false),
CANARY_MADRONE(null, false),
TAMBALACOQUE(null, false),
CORRIOSA(null, true),
GALAPAGOS_MICONIA(null, true),
KAPOK(null, false),
TABERNAEMONTANA_CERIFERA(null, true);

public Block log;
public Block leaves;
public Block planks;
public Block sapling;
public WorldGenerator structure;
public boolean isShrub;

private EnumTrees(WorldGenerator structure, boolean isShrub) {
	this.structure = structure;
	this.isShrub = isShrub;
}

public static void init() {
	for (EnumTrees tree : EnumTrees.values()) {
		tree.leaves = new BlockArchipelagoLeaves(tree);
		if(!tree.isShrub){
			tree.log = new BlockArchipelagoLog(tree);
			tree.planks = new BlockArchipelagoPlanks(tree);	
		}
		tree.sapling = new BlockArchipelagoSapling(tree);
	}
}
}
