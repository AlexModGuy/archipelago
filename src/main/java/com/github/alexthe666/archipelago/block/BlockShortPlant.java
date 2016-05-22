package com.github.alexthe666.archipelago.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.github.alexthe666.archipelago.Archipelago;
import com.github.alexthe666.archipelago.core.ModWorld;
import com.github.alexthe666.archipelago.util.PlantEntry;
import com.github.alexthe666.archipelago.world.WorldGeneratorArchipelago;

public class BlockShortPlant extends BlockBush{

	public BlockShortPlant(String name, int chance, BiomeGenBase[] biomes){
		super(Material.plants);
		this.setHardness(0.0F);
		this.setStepSound(SoundType.PLANT);
		this.setUnlocalizedName("archipelago.plant." + name);
		this.setCreativeTab(Archipelago.tab);
		GameRegistry.registerBlock(this, name);
		Archipelago.PROXY.addItemRender(Item.getItemFromBlock(this), name);
		PlantEntry entry = new PlantEntry(this, chance, false);
		for(BiomeGenBase biome : biomes){
			entry.addBiome(BiomeGenBase.getIdForBiome(biome));
		}
		WorldGeneratorArchipelago.flowersEntries.add(entry);
	}
	
    @SideOnly(Side.CLIENT)
    public Block.EnumOffsetType getOffsetType(){
        return Block.EnumOffsetType.XZ;
    }
	
}
