package com.github.alexthe666.archipelago.core;

import com.github.alexthe666.archipelago.Archipelago;
import com.github.alexthe666.archipelago.entity.living.EntityClownfish;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class ModEntities {
    public static void init(){
        registerSpawnable(EntityClownfish.class, "clownfish", 0, 0XFE7100, 0XF6F6F6);
    }

    public static void registerSpawnable(Class entityClass, String name, int id, int mainColor, int subColor) {
        EntityRegistry.registerModEntity(entityClass, name, id, Archipelago.INSTANCE, 64, 3, true, mainColor, subColor);
    }

    public static void registerUnspawnable(Class entityClass, String name, int id) {
        EntityRegistry.registerModEntity(entityClass, name, id, Archipelago.INSTANCE, 64, 3, true);
    }

}
