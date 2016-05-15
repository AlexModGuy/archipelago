package com.github.alexthe666.archipelago.block;

import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.fml.common.registry.GameRegistry;

import com.github.alexthe666.archipelago.Archipelago;
import com.github.alexthe666.archipelago.core.ModWorld;
import com.github.alexthe666.archipelago.util.PlantEntry;
import com.github.alexthe666.archipelago.world.BiomeGenTropical;
import com.github.alexthe666.archipelago.world.GenLayerBiomesArchipelago;
import com.github.alexthe666.archipelago.world.WorldGeneratorArchipelago;

public class BlockShortPlant extends BlockBush{
	
	public static BiomeGenBase[] islandBiomes = {ModWorld.tropicGrasslands, ModWorld.tropicShrublands, ModWorld.tropicJungle, ModWorld.dryPeaks, ModWorld.dryScrubland};

	public BlockShortPlant(String name, int chance){
		super(Material.plants);
		this.setHardness(0.0F);
		this.setStepSound(SoundType.PLANT);
		this.setUnlocalizedName("archipelago.plant." + name);
		this.setCreativeTab(Archipelago.tab);
		GameRegistry.registerBlock(this, name);
		Archipelago.PROXY.addItemRender(Item.getItemFromBlock(this), name);
		PlantEntry entry = new PlantEntry(this, chance, false);
		for(BiomeGenBase biome : islandBiomes){
			entry.addBiome(biome);
		}
		WorldGeneratorArchipelago.flowersEntries.add(entry);
	}
}
