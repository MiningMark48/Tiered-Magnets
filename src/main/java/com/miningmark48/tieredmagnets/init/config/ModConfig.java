package com.miningmark48.tieredmagnets.init.config;

import com.miningmark48.tieredmagnets.reference.Reference;
import com.miningmark48.tieredmagnets.util.ModLogger;
import net.minecraft.client.Minecraft;
import net.minecraft.server.integrated.IntegratedServer;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.DoubleValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;
import net.minecraftforge.fml.common.Mod;
import org.apache.commons.lang3.tuple.Pair;

@Mod.EventBusSubscriber
@SuppressWarnings({"Duplicates", "FieldCanBeLocal"})
public class ModConfig {

    public static final ForgeConfigSpec serverSpec;
    public static final ForgeConfigSpec clientSpec;
    public static final ForgeConfigSpec commonSpec;
    public static final ServerConfigs SERVER;
    public static final ClientConfigs CLIENT;
    public static final CommonConfigs COMMON;

    private static String name_general = "general";
    private static String comment_general = "General mod settings";
    private static String name_modules = "modules";
    private static String comment_modules = "Module control settings";
    private static String name_vanilla = "vanilla";
    private static String comment_vanilla = "Vanilla module control settings";
    private static String name_thermal = "thermal_expansion";
    private static String comment_thermal = "Thermal module control settings";
    private static String name_cursed = "cursed";
    private static String comment_cursed = "Cursed module control settings";
    private static String name_utilityBlocks = "utility_blocks";
    private static String comment_utilityBlocks = "Utility Block module control settings";

    static {
        final Pair<CommonConfigs, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(CommonConfigs::new);
        commonSpec = specPair.getRight();
        COMMON = specPair.getLeft();
    }

    static {
        final Pair<ServerConfigs, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(ServerConfigs::new);
        serverSpec = specPair.getRight();
        SERVER = specPair.getLeft();
    }

    static {
        final Pair<ClientConfigs, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(ClientConfigs::new);
        clientSpec = specPair.getRight();
        CLIENT = specPair.getLeft();
    }

    public static final class CommonConfigs {

        //General
        public final IntValue general_cooldownTime;
        public final DoubleValue general_costDistance;
        public final BooleanValue general_enableFiltering;
        public final BooleanValue general_enableXPMagnet;

        //Modules
        public final BooleanValue enableModuleVanilla;
        public final BooleanValue enableModuleVanillaMagic;
        public final BooleanValue enableModuleCursed;
        public final BooleanValue enableModuleCursedMagic;
        public final BooleanValue enableUtilityBlocks;

        //Module - Vanilla
        public final BooleanValue vanilla_hasCost;
        public final DoubleValue vanilla_speed;
        public final IntValue vanilla_baseRange;
        public final IntValue vanilla_multiplierRange;
        public final IntValue vanilla_baseDurability;
        public final IntValue vanilla_multiplierDurability;
        public final IntValue vanilla_multiplierMagic;

        //Module - Thermal Expansion
        public final DoubleValue te_speed;
        public final IntValue te_baseRange;
        public final IntValue te_multiplierRange;
        public final IntValue te_baseEnergy;
        public final IntValue te_multiplierEnergy;
        public final IntValue te_baseUsageEnergy;
        public final IntValue te_multiplierUsageEnergy;
        public final DoubleValue te_multiplierMagic;

        //Module - Cursed
        public final DoubleValue cursed_speed;
        public final IntValue cursed_range;

        //Module - Utility Blocks
        public final BooleanValue ub_enableMInsulator;
        public final BooleanValue ub_enableMProjector;
        public final BooleanValue ub_enableMagnetSwap;
        public final IntValue ub_insulatorRange;

        //Debug
        public final BooleanValue debug_nbtTooltips;

