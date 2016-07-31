package com.github.alexthe666.archipelago.client.gui;

import com.github.alexthe666.archipelago.inventory.ContainerCampfire;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiCampfire extends GuiContainer {
    private static final ResourceLocation TEXTURE = new ResourceLocation("archipelago:textures/gui/campfire.png");
    private final InventoryPlayer playerInventory;
    private final IInventory tile;

    public GuiCampfire(InventoryPlayer playerInventory, IInventory tile) {
        super(new ContainerCampfire(playerInventory, tile));
        this.playerInventory = playerInventory;
        this.tile = tile;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String name = I18n.format("tile.archipelago.campfire.name");
        this.fontRendererObj.drawString(name, this.xSize / 2 - this.fontRendererObj.getStringWidth(name) / 2, 6, 4210752);
        this.fontRendererObj.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(TEXTURE);
        int drawX = (this.width - this.xSize) / 2;
        int drawY = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(drawX, drawY, 0, 0, this.xSize, this.ySize);
        int burnLeftScaled = this.getBurnLeftScaled(13);
        this.drawTexturedModalRect(drawX + 84, drawY + 55 + 12 - burnLeftScaled, 176, 12 - burnLeftScaled, 14, burnLeftScaled + 1);
        int cookProgressScaled = this.getCookProgressScaled(24);
        this.drawTexturedModalRect(drawX + 79, drawY + 34, 176, 14, cookProgressScaled + 1, 16);
    }

    private int getCookProgressScaled(int pixels) {
        return (int) (this.tile.getField(1) / 200.0F * pixels);
    }

    private int getBurnLeftScaled(int pixels) {
        return (int) (this.tile.getField(0) / 1000.0F * pixels);
    }
}