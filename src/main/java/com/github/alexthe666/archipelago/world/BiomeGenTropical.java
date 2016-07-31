package com.github.alexthe666.archipelago.world;

import com.github.alexthe666.archipelago.core.ModBlocks;
import com.github.alexthe666.archipelago.core.ModWorld;
import com.github.alexthe666.archipelago.enums.TropicBiomeSediment;
import com.github.alexthe666.archipelago.enums.TropicGrassColor;
import com.google.common.base.Function;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockSand;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BiomeGenTropical extends Biome {
    float r, g, b;
    private TropicGrassColor grassColor;
    private TreeGenerator[] treeGenerators;
    private int generationChance = 10;

    public BiomeGenTropical(String name, int id, float height, float variation, int waterColor, TropicGrassColor grassColor, TropicBiomeSediment biomeSediment, TreeGenerator... treeGenerators) {
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
        this.treeGenerators = treeGenerators;
    }

    public BiomeGenTropical(String name, int id, float height, float variation, int waterColor, TropicGrassColor grassColor, TropicBiomeSediment biomeSediment, float r, float g, float b, TreeGenerator... treeGenerators) {
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
        this.r = r;
        this.g = g;
        this.b = b;
        this.treeGenerators = treeGenerators;
    }

    public BiomeGenTropical setTreesPerChunk(int treesPerChunk) {
        this.theBiomeDecorator.treesPerChunk = treesPerChunk;
        return this;
    }

    public BiomeGenTropical setGenerationChance(int generationChance) {
        this.generationChance = generationChance;
        return this;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getSkyColorByTemp(float currentTemperature) {
        if (this.r == 0 && this.g == 0 && this.b == 0) {
            currentTemperature = currentTemperature / 3.0F;
            currentTemperature = MathHelper.clamp_float(currentTemperature, -1.0F, 1.0F);
            return MathHelper.hsvToRGB(0.62222224F - currentTemperature * 0.05F, 0.5F + currentTemperature * 0.1F, 1.0F);
        } else {
            return MathHelper.rgb(this.r, this.g, this.b);
        }
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

                if (prevState.getMaterial() == Material.AIR || prevState.getMaterial() == Material.WATER) {
                    j = -1;
                } else if (prevState.getBlock() == Blocks.STONE) {
                    if (j == -1) {
                        if (y < seaLevel && (topBlock == null || topBlock.getMaterial() == Material.AIR)) {
                            if (this.getFloatTemperature(pos.setPos(x, y, z)) < 0.15F) {
                                topBlock = ICE;
                            } else {
                                topBlock = WATER;
                            }
                        }

                        j = noise;
                        if (y < seaLevel - 1 && this == ModWorld.tropicReef) {
                            topBlock = rand.nextInt(4) == 0 ? Blocks.SAND.getDefaultState() : ModBlocks.coral_rock.getStateFromMeta(rand.nextInt(9));
                            chunkPrimer.setBlockState(chunkX, y, chunkZ, topBlock);
                        } else if (y >= seaLevel - 1) {
                            chunkPrimer.setBlockState(chunkX, y, chunkZ, topBlock);
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
        double temperature = MathHelper.clamp_float(this.grassColor.temperature, 0.0F, 1.0F);
        double humidity = MathHelper.clamp_float(this.grassColor.humidity, 0.0F, 1.0F);
        return this.grassColor == TropicGrassColor.BURNT ? 0X303030 : this.getModdedBiomeGrassColor(ColorizerGrass.getGrassColor(temperature, humidity));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getFoliageColorAtPos(BlockPos pos) {
        double d0 = (double) MathHelper.clamp_float(this.getFloatTemperature(pos), 0.0F, 1.0F);
        double d1 = (double) MathHelper.clamp_float(this.getRainfall() * 2, 0.0F, 1.0F);
        return this.getModdedBiomeFoliageColor(ColorizerFoliage.getFoliageColor(d0, d1));
    }

    @Override
    public WorldGenAbstractTree genBigTreeChance(Random rand) {
        if (this.treeGenerators.length > 0) {
            int chance = 0;
            for (TreeGenerator generator : this.treeGenerators) {
                chance += generator.chance;
            }
            int chosen = rand.nextInt(chance);
            chance = 0;
            for (TreeGenerator generator : this.treeGenerators) {
                chance += generator.chance;
                if (chance >= chosen) {
                    return generator.generator.apply(rand);
                }
            }
        }
        return super.genBigTreeChance(rand);
    }

    public int getGenerationChance() {
        return this.generationChance;
    }

    public static class TreeGenerator {
        private Function<Random, WorldGenAbstractTree> generator;
        private int chance;

        public TreeGenerator(Function<Random, WorldGenAbstractTree> generator, int chance) {
            this.generator = generator;
            this.chance = chance;
        }
    }
}