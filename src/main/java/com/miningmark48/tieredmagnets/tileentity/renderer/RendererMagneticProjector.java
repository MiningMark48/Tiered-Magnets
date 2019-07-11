package com.miningmark48.tieredmagnets.tileentity.renderer;

import com.miningmark48.tieredmagnets.block.BlockMagneticProjector;
import com.miningmark48.tieredmagnets.init.ModConfig;
import com.miningmark48.tieredmagnets.tileentity.TileEntityMagneticProjector;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import org.lwjgl.opengl.GL11;

public class RendererMagneticProjector extends TileEntitySpecialRenderer<TileEntityMagneticProjector> {

    private EntityItem entityItem = new EntityItem(Minecraft.getMinecraft().world, 0D, 0D, 0D);

    public RendererMagneticProjector() {

    }

    @SuppressWarnings("Duplicates")
    @Override
    public void func_192841_a(TileEntityMagneticProjector te, double x, double y, double z, float partialTicks, int destroyStage, float partial) {
        Tessellator tess = Tessellator.getInstance();
        BufferBuilder buf = tess.getBuffer();

        if (te != null && !te.getWorld().isBlockPowered(te.getPos())) {
            ItemStack stack = te.getStackInSlot(0);
            if (!stack.isEmpty()) {
                renderItem(te, stack, x, y, z);

                if (ModConfig.utilityBlockConfigs.projectorLampRender) {
                    float red = 1f;
                    float green = 1f;
                    float blue = 0f;
                    float alpha = 0.25f;

                    GlStateManager.pushMatrix();
                    GlStateManager.disableCull();
                    GlStateManager.disableLighting();

                    GlStateManager.enableAlpha();
                    GlStateManager.enableBlend();
                    GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);

                    GlStateManager.disableTexture2D();
                    GlStateManager.shadeModel(GL11.GL_SMOOTH);

                    GlStateManager.alphaFunc(GL11.GL_ALWAYS, 0);
//                    GlStateManager.translate(x + 0.5f, y + 0.5f, z + 0.5f);

                    double translateX = x + 0D;
                    double translateY = y + 0.75D;
                    double translateZ = z + 0D;

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

                    tess.draw();


//                    buf.begin(GL11.GL_LINES, DefaultVertexFormats.POSITION_COLOR);
//                    GL11.glLineWidth(5f);
//
//                    buf.pos(translateX + 0f, translateY + 1f, translateZ + 1f).color(1, 1, 1, 1).endVertex();
//                    buf.pos(translateX + 0f, translateY + 1f, translateZ + 0f).color(1, 1, 1, 1).endVertex();
//
//                    tess.draw();

                    GlStateManager.enableTexture2D();
                    GlStateManager.disableBlend();
                    GlStateManager.disableAlpha();
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
        entityItem.hoverStart = 0;
        GlStateManager.pushMatrix();

        GlStateManager.translate(x + 0.5D, y + 0.5D, z + 0.5D);

        //For rotating the item based on the block's direction it's facing
        int facingIndex = getDirection(te.getWorld().getBlockState(te.getPos()).getValue(BlockMagneticProjector.FACING));
        GlStateManager.rotate(90f * facingIndex, 0, 1, 0);

        double xOffset = 0D;
        double yOffset = 0D;
        double zOffset = 0D;

        GlStateManager.translate(xOffset, yOffset, zOffset);

        if (getDoesFloat() || getDoesRotation()) {
            double rTime = Minecraft.getSystemTime() / (getEffectSpeed() * 100D);
            if (getDoesFloat()) GlStateManager.translate(0D, Math.sin(rTime % (2 * Math.PI)) * 0.065, 0D);
            if (getDoesRotation()) GlStateManager.rotate((float) (((rTime * 40D) % 360)), 0, 1, 0);
        }

        Minecraft.getMinecraft().getRenderManager().doRenderEntity(entityItem, 0D, 0D, 0D, 0.0F, 0.0F, false);

        GlStateManager.popMatrix();
    }

    public int getEffectSpeed() {
        return 16;
    }

    public boolean getDoesRotation() {
        return true;
    }

    public boolean getDoesFloat() {
        return false;
    }

    private static int getDirection(EnumFacing facing) {

        if (facing.equals(EnumFacing.NORTH)) {
            return 0;
        } else if (facing.equals(EnumFacing.WEST)) {
            return 1;
        } else if (facing.equals(EnumFacing.SOUTH)) {
            return 2;
        } else if (facing.equals(EnumFacing.EAST)) {
            return 3;
        }

        return 0;
    }
}
