package com.miningmark48.tieredmagnets.init;

import com.miningmark48.tieredmagnets.reference.Reference;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = Reference.MOD_ID, name = Reference.MOD_NAME, category = Reference.MOD_ID)
public class ModConfig {

    @Config.Name("Modules")
    @Config.Comment("Module control")
    public static Modules modules = new Modules();

    @Config.Name("Vanilla")
    @Config.Comment("Vanilla configuration settings")
    public static VanillaConfigs vanillaConfigs = new VanillaConfigs();

    @Config.Name("Thermal Expansion")
    @Config.Comment("Thermal Expansion configuration settings")
    public static ThermalExpansionConfigs thermalExpansionConfigs = new ThermalExpansionConfigs();

    @Config.Name("Cursed Magnets")
    @Config.Comment("Cursed Magnets configuration settings")
    public static CursedMagnetConfigs cursedMagnetsConfigs = new CursedMagnetConfigs();

    @Config.Name("Utility Blocks")
    @Config.Comment("Magnetic Insulator configuration settings")
    public static UtilityBlockConfigs utilityBlockConfigs = new UtilityBlockConfigs();

    @Config.Name("Misc")
    @Config.Comment("Miscellaneous configuration settings")
    public static MiscConfigs miscconfigs = new MiscConfigs();

    public static class Modules {
        @Config.Name("Vanilla")
        @Config.Comment("If true, enables Vanilla-based, durability magnets.")
        @Config.RequiresMcRestart
        public boolean vanillaModule = true;

        @Config.Name("Thermal Expansion")
        @Config.Comment("If true, enables Thermal Expansion-based, RF-powered magnets (Requires Thermal Expansion).")
        @Config.RequiresMcRestart
        public boolean thermalExpansionModule = true;

        @Config.Name("Vanilla - Magic")
        @Config.Comment("If true, enables Vanilla-based, durability magnets that teleport items to the player.")
        @Config.RequiresMcRestart
        public boolean vanillaMagicModule = true;

        @Config.Name("Thermal Expansion - Magic")
        @Config.Comment("If true, enables Thermal Expansion-based, RF-powered magnets that teleport items to the player (Requires Thermal Expansion).")
        @Config.RequiresMcRestart
        public boolean thermalExpansionMagicModule = true;

        @Config.Name("Cursed Magnets")
        @Config.Comment("If true, enables magnets that have no cost to use.")
        @Config.RequiresMcRestart
        public boolean cursedMagnetsModule = true;

        @Config.Name("Utility Blocks")
        @Config.Comment("If true, utility blocks for the magnets will be enabled.")
        public boolean utilityBlocksModule = true;

        @Config.Name("Patchouli")
        @Config.Comment("If true, enables the Tiered Magnets manual added by Patchouli.")
        @Config.RequiresMcRestart
        public boolean patchouliModule = true;

    }

    public static class VanillaConfigs {
        @Config.Name("Speed")
        @Config.RangeDouble(min = 0.01)
        @Config.Comment("Affects the speed in which items are attracted.")
        @Config.RequiresMcRestart
        public double speed = 0.05D;

        @Config.Name("Base Range")
        @Config.RangeInt(min = 1)
        @Config.Comment("Set the base range for the vanilla magnets.")
        @Config.RequiresMcRestart
        public int baseRange = 2;

        @Config.Name("Multiplier Range")
        @Config.RangeInt(min = 0)
        @Config.Comment("Affects the increase in range between tiers.")
        @Config.RequiresMcRestart
        public int multiplierRange = 4;

        @Config.Name("Base Durability")
        @Config.RangeInt(min = 1)
        @Config.Comment("Set the base durability for the vanilla magnets.")
        @Config.RequiresMcRestart
        public int baseDurability = 1024;

        @Config.Name("Multiplier Durability")
        @Config.RangeDouble(min = 0)
        @Config.Comment("Affects the increase in durability between magnet tiers.")
        @Config.RequiresMcRestart
        public double multiplierDurability = 0.5D;

        @Config.Name("Multiplier Magic")
        @Config.RangeInt(min = 0)
        @Config.Comment("Affects the increase in damage in the magic magnets.")
        @Config.RequiresMcRestart
        public int multiplierMagic = 3;

    }

    public static class ThermalExpansionConfigs {
        @Config.Name("Speed")
        @Config.RangeDouble(min = 0.01)
        @Config.Comment("Affects the speed in which items are attracted.")
        @Config.RequiresMcRestart
        public double speed = 0.075D;

        @Config.Name("Energy Transfer Rate")
        @Config.RangeInt(min = 1)
        @Config.Comment("The rate at which the magnet can be charged by a machine.")
        @Config.RequiresMcRestart
        public int transferRate = 2500;

