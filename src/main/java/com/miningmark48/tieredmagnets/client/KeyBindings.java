package com.miningmark48.tieredmagnets.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.settings.IKeyConflictContext;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

@SideOnly(Side.CLIENT)
public class KeyBindings {

    private static final KeyConflictContextMagnet CONFLICT_CONTEXT_MAGNET = new KeyConflictContextMagnet();
    public static KeyBinding toggleMagnet;

    public static void init() {
        toggleMagnet = createBinding("toggle_magnet", Keyboard.KEY_M);
    }

    private static KeyBinding createBinding(String name, int key) {
        KeyBinding keyBinding = new KeyBinding("key." + name, CONFLICT_CONTEXT_MAGNET, key, "key.categories.tieredmagnets");
        ClientRegistry.registerKeyBinding(keyBinding);
        return keyBinding;
    }

    public static class KeyConflictContextMagnet implements IKeyConflictContext
    {
        @Override
        public boolean isActive() {
            return !KeyConflictContext.GUI.isActive() && Minecraft.getMinecraft().player != null;
        }

        @Override
        public boolean conflicts(IKeyConflictContext other) {
            return other == this || other == KeyConflictContext.IN_GAME;
        }
    }

}
