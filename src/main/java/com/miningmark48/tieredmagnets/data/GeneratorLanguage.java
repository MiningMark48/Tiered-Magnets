package com.miningmark48.tieredmagnets.data;

import com.miningmark48.tieredmagnets.init.ModBlocks;
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
        add(ModBlocks.MAGNETIC_INSULATOR.get(), "Magnetic Insulator");
        add(ModBlocks.MAGNETIC_PROJECTOR.get(), "Magnetic Insulator");
    }

}
