package com.github.alexthe666.archipelago.classtransformer;

import com.github.alexthe666.archipelago.block.ISpecialRenderedBlock;
import com.github.alexthe666.archipelago.core.ModConfig;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.chunk.CompiledChunk;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

public class ArchipelagoHooks {
    @SideOnly(Side.CLIENT)
    private static final Map<CompiledChunk, List<BlockPos>> SPECIAL_RENDERERS = new WeakHashMap<CompiledChunk, List<BlockPos>>();
    @SideOnly(Side.CLIENT)
    private static final Minecraft MC = Minecraft.getMinecraft();
    private static final Object SPECIAL_RENDERER_LOCK = new Object();

    @SideOnly(Side.CLIENT)
    private static final AxisAlignedBB BLOCK_BOUNDS = new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 1.0, 1.0);

    public static void renderUnderwaterFog(Entity entity, IBlockState state) {
        if (entity.dimension == ModConfig.ARCHIPELAGO_DIMENSION_ID && state != null && state.getMaterial() == Material.water && entity instanceof EntityLivingBase) {
            if (!((EntityLivingBase) entity).isPotionActive(MobEffects.waterBreathing)) {
                GlStateManager.setFogDensity(0.01F);
            }
        }
    }

    public static void rebuildChunk(CompiledChunk chunk, IBlockAccess world, BlockPos pos1, BlockPos pos2) {
        synchronized (SPECIAL_RENDERER_LOCK) {
            if (!world.extendedLevelsInChunkCache()) {
                List<BlockPos> blocks = new ArrayList<BlockPos>();
                for (BlockPos.MutableBlockPos pos : BlockPos.getAllInBoxMutable(pos1, pos2)) {
                    IBlockState state = world.getBlockState(pos);
                    Block block = state.getBlock();
                    if (block instanceof ISpecialRenderedBlock) {
                        blocks.add(new BlockPos(pos));
                    }
                }
                SPECIAL_RENDERERS.put(chunk, blocks);
            }
        }
    }

    public static void drawSpecialRenderers(ICamera camera) {
        if (MinecraftForgeClient.getRenderPass() == 0) {
            synchronized (SPECIAL_RENDERER_LOCK) {
                World world = MC.theWorld;
                for (Map.Entry<CompiledChunk, List<BlockPos>> entry : SPECIAL_RENDERERS.entrySet()) {
                    for (BlockPos pos : entry.getValue()) {
                        IBlockState state = world.getBlockState(pos);
                        if (camera.isBoundingBoxInFrustum(BLOCK_BOUNDS.offset(pos))) {
                            Block block = state.getBlock();
                            if (block instanceof ISpecialRenderedBlock) {
                                ISpecialRenderedBlock specialRenderedBlock = (ISpecialRenderedBlock) block;
                                specialRenderedBlock.render(world, pos);
                            }
                        }
                    }
                }
            }
        }
    }
}
