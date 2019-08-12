package com.miningmark48.tieredmagnets.item.base;

import com.miningmark48.tieredmagnets.client.particle.EnumParticles;
import com.miningmark48.tieredmagnets.container.ContainerMagnetFilter;
import com.miningmark48.tieredmagnets.init.config.ModConfig;
import com.miningmark48.tieredmagnets.reference.NBTKeys;
import com.miningmark48.tieredmagnets.util.KeyChecker;
import com.miningmark48.tieredmagnets.util.ModTranslate;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.particles.RedstoneParticleData;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.network.NetworkHooks;
import org.checkerframework.checker.nullness.qual.NonNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;
import java.util.Set;

@SuppressWarnings("Duplicates")
//@Optional.Interface(iface="baubles.api.IBauble", modid = Reference.BAUBLES)
public abstract class ItemMagnetBase extends Item /* implements IBauble */ {

    private boolean isMagic;

    public ItemMagnetBase(Properties properties, boolean isMagic){
        super(properties);

        this.isMagic = isMagic;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World world, List<ITextComponent> list, ITooltipFlag flag) {
        setTagDefaults(stack);

        list.add(new StringTextComponent(TextFormatting.YELLOW + ModTranslate.toLocal(String.format("tooltip.item.magnet%s_base.line1", (isMagic ? "_magic" : "")))));
        list.add(new StringTextComponent(isEnabled(stack) ? (TextFormatting.DARK_GREEN + ModTranslate.toLocal("tooltip.item.magnet_base.enabled")) : (TextFormatting.DARK_RED + ModTranslate.toLocal("tooltip.item.magnet_base.disabled"))));

        if (KeyChecker.isHoldingShift()) {
            list.add(new StringTextComponent(TextFormatting.BLUE + ModTranslate.toLocal("tooltip.item.magnet_base.range1") + TextFormatting.AQUA + " " + getRange(stack) + " " + TextFormatting.BLUE + ModTranslate.toLocal("tooltip.item.magnet_base.range2")));
        } else {
            list.add(new StringTextComponent(ModTranslate.toLocal("tooltip.item.hold") + " " + TextFormatting.AQUA + TextFormatting.ITALIC + ModTranslate.toLocal("tooltip.item.shift")));
        }

        if (stack.getTag() != null /*&& ModConfig.isServerConfigLoaded() && ModConfig.COMMON.debug_nbtTooltips.get()*/) {
            list.add(new StringTextComponent(TextFormatting.GRAY + stack.getTag().toString()));
        }

    }

    @Override
    public ActionResult onItemRightClick(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = getMagnet(player);
        setTagDefaults(stack);

        if (!world.isRemote) {
            if (!player.isSneaking()) {
                toggleMagnet(stack, player);
            } else {
                if (player instanceof ServerPlayerEntity) {
                    if (ModConfig.COMMON.general_enableFiltering.get() && stack.getItem() == this) {
                        NetworkHooks.openGui((ServerPlayerEntity) player, new INamedContainerProvider() {
                            @Nonnull
                            @Override
                            public ITextComponent getDisplayName() {
                                return stack.getDisplayName();
                            }

                            @Nullable
                            @Override
                            public Container createMenu(int i, @Nonnull PlayerInventory playerInventory, @Nonnull PlayerEntity playerEntity) {
                                return new ContainerMagnetFilter(i, playerInventory, stack);
                            }
                        }, packetBuffer -> packetBuffer.writeItemStack(stack));
                    } else {
                        player.sendMessage(new StringTextComponent(TextFormatting.DARK_RED + ModTranslate.toLocal("chat.item.magnet_base.filtering.disabled")));
                    }
                }
            }
        }
        return new ActionResult(ActionResultType.SUCCESS, stack);
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        if (stack.hasTag()) {
            return isEnabled(stack);
        }
        return false;
    }

    public static ItemStack getMagnet(PlayerEntity player) {
        ItemStack heldItem = player.getHeldItemMainhand();
        if (!(heldItem.getItem() instanceof ItemMagnetBase)) {
            heldItem = player.getHeldItemOffhand();
            if (!(heldItem.getItem() instanceof ItemMagnetBase)) {
                return ItemStack.EMPTY;
            }
        }
        return heldItem;
    }

