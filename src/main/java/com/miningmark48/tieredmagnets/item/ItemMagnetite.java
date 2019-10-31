package com.miningmark48.tieredmagnets.item;

import com.miningmark48.tieredmagnets.init.ModCreativeTab;
import com.miningmark48.tieredmagnets.init.config.ModConfig;
import com.miningmark48.tieredmagnets.reference.Translations;
import com.miningmark48.tieredmagnets.util.ModTranslate;
import net.minecraft.block.Blocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemMagnetite extends Item {

    public ItemMagnetite() {
        super(new Properties().group(ModCreativeTab.Magnets_Tab));
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        super.addInformation(stack, world, tooltip, flag);

        tooltip.add(new StringTextComponent(TextFormatting.YELLOW + String.format(ModTranslate.toLocal(Translations.Tooltips.I_MAGNETITE_DROPS.getTooltip()) + " %s", Blocks.IRON_ORE.getNameTextComponent().getString())));
        if (ModConfig.COMMON.general_enableRepair.get()) tooltip.add(new StringTextComponent(TextFormatting.DARK_GREEN + ModTranslate.toLocal(Translations.Tooltips.I_MAGNETITE_REPAIR.getTooltip())));
    }
}
