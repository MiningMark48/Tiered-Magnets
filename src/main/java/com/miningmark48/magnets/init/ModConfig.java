package com.miningmark48.magnets.init;

import com.miningmark48.magnets.reference.Reference;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = Reference.MOD_ID, name = Reference.MOD_NAME, category = Reference.MOD_ID)
public class ModConfig {

    public static Modules modules = new Modules();
    public static VanillaConfigs vanillaConfigs = new VanillaConfigs();
    public static ThermalExpansionConfigs thermalExpansionConfigs = new ThermalExpansionConfigs();
    public static MiscConfigs miscconfigs = new MiscConfigs();

    public static class Modules {
        @Config.Comment("If true, enables Vanilla-based, durability magnets.")
        @Config.RequiresMcRestart
        public boolean vanillaModule = true;
        @Config.Comment("If true, enables Thermal Expansion-based, RF-powered magnets (Requires Thermal Expansion).")
        @Config.RequiresMcRestart
        public boolean thermalExpansionModule = true;
//        @Config.Comment("If true, enables special magnets.")
//        public boolean specialModule = true;
    }

    public static class VanillaConfigs {
        @Config.RangeDouble(min = 0.01)
        @Config.Comment("Affects the speed in which items are attracted.")
        @Config.RequiresMcRestart
        public double speed = 0.05D;
        @Config.RangeInt(min = 1)
        @Config.Comment("Set the base range for the vanilla magnets.")
        @Config.RequiresMcRestart
        public int baseRange = 2;
        @Config.RangeInt(min = 0)
        @Config.Comment("Affects the increase in range between tiers.")
        @Config.RequiresMcRestart
        public int multiplierRange = 4;
        @Config.RangeInt(min = 1)
        @Config.Comment("Set the base durability for the vanilla magnets.")
        @Config.RequiresMcRestart
        public int baseDurability = 1024;
        @Config.RangeDouble(min = 0)
        @Config.Comment("Affects the increase in durability between tiers.")
        @Config.RequiresMcRestart
        public double multiplierDurability = 0.5D;
    }

    public static class ThermalExpansionConfigs {
        //TODO
    }

    public static class MiscConfigs { // heh heh ;)
        @Config.Comment("If true, particles will be displayed as items are attracted.")
        public boolean doParticles = true;
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
