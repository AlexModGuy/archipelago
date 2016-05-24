package com.github.alexthe666.archipelago.core;

import com.github.alexthe666.archipelago.Archipelago;
import com.github.alexthe666.archipelago.block.BlockTropicalWater;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModFluids {

    public static Block tropical_water;
    public static Fluid fluid_tropical_water;

    public static void init() {
        fluid_tropical_water = new Fluid("tropical_water", new ResourceLocation("archipelago", "blocks/tropical_water_still"), new ResourceLocation("archipelago", "blocks/tropical_water_flowing"));
        FluidRegistry.registerFluid(fluid_tropical_water);
        tropical_water = new BlockTropicalWater(fluid_tropical_water, Material.water).setUnlocalizedName("archipelago.tropical_water");
        GameRegistry.registerBlock(tropical_water, "tropical_water");
        Archipelago.PROXY.renderFluids();
    }
}