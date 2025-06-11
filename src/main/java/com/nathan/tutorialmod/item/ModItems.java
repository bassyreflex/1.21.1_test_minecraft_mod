package com.nathan.tutorialmod.item;

import com.nathan.tutorialmod.TutorialMod;
import com.nathan.tutorialmod.block.custom.FuelItem;
import com.nathan.tutorialmod.item.custom.ChiselItem;
import com.nathan.tutorialmod.item.custom.ModToolTiers;
import com.nathan.tutorialmod.item.custom.TntLauncherItem;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.component.ChargedProjectiles;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, TutorialMod.MOD_ID);

    public static final RegistryObject<Item> SUPERITE = ITEMS.register("superite",
            () -> new Item(new Item.Properties().food(ModFoodProperties.SUPERITE)));
    public static final RegistryObject<Item> RAW_SUPERITE = ITEMS.register("raw_superite",
            () -> new Item(new Item.Properties().food(ModFoodProperties.SUPERITE)));

    public static final RegistryObject<Item> CHISEL = ITEMS.register("chisel",
            () -> new ChiselItem(new Item.Properties().durability(32)));

    public static final RegistryObject<Item> KOHLRABI = ITEMS.register("kohlrabi",
            () -> new Item(new Item.Properties().food(ModFoodProperties.KOHLRABI)));

    public static final RegistryObject<Item> AURORA_ASHES = ITEMS.register("aurora_ashes",
            () -> new FuelItem(new Item.Properties(), 1200));

    public static final RegistryObject<Item> TNT_LAUNCHER = ITEMS.register("tnt_launcher",
            () -> new TntLauncherItem(new Item.Properties()));

    public static final RegistryObject<Item> SUPERITE_SWORD = ITEMS.register("superite_sword",
            () -> new SwordItem(ModToolTiers.SUPERITE, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.SUPERITE, 12, -1.5F))));


    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

}
