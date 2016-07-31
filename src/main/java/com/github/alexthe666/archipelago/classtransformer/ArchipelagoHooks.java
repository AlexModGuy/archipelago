package com.github.alexthe666.archipelago.classtransformer;

import com.github.alexthe666.archipelago.block.BlockGrowingSeaweed;
import com.github.alexthe666.archipelago.block.SpecialRenderedBlock;
import com.github.alexthe666.archipelago.core.ModConfig;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.chunk.CompiledChunk;
import net.minecraft.client.renderer.chunk.RenderChunk;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ChunkCache;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

public class ArchipelagoHooks {
    @SideOnly(Side.CLIENT)
    private static final Map<CompiledChunk, List<BlockPos>> SPECIAL_RENDERERS = new WeakHashMap<>();
    @SideOnly(Side.CLIENT)
    private static final Minecraft MC = Minecraft.getMinecraft();
    private static final Object SPECIAL_RENDERER_LOCK = new Object();

    @SideOnly(Side.CLIENT)
    private static final AxisAlignedBB BLOCK_BOUNDS = new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 1.0, 1.0);
    @SideOnly(Side.CLIENT)
    private static final AxisAlignedBB BLOCK_BOUNDS_KELP = new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 6.0, 1.0);

    public static void renderUnderwaterFog(Entity entity, IBlockState state) {
        if (entity.dimension == ModConfig.ARCHIPELAGO_DIMENSION_ID && state != null && state.getMaterial() == Material.WATER && entity instanceof EntityLivingBase) {
            if (!((EntityLivingBase) entity).isPotionActive(MobEffects.WATER_BREATHING)) {
                GlStateManager.setFogDensity(0.01F);
            }
        }
    }

    public static void rebuildChunk(CompiledChunk chunk, ChunkCache region, BlockPos pos1, BlockPos pos2) {
        if (!region.extendedLevelsInChunkCache()) {
            List<BlockPos> blocks = new LinkedList<>();
            for (BlockPos.MutableBlockPos pos : BlockPos.getAllInBoxMutable(pos1, pos2)) {
                IBlockState state = region.getBlockState(pos);
                Block block = state.getBlock();
                if (block instanceof SpecialRenderedBlock) {
                    blocks.add(new BlockPos(pos));
                }
            }
            synchronized (SPECIAL_RENDERER_LOCK) {
                if (blocks.size() == 0) {
                    SPECIAL_RENDERERS.remove(chunk);
                } else {
                    SPECIAL_RENDERERS.put(chunk, blocks);
                }
            }
        }
    }

    public static void endChunk(RenderChunk renderer) {
        synchronized (SPECIAL_RENDERER_LOCK) {
            SPECIAL_RENDERERS.remove(renderer.compiledChunk);
        }
    }

    public static void drawSpecialRenderers(ICamera camera) {
        if (MinecraftForgeClient.getRenderPass() == 0) {
            World world = MC.theWorld;
            EntityPlayerSP player = MC.thePlayer;
            synchronized (SPECIAL_RENDERER_LOCK) {
                for (Map.Entry<CompiledChunk, List<BlockPos>> entry : SPECIAL_RENDERERS.entrySet()) {
                    for (BlockPos pos : entry.getValue()) {
                        if (player.getDistanceSq(pos.getX(), pos.getY(), pos.getZ()) < 16384) {
                            IBlockState state = world.getBlockState(pos);
                            if (camera.isBoundingBoxInFrustum(state.getBlock() instanceof BlockGrowingSeaweed ? BLOCK_BOUNDS_KELP.offset(pos) : BLOCK_BOUNDS.offset(pos))) {
                                Block block = state.getBlock();
                                if (block instanceof SpecialRenderedBlock) {
                                    SpecialRenderedBlock specialRenderedBlock = (SpecialRenderedBlock) block;
                                    specialRenderedBlock.render(world, pos, state);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
