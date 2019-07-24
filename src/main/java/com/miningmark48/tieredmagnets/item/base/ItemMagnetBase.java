package com.miningmark48.tieredmagnets.item.base;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import com.miningmark48.tieredmagnets.client.particle.base.ParticleMagnetize.Particles;
import com.miningmark48.tieredmagnets.client.particle.ParticleMagnetizeEnergy;
import com.miningmark48.tieredmagnets.client.particle.ParticleMagnetizeFree;
import com.miningmark48.tieredmagnets.client.particle.ParticleMagnetizeVanilla;
import com.miningmark48.tieredmagnets.init.ModConfig;
import com.miningmark48.tieredmagnets.reference.Reference;
import com.miningmark48.tieredmagnets.reference.ReferenceGUIs;
import com.miningmark48.tieredmagnets.util.KeyChecker;
import com.miningmark48.tieredmagnets.util.ModTranslate;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleManager;
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
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;
import java.util.Set;

@SuppressWarnings("Duplicates")
@Optional.Interface(iface="baubles.api.IBauble", modid = Reference.BAUBLES)
public abstract class ItemMagnetBase extends Item implements IBauble {

    public double speed;
    private boolean isMagic;
    private final int defaultRange;

    public ItemMagnetBase(int range, double speed, boolean isMagic){
        setMaxStackSize(1);

        this.defaultRange = range;
        this.speed = speed;
        this.isMagic = isMagic;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World playerIn, List<String> list, ITooltipFlag advanced) {
        setTagDefaults(stack);

        list.add(TextFormatting.YELLOW + ModTranslate.toLocal(String.format("tooltip.item.magnet%s_base.line1", (isMagic ? "_magic" : ""))));
        list.add(isEnabled(stack) ? (TextFormatting.DARK_GREEN + ModTranslate.toLocal("tooltip.item.magnet_base.enabled")) : (TextFormatting.DARK_RED + ModTranslate.toLocal("tooltip.item.magnet_base.disabled")));

        if (KeyChecker.isHoldingShift()) {
            list.add(TextFormatting.BLUE + ModTranslate.toLocal("tooltip.item.magnet_base.range1") + TextFormatting.AQUA + " " + getRange(stack) + " " + TextFormatting.BLUE + ModTranslate.toLocal("tooltip.item.magnet_base.range2"));
        } else {
            list.add(ModTranslate.toLocal("tooltip.item.hold") + " " + TextFormatting.AQUA + TextFormatting.ITALIC + ModTranslate.toLocal("tooltip.item.shift"));
        }

    }

    @Override
    public ActionResult onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        ItemStack stack = player.getHeldItem(hand);
        setTagDefaults(stack);

