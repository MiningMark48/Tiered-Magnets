package com.miningmark48.tieredmagnets.client.gui;

import com.miningmark48.tieredmagnets.container.ContainerMagnetFilter;
import com.miningmark48.tieredmagnets.inventory.InventoryMagnetFilter;
import com.miningmark48.tieredmagnets.item.base.ItemMagnetBase;
import com.miningmark48.tieredmagnets.network.PacketHandler;
import com.miningmark48.tieredmagnets.network.packets.PacketChangeRangeMagnetFilter;
import com.miningmark48.tieredmagnets.network.packets.PacketFilterToggle;
import com.miningmark48.tieredmagnets.reference.Reference;
import com.miningmark48.tieredmagnets.util.KeyChecker;
import com.miningmark48.tieredmagnets.util.ModTranslate;
import com.miningmark48.tieredmagnets.util.UtilGui;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.client.config.GuiUtils;

import java.util.ArrayList;
import java.util.List;

public class GuiMagnetFilter extends ContainerScreen<ContainerMagnetFilter> {

    private ResourceLocation texture = new ResourceLocation(Reference.MOD_ID + ":textures/gui/gui_magnet_filter.png");
    private final InventoryMagnetFilter inventory;
    private final ItemStack magnet;
    private final PlayerEntity player;
    private int range = 0;

    private Button buttonFilterToggle, buttonRangeDecrease, buttonRangeIncrease;
    private boolean buttonModeBlacklist = true;

    public GuiMagnetFilter(ContainerMagnetFilter container, PlayerInventory invPlayer, ITextComponent title) {
        this(container, invPlayer, title, invPlayer.getCurrentItem());
    }

    public GuiMagnetFilter(ContainerMagnetFilter containerItem, PlayerInventory invPlayer, ITextComponent title, ItemStack stack) {
        super(containerItem, invPlayer, title);
        this.inventory = containerItem.inventory;
        this.magnet = stack;
        this.player = containerItem.player;

        this.xSize = 176;
        this.ySize = 166;

        if (magnet.getItem() instanceof ItemMagnetBase) {
            ItemMagnetBase m = (ItemMagnetBase) magnet.getItem();
            range = m.getRange(stack);
        }
    }

    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String text = ModTranslate.toLocal("gui.magnet_filter.name");
        int x = UtilGui.getXCenter(text, this.font, xSize);
        this.font.drawString(text, x, 5, 0x404040);

        if (magnet.getItem() instanceof ItemMagnetBase) {
            ItemMagnetBase m = (ItemMagnetBase) magnet.getItem();
            range = m.getRange(player.getHeldItem(Hand.MAIN_HAND));
        }
        this.font.drawString(ModTranslate.toLocal("gui.magnet_filter.label.range.name"), 110, 44, 0x404040);
        this.font.drawString(String.valueOf(range), 118, 61, 0x404040);

        renderTooltips(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
        GlStateManager.color4f(1, 1, 1, 1);
        getMinecraft().getTextureManager().bindTexture(texture);
        blit(guiLeft, guiTop, 0, 0, xSize, ySize);
    }

//    @Override
//    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
//        this.drawDefaultBackground();
//        super.drawScreen(mouseX, mouseY, partialTicks);
//        this.func_191948_b(mouseX, mouseY);
//    }

    @Override
    public void init() {
        super.init();

        if (!this.magnet.hasTag()) {
            this.magnet.setTag(new CompoundNBT());
            assert this.magnet.getTag() != null;
            this.magnet.getTag().putBoolean("filterModeBlacklist", true);
        }
        assert this.magnet.getTag() != null;
        buttonModeBlacklist = this.magnet.getTag().getBoolean("filterModeBlacklist");

        buttonFilterToggle = addButton(createAndAddButton(95, 20, 60, 20, buttonModeBlacklist ? ModTranslate.toLocal("gui.magnet_filter.button.blacklist") : ModTranslate.toLocal("gui.magnet_filter.button.whitelist"), (button) -> {
            buttonModeBlacklist = !buttonModeBlacklist;
            button.setMessage(buttonModeBlacklist ? ModTranslate.toLocal("gui.magnet_filter.button.blacklist") : ModTranslate.toLocal("gui.magnet_filter.button.whitelist"));
            PacketHandler.INSTANCE.sendToServer(new PacketFilterToggle());
        }));

        buttonRangeDecrease = addButton(createAndAddButton(100, 55, 15, 20, "<", (button) -> {
            if (KeyChecker.isHoldingShift()) {
                PacketHandler.INSTANCE.sendToServer(new PacketChangeRangeMagnetFilter(-5));
            } else {
                PacketHandler.INSTANCE.sendToServer(new PacketChangeRangeMagnetFilter(-1));
            }
        }));

        buttonRangeIncrease = addButton(createAndAddButton(136, 55, 15, 20, ">", (button) -> {
            if (KeyChecker.isHoldingShift()) {
                PacketHandler.INSTANCE.sendToServer(new PacketChangeRangeMagnetFilter(5));
            } else {
                PacketHandler.INSTANCE.sendToServer(new PacketChangeRangeMagnetFilter(1));
            }
        }));

//        buttonFilterToggle = new Button( getGuiLeft() + 95, getGuiTop() + 20, 60, 20, buttonModeBlacklist ? ModTranslate.toLocal("gui.magnet_filter.button.blacklist") : ModTranslate.toLocal("gui.magnet_filter.button.whitelist"));
//        buttonRangeDecrease = new Button( getGuiLeft() + 100, getGuiTop() + 55, 15, 20, "<");
//        buttonRangeIncrease = new Button( getGuiLeft() + 136, getGuiTop() + 55, 15, 20, ">");
//        this.buttonList.add(buttonFilterToggle);
//        this.buttonList.add(buttonRangeDecrease);
//        this.buttonList.add(buttonRangeIncrease);
    }

    private Button createAndAddButton(int x, int y, int width, int height, String text, Button.IPressable action) {
        return new Button(guiLeft + x, guiTop + y, width, height, text, action);
    }

    @SuppressWarnings("Duplicates")
    private void renderTooltips(int mouseX, int mouseY) {
        Minecraft mc = Minecraft.getInstance();
        if (this.isMouseOver(mouseX, mouseY, 100, 55, 113, 73) || this.isMouseOver(mouseX, mouseY, 136, 55, 149, 73)) {
            List<String> text = new ArrayList<>();
            text.add(TextFormatting.GOLD + "" + TextFormatting.BOLD + ModTranslate.toLocal("gui.tooltips.adjust_range.name"));
            text.add(ModTranslate.toLocal("gui.tooltips.adjust_range.none"));
            text.add(ModTranslate.toLocal("gui.tooltips.adjust_range.shift"));
            GuiUtils.drawHoveringText(text, mouseX - ((this.width - this.xSize) / 2), mouseY - ((this.height - this.ySize) / 2) - 20, mc.mainWindow.getWidth(), mc.mainWindow.getHeight(), -1, mc.fontRenderer);
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
