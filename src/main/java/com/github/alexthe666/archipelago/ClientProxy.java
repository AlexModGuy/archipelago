package com.github.alexthe666.archipelago;

import com.github.alexthe666.archipelago.block.BlockArchipelagoSapling;
import com.github.alexthe666.archipelago.block.BlockBlackSandstone;
import com.github.alexthe666.archipelago.client.model.entity.*;
import com.github.alexthe666.archipelago.client.particle.TeleportFX;
import com.github.alexthe666.archipelago.client.render.entity.RenderArchipelagoAnimal;
import com.github.alexthe666.archipelago.core.ModBlocks;
import com.github.alexthe666.archipelago.core.ModFluids;
import com.github.alexthe666.archipelago.entity.living.*;
import com.github.alexthe666.archipelago.enums.TropicParticle;
import com.github.alexthe666.archipelago.enums.TropicTreeType;
import com.github.alexthe666.archipelago.event.client.ClientEvents;
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
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

import javax.annotation.Nullable;
import java.awt.*;

public class ClientProxy extends CommonProxy {
    @Override
    public void init() {
        ModelBakery.registerItemVariants(Item.getItemFromBlock(ModBlocks.black_sandstone), new ResourceLocation("archipelago:black_sandstone"), new ResourceLocation("archipelago:chiseled_black_sandstone"), new ResourceLocation("archipelago:smooth_black_sandstone"));
        Archipelago.PROXY.addItemRender(Item.getItemFromBlock(ModBlocks.black_sandstone), "black_sandstone");
        Archipelago.PROXY.addItemRenderWithMeta(Item.getItemFromBlock(ModBlocks.black_sandstone), "chiseled_black_sandstone", 1);
        Archipelago.PROXY.addItemRenderWithMeta(Item.getItemFromBlock(ModBlocks.black_sandstone), "smooth_black_sandstone", 2);
        MinecraftForge.EVENT_BUS.register(new ClientEvents());
        for (TropicTreeType tree : TropicTreeType.values()) {
            Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelShapes().registerBlockWithStateMapper(tree.leaves, (new StateMap.Builder()).ignore(new IProperty[] { BlockLeaves.CHECK_DECAY, BlockLeaves.DECAYABLE }).build());
            Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelShapes().registerBlockWithStateMapper(tree.sapling, (new StateMap.Builder()).ignore(new IProperty[] { BlockArchipelagoSapling.STAGE }).build());
        }
        Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(new IBlockColor() {
            @Override
            public int colorMultiplier(IBlockState state, @Nullable IBlockAccess worldIn, @Nullable BlockPos pos, int tintIndex) {
                return worldIn != null && pos != null ? BiomeColorHelper.getFoliageColorAtPos(worldIn, pos) : ColorizerFoliage.getFoliageColorBasic();
            }
        }, TropicTreeType.HISPANIOLAN_PINE.leaves);
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new IItemColor() {
            @Override
            public int getColorFromItemstack(ItemStack stack, int tintIndex) {
                IBlockState iblockstate = ((ItemBlock) stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata());
                return Minecraft.getMinecraft().getBlockColors().colorMultiplier(iblockstate, null, null, tintIndex);
            }
        }, TropicTreeType.HISPANIOLAN_PINE.leaves);
        Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelShapes().registerBlockWithStateMapper(ModBlocks.black_sandstone, (new StateMap.Builder()).withName(BlockBlackSandstone.TYPE).build());

        RenderingRegistry.registerEntityRenderingHandler(EntityClownfish.class, new RenderArchipelagoAnimal(new ModelClownfish(), 0.3F));
        RenderingRegistry.registerEntityRenderingHandler(EntityBrownCrab.class, new RenderArchipelagoAnimal(new ModelBrownCrab(), 0.4F));
        RenderingRegistry.registerEntityRenderingHandler(EntityButterflyfish.class, new RenderArchipelagoAnimal(new ModelButterflyfish(), 0.4F));
        RenderingRegistry.registerEntityRenderingHandler(EntityBannerfish.class, new RenderArchipelagoAnimal(new ModelBannerfish(), 0.4F));
        RenderingRegistry.registerEntityRenderingHandler(EntityStingray.class, new RenderArchipelagoAnimal(new ModelStingray(), 0.4F));
        RenderingRegistry.registerEntityRenderingHandler(EntityNurseShark.class, new RenderArchipelagoAnimal(new ModelNurseShark(), 0.4F));
        RenderingRegistry.registerEntityRenderingHandler(EntitySurgeonfish.class, new RenderArchipelagoAnimal(new ModelSurgeonfish(), 0.4F));
        RenderingRegistry.registerEntityRenderingHandler(EntityWhitetipReefShark.class, new RenderArchipelagoAnimal(new ModelWhitetipReefShark(), 0.4F));
        RenderingRegistry.registerEntityRenderingHandler(EntityBlacktipReefShark.class, new RenderArchipelagoAnimal(new ModelBlacktipReefShark(), 0.4F));
        RenderingRegistry.registerEntityRenderingHandler(EntityCoralGrouper.class, new RenderArchipelagoAnimal(new ModelCoralGrouper(), 0.4F));
        RenderingRegistry.registerEntityRenderingHandler(EntityBottlenoseDolphin.class, new RenderArchipelagoAnimal(new ModelBottlenoseDolphin(), 0.4F));
        RenderingRegistry.registerEntityRenderingHandler(EntitySpottedEagleRay.class, new RenderArchipelagoAnimal(new ModelSpottedEagleRay(), 0.4F));
        RenderingRegistry.registerEntityRenderingHandler(EntityTigerShark.class, new RenderArchipelagoAnimal(new ModelTigerShark(), 0.4F));
        RenderingRegistry.registerEntityRenderingHandler(EntitySergeantMajor.class, new RenderArchipelagoAnimal(new ModelSergeantMajor(), 0.4F));
        RenderingRegistry.registerEntityRenderingHandler(EntityGreenSeaTurtle.class, new RenderArchipelagoAnimal(new ModelGreenSeaTurtle(), 0.4F));
        RenderingRegistry.registerEntityRenderingHandler(EntityBlueMarlin.class, new RenderArchipelagoAnimal(new ModelBlueMarlin(), 0.4F));
        RenderingRegistry.registerEntityRenderingHandler(EntityTiger.class, new RenderArchipelagoAnimal(new ModelTiger(), 0.4F));
        RenderingRegistry.registerEntityRenderingHandler(EntitySaltwaterCrocodile.class, new RenderArchipelagoAnimal(new ModelSaltwaterCrocodile(), 0.4F));
        RenderingRegistry.registerEntityRenderingHandler(EntityOctopus.class, new RenderArchipelagoAnimal(new ModelOctopus(), 0.4F));
        RenderingRegistry.registerEntityRenderingHandler(EntityKraken.class, new RenderArchipelagoAnimal(new ModelKraken(), 0.4F));
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
    public void spawnParticle(TropicParticle particle, World world, float x, float y, float z, double motionX, double motionY, double motionZ) {
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
