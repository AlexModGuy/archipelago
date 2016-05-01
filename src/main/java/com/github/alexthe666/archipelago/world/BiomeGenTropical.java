package com.github.alexthe666.archipelago.world;

import java.util.Random;

import net.minecraft.block.BlockSand;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.ChunkPrimer;
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

	public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal)
	{
		int i = worldIn.getSeaLevel();
		IBlockState iblockstate = this.topBlock;
		IBlockState iblockstate1 = this.fillerBlock;
		int j = -1;
		int k = (int)(noiseVal / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
		int l = x & 15;
		int i1 = z & 15;
		BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

		for (int j1 = 255; j1 >= 0; --j1)
		{
			if (j1 <= rand.nextInt(5))
			{
				chunkPrimerIn.setBlockState(i1, j1, l, BEDROCK);
			}
			else
			{
				IBlockState iblockstate2 = chunkPrimerIn.getBlockState(i1, j1, l);

				if (iblockstate2.getMaterial() == Material.air)
				{
					j = -1;
				}
				else if (iblockstate2.getBlock() == Blocks.stone)
				{
					if (j == -1)
					{
						if (k <= 0)
						{
							iblockstate = AIR;
							iblockstate1 = STONE;
						}
						else if (j1 >= i - 4 && j1 <= i + 1)
						{
							iblockstate = this.topBlock;
							iblockstate1 = this.fillerBlock;
						}

						if (j1 < i && (iblockstate == null || iblockstate.getMaterial() == Material.air))
						{
							if (this.getFloatTemperature(blockpos$mutableblockpos.set(x, j1, z)) < 0.15F)
							{
								iblockstate = ICE;
							}
							else
							{
								iblockstate = WATER;
							}
						}

						j = k;

						if (j1 >= i - 1)
						{
							chunkPrimerIn.setBlockState(i1, j1, l, iblockstate);
						}
						else if (j1 < i - 7 - k)
						{
							iblockstate = AIR;
							iblockstate1 = STONE;
							chunkPrimerIn.setBlockState(i1, j1, l, Blocks.sand.getDefaultState());
						}
						else
						{
							chunkPrimerIn.setBlockState(i1, j1, l, iblockstate1);
						}
					}
					else if (j > 0)
					{
						--j;
						chunkPrimerIn.setBlockState(i1, j1, l, iblockstate1);

						if (j == 0 && iblockstate1.getBlock() == Blocks.sand)
						{
							j = rand.nextInt(4) + Math.max(0, j1 - 63);
							iblockstate1 = iblockstate1.getValue(BlockSand.VARIANT) == BlockSand.EnumType.RED_SAND ? RED_SANDSTONE : SANDSTONE;
						}
					}
				}
			}
		}
	}

	@SideOnly(Side.CLIENT)
	public int getGrassColorAtPos(BlockPos pos)
	{
		double d0 = (double)MathHelper.clamp_float(grassColor.tempature, 0.0F, 1.0F);
		double d1 = (double)MathHelper.clamp_float(grassColor.humidity, 0.0F, 1.0F);
		return getModdedBiomeGrassColor(ColorizerGrass.getGrassColor(d0, d1));
	}
}