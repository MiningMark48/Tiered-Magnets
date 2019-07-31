package com.miningmark48.tieredmagnets.init.config;

import com.miningmark48.tieredmagnets.reference.Reference;
import com.miningmark48.tieredmagnets.util.ModLogger;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.*;
import net.minecraftforge.fml.common.Mod;

import static net.minecraftforge.fml.Logging.CORE;

@Mod.EventBusSubscriber
@SuppressWarnings("Duplicates")
public class ModConfig {

    private static final String CATEGORY_GENERAL = "general";

    private static final Builder COMMON_BUILDER = new Builder();
    private static final Builder SERVER_BUILDER = new Builder();
    private static final Builder CLIENT_BUILDER = new Builder();

    public static final CategoryGeneral GENERAL = new CategoryGeneral();
    public static final CategoryModules MODULES = new CategoryModules();
    public static final CategoryVanilla MODULE_VANILLA = new CategoryVanilla();
    public static final CategoryThermalExpansion MODULE_TE = new CategoryThermalExpansion();
    public static final CategoryCursed MODULE_CURSED = new CategoryCursed();
    public static final CategoryUtilityBlocks MODULE_UTILITY_BLOCKS = new CategoryUtilityBlocks();

    public static final class CategoryGeneral {

        //Client
        public final BooleanValue enableParticles;
        //Server
        public final IntValue cooldownTime;
        public final DoubleValue costDistance;
        public final BooleanValue enableFiltering;
        public final BooleanValue enableXPMagnet;

        private static String modules_name = "general";
        private static String general_comment = "General mod settings";

        private CategoryGeneral() {
            SERVER_BUILDER.comment(general_comment).push(modules_name);
            CLIENT_BUILDER.comment(general_comment).push(modules_name);
            COMMON_BUILDER.comment(general_comment).push(modules_name);

            //Client
            enableParticles = CLIENT_BUILDER
                    .comment("If true, particles will be displayed.")
                    .define("Enable Particles", true);

            //Server
            cooldownTime = SERVER_BUILDER
                    .comment("The time (in ticks) between when the player can enable and disable a magnet.")
                    .defineInRange("Cooldown Time", 10, 0, Integer.MAX_VALUE);

            costDistance = SERVER_BUILDER
                    .comment("The maximum distance in which magnetizing items and XP begins to have a cost.")
                    .defineInRange("Cost Distance", 1.5D, 0, Double.MAX_VALUE);

            enableFiltering = SERVER_BUILDER
                    .comment("If true,  magnets will be able to be filtered.")
                    .define("Enable Filtering", true);
            enableXPMagnet = SERVER_BUILDER
                    .comment("If true,  magnets will be able to be attract experience orbs.")
                    .define("XP Magnet", true);

            SERVER_BUILDER.pop();
            CLIENT_BUILDER.pop();
            COMMON_BUILDER.pop();
        }

    }

    public static final class CategoryModules {

        public final BooleanValue enableModuleVanilla;
        public final BooleanValue enableModuleVanillaMagic;
        public final BooleanValue enableModuleCursed;
        public final BooleanValue enableModuleCursedMagic;
        public final BooleanValue enableUtilityBlocks;

        private static String modules_name = "modules";
        private static String modules_comment = "Module control settings";

        private CategoryModules() {
            SERVER_BUILDER.comment(modules_comment).push(modules_name);
            CLIENT_BUILDER.comment(modules_comment).push(modules_name);
            COMMON_BUILDER.comment(modules_comment).push(modules_name);


            enableModuleVanilla = SERVER_BUILDER
                    .comment("If true, enables recipes for Vanilla-based, durability magnets.")
                    .define("Vanilla", true);
            enableModuleVanillaMagic = SERVER_BUILDER
                    .comment("If true, enables recipes for Vanilla-based, durability magnets that teleport items to the player.")
                    .define("Vanilla - Magic", true);
            enableModuleCursed = SERVER_BUILDER
                    .comment("If true, enables recipes for magnets that have no cost to use.")
                    .define("Cursed", true);
            enableModuleCursedMagic = SERVER_BUILDER
                    .comment("If true, enables recipes for magnets that teleport items to the player while having no cost to use.")
                    .define("Cursed - Magic", true);
            enableUtilityBlocks = SERVER_BUILDER
                    .comment("If true, utility blocks for the magnets will be enabled.")
                    .define("Utility Blocks", true);

            //TODO: Thermal and Patchouli once they're updated


            SERVER_BUILDER.pop();
            CLIENT_BUILDER.pop();
            COMMON_BUILDER.pop();
        }

    }

