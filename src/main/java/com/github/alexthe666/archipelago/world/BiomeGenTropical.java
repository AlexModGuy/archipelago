package com.github.alexthe666.archipelago.world;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.github.alexthe666.archipelago.enums.EnumBiomeSediment;
import com.github.alexthe666.archipelago.enums.EnumGrassColor;

public class BiomeGenTropical extends BiomeGenBase
{
	private EnumGrassColor grassColor;

	public BiomeGenTropical(String name, int id, float height, float variation, EnumGrassColor grassColor, EnumBiomeSediment biomeSediment)
	{
		super((new BiomeGenBase.BiomeProperties(name)).setBaseHeight(height).setHeightVariation(variation).setWaterColor(0X46FFFF));
		this.spawnableCreatureList.clear();
		this.spawnableWaterCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.grassColor = grassColor;
		this.topBlock = biomeSediment.topBlock.getDefaultState();
		this.fillerBlock = biomeSediment.bottomBlock.getDefaultState();
		registerBiome(id, name, this);
	}

	public BiomeGenBase.TempCategory getTempCategory()
	{
		return BiomeGenBase.TempCategory.WARM;
	}

	@SideOnly(Side.CLIENT)
	public int getGrassColorAtPos(BlockPos pos)
	{
		double d0 = (double)MathHelper.clamp_float(grassColor.tempature, 0.0F, 1.0F);
		double d1 = (double)MathHelper.clamp_float(grassColor.humidity, 0.0F, 1.0F);
		return getModdedBiomeGrassColor(ColorizerGrass.getGrassColor(d0, d1));
	}
}