package com.miningmark48.tieredmagnets.reference;

public enum NBTKeys {

    ENABLED("enabled"),
    ENERGY("Energy"),
    FILTER_MODE("filterModeBlacklist"),
    ITEM_INV("ItemInventory"),
    NO_MAGNET("noMagnet"),
    PREVIEW("doPreview"),
    RANGE("range"),
    INT_DEMAGNETIZE("PreventRemoteMovement");


    private final String key;

    NBTKeys(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

}
