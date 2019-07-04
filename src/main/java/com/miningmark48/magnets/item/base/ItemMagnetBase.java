package com.miningmark48.magnets.item.base;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import com.miningmark48.magnets.init.ModConfig;
import com.miningmark48.magnets.reference.Reference;
import com.miningmark48.magnets.reference.ReferenceGUIs;
import com.miningmark48.magnets.util.ModLogger;
import com.miningmark48.magnets.util.ModTranslate;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Optional.Interface(iface="baubles.api.IBauble", modid = Reference.BAUBLES)
public abstract class ItemMagnetBase extends Item implements IBauble {

    private final int defaultRange;
    private int range;
    private double speed;
    private boolean isMagic;

    public ItemMagnetBase(int range, double speed, boolean isMagic){
        setMaxStackSize(1);

        this.defaultRange = range;
        this.range = range;
        this.speed = speed;
        this.isMagic = isMagic;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World playerIn, List<String> list, ITooltipFlag advanced) {
        if (!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
            stack.getTagCompound().setBoolean("enabled", false);
            stack.getTagCompound().setBoolean("filterModeBlacklist", true);
        }

        list.add(TextFormatting.YELLOW + ModTranslate.toLocal(String.format("tooltip.item.magnet%s_base.line1", (isMagic ? "_magic" : ""))));

        list.add(stack.getTagCompound().getBoolean("enabled") ? (TextFormatting.DARK_GREEN + ModTranslate.toLocal("tooltip.item.magnet_base.enabled")) : (TextFormatting.DARK_RED + ModTranslate.toLocal("tooltip.item.magnet_base.disabled")));
        list.add(TextFormatting.BLUE + ModTranslate.toLocal("tooltip.item.magnet_base.range1") + TextFormatting.AQUA + " " + range + " " + TextFormatting.BLUE + ModTranslate.toLocal("tooltip.item.magnet_base.range2"));
    }

    @Override
    public ActionResult onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        ItemStack stack = player.getHeldItem(hand);
        if (!stack.hasTagCompound()){
            stack.setTagCompound(new NBTTagCompound());
            stack.getTagCompound().setBoolean("enabled", false);
            stack.getTagCompound().setBoolean("filterModeBlacklist", true);
        }

