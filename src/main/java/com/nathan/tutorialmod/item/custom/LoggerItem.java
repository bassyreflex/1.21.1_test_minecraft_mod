package com.nathan.tutorialmod.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

import java.util.*;

public class LoggerItem extends DiggerItem {
    public LoggerItem(Tier pTier, Properties pProperties) {
        super(pTier, BlockTags.MINEABLE_WITH_AXE, pProperties);
    }

    public static List<BlockPos> findTreeBlocks(Level level, BlockPos startPos) {
        List<BlockPos> treeBlocks = new ArrayList<>();
        Set<BlockPos> visited = new HashSet<>();
        Queue<BlockPos> toVisit = new LinkedList<>();

        // Add starting position
        toVisit.add(startPos);

        // Directions for adjacency (6 directions for cubic blocks)
        BlockPos[] directions = new BlockPos[]{
                new BlockPos(1, 0, 0), new BlockPos(-1, 0, 0),
                new BlockPos(0, 1, 0), new BlockPos(0, -1, 0),
                new BlockPos(0, 0, 1), new BlockPos(0, 0, -1)
        };

        // BFS loop
        while (!toVisit.isEmpty()) {
            BlockPos currentPos = toVisit.poll();
            if (!visited.contains(currentPos)) {
                visited.add(currentPos);

                // Check if the block at the current position is wood
                BlockState state = level.getBlockState(currentPos);
                if (isWood(state)) {
                    treeBlocks.add(currentPos);

                    // Add all adjacent blocks to the queue
                    for (BlockPos direction : directions) {
                        BlockPos neighbor = currentPos.offset(direction);
                        if (!visited.contains(neighbor)) {
                            toVisit.add(neighbor);
                        }
                    }
                }
            }
        }
        return treeBlocks;
    }

    private static boolean isWood(BlockState state) {
        // Check if the block is a wood block
        return state.getBlock().defaultBlockState().is(Blocks.OAK_LOG) || // Replace/add other wood blocks here
                state.getBlock().defaultBlockState().is(Blocks.SPRUCE_LOG) ||
                state.getBlock().defaultBlockState().is(Blocks.BIRCH_LOG) ||
                state.getBlock().defaultBlockState().is(Blocks.JUNGLE_LOG) ||
                state.getBlock().defaultBlockState().is(Blocks.ACACIA_LOG) ||
                state.getBlock().defaultBlockState().is(Blocks.DARK_OAK_LOG);
    }
}



