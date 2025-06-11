package com.nathan.tutorialmod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoodProperties {
    public static final FoodProperties KOHLRABI = new FoodProperties.Builder()
            .nutrition(3)
            .saturationModifier(0.25f)
            .effect(new MobEffectInstance(MobEffects.INVISIBILITY, 400), 0.4f)
            .fast()
            .build();
    public static final FoodProperties SUPERITE = new FoodProperties.Builder()
            .nutrition(3)
            .saturationModifier(0.50f)
            .effect(new MobEffectInstance(MobEffects.HARM, 1, 10), 0.4f)
            .effect(new MobEffectInstance(MobEffects.HEALTH_BOOST, 10000, 5),0.6f)
            .fast().alwaysEdible()
            .build();
}
