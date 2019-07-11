package com.miningmark48.tieredmagnets.client.gui;

import com.miningmark48.tieredmagnets.reference.Reference;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.config.GuiConfig;

public class GuiModConfig extends GuiConfig {

    public GuiModConfig(GuiScreen parentScreen) {
        super(parentScreen, Reference.MOD_ID, Reference.MOD_NAME);
    }
}
