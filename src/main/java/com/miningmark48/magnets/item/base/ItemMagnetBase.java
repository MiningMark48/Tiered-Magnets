package com.miningmark48.magnets.item.base;

import com.miningmark48.magnets.init.ModConfig;
import com.miningmark48.mininglib.base.item.ModBaseItem;
import com.miningmark48.mininglib.utility.ModTranslate;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public abstract class ItemMagnetBase extends ModBaseItem {

    private int range;
    private double speed;

    public ItemMagnetBase(int range, double speed){
        setMaxStackSize(1);

        this.range = range;
        this.speed = speed;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World playerIn, List<String> list, ITooltipFlag advanced) {
        if (!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
            stack.getTagCompound().setBoolean("enabled", false);
        }

        list.add(TextFormatting.YELLOW + ModTranslate.toLocal("tooltip.item.magnet_base.line1"));
        list.add(stack.getTagCompound().getBoolean("enabled") ? (TextFormatting.DARK_GREEN + ModTranslate.toLocal("tooltip.item.magnet_base.enabled")) : (TextFormatting.DARK_RED + ModTranslate.toLocal("tooltip.item.magnet_base.disabled")));
        list.add(TextFormatting.BLUE + ModTranslate.toLocal("tooltip.item.magnet_base.range1") + TextFormatting.AQUA + " " + range + " " + TextFormatting.BLUE + ModTranslate.toLocal("tooltip.item.magnet_base.range2"));
    }

    @Override
    public ActionResult onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        ItemStack stack = player.getHeldItem(hand);
        if (!stack.hasTagCompound()){
            stack.setTagCompound(new NBTTagCompound());
            stack.getTagCompound().setBoolean("enabled", false);
        }

        if (!world.isRemote) {
            if (!player.isSneaking()) {
                if (stack.getTagCompound().getBoolean("enabled")) {
                    stack.getTagCompound().setBoolean("enabled", false);
                    player.sendMessage(new TextComponentString(TextFormatting.DARK_RED + ModTranslate.toLocal("chat.item.magnet_base.disabled")));
                } else {
                    stack.getTagCompound().setBoolean("enabled", true);
                    player.sendMessage(new TextComponentString(TextFormatting.GOLD + ModTranslate.toLocal("chat.item.magnet_base.enabled")));
                }
            }
        }

        player.getCooldownTracker().setCooldown(this, 10);

        return new ActionResult(EnumActionResult.SUCCESS, stack);

    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected){
        doUpdate(stack, world, entity);
    }

    public void doUpdate(ItemStack stack, World world, Entity entity){

        if (!stack.hasTagCompound()){
            stack.setTagCompound(new NBTTagCompound());
            stack.getTagCompound().setBoolean("enabled", false);
            stack.getTagCompound().setString("mode", "Attracts");
        }

        if (entity instanceof EntityPlayer){
            EntityPlayer player = (EntityPlayer) entity;

            if (stack.getTagCompound().getBoolean("enabled") && (canMagnet(player, stack) || player.capabilities.isCreativeMode)){
                double x = player.posX;
                double y = player.posY;
                double z = player.posZ;

                List<EntityItem> items = entity.world.getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(x - range, y - range, z - range, x + range, y + range, z + range));
                List<EntityXPOrb> xp = entity.world.getEntitiesWithinAABB(EntityXPOrb.class, new AxisAlignedBB(x - range, y - range, z - range, x + range, y + range, z + range));
                for (EntityItem e: items){
                    doMagnet(stack, e, player);
                    if (!player.isSneaking() && ModConfig.miscconfigs.doParticles){
                        world.spawnParticle(EnumParticleTypes.REDSTONE, e.posX, e.posY + 0.3, e.posZ, 0.0D, 0.0D, 0.0D);
//                            world.spawnParticle(EnumParticleTypes.PORTAL, e.posX, e.posY - 0.3, e.posZ, 0.0D, 0.0D, 0.0D);
                    }
                }
                for (EntityXPOrb e: xp){
                    doMagnet(stack, e, player);
                }
            }
        }
    }

    public void doMagnet(ItemStack stack, Entity entity, EntityPlayer player) {
        double x = player.posX;
        double y = player.posY;
        double z = player.posZ;
        if (!player.isSneaking()) {
            assert stack.getTagCompound() != null;
            entity.addVelocity((x - entity.posX) * speed, (y - entity.posY) * speed, (z - entity.posZ) * speed); //Attracts
//            entity.addVelocity((entity.posX - x) * speed, (entity.posY - y) * speed, (entity.posZ - z) * speed); //Repels
            if (!player.capabilities.isCreativeMode) doCost(player, stack);
        }
    }

    public boolean canMagnet(EntityPlayer player, ItemStack stack) {
        return true;
    }

    public void doCost(EntityPlayer player, ItemStack stack) {

    }

}
