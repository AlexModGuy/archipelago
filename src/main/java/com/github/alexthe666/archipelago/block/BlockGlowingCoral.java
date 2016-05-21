package com.github.alexthe666.archipelago.block;

import net.minecraft.world.biome.BiomeGenBase;

public class BlockGlowingCoral extends BlockShortCoral{

	public BlockGlowingCoral(String name, int chance, BiomeGenBase[] biomes) {
		super(name, chance, biomes);
		this.setLightLevel(0.5F);
	}

}
