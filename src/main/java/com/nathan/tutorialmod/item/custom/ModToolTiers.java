
package com.nathan.tutorialmod.item.custom;

import com.nathan.tutorialmod.item.ModItems;
import com.nathan.tutorialmod.util.ModTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class ModToolTiers {
    public static final Tier SUPERITE = new ForgeTier(1400, 12, 5F, 12,
            ModTags.Blocks.NEEDS_SUPERITE_TOOL, () -> Ingredient.of(ModItems.SUPERITE.get()),
            ModTags.Blocks.INCORRECT_FOR_SUPERITE_TOOL);
}
