package com.github.alexthe666.archipelago.block;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockGlowingCoral extends BlockShortCoral {
    public BlockGlowingCoral(String name, int chance, BiomeGenBase[] biomes) {
        super(name, chance, biomes);
        this.setLightLevel(0.3F);
    }

    @SideOnly(Side.CLIENT)
    public Block.EnumOffsetType getOffsetType() {
        return Block.EnumOffsetType.XZ;
    }
}
