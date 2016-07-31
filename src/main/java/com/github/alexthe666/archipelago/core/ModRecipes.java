package com.github.alexthe666.archipelago.core;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class ModRecipes {

    public static void init() {
        GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.sunstone, "XYX", "YZY", "XYX", 'X', Items.BLAZE_POWDER, 'Y', "nuggetGold", 'Z', Blocks.GLOWSTONE));
        GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.sunstone_staff, "Y  ", " X ", "  X", 'X', "stickWood", 'Y', ModItems.sunstone));
        GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.sunstone_staff, "  Y", " X ", "X  ", 'X', "stickWood", 'Y', ModItems.sunstone));
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.sunstone_staff), ModItems.sunstone_staff_broken, ModItems.sunstone);
    }
}
