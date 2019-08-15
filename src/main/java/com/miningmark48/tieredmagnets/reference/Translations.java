package com.miningmark48.tieredmagnets.reference;

import com.miningmark48.tieredmagnets.util.UtilLang;

public class Translations {

    public static String getKey(String key, String type, String name, String args) {
        return UtilLang.getLangKey(key, type, name, args);
    }

    public static String getKey(String key, String name, String args) {
        return UtilLang.getLangKey(key, name, args);
    }

    public enum Generic {

        TOOLTIP("tooltip");

        private final String name;

        Generic(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

    }

    public enum Tooltips {

        MAGNET_BASE("magnet_base"),
        MAGNETIC_INSULATOR("magnetic_insulator"),
        MAGNETIC_PROJECTOR("magnetic_projector"),
        SHIFT("shift");

        private final String key;

        Tooltips(String key) {
            this.key = key;
        }

        private String getKeyTooltip(String type, String args) {
            return Translations.getKey(Generic.TOOLTIP.getName(), type, key, args);
        }

        public String getKeyNone(String args) {
            return Translations.getKey(Generic.TOOLTIP.getName(), key, args);
        }

        public String getKeyBlock(String args) {
            return getKeyTooltip("block", args);
        }

        public String getKeyItem(String args) {
            return getKeyTooltip("item", args);
        }

    }

}