        if (!world.isRemote) {
            if (!player.isSneaking()) {
                toggleMagnet(stack, player);
            } else {
                if(ModConfig.miscconfigs.doFilter) player.openGui(Reference.MOD_ID, ReferenceGUIs.gui_id_magnet_filter, world, 0, 0, 0);
            }
        }
        return new ActionResult(EnumActionResult.SUCCESS, stack);
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        if (stack.hasTagCompound()) {
            return stack.getTagCompound().getBoolean("enabled") && ModConfig.miscconfigs.doGlow;
        }
        return false;
    }

    public static ItemStack getMagnet(EntityPlayer player) {
        ItemStack heldItem = player.getHeldItemMainhand();
        if (!(heldItem.getItem() instanceof ItemMagnetBase)) {
            heldItem = player.getHeldItemOffhand();
            if (!(heldItem.getItem() instanceof ItemMagnetBase)) {
                return ItemStack.EMPTY;
            }
        }
        return heldItem;
    }

    public static void toggleMagnet(ItemStack stack, EntityPlayer player) {
        if (stack.getTagCompound().getBoolean("enabled")) {
            stack.getTagCompound().setBoolean("enabled", false);
            player.sendMessage(new TextComponentString(TextFormatting.DARK_RED + ModTranslate.toLocal("chat.item.magnet_base.disabled")));
        } else {
            stack.getTagCompound().setBoolean("enabled", true);
            player.sendMessage(new TextComponentString(TextFormatting.GOLD + ModTranslate.toLocal("chat.item.magnet_base.enabled")));
        }
        player.getCooldownTracker().setCooldown(stack.getItem(), 10);
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected){
        doUpdate(stack, world, entity);
    }

    public void doUpdate(ItemStack stack, World world, Entity entity){
        if (!stack.hasTagCompound()){
            stack.setTagCompound(new NBTTagCompound());
            stack.getTagCompound().setBoolean("enabled", false);
            stack.getTagCompound().setBoolean("filterModeBlacklist", true);
        }

        if (getRange() > getDefaultRange()) {
            setRange(getDefaultRange());
        } else if (getRange() <= 0) {
            setRange(1);
        }

        if (entity instanceof EntityPlayer){
            EntityPlayer player = (EntityPlayer) entity;

            if (stack.getTagCompound().getBoolean("enabled") && (canMagnet(player, stack) || player.capabilities.isCreativeMode)){
                double x = player.posX;
                double y = player.posY;
                double z = player.posZ;
                boolean blacklist = stack.getTagCompound().getBoolean("filterModeBlacklist");

                ArrayList<Item> inventory = new ArrayList<>();
                if (ModConfig.miscconfigs.doFilter) {
                    NBTTagList invItems = stack.getTagCompound().getTagList("ItemInventory", Constants.NBT.TAG_COMPOUND);
                    for (int i = 0; i < invItems.tagCount(); i++) {
                        NBTTagCompound item = invItems.getCompoundTagAt(i);
                        inventory.add(new ItemStack(item).getItem());
                    }
                }

                List<EntityItem> items = entity.world.getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(x - range, y - range, z - range, x + range, y + range, z + range));
                for (EntityItem e: items){
                    if (ModConfig.miscconfigs.doFilter) {
                        if (blacklist) {
                            if (!inventory.contains(e.getItem().getItem())) {
                                doMagnet(stack, e, player, true);
                            }
                        } else {
                            if (inventory.contains(e.getItem().getItem())) {
                                doMagnet(stack, e, player, true);
                            }
                        }
                    } else {
                        doMagnet(stack, e, player, true);
                    }
                }
                if (ModConfig.miscconfigs.doXPVacuum) {
                    List<EntityXPOrb> xp = entity.world.getEntitiesWithinAABB(EntityXPOrb.class, new AxisAlignedBB(x - range, y - range, z - range, x + range, y + range, z + range));
                    for (EntityXPOrb e : xp) {
                        doMagnet(stack, e, player, false);
                    }
                }
            }
        }
    }

    public void doMagnet(ItemStack stack, Entity entity, EntityPlayer player, boolean particles) {
        double x = player.posX;
        double y = player.posY;
        double z = player.posZ;
        if (!player.isSneaking()) {
            assert stack.getTagCompound() != null;
            if (!isMagic) {
                entity.addVelocity((x - entity.posX) * speed, (y - entity.posY) * speed, (z - entity.posZ) * speed); //Attracts
//            entity.addVelocity((entity.posX - x) * speed, (entity.posY - y) * speed, (entity.posZ - z) * speed); //Repels
            } else {
                entity.setPositionAndUpdate(x, y, z);
            }

            if (ModConfig.miscconfigs.doParticles && particles){
                Random rand = new Random();
                double r = 0.15D * Math.sqrt(rand.nextDouble() + 1);
                double th = (rand.nextDouble() + 1) * 2 * Math.PI;
                double pX = entity.posX + r * Math.cos(th);
                double pZ = entity.posZ + r * Math.sin(th);
                player.world.spawnParticle(getParticle(), pX, entity.posY + 0.3, pZ, 0.0D, 0.0D, 0.0D);
            }

            if (!player.capabilities.isCreativeMode) doCost(player, stack);
        }
    }

    public boolean canMagnet(EntityPlayer player, ItemStack stack) {
        return true;
    }

    public void doCost(EntityPlayer player, ItemStack stack) {

    }

    public EnumParticleTypes getParticle() {
        return EnumParticleTypes.REDSTONE;
    }

    public int getDefaultRange() {
        return this.defaultRange;
    }

    public int getRange() {
        return this.range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    /* Baubles */
    @Override
    @Optional.Method(modid = Reference.BAUBLES)
    public void onWornTick(ItemStack stack, EntityLivingBase player) {
        if (player instanceof EntityPlayer) {
            doUpdate(stack, player.getEntityWorld(), player);
        }
    }

    @Override
    @Optional.Method(modid = Reference.BAUBLES)
    public BaubleType getBaubleType(ItemStack itemstack) {
        return BaubleType.RING;
    }
}
