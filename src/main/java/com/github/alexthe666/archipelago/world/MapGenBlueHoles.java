package com.github.alexthe666.archipelago.world;

import java.util.Random;

import net.minecraft.block.BlockSand;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.MapGenBase;

import com.github.alexthe666.archipelago.core.ModFluids;
import com.google.common.base.Objects;

public class MapGenBlueHoles extends MapGenBase {
	protected static final IBlockState BLK_WATER = ModFluids.tropical_water.getDefaultState();
	protected static final IBlockState BLK_AIR = Blocks.AIR.getDefaultState();
	protected static final IBlockState BLK_SANDSTONE = Blocks.SANDSTONE.getDefaultState();
	protected static final IBlockState BLK_RED_SANDSTONE = Blocks.RED_SANDSTONE.getDefaultState();

	protected void addRoom(long seed, int chunkX, int chunkZ, ChunkPrimer primer, double im, double not, double sure) {
		this.addTunnel(seed, chunkX, chunkZ, primer, im, not, sure, 3.4F + this.rand.nextFloat() * 8.0F, 0.0F, 0.0F, -1, -1, 4D);
	}

	protected void addTunnel(long seed, int chunkX, int chunkZ, ChunkPrimer primer, double im, double not, double sure, float param1, float param2, float param3, int param4, int param5, double param6) {
		double d0 = chunkX * 16 + 8;
		double d1 = chunkZ * 16 + 8;
		float f = 0.0F;
		float f1 = 0.0F;
		Random random = new Random(seed);

		if (param5 <= 0) {
			int i = this.range * 16 - 16;
			param5 = i - random.nextInt(i / 4);
		}

		boolean flag2 = false;

		if (param4 == -1) {
			param4 = param5 / 2;
			flag2 = true;
		}

		int j = random.nextInt(param5 / 2) + param5 / 4;

		for (boolean flag = random.nextInt(6) == 0; param4 < param5; ++param4) {
			double d2 = 1.5D + MathHelper.sin(param4 * (float) Math.PI / param5) * param1;
			double d3 = d2 * param6;
			float f2 = MathHelper.cos(param3);
			float f3 = MathHelper.sin(param3);
			im += MathHelper.cos(param2) * f2;
			not += f3;
			sure += MathHelper.sin(param2) * f2;

			if (flag) {
				param3 = param3 * 0.92F;
			} else {
				param3 = param3 * 0.7F;
			}

			param3 = param3 + f1 * 0.1F;
			param2 += f * 0.1F;
			f1 = f1 * 0.9F;
			f = f * 0.75F;
			f1 = f1 + (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 2.0F;
			f = f + (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 4.0F;

			if (!flag2 && param4 == j && param1 > 1.0F && param5 > 0) {
				this.addTunnel(random.nextLong(), chunkX, chunkZ, primer, im, not, sure, random.nextFloat() * 0.5F + 3.5F, param2 - ((float) Math.PI / 2F), param3 / 3.0F, param4, param5, 1.0D);
				this.addTunnel(random.nextLong(), chunkX, chunkZ, primer, im, not, sure, random.nextFloat() * 0.5F + 3.5F, param2 + ((float) Math.PI / 2F), param3 / 3.0F, param4, param5, 1.0D);
				return;
			}

			if (flag2 || random.nextInt(4) != 0) {
				double d4 = im - d0;
				double d5 = sure - d1;
				double d6 = param5 - param4;
				double d7 = param1 + 2.0F + 16.0F;

				if (d4 * d4 + d5 * d5 - d6 * d6 > d7 * d7) {
					return;
				}

				if (im >= d0 - 16.0D - d2 * 2.0D && sure >= d1 - 16.0D - d2 * 2.0D && im <= d0 + 16.0D + d2 * 2.0D && sure <= d1 + 16.0D + d2 * 2.0D) {
					int k2 = MathHelper.floor_double(im - d2) - chunkX * 16 - 1;
					int k = MathHelper.floor_double(im + d2) - chunkX * 16 + 1;
					int l2 = MathHelper.floor_double(not - d3) - 1;
					int l = MathHelper.floor_double(not + d3) + 1;
					int i3 = MathHelper.floor_double(sure - d2) - chunkZ * 16 - 1;
					int i1 = MathHelper.floor_double(sure + d2) - chunkZ * 16 + 1;

					if (k2 < 0) {
						k2 = 0;
					}

					if (k > 16) {
						k = 16;
					}

					if (l2 < 1) {
						l2 = 1;
					}

					if (l > 248) {
						l = 248;
					}

					if (i3 < 0) {
						i3 = 0;
					}

					if (i1 > 16) {
						i1 = 16;
					}

					boolean flag3 = false;

					for (int j1 = k2; !flag3 && j1 < k; ++j1) {
						for (int k1 = i3; !flag3 && k1 < i1; ++k1) {
							for (int l1 = l + 1; !flag3 && l1 >= l2 - 1; --l1) {
								if (l1 >= 0 && l1 < 256) {
									if (isOceanBlock(primer, j1, l1, k1, chunkX, chunkZ)) {
										flag3 = true;
									}

									if (l1 != l2 - 1 && j1 != k2 && j1 != k - 1 && k1 != i3 && k1 != i1 - 1) {
										l1 = l2;
									}
								}
							}
						}
					}

					if (!flag3) {
						BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

						for (int j3 = k2; j3 < k; ++j3) {
							double d10 = (j3 + chunkX * 16 + 0.5D - im) / d2;

							for (int i2 = i3; i2 < i1; ++i2) {
								double d8 = (i2 + chunkZ * 16 + 0.5D - sure) / d2;
								boolean flag1 = true;

								if (d10 * d10 + d8 * d8 < 1.0D) {
									for (int j2 = l; j2 > l2; --j2) {
										double d9 = (j2 - 1 + 0.5D - not) / d3;

										if (d9 > -0.7D && d10 * d10 + d9 * d9 + d8 * d8 < 1.0D) {
											IBlockState iblockstate1 = primer.getBlockState(j3, j2, i2);
											IBlockState iblockstate2 = Objects.firstNonNull(primer.getBlockState(j3, j2 + 1, i2), BLK_WATER);

											digBlock(primer, j3, j2, i2, chunkX, chunkZ, flag1, iblockstate1, iblockstate2);
										}
									}
								}
							}
						}

						if (flag2) {
							break;
						}
					}
				}
			}
		}
	}

	protected boolean canReplaceBlock(IBlockState blockstate, IBlockState blockstate2) {
		if (blockstate.getBlock() == Blocks.SAND || blockstate.getBlock() == ModFluids.tropical_water) {
			return true;
		}
		return blockstate.getBlock() == Blocks.STONE ? true : (blockstate.getBlock() == Blocks.DIRT ? true : (blockstate.getBlock() == Blocks.GRASS ? true : (blockstate.getBlock() == Blocks.HARDENED_CLAY ? true : (blockstate.getBlock() == Blocks.STAINED_HARDENED_CLAY ? true : (blockstate.getBlock() == Blocks.SANDSTONE ? true : (blockstate.getBlock() == Blocks.RED_SANDSTONE ? true : (blockstate.getBlock() == Blocks.MYCELIUM ? true : (blockstate.getBlock() == Blocks.SNOW_LAYER ? true : (blockstate.getBlock() == Blocks.SAND || blockstate.getBlock() == Blocks.GRAVEL) && blockstate2.getMaterial() != Material.WATER))))))));
	}

	@Override
	protected void recursiveGenerate(World worldIn, int chunkX, int chunkZ, int x, int z, ChunkPrimer chunkPrimerIn) {
		int i = this.rand.nextInt(this.rand.nextInt(this.rand.nextInt(15) + 1) + 1);

		if (this.rand.nextInt(2) != 0) {
			i = 0;
		}

		for (int j = 0; j < i; ++j) {
			double d0 = chunkX * 16 + this.rand.nextInt(16);
			double d1 = this.rand.nextInt(this.rand.nextInt(120) + 8);
			double d2 = chunkZ * 16 + this.rand.nextInt(16);
			if (this.rand.nextInt(2) == 0) {
				this.addRoom(this.rand.nextLong(), x, z, chunkPrimerIn, d0, d1, d2);
			}
		}
	}

	protected boolean isOceanBlock(ChunkPrimer data, int x, int y, int z, int chunkX, int chunkZ) {
		net.minecraft.block.Block block = data.getBlockState(x, y, z).getBlock();
		return block == ModFluids.tropical_water;
	}

	private boolean isTopBlock(ChunkPrimer data, int x, int y, int z, int chunkX, int chunkZ) {
		net.minecraft.world.biome.Biome biome = worldObj.getBiomeGenForCoords(new BlockPos(x + chunkX * 16, 0, z + chunkZ * 16));
		IBlockState state = data.getBlockState(x, y, z);
		return state.getBlock() == Blocks.SAND;
	}

	protected void digBlock(ChunkPrimer data, int x, int y, int z, int chunkX, int chunkZ, boolean foundTop, IBlockState state, IBlockState up) {
		net.minecraft.world.biome.Biome biome = worldObj.getBiomeGenForCoords(new BlockPos(x + chunkX * 16, 0, z + chunkZ * 16));
		IBlockState filler = biome.fillerBlock;
		if (y < 61) {
			data.setBlockState(x, y, z, BLK_WATER);

			if (up.getBlock() == BLK_AIR) {
				data.setBlockState(x, y + 1, z, up.getValue(BlockSand.VARIANT) == BlockSand.EnumType.RED_SAND ? BLK_RED_SANDSTONE : BLK_SANDSTONE);
			}

			if (foundTop && data.getBlockState(x, y - 1, z).getBlock() == filler.getBlock()) {
				data.setBlockState(x, y + 1, z, BLK_WATER);
				data.setBlockState(x, y + 2, z, BLK_WATER);
				data.setBlockState(x, y + 3, z, BLK_WATER);

			}
		}
	}
}