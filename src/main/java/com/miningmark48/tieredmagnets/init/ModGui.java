package com.miningmark48.tieredmagnets.init;

import com.miningmark48.tieredmagnets.client.gui.GuiMagneticInsulator;
import com.miningmark48.tieredmagnets.client.gui.GuiMagneticProjector;
import com.miningmark48.tieredmagnets.container.ContainerMagneticInsulator;
import com.miningmark48.tieredmagnets.container.ContainerMagneticProjector;
import com.miningmark48.tieredmagnets.tileentity.*;
import com.miningmark48.tieredmagnets.util.UtilLang;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.function.Consumer;
import java.util.function.Function;

public enum ModGui {

    MAGNETIC_INSULATOR(ModContainers.ContainerReference.MAGNETIC_INSULATOR, message -> {
        TileEntity te = Minecraft.getInstance().world.getTileEntity(message.getAdditionalData().readBlockPos());
        ClientPlayerEntity player = Minecraft.getInstance().player;
        return te instanceof TileEntityMagneticInsulator
                ? new GuiMagneticInsulator((TileEntityMagneticInsulator) te, getMInsulatorContainer(player, te), player.inventory)
                : null;
    }, (id, player, world, pos) -> {
        TileEntity te = world.getTileEntity(pos);
        if (te instanceof TileEntityMagneticInsulator) {
            openGuiContainer(id, player, getMInsulatorContainer(player, te), buffer -> buffer.writeBlockPos(pos));
            return true;
        }
        return false;
    }),
    MAGNETIC_PROJECTOR(ModContainers.ContainerReference.MAGNETIC_PROJECTOR, message -> {
        TileEntity te = Minecraft.getInstance().world.getTileEntity(message.getAdditionalData().readBlockPos());
        ClientPlayerEntity player = Minecraft.getInstance().player;
        return te instanceof TileEntityMagneticProjector
                ? new GuiMagneticProjector((TileEntityMagneticProjector) te, getMProjectorContainer(player, te), player.inventory)
                : null;
    }, (id, player, world, pos) -> {
        TileEntity te = world.getTileEntity(pos);
        if (te instanceof TileEntityMagneticProjector) {
            openGuiContainer(id, player, getMProjectorContainer(player, te), buffer -> buffer.writeBlockPos(pos));
            return true;
        }
        return false;
    });

//    MATERIAL_LIST(ITemplate::getTemplate, MaterialListGUI::new);

    private static interface IContainerOpener {
        boolean open(String id, ServerPlayerEntity player, World world, BlockPos pos);
    }

    private Function<PlayerEntity, ItemStack> stackReader;
    private Function<ItemStack, ? extends Screen> clientScreenProvider;
    private Function<FMLPlayMessages.OpenContainer, ? extends Screen> commonScreenProvider;
    private IContainerOpener containerOpener;
    private String id;

    private ModGui(Function<PlayerEntity, ItemStack> stackReader, Function<ItemStack, ? extends Screen> clientScreenProvider) {
        this.stackReader = stackReader;
        this.clientScreenProvider = clientScreenProvider;
    }

    private ModGui(String id, Function<FMLPlayMessages.OpenContainer, ? extends Screen> commonScreenProvider, IContainerOpener containerOpener) {
        this.id = id;
        this.commonScreenProvider = commonScreenProvider;
        this.containerOpener = containerOpener;
    }

    public static Screen openScreen(Minecraft minecraft, Screen screen) {
        return null;
    }

    public boolean openScreen(PlayerEntity player) {
        if (clientScreenProvider == null)
            return false;

        ItemStack stack = stackReader.apply(player);
        if (stack == null || stack.isEmpty())
            return false;

        Screen screen = clientScreenProvider.apply(stack);
        Minecraft.getInstance().displayGuiScreen(screen);
        return screen == null;
    }

    public boolean openContainer(PlayerEntity player, World world, BlockPos pos) {
        return containerOpener != null && player instanceof ServerPlayerEntity && containerOpener.open(id, (ServerPlayerEntity) player, world, pos);
    }

    public static Screen openScreen(FMLPlayMessages.OpenContainer message) {
        return null;
    }

    private static ContainerMagneticInsulator getMInsulatorContainer(PlayerEntity player, TileEntity te) {
        return new ContainerMagneticInsulator(0, player.inventory, (TileEntityMagneticInsulator) te);
    }

    private static ContainerMagneticProjector getMProjectorContainer(PlayerEntity player, TileEntity te) {
        return new ContainerMagneticProjector(0, player.inventory, (TileEntityMagneticProjector) te);
    }

    private static void openGuiContainer(String id, ServerPlayerEntity player, Container container, Consumer<PacketBuffer> extraDataWriter) {
        NetworkHooks.openGui(player, new INamedContainerProvider() {
            @Override
            public ITextComponent getDisplayName() {
                return null;
            }

            @Nullable
            @Override
            public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_) {
                return container;
            }
        }, extraDataWriter);
    }

    public static String getLangKeyField(String prefix, String name) {
        return getLangKey(prefix, "field", name);
    }

    public static String getLangKeyButton(String prefix, String name) {
        return getLangKey(prefix, "button", name);
    }

    public static String getLangKeyArea(String prefix, String name) {
        return getLangKey(prefix, "area", name);
    }

    public static String getLangKey(String prefix, String type, String name) {
        return UtilLang.getLangKey("gui", prefix, type, name);
    }

    public static String getLangKeySingle(String name) {
        return UtilLang.getLangKey("gui", "single", name);
    }

//    public static void setEmptyField(GuiTextFieldBase field, Supplier<Integer> value) {
//        if (field.getText().isEmpty())
//            field.setText(String.valueOf(value.get()));
//    }
//
//    public static boolean sizeCheckBoxes(Iterator<GuiTextFieldBase> fields, int min, int max) {
//        while (fields.hasNext()) {
//            int n = fields.next().getInt();
//            if (n < min || n > max)
//                return false;
//        }
//        return true;
//    }

    public static Color getColor(Color color, int alpha) {
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
    }

}
