package com.miningmark48.tieredmagnets.data;

import com.miningmark48.tieredmagnets.init.ModBlocks;
import com.miningmark48.tieredmagnets.init.ModItems;
import com.miningmark48.tieredmagnets.reference.Reference;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

@SuppressWarnings("ConstantConditions")
public class GeneratorLanguage extends LanguageProvider {

    public GeneratorLanguage(DataGenerator gen) {
        super(gen, Reference.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        //Creative Tabs
        add("itemGroup.tieredmagnets", "Tiered Magnets");

        //Items
        add(ModItems.MAGNET_DURABILITY_STONE.get(), "Stone Magnet");
        add(ModItems.MAGNET_DURABILITY_IRON.get(), "Iron Magnet");
        add(ModItems.MAGNET_DURABILITY_REDSTONE.get(), "Redstone Magnet");
        add(ModItems.MAGNET_DURABILITY_GOLD.get(), "Gold Magnet");
        add(ModItems.MAGNET_DURABILITY_OBSIDIAN.get(), "Obsidian Magnet");
        add(ModItems.MAGNET_DURABILITY_LAPIS.get(), "Lapis Magnet");
        add(ModItems.MAGNET_DURABILITY_DIAMOND.get(), "Diamond Magnet");
        add(ModItems.MAGNET_DURABILITY_EMERALD.get(), "Emerald Magnet");
        add(ModItems.MAGNET_MAGIC_DURABILITY_STONE.get(), "Magic Stone Magnet");
        add(ModItems.MAGNET_MAGIC_DURABILITY_IRON.get(), "Magic Iron Magnet");
        add(ModItems.MAGNET_MAGIC_DURABILITY_REDSTONE.get(), "Magic Redstone Magnet");
        add(ModItems.MAGNET_MAGIC_DURABILITY_GOLD.get(), "Magic Gold Magnet");
        add(ModItems.MAGNET_MAGIC_DURABILITY_OBSIDIAN.get(), "Magic Obsidian Magnet");
        add(ModItems.MAGNET_MAGIC_DURABILITY_LAPIS.get(), "Magic Lapis Magnet");
        add(ModItems.MAGNET_MAGIC_DURABILITY_DIAMOND.get(), "Magic Diamond Magnet");
        add(ModItems.MAGNET_MAGIC_DURABILITY_EMERALD.get(), "Magic Emerald Magnet");
        add(ModItems.MAGNET_ENERGY_LEADSTONE.get(), "Leadstone Electromagnet");
        add(ModItems.MAGNET_ENERGY_HARDENED.get(), "Hardened Electromagnet");
        add(ModItems.MAGNET_ENERGY_REINFORCED.get(), "Reinforced Electromagnet");
        add(ModItems.MAGNET_ENERGY_SIGNALUM.get(), "Signalum Electromagnet");
        add(ModItems.MAGNET_ENERGY_RESONANT.get(), "Resonant Electromagnet");
        add(ModItems.MAGNET_MAGIC_ENERGY_LEADSTONE.get(), "Magic Leadstone Electromagnet");
        add(ModItems.MAGNET_MAGIC_ENERGY_HARDENED.get(), "Magic Hardened Electromagnet");
        add(ModItems.MAGNET_MAGIC_ENERGY_REINFORCED.get(), "Magic Reinforced Electromagnet");
        add(ModItems.MAGNET_MAGIC_ENERGY_SIGNALUM.get(), "Magic Signalum Electromagnet");
        add(ModItems.MAGNET_MAGIC_ENERGY_RESONANT.get(), "Magic Resonant Electromagnet");
        add(ModItems.MAGNET_FREE.get(), "Cursed Magnet");
        add(ModItems.MAGNET_MAGIC_FREE.get(), "Magic Cursed Magnet");
        add(ModItems.MAGNETITE.get(), "Magnetite");

        //Blocks
        add(ModBlocks.MAGNETIC_INSULATOR.get(), "Magnetic Insulator");
        add(ModBlocks.MAGNETIC_PROJECTOR.get(), "Magnetic Insulator");

        //Tooltips
        add(getTooltip("item.magnet_base.enabled"), "Enabled");
        add(getTooltip("item.magnet_base.disabled"), "Disabled");
        add(getTooltip("item.magnet_base.range.1"), "Range:");
        add(getTooltip("item.magnet_base.range.2"), "Blocks");
        add(getTooltip("item.magnet_base.free"), "Free to use!");
        add(getTooltip("item.magnet_base.durability"), "Durability:");
        add(getTooltip("item.magnet_base.energy.1"), "Energy:");
        add(getTooltip("item.magnet_base.energy.2"), "RF");
        add(getTooltip("item.magnet_base.line1"), "Pulls items towards the player.");
        add(getTooltip("item.magnet_base.magic.line1"), "Teleports items to the player.");
        add(getTooltip("item.magnetite.drops"), "Drops from");
        add(getTooltip("item.magnetite.repair"), "Can be used to repair magnets in an anvil!");
        add(getTooltip("block.magnetic_insulator.line1"), "Prevents magnets from pulling items in a defined range.");
        add(getTooltip("block.magnetic_projector.line1"), "Projects the effects of a magnet into the world as a block.");
        add(getTooltip("misc.shift.hold.1"), "Hold");
        add(getTooltip("misc.shift.hold.2"), "Shift");

        //Chat
        add(getChat("item.magnet_base.enabled"), "Magnet enabled");
        add(getChat("item.magnet_base.disabled"), "Magnet disabled");
        add(getChat("item.magnet_base.filtering.disabled"), "Filtering has been disabled!");

        //GUIs
        add(getGUI("tooltips.adjust_range.name"), "Adjust Range");
        add(getGUI("tooltips.adjust_range.none"), "None - Inc/Dec by 1");
        add(getGUI("tooltips.adjust_range.shift"), "None - Inc/Dec by 5");
        add(getGUI("magnet_filter.name"), "Magnet Control");
        add(getGUI("magnet_filter.label.range.name"), "Range");
        add(getGUI("magnet_filter.button.blacklist"), "Blacklist");
        add(getGUI("magnet_filter.button.whitelist"), "Whitelist");
        add(getGUI("magnetic_insulator.name"), "Magnetic Insulator");
        add(getGUI("magnetic_insulator.button.show_preview"), "Show Preview");
        add(getGUI("magnetic_insulator.button.hide_preview"), "Hide Preview");
        add(getGUI("magnetic_insulator.label.range.name"), "Range");
        add(getGUI("magnetic_projector.name"), "Magnetic Projector");
        add(getGUI("magnetic_projector.button.show_range"), "Show Range");
        add(getGUI("magnetic_projector.button.hide_range"), "Hide Range");

        //Keys
        add(getKeys("category"), "Magnets");
        add(getKeys("toggle_magnet"), "Toggle Magnet");

    }

    private String getTooltip(String key) {
        return String.format("tooltip.%s.%s", Reference.MOD_ID, key);
    }

    private String getChat(String key) {
        return String.format("chat.%s.%s", Reference.MOD_ID, key);
    }

    private String getGUI(String key) {
        return String.format("gui.%s.%s", Reference.MOD_ID, key);
    }

    private String getKeys(String key) {
        return String.format("key.%s.%s", Reference.MOD_ID, key);
    }

}
