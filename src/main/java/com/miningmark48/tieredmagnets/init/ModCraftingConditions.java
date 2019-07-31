package com.miningmark48.tieredmagnets.init;

import com.miningmark48.tieredmagnets.init.config.crafting.ConditionModuleCursed;
import com.miningmark48.tieredmagnets.init.config.crafting.ConditionModuleCursedMagic;
import com.miningmark48.tieredmagnets.init.config.crafting.ConditionModuleVanilla;
import com.miningmark48.tieredmagnets.init.config.crafting.ConditionModuleVanillaMagic;
import com.miningmark48.tieredmagnets.reference.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;

public class ModCraftingConditions {

    public static void init() {
        //Vanilla
        CraftingHelper.register(ConditionsReference.CONDITION_VANILLA_ID, new ConditionModuleVanilla());
        CraftingHelper.register(ConditionsReference.CONDITION_VANILLA_MAGIC_ID, new ConditionModuleVanillaMagic());
        //Cursed
        CraftingHelper.register(ConditionsReference.CONDITION_CURSED_ID, new ConditionModuleCursed());
        CraftingHelper.register(ConditionsReference.CONDITION_CURSED_MAGIC_ID, new ConditionModuleCursedMagic());
    }

    public static final class ConditionsReference {

        //Vanilla
        static final ResourceLocation CONDITION_VANILLA_ID = new ResourceLocation(Reference.MOD_ID, "enable_module_vanilla");
        static final ResourceLocation CONDITION_VANILLA_MAGIC_ID = new ResourceLocation(Reference.MOD_ID, "enable_module_vanilla_magic");
        //Cursed
        static final ResourceLocation CONDITION_CURSED_ID = new ResourceLocation(Reference.MOD_ID, "enable_module_cursed");
        static final ResourceLocation CONDITION_CURSED_MAGIC_ID = new ResourceLocation(Reference.MOD_ID, "enable_module_cursed_magic");

    }

}
