package com.github.alexthe666.archipelago.enums;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public enum EnumBiomeSediment {

	SANDY(Blocks.sand, Blocks.sand),
	GRASSY(Blocks.grass, Blocks.dirt);
	
	public Block topBlock;
	public Block bottomBlock;
	private EnumBiomeSediment(Block topBlock, Block bottomBlock){
		this.topBlock = topBlock;
		this.bottomBlock = bottomBlock;
	}
}