        if (!world.isRemote) {
            if (!player.isSneaking()) {
                toggleMagnet(stack, player);
            } else {
                if (ModConfig.miscconfigs.doFilter && player.getHeldItem(EnumHand.MAIN_HAND).getItem() == this) player.openGui(Reference.MOD_ID, ReferenceGUIs.gui_id_magnet_filter, world, 0, 0, 0);
            }
        }
        return new ActionResult(EnumActionResult.SUCCESS, stack);
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        if (stack.hasTagCompound()) {
            return isEnabled(stack) && ModConfig.miscconfigs.doGlow;
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
        if (isEnabled(stack)) {
            setEnabled(stack, false);
            player.sendStatusMessage(new TextComponentString(TextFormatting.DARK_RED + ModTranslate.toLocal("chat.item.magnet_base.disabled")), true);
        } else {
            setEnabled(stack, true);
            player.sendStatusMessage(new TextComponentString(TextFormatting.GOLD + ModTranslate.toLocal("chat.item.magnet_base.enabled")), true);
        }
        player.getCooldownTracker().setCooldown(stack.getItem(), ModConfig.miscconfigs.cooldownTime);
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected){
        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entity;
            if (!player.isSneaking()) doUpdate(stack, player.world, player.posX, player.posY, player.posZ, player.capabilities.isCreativeMode);
        }
    }

    public void doUpdate(ItemStack stack, World world, double x, double y, double z, boolean noCost){
        setTagDefaults(stack);

        setRange(stack, MathHelper.clamp(getRange(stack), 1, getDefaultRange()));

        if (isEnabled(stack) && (canMagnet(stack) || noCost)) {
            boolean blacklist = stack.getTagCompound().getBoolean("filterModeBlacklist");

            Set<Item> inventory = new ObjectOpenHashSet<>();
            if (ModConfig.miscconfigs.doFilter) {
                NBTTagList invItems = stack.getTagCompound().getTagList("ItemInventory", Constants.NBT.TAG_COMPOUND);
                for (int i = 0; i < invItems.tagCount(); i++) {
                    NBTTagCompound item = invItems.getCompoundTagAt(i);
                    inventory.add(new ItemStack(item).getItem());
                }
            }

            int range = getRange(stack);
            List<EntityItem> items = world.getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(x - range, y - range, z - range, x + range, y + range, z + range));
            items.forEach(e -> {
                if (canMagnetItem(e)) {
                    if (ModConfig.miscconfigs.doFilter) {
                        if (blacklist) {
                            if (!inventory.contains(e.getItem().getItem())) {
                                doMagnet(stack, e, x, y, z, noCost, true);
                            }
                        } else {
                            if (inventory.contains(e.getItem().getItem())) {
                                doMagnet(stack, e, x, y, z, noCost, true);
                            }
                        }
                    } else {
                        doMagnet(stack, e, x, y, z, noCost, true);
                    }
                }
            });
            if (ModConfig.miscconfigs.doXPVacuum) {
                List<EntityXPOrb> xp = world.getEntitiesWithinAABB(EntityXPOrb.class, new AxisAlignedBB(x - range, y - range, z - range, x + range, y + range, z + range));
                xp.forEach(e -> {
                    if (canMagnetItem(e)) {
                        doMagnet(stack, e, x, y, z, noCost, false);
                    }
                });
            }
        }
    }

    public void doMagnet(ItemStack stack, Entity entity, double x, double y, double z, boolean noCost, boolean particles) {
            assert stack.getTagCompound() != null;
            if (!isMagic) {
                entity.addVelocity((x - entity.posX) * speed, (y - entity.posY) * speed, (z - entity.posZ) * speed); //Attracts
//            entity.addVelocity((entity.posX - x) * speed, (entity.posY - y) * speed, (entity.posZ - z) * speed); //Repels
            } else {
                entity.setPositionAndUpdate(x, y, z);
            }

            if (ModConfig.miscconfigs.doParticles && particles && entity.world.isRemote){
                Random rand = new Random();
                double r = 0.15D * Math.sqrt(rand.nextDouble() + 1);
                double th = (rand.nextDouble() + 1) * 2 * Math.PI;
                double pX = entity.posX + r * MathHelper.cos((float) th);
                double pZ = entity.posZ + r * MathHelper.sin((float) th);

                spawnParticles(entity.world, pX, entity.posY, pZ);
            }

            if (!noCost && entity.getDistance(x, y, z) <= ModConfig.miscconfigs.costForDistance) {
                doCost(stack);
            }

    }

    @SideOnly(Side.CLIENT)
    private void spawnParticles(World world, double x, double y, double z) {
        ParticleManager pm = Minecraft.getMinecraft().effectRenderer;
        double yOffset = y + 0.3D;
        switch (getParticle()) {
            default:
            case VANILLA:
                pm.addEffect(new ParticleMagnetizeVanilla(world, x, yOffset, z));
                break;
            case ENERGY:
                pm.addEffect(new ParticleMagnetizeEnergy(world, x, yOffset, z));
                break;
            case FREE:
                pm.addEffect(new ParticleMagnetizeFree(world, x, yOffset, z));
                break;
        }
    }

    private boolean canMagnetItem(Entity entity) {
        return !entity.getEntityData().getBoolean("noMagnet") && !entity.getEntityData().getBoolean("PreventRemoteMovement");
    }

    public boolean canMagnet(ItemStack stack) {
        return true;
    }

    public void doCost(ItemStack stack) {

    }

    public Particles getParticle() {
        return Particles.VANILLA;
    }

    public int getDefaultRange() {
        return this.defaultRange;
    }

    public int getRange(ItemStack stack) {
        setTagDefaults(stack);
        return stack.getTagCompound().getInteger("range");
    }

    public void setRange(ItemStack stack, int range) {
        setTagDefaults(stack);
        stack.getTagCompound().setInteger("range", range);
    }

    private static boolean isEnabled(ItemStack stack) {
        assert stack.getTagCompound() != null;
        return stack.getTagCompound().getBoolean("enabled");
    }

    private static void setEnabled(ItemStack stack, boolean enabled) {
        assert stack.getTagCompound() != null;
        stack.getTagCompound().setBoolean("enabled", enabled);
    }

    private void setTagDefaults(ItemStack stack) {
        if (!stack.hasTagCompound()){
            stack.setTagCompound(new NBTTagCompound());
            stack.getTagCompound().setBoolean("enabled", false);
            stack.getTagCompound().setBoolean("filterModeBlacklist", true);
            stack.getTagCompound().setInteger("range", getDefaultRange());
        }
    }

    /* Baubles */
    @Override
    @Optional.Method(modid = Reference.BAUBLES)
    public void onWornTick(ItemStack stack, EntityLivingBase player) {
        if (player instanceof EntityPlayer) {
            if (!player.isSneaking()) doUpdate(stack, player.getEntityWorld(), player.posX, player.posY, player.posZ, ((EntityPlayer) player).capabilities.isCreativeMode);
        }
    }

    @Override
    @Optional.Method(modid = Reference.BAUBLES)
    public BaubleType getBaubleType(ItemStack itemstack) {
        return BaubleType.RING;
    }
}
