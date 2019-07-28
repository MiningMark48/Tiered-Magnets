package com.miningmark48.tieredmagnets.init;

import com.miningmark48.tieredmagnets.init.config.crafting.ConditionModuleVanilla;
import com.miningmark48.tieredmagnets.init.config.crafting.ConditionModuleVanillaMagic;
import com.miningmark48.tieredmagnets.reference.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;

public class ModCraftingConditions {

    public static void init() {
        CraftingHelper.register(ConditionsReference.CONDITION_VANILLA_ID, new ConditionModuleVanilla());
        CraftingHelper.register(ConditionsReference.CONDITION_VANILLA_MAGIC_ID, new ConditionModuleVanillaMagic());
    }

    public static final class ConditionsReference {

        static final ResourceLocation CONDITION_VANILLA_ID = new ResourceLocation(Reference.MOD_ID, "enable_module_vanilla");
        static final ResourceLocation CONDITION_VANILLA_MAGIC_ID = new ResourceLocation(Reference.MOD_ID, "enable_module_vanilla_magic");

    }

}