    public static final class CategoryVanilla {

        //Defaults
        public final int defaultBaseRange = 2;
        public final int defaultMultiplierRange = 4;
        public final int defaultBaseDurability = 1024;
        public final int defaultMultiplierDurability = 1;
        public final int defaultMultiplierMagic = 3;

        public final BooleanValue hasCost;
        public final DoubleValue speed;
        public final IntValue baseRange;
        public final IntValue multiplierRange;
        public final IntValue baseDurability;
        public final IntValue multiplierDurability;
        public final IntValue multiplierMagic;

        private static String modules_name = "vanilla";
        private static String modules_comment = "Vanilla module control settings";

        private CategoryVanilla() {
            SERVER_BUILDER.comment(modules_comment).push(modules_name);
            CLIENT_BUILDER.comment(modules_comment).push(modules_name);
            COMMON_BUILDER.comment(modules_comment).push(modules_name);


            hasCost = SERVER_BUILDER
                    .comment("If true, magnets will take damage when used.")
                    .define("Has Cost", true);

            speed = SERVER_BUILDER
                    .comment("Speed in which items are pulled toward the player.")
                    .defineInRange("Speed", 0.05D, 0.01D, Double.MAX_VALUE);

            baseRange = SERVER_BUILDER
                    .comment("Set the base range for the vanilla magnets.")
                    .defineInRange("Base Range", defaultBaseRange, 1, Integer.MAX_VALUE);
            multiplierRange = SERVER_BUILDER
                    .comment("Affects the increase in range between tiers.")
                    .defineInRange("Multiplier Range", defaultMultiplierRange, 0, Integer.MAX_VALUE);
            baseDurability = SERVER_BUILDER
                    .comment("Set the base durability for the vanilla magnets.")
                    .defineInRange("[DISABLED] Base Durability", defaultBaseDurability, 1, Integer.MAX_VALUE);
            multiplierDurability = SERVER_BUILDER
                    .comment("Affects the increase in durability between magnet tiers.")
                    .defineInRange("[DISABLED] Multiplier Durability", defaultMultiplierDurability, 1, Integer.MAX_VALUE);
            multiplierMagic = SERVER_BUILDER
                    .comment("Affects the increase in damage in the magic magnets.")
                    .defineInRange("Multiplier Magic", defaultMultiplierMagic, 0, Integer.MAX_VALUE);


            SERVER_BUILDER.pop();
            CLIENT_BUILDER.pop();
            COMMON_BUILDER.pop();
        }

    }

    public static final class CategoryThermalExpansion {

        //Defaults
        public final int defaultBaseRange = 8;
        public final int defaultMultiplierRange = 1;

        public final DoubleValue speed;
        public final IntValue baseRange;
        public final IntValue multiplierRange;

        private static String modules_name = "thermal_expansion";
        private static String modules_comment = "Thermal module control settings";

        private CategoryThermalExpansion() {
            SERVER_BUILDER.comment(modules_comment).push(modules_name);
            CLIENT_BUILDER.comment(modules_comment).push(modules_name);
            COMMON_BUILDER.comment(modules_comment).push(modules_name);

            speed = SERVER_BUILDER
                    .comment("Speed in which items are pulled toward the player.")
                    .defineInRange("Speed", 0.075D, 0.01D, Double.MAX_VALUE);

            baseRange = SERVER_BUILDER
                    .comment("Set the base range for the vanilla magnets.")
                    .defineInRange("Base Range", defaultBaseRange, 1, Integer.MAX_VALUE);
            multiplierRange = SERVER_BUILDER
                    .comment("Affects the increase in range between tiers.")
                    .defineInRange("Multiplier Range", defaultMultiplierRange, 0, Integer.MAX_VALUE);

            //TODO: Rest of configs once energy is added

            SERVER_BUILDER.pop();
            CLIENT_BUILDER.pop();
            COMMON_BUILDER.pop();
        }

    }

