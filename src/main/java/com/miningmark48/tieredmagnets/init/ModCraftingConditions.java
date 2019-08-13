package com.miningmark48.tieredmagnets.init;

import com.miningmark48.tieredmagnets.init.config.crafting.*;
import com.miningmark48.tieredmagnets.reference.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;

public class ModCraftingConditions {

    public static void init() {
        //Vanilla
        CraftingHelper.register(ConditionsReference.CONDITION_VANILLA_ID, new ConditionModuleVanilla());
        CraftingHelper.register(ConditionsReference.CONDITION_VANILLA_MAGIC_ID, new ConditionModuleVanillaMagic());
        //Thermal Expansion
        CraftingHelper.register(ConditionsReference.CONDITION_TE_ID, new ConditionModuleTE());
        CraftingHelper.register(ConditionsReference.CONDITION_TE_MAGIC_ID, new ConditionModuleTEMagic());
        //Cursed
        CraftingHelper.register(ConditionsReference.CONDITION_CURSED_ID, new ConditionModuleCursed());
        CraftingHelper.register(ConditionsReference.CONDITION_CURSED_MAGIC_ID, new ConditionModuleCursedMagic());
        //Utility Blocks
        CraftingHelper.register(ConditionsReference.CONDITION_MINSULATOR_ID, new ConditionBlockMInsulator());
        CraftingHelper.register(ConditionsReference.CONDITION_MPROJECTOR_ID, new ConditionBlockMProjector());
    }

    public static final class ConditionsReference {

        //Vanilla
        static final ResourceLocation CONDITION_VANILLA_ID = new ResourceLocation(Reference.MOD_ID, "enable_module_vanilla");
        static final ResourceLocation CONDITION_VANILLA_MAGIC_ID = new ResourceLocation(Reference.MOD_ID, "enable_module_vanilla_magic");
        //Thermal Expansion
        static final ResourceLocation CONDITION_TE_ID = new ResourceLocation(Reference.MOD_ID, "enable_module_te");
        static final ResourceLocation CONDITION_TE_MAGIC_ID = new ResourceLocation(Reference.MOD_ID, "enable_module_te_magic");
        //Cursed
        static final ResourceLocation CONDITION_CURSED_ID = new ResourceLocation(Reference.MOD_ID, "enable_module_cursed");
        static final ResourceLocation CONDITION_CURSED_MAGIC_ID = new ResourceLocation(Reference.MOD_ID, "enable_module_cursed_magic");
        //Utility Blocks
        static final ResourceLocation CONDITION_MINSULATOR_ID = new ResourceLocation(Reference.MOD_ID, "enable_ub_minsulator");
        static final ResourceLocation CONDITION_MPROJECTOR_ID = new ResourceLocation(Reference.MOD_ID, "enable_ub_mprojector");

    }

}
