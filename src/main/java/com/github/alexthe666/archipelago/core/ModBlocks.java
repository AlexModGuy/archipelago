package com.github.alexthe666.archipelago.core;

import java.lang.reflect.Field;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.GameRegistry;

import com.github.alexthe666.archipelago.block.BlockShortPlant;
import com.github.alexthe666.archipelago.block.BlockTallPlant;
import com.google.common.collect.Lists;

public class ModBlocks {

	public List<String> list = Lists.newArrayList();
	public static Block anthurium_andraeanum;
	public static Block bottle_palm;
	public static Block ceratozamia_mexicana;
	public static Block jambu;
	public static Block canary_island_foxglove;
	public static Block la_palma_sow_thistle;
	public static Block parrot_lily;

	public static void init(){
		anthurium_andraeanum = new BlockShortPlant("anthurium_andraeanum", 5);
		bottle_palm = new BlockTallPlant("bottle_palm", 5);
		ceratozamia_mexicana = new BlockTallPlant("ceratozamia_mexicana", 5);
		jambu = new BlockShortPlant("jambu", 5);
		canary_island_foxglove = new BlockTallPlant("canary_island_foxglove", 5);
		la_palma_sow_thistle =new BlockTallPlant("la_palma_sow_thistle", 5);
		parrot_lily = new BlockShortPlant("parrot_lily", 5);
	}
}
