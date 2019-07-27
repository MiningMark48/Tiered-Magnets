package com.miningmark48.tieredmagnets.client;

import com.miningmark48.tieredmagnets.reference.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraftforge.client.settings.IKeyConflictContext;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.glfw.GLFW;

public class KeyBindings {

    private static final KeyConflictContextMagnet CONFLICT_CONTEXT_MAGNET = new KeyConflictContextMagnet();
    public static KeyBinding toggleMagnet;

    public static void init() {
        toggleMagnet = createBinding("toggle_magnet", GLFW.GLFW_KEY_M);
    }

    private static KeyBinding createBinding(String name, int key) {
        KeyBinding keyBinding = new KeyBinding(getKey(name), CONFLICT_CONTEXT_MAGNET, InputMappings.Type.KEYSYM.getOrMakeInput(key), getKey("category"));
        ClientRegistry.registerKeyBinding(keyBinding);
        return keyBinding;
    }

    private static String getKey(String name) {
        return String.join(".", "key", Reference.MOD_ID, name);
    }

    public static class KeyConflictContextMagnet implements IKeyConflictContext {
        @Override
        public boolean isActive() {
            return !KeyConflictContext.GUI.isActive() && Minecraft.getInstance().player != null;
        }

        @Override
        public boolean conflicts(IKeyConflictContext other) {
            return other == this || other == KeyConflictContext.IN_GAME;
        }
    }

}
