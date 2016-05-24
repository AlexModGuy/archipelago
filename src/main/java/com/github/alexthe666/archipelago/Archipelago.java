package com.github.alexthe666.archipelago;

import com.github.alexthe666.archipelago.core.*;
import com.github.alexthe666.archipelago.event.server.ServerEvents;
import com.github.alexthe666.archipelago.properties.ArchipelagoEntityProperties;
import com.github.alexthe666.archipelago.world.WorldGeneratorArchipelago;
import com.github.alexthe666.archipelago.world.WorldProviderArchipelago;
import net.ilexiconn.llibrary.server.entity.EntityPropertiesHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = Archipelago.MODID, name = Archipelago.NAME, version = Archipelago.VERSION)
public class Archipelago {
    public static final String MODID = "archipelago";
    public static final String NAME = "Archipelago";
    public static final String VERSION = "alpha";
    @Instance(value = MODID)
    public static Archipelago INSTANCE;
    @SidedProxy(clientSide = "com.github.alexthe666.archipelago.ClientProxy", serverSide = "com.github.alexthe666.archipelago.CommonProxy")
    public static CommonProxy PROXY;
    public static CreativeTabs tab;
    public static DimensionType dimType;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Configuration config = new Configuration(event.getSuggestedConfigurationFile());
        config.load();
        ModConfig.load(config);
        config.save();
        dimType = DimensionType.register("Archipelago", "_archipelago", ModConfig.ARCHIPELAGO_DIMENSION_ID, WorldProviderArchipelago.class, false);
        DimensionManager.registerDimension(ModConfig.ARCHIPELAGO_DIMENSION_ID, dimType);
        ModFluids.init();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        EntityPropertiesHandler.INSTANCE.registerProperties(ArchipelagoEntityProperties.class);
        MinecraftForge.EVENT_BUS.register(new ServerEvents());
        tab = new CreativeTabs(MODID) {
            public Item getTabIconItem() {
                return ModItems.sunstone;
            }
        };
        ModItems.init();
        ModWorld.init();
        ModBlocks.init();
        ModRecipes.init();
        GameRegistry.registerWorldGenerator(new WorldGeneratorArchipelago(), 20);
        PROXY.init();
    }
}
