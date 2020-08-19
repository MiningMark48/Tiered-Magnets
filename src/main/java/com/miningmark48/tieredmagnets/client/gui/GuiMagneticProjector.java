package com.miningmark48.tieredmagnets.client.gui;

import com.miningmark48.tieredmagnets.container.ContainerMagneticProjector;
import com.miningmark48.tieredmagnets.network.PacketHandler;
import com.miningmark48.tieredmagnets.network.packets.PacketTogglePreview;
import com.miningmark48.tieredmagnets.reference.Reference;
import com.miningmark48.tieredmagnets.reference.Translations.Gui;
import com.miningmark48.tieredmagnets.tileentity.TileEntityMagneticProjector;
import com.miningmark48.tieredmagnets.util.ModTranslate;
import com.miningmark48.tieredmagnets.util.UtilGui;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import org.lwjgl.opengl.GL11;

public class GuiMagneticProjector extends ContainerScreen<ContainerMagneticProjector> {

    private ResourceLocation texture = new ResourceLocation(Reference.MOD_ID + ":textures/gui/gui_magnetic_projector.png");
    private IInventory playerInv;
    private PlayerEntity player;
    private TileEntityMagneticProjector te;

    private Button buttonTogglePreview;

    public GuiMagneticProjector(ContainerMagneticProjector container, PlayerInventory playerInventory, ITextComponent title) {
        this(container.getTe(), container, playerInventory);
    }

    public GuiMagneticProjector(TileEntityMagneticProjector te, ContainerMagneticProjector container, PlayerInventory playerInv) {
        super(container, playerInv, new StringTextComponent("Magnetic Projector"));

        this.xSize = 176;
        this.ySize = 166;

        this.playerInv = playerInv;
        this.te = te;
        this.player = playerInv.player;
    }

    @Override
    public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(stack);
        super.render(stack, mouseX, mouseY, partialTicks);
//        this.renderHoveredToolTip(stack, mouseX, mouseY);
    }

    protected void drawGuiContainerForegroundLayer(MatrixStack stack, int mouseX, int mouseY) {
        String text = ModTranslate.toLocal(Gui.MPROJECTOR_NAME.getGui());
        int x = UtilGui.getXCenter(text, this.font, xSize);
        this.font.drawString(stack, text, x, 5, 0x404040);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack stack, float var1, int var2, int var3) {
        GL11.glColor4f(1F, 1F, 1F, 1F);
        Minecraft.getInstance().getTextureManager().bindTexture(texture);
        this.blit(stack, guiLeft, guiTop, 0, 0, xSize, ySize);
    }

    @SuppressWarnings("Duplicates")
    @Override
    public void init() {
        super.init();

        buttonTogglePreview = addButton(createAndAddButton(51, 55, 75, 20, this.te.getDoPreview() ? ModTranslate.toLocal(Gui.MPROJECTOR_BUTTON_PREVIEW_H.getGui()) : ModTranslate.toLocal(Gui.MPROJECTOR_BUTTON_PREVIEW_S.getGui()), (button) -> {
            buttonTogglePreview.setMessage(new StringTextComponent(!this.te.getDoPreview() ? ModTranslate.toLocal(Gui.MPROJECTOR_BUTTON_PREVIEW_H.getGui()) : ModTranslate.toLocal(Gui.MPROJECTOR_BUTTON_PREVIEW_S.getGui())));
            PacketHandler.INSTANCE.sendToServer(new PacketTogglePreview(this.te.getPos()));
        }));

    }

    private Button createAndAddButton(int x, int y, int width, int height, String text, Button.IPressable action) {
        return new Button(guiLeft + x, guiTop + y, width, height, new StringTextComponent(text), action);
    }

//    @SuppressWarnings("Duplicates")
//    private void renderTooltips(int mouseX, int mouseY) {
//        Minecraft mc = Minecraft.getMinecraft();
//        if (this.isMouseOver(mouseX, mouseY, 63, 30, 76, 48) || this.isMouseOver(mouseX, mouseY, 99, 30, 112, 48)) {
//            List<String> text = new ArrayList<>();
//            text.add(TextFormatting.GOLD + "" + TextFormatting.BOLD + ModTranslate.toLocal("gui.tooltips.adjust_range.name"));
//            text.add(ModTranslate.toLocal("gui.tooltips.adjust_range.none"));
//            text.add(ModTranslate.toLocal("gui.tooltips.adjust_range.shift"));
//            GuiUtils.drawHoveringText(text, mouseX - ((this.width - this.xSize) / 2), mouseY - ((this.height - this.ySize) / 2) + 25, mc.displayWidth, mc.displayHeight, -1, mc.fontRenderer);
//        }
//    }
//
//    @SuppressWarnings("Duplicates")
//    private boolean isMouseOver(int mouseX, int mouseY, int minX, int minY, int maxX, int maxY){
//        int actualX = mouseX - ((this.width - this.xSize) / 2);
//        int actualY = mouseY - ((this.height - this.ySize) / 2);
////        ModLogger.info("x: " + actualX + ", y: " + actualY);
//        return (actualX >= minX) && (actualX <= maxX) && (actualY >= minY) && (actualY <= maxY);
//    }

}
