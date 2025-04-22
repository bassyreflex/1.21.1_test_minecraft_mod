package com.nathan.tutorialmod.datagen;

import com.nathan.tutorialmod.TutorialMod;
import com.nathan.tutorialmod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, TutorialMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.SUPERITE.get());
        basicItem(ModItems.RAW_SUPERITE.get());
        basicItem(ModItems.CHISEL.get());
        basicItem(ModItems.KOHLRABI.get());
        basicItem(ModItems.AURORA_ASHES.get());

    }
}
