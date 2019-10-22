package com.miningmark48.tieredmagnets.init.config.crafting;

import com.google.gson.JsonObject;
import com.miningmark48.tieredmagnets.init.config.ModConfig;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;

public class ConditionModuleEnabled implements ICondition {

    private static final ResourceLocation NAME = new ResourceLocation("tieredmagnets", "module_enabled");
    private final String module_name;

    public ConditionModuleEnabled(String module_name)
    {
        this.module_name = module_name;
    }

    @Override
    public ResourceLocation getID()
    {
        return NAME;
    }

    @Override
    public boolean test()
    {
        switch (module_name) {
            default:
                return true;
            case "vanilla":
                return ModConfig.COMMON.enableModuleVanilla.get();
            case "vanilla_magic":
                return ModConfig.COMMON.enableModuleVanillaMagic.get();
            case "cursed":
                return ModConfig.COMMON.enableModuleCursed.get();
            case "cursed_magic":
                return ModConfig.COMMON.enableModuleCursedMagic.get();
            case "te":
                return ModConfig.COMMON.enableModuleTE.get();
            case "te_magic":
                return ModConfig.COMMON.enableModuleTE.get();
            case "insulator":
                return ModConfig.COMMON.ub_enableMInsulator.get();
            case "projector":
                return ModConfig.COMMON.ub_enableMProjector.get();
        }
    }

    @Override
    public String toString()
    {
        return "module_enabled(\"" + module_name + "\")";
    }

    public static class Serializer implements IConditionSerializer<ConditionModuleEnabled>
    {
        public static final ConditionModuleEnabled.Serializer INSTANCE = new ConditionModuleEnabled.Serializer();

        @Override
        public void write(JsonObject json, ConditionModuleEnabled value)
        {
            json.addProperty("module_name", value.module_name);
        }

        @Override
        public ConditionModuleEnabled read(JsonObject json)
        {
            return new ConditionModuleEnabled(JSONUtils.getString(json, "module_name"));
        }

        @Override
        public ResourceLocation getID()
        {
            return ConditionModuleEnabled.NAME;
        }
    }

}