        private CommonConfigs(ForgeConfigSpec.Builder builder) {

            //General
            builder.comment(comment_general).push(name_general);
            general_cooldownTime = builder
                    .comment("The time (in ticks) between when the player can enable and disable a magnet.")
                    .defineInRange("Cooldown Time", 10, 0, Integer.MAX_VALUE);
            general_costDistance = builder
                    .comment("The maximum distance in which magnetizing items and XP begins to have a cost.")
                    .defineInRange("Cost Distance", 1.5D, 0, Double.MAX_VALUE);
            general_enableFiltering = builder
                    .comment("If true, magnets will be able to be filtered.")
                    .define("Enable Filtering", true);
            general_enableXPMagnet = builder
                    .comment("If true, magnets will be able to be attract experience orbs.")
                    .define("XP Magnet", true);
            builder.pop();

            //Modules
            builder.comment(comment_modules).push(name_modules);
            enableModuleVanilla = builder
                    .comment("If true, enables recipes for Vanilla-based, durability magnets.")
                    .define("Vanilla", true);
            enableModuleVanillaMagic = builder
                    .comment("If true, enables recipes for Vanilla-based, durability magnets that teleport items to the player.")
                    .define("Vanilla - Magic", true);
            enableModuleCursed = builder
                    .comment("If true, enables recipes for magnets that have no cost to use.")
                    .define("Cursed", true);
            enableModuleCursedMagic = builder
                    .comment("If true, enables recipes for magnets that teleport items to the player while having no cost to use.")
                    .define("Cursed - Magic", true);
            enableUtilityBlocks = builder
                    .comment("If true, utility blocks for the magnets will be enabled.")
                    .define("Utility Blocks", true);
            //TODO: Thermal Expansion and Patchouli
            builder.pop();

            //Module - Vanilla
            builder.comment(comment_vanilla).push(name_vanilla);
            vanilla_hasCost = builder
                    .comment("If true, magnets will take damage when used.")
                    .define("Has Cost", true);
            vanilla_speed = builder
                    .comment("Speed in which items are pulled toward the player.")
                    .defineInRange("Speed", 0.05D, 0.01D, Double.MAX_VALUE);
            vanilla_baseRange = builder
                    .comment("Set the base range for the vanilla magnets.")
                    .defineInRange("Base Range", 2, 1, Integer.MAX_VALUE);
            vanilla_multiplierRange = builder
                    .comment("Affects the increase in range between tiers.")
                    .defineInRange("Multiplier Range", 3, 0, Integer.MAX_VALUE);
            vanilla_baseDurability = builder
                    .comment("Set the base durability for the vanilla magnets.")
                    .defineInRange("Base Durability", 1024, 1, Integer.MAX_VALUE);
            vanilla_multiplierDurability = builder
                    .comment("Affects the increase in durability between magnet tiers.")
                    .defineInRange("Multiplier Durability", 1, 1, Integer.MAX_VALUE);
            vanilla_multiplierMagic = builder
                    .comment("Affects the increase in damage in the magic magnets.")
                    .defineInRange("Multiplier Magic", 3, 0, Integer.MAX_VALUE);
            builder.pop();

            //Module - Thermal
            builder.comment(comment_thermal).push(name_thermal);
            te_speed = builder
                    .comment("Speed in which items are pulled toward the player.")
                    .defineInRange("Speed", 0.075D, 0.01D, Double.MAX_VALUE);
            te_baseRange = builder
                    .comment("Set the base range for the vanilla magnets.")
                    .defineInRange("Base Range", 8, 1, Integer.MAX_VALUE);
            te_multiplierRange = builder
                    .comment("Affects the increase in range between tiers.")
                    .defineInRange("Multiplier Range", 1, 0, Integer.MAX_VALUE);
            te_baseEnergy = builder
                    .comment("Set the max energy storage for the electromagnets.")
                    .defineInRange("Base Energy", 25000, 1, Integer.MAX_VALUE);
            te_multiplierEnergy = builder
                    .comment("Affects the increase in energy storage between electromagnet tiers.")
                    .defineInRange("Multiplier Energy", 4, 1, Integer.MAX_VALUE);
            te_baseUsageEnergy = builder
                    .comment("The amount of energy used per tick for each tier when an item is within range.")
                    .defineInRange("Base Energy Usage", 10, 1, Integer.MAX_VALUE);
            te_multiplierUsageEnergy = builder
                    .comment("Affects the increase in energy usage between electromagnet tiers.")
                    .defineInRange("Multiplier Energy Usage", 1, 1, Integer.MAX_VALUE);
            te_multiplierMagic = builder
                    .comment("Affects the increase in energy usage in the magic magnets.")
                    .defineInRange("Multiplier Magic Energy Usage", 3D, 0, Double.MAX_VALUE);
            builder.pop();

            //Module - Cursed
            builder.comment(comment_cursed).push(name_cursed);
            cursed_speed = builder
                    .comment("Speed in which items are pulled toward the player.")
                    .defineInRange("Speed", 0.05D, 0.01D, Double.MAX_VALUE);
            cursed_range = builder
                    .comment("Set the range for the Cursed Magnets.")
                    .defineInRange("Range", 64, 1, Integer.MAX_VALUE);
            builder.pop();

            //Module - Utility Blocks
            builder.comment(comment_utilityBlocks).push(name_utilityBlocks);
            ub_enableMInsulator = builder
                    .comment("If true, enables a block to prevent items from being picked up.")
                    .define("Enable Magnetic Insulator", true);
            ub_enableMProjector = builder
                    .comment("If true, enables a block to recreate a magnet in block-form.")
                    .define("Enable Magnetic Projector", true);
            ub_enableMagnetSwap = builder
                    .comment("If true, a magnet can be inserted into the Magnetic Projector by right clicking the block while holding a magnet.")
                    .define("Enable Magnet Swap", true);
            ub_insulatorRange = builder
                    .comment("Affects the maximum range in which the Magnetic Insulator can disable item pickup.")
                    .defineInRange("Magnetic Insulator Range", 16, 1, Integer.MAX_VALUE);
            builder.pop();

            //Debug
            builder.push("debug");
            debug_nbtTooltips = builder
                    .comment("If true, tooltips will be added to the magnets to show their NBT.")
                    .define("NBT Tooltips", false);
            builder.pop();

        }

    }

