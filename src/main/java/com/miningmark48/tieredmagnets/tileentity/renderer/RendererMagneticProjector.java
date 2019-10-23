package com.miningmark48.tieredmagnets.tileentity.renderer;

import com.miningmark48.tieredmagnets.init.config.ModConfig;
import com.miningmark48.tieredmagnets.tileentity.TileEntityMagneticProjector;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

import java.util.Objects;

public class RendererMagneticProjector extends TileEntityRenderer<TileEntityMagneticProjector> {

    private ItemEntity entityItem = new ItemEntity(Minecraft.getInstance().world, 0D, 0D, 0D);

    public RendererMagneticProjector() {

    }

    @SuppressWarnings("Duplicates")
    @Override
    public void render(TileEntityMagneticProjector te, double x, double y, double z, float p_199341_8_, int p_199341_9_) {

        Tessellator tess = Tessellator.getInstance();
        BufferBuilder buf = tess.getBuffer();

        if (te != null && !Objects.requireNonNull(te.getWorld()).isBlockPowered(te.getPos())) {
            ItemStack stack = te.getStackInSlot(0);
            if (!stack.isEmpty()) {
                renderItem(te, stack, x, y, z);

                if (ModConfig.CLIENT.ub_enableLampRender.get()) {
                    float red = 1f;
                    float green = 1f;
                    float blue = 0f;
                    float alpha = 0.25f;

                    GlStateManager.pushMatrix();
                    GlStateManager.disableCull();
                    GlStateManager.disableLighting();

                    GlStateManager.enableAlphaTest();
                    GlStateManager.enableBlend();
                    GlStateManager.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);

                    GlStateManager.disableTexture();
                    GlStateManager.shadeModel(GL11.GL_SMOOTH);

                    GlStateManager.alphaFunc(GL11.GL_ALWAYS, 0);
//                    GlStateManager.translate(x + 0.5f, y + 0.5f, z + 0.5f);

                    double translateX = x + 0D;
                    double translateY = y + 0.75D;
                    double translateZ = z + 0D;

                    double rSpeed = 0.4f;
                    double rTime = Minecraft.getInstance().world.getGameTime() / (rSpeed * 100D);

                    buf.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_COLOR);

                    //north side
                    buf.pos(translateX + 1f, translateY + 0.25f, translateZ + 0f).color(red, green, blue, alpha).endVertex();
                    buf.pos(translateX + 0f, translateY + 0.25f, translateZ + 0f).color(red, green, blue, alpha).endVertex();
                    buf.pos(translateX + 0.38f, translateY - 0.19f, translateZ + 0.38f).color(red, green, blue, alpha).endVertex();
                    buf.pos(translateX + 0.62f, translateY - 0.19f, translateZ + 0.38f).color(red, green, blue, alpha).endVertex();

                    //east side
                    buf.pos(translateX + 1f, translateY + 0.25f, translateZ + 1f).color(red, green, blue, alpha).endVertex();
                    buf.pos(translateX + 1f, translateY + 0.25f, translateZ + 0f).color(red, green, blue, alpha).endVertex();
                    buf.pos(translateX + 0.62f, translateY - 0.19f, translateZ + 0.38f).color(red, green, blue, alpha).endVertex();
                    buf.pos(translateX + 0.62f, translateY - 0.19f, translateZ + 0.62f).color(red, green, blue, alpha).endVertex();

                    //south side
                    buf.pos(translateX + 1f, translateY + 0.25f, translateZ + 1f).color(red, green, blue, alpha).endVertex();
                    buf.pos(translateX + 0f, translateY + 0.25f, translateZ + 1f).color(red, green, blue, alpha).endVertex();
                    buf.pos(translateX + 0.38f, translateY - 0.19f, translateZ + 0.62f).color(red, green, blue, alpha).endVertex();
                    buf.pos(translateX + 0.62f, translateY - 0.19f, translateZ + 0.62f).color(red, green, blue, alpha).endVertex();

                    //west side
                    buf.pos(translateX + 0f, translateY + 0.25f, translateZ + 1f).color(red, green, blue, alpha).endVertex();
                    buf.pos(translateX + 0f, translateY + 0.25f, translateZ + 0f).color(red, green, blue, alpha).endVertex();
                    buf.pos(translateX + 0.38f, translateY - 0.19f, translateZ + 0.38f).color(red, green, blue, alpha).endVertex();
                    buf.pos(translateX + 0.38f, translateY - 0.19f, translateZ + 0.62f).color(red, green, blue, alpha).endVertex();

                    GlStateManager.translated(translateX + 0.5f, translateY, translateZ + 0.5f);
                    GlStateManager.rotatef(-(float) (((rTime * 40D) % 360)), 0, 1, 0);
                    GlStateManager.translated(-translateX - 0.5f, -translateY, -translateZ - 0.5f);

                    tess.draw();


//                    buf.begin(GL11.GL_LINES, DefaultVertexFormats.POSITION_COLOR);
//                    GL11.glLineWidth(5f);
//
//                    buf.pos(translateX + 0f, translateY + 1f, translateZ + 1f).color(1, 1, 1, 1).endVertex();
//                    buf.pos(translateX + 0f, translateY + 1f, translateZ + 0f).color(1, 1, 1, 1).endVertex();
//
//                    tess.draw();

                    GlStateManager.enableTexture();
                    GlStateManager.disableBlend();
                    GlStateManager.disableAlphaTest();
                    GlStateManager.enableLighting();
                    GlStateManager.enableCull();
                    GlStateManager.popMatrix();
                }

            }
        }
    }

    private void renderItem(TileEntityMagneticProjector te, ItemStack stack, double x, double y, double z) {
        ItemStack magnet = new ItemStack(stack.getItem());
        magnet.setCount(1);
        entityItem.setItem(magnet);
//        entityItem.hoverStart = 0;
        GlStateManager.pushMatrix();

        GlStateManager.translated(x + 0.5D, y + 0.5D, z + 0.5D);

        //For rotating the item based on the block's direction it's facing
        int facingIndex = 0; //getDirection(Objects.requireNonNull(te.getWorld()).getBlockState(te.getPos()).getValue(BlockMagneticProjector.FACING));
        GlStateManager.rotatef(90f * facingIndex, 0, 1, 0);

        double xOffset = 0D;
        double yOffset = 0D;
        double zOffset = 0D;

        GlStateManager.translated(xOffset, yOffset, zOffset);

        if (getDoesFloat() || getDoesRotation()) {
            double rTime = Minecraft.getInstance().world.getGameTime() / (getEffectSpeed() * 100D);
            if (getDoesFloat()) GlStateManager.translated(0D, Math.sin(rTime % (2 * Math.PI)) * 0.065, 0D);
            if (getDoesRotation()) GlStateManager.rotated((float) (((rTime * 40D) % 360)), 0, 1, 0);
        }

        Minecraft.getInstance().getRenderManager().renderEntity(entityItem, 0D, 0D, 0D, 0.0F, 0.0F, false);

        GlStateManager.popMatrix();
    }

    private double getEffectSpeed() {
        return 0.25;
    }

    private boolean getDoesRotation() {
        return true;
    }

    private boolean getDoesFloat() {
        return false;
    }

    private static int getDirection(/*EnumFacing facing*/) {

//        if (facing.equals(EnumFacing.NORTH)) {
//            return 0;
//        } else if (facing.equals(EnumFacing.WEST)) {
//            return 1;
//        } else if (facing.equals(EnumFacing.SOUTH)) {
//            return 2;
//        } else if (facing.equals(EnumFacing.EAST)) {
//            return 3;
//        }

        return 0;
    }
}
