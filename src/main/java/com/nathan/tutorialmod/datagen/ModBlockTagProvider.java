package com.nathan.tutorialmod.datagen;

import com.nathan.tutorialmod.TutorialMod;
import com.nathan.tutorialmod.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, TutorialMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.SUPERITE_BLOCK.get())
                .add(ModBlocks.RAW_SUPERITE_BLOCK.get())
                .add(ModBlocks.SUPERITE_ORE.get())
                .add(ModBlocks.SUPERITE_DEEPSLATE_ORE.get())
                .add(ModBlocks.MAGIC_BLOCK.get());

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.SUPERITE_ORE.get())
                .add(ModBlocks.SUPERITE_DEEPSLATE_ORE.get());
    }
}