    public static final class CategoryCursed {

        //Defaults
        public final int defaultRange = 64;

        public final DoubleValue speed;
        public final IntValue range;

        private static String modules_name = "cursed";
        private static String modules_comment = "Cursed module control settings";

        private CategoryCursed() {
            SERVER_BUILDER.comment(modules_comment).push(modules_name);
            CLIENT_BUILDER.comment(modules_comment).push(modules_name);
            COMMON_BUILDER.comment(modules_comment).push(modules_name);


            speed = SERVER_BUILDER
                    .comment("Speed in which items are pulled toward the player.")
                    .defineInRange("Speed", 0.05D, 0.01D, Double.MAX_VALUE);

            range = SERVER_BUILDER
                    .comment("Set the range for the Cursed Magnets.")
                    .defineInRange("Range", defaultRange, 1, Integer.MAX_VALUE);


            SERVER_BUILDER.pop();
            CLIENT_BUILDER.pop();
            COMMON_BUILDER.pop();
        }

    }

    public static final class CategoryUtilityBlocks {

        //Client
        public final BooleanValue enableLampRender;

        //Server
        public final BooleanValue enableMInsulator;
        public final BooleanValue enableMProjector;
        public final IntValue insulatorRange;

        private static String modules_name = "utility_blocks";
        private static String modules_comment = "Utility Block module control settings";

        private CategoryUtilityBlocks() {
            SERVER_BUILDER.comment(modules_comment).push(modules_name);
            CLIENT_BUILDER.comment(modules_comment).push(modules_name);
            COMMON_BUILDER.comment(modules_comment).push(modules_name);

            //Client
            enableLampRender = CLIENT_BUILDER
                    .comment("If true, a lamp render will be displayed on the Magnetic Projector. Disabling MAY improve performance.")
                    .define("Enable Magnetic Projector Render", true);

            //Server
            enableMInsulator = SERVER_BUILDER
                    .comment("If true, enables a block to prevent items from being picked up.")
                    .define("Enable Magnetic Insulator", true);
            enableMProjector = SERVER_BUILDER
                    .comment("If true, enables a block to recreate a magnet in block-form.")
                    .define("Enable Magnetic Projector", true);

            insulatorRange = SERVER_BUILDER
                    .comment("Affects the maximum range in which the Magnetic Insulator can disable item pickup.")
                    .defineInRange("Magnetic Insulator Range", 16, 1, Integer.MAX_VALUE);


            SERVER_BUILDER.pop();
            CLIENT_BUILDER.pop();
            COMMON_BUILDER.pop();
        }

    }

    public static final ForgeConfigSpec COMMON_CONFIG = COMMON_BUILDER.build();
    public static final ForgeConfigSpec SERVER_CONFIG = SERVER_BUILDER.build();
    public static final ForgeConfigSpec CLIENT_CONFIG = CLIENT_BUILDER.build();
    private static boolean serverCfgLoaded = false;

    private static void loadServerConfig() {
        serverCfgLoaded = true;
    }

    public static void onLoad(final net.minecraftforge.fml.config.ModConfig.Loading configEvent) {
        if (configEvent.getConfig().getSpec() == ModConfig.SERVER_CONFIG)
            loadServerConfig();
        ModLogger.debug("Loaded {} config file {}", Reference.MOD_ID, configEvent.getConfig().getFileName());
    }

    public static void onFileChange(final net.minecraftforge.fml.config.ModConfig.ConfigReloading configEvent) {
        ModLogger.fatal(CORE, "{} config just got changed on the file system!", Reference.MOD_ID);
    }

    public static boolean isServerConfigLoaded() {
        return serverCfgLoaded;
    }

}
