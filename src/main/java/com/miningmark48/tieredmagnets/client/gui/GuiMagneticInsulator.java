package com.miningmark48.tieredmagnets.client.gui;

import com.miningmark48.tieredmagnets.container.ContainerMagneticInsulator;
import com.miningmark48.tieredmagnets.network.PacketHandler;
import com.miningmark48.tieredmagnets.network.packets.PacketChangeRangeInsulator;
import com.miningmark48.tieredmagnets.network.packets.PacketTogglePreview;
import com.miningmark48.tieredmagnets.reference.Reference;
import com.miningmark48.tieredmagnets.reference.Translations.Gui;
import com.miningmark48.tieredmagnets.tileentity.TileEntityMagneticInsulator;
import com.miningmark48.tieredmagnets.util.KeyChecker;
import com.miningmark48.tieredmagnets.util.ModTranslate;
import com.miningmark48.tieredmagnets.util.UtilGui;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.client.config.GuiUtils;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

public class GuiMagneticInsulator extends ContainerScreen<ContainerMagneticInsulator> {

    private ResourceLocation texture = new ResourceLocation(Reference.MOD_ID + ":textures/gui/gui_magnetic_insulator.png");
    private IInventory playerInv;
    private PlayerEntity player;
    private TileEntityMagneticInsulator te;

    private Button buttonRangeDecrease, buttonRangeIncrease, buttonTogglePreview;

    public GuiMagneticInsulator(ContainerMagneticInsulator container, PlayerInventory playerInventory, ITextComponent title) {
        this(container.getTe(), container, playerInventory);
    }

    public GuiMagneticInsulator(TileEntityMagneticInsulator te, ContainerMagneticInsulator container, PlayerInventory playerInv) {
        super(container, playerInv, new StringTextComponent("Magnetic Insulator"));

        this.xSize = 176;
        this.ySize = 166;

        this.playerInv = playerInv;
        this.te = te;
        this.player = playerInv.player;
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        this.renderBackground();
        super.render(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String text = ModTranslate.toLocal(Gui.MINSULATOR_NAME.getGui());
        int x = UtilGui.getXCenter(text, this.font, xSize);
        this.font.drawString(text, x, 5, 0x404040);

        this.font.drawString(ModTranslate.toLocal(Gui.MINSULATOR_RANGE_LABEL.getGui()), 73, 20, 0x404040);
        this.font.drawString(String.valueOf(this.te.getRange()), 82, 36, 0x404040);

        renderTooltips(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
        GL11.glColor4f(1F, 1F, 1F, 1F);
        Minecraft.getInstance().getTextureManager().bindTexture(texture);
        blit(guiLeft, guiTop, 0, 0, xSize, ySize);
    }

    @SuppressWarnings("Duplicates")
    @Override
    public void init() {
        super.init();

        buttonRangeDecrease = addButton(createAndAddButton(63, 30, 15, 20, "<", (button) -> {
            if (KeyChecker.isHoldingShift()) {
                PacketHandler.INSTANCE.sendToServer(new PacketChangeRangeInsulator(this.te.getPos(), -5));
            } else {
                PacketHandler.INSTANCE.sendToServer(new PacketChangeRangeInsulator(this.te.getPos(), -1));
            }
        }));
        buttonRangeIncrease = addButton(createAndAddButton(99, 30, 15, 20, ">", (button) -> {
            if (KeyChecker.isHoldingShift()) {
                PacketHandler.INSTANCE.sendToServer(new PacketChangeRangeInsulator(this.te.getPos(), 5));
            } else {
                PacketHandler.INSTANCE.sendToServer(new PacketChangeRangeInsulator(this.te.getPos(), 1));
            }
        }));
        buttonTogglePreview = addButton(createAndAddButton(51, 55, 75, 20, this.te.getDoPreview() ? ModTranslate.toLocal(Gui.MINSULATOR_BUTTON_PREVIEW_H.getGui()) : ModTranslate.toLocal(Gui.MINSULATOR_BUTTON_PREVIEW_S.getGui()), (button) -> {
            buttonTogglePreview.setMessage(!this.te.getDoPreview() ? ModTranslate.toLocal(Gui.MINSULATOR_BUTTON_PREVIEW_H.getGui()) : ModTranslate.toLocal(Gui.MINSULATOR_BUTTON_PREVIEW_S.getGui()));
            PacketHandler.INSTANCE.sendToServer(new PacketTogglePreview(this.te.getPos()));
        }));

//        buttonRangeDecrease = new GuiButton(0, getGuiLeft() + 63, getGuiTop() + 30, 15, 20, "<");
//        buttonRangeIncrease = new GuiButton(1, getGuiLeft() + 99, getGuiTop() + 30, 15, 20, ">");
//        buttonTogglePreview = new GuiButton(2, getGuiLeft() + 51, getGuiTop() + 55, 75, 20, this.te.getDoPreview() ? ModTranslate.toLocal("gui.magnetic_insulator.button.hide_preview") : ModTranslate.toLocal("gui.magnetic_insulator.button.show_preview"));
//        this.buttonList.add(buttonRangeDecrease);
//        this.buttonList.add(buttonRangeIncrease);
//        this.buttonList.add(buttonTogglePreview);
    }

    private Button createAndAddButton(int x, int y, int width, int height, String text, Button.IPressable action) {
        return new Button(guiLeft + x, guiTop + y, width, height, text, action);
    }

    @SuppressWarnings("Duplicates")
    private void renderTooltips(int mouseX, int mouseY) {
        Minecraft mc = Minecraft.getInstance();
        if (this.isMouseOver(mouseX, mouseY, 63, 30, 76, 48) || this.isMouseOver(mouseX, mouseY, 99, 30, 112, 48)) {
            List<String> text = new ArrayList<>();
            text.add(TextFormatting.GOLD + "" + TextFormatting.BOLD + ModTranslate.toLocal(Gui.TOOLTIPS_RANGE_NAME.getGui()));
            text.add(ModTranslate.toLocal(Gui.TOOLTIPS_RANGE_NONE.getGui()));
            text.add(ModTranslate.toLocal(Gui.TOOLTIPS_RANGE_SHIFT.getGui()));
            GuiUtils.drawHoveringText(text, mouseX - ((this.width - this.xSize) / 2), mouseY - ((this.height - this.ySize) / 2) + 25, mc.mainWindow.getWidth(), mc.mainWindow.getHeight(), -1, mc.fontRenderer);
        }
    }

    @SuppressWarnings("Duplicates")
    private boolean isMouseOver(int mouseX, int mouseY, int minX, int minY, int maxX, int maxY){
        int actualX = mouseX - ((this.width - this.xSize) / 2);
        int actualY = mouseY - ((this.height - this.ySize) / 2);
//        ModLogger.info("x: " + actualX + ", y: " + actualY);
        return (actualX >= minX) && (actualX <= maxX) && (actualY >= minY) && (actualY <= maxY);
    }

}
