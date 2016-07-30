package com.github.alexthe666.archipelago.client.gui;

import com.github.alexthe666.archipelago.block.entity.TileEntityCampfire;
import com.github.alexthe666.archipelago.inventory.ContainerCampfire;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerFurnace;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiCampfire extends GuiContainer
{
    private static final ResourceLocation TEXTURE = new ResourceLocation("archipelago:textures/gui/campfire.png");
    private final InventoryPlayer playerInventory;
    private final IInventory tileFurnace;

    public GuiCampfire(InventoryPlayer playerInv, IInventory furnaceInv) {
        super(new ContainerCampfire(playerInv, furnaceInv));
        this.playerInventory = playerInv;
        this.tileFurnace = furnaceInv;
    }

    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String s = I18n.format("tile.archipelago.campfire.name");
        this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        this.fontRendererObj.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
    }

    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(TEXTURE);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);
        int k = this.getBurnLeftScaled(13);
        this.drawTexturedModalRect(i + 84, j + 55 + 12 - k, 176, 12 - k, 14, k + 1);
        int l = this.getCookProgressScaled(24);
        this.drawTexturedModalRect(i + 79, j + 34, 176, 14, l + 1, 16);
    }

    private int getCookProgressScaled(int pixels) {
        int i = this.tileFurnace.getField(2);
        int j = this.tileFurnace.getField(1);
        return j != 0 && i != 0 ? i * pixels / j : 0;
    }

    private int getBurnLeftScaled(int pixels) {
        int i = 200;
        return this.tileFurnace.getField(0) * pixels / i;
    }
}