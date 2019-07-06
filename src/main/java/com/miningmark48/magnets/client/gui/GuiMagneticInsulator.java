package com.miningmark48.magnets.client.gui;

import com.miningmark48.magnets.container.ContainerMagneticInsulator;
import com.miningmark48.magnets.network.PacketHandler;
import com.miningmark48.magnets.network.packets.PacketChangeRangeInsulator;
import com.miningmark48.magnets.reference.Reference;
import com.miningmark48.magnets.tileentity.TileEntityMagneticInsulator;
import com.miningmark48.magnets.util.KeyChecker;
import com.miningmark48.magnets.util.ModTranslate;
import com.miningmark48.magnets.util.UtilGui;
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
    private int range = 0;

    private GuiButton buttonRangeDecrease;
    private GuiButton buttonRangeIncrease;

    public GuiMagneticInsulator(IInventory playerInv, TileEntityMagneticInsulator te, EntityPlayer player) {
        super(new ContainerMagneticInsulator(playerInv, te));

        this.xSize = 176;
        this.ySize = 166;

        this.playerInv = playerInv;
        this.te = te;
        this.player = player;

        range = te.getTileData().getInteger("range");

    }

    public void onGuiClosed() {
        super.onGuiClosed();
    }

    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String text = ModTranslate.toLocal("gui.magnetic_insulator.name");
        int x = UtilGui.getXCenter(text, this.fontRenderer, xSize);
        this.fontRenderer.drawString(text, x, 5, 0x404040);

        range = te.getTileData().getInteger("range");

        this.fontRenderer.drawString(ModTranslate.toLocal("gui.magnetic_insulator.label.range.name"), 110, 44, 0x404040);
        this.fontRenderer.drawString(String.valueOf(range), 118, 61, 0x404040);

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

        buttonRangeDecrease = new GuiButton(0, getGuiLeft() + 100, getGuiTop() + 55, 15, 20, "<");
        buttonRangeIncrease = new GuiButton(1, getGuiLeft() + 136, getGuiTop() + 55, 15, 20, ">");
        this.buttonList.add(buttonRangeDecrease);
        this.buttonList.add(buttonRangeIncrease);
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
            }
            if (button.id == buttonRangeIncrease.id) {
                if (KeyChecker.isHoldingShift()) {
                    PacketHandler.INSTANCE.sendToServer(new PacketChangeRangeInsulator(this.te.getPos(), 5));
                } else {
                    PacketHandler.INSTANCE.sendToServer(new PacketChangeRangeInsulator(this.te.getPos(), 1));
                }
            }
        }
    }

    @SuppressWarnings("Duplicates")
    private void renderTooltips(int mouseX, int mouseY) {
        Minecraft mc = Minecraft.getMinecraft();
        if (this.isMouseOver(mouseX, mouseY, 100, 55, 113, 73) || this.isMouseOver(mouseX, mouseY, 136, 55, 149, 73)) {
            List<String> text = new ArrayList<>();
            text.add(TextFormatting.BOLD + "Adjust Range");
            text.add("None - Inc/Dec by 1");
            text.add("Shift - Inc/Dec by 5");
            GuiUtils.drawHoveringText(text, mouseX - ((this.width - this.xSize) / 2), mouseY - ((this.height - this.ySize) / 2) - 20, mc.displayWidth, mc.displayHeight, -1, mc.fontRenderer);
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
