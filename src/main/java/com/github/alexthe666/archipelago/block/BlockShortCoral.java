package com.github.alexthe666.archipelago.block;

import com.github.alexthe666.archipelago.Archipelago;
import com.github.alexthe666.archipelago.util.PlantEntry;
import com.github.alexthe666.archipelago.world.WorldGeneratorArchipelago;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import java.lang.reflect.InvocationTargetException;

public class BlockShortCoral extends BlockBush implements ISpecialRenderedBlock {
    @SideOnly(Side.CLIENT)
    private static final Minecraft MC = Minecraft.getMinecraft();
    private String name;
    @SideOnly(Side.CLIENT)
    private TextureAtlasSprite sprite;
    @SideOnly(Side.CLIENT)
    private float minU, maxU, minV, maxV;

    public BlockShortCoral(String name, int chance, Biome[] biomes) {
        super(Material.CORAL);
        this.name = name;
        this.setHardness(0.0F);
        this.setSoundType(SoundType.PLANT);
        this.setUnlocalizedName("archipelago.plant." + name);
        this.setCreativeTab(Archipelago.tab);
        this.setLightOpacity(0);
        this.useNeighborBrightness = true;
        GameRegistry.registerBlock(this, name);
        Archipelago.PROXY.addItemRender(Item.getItemFromBlock(this), name);
        PlantEntry entry = new PlantEntry(this, chance, false);
        for (Biome biome : biomes) {
            entry.addBiome(Biome.getIdForBiome(biome));
        }
        WorldGeneratorArchipelago.coralsEntries.add(entry);
    }

    @Override
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
        if ((worldIn.getBlockState(new BlockPos(entityIn).down()).getMaterial() == Material.WATER || worldIn.getBlockState(new BlockPos(entityIn).down()).getMaterial() == Material.CORAL) && worldIn.getBlockState(pos.down()).getMaterial() == Material.WATER && entityIn.getRidingEntity() == null) {
            if (entityIn instanceof EntityLivingBase && !(entityIn instanceof EntityPlayer)) {
                EntityLivingBase living = (EntityLivingBase) entityIn;
                try {
                    ReflectionHelper.findMethod(Entity.class, entityIn, new String[] { "setFlag", "func_70052_a" }, int.class, boolean.class).invoke(living, 7, true);
                } catch (IllegalAccessException | InvocationTargetException | IllegalArgumentException e) {
                    e.printStackTrace();
                }
			}
            if (entityIn instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) entityIn;
                if (!player.capabilities.isFlying) {
                    try {
                        ReflectionHelper.findMethod(Entity.class, entityIn, new String[] { "setFlag", "func_70052_a" }, int.class, boolean.class).invoke(player, 7, true);
                    } catch (IllegalAccessException | InvocationTargetException | IllegalArgumentException e) {
                        e.printStackTrace();
                    }
					entityIn.motionX *= 1.02;
                    entityIn.motionZ *= 1.02;
                }
            }
        }
    }

    @Override
    public boolean canPlaceBlockAt(World world, BlockPos pos) {
        return checkCanStay(world.getBlockState(pos.down()), world.getBlockState(pos.up())) && world.getBlockState(pos).getMaterial() == Material.WATER;
    }

    @Override
    public boolean canBlockStay(World world, BlockPos pos, IBlockState state) {
        return this.checkCanStay(world.getBlockState(pos.down()), world.getBlockState(pos.up()));
    }

    protected boolean checkCanStay(IBlockState state, IBlockState state2) {
        return (state.getMaterial() == Material.SAND || state.getMaterial() == Material.GROUND) && state2.getBlock() instanceof BlockTropicalWater;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Block.EnumOffsetType getOffsetType() {
        return Block.EnumOffsetType.XZ;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void render(IBlockAccess world, BlockPos pos) {
        GlStateManager.pushMatrix();
        GlStateManager.enableLighting();
        int light = MC.theWorld.getCombinedLight(pos, 0);
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float) light % 65536, light / 65536.0F);
        RenderHelper.disableStandardItemLighting();
        MC.getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        float sway = (MC.thePlayer.ticksExisted + (pos.hashCode() * 0.2F)) * 0.025F;
        float swayX = (float) Math.sin(sway) / 4.0F;
        float swayZ = (float) Math.cos(sway) / 4.0F;
        if (sprite == null) {
            sprite = MC.getTextureMapBlocks().getTextureExtry(Archipelago.MODID + ":blocks/" + name);
            minU = sprite.getMinU();
            minV = sprite.getMinV();
            maxU = sprite.getMaxU();
            maxV = sprite.getMaxV();
        }
        double x = (pos.getX() + 0.5) - TileEntityRendererDispatcher.staticPlayerX;
        double y = pos.getY() - TileEntityRendererDispatcher.staticPlayerY;
        double z = (pos.getZ() + 0.5) - TileEntityRendererDispatcher.staticPlayerZ;
        GlStateManager.translate(x, y, z);
        Tessellator tessellator = Tessellator.getInstance();
        VertexBuffer buffer = tessellator.getBuffer();
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
        buffer.pos(-0.5F, 0.0F, -0.5F).tex(minU, maxV).endVertex();
        buffer.pos(0.5F, 0.0F, 0.5F).tex(maxU, maxV).endVertex();
        buffer.pos(0.5F + swayX, 1.0F, 0.5F + swayZ).tex(maxU, minV).endVertex();
        buffer.pos(-0.5F + swayX, 1.0F, -0.5F + swayZ).tex(minU, minV).endVertex();
        tessellator.draw();
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
        buffer.pos(-0.5F, 0.0F, 0.5F).tex(minU, maxV).endVertex();
        buffer.pos(0.5F, 0.0F, -0.5F).tex(maxU, maxV).endVertex();
        buffer.pos(0.5F + swayX, 1.0F, -0.5F + swayZ).tex(maxU, minV).endVertex();
        buffer.pos(-0.5F + swayX, 1.0F, 0.5F + swayZ).tex(minU, minV).endVertex();
        tessellator.draw();
        GlStateManager.popMatrix();
    }
}
