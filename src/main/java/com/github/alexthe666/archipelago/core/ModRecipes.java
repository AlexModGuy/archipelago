package com.github.alexthe666.archipelago.core;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class ModRecipes {

	public static void init(){
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.sunstone, new Object[] {"XYX", "YZY", "XYX", Character.valueOf('X'), Items.blaze_powder, Character.valueOf('Y'), "nuggetGold", Character.valueOf('Z'), Blocks.glowstone}));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.sunstone_staff, new Object[] {"Y  ", " X ", "  X", Character.valueOf('X'), "stickWood", Character.valueOf('Y'), ModItems.sunstone}));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.sunstone_staff, new Object[] {"  Y", " X ", "X  ", Character.valueOf('X'), "stickWood", Character.valueOf('Y'), ModItems.sunstone}));
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.sunstone_staff), ModItems.sunstone_staff_broken, ModItems.sunstone);

	}
}
