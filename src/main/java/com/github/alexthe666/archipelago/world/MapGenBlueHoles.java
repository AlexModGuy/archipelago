package com.github.alexthe666.archipelago.world;

import java.util.Random;

import net.minecraft.block.BlockSand;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.MapGenBase;

import com.github.alexthe666.archipelago.core.ModFluids;
import com.github.alexthe666.archipelago.core.ModWorld;
import com.google.common.base.Objects;

public class MapGenBlueHoles extends MapGenBase
{
    protected static final IBlockState BLK_AIR = Blocks.AIR.getDefaultState();
	protected static final IBlockState BLK_SANDSTONE = Blocks.SANDSTONE.getDefaultState();
	protected static final IBlockState BLK_RED_SANDSTONE = Blocks.RED_SANDSTONE.getDefaultState();

	protected void addRoom(long seed, int x, int z, ChunkPrimer primer, double x1, double y1, double z1){
		this.addTunnel(seed, x, z, primer, x1, y1, z1, 1.0F + this.rand.nextFloat() * 6.0F, 0.0F, 0.0F, -1, -1, 0.5D);
	}

	protected void addTunnel(long seed, int x, int z, ChunkPrimer primer, double x1, double y1, double z1, float x2, float y2, float z2, int integer1, int integer2, double double1){
		double d0 = (double)(x * 16 + 8);
		double d1 = (double)(z * 16 + 8);

		float f = 0.0F;
		float f1 = 0.0F;
		Random random = new Random(seed);

		if (integer2 <= 0){
			int i = this.range * 16 - 16;
			integer2 = i - random.nextInt(i / 4);
		}

		boolean flag2 = false;

		if (integer1 == -1){
			integer1 = integer2 / 2;
			flag2 = true;
		}

		int j = random.nextInt(integer2 / 2) + integer2 / 4;

		for (boolean flag = random.nextInt(6) == 0; integer1 < integer2; ++integer1){
			double d2 = 1.5D + (double)(MathHelper.sin((float)integer1 * (float)Math.PI / (float)integer2) * x2);
			double d3 = d2 * double1;
			float f2 = MathHelper.cos(z2);
			float f3 = MathHelper.sin(z2);
			x1 += (double)(MathHelper.cos(y2) * f2);
			y1 += (double)f3;
			z1 += (double)(MathHelper.sin(y2) * f2);

			if (flag){
				z2 = z2 * 0.92F;
			}
			else{
				z2 = z2 * 0.7F;
			}

			z2 = z2 + f1 * 0.1F;
			y2 += f * 0.1F;
			f1 = f1 * 0.9F;
			f = f * 0.75F;
			f1 = f1 + (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 2.0F;
			f = f + (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 4.0F;

			if (!flag2 && integer1 == j && x2 > 1.0F && integer2 > 0){
				this.addTunnel(random.nextLong(), x, z, primer, x1, y1, z1, random.nextFloat() * 0.5F + 0.5F, y2 - ((float)Math.PI / 2F), z2 / 3.0F, integer1, integer2, 1.0D);
				this.addTunnel(random.nextLong(), x, z, primer, x1, y1, z1, random.nextFloat() * 0.5F + 0.5F, y2 + ((float)Math.PI / 2F), z2 / 3.0F, integer1, integer2, 1.0D);
				return;
			}

			if (flag2 || random.nextInt(4) != 0){
				double d4 = x1 - d0;
				double d5 = z1 - d1;
				double d6 = (double)(integer2 - integer1);
				double d7 = (double)(x2 + 2.0F + 16.0F);

				if (d4 * d4 + d5 * d5 - d6 * d6 > d7 * d7){
					return;
				}

				if (x1 >= d0 - 16.0D - d2 * 2.0D && z1 >= d1 - 16.0D - d2 * 2.0D && x1 <= d0 + 16.0D + d2 * 2.0D && z1 <= d1 + 16.0D + d2 * 2.0D){
					int k2 = MathHelper.floor_double(x1 - d2) - x * 16 - 1;
					int k = MathHelper.floor_double(x1 + d2) - x * 16 + 1;
					int l2 = MathHelper.floor_double(y1 - d3) - 1;
					int l = MathHelper.floor_double(y1 + d3) + 1;
					int i3 = MathHelper.floor_double(z1 - d2) - z * 16 - 1;
					int i1 = MathHelper.floor_double(z1 + d2) - z * 16 + 1;

					if (k2 < 0){
						k2 = 0;
					}

					if (k > 16){
						k = 16;
					}

					if (l2 < 1){
						l2 = 1;
					}

					if (l > 248){
						l = 248;
					}

					if (i3 < 0){
						i3 = 0;
					}

					if (i1 > 16){
						i1 = 16;
					}

					boolean flag3 = false;

					for (int j1 = k2; !flag3 && j1 < k; ++j1){
						for (int k1 = i3; !flag3 && k1 < i1; ++k1){
							for (int l1 = l + 1; !flag3 && l1 >= l2 - 1; --l1){
								if (l1 >= 0 && l1 < 256){
									if (isOceanBlock(primer, j1, l1, k1, x, z)){
										flag3 = true;
									}

									if (l1 != l2 - 1 && j1 != k2 && j1 != k - 1 && k1 != i3 && k1 != i1 - 1){
										l1 = l2;
									}
								}
							}
						}
					}

					if (!flag3){
						BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

						for (int j3 = k2; j3 < k; ++j3){
							double d10 = ((double)(j3 + x * 16) + 0.5D - x1) / d2;

							for (int i2 = i3; i2 < i1; ++i2){
								double d8 = ((double)(i2 + z * 16) + 0.5D - z1) / d2;
								boolean flag1 = false;

								if (d10 * d10 + d8 * d8 < 1.0D){
									for (int j2 = l; j2 > l2; --j2){
										double d9 = ((double)(j2 - 1) + 0.5D - y1) / d3;

										if (d9 > -0.7D && d10 * d10 + d9 * d9 + d8 * d8 < 1.0D){
											IBlockState iblockstate1 = primer.getBlockState(j3, j2, i2);
											IBlockState iblockstate2 = (IBlockState)Objects.firstNonNull(primer.getBlockState(j3, j2 + 1, i2), BLK_AIR);

											if (isTopBlock(primer, j3, j2, i2, x, z)){
												flag1 = true;
											}

											digBlock(primer, j3, j2, i2, x, z, flag1, iblockstate1, iblockstate2);
										}
									}
								}
							}
						}

						if (flag2){
							break;
						}
					}
				}
			}
		}
	}

	protected boolean canReplaceBlock(IBlockState state, IBlockState up){
		return state.getBlock() == Blocks.STONE ? true : (state.getBlock() == Blocks.DIRT ? true : (state.getBlock() == Blocks.GRASS ? true : (state.getBlock() == Blocks.HARDENED_CLAY ? true : (state.getBlock() == Blocks.STAINED_HARDENED_CLAY ? true : (state.getBlock() == Blocks.SANDSTONE ? true : (state.getBlock() == Blocks.RED_SANDSTONE ? true : (state.getBlock() == Blocks.MYCELIUM ? true : (state.getBlock() == Blocks.SNOW_LAYER ? true : (state.getBlock() == Blocks.SAND || state.getBlock() == Blocks.GRAVEL) && up.getMaterial() != Material.WATER))))))));
	}

	protected void recursiveGenerate(World worldIn, int chunkX, int chunkZ, int p_180701_4_, int p_180701_5_, ChunkPrimer chunkPrimerIn){
		int i = this.rand.nextInt(this.rand.nextInt(this.rand.nextInt(15) + 1) + 1);
		if (this.rand.nextInt(7) != 0){
			i = 0;
		}

		for (int j = 0; j < i; ++j){
			double d0 = (double)(chunkX * 16 + this.rand.nextInt(16));
			double d1 = (double)this.rand.nextInt(this.rand.nextInt(120) + 8);
			double d2 = (double)(chunkZ * 16 + this.rand.nextInt(16));
			int k = 1;

			if (this.rand.nextInt(4) == 0){
				this.addRoom(this.rand.nextLong(), p_180701_4_, p_180701_5_, chunkPrimerIn, d0, d1, d2);
				k += this.rand.nextInt(4);
			}

			for (int l = 0; l < k; ++l){
				float f = this.rand.nextFloat() * ((float)Math.PI * 2F);
				float f1 = (this.rand.nextFloat() - 0.5F) * 2.0F / 8.0F;
				float f2 = this.rand.nextFloat() * 2.0F + this.rand.nextFloat();

				if (this.rand.nextInt(10) == 0){
					f2 *= this.rand.nextFloat() * this.rand.nextFloat() * 3.0F + 1.0F;
				}

				this.addTunnel(this.rand.nextLong(), p_180701_4_, p_180701_5_, chunkPrimerIn, d0, d1, d2, f2, f, f1, 0, 0, 1.0D);
			}
		}
	}

	protected boolean isOceanBlock(ChunkPrimer data, int x, int y, int z, int chunkX, int chunkZ){
		net.minecraft.block.Block block = data.getBlockState(x, y, z).getBlock();
		return block== Blocks.FLOWING_WATER || block == Blocks.WATER || block == ModFluids.tropical_water;
	}

	private boolean isExceptionBiome(Biome biome){
		if (biome == net.minecraft.init.Biomes.BEACH) return true;
		if (biome == net.minecraft.init.Biomes.DESERT) return true;
		return false;
	}

	private boolean isTopBlock(ChunkPrimer data, int x, int y, int z, int chunkX, int chunkZ){
		Biome biome = worldObj.getBiomeGenForCoords(new BlockPos(x + chunkX * 16, 0, z + chunkZ * 16));
		IBlockState state = data.getBlockState(x, y, z);
		return (isExceptionBiome(biome) ? state.getBlock() == Blocks.GRASS : state.getBlock() == biome.topBlock);
	}

	protected void digBlock(ChunkPrimer data, int x, int y, int z, int chunkX, int chunkZ, boolean foundTop, IBlockState state, IBlockState up){
		Biome biome = worldObj.getBiomeGenForCoords(new BlockPos(x + chunkX * 16, 0, z + chunkZ * 16));
		IBlockState top = biome.topBlock;
		IBlockState filler = biome.fillerBlock;
		if (this.canReplaceBlock(state, up) || state.getBlock() == top.getBlock() || state.getBlock() == filler.getBlock()){
			data.setBlockState(x, y, z, BLK_AIR);

			if (up.getBlock() == Blocks.SAND){
				data.setBlockState(x, y + 1, z, up.getValue(BlockSand.VARIANT) == BlockSand.EnumType.RED_SAND ? BLK_RED_SANDSTONE : BLK_SANDSTONE);
			}

			if (foundTop && data.getBlockState(x, y - 1, z).getBlock() != Blocks.SANDSTONE && data.getBlockState(x, y - 1, z).getBlock() != Blocks.AIR){
				data.setBlockState(x, y - 1, z, Blocks.SANDSTONE.getDefaultState());
			}
		}
	}
}