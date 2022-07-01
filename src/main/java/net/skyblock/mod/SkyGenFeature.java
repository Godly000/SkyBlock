package net.skyblock.mod;

import com.mojang.serialization.Codec;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class SkyGenFeature extends Feature<DefaultFeatureConfig> {
    public SkyGenFeature(Codec<DefaultFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        BlockPos topPos = context.getWorld().getTopPosition(Heightmap.Type.OCEAN_FLOOR_WG, context.getOrigin());
        Direction offset = Direction.NORTH;

        for (int y = 0; y < 15; y++) {
            offset = offset.rotateYClockwise();
            context.getWorld().setBlockState(topPos.up(y).offset(offset), Blocks.AIR.getDefaultState(), 3);
        }

        return true;
    }
}