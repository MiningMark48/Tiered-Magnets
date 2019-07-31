package com.miningmark48.tieredmagnets.init.config;

public class OldConfig {

//      ||~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*||
//      ||                              ||
//      ||  THIS FILE IS A PLACEHOLDER  ||
//      ||      AND WILL BE REMOVED     ||
//      ||                              ||
//      ||~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*||


    public static ThermalExpansionConfigs thermalExpansionConfigs = new ThermalExpansionConfigs();
    public static UtilityBlockConfigs utilityBlockConfigs = new UtilityBlockConfigs();
    public static MiscConfigs miscconfigs = new MiscConfigs();

    public static class ThermalExpansionConfigs {

//        @Config.Name("Has Cost")
//        @Config.Comment("If true, magnets will consume energy when used.")
//        public boolean hasCost = true;

//        @Config.Name("Speed")
//        @Config.RangeDouble(min = 0.01)
//        @Config.Comment("Affects the speed in which items are attracted.")
//        @Config.RequiresMcRestart
        public double speed = 0.075D;

//        @Config.Name("Energy Transfer Rate")
//        @Config.RangeInt(min = 1)
//        @Config.Comment("The rate at which the magnet can be charged by a machine.")
//        @Config.RequiresMcRestart
        public int transferRate = 2500;

//        @Config.Name("Base Range")
//        @Config.RangeInt(min = 1)
//        @Config.Comment("Set the base range for the vanilla magnets.")
//        @Config.RequiresMcRestart
        public int baseRange = 8;

//        @Config.Name("Multiplier Range")
//        @Config.RangeInt(min = 0)
//        @Config.Comment("Affects the increase in range between tiers.")
//        @Config.RequiresMcRestart
        public int multiplierRange = 1;

//        @Config.Name("Base Energy")
//        @Config.RangeInt(min = 1)
//        @Config.Comment("Set the max energy storage for the electromagnets.")
//        @Config.RequiresMcRestart
        public int baseEnergy = 25000;

//        @Config.Name("Multiplier Energy")
//        @Config.RangeInt(min = 0)
//        @Config.Comment("Affects the increase in energy storage between electromagnet tiers.")
//        @Config.RequiresMcRestart
        public int multiplierEnergy = 4;

//        @Config.Name("Base Energy Usage")
//        @Config.RangeInt(min = 1)
//        @Config.Comment("The amount of energy used per tick for each tier.")
//        @Config.RequiresMcRestart
        public int baseUsageEnergy = 10;

//        @Config.Name("Multiplier Energy Usage")
//        @Config.RangeInt(min = 1)
//        @Config.Comment("Affects the increase in energy usage between electromagnet tiers.")
//        @Config.RequiresMcRestart
        public int multiplierUsageEnergy = 1;

//        @Config.Name("Multiplier Magic Energy Usage")
//        @Config.RangeDouble(min = 0)
//        @Config.Comment("Affects the increase in energy usage in the magic magnets.")
//        @Config.RequiresMcRestart
        public double multiplierMagic = 3D;

    }

    public static class UtilityBlockConfigs {
        //M. Insulator
//        @Config.Name("Magnetic Insulator")
//        @Config.Comment("If true, enables a block to prevent items from being picked up.")
//        @Config.RequiresMcRestart
        public boolean insulator = true;

//        @Config.Name("Magnetic Insulator - Range")
//        @Config.Comment("Affects the maximum range in which the Magnetic Insulator can disable item pickup.")
        public int insulatorRange = 16;

        //M. Projector
//        @Config.Name("Magnetic Projector")
//        @Config.Comment("If true, enables a block to recreate a magnet in block-form.")
//        @Config.RequiresMcRestart
        public boolean projector = true;

//        @Config.Name("Magnetic Projector - Lamp Render")
//        @Config.Comment("If true, a lamp render will be displayed on the Magnetic Projector.")
        public boolean projectorLampRender = true;
    }

    public static class MiscConfigs { // heh heh ;)
//        @Config.Name("Particles")
//        @Config.Comment("If true, particles will be displayed as items are attracted.")
//        public boolean doParticles = true;

//        @Config.Name("Glow")
//        @Config.Comment("If true, magnets will glow when enabled.")
        public boolean doGlow = true;

//        @Config.Name("Filter")
//        @Config.Comment("If true, magnets will be able to be filtered.")
        public boolean doFilter = true;

//        @Config.Name("XP Vacuum")
//        @Config.Comment("If true, XP will be vacuumed toward the player.")
        public boolean doXPVacuum = true;

//        @Config.Name("Cooldown Time")
//        @Config.Comment("The time (in ticks) the player can enable/disable a magnet.")
//        @Config.RangeInt(min = 0)
        public int cooldownTime = 10;

//        @Config.Name("Cost for Distance")
//        @Config.Comment("The maximum distance in which magnetizing items and XP begins to have a cost.")
//        @Config.RangeDouble(min = 0)
        public double costForDistance = 1.5D;

    }

}
