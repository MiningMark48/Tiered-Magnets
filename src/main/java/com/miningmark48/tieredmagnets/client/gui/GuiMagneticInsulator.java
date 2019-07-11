package com.miningmark48.tieredmagnets.client.gui;

import com.miningmark48.tieredmagnets.container.ContainerMagneticInsulator;
import com.miningmark48.tieredmagnets.network.PacketHandler;
import com.miningmark48.tieredmagnets.network.packets.PacketChangeRangeInsulator;
import com.miningmark48.tieredmagnets.network.packets.PacketTogglePreview;
import com.miningmark48.tieredmagnets.reference.Reference;
import com.miningmark48.tieredmagnets.tileentity.TileEntityMagneticInsulator;
import com.miningmark48.tieredmagnets.util.KeyChecker;
import com.miningmark48.tieredmagnets.util.ModTranslate;
import com.miningmark48.tieredmagnets.util.UtilGui;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.client.config.GuiUtils;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

public class GuiMagneticInsulator extends GuiContainer {

    private ResourceLocation texture = new ResourceLocation(Reference.MOD_ID + ":textures/gui/gui_magnetic_insulator.png");
    private IInventory playerInv;
    private EntityPlayer player;
    private TileEntityMagneticInsulator te;

    private GuiButton buttonRangeDecrease;
    private GuiButton buttonRangeIncrease;
    private GuiButton buttonTogglePreview;

    public GuiMagneticInsulator(IInventory playerInv, TileEntityMagneticInsulator te, EntityPlayer player) {
        super(new ContainerMagneticInsulator(playerInv, te));

        this.xSize = 176;
        this.ySize = 166;

        this.playerInv = playerInv;
        this.te = te;
        this.player = player;

    }

    public void onGuiClosed() {
        super.onGuiClosed();
    }

    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String text = ModTranslate.toLocal("gui.magnetic_insulator.name");
        int x = UtilGui.getXCenter(text, this.fontRenderer, xSize);
        this.fontRenderer.drawString(text, x, 5, 0x404040);

        this.fontRenderer.drawString(ModTranslate.toLocal("gui.magnetic_insulator.label.range.name"), 73, 20, 0x404040);
        this.fontRenderer.drawString(String.valueOf(this.te.getRange()), 82, 36, 0x404040);

        renderTooltips(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
        GL11.glColor4f(1F, 1F, 1F, 1F);
        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.func_191948_b(mouseX, mouseY);
    }

    @Override
    public void initGui() {
        super.initGui();

        buttonRangeDecrease = new GuiButton(0, getGuiLeft() + 63, getGuiTop() + 30, 15, 20, "<");
        buttonRangeIncrease = new GuiButton(1, getGuiLeft() + 99, getGuiTop() + 30, 15, 20, ">");
        buttonTogglePreview = new GuiButton(2, getGuiLeft() + 51, getGuiTop() + 55, 75, 20, this.te.getDoPreview() ? ModTranslate.toLocal("gui.magnetic_insulator.button.hide_preview") : ModTranslate.toLocal("gui.magnetic_insulator.button.show_preview"));
        this.buttonList.add(buttonRangeDecrease);
        this.buttonList.add(buttonRangeIncrease);
        this.buttonList.add(buttonTogglePreview);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (button.mousePressed(Minecraft.getMinecraft(), button.x, button.y)) {
            if (button.id == buttonRangeDecrease.id) {
                if (KeyChecker.isHoldingShift()) {
                    PacketHandler.INSTANCE.sendToServer(new PacketChangeRangeInsulator(this.te.getPos(), -5));
                } else {
                    PacketHandler.INSTANCE.sendToServer(new PacketChangeRangeInsulator(this.te.getPos(), -1));
                }
                this.te.sendUpdates();
            }
            if (button.id == buttonRangeIncrease.id) {
                if (KeyChecker.isHoldingShift()) {
                    PacketHandler.INSTANCE.sendToServer(new PacketChangeRangeInsulator(this.te.getPos(), 5));
                } else {
                    PacketHandler.INSTANCE.sendToServer(new PacketChangeRangeInsulator(this.te.getPos(), 1));
                }
                this.te.sendUpdates();
            }
            if (button.id == buttonTogglePreview.id) {
                buttonTogglePreview.displayString = !this.te.getDoPreview() ? ModTranslate.toLocal("gui.magnetic_insulator.button.hide_preview") : ModTranslate.toLocal("gui.magnetic_insulator.button.show_preview");
                PacketHandler.INSTANCE.sendToServer(new PacketTogglePreview(this.te.getPos()));
                this.te.sendUpdates();
            }
        }
    }

    @SuppressWarnings("Duplicates")
    private void renderTooltips(int mouseX, int mouseY) {
        Minecraft mc = Minecraft.getMinecraft();
        if (this.isMouseOver(mouseX, mouseY, 63, 30, 76, 48) || this.isMouseOver(mouseX, mouseY, 99, 30, 112, 48)) {
            List<String> text = new ArrayList<>();
            text.add(TextFormatting.GOLD + "" + TextFormatting.BOLD + ModTranslate.toLocal("gui.tooltips.adjust_range.name"));
            text.add(ModTranslate.toLocal("gui.tooltips.adjust_range.none"));
            text.add(ModTranslate.toLocal("gui.tooltips.adjust_range.shift"));
            GuiUtils.drawHoveringText(text, mouseX - ((this.width - this.xSize) / 2), mouseY - ((this.height - this.ySize) / 2) + 25, mc.displayWidth, mc.displayHeight, -1, mc.fontRenderer);
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
