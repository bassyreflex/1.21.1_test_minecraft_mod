package com.nathan.tutorialmod.datagen;

import com.nathan.tutorialmod.TutorialMod;
import com.nathan.tutorialmod.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, TutorialMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.SUPERITE_BLOCK);
        blockWithItem(ModBlocks.RAW_SUPERITE_BLOCK);

        blockWithItem(ModBlocks.SUPERITE_ORE);
        blockWithItem(ModBlocks.SUPERITE_DEEPSLATE_ORE);

        blockWithItem(ModBlocks.MAGIC_BLOCK);
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject){
        simpleBlockItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
