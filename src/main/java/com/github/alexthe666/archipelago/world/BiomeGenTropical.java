package com.github.alexthe666.archipelago.world;

import com.github.alexthe666.archipelago.core.ModBlocks;
import com.github.alexthe666.archipelago.core.ModWorld;
import com.github.alexthe666.archipelago.enums.EnumBiomeSediment;
import com.github.alexthe666.archipelago.enums.EnumGrassColor;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockSand;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BiomeGenTropical extends Biome {
	private EnumGrassColor grassColor;

	public BiomeGenTropical(String name, int id, float height, float variation, int waterColor, EnumGrassColor grassColor, EnumBiomeSediment biomeSediment) {
		super((new Biome.BiomeProperties(name)).setBaseHeight(height).setHeightVariation(variation).setWaterColor(waterColor));
		this.spawnableCreatureList.clear();
		this.spawnableWaterCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.grassColor = grassColor;
		this.topBlock = biomeSediment.topBlock.getDefaultState();
		this.fillerBlock = biomeSediment.bottomBlock.getDefaultState();
		registerBiome(id, name, this);
		this.theBiomeDecorator.reedsPerChunk = -1;
		this.theBiomeDecorator.grassPerChunk = 3;
	}

	@Override
	public BlockFlower.EnumFlowerType pickRandomFlower(Random rand, BlockPos pos) {
		return BlockFlower.EnumFlowerType.values()[rand.nextInt(2)];
	}

	@Override
	public Biome.TempCategory getTempCategory() {
		return Biome.TempCategory.WARM;
	}

	@Override
	public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimer, int x, int z, double noiseVal) {
		int seaLevel = worldIn.getSeaLevel();
		IBlockState topBlock = this.topBlock;
		IBlockState fillerBlock = this.fillerBlock;
		int j = -1;
		int noise = (int) (noiseVal / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
		int chunkX = x & 15;
		int chunkZ = z & 15;
		BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

		for (int y = 255; y >= 0; --y) {
			if (y <= rand.nextInt(5)) {
				chunkPrimer.setBlockState(chunkX, y, chunkZ, BEDROCK);
			} else {
				IBlockState prevState = chunkPrimer.getBlockState(chunkX, y, chunkZ);

				if (prevState.getMaterial() == Material.AIR) {
					j = -1;
				} else if (prevState.getBlock() == Blocks.STONE) {
					if (j == -1) {
						if (noise <= 0) {
							topBlock = AIR;
							fillerBlock = STONE;
						} else if (y >= seaLevel - 13 && y <= seaLevel + 1) {
							topBlock = Blocks.SAND.getDefaultState();
							fillerBlock = Blocks.SAND.getDefaultState();
						}

						if (y < seaLevel && (topBlock == null || topBlock.getMaterial() == Material.AIR)) {
							if (this.getFloatTemperature(pos.setPos(x, y, z)) < 0.15F) {
								topBlock = ICE;
							} else {
								topBlock = WATER;
							}
						}

						j = noise;
						if (y <= seaLevel - 1 && this == ModWorld.tropicReef) {
							topBlock = rand.nextInt(4) == 0 ? Blocks.SAND.getDefaultState() : ModBlocks.coral_rock.getStateFromMeta(rand.nextInt(9));
							chunkPrimer.setBlockState(chunkX, y, chunkZ, topBlock);
						} else if (y >= seaLevel - 1) {
							chunkPrimer.setBlockState(chunkX, y, chunkZ, topBlock);
						} else if (y < seaLevel - 7 - noise) {
							topBlock = AIR;
							fillerBlock = STONE;
							chunkPrimer.setBlockState(chunkX, y, chunkZ, Blocks.SAND.getDefaultState());
						} else {
							chunkPrimer.setBlockState(chunkX, y, chunkZ, fillerBlock);
						}
					} else if (j > 0) {
						--j;
						chunkPrimer.setBlockState(chunkX, y, chunkZ, fillerBlock);

						if (j == 0 && fillerBlock.getBlock() == Blocks.SAND) {
							j = rand.nextInt(4) + Math.max(0, y - 63);
							fillerBlock = fillerBlock.getValue(BlockSand.VARIANT) == BlockSand.EnumType.RED_SAND ? RED_SANDSTONE : SANDSTONE;
						}
					}
				}
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getGrassColorAtPos(BlockPos pos) {
		double d0 = MathHelper.clamp_float(grassColor.tempature, 0.0F, 1.0F);
		double d1 = MathHelper.clamp_float(grassColor.humidity, 0.0F, 1.0F);
		return getModdedBiomeGrassColor(ColorizerGrass.getGrassColor(d0, d1));
	}
}