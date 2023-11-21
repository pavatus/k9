package mdt.k9mod.datagen;

import mdt.k9mod.block.BlockInit;
import mdt.k9mod.datagen.datagen_providers.K9ModLanguageProvider;
import mdt.k9mod.datagen.datagen_providers.K9ModModelProvider;
import mdt.k9mod.datagen.datagen_providers.K9ModRecipeProvider;
import mdt.k9mod.datagen.datagen_providers.K9ModSoundProvider;
import mdt.k9mod.item.ItemGroupInit;
import mdt.k9mod.item.ItemInit;
import mdt.k9mod.sounds.SoundsInit;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class K9ModDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        generateRecipes(pack);
        generateLanguages(pack);
        generateBlockModels(pack);
        generateSoundData(pack);
    }

    public void generateSoundData(FabricDataGenerator.Pack pack) {
        pack.addProvider((((output, registriesFuture) -> {
            K9ModSoundProvider k9ModSoundProvider = new K9ModSoundProvider(output);
            k9ModSoundProvider.addSound("k9_defense", SoundsInit.K9_DEFENSE);
            k9ModSoundProvider.addSound("k9_die", SoundsInit.K9_DIE);
            k9ModSoundProvider.addSound("k9_hurt", SoundsInit.K9_HURT);
            k9ModSoundProvider.addSound("k9_master", SoundsInit.K9_MASTER);
            k9ModSoundProvider.addSound("k9_restart", SoundsInit.K9_RESTART);
            k9ModSoundProvider.addSound("k9_run", SoundsInit.K9_RUN);
            return k9ModSoundProvider;
        })));
    }

    public void generateBlockModels(FabricDataGenerator.Pack pack) {
        pack.addProvider(((output, registriesFuture) -> {
            K9ModModelProvider k9ModModelProvider = new K9ModModelProvider(output);
            k9ModModelProvider.registerSimpleBlock(BlockInit.K9_CRATE);
            //k9ModModelProvider.registerDirectionalBlock(BlockInit.SUM_DIRECTIONAL_BLOCK);
            return k9ModModelProvider;
        }));
    }

    public void generateRecipes(FabricDataGenerator.Pack pack) {
        pack.addProvider(((output, registriesFuture) -> {
            K9ModRecipeProvider k9ModRecipeProvider = new K9ModRecipeProvider(output);
            k9ModRecipeProvider.addShapedRecipe(ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ItemInit.K9_WRENCH, 1)
                    .pattern(" R ")
                    .pattern("IAR")
                    .pattern("TI ")
                    .input('R', Items.REPEATER)
                    .input('I', Items.IRON_INGOT)
                    .input('A', Items.IRON_AXE)
                    .input('T', Items.REDSTONE_TORCH)
                    .criterion(FabricRecipeProvider.hasItem(Items.REPEATER),
                            FabricRecipeProvider.conditionsFromItem(Items.REPEATER))
                    .criterion(FabricRecipeProvider.hasItem(Items.IRON_INGOT),
                            FabricRecipeProvider.conditionsFromItem(Items.IRON_INGOT))
                    .criterion(FabricRecipeProvider.hasItem(Items.IRON_AXE),
                            FabricRecipeProvider.conditionsFromItem(Items.IRON_AXE))
                    .criterion(FabricRecipeProvider.hasItem(Items.REDSTONE_TORCH),
                            FabricRecipeProvider.conditionsFromItem(Items.REDSTONE_TORCH))
            );
            k9ModRecipeProvider.addShapedRecipe(ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, BlockInit.K9_CRATE, 1)
                    .pattern("SSS")
                    .pattern("LWL")
                    .pattern("SMS")
                    .input('S', Items.STRIPPED_OAK_WOOD)
                    .input('L', Items.OAK_PLANKS)
                    .input('W', ItemInit.K9_WRENCH)
                    .input('M', Items.MINECART)
                    .criterion(FabricRecipeProvider.hasItem(Items.STRIPPED_OAK_WOOD),
                            FabricRecipeProvider.conditionsFromItem(Items.STRIPPED_OAK_WOOD))
                    .criterion(FabricRecipeProvider.hasItem(Items.OAK_PLANKS),
                            FabricRecipeProvider.conditionsFromItem(Items.OAK_PLANKS))
                    .criterion(FabricRecipeProvider.hasItem(ItemInit.K9_WRENCH),
                            FabricRecipeProvider.conditionsFromItem(ItemInit.K9_WRENCH))
                    .criterion(FabricRecipeProvider.hasItem(Items.MINECART),
                            FabricRecipeProvider.conditionsFromItem(Items.MINECART))
            );
            k9ModRecipeProvider.addShapedRecipe(ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ItemInit.K9_BONE, 1)
                    .pattern("III")
                    .pattern("IBI")
                    .pattern("III")
                    .input('I', Items.IRON_INGOT)
                    .input('B', Items.BONE)
                    .criterion(FabricRecipeProvider.hasItem(Items.IRON_INGOT),
                            FabricRecipeProvider.conditionsFromItem(Items.IRON_INGOT))
                    .criterion(FabricRecipeProvider.hasItem(Items.BONE),
                            FabricRecipeProvider.conditionsFromItem(Items.BONE))
            );
            k9ModRecipeProvider.addShapedRecipe(ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ItemInit.K9_LITHIUM_CELL, 1)
                    .pattern("IAI")
                    .pattern("OCO")
                    .pattern("IAI")
                    .input('I', Items.IRON_INGOT)
                    .input('O', Items.OBSIDIAN)
                    .input('A', Items.COAL)
                    .input('C', Items.COPPER_INGOT)
                    .criterion(FabricRecipeProvider.hasItem(Items.IRON_INGOT),
                            FabricRecipeProvider.conditionsFromItem(Items.IRON_INGOT))
                    .criterion(FabricRecipeProvider.hasItem(Items.OBSIDIAN),
                            FabricRecipeProvider.conditionsFromItem(Items.OBSIDIAN))
                    .criterion(FabricRecipeProvider.hasItem(Items.COAL),
                            FabricRecipeProvider.conditionsFromItem(Items.COAL))
                    .criterion(FabricRecipeProvider.hasItem(Items.COPPER_INGOT),
                            FabricRecipeProvider.conditionsFromItem(Items.COPPER_INGOT))
            );
            return k9ModRecipeProvider;
        }));
    }

    public void generateLanguages(FabricDataGenerator.Pack pack) {
        generate_EN_US_Language(pack); // en_us (English US)
        generate_EN_UK_Language(pack); // en_uk (English UK)
        generate_FR_CA_Language(pack); // fr_ca (French Canadian)
        generate_FR_FR_Language(pack); // fr_fr (French France)
        generate_ES_AR_Language(pack); // es_ar (Spanish Argentina)
        generate_ES_CL_Language(pack); // es_cl (Spanish Chile)
        generate_ES_EC_Language(pack); // es_ec (Spanish Ecuador)
        generate_ES_ES_Language(pack); // es_es (Spanish Spain)
        generate_ES_MX_Language(pack); // es_mx (Spanish Mexico)
        generate_ES_UY_Language(pack); // es_uy (Spanish Uruguay)
        generate_ES_VE_Language(pack); // es_ve (Spanish Venezuela)
        generate_EN_AU_Language(pack); // en_au (English Australia)
        generate_EN_CA_Language(pack); // en_ca (English Canada)
        generate_EN_GB_Language(pack); // en_gb (English Great Britain)
        generate_EN_NZ_Language(pack); // en_nz (English New Zealand)
    }

    /**
     * Adds English translations to the language file.
     *
     * @param output           The data generator output.
     * @param registriesFuture The registries future.
     * @param languageType     The language type.
     * @return The FalloutLanguageProvider.
     */
    public K9ModLanguageProvider addEnglishTranslations(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture, LanguageType languageType) {
        K9ModLanguageProvider k9ModLanguageProvider = new K9ModLanguageProvider(output, languageType);
        k9ModLanguageProvider.addTranslation(ItemInit.K9_WRENCH, "Wrench");
        k9ModLanguageProvider.addTranslation(ItemInit.K9_BONE, "Bone");
        k9ModLanguageProvider.addTranslation(ItemInit.K9_LITHIUM_CELL, "Lithium Cell");
        k9ModLanguageProvider.addTranslation(ItemGroupInit.K9_GROUP, "K9 Mod");
        k9ModLanguageProvider.addTranslation(BlockInit.K9_CRATE, "K9 Crate");

        return k9ModLanguageProvider;
    }

    /**
     * Adds French translations to the language file.
     *
     * @param output           The data generator output.
     * @param registriesFuture The registries future.
     * @param languageType     The language type.
     * @return The FalloutLanguageProvider.
     */
    public K9ModLanguageProvider addFrenchTranslations(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture, LanguageType languageType) {
        K9ModLanguageProvider k9ModLanguageProvider = new K9ModLanguageProvider(output, languageType);
        k9ModLanguageProvider.addTranslation(ItemInit.K9_WRENCH, "Le Wrench");
        k9ModLanguageProvider.addTranslation(ItemInit.K9_BONE, "Le Bone");
        k9ModLanguageProvider.addTranslation(ItemInit.K9_LITHIUM_CELL, "Le Lithium Cell");
        k9ModLanguageProvider.addTranslation(ItemGroupInit.K9_GROUP, "Le K9 Mod");
        k9ModLanguageProvider.addTranslation(BlockInit.K9_CRATE, "Le K9 Crate");

        return k9ModLanguageProvider;
    }

    /**
     * Adds Spanish translations to the language file.
     *
     * @param output           The data generator output.
     * @param registriesFuture The registries future.
     * @param languageType     The language type.
     * @return The FalloutLanguageProvider.
     */
    public K9ModLanguageProvider addSpanishTranslations(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture, LanguageType languageType) {
        K9ModLanguageProvider k9ModLanguageProvider = new K9ModLanguageProvider(output, languageType);
        k9ModLanguageProvider.addTranslation(ItemInit.K9_WRENCH, "Wrenche");
        k9ModLanguageProvider.addTranslation(ItemInit.K9_BONE, "Boene");
        k9ModLanguageProvider.addTranslation(ItemInit.K9_LITHIUM_CELL, "Power Celle");
        k9ModLanguageProvider.addTranslation(ItemGroupInit.K9_GROUP, "K9 Moede");
        k9ModLanguageProvider.addTranslation(BlockInit.K9_CRATE, "K9 Boxe");

        return k9ModLanguageProvider;
    }

    public void generate_EN_US_Language(FabricDataGenerator.Pack pack) {
        pack.addProvider(((output, registriesFuture) -> addEnglishTranslations(output, registriesFuture, LanguageType.EN_US))); // en_us (English US)
    }

    public void generate_EN_UK_Language(FabricDataGenerator.Pack pack) {
        pack.addProvider(((output, registriesFuture) -> addEnglishTranslations(output, registriesFuture, LanguageType.EN_UK))); // en_uk (English UK)
    }

    public void generate_FR_CA_Language(FabricDataGenerator.Pack pack) {
        pack.addProvider((((output, registriesFuture) -> addFrenchTranslations(output, registriesFuture, LanguageType.FR_CA)))); // fr_ca (French Canadian)
    }

    public void generate_FR_FR_Language(FabricDataGenerator.Pack pack) {
        pack.addProvider((((output, registriesFuture) -> addFrenchTranslations(output, registriesFuture, LanguageType.FR_FR)))); // fr_fr (French France)
    }

    public void generate_ES_AR_Language(FabricDataGenerator.Pack pack) {
        pack.addProvider((((output, registriesFuture) -> addSpanishTranslations(output, registriesFuture, LanguageType.ES_AR)))); // es_ar (Spanish Argentina)
    }

    public void generate_ES_CL_Language(FabricDataGenerator.Pack pack) {
        pack.addProvider((((output, registriesFuture) -> addSpanishTranslations(output, registriesFuture, LanguageType.ES_CL)))); // es_cl (Spanish Chile)
    }

    public void generate_ES_EC_Language(FabricDataGenerator.Pack pack) {
        pack.addProvider((((output, registriesFuture) -> addSpanishTranslations(output, registriesFuture, LanguageType.ES_EC)))); // es_ec (Spanish Ecuador)
    }

    public void generate_ES_ES_Language(FabricDataGenerator.Pack pack) {
        pack.addProvider((((output, registriesFuture) -> addSpanishTranslations(output, registriesFuture, LanguageType.ES_ES)))); // es_es (Spanish Spain)
    }

    public void generate_ES_MX_Language(FabricDataGenerator.Pack pack) {
        pack.addProvider((((output, registriesFuture) -> addSpanishTranslations(output, registriesFuture, LanguageType.ES_MX)))); // es_mx (Spanish Mexico)
    }

    public void generate_ES_UY_Language(FabricDataGenerator.Pack pack) {
        pack.addProvider((((output, registriesFuture) -> addSpanishTranslations(output, registriesFuture, LanguageType.ES_UY)))); // es_uy (Spanish Uruguay)
    }

    public void generate_ES_VE_Language(FabricDataGenerator.Pack pack) {
        pack.addProvider((((output, registriesFuture) -> addSpanishTranslations(output, registriesFuture, LanguageType.ES_VE)))); // es_ve (Spanish Venezuela)
    }

    public void generate_EN_AU_Language(FabricDataGenerator.Pack pack) {
        pack.addProvider(((output, registriesFuture) -> addEnglishTranslations(output, registriesFuture, LanguageType.EN_AU))); // en_au (English Australia)
    }

    public void generate_EN_CA_Language(FabricDataGenerator.Pack pack) {
        pack.addProvider(((output, registriesFuture) -> addEnglishTranslations(output, registriesFuture, LanguageType.EN_CA))); // en_ca (English Canada)
    }

    public void generate_EN_GB_Language(FabricDataGenerator.Pack pack) {
        pack.addProvider(((output, registriesFuture) -> addEnglishTranslations(output, registriesFuture, LanguageType.EN_GB))); // en_gb (English Great Britain)
    }

    public void generate_EN_NZ_Language(FabricDataGenerator.Pack pack) {
        pack.addProvider(((output, registriesFuture) -> addEnglishTranslations(output, registriesFuture, LanguageType.EN_NZ))); // en_nz (English New Zealand)
    }
}