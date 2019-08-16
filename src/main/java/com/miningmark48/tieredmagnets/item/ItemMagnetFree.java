package com.miningmark48.tieredmagnets.item;

import com.miningmark48.tieredmagnets.client.particle.EnumParticles;
import com.miningmark48.tieredmagnets.init.config.ModConfig;
import com.miningmark48.tieredmagnets.item.base.ItemMagnetBase;
import com.miningmark48.tieredmagnets.reference.NBTKeys;
import com.miningmark48.tieredmagnets.reference.Translations;
import com.miningmark48.tieredmagnets.util.KeyChecker;
import com.miningmark48.tieredmagnets.util.ModTranslate;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class ItemMagnetFree extends ItemMagnetBase {

    public ItemMagnetFree(Properties properties, boolean isMagic) {
        super(properties, isMagic);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World playerIn, List<ITextComponent> list, ITooltipFlag advanced) {
        super.addInformation(stack, playerIn, list, advanced);
        if (KeyChecker.isHoldingShift()) {
            list.add(new StringTextComponent(TextFormatting.GOLD + ModTranslate.toLocal(Translations.Tooltips.I_MAGNET_FREE.getTooltip())));
        }

//        if (OldConfig.cursedMagnetsConfigs.vanishing && !EnchantmentHelper.hasVanishingCurse(stack)) list.add(new StringTextComponent(TextFormatting.RED + ModTranslate.toLocal("enchantment.vanishing_curse")));
    }

    @Override
    public void onUsingTick(ItemStack stack, LivingEntity entity, int count) {
//        if (OldConfig.cursedMagnetsConfigs.vanishing && !EnchantmentHelper.hasVanishingCurse(stack)) stack.addEnchantment(Enchantments.VANISHING_CURSE, 1);
        super.onUsingTick(stack, entity, count);
    }

    @Override
    public void setTagDefaults(ItemStack stack) {
        if (!stack.hasTag()) {
            super.setTagDefaults(stack);
            assert stack.getTag() != null;
            stack.getTag().putInt(NBTKeys.RANGE.getKey(), getDefaultRange());
        }
    }

    @Override
    public int getDefaultRange() {
        try {
            return ModConfig.COMMON.cursed_range.get();
        } catch (NullPointerException e) {
            return super.getDefaultRange();
        }
    }

    @Override
    public double getSpeed() {
        return ModConfig.COMMON.cursed_speed.get();
    }

    @Nonnull
    @Override
    public EnumParticles getParticle() {
        return EnumParticles.FREE;
    }

}
