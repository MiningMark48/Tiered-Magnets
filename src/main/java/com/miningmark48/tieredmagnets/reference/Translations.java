package com.miningmark48.tieredmagnets.reference;

import com.miningmark48.tieredmagnets.util.UtilLang;

public class Translations {

    public static String getKey(String key, String type, String name, String args) {
        return UtilLang.getLangKey(key, type, name, args);
    }

    public static String getKey(String key, String name, String args) {
        return UtilLang.getLangKey(key, name, args);
    }

    private enum Prefixes {

        CHAT("chat"),
        GUI("gui"),
        TOOLTIP("tooltip");

        private final String name;

        Prefixes(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

    }

    private enum Types {

        ITEM("item"),
        BLOCK("block"),
        MISC("misc");

        private final String name;

        Types(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

    }

    private enum Keys {

        MAGNET_BASE("magnet_base"),
        MAGNET_FILTER("magnet_filter"),
        MAGNETIC_INSULATOR("magnetic_insulator"),
        MAGNETIC_PROJECTOR("magnetic_projector"),
        MAGNETITE("magnetite"),
        SHIFT("shift"),
        TOOLTIP("tooltips");

        private final String key;

        Keys(String key) {
            this.key = key;
        }

        public String getName() {
            return key;
        }

    }

    public enum Tooltips {
        I_MAGNET_INFO(makeTooltip(Types.ITEM, Keys.MAGNET_BASE, "%sline1")),
        I_MAGNET_ENABLED(makeTooltip(Types.ITEM, Keys.MAGNET_BASE, "enabled")),
        I_MAGNET_DISABLED(makeTooltip(Types.ITEM, Keys.MAGNET_BASE, "disabled")),
        I_MAGNET_RANGE(makeTooltip(Types.ITEM, Keys.MAGNET_BASE, "range")),
        I_MAGNET_DURABILITY(makeTooltip(Types.ITEM, Keys.MAGNET_BASE, "durability")),
        I_MAGNET_ENERGY(makeTooltip(Types.ITEM, Keys.MAGNET_BASE, "energy")),
        I_MAGNET_FREE(makeTooltip(Types.ITEM, Keys.MAGNET_BASE, "free")),
        I_MAGNETITE_DROPS(makeTooltip(Types.ITEM, Keys.MAGNETITE, "drops")),
        I_MAGNETITE_REPAIR(makeTooltip(Types.ITEM, Keys.MAGNETITE, "repair")),
        B_MINSULATOR(makeTooltip(Types.BLOCK, Keys.MAGNETIC_INSULATOR, "line1")),
        B_MPROJECTOR(makeTooltip(Types.BLOCK, Keys.MAGNETIC_PROJECTOR, "line1")),
        M_SHIFT(makeTooltip(Types.MISC, Keys.SHIFT, "hold"));

        private final String tooltip;

        Tooltips(String tooltip) {
            this.tooltip = tooltip;
        }

        public String getTooltip() {
            return tooltip;
        }

        private static String makeTooltip(Types type, Keys key, String args) {
            return getKey(Prefixes.TOOLTIP.getName(), type.getName(), key.getName(), args);
        }

    }

    public enum ChatMsgs {

        I_MAGNET_ENABLED(makeMessage(Types.ITEM, Keys.MAGNET_BASE, "enabled")),
        I_MAGNET_DISABLED(makeMessage(Types.ITEM, Keys.MAGNET_BASE, "disabled")),
        I_MAGNET_NO_FILTER(makeMessage(Types.ITEM, Keys.MAGNET_BASE, "filtering.disabled"));

        private final String msg;

        ChatMsgs(String msg) {
            this.msg = msg;
        }

        public String getMessage() {
            return msg;
        }

        private static String makeMessage(Types type, Keys key, String args) {
            return getKey(Prefixes.CHAT.getName(), type.getName(), key.getName(), args);
        }

    }

    public enum Gui {

        TOOLTIPS_RANGE_NAME(make(Keys.TOOLTIP, "adjust_range.name")),
        TOOLTIPS_RANGE_NONE(make(Keys.TOOLTIP, "adjust_range.none")),
        TOOLTIPS_RANGE_SHIFT(make(Keys.TOOLTIP, "adjust_range.shift")),
        MAGNET_FILTER_BUTTON_W(make(Keys.MAGNET_FILTER, "button.whitelist")),
        MAGNET_FILTER_BUTTON_B(make(Keys.MAGNET_FILTER, "button.blacklist")),
        MAGNET_FILTER_NAME(make(Keys.MAGNET_FILTER, "name")),
        MAGNET_FILTER_RANGE_LABEL(make(Keys.MAGNET_FILTER, "label.range.name")),
        MINSULATOR_BUTTON_PREVIEW_H(make(Keys.MAGNETIC_INSULATOR, "button.hide_preview")),
        MINSULATOR_BUTTON_PREVIEW_S(make(Keys.MAGNETIC_INSULATOR, "button.show_preview")),
        MINSULATOR_RANGE_LABEL(make(Keys.MAGNETIC_INSULATOR, "label.range.name")),
        MINSULATOR_NAME(make(Keys.MAGNETIC_INSULATOR, "name")),
        MPROJECTOR_BUTTON_PREVIEW_H(make(Keys.MAGNETIC_PROJECTOR, "button.hide_range")),
        MPROJECTOR_BUTTON_PREVIEW_S(make(Keys.MAGNETIC_PROJECTOR, "button.show_range")),
        MPROJECTOR_NAME(make(Keys.MAGNETIC_PROJECTOR, "name"));

        private final String gui;

        Gui(String gui) {
            this.gui = gui;
        }

        public String getGui() {
            return gui;
        }

        private static String make(Keys key, String args) {
            return getKey(Prefixes.GUI.getName(), key.getName(), args);
        }

    }

}
