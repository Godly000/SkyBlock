package net.skyblock.mod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.*;
import net.minecraft.block.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.*;
import net.minecraft.util.registry.*;
import net.minecraft.world.*;
import net.minecraft.world.gen.*;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.*;

public class SkyGenMod implements ModInitializer {
  private static final Feature<SkyGenConfig> SKYGEN = new SkyFeature(SkyGenConfig.CODEC);
  @Override
  public void onInitialize() {
    Registry.register(Registry.FEATURE, new Identifier("godly", "sky_gen"), SKYGEN);
    BiomeModifications.create(new Identifier("godly", "sky_gen"))
            .add(ModificationPhase.ADDITIONS,
                    (context) -> true, // return true for all biomes
                    (context) -> context.getGenerationSettings().addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION,
                            RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier("godly", "sky_gen"))));
  }
}