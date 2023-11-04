package mdt.falloutmod.datagen;

import mdt.falloutmod.FalloutMod;
import mdt.falloutmod.core.FMCBlocks;
import mdt.falloutmod.core.FMCItems;
import mdt.falloutmod.core.FMCSounds;
import mdt.falloutmod.datagen.datagen_providers.FalloutLanguageProvider;
import mdt.falloutmod.datagen.datagen_providers.FalloutModelProvider;
import mdt.falloutmod.datagen.datagen_providers.FalloutSoundProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class FalloutModDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		generateLanguages(pack);
		generateBlockModels(pack);
		generateSoundData(pack);
	}

	public void generateSoundData(FabricDataGenerator.Pack pack) {
		pack.addProvider((((output, registriesFuture) ->  {
			FalloutSoundProvider falloutSoundProvider = new FalloutSoundProvider(output);
			falloutSoundProvider.addSound("stimpak_use", FMCSounds.STIMPAK_USE);
			falloutSoundProvider.addSound("dematerialize", FMCSounds.DEMATERIALIZE);
			falloutSoundProvider.addSound("title_music", FMCSounds.TITLE_MUSIC);
			falloutSoundProvider.addSound("blast_door_open", FMCSounds.BLAST_DOOR_OPEN);
			return falloutSoundProvider;
		})));
	}

	public void generateBlockModels(FabricDataGenerator.Pack pack) {
		pack.addProvider(((output, registriesFuture) -> {
			FalloutModelProvider falloutModelProvider = new FalloutModelProvider(output);
			falloutModelProvider.registerDirectionalBlock(FMCBlocks.RADIO);
			falloutModelProvider.registerDirectionalBlock(FMCBlocks.STAND);
			falloutModelProvider.registerDirectionalBlock(FMCBlocks.TYPEWRITER);

			falloutModelProvider.registerSimpleBlock(FMCBlocks.DEEPSLATE_URANIUM_ORE);
			falloutModelProvider.registerSimpleBlock(FMCBlocks.URANIUM_ORE);
			return falloutModelProvider;
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
	 * @param output The data generator output.
	 * @param registriesFuture The registries future.
	 * @param languageType The language type.
	 * @return The FalloutLanguageProvider.
	 */
	public FalloutLanguageProvider addEnglishTranslations(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture, LanguageType languageType) {
		FalloutLanguageProvider falloutLanguageProvider = new FalloutLanguageProvider(output, languageType);
		falloutLanguageProvider.addTranslation(FMCItems.URANIUM_CHUNK, "Uranium Chunk");
		falloutLanguageProvider.addTranslation(FMCItems.STIMPAK, "Stimpak");
		falloutLanguageProvider.addTranslation(FMCItems.CAPS, "Caps");
		falloutLanguageProvider.addTranslation(FalloutMod.FALLOUT_ITEM_GROUP, "Fallout Mod");
		falloutLanguageProvider.addTranslation(FMCItems.HOLOTAPE, "Holotape");
		falloutLanguageProvider.addTranslation(FMCItems.PIP_BOY, "Pip Boy");
		falloutLanguageProvider.addTranslation(FMCBlocks.RADIO, "Radio");
		falloutLanguageProvider.addTranslation(FMCBlocks.STAND, "Stand");
		falloutLanguageProvider.addTranslation(FMCBlocks.TYPEWRITER, "Typewriter");
		falloutLanguageProvider.addTranslation(FMCBlocks.URANIUM_ORE, "Uranium Ore");
		falloutLanguageProvider.addTranslation(FMCBlocks.DEEPSLATE_URANIUM_ORE, "Deepslate Uranium Ore");

		return falloutLanguageProvider;
	}

	/**
	 * Adds French translations to the language file.
	 * @param output The data generator output.
	 * @param registriesFuture The registries future.
	 * @param languageType The language type.
	 * @return The FalloutLanguageProvider.
	 */
	public FalloutLanguageProvider addFrenchTranslations(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture, LanguageType languageType) {
		FalloutLanguageProvider falloutLanguageProvider = new FalloutLanguageProvider(output, languageType);
		falloutLanguageProvider.addTranslation(FMCItems.URANIUM_CHUNK, "Morceau d'uranium");
		falloutLanguageProvider.addTranslation(FMCItems.STIMPAK, "Stimpak");
		falloutLanguageProvider.addTranslation(FMCItems.CAPS, "Casquettes");
		falloutLanguageProvider.addTranslation(FalloutMod.FALLOUT_ITEM_GROUP, "Mod de retombees");
		falloutLanguageProvider.addTranslation(FMCItems.HOLOTAPE, "Holobande");
		falloutLanguageProvider.addTranslation(FMCItems.PIP_BOY, "Pip Garcon");
		falloutLanguageProvider.addTranslation(FMCBlocks.RADIO, "Radio");
		falloutLanguageProvider.addTranslation(FMCBlocks.STAND, "la plate-forme");
		falloutLanguageProvider.addTranslation(FMCBlocks.TYPEWRITER, "machine a ecrire");
		falloutLanguageProvider.addTranslation(FMCBlocks.URANIUM_ORE, "Minerai d'uranium");
		falloutLanguageProvider.addTranslation(FMCBlocks.DEEPSLATE_URANIUM_ORE, "Minerai d'uranium de Deepslate");


		return falloutLanguageProvider;
	}

	/**
	 * Adds Spanish translations to the language file.
	 * @param output The data generator output.
	 * @param registriesFuture The registries future.
	 * @param languageType The language type.
	 * @return The FalloutLanguageProvider.
	 */
	public FalloutLanguageProvider addSpanishTranslations(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture, LanguageType languageType) {
		FalloutLanguageProvider falloutLanguageProvider = new FalloutLanguageProvider(output, languageType);
		falloutLanguageProvider.addTranslation(FMCItems.URANIUM_CHUNK, "Trozo de uranio");
		falloutLanguageProvider.addTranslation(FMCItems.STIMPAK, "Stimpak");
		falloutLanguageProvider.addTranslation(FMCItems.CAPS, "Tapas");
		falloutLanguageProvider.addTranslation(FalloutMod.FALLOUT_ITEM_GROUP, "Mod de caida");
		falloutLanguageProvider.addTranslation(FMCItems.HOLOTAPE, "Holocinta");
		falloutLanguageProvider.addTranslation(FMCItems.PIP_BOY, "Pip Chico");
		falloutLanguageProvider.addTranslation(FMCBlocks.RADIO, "Radio");
		falloutLanguageProvider.addTranslation(FMCBlocks.STAND, "estar de pie");
		falloutLanguageProvider.addTranslation(FMCBlocks.TYPEWRITER, "maquina de escribir");
		falloutLanguageProvider.addTranslation(FMCBlocks.URANIUM_ORE, "mineral de uranio");
		falloutLanguageProvider.addTranslation(FMCBlocks.DEEPSLATE_URANIUM_ORE, "mineral de uranio de Deepslate");


		return falloutLanguageProvider;
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
