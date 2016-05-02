package com.github.alexthe666.archipelago.world;

import net.minecraft.world.gen.MapGenBase;

public class MapGenIsland extends MapGenBase{

}
/*
int jj = 0;
int i = x << 4;
int j = z << 4;
for (int k = i; k < i + 16; k++)
{
	for (int m = j; m < j + 16; m++)
	{
		float i2 = 75;
		i2 -= (Math.sqrt((0D-k)*(0D-k) + (0D-m)*(0D-m)) / 6) + (noise.turbulence2(k / 60F, m / 60F, 4F) * 5F);
		if(i2 < 50f) { i2 = 50f; }

		for (int i3 = 0; i3 < 256; i3++)
		{
			Block i4 = Blocks.air;
			if(i2 > 67)
			{
				if(i3 < i2 - 3)
				{
					i4 = Blocks.stone;
				}
				else if(i3 < i2 - 1)
				{
					i4 = Blocks.dirt;
				}
				else if(i3 < i2)
				{
					i4 = Blocks.grass;
				}
			}
			else
			{
				if(i3 < i2 - 6 + rand.nextInt(3))
				{
					i4 = Blocks.stone;
				}
				else if(i3 < i2 - 3)
				{
					i4 = Blocks.sandstone;
				}
				else if(i3 < i2)
				{
					i4 = Blocks.sand;
				}
				else if(i3 <= 63)
				{
					i4 = ModFluids.air;
				}
			}
			getData(chunkprimer)[jj++] = (char) Block.BLOCK_STATE_IDS.get(i4.getDefaultState());
		}
	}
}
*/