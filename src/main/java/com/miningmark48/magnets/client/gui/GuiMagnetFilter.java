package com.miningmark48.magnets.client.gui;

import com.miningmark48.magnets.container.ContainerMagnetFilter;
import com.miningmark48.magnets.inventory.InventoryMagnetFilter;
import com.miningmark48.magnets.item.base.ItemMagnetBase;
import com.miningmark48.magnets.network.PacketHandler;
import com.miningmark48.magnets.network.packets.PacketChangeRange;
import com.miningmark48.magnets.network.packets.PacketFilterToggle;
import com.miningmark48.magnets.network.packets.PacketToggleMagnet;
import com.miningmark48.magnets.reference.Reference;
import com.miningmark48.mininglib.utility.GuiUtil;
import com.miningmark48.mininglib.utility.KeyChecker;
import com.miningmark48.mininglib.utility.ModLogger;
import com.miningmark48.mininglib.utility.ModTranslate;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.client.config.GuiUtils;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

public class GuiMagnetFilter extends GuiContainer {

    private ResourceLocation texture = new ResourceLocation(Reference.MOD_ID + ":textures/gui/gui_magnet_filter.png");
    private final InventoryMagnetFilter inventory;
    private final ItemStack magnet;
    private int range = 0;

    private GuiButton buttonFilterToggle;
    private boolean buttonModeBlacklist = true;
    private GuiButton buttonRangeDecrease;
    private GuiButton buttonRangeIncrease;

    public GuiMagnetFilter(ContainerMagnetFilter containerItem, ItemStack stack) {
        super(containerItem);
        this.inventory = containerItem.inventory;
        this.magnet = stack;

        this.xSize = 176;
        this.ySize = 166;

        if (magnet.getItem() instanceof ItemMagnetBase) {
            ItemMagnetBase m = (ItemMagnetBase) magnet.getItem();
            range = m.getRange();
        }

    }

    public void onGuiClosed() {
        super.onGuiClosed();
    }

    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String text = ModTranslate.toLocal("gui.magnet_filter.name");
        int x = GuiUtil.getXCenter(text, this.fontRenderer, xSize);
        this.fontRenderer.drawString(text, x, 5, 0x404040);

        if (magnet.getItem() instanceof ItemMagnetBase) {
            ItemMagnetBase m = (ItemMagnetBase) magnet.getItem();
            range = m.getRange();
        }
        this.fontRenderer.drawString(ModTranslate.toLocal("gui.magnet_filter.label.range.name"), 110, 44, 0x404040);
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

        if (!this.magnet.hasTagCompound()) {
            this.magnet.setTagCompound(new NBTTagCompound());
            this.magnet.getTagCompound().setBoolean("filterModeBlacklist", true);
        }
        buttonModeBlacklist = this.magnet.getTagCompound().getBoolean("filterModeBlacklist");

        buttonFilterToggle = new GuiButton(0, getGuiLeft() + 95, getGuiTop() + 20, 60, 20, buttonModeBlacklist ? ModTranslate.toLocal("gui.magnet_filter.button.blacklist") : ModTranslate.toLocal("gui.magnet_filter.button.whitelist"));
        buttonRangeDecrease = new GuiButton(1, getGuiLeft() + 100, getGuiTop() + 55, 15, 20, "<");
        buttonRangeIncrease = new GuiButton(2, getGuiLeft() + 136, getGuiTop() + 55, 15, 20, ">");
        this.buttonList.add(buttonFilterToggle);
        this.buttonList.add(buttonRangeDecrease);
        this.buttonList.add(buttonRangeIncrease);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (button.mousePressed(Minecraft.getMinecraft(), button.x, button.y)) {
            if (button.id == buttonFilterToggle.id) {
                buttonModeBlacklist = !buttonModeBlacklist;
                button.displayString = buttonModeBlacklist ? ModTranslate.toLocal("gui.magnet_filter.button.blacklist") : ModTranslate.toLocal("gui.magnet_filter.button.whitelist");
                PacketHandler.INSTANCE.sendToServer(new PacketFilterToggle());
            }
            if (button.id == buttonRangeDecrease.id) {
                if (KeyChecker.isHoldingShift()) {
                    PacketHandler.INSTANCE.sendToServer(new PacketChangeRange(-5));
                } else {
                    PacketHandler.INSTANCE.sendToServer(new PacketChangeRange(-1));
                }
            }
            if (button.id == buttonRangeIncrease.id) {
                if (KeyChecker.isHoldingShift()) {
                    PacketHandler.INSTANCE.sendToServer(new PacketChangeRange(5));
                } else {
                    PacketHandler.INSTANCE.sendToServer(new PacketChangeRange(1));
                }
            }
        }
    }

    public void renderTooltips(int mouseX, int mouseY) {
        Minecraft mc = Minecraft.getMinecraft();

//        ModLogger.info("x: " + mouseX + ", y: " + mouseY);

        if (this.isMouseOver(mouseX, mouseY, 100, 55, 113, 73) || this.isMouseOver(mouseX, mouseY, 136, 55, 149, 73)) {
            List<String> text = new ArrayList<>();
            text.add(TextFormatting.BOLD + "Adjust Range");
            text.add("None - Inc/Dec by 1");
            text.add("Shift - Inc/Dec by 5");

            GuiUtils.drawHoveringText(text, mouseX - ((this.width - this.xSize) / 2), mouseY - ((this.height - this.ySize) / 2) - 20, mc.displayWidth, mc.displayHeight, -1, mc.fontRenderer);

        }
    }

    private boolean isMouseOver(int mouseX, int mouseY, int minX, int minY, int maxX, int maxY){
        int actualX = mouseX - ((this.width - this.xSize) / 2);
        int actualY = mouseY - ((this.height - this.ySize) / 2);
//        ModLogger.info("x: " + actualX + ", y: " + actualY);
        return (actualX >= minX) && (actualX <= maxX) && (actualY >= minY) && (actualY <= maxY);
    }

}
