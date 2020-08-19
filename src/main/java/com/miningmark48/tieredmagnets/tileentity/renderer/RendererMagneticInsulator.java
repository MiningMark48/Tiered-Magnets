package com.miningmark48.tieredmagnets.tileentity.renderer;

import com.miningmark48.tieredmagnets.tileentity.TileEntityMagneticInsulator;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.lwjgl.opengl.GL11;

public class RendererMagneticInsulator extends TileEntityRenderer<TileEntityMagneticInsulator> {

    public RendererMagneticInsulator(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @SuppressWarnings("Duplicates")
    @Override
    public void render(TileEntityMagneticInsulator te, float v, MatrixStack mStack, IRenderTypeBuffer iRenderTypeBuffer, int i, int i1) {
        Tessellator tess = Tessellator.getInstance();
        BufferBuilder buf = tess.getBuffer();

        int x = te.getPos().getX();
        int y = te.getPos().getY();
        int z = te.getPos().getZ();

        if (te != null && te.getDoPreview()) {

            float red = 0f;
            float green = 0.1f;
            float blue = 1f;
            float alpha = 0.75f;

            GlStateManager.pushMatrix();
            GlStateManager.disableCull();
            GlStateManager.disableLighting();

            GlStateManager.enableAlphaTest();
            GlStateManager.enableBlend();
//            GlStateManager.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);

            GlStateManager.disableTexture();
            GlStateManager.shadeModel(GL11.GL_SMOOTH);

            GlStateManager.alphaFunc(GL11.GL_ALWAYS, 0);
//                    GlStateManager.translate(x + 0.5f, y + 0.5f, z + 0.5f);

            double translateX = x + 0.5D;
            double translateY = y + 0.5D;
            double translateZ = z + 0.5D;

            double rSpeed = 0.25f;
            double rTime = Minecraft.getInstance().world.getGameTime() / (rSpeed * 100D);

            int r = te.getRange();

            GlStateManager.pushMatrix();
            renderLines(buf, r, translateX, translateY, translateZ, red, green, blue, alpha);
            GlStateManager.translated(translateX, translateY, translateZ);
            GlStateManager.rotatef((float) (((rTime * 40D) % 360)), 0, 1, 0);
            GlStateManager.translated(-translateX, -translateY, -translateZ);
            tess.draw();
            GlStateManager.popMatrix();

            GlStateManager.pushMatrix();
            renderLines(buf, r, translateX, translateY, translateZ, red, green, blue, alpha);
            GlStateManager.translated(translateX, translateY, translateZ);
            GlStateManager.rotatef(90f, 1f, 0f, 0f);
            GlStateManager.rotatef((float) (((rTime * 40D) % 360)), 0, 1, 0);
            GlStateManager.translated(-translateX, -translateY, -translateZ);
            tess.draw();
            GlStateManager.popMatrix();

            GlStateManager.pushMatrix();
            renderLines(buf, r, translateX, translateY, translateZ, red, green, blue, alpha);
            GlStateManager.translated(translateX, translateY, translateZ);
            GlStateManager.rotatef(90f, 0f, 0f, 1f);
            GlStateManager.rotatef((float) (((rTime * 40D) % 360)), 0, 1, 0);
            GlStateManager.translated(-translateX, -translateY, -translateZ);
            tess.draw();
            GlStateManager.popMatrix();

            GlStateManager.enableTexture();
            GlStateManager.disableBlend();
            GlStateManager.disableAlphaTest();
            GlStateManager.enableLighting();
            GlStateManager.enableCull();
            GlStateManager.popMatrix();

        }
    }

    public static void renderLines(BufferBuilder buf, int r, double translateX, double translateY, double translateZ, float red, float green, float blue, float alpha) {
        buf.begin(GL11.GL_LINES, DefaultVertexFormats.POSITION_COLOR);
        GL11.glLineWidth(5f);

        float armSize = r * 0.25f;

        //Vert
        buf.pos(translateX, translateY + r + 0.5f, translateZ).color(red, green, blue, alpha).endVertex();
        buf.pos(translateX, translateY - r - 0.5f, translateZ).color(red, green, blue, alpha).endVertex();

        //Cross - Up
        //Line 1
        buf.pos(translateX, translateY + r + 0.5f, translateZ + armSize).color(red, green, blue, alpha).endVertex();
        buf.pos(translateX, translateY + r + 0.5f, translateZ - armSize).color(red, green, blue, alpha).endVertex();
        //Line 2
        buf.pos(translateX + armSize, translateY + r + 0.5f, translateZ).color(red, green, blue, alpha).endVertex();
        buf.pos(translateX - armSize, translateY + r + 0.5f, translateZ).color(red, green, blue, alpha).endVertex();

        //Cross - Down
        //Line 1
        buf.pos(translateX, translateY - r - 0.5f, translateZ + armSize).color(red, green, blue, alpha).endVertex();
        buf.pos(translateX, translateY - r - 0.5f, translateZ - armSize).color(red, green, blue, alpha).endVertex();
        //Line 2
        buf.pos(translateX + armSize, translateY - r - 0.5f, translateZ).color(red, green, blue, alpha).endVertex();
        buf.pos(translateX - armSize, translateY - r - 0.5f, translateZ).color(red, green, blue, alpha).endVertex();
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
