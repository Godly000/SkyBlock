package net.skyblock.mod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

public class SkyGenMod implements ModInitializer {
  private static final Feature<DefaultFeatureConfig> SKYGEN = new SkyFeature(DefaultFeatureConfig.CODEC);

  @Override
  public void onInitialize() {
    Registry.register(Registry.FEATURE, new Identifier("godly", "sky_gen"), SKYGEN);
    BiomeModifications.create(new Identifier("godly", "sky_gen"))
            .add(ModificationPhase.ADDITIONS,
                    (context) -> true, // return true for all biomes
                    (context) -> context.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                            RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier("godly", "sky_gen"))));
  }
}