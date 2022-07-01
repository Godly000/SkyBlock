package net.skyblock.mod;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public record SkyGenConfig(IntProvider height, BlockStateProvider block) implements FeatureConfig {
    public static final Codec<SkyGenConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            IntProvider.VALUE_CODEC.fieldOf("height").forGetter(SkyGenConfig::height),
            BlockStateProvider.TYPE_CODEC.fieldOf("block").forGetter(SkyGenConfig::block)
    ).apply(instance, instance.stable(SkyGenConfig::new)));
}