package com.nathan.tutorialmod.util;

import com.nathan.tutorialmod.TutorialMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public class Blocks {
        public static final TagKey<Block> NEEDS_SUPERITE_TOOL = createTag("needs_superite_tool");
        public static final TagKey<Block> INCORRECT_FOR_SUPERITE_TOOL = createTag("incorrect_for_superite_tool");

        private static net.minecraft.tags.TagKey<Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(TutorialMod.MOD_ID, name));
        }

        public class Items {

        }
    }
}
