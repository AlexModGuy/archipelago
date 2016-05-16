package com.github.alexthe666.archipelago.block;

import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.fml.common.registry.GameRegistry;

import com.github.alexthe666.archipelago.Archipelago;
import com.github.alexthe666.archipelago.core.ModWorld;
import com.github.alexthe666.archipelago.util.PlantEntry;
import com.github.alexthe666.archipelago.world.BiomeGenTropical;
import com.github.alexthe666.archipelago.world.GenLayerBiomesArchipelago;
import com.github.alexthe666.archipelago.world.WorldGeneratorArchipelago;

public class BlockShortCoral extends BlockBush{

	public BlockShortCoral(String name, int chance, BiomeGenBase[] biomes){
		super(Material.coral);
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

    public boolean canPlaceBlockAt(World worldIn, BlockPos pos){
        return checkCanStay(worldIn.getBlockState(pos.down()), worldIn.getBlockState(pos.up()));
    }
    
	public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state){
		return this.checkCanStay(worldIn.getBlockState(pos.down()), worldIn.getBlockState(pos.up()));
	}

	protected boolean checkCanStay(IBlockState state, IBlockState state2)
	{
		return (state.getMaterial() == Material.sand || state.getMaterial() == Material.ground) && state2.getMaterial() == Material.water;
	}
}
