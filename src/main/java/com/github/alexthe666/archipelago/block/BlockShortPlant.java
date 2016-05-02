package com.github.alexthe666.archipelago.block;

import com.github.alexthe666.archipelago.Archipelago;

import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockShortPlant extends BlockBush{
	public BlockShortPlant(String name){
		super(Material.plants);
		this.setHardness(0.0F);
		this.setStepSound(SoundType.PLANT);
		this.setUnlocalizedName("archipelago.plant." + name);
		this.setCreativeTab(Archipelago.tab);
		GameRegistry.registerBlock(this, name);
		Archipelago.PROXY.addItemRender(Item.getItemFromBlock(this), name);
	}
}
