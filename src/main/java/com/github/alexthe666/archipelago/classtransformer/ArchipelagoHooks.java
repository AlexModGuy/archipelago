package com.github.alexthe666.archipelago.classtransformer;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.MethodInsnNode;

import com.github.alexthe666.archipelago.core.ModConfig;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;

public class ArchipelagoHooks {

	public static void renderUnderwaterFog(){
		GlStateManager.setFogDensity(0.01F);
	}
}
