package com.nathan.tutorialmod.block;

import com.nathan.tutorialmod.TutorialMod;
import com.nathan.tutorialmod.block.custom.FlashBangLampBlock;
import com.nathan.tutorialmod.block.custom.MagicBlock;
import com.nathan.tutorialmod.item.ModItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, TutorialMod.MOD_ID);

    public static final  RegistryObject<Block> SUPERITE_BLOCK = registerBlock("superite_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.AMETHYST)));

    public static final  RegistryObject<Block> RAW_SUPERITE_BLOCK = registerBlock("raw_superite_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3F)
                    .requiresCorrectToolForDrops()));

    public static final  RegistryObject<Block> SUPERITE_ORE = registerBlock("superite_ore",
            () -> new DropExperienceBlock(UniformInt.of(2 ,4), BlockBehaviour.Properties.of()
                    .strength(4F)
                    .requiresCorrectToolForDrops()));

    public static final  RegistryObject<Block> SUPERITE_DEEPSLATE_ORE = registerBlock("superite_deepslate_ore",
            () -> new DropExperienceBlock(UniformInt.of(3 ,6), BlockBehaviour.Properties.of()
                    .strength(5F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE)));

    public static final  RegistryObject<Block> MAGIC_BLOCK = registerBlock("magic_block",
            () -> new MagicBlock(BlockBehaviour.Properties.of()
                    .strength(10F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.AMETHYST_CLUSTER)));

    public static final RegistryObject<Block> FLASHBANG_LAMP = registerBlock("flashbang_lamp",
            () -> new FlashBangLampBlock(BlockBehaviour.Properties.of().strength(4f)
                    .lightLevel(state -> state.getValue(FlashBangLampBlock.CLICKED) ? 30 : 0)));




    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name,block);
        registerBlockItem(name,toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block){
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }

}
