package com.nathan.tutorialmod.item;

import com.nathan.tutorialmod.TutorialMod;
import com.nathan.tutorialmod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TutorialMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> SUPERITE_ITEMS_TAB = CREATIVE_MODE_TABS.register("superite_items_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.SUPERITE.get()))
                    .title(Component.translatable("creativetab.tutorialmod.superite_items"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.SUPERITE.get());
                        output.accept(ModItems.RAW_SUPERITE.get());
                        output.accept(ModItems.CHISEL.get());
                        output.accept(ModItems.KOHLRABI.get());
                        output.accept(ModItems.AURORA_ASHES.get());
                        output.accept(ModItems.TNT_LAUNCHER.get());
                        output.accept(ModItems.SUPERITE_SWORD.get());

                    }).build());

    public static final RegistryObject<CreativeModeTab> SUPERITE_BLOCKS_TAB = CREATIVE_MODE_TABS.register("superite_blocks_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModBlocks.SUPERITE_BLOCK.get()))
                    .withTabsBefore(SUPERITE_ITEMS_TAB.getId())
                    .title(Component.translatable("creativetab.tutorialmod.superite_blocks"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModBlocks.SUPERITE_BLOCK.get());
                        output.accept(ModBlocks.RAW_SUPERITE_BLOCK.get());

                        output.accept(ModBlocks.SUPERITE_ORE.get());
                        output.accept(ModBlocks.SUPERITE_DEEPSLATE_ORE.get());

                        output.accept(ModBlocks.MAGIC_BLOCK.get());
                        output.accept(ModBlocks.FLASHBANG_LAMP.get());

                        output.accept(ModItems.LOGGER_AXE.get());

                    }).build());

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }

}
