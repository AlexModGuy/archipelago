package com.github.alexthe666.archipelago.block;

import com.github.alexthe666.archipelago.Archipelago;
import com.github.alexthe666.archipelago.util.PlantEntry;
import com.github.alexthe666.archipelago.world.WorldGeneratorArchipelago;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
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
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Random;

public class BlockGrowingSeaweed extends BlockBush implements ISpecialRenderedBlock {
    private String name;

    @SideOnly(Side.CLIENT)
    private static final Minecraft MC = Minecraft.getMinecraft();

    @SideOnly(Side.CLIENT)
    private TextureAtlasSprite[] sprite = new TextureAtlasSprite[Part.values().length];
    @SideOnly(Side.CLIENT)
    private float[] minU = new float[Part.values().length], maxU = new float[Part.values().length], minV = new float[Part.values().length], maxV = new float[Part.values().length];

    public static final PropertyEnum<Part> PART = PropertyEnum.create("part", Part.class);
    private int height;

    public BlockGrowingSeaweed(String name, int chance, int height, BiomeGenBase[] biomes) {
        super(Material.coral);
        this.setHardness(0.0F);
        this.setStepSound(SoundType.PLANT);
        this.setDefaultState(this.blockState.getBaseState().withProperty(PART, Part.MIDDLE));
        this.setUnlocalizedName("archipelago.plant." + name);
        this.setCreativeTab(Archipelago.tab);
        this.setLightOpacity(0);
        this.useNeighborBrightness = true;
        this.name = name;
        this.height = height;
        this.setTickRandomly(true);
        GameRegistry.registerBlock(this, name);
        Archipelago.PROXY.addItemRender(Item.getItemFromBlock(this), name);
        PlantEntry entry = new PlantEntry(this, chance, false);
        for (BiomeGenBase biome : biomes) {
            entry.addBiome(BiomeGenBase.getIdForBiome(biome));
        }
        WorldGeneratorArchipelago.kelpEntries.add(entry);
    }

