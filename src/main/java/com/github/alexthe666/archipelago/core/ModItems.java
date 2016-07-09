package com.github.alexthe666.archipelago.core;

import net.minecraft.item.Item;

import com.github.alexthe666.archipelago.item.ItemBasic;
import com.github.alexthe666.archipelago.item.ItemSunstoneStaff;

public class ModItems {

    public static Item sunstone;
    public static Item sunstone_staff;
    public static Item sunstone_staff_broken;

    public static void init() {
        sunstone = new ItemBasic("sunstone");
        sunstone_staff = new ItemSunstoneStaff(false);
        sunstone_staff_broken = new ItemSunstoneStaff(true);
        // FluidRegistry.addBucketForFluid(ModFluids.fluid_tropical_water);
    }

}
