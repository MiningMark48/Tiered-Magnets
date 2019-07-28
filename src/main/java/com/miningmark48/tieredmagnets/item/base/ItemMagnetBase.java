package com.miningmark48.tieredmagnets.item.base;

import com.miningmark48.tieredmagnets.client.particle.ParticleMagnetizeEnergy;
import com.miningmark48.tieredmagnets.client.particle.ParticleMagnetizeFree;
import com.miningmark48.tieredmagnets.client.particle.ParticleMagnetizeVanilla;
import com.miningmark48.tieredmagnets.client.particle.base.ParticleMagnetize.Particles;
import com.miningmark48.tieredmagnets.container.ContainerMagnetFilter;
import com.miningmark48.tieredmagnets.init.ModConfig;
import com.miningmark48.tieredmagnets.util.KeyChecker;
import com.miningmark48.tieredmagnets.util.ModLogger;
import com.miningmark48.tieredmagnets.util.ModTranslate;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
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
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;
import java.util.Set;

@SuppressWarnings("Duplicates")
//@Optional.Interface(iface="baubles.api.IBauble", modid = Reference.BAUBLES)
public abstract class ItemMagnetBase extends Item /* implements IBauble */ {

    public double speed;
    private boolean isMagic;
    private final int defaultRange;

    public ItemMagnetBase(Properties properties, int range, double speed, boolean isMagic){
        super(properties);

        this.defaultRange = range;
        this.speed = speed;
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
                    if (ModConfig.miscconfigs.doFilter && stack.getItem() == this) {
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
                    }
                }
            }
        }
        return new ActionResult(ActionResultType.SUCCESS, stack);
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        if (stack.hasTag()) {
            return isEnabled(stack) && ModConfig.miscconfigs.doGlow;
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
        player.getCooldownTracker().setCooldown(stack.getItem(), ModConfig.miscconfigs.cooldownTime);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_) {
        if (entity instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) entity;
            if (!player.isSneaking()) doUpdate(stack, player.world, player.posX, player.posY, player.posZ, player.abilities.isCreativeMode);
        }
    }

    public void doUpdate(ItemStack stack, World world, double x, double y, double z, boolean noCost){
        setTagDefaults(stack);

        setRange(stack, MathHelper.clamp(getRange(stack), 1, getDefaultRange()));

        if (isEnabled(stack) && (canMagnet(stack) || noCost)) {
            assert stack.getTag() != null;
            boolean blacklist = stack.getTag().getBoolean("filterModeBlacklist");

            Set<Item> inventory = new ObjectOpenHashSet<>();
            if (ModConfig.miscconfigs.doFilter) {
                ListNBT invItems = stack.getTag().getList("ItemInventory", Constants.NBT.TAG_COMPOUND);
                for (int i = 0; i < invItems.size(); i++) {
                    CompoundNBT item = invItems.getCompound(i);
                    inventory.add(ItemStack.read(item).getItem());
                }
            }

            int range = getRange(stack);
            List<ItemEntity> items = world.getEntitiesWithinAABB(ItemEntity.class, new AxisAlignedBB(x - range, y - range, z - range, x + range, y + range, z + range));
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

            if (!noCost && entity.getDistanceSq(x, y, z) <= ModConfig.miscconfigs.costForDistance) {
                doCost(stack);
            }

    }

//    @SideOnly(Side.CLIENT)
    private void spawnParticles(World world, double x, double y, double z) {
        ParticleManager pm = Minecraft.getInstance().particles;
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
        assert stack.getTag() != null;
        return stack.getTag().getInt("range");
    }

    public void setRange(ItemStack stack, int range) {
        setTagDefaults(stack);
        assert stack.getTag() != null;
        stack.getTag().putInt("range", range);
    }

    private static boolean isEnabled(ItemStack stack) {
        assert stack.getTag() != null;
        return stack.getTag().getBoolean("enabled");
    }

    private static void setEnabled(ItemStack stack, boolean enabled) {
        assert stack.getTag() != null;
        stack.getTag().putBoolean("enabled", enabled);
    }

    private void setTagDefaults(ItemStack stack) {
        if (!stack.hasTag()){
            stack.setTag(new CompoundNBT());
            assert stack.getTag() != null;
            stack.getTag().putBoolean("enabled", false);
            stack.getTag().putBoolean("filterModeBlacklist", true);
            stack.getTag().putInt("range", getDefaultRange());
        }
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
