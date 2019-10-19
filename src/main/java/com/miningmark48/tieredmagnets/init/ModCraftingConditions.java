package com.miningmark48.tieredmagnets.init;

import com.miningmark48.tieredmagnets.init.config.crafting.ConditionModuleEnabled;
import net.minecraftforge.common.crafting.CraftingHelper;

public class ModCraftingConditions {

    public static void init() {
        CraftingHelper.register(ConditionModuleEnabled.Serializer.INSTANCE);
    }

}
