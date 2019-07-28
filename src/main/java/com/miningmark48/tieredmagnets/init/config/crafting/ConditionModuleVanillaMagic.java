package com.miningmark48.tieredmagnets.init.config.crafting;

import com.google.gson.JsonObject;
import net.minecraftforge.common.crafting.IConditionSerializer;

import java.util.function.BooleanSupplier;

public class ConditionModuleVanillaMagic implements IConditionSerializer {

    @Override
    public BooleanSupplier parse(JsonObject json) {
        // return ModConfig.MODULE.enableVanilla::get; //TODO
        return () -> true;
    }
}