    public static final class ServerConfigs {

        private ServerConfigs(ForgeConfigSpec.Builder builder) {

        }

    }

    public static final class ClientConfigs {

        public final BooleanValue general_enableParticles;

        public final BooleanValue ub_enableLampRender;

        private ClientConfigs(ForgeConfigSpec.Builder builder) {
            builder.comment(comment_general).push(name_general);
            general_enableParticles = builder
                    .comment("If true, particles will be displayed.")
                    .define("Enable Particles", true);
            builder.pop();

            builder.comment(comment_utilityBlocks).push(name_utilityBlocks);
            ub_enableLampRender = builder
                    .comment("If true, a lamp render will be displayed on the Magnetic Projector. Disabling MAY improve performance.")
                    .define("Enable Magnetic Projector Render", true);
            builder.pop();

        }

    }

    private static boolean serverCfgLoaded = false;

    private static void loadServerConfig() {
        serverCfgLoaded = true;
    }

    public static void onLoad(final net.minecraftforge.fml.config.ModConfig.Loading configEvent) {
        if (configEvent.getConfig().getSpec() == ModConfig.serverSpec)
            loadServerConfig();

        ModLogger.debug("Loaded %s config file %s", Reference.MOD_ID, configEvent.getConfig().getFileName());
    }

    public static void onFileChange(final net.minecraftforge.fml.config.ModConfig.ConfigReloading configEvent) {
        ModLogger.info("%s config just got changed on the file system, reloading!", Reference.MOD_ID);
        IntegratedServer serv = Minecraft.getInstance().getIntegratedServer();
        if (serv != null) serv.reload();
    }

    public static boolean isServerConfigLoaded() {
        return serverCfgLoaded;
    }

}
