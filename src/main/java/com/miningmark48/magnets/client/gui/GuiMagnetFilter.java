package com.miningmark48.magnets.client.gui;

import com.miningmark48.magnets.container.ContainerMagnetFilter;
import com.miningmark48.magnets.inventory.InventoryMagnetFilter;
import com.miningmark48.magnets.network.PacketHandler;
import com.miningmark48.magnets.network.packets.PacketFilterToggle;
import com.miningmark48.magnets.network.packets.PacketToggleMagnet;
import com.miningmark48.magnets.reference.Reference;
import com.miningmark48.mininglib.utility.GuiUtil;
import com.miningmark48.mininglib.utility.ModTranslate;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiMagnetFilter extends GuiContainer {

    private ResourceLocation texture = new ResourceLocation(Reference.MOD_ID + ":textures/gui/gui_magnet_filter.png");
    private final InventoryMagnetFilter inventory;
    private final ItemStack magnet;

    private GuiButton buttonFilterToggle;
    private boolean buttonModeBlacklist = true;

    public GuiMagnetFilter(ContainerMagnetFilter containerItem, ItemStack stack) {
        super(containerItem);
        this.inventory = containerItem.inventory;
        this.magnet = stack;

        this.xSize = 176;
        this.ySize = 166;
    }

    public void onGuiClosed() {
        super.onGuiClosed();
    }

    protected void drawGuiContainerForegroundLayer(int i, int j) {
        String text = ModTranslate.toLocal("gui.magnet_filter.name");
        int x = GuiUtil.getXCenter(text, this.fontRenderer, xSize);
        this.fontRenderer.drawString(text, x, 5, 0x404040);
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

        if (this.magnet.hasTagCompound()) {
            buttonModeBlacklist = this.magnet.getTagCompound().getBoolean("filterModeBlacklist");
        }

        buttonFilterToggle = new GuiButton(0, getGuiLeft() + 90, getGuiTop() + 35, 60, 20, buttonModeBlacklist ? ModTranslate.toLocal("gui.magnet_filter.button.blacklist") : ModTranslate.toLocal("gui.magnet_filter.button.whitelist"));
        this.buttonList.add(buttonFilterToggle);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (button.mousePressed(Minecraft.getMinecraft(), button.x, button.y)) {
            buttonModeBlacklist = !buttonModeBlacklist;
            button.displayString = buttonModeBlacklist ? ModTranslate.toLocal("gui.magnet_filter.button.blacklist") : ModTranslate.toLocal("gui.magnet_filter.button.whitelist");

            PacketHandler.INSTANCE.sendToServer(new PacketFilterToggle());
        }
    }

}