        @Config.Name("Base Range")
        @Config.RangeInt(min = 1)
        @Config.Comment("Set the base range for the vanilla magnets.")
        @Config.RequiresMcRestart
        public int baseRange = 8;

        @Config.Name("Multiplier Range")
        @Config.RangeInt(min = 0)
        @Config.Comment("Affects the increase in range between tiers.")
        @Config.RequiresMcRestart
        public int multiplierRange = 1;

        @Config.Name("Base Energy")
        @Config.RangeInt(min = 1)
        @Config.Comment("Set the max energy storage for the electromagnets.")
        @Config.RequiresMcRestart
        public int baseEnergy = 25000;

        @Config.Name("Multiplier Energy")
        @Config.RangeInt(min = 0)
        @Config.Comment("Affects the increase in energy storage between electromagnet tiers.")
        @Config.RequiresMcRestart
        public int multiplierEnergy = 4;

        @Config.Name("Base Energy Usage")
        @Config.RangeInt(min = 1)
        @Config.Comment("The amount of energy used per tick for each tier.")
        @Config.RequiresMcRestart
        public int baseUsageEnergy = 10;

        @Config.Name("Multiplier Energy Usage")
        @Config.RangeInt(min = 1)
        @Config.Comment("Affects the increase in energy usage between electromagnet tiers.")
        @Config.RequiresMcRestart
        public int multiplierUsageEnergy = 1;

        @Config.Name("Multiplier Magic Energy Usage")
        @Config.RangeDouble(min = 0)
        @Config.Comment("Affects the increase in energy usage in the magic magnets.")
        @Config.RequiresMcRestart
        public double multiplierMagic = 3D;

    }

    public static class CursedMagnetConfigs {
        @Config.Name("Cursed Magnet")
        @Config.Comment("If true, the Cursed Magnet will be enabled.")
        @Config.RequiresMcRestart
        public boolean cursedMagnet = true;

        @Config.Name("Magic Cursed Magnet")
        @Config.Comment("If true, the Magic Cursed Magnet will be enabled.")
        @Config.RequiresMcRestart
        public boolean magicCursedMagnet = true;

        @Config.Name("Curse of Vanishing")
        @Config.Comment("If true, the Curse of Vanishing will be applied to the magnet, causing it to be lost on death.")
        public boolean vanishing = true;

        @Config.Name("Speed")
        @Config.RangeDouble(min = 0.01)
        @Config.Comment("Affects the speed in which items are attracted.")
        @Config.RequiresMcRestart
        public double speed = 0.05D;

        @Config.Name("Range")
        @Config.RangeInt(min = 1)
        @Config.Comment("Set the range for the Cursed Magnets.")
        @Config.RequiresMcRestart
        public int range = 64;

    }

    public static class UtilityBlockConfigs {
        //M. Insulator
        @Config.Name("Magnetic Insulator")
        @Config.Comment("If true, enables a block to prevent items from being picked up.")
        @Config.RequiresMcRestart
        public boolean insulator = true;

        @Config.Name("Magnetic Insulator - Range")
        @Config.Comment("Affects the maximum range in which the Magnetic Insulator can disable item pickup.")
        public int insulatorRange = 16;

        //M. Projector
        @Config.Name("Magnetic Projector")
        @Config.Comment("If true, enables a block to recreate a magnet in block-form.")
        @Config.RequiresMcRestart
        public boolean projector = true;

        @Config.Name("Magnetic Projector - Lamp Render")
        @Config.Comment("If true, a lamp render will be displayed on the Magnetic Projector.")
        public boolean projectorLampRender = true;
    }

    public static class MiscConfigs { // heh heh ;)
        @Config.Name("Particles")
        @Config.Comment("If true, particles will be displayed as items are attracted.")
        public boolean doParticles = true;

        @Config.Name("Glow")
        @Config.Comment("If true, magnets will glow when enabled.")
        public boolean doGlow = true;

        @Config.Name("Filter")
        @Config.Comment("If true, magnets will be able to be filtered.")
        public boolean doFilter = true;

        @Config.Name("XP Vacuum")
        @Config.Comment("If true, XP will be vacuumed toward the player.")
        public boolean doXPVacuum = true;

        @Config.Name("Cooldown Time")
        @Config.Comment("The time (in ticks) the player can enable/disable a magnet.")
        @Config.RangeInt(min = 0)
        public int cooldownTime = 10;

        @Config.Name("Cost for Distance")
        @Config.Comment("The maximum distance in which magnetizing items and XP begins to have a cost.")
        @Config.RangeDouble(min = 0)
        public double costForDistance = 1.5D;

    }

    @Mod.EventBusSubscriber
    public static class ConfigHolder {
        @SubscribeEvent
        public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
            if (event.getModID().equals(Reference.MOD_ID)) {
                ConfigManager.sync(Reference.MOD_ID, Config.Type.INSTANCE);
            }
        }
    }

}
