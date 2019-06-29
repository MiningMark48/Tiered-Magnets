package com.miningmark48.magnets.client.gui;

import com.miningmark48.mininglib.utility.ModTranslate;
import com.miningmark48.magnets.handler.ConfigurationHandler;
import com.miningmark48.magnets.reference.Reference;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;

import java.util.Arrays;

public class GuiModConfig extends GuiConfig {

    public GuiModConfig(GuiScreen guiScreen){
        super(guiScreen,
                Arrays.asList(new IConfigElement[]{
//                        new ConfigElement(ConfigurationHandler.configuration.getCategory(Configuration.CATEGORY_GENERAL)),
                        new ConfigElement(ConfigurationHandler.configuration.getCategory(ModTranslate.toLocal("config.category.clientSide.title").toLowerCase())),
                        new ConfigElement(ConfigurationHandler.configuration.getCategory(ModTranslate.toLocal("config.category.magnetItemDurability.title").toLowerCase()))
                }),
                Reference.MOD_ID,
                false,
                false,
                GuiConfig.getAbridgedConfigPath(ConfigurationHandler.configuration.toString()));
    }

}
