package com.github.alexthe666.archipelago.classtransformer;

import com.github.alexthe666.archipelago.core.ModConfig;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;

public class ArchipelagoHooks {
    public static void renderUnderwaterFog(Entity entity, IBlockState state) {
        if (entity.dimension == ModConfig.ARCHIPELAGO_DIMENSION_ID && state != null && state.getMaterial() == Material.water && entity instanceof EntityLivingBase) {
            if (!((EntityLivingBase) entity).isPotionActive(MobEffects.waterBreathing)) {
                GlStateManager.setFogDensity(0.01F);
            }
        }
    }
}