    public static void toggleMagnet(ItemStack stack, PlayerEntity player) {
        if (isEnabled(stack)) {
            setEnabled(stack, false);
            player.sendStatusMessage(new StringTextComponent(TextFormatting.DARK_RED + ModTranslate.toLocal("chat.item.magnet_base.disabled")), true);
        } else {
            setEnabled(stack, true);
            player.sendStatusMessage(new StringTextComponent(TextFormatting.GOLD + ModTranslate.toLocal("chat.item.magnet_base.enabled")), true);
        }
        player.getCooldownTracker().setCooldown(stack.getItem(), ModConfig.COMMON.general_cooldownTime.get());
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_) {
        if (entity instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) entity;
            if (!player.isSneaking()) doUpdate(stack, player.world, player.posX, player.posY, player.posZ, player.abilities.isCreativeMode);
        }
    }

    public void doUpdate(ItemStack stack, World world, double x, double y, double z, boolean noCost){
        if (isEnabled(stack) && (canMagnet(stack) || noCost)) {
            assert stack.getTag() != null;
            boolean blacklist = stack.getTag().getBoolean(NBTKeys.FILTER_MODE.getKey());

            Set<Item> inventory = new ObjectOpenHashSet<>();
            if (ModConfig.COMMON.general_enableFiltering.get()) {
                ListNBT invItems = stack.getTag().getList(NBTKeys.ITEM_INV.getKey(), Constants.NBT.TAG_COMPOUND);
                for (int i = 0; i < invItems.size(); i++) {
                    CompoundNBT item = invItems.getCompound(i);
                    inventory.add(ItemStack.read(item).getItem());
                }
            }

            int range = getRange(stack);
            List<ItemEntity> items = world.getEntitiesWithinAABB(ItemEntity.class, new AxisAlignedBB(x - range, y - range, z - range, x + range, y + range, z + range));
            items.forEach(e -> {
                if (canMagnetItem(e)) {
                    if (ModConfig.COMMON.general_enableFiltering.get()) {
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
            if (ModConfig.COMMON.general_enableXPMagnet.get()) {
                List<ExperienceOrbEntity> xp = world.getEntitiesWithinAABB(ExperienceOrbEntity.class, new AxisAlignedBB(x - range, y - range, z - range, x + range, y + range, z + range));
                xp.forEach(e -> {
                    if (canMagnetItem(e)) {
                        doMagnet(stack, e, x, y, z, noCost, false);
                    }
                });
            }
        }
    }

    public void doMagnet(ItemStack stack, Entity entity, double x, double y, double z, boolean noCost, boolean particles) {
        assert stack.getTag() != null;
        if (!isMagic) {
            double speed = getSpeed();
            entity.addVelocity((x - entity.posX) * speed, (y - entity.posY) * speed, (z - entity.posZ) * speed); //Attracts
//            entity.addVelocity((entity.posX - x) * speed, (entity.posY - y) * speed, (entity.posZ - z) * speed); //Repels
        } else {
            entity.setPositionAndUpdate(x, y, z);
        }

        if (particles && entity.world.isRemote && ModConfig.CLIENT.general_enableParticles.get()) {
            Random rand = new Random();
            double r = 0.15D * Math.sqrt(rand.nextDouble() + 1);
            double th = (rand.nextDouble() + 1) * 2 * Math.PI;
            double pX = entity.posX + r * MathHelper.cos((float) th);
            double pZ = entity.posZ + r * MathHelper.sin((float) th);

            spawnParticles(entity.world, pX, entity.posY, pZ);
        }

        if (!noCost && entity.getDistanceSq(x, y, z) <= ModConfig.COMMON.general_costDistance.get()) {
            doCost(stack, entity.world, entity);
        }

    }

    @OnlyIn(Dist.CLIENT)
    private void spawnParticles(World world, double x, double y, double z) {
        getParticle().spawnParticle(world, x, y + 0.3D, z, 0.5f);
    }

    private boolean canMagnetItem(Entity entity) {
        return !entity.getEntityData().getBoolean(NBTKeys.NO_MAGNET.getKey()) && !entity.getEntityData().getBoolean(NBTKeys.INT_DEMAGNETIZE.getKey());
    }

    public boolean canMagnet(ItemStack stack) {
        return true;
    }

    public void doCost(ItemStack stack, World world, Entity entity) {

    }

    @NonNull
    public EnumParticles getParticle() {
        return EnumParticles.VANILLA;
    }

    public int getDefaultRange() {
        return 0;
    }

    public int getRange(ItemStack stack) {
//        setTagDefaults(stack);
        assert stack.getTag() != null;
        return stack.getTag().getInt(NBTKeys.RANGE.getKey());
    }

    public void setRange(ItemStack stack, int range) {
//        setTagDefaults(stack);
        assert stack.getTag() != null;
        stack.getTag().putInt(NBTKeys.RANGE.getKey(), MathHelper.clamp(range, 1, getDefaultRange()));
    }

    public double getSpeed() {
        return 0;
    }

    private static boolean isEnabled(ItemStack stack) {
        assert stack.getTag() != null;
        return stack.getTag().getBoolean(NBTKeys.ENABLED.getKey());
    }

    private static void setEnabled(ItemStack stack, boolean enabled) {
        assert stack.getTag() != null;
        stack.getTag().putBoolean(NBTKeys.ENABLED.getKey(), enabled);
    }

    public void setTagDefaults(ItemStack stack) {
        if (!stack.hasTag()){
            stack.setTag(new CompoundNBT());
            assert stack.getTag() != null;
            stack.getTag().putBoolean(NBTKeys.ENABLED.getKey(), false);
            stack.getTag().putBoolean(NBTKeys.FILTER_MODE.getKey(), true);
        }
    }

    public int calculateAmount(int base, int multiplier, int tier) {
        return base + (base * multiplier * tier);
    }

    /* Baubles */
//    @Override
//    @Optional.Method(modid = Reference.BAUBLES)
//    public void onWornTick(ItemStack stack, EntityLivingBase player) {
//        if (player instanceof EntityPlayer) {
//            if (!player.isSneaking()) doUpdate(stack, player.getEntityWorld(), player.posX, player.posY, player.posZ, ((EntityPlayer) player).capabilities.isCreativeMode);
//        }
//    }
//
//    @Override
//    @Optional.Method(modid = Reference.BAUBLES)
//    public BaubleType getBaubleType(ItemStack itemstack) {
//        return BaubleType.RING;
//    }
}
