package net.skyblock.mod;

import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.skyblock.mod.mixin.ProtoChunkAccessor;

public class SkyFeature extends Feature<DefaultFeatureConfig> {
    public SkyFeature(Codec<DefaultFeatureConfig> configCodec) {
        super(configCodec);
    }

    private static final TagKey<Block> BLOCKS_TO_KEEP_TAG = TagKey.of(Registry.BLOCK_KEY, new Identifier("godly", "blocks_to_keep"));

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        BlockPos originalPosition = context.getOrigin();
        StructureWorldAccess world = context.getWorld();
        Chunk chunk = world.getChunk(originalPosition);
        int chunkHeight = chunk.getTopY() - chunk.getBottomY() - 1;
        BlockPos.Mutable reusableBlockPos = new BlockPos.Mutable();
        BlockState replacementBlock = Blocks.AIR.getDefaultState();

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                for (int y = 0; y < chunkHeight; y++) {
                    reusableBlockPos.set(originalPosition).move(x, y, z);
                    BlockState currentState = chunk.getBlockState(reusableBlockPos);

                    if (!currentState.isIn(BLOCKS_TO_KEEP_TAG)) {
                         chunk.setBlockState(reusableBlockPos, replacementBlock, false);
                         ((ProtoChunkAccessor)chunk).getLightSources().remove(reusableBlockPos);
                    }
                }
            }
        }

        ((ProtoChunkAccessor)chunk).getEntities().clear();
        return true;
    }
}
