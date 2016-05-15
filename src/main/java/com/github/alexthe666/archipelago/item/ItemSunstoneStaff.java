package com.github.alexthe666.archipelago.item;

import com.github.alexthe666.archipelago.Archipelago;
import com.github.alexthe666.archipelago.core.ModItems;
import com.github.alexthe666.archipelago.properties.ArchipelagoEntityProperties;

import net.ilexiconn.llibrary.server.entity.EntityPropertiesHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemSunstoneStaff extends Item {

	private boolean isBroken;

	public ItemSunstoneStaff(boolean isBroken){
		this.isBroken = isBroken;
		this.setUnlocalizedName(isBroken ? "archipelago.sunstone_staff_broken" : "archipelago.sunstone_staff");
		Archipelago.PROXY.addItemRender(this, isBroken ? "sunstone_staff_broken" : "sunstone_staff");
		this.setCreativeTab(Archipelago.tab);
		this.maxStackSize = 1;
		GameRegistry.registerItem(this, isBroken ? "sunstone_staff_broken" : "sunstone_staff");
	}

	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand)
	{
        playerIn.setActiveHand(hand);
		if(isBroken){
			return new ActionResult(EnumActionResult.SUCCESS, itemStackIn);
		}else{
			itemStackIn.setItem(ModItems.sunstone_staff_broken);
			ArchipelagoEntityProperties properties = EntityPropertiesHandler.INSTANCE.getProperties(playerIn, ArchipelagoEntityProperties.class);
			properties.teleportTime = 1;
			playerIn.addPotionEffect(new PotionEffect(MobEffects.levitation, 160, 0, true, false));
			worldIn.playSound((EntityPlayer)null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.block_portal_travel, SoundCategory.NEUTRAL, 1F, 1F);
			return new ActionResult(EnumActionResult.SUCCESS, itemStackIn);
		}
	}

    public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.BOW;
    }
}
