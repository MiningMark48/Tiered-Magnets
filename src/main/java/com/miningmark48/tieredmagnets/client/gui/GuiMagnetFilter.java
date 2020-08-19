package com.miningmark48.tieredmagnets.client.gui;

import com.miningmark48.tieredmagnets.container.ContainerMagnetFilter;
import com.miningmark48.tieredmagnets.inventory.InventoryMagnetFilter;
import com.miningmark48.tieredmagnets.item.base.ItemMagnetBase;
import com.miningmark48.tieredmagnets.network.PacketHandler;
import com.miningmark48.tieredmagnets.network.packets.PacketChangeRangeMagnetFilter;
import com.miningmark48.tieredmagnets.network.packets.PacketFilterToggle;
import com.miningmark48.tieredmagnets.reference.NBTKeys;
import com.miningmark48.tieredmagnets.reference.Reference;
import com.miningmark48.tieredmagnets.reference.Translations.Gui;
import com.miningmark48.tieredmagnets.util.KeyChecker;
import com.miningmark48.tieredmagnets.util.ModTranslate;
import com.miningmark48.tieredmagnets.util.UtilGui;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

//import net.minecraftforge.fml.client.config.GuiUtils;

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

    @Override
    public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(stack);
        super.render(stack, mouseX, mouseY, partialTicks);
//        this.renderTooltips(stack, mouseX, mouseY);
    }

    @Override
    public void drawGuiContainerForegroundLayer(MatrixStack stack, int mouseX, int mouseY) {
        String text = ModTranslate.toLocal(Gui.MAGNET_FILTER_NAME.getGui());
        int x = UtilGui.getXCenter(text, this.font, xSize);
        this.font.drawString(stack, text, x, 5, 0x404040);

        if (magnet.getItem() instanceof ItemMagnetBase) {
            ItemMagnetBase m = (ItemMagnetBase) magnet.getItem();
            range = m.getRange(player.getHeldItem(Hand.MAIN_HAND));
        }
        this.font.drawString(stack, ModTranslate.toLocal(Gui.MAGNET_FILTER_RANGE_LABEL.getGui()), 110, 44, 0x404040);
        this.font.drawString(stack, String.valueOf(range), 118, 61, 0x404040);

//        this.renderTooltips(stack, mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack stack, float var1, int var2, int var3) {
        GlStateManager.color4f(1, 1, 1, 1);
        getMinecraft().getTextureManager().bindTexture(texture);
        this.blit(stack, guiLeft, guiTop, 0, 0, xSize, ySize);
    }

    @Override
    public void init() {
        super.init();

        if (!this.magnet.hasTag()) {
            this.magnet.setTag(new CompoundNBT());
            assert this.magnet.getTag() != null;
            this.magnet.getTag().putBoolean(NBTKeys.FILTER_MODE.getKey(), true);
        }
        assert this.magnet.getTag() != null;
        buttonModeBlacklist = this.magnet.getTag().getBoolean(NBTKeys.FILTER_MODE.getKey());

        buttonFilterToggle = addButton(createAndAddButton(95, 20, 60, 20, buttonModeBlacklist ? ModTranslate.toLocal(Gui.MAGNET_FILTER_BUTTON_B.getGui()) : ModTranslate.toLocal(Gui.MAGNET_FILTER_BUTTON_W.getGui()), (button) -> {
            buttonModeBlacklist = !buttonModeBlacklist;
            button.setMessage(new StringTextComponent(buttonModeBlacklist ? ModTranslate.toLocal(Gui.MAGNET_FILTER_BUTTON_B.getGui()) : ModTranslate.toLocal(Gui.MAGNET_FILTER_BUTTON_W.getGui())));
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
    }

    private Button createAndAddButton(int x, int y, int width, int height, String text, Button.IPressable action) {
        return new Button(guiLeft + x, guiTop + y, width, height, new StringTextComponent(text), action);
    }

//    @SuppressWarnings("Duplicates")
//    private void renderTooltips(MatrixStack stack, int mouseX, int mouseY) {
//        Minecraft mc = Minecraft.getInstance();
//        if (this.isMouseOver(mouseX, mouseY, 100, 55, 113, 73) || this.isMouseOver(mouseX, mouseY, 136, 55, 149, 73)) {
//            List<IReorderingProcessor> text = new ArrayList<>();
//            text.add(new TranslationTextComponent(TextFormatting.GOLD + "" + TextFormatting.BOLD + ModTranslate.toLocal(Gui.TOOLTIPS_RANGE_NAME.getGui())));
//            text.add(new TranslationTextComponent(ModTranslate.toLocal(Gui.TOOLTIPS_RANGE_NONE.getGui())));
//            text.add(new TranslationTextComponent(ModTranslate.toLocal(Gui.TOOLTIPS_RANGE_SHIFT.getGui())));
////            GuiUtils.drawHoveringText(text, mouseX - ((this.width - this.xSize) / 2), mouseY - ((this.height - this.ySize) / 2) - 20, mc.mainWindow.getWidth(), mc.mainWindow.getHeight(), -1, mc.fontRenderer);
//            this.renderTooltip(stack, text, mouseX - ((this.width - this.xSize) / 2), mouseY - ((this.height - this.ySize) / 2) - 20);
//        }
//    }

    @SuppressWarnings("Duplicates")
    private boolean isMouseOver(int mouseX, int mouseY, int minX, int minY, int maxX, int maxY){
        int actualX = mouseX - ((this.width - this.xSize) / 2);
        int actualY = mouseY - ((this.height - this.ySize) / 2);
//        ModLogger.info("x: " + actualX + ", y: " + actualY);
        return (actualX >= minX) && (actualX <= maxX) && (actualY >= minY) && (actualY <= maxY);
    }
}
