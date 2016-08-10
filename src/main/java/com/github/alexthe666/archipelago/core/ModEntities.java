package com.github.alexthe666.archipelago.core;

import com.github.alexthe666.archipelago.Archipelago;
import com.github.alexthe666.archipelago.entity.living.EntityBannerfish;
import com.github.alexthe666.archipelago.entity.living.EntityBlacktipReefShark;
import com.github.alexthe666.archipelago.entity.living.EntityBottlenoseDolphin;
import com.github.alexthe666.archipelago.entity.living.EntityBrownCrab;
import com.github.alexthe666.archipelago.entity.living.EntityButterflyfish;
import com.github.alexthe666.archipelago.entity.living.EntityClownfish;
import com.github.alexthe666.archipelago.entity.living.EntityCoralGrouper;
import com.github.alexthe666.archipelago.entity.living.EntityNurseShark;
import com.github.alexthe666.archipelago.entity.living.EntitySergeantMajor;
import com.github.alexthe666.archipelago.entity.living.EntitySpottedEagleRay;
import com.github.alexthe666.archipelago.entity.living.EntityStingray;
import com.github.alexthe666.archipelago.entity.living.EntitySurgeonfish;
import com.github.alexthe666.archipelago.entity.living.EntityTigerShark;
import com.github.alexthe666.archipelago.entity.living.EntityWhitetipReefShark;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class ModEntities {
    public static void init() {
        registerSpawnable(EntityClownfish.class, "clownfish", 0, 0XFE7100, 0XF6F6F6);
        registerSpawnable(EntityBrownCrab.class, "brown_crab", 1, 0XB8A36E, 0X733223);
        registerSpawnable(EntityButterflyfish.class, "butterflyfish", 2, 0XF8DF17, 0XDFE6E9);
        registerSpawnable(EntityBannerfish.class, "bannerfish", 3, 0XE1E1E1, 0X171717);
        registerSpawnable(EntityStingray.class, "stingray", 4, 0XB69878, 0XFFFFFF);
        registerSpawnable(EntityNurseShark.class, "nurse_shark", 5, 0X847D68, 0XDAE2D7);
        registerSpawnable(EntitySurgeonfish.class, "surgeonfish", 6, 0X173ABA, 0XFFD800);
        registerSpawnable(EntityWhitetipReefShark.class, "whitetip_reef_shark", 7, 0X5A5A5A, 0XDEDEDE);
        registerSpawnable(EntityBlacktipReefShark.class, "blacktip_reef_shark", 8, 0X877968, 0X222222);
        registerSpawnable(EntityCoralGrouper.class, "coral_grouper", 9, 0XE61C00, 0X00BAFF);
        registerSpawnable(EntityBottlenoseDolphin.class, "bottlenose_dolphin", 10, 0X7E8186, 0X9BA0A3);
        registerSpawnable(EntitySpottedEagleRay.class, "spotted_eagle_ray", 11, 0X202152, 0XDEDEDE);
        registerSpawnable(EntityTigerShark.class, "tiger_shark", 12, 0X49443F, 0X9C9690);
        registerSpawnable(EntitySergeantMajor.class, "sergeant_major", 13, 0XB4C0C1, 0XB4C02A);
    }

    public static void registerSpawnable(Class entityClass, String name, int id, int mainColor, int subColor) {
        EntityRegistry.registerModEntity(entityClass, name, id, Archipelago.INSTANCE, 64, 3, true, mainColor, subColor);
    }

    public static void registerUnspawnable(Class entityClass, String name, int id) {
        EntityRegistry.registerModEntity(entityClass, name, id, Archipelago.INSTANCE, 64, 3, true);
    }
}