    public AxisAlignedBB getCollisionBoundingBox(IBlockState worldIn, World pos, BlockPos state) {
        return field_185515_b;
    }

    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        worldIn.setBlockState(pos, this.getDefaultState().withProperty(PART, Part.LOWER), 2);
        worldIn.setBlockState(pos.up(), this.getDefaultState().withProperty(PART, Part.UPPER), 2);
    }

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return new AxisAlignedBB(0.30000001192092896D, 0.0D, 0.30000001192092896D, 0.699999988079071D, 1D, 0.699999988079071D);
    }

    public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
        return worldIn.getBlockState(pos.down()).getMaterial() == Material.sand && worldIn.getBlockState(pos).getBlock() instanceof BlockTropicalWater;
    }

    public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state) {
        return canGrow(worldIn, pos);
    }

    public boolean canGrow(World world, BlockPos pos) {
        if (world.isAirBlock(pos.up())) {
            return false;
        }

        if (world.getBlockState(pos).getMaterial() != Material.water) {
            return false;
        }
        if (world.getBlockState(pos).getBlock() == this) {
            if (world.getBlockState(pos).getValue(PART) == Part.LOWER && world.getBlockState(pos.down()).getMaterial() != Material.sand) {
                return false;
            }
        }
        return true;
    }

    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
        IBlockState blockstate = world.getBlockState(pos);
        IBlockState blockstate1 = world.getBlockState(pos.down());
        if (rand.nextInt(3) == 0) {
            if (canGrow(world, pos) && world.getBlockState(pos.up()).getBlock() != this) {
                world.setBlockState(pos.up(), blockstate.withProperty(PART, Part.UPPER), 2);
            }
        }
        if (world.getBlockState(pos.up()).getBlock() == this) {
            if (world.getBlockState(pos).getValue(PART) == Part.UPPER && world.getBlockState(pos.up()).getValue(PART) == Part.UPPER) {
                world.setBlockState(pos, blockstate.withProperty(PART, Part.MIDDLE), 2);
            }
        }
    }

    public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
        IBlockState state2 = worldIn.getBlockState(pos);
        IBlockState state3 = worldIn.getBlockState(pos.down());
        if (state2.getBlock() == this) {
            if (state2.getValue(PART) == Part.LOWER && state3.getMaterial() != Material.sand) {
                this.checkAndDropBlock(worldIn, pos, state);
            }
            if (state2.getValue(PART) != Part.LOWER && state3.getBlock() != this) {
                this.checkAndDropBlock(worldIn, pos, state);
            }
        }
    }

    protected void checkAndDropBlock(World worldIn, BlockPos pos, IBlockState state) {
        if (state.getBlock() == this && !this.canBlockStay(worldIn, pos, state)) {
            boolean flag = state.getValue(PART) == Part.LOWER;
            if (flag) this.dropBlockAsItem(worldIn, pos, state, 0);
            for (int y = 0; y < worldIn.getHeight(pos).getY() - pos.getY(); y++) {
                if (worldIn.getBlockState(pos.up(y)).getBlock() == this) {
                    worldIn.destroyBlock(pos.up(y), false);
                }
            }
        }
    }

    @Override
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
        if ((worldIn.getBlockState(new BlockPos(entityIn).down()).getMaterial() == Material.water || worldIn.getBlockState(new BlockPos(entityIn).down()).getMaterial() == Material.coral) && worldIn.getBlockState(pos.down()).getMaterial() == Material.water && entityIn.getRidingEntity() == null) {
            if (entityIn instanceof EntityLivingBase && !(entityIn instanceof EntityPlayer)) {
                EntityLivingBase living = (EntityLivingBase) entityIn;
                try {
                    ReflectionHelper.findMethod(Entity.class, entityIn, new String[] { "setFlag", "func_70052_a" }, int.class, boolean.class).invoke(living, 7, true);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
            if (entityIn instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) entityIn;
                if (!player.capabilities.isFlying) {
                    try {
                        ReflectionHelper.findMethod(Entity.class, entityIn, new String[] { "setFlag", "func_70052_a" }, int.class, boolean.class).invoke(player, 7, true);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                    entityIn.motionX *= 1.02;
                    entityIn.motionZ *= 1.02;
                }
            }
        }
    }

    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(PART, Part.byMetadata(meta));
    }

    public int getMetaFromState(IBlockState state) {
        return state.getValue(PART).ordinal();
    }

    protected boolean checkCanStay(IBlockState state, IBlockState state2) {
        return (state.getBlock() == this || state.getMaterial() == Material.sand || state.getMaterial() == Material.ground) && state2.getBlock() instanceof BlockTropicalWater;
    }

    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, PART);
    }

    public AxisAlignedBB getSelectedBoundingBox(IBlockState blockState, World worldIn, BlockPos pos) {
        return field_185515_b;
    }

    @Override
    public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player, boolean willHarvest) {
        for (int y = 0; y < height; y++) {
            if (state.getBlock() == this && state.getValue(PART) != Part.LOWER && world.getBlockState(pos.up(y)).getBlock() == this)
                world.setBlockToAir(pos.up(y));
        }
        return world.setBlockToAir(pos);
    }

    public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune) {
        if (state.getBlock() == this && state.getValue(PART) == Part.LOWER) {
            if (!worldIn.isRemote && !worldIn.restoringBlockSnapshots) {
                java.util.List<ItemStack> items = getDrops(worldIn, pos, state, fortune);
                chance = net.minecraftforge.event.ForgeEventFactory.fireBlockHarvesting(items, worldIn, pos, state, fortune, chance, false, harvesters.get());

                for (ItemStack item : items) {
                    if (worldIn.rand.nextFloat() <= chance) {
                        spawnAsEntity(worldIn, pos, item);
                    }
                }
            }
        }
    }


    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB bb, List<AxisAlignedBB> list, Entity mob) {
        addCollisionBoxToList(pos, field_185515_b, list, state.getSelectedBoundingBox(worldIn, pos));
    }

    @SideOnly(Side.CLIENT)
    public Block.EnumOffsetType getOffsetType() {
        return Block.EnumOffsetType.XZ;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void render(IBlockAccess world, BlockPos pos) {
        IBlockState state = world.getBlockState(pos);
        if (state.getValue(PART) == Part.LOWER) {
            GlStateManager.pushMatrix();
            GlStateManager.enableLighting();
            MC.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            double x = (pos.getX() + 0.5) - TileEntityRendererDispatcher.staticPlayerX;
            double y = pos.getY() - TileEntityRendererDispatcher.staticPlayerY;
            double z = (pos.getZ() + 0.5) - TileEntityRendererDispatcher.staticPlayerZ;
            GlStateManager.translate(x, y, z);
            while (state.getBlock() instanceof BlockGrowingSeaweed) {
                int light = MC.theWorld.getCombinedLight(pos, 0);
                OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float) light % 65536, (float) light / 65536.0F);
                RenderHelper.disableStandardItemLighting();
                float sway = (MC.thePlayer.ticksExisted + (pos.hashCode() * 0.2F)) * 0.0125F;
                float swayX = (float) Math.sin(sway) / 8.0F;
                float swayZ = (float) Math.cos(sway) / 8.0F;
                Part part = state.getValue(PART);
                int ordinal = part.ordinal();
                if (sprite[ordinal] == null) {
                    TextureAtlasSprite sprite = MC.getTextureMapBlocks().getTextureExtry(Archipelago.MODID + ":blocks/" + name + "_" + (2 - ordinal));
                    this.sprite[ordinal] = sprite;
                    minU[ordinal] = sprite.getMinU();
                    minV[ordinal] = sprite.getMinV();
                    maxU[ordinal] = sprite.getMaxU();
                    maxV[ordinal] = sprite.getMaxV();
                }
                float minU = this.minU[ordinal];
                float minV = this.minV[ordinal];
                float maxU = this.maxU[ordinal];
                float maxV = this.maxV[ordinal];
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
                pos = pos.up();
                state = world.getBlockState(pos);
                GlStateManager.translate(swayX, 1.0F, swayZ);
            }
            GlStateManager.popMatrix();
        }
    }

    public enum Part implements IStringSerializable {
        UPPER,
        MIDDLE,
        LOWER;
        private static final Part[] METADATA_LOOKUP = new Part[values().length];

        public String toString() {
            return super.toString().toLowerCase();
        }

        static {
            for (Part type : values()) {
                METADATA_LOOKUP[type.ordinal()] = type;
            }
        }

        public static Part byMetadata(int metadata) {
            if (metadata < 0 || metadata >= METADATA_LOOKUP.length) {
                metadata = 0;
            }

            return METADATA_LOOKUP[metadata];
        }

        public String getName() {
            return this.toString().toLowerCase();
        }
    }
}
