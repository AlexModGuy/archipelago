package com.github.alexthe666.archipelago;

import com.github.alexthe666.archipelago.block.BlockArchipelagoSapling;
import com.github.alexthe666.archipelago.client.particle.TeleportFX;
import com.github.alexthe666.archipelago.core.ModFluids;
import com.github.alexthe666.archipelago.enums.EnumParticle;
import com.github.alexthe666.archipelago.enums.EnumTrees;
import com.github.alexthe666.archipelago.event.client.ClientEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;

import javax.annotation.Nullable;

public class ClientProxy extends CommonProxy {
    @Override
    public void init() {
        MinecraftForge.EVENT_BUS.register(new ClientEvents());
        for (EnumTrees tree : EnumTrees.values()) {
            Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelShapes().registerBlockWithStateMapper(tree.leaves, (new StateMap.Builder()).ignore(new IProperty[] { BlockLeaves.CHECK_DECAY, BlockLeaves.DECAYABLE }).build());
            Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelShapes().registerBlockWithStateMapper(tree.sapling, (new StateMap.Builder()).ignore(new IProperty[] { BlockArchipelagoSapling.STAGE }).build());
        }
        Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(new IBlockColor() {
            @Override
            public int colorMultiplier(IBlockState state, @Nullable IBlockAccess worldIn, @Nullable BlockPos pos, int tintIndex) {
                return worldIn != null && pos != null ? BiomeColorHelper.getFoliageColorAtPos(worldIn, pos) : ColorizerFoliage.getFoliageColorBasic();
            }
        }, EnumTrees.HISPANIOLAN_PINE.leaves);
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new IItemColor() {
            @Override
            public int getColorFromItemstack(ItemStack stack, int tintIndex) {
                IBlockState iblockstate = ((ItemBlock) stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata());
                return Minecraft.getMinecraft().getBlockColors().colorMultiplier(iblockstate, null, null, tintIndex);
            }
        }, EnumTrees.HISPANIOLAN_PINE.leaves);
    }

    @Override
    public void addItemRender(Item item, String name) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation("archipelago:" + name, "inventory"));
    }

    @Override
    public void addItemRenderWithMeta(Item item, String name, int meta) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, meta, new ModelResourceLocation("archipelago:" + name, "inventory"));
    }

    @Override
    public boolean areLeavesFancy() {
        return Minecraft.getMinecraft().gameSettings.fancyGraphics;
    }

    @Override
    public void spawnParticle(EnumParticle particle, World world, float x, float y, float z, double motionX, double motionY, double motionZ) {
        switch (particle) {
            case TELEPORT:
                Minecraft.getMinecraft().effectRenderer.addEffect(new TeleportFX(world, x, y, z, motionX, motionY, motionZ));
            default:
                break;
        }
    }

    @Override
    public void renderFluids() {
        final ModelResourceLocation tropical_water_model = new ModelResourceLocation("archipelago:tropical_water", "fluid");

        Item item = Item.getItemFromBlock(ModFluids.tropical_water);
        ModelBakery.registerItemVariants(item);
        ModelLoader.setCustomMeshDefinition(item, new ItemMeshDefinition() {
            @Override
            public ModelResourceLocation getModelLocation(ItemStack stack) {
                return tropical_water_model;
            }
        });
        ModelLoader.setCustomStateMapper(ModFluids.tropical_water, new StateMapperBase() {
            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
                return tropical_water_model;
            }
        });
    }
}
