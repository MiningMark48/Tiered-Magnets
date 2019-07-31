package com.miningmark48.tieredmagnets.init;

public class ModRecipes {

//    public static void init(RegistryEvent.Register<IRecipe> e) {
//        if (OldConfig.modules.thermalExpansionModule && Loader.isModLoaded(Reference.TE)) {
//            IntegrationThermalExpansion.init();
//        }
//
//        //Manual
//        if (OldConfig.modules.patchouliModule && Loader.isModLoaded(Reference.PATCHOULI)) {
//            IntegrationPatchouli.init();
//            GameRegistry.addShapelessRecipe(new ResourceLocation(Reference.MOD_ID + ":magnet_manual"), null, IntegrationPatchouli.magnet_manual,
//                    Ingredient.func_193369_a(new ItemStack(Items.BOOK)), Ingredient.func_193369_a(new ItemStack(Items.REDSTONE)), Ingredient.func_193369_a(new ItemStack(Items.IRON_INGOT))
//            );
//        }
//
//        // Vanilla
//        if (OldConfig.modules.vanillaModule) {
//            GameRegistry.addShapedRecipe(
//                    new ResourceLocation(Reference.MOD_ID + ":magnet_durability_stone"), null, new ItemStack(ModItems.ItemMagnetDurabilityStone),
//                    "MMM", "M M", "R R",
//                    'M', new ItemStack(Blocks.COBBLESTONE), 'R', new ItemStack(Items.REDSTONE)
//            );
//            GameRegistry.addShapedRecipe(
//                    new ResourceLocation(Reference.MOD_ID + ":magnet_durability_iron"), null, new ItemStack(ModItems.ItemMagnetDurabilityIron),
//                    "MMM", "M M", "R R",
//                    'M', new ItemStack(Items.IRON_INGOT), 'R', new ItemStack(Items.REDSTONE)
//            );
//            GameRegistry.addShapedRecipe(
//                    new ResourceLocation(Reference.MOD_ID + ":magnet_durability_redstone"), null, new ItemStack(ModItems.ItemMagnetDurabilityRedstone),
//                    "MIM", "I I", "R R",
//                    'M', new ItemStack(Items.REDSTONE), 'I', new ItemStack(Items.IRON_INGOT), 'R', new ItemStack(Items.REDSTONE)
//            );
//            GameRegistry.addShapedRecipe(
//                    new ResourceLocation(Reference.MOD_ID + ":magnet_durability_gold"), null, new ItemStack(ModItems.ItemMagnetDurabilityGold),
//                    "MIM", "I I", "R R",
//                    'M', new ItemStack(Items.GOLD_INGOT), 'I', new ItemStack(Items.IRON_INGOT), 'R', new ItemStack(Items.REDSTONE)
//            );
//            GameRegistry.addShapedRecipe(
//                    new ResourceLocation(Reference.MOD_ID + ":magnet_durability_obsidian"), null, new ItemStack(ModItems.ItemMagnetDurabilityObsidian),
//                    "MIM", "I I", "R R",
//                    'M', new ItemStack(Blocks.OBSIDIAN), 'I', new ItemStack(Items.IRON_INGOT), 'R', new ItemStack(Items.REDSTONE)
//            );
//            GameRegistry.addShapedRecipe(
//                    new ResourceLocation(Reference.MOD_ID + ":magnet_durability_lapis"), null, new ItemStack(ModItems.ItemMagnetDurabilityLapis),
//                    "MIM", "I I", "R R",
//                    'M', new ItemStack(Items.DYE, 1, 4), 'I', new ItemStack(Items.IRON_INGOT), 'R', new ItemStack(Items.REDSTONE)
//            );
//            GameRegistry.addShapedRecipe(
//                    new ResourceLocation(Reference.MOD_ID + ":magnet_durability_diamond"), null, new ItemStack(ModItems.ItemMagnetDurabilityDiamond),
//                    "MIM", "I I", "R R",
//                    'M', new ItemStack(Items.DIAMOND), 'I', new ItemStack(Items.IRON_INGOT), 'R', new ItemStack(Items.REDSTONE)
//            );
//            GameRegistry.addShapedRecipe(
//                    new ResourceLocation(Reference.MOD_ID + ":magnet_durability_emerald"), null, new ItemStack(ModItems.ItemMagnetDurabilityEmerald),
//                    "MIM", "I I", "R R",
//                    'M', new ItemStack(Items.EMERALD), 'I', new ItemStack(Items.IRON_INGOT), 'R', new ItemStack(Items.REDSTONE)
//            );
//
//        }
//
//        // Vanilla - Magic
//        if (OldConfig.modules.vanillaMagicModule) {
//            GameRegistry.addShapedRecipe(
//                    new ResourceLocation(Reference.MOD_ID + ":magnet_magic_durability_stone"), null, new ItemStack(ModItems.ItemMagnetMagicDurabilityStone),
//                    " E ", "GMG", " E ",
//                    'M', new ItemStack(ModItems.ItemMagnetDurabilityStone), 'E', new ItemStack(Items.ENDER_PEARL), 'G', new ItemStack(Items.GOLD_INGOT)
//            );
//            GameRegistry.addShapedRecipe(
//                    new ResourceLocation(Reference.MOD_ID + ":magnet_magic_durability_iron"), null, new ItemStack(ModItems.ItemMagnetMagicDurabilityIron),
//                    " E ", "GMG", " E ",
//                    'M', new ItemStack(ModItems.ItemMagnetDurabilityIron), 'E', new ItemStack(Items.ENDER_PEARL), 'G', new ItemStack(Items.GOLD_INGOT)
//            );
//            GameRegistry.addShapedRecipe(
//                    new ResourceLocation(Reference.MOD_ID + ":magnet_magic_durability_redstone"), null, new ItemStack(ModItems.ItemMagnetMagicDurabilityRedstone),
//                    " E ", "GMG", " E ",
//                    'M', new ItemStack(ModItems.ItemMagnetDurabilityRedstone), 'E', new ItemStack(Items.ENDER_PEARL), 'G', new ItemStack(Items.GOLD_INGOT)
//            );
//            GameRegistry.addShapedRecipe(
//                    new ResourceLocation(Reference.MOD_ID + ":magnet_magic_durability_gold"), null, new ItemStack(ModItems.ItemMagnetMagicDurabilityGold),
//                    " E ", "GMG", " E ",
//                    'M', new ItemStack(ModItems.ItemMagnetDurabilityGold), 'E', new ItemStack(Items.ENDER_PEARL), 'G', new ItemStack(Items.GOLD_INGOT)
//            );
//            GameRegistry.addShapedRecipe(
//                    new ResourceLocation(Reference.MOD_ID + ":magnet_magic_durability_obsidian"), null, new ItemStack(ModItems.ItemMagnetMagicDurabilityObsidian),
//                    " E ", "GMG", " E ",
//                    'M', new ItemStack(ModItems.ItemMagnetDurabilityObsidian), 'E', new ItemStack(Items.ENDER_PEARL), 'G', new ItemStack(Items.GOLD_INGOT)
//            );
//            GameRegistry.addShapedRecipe(
//                    new ResourceLocation(Reference.MOD_ID + ":magnet_magic_durability_lapis"), null, new ItemStack(ModItems.ItemMagnetMagicDurabilityLapis),
//                    " E ", "GMG", " E ",
//                    'M', new ItemStack(ModItems.ItemMagnetDurabilityLapis), 'E', new ItemStack(Items.ENDER_PEARL), 'G', new ItemStack(Items.GOLD_INGOT)
//            );
//            GameRegistry.addShapedRecipe(
//                    new ResourceLocation(Reference.MOD_ID + ":magnet_magic_durability_diamond"), null, new ItemStack(ModItems.ItemMagnetMagicDurabilityDiamond),
//                    " E ", "GMG", " E ",
//                    'M', new ItemStack(ModItems.ItemMagnetDurabilityDiamond), 'E', new ItemStack(Items.ENDER_PEARL), 'G', new ItemStack(Items.GOLD_INGOT)
//            );
//            GameRegistry.addShapedRecipe(
//                    new ResourceLocation(Reference.MOD_ID + ":magnet_magic_durability_emerald"), null, new ItemStack(ModItems.ItemMagnetMagicDurabilityEmerald),
//                    " E ", "GMG", " E ",
//                    'M', new ItemStack(ModItems.ItemMagnetDurabilityEmerald), 'E', new ItemStack(Items.ENDER_PEARL), 'G', new ItemStack(Items.GOLD_INGOT)
//            );
//        }
//
//        // Thermal Expansion
//        if (OldConfig.modules.thermalExpansionModule && Loader.isModLoaded(Reference.TE)) {
//            GameRegistry.addShapedRecipe(
//                new ResourceLocation(Reference.MOD_ID + ":magnet_energy_leadstone"), null, new ItemStack(ModItems.ItemMagnetElectromagnetLeadstone),
//                " G ", "C R", " G ",
//                'C', IntegrationThermalExpansion.fluxCapacitorBasic , 'R', IntegrationThermalExpansion.redstoneReceptionCoil, 'G', new ItemStack(Items.GOLD_NUGGET)
//            );
//            GameRegistry.addShapedRecipe(
//                    new ResourceLocation(Reference.MOD_ID + ":magnet_energy_hardened"), null, new ItemStack(ModItems.ItemMagnetElectromagnetHardened),
//                    " R ", "XMX", "RYR",
//                    'M', ModItems.ItemMagnetElectromagnetLeadstone , 'X', IntegrationThermalExpansion.ingotInvar, 'Y', IntegrationThermalExpansion.ingotTin, 'R', new ItemStack(Items.REDSTONE)
//            );
//            GameRegistry.addShapedRecipe(
//                    new ResourceLocation(Reference.MOD_ID + ":magnet_energy_reinforced"), null, new ItemStack(ModItems.ItemMagnetElectromagnetReinforced),
//                    " R ", "XMX", "RYR",
//                    'M', ModItems.ItemMagnetElectromagnetHardened , 'X', IntegrationThermalExpansion.ingotElectrum, 'Y', IntegrationThermalExpansion.hardenedGlass, 'R', new ItemStack(Items.REDSTONE)
//            );
//            GameRegistry.addShapedRecipe(
//                    new ResourceLocation(Reference.MOD_ID + ":magnet_energy_signalum"), null, new ItemStack(ModItems.ItemMagnetElectromagnetSignalum),
//                    " R ", "XMX", "RYR",
//                    'M', ModItems.ItemMagnetElectromagnetReinforced , 'X', IntegrationThermalExpansion.ingotSignalum, 'Y', IntegrationThermalExpansion.dustCryotheum, 'R', new ItemStack(Items.REDSTONE)
//            );
//            GameRegistry.addShapedRecipe(
//                    new ResourceLocation(Reference.MOD_ID + ":magnet_energy_resonant"), null, new ItemStack(ModItems.ItemMagnetElectromagnetResonant),
//                    " R ", "XMX", "RYR",
//                    'M', ModItems.ItemMagnetElectromagnetSignalum , 'X', IntegrationThermalExpansion.ingotEnderium, 'Y', IntegrationThermalExpansion.dustPyrotheum, 'R', new ItemStack(Items.REDSTONE)
//            );
//            GameRegistry.addShapedRecipe(
//                    new ResourceLocation(Reference.MOD_ID + ":magnet_energy_hardened2"), null, new ItemStack(ModItems.ItemMagnetElectromagnetHardened),
//                    " G ", "C R", " G ",
//                    'C', IntegrationThermalExpansion.fluxCapacitorHardened , 'R', IntegrationThermalExpansion.redstoneReceptionCoil, 'G', new ItemStack(Items.GOLD_NUGGET)
//            );
//            GameRegistry.addShapedRecipe(
//                    new ResourceLocation(Reference.MOD_ID + ":magnet_energy_reinforced2"), null, new ItemStack(ModItems.ItemMagnetElectromagnetReinforced),
//                    " G ", "C R", " G ",
//                    'C', IntegrationThermalExpansion.fluxCapacitorReinforced , 'R', IntegrationThermalExpansion.redstoneReceptionCoil, 'G', new ItemStack(Items.GOLD_NUGGET)
//            );
//            GameRegistry.addShapedRecipe(
//                    new ResourceLocation(Reference.MOD_ID + ":magnet_energy_signalum2"), null, new ItemStack(ModItems.ItemMagnetElectromagnetSignalum),
//                    " G ", "C R", " G ",
//                    'C', IntegrationThermalExpansion.fluxCapacitorSignalum , 'R', IntegrationThermalExpansion.redstoneReceptionCoil, 'G', new ItemStack(Items.GOLD_NUGGET)
//            );
//            GameRegistry.addShapedRecipe(
//                    new ResourceLocation(Reference.MOD_ID + ":magnet_energy_resonant2"), null, new ItemStack(ModItems.ItemMagnetElectromagnetResonant),
//                    " G ", "C R", " G ",
//                    'C', IntegrationThermalExpansion.fluxCapacitorResonant , 'R', IntegrationThermalExpansion.redstoneReceptionCoil, 'G', new ItemStack(Items.GOLD_NUGGET)
//            );
//        }
//
//        // Thermal Expansion - Magic
//        if (OldConfig.modules.thermalExpansionMagicModule && Loader.isModLoaded(Reference.TE)) {
//            new ShapedCopyNBTRecipe(
//                    e, new ResourceLocation(Reference.MOD_ID + ":magnet_magic_energy_leadstone"), new ItemStack(ModItems.ItemMagnetMagicElectromagnetLeadstone), new ItemStack(ModItems.ItemMagnetElectromagnetLeadstone),
//                    " E ", "GMG", " E ",
//                    'M', new ItemStack(ModItems.ItemMagnetElectromagnetLeadstone), 'E', new ItemStack(Items.ENDER_PEARL), 'G', new ItemStack(Items.GOLD_INGOT)
//            );
//            new ShapedCopyNBTRecipe(
//                    e, new ResourceLocation(Reference.MOD_ID + ":magnet_magic_energy_hardened"), new ItemStack(ModItems.ItemMagnetMagicElectromagnetHardened), new ItemStack(ModItems.ItemMagnetElectromagnetHardened),
//                    " E ", "GMG", " E ",
//                    'M', new ItemStack(ModItems.ItemMagnetElectromagnetHardened), 'E', new ItemStack(Items.ENDER_PEARL), 'G', new ItemStack(Items.GOLD_INGOT)
//            );
//            new ShapedCopyNBTRecipe(
//                    e, new ResourceLocation(Reference.MOD_ID + ":magnet_magic_energy_reinforced"), new ItemStack(ModItems.ItemMagnetMagicElectromagnetReinforced), new ItemStack(ModItems.ItemMagnetElectromagnetReinforced),
//                    " E ", "GMG", " E ",
//                    'M', new ItemStack(ModItems.ItemMagnetElectromagnetReinforced), 'E', new ItemStack(Items.ENDER_PEARL), 'G', new ItemStack(Items.GOLD_INGOT)
//            );
//            new ShapedCopyNBTRecipe(
//                    e, new ResourceLocation(Reference.MOD_ID + ":magnet_magic_energy_signalum"), new ItemStack(ModItems.ItemMagnetMagicElectromagnetSignalum), new ItemStack(ModItems.ItemMagnetElectromagnetSignalum),
//                    " E ", "GMG", " E ",
//                    'M', new ItemStack(ModItems.ItemMagnetElectromagnetSignalum), 'E', new ItemStack(Items.ENDER_PEARL), 'G', new ItemStack(Items.GOLD_INGOT)
//            );
//            new ShapedCopyNBTRecipe(
//                    e, new ResourceLocation(Reference.MOD_ID + ":magnet_magic_energy_resonant"), new ItemStack(ModItems.ItemMagnetMagicElectromagnetResonant), new ItemStack(ModItems.ItemMagnetElectromagnetResonant),
//                    " E ", "GMG", " E ",
//                    'M', new ItemStack(ModItems.ItemMagnetElectromagnetResonant), 'E', new ItemStack(Items.ENDER_PEARL), 'G', new ItemStack(Items.GOLD_INGOT)
//            );
//        }
//
//        if (OldConfig.modules.cursedMagnetsModule) {
//            //Free
//            if (OldConfig.cursedMagnetsConfigs.cursedMagnet) {
//                GameRegistry.addShapedRecipe(
//                        new ResourceLocation(Reference.MOD_ID + ":magnet_free"), null, new ItemStack(ModItems.ItemMagnetFree),
//                        "DGD", "ISI", "R R",
//                        'D', new ItemStack(Items.DIAMOND), 'G', new ItemStack(Items.GHAST_TEAR), 'I', new ItemStack(Items.IRON_INGOT), 'S', new ItemStack(Blocks.SOUL_SAND), 'R', new ItemStack(Items.REDSTONE)
//                );
//            }
//            //Magic Free
//            if (OldConfig.cursedMagnetsConfigs.magicCursedMagnet) {
//                GameRegistry.addShapedRecipe(
//                        new ResourceLocation(Reference.MOD_ID + ":magnet_magic_free"), null, new ItemStack(ModItems.ItemMagnetMagicFree),
//                        " E ", "GMG", " E ",
//                        'M', new ItemStack(ModItems.ItemMagnetFree), 'E', new ItemStack(Items.ENDER_PEARL), 'G', new ItemStack(Items.GOLD_INGOT)
//                );
//            }
//        }
//
//        if (OldConfig.modules.utilityBlocksModule) {
//            //Magnetic Insulator
//            if (OldConfig.utilityBlockConfigs.insulator) {
//                GameRegistry.addShapedRecipe(
//                        new ResourceLocation(Reference.MOD_ID + ":magnetic_insulator"), null, new ItemStack(ModBlocks.BlockMagneticInsulator),
//                        "EGE", " G ", "III",
//                        'E', new ItemStack(Items.ENDER_PEARL), 'G', new ItemStack(Items.GOLD_INGOT), 'I', new ItemStack(Items.IRON_INGOT)
//                );
//            }
//
//            //Magnetic Projector
//            if (OldConfig.utilityBlockConfigs.projector) {
//                GameRegistry.addShapedRecipe(
//                        new ResourceLocation(Reference.MOD_ID + ":magnetic_projector"), null, new ItemStack(ModBlocks.BlockMagneticProjector),
//                        "IPI", "GRG", "IHI",
//                        'G', new ItemStack(Items.GOLD_INGOT), 'H', new ItemStack(Blocks.HOPPER), 'I', new ItemStack(Items.IRON_INGOT), 'P', new ItemStack(Blocks.GLASS_PANE), 'R', new ItemStack(Items.REDSTONE)
//                );
//            }
//        }
//    }

//    private static class ShapedCopyNBTRecipe extends ShapedOreRecipe {
//
//        private final ItemStack nbtCopyStack;
//
//        private ShapedCopyNBTRecipe(RegistryEvent.Register<IRecipe> e, ResourceLocation group, @Nonnull ItemStack result, ItemStack nbtCopyStack, Object... recipe) {
//            super(group, result, recipe);
//            this.nbtCopyStack = nbtCopyStack;
//
//            e.getRegistry().register(this.setRegistryName(group));
//        }
//
//        @Nonnull
//        @Override
//        public ItemStack getCraftingResult(InventoryCrafting inventory) {
//            ItemStack stack = super.getCraftingResult(inventory);
//            if (!stack.isEmpty()) {
//                for (int i = 0; i < inventory.getSizeInventory(); i++) {
//                    ItemStack input = inventory.getStackInSlot(i);
//                    if (this.nbtCopyStack.isItemEqual(input)) {
//                        stack.setTagCompound(input.getTagCompound());
//                        break;
//                    }
//                }
//            }
//            return stack;
//        }
//
//    }

}
