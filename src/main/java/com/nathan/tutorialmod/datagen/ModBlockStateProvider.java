package com.nathan.tutorialmod.datagen;

import com.nathan.tutorialmod.TutorialMod;
import com.nathan.tutorialmod.block.ModBlocks;
import com.nathan.tutorialmod.block.custom.FlashBangLampBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
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

        customLamp();
    }

    private void customLamp() {
        getVariantBuilder(ModBlocks.FLASHBANG_LAMP.get()).forAllStates(state -> {
            if(state.getValue(FlashBangLampBlock.CLICKED)) {
                return new ConfiguredModel[]{new ConfiguredModel(models().cubeAll("flashbang_lamp_on",
                        ResourceLocation.fromNamespaceAndPath(TutorialMod.MOD_ID, "block/" + "flashbang_lamp_on")))};
            } else {
                return new ConfiguredModel[]{new ConfiguredModel(models().cubeAll("flashbang_lamp_off",
                        ResourceLocation.fromNamespaceAndPath(TutorialMod.MOD_ID, "block/" + "flashbang_lamp_off")))};
            }
        });
        simpleBlockItem(ModBlocks.FLASHBANG_LAMP.get(), models().cubeAll("flashbang_lamp_on",
                ResourceLocation.fromNamespaceAndPath(TutorialMod.MOD_ID, "block/" + "flashbang_lamp_on")));
    }


    private void blockWithItem(RegistryObject<Block> blockRegistryObject){
        simpleBlockItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
