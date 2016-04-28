package com.github.alexthe666.archipelago;

import net.ilexiconn.llibrary.server.entity.EntityPropertiesHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import com.github.alexthe666.archipelago.core.ModItems;
import com.github.alexthe666.archipelago.core.ModRecipes;
import com.github.alexthe666.archipelago.event.server.ServerEvents;
import com.github.alexthe666.archipelago.properties.ArchipelagoEntityProperties;

@Mod(modid = Archipelago.MODID, name = Archipelago.NAME, version = Archipelago.VERSION)
public class Archipelago
{
	public static final String MODID = "archipelago";
	public static final String NAME = "Archipelago";
	public static final String VERSION = "alpha";
	@Instance(value = MODID)
	public static Archipelago instance;
	@SidedProxy(clientSide = "com.github.alexthe666.archipelago.ClientProxy", serverSide = "com.github.alexthe666.archipelago.CommonProxy")
	public static CommonProxy proxy;
	public static CreativeTabs tab;

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		tab = new CreativeTabs(MODID){
			public Item getTabIconItem() {
				return ModItems.sunstone;
			}
		};
		EntityPropertiesHandler.INSTANCE.registerProperties(ArchipelagoEntityProperties.class);
		MinecraftForge.EVENT_BUS.register(new ServerEvents());
		ModItems.init();
		ModRecipes.init();
		proxy.render();
	}
}
