package com.nathan.tutorialmod.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class TntLauncherItem extends Item {

    public TntLauncherItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide) {
            ItemStack itemStack = player.getItemInHand(hand);

            // Spawn TNT entity
            BlockPos playerPos = player.blockPosition();
            PrimedTnt tnt = new PrimedTnt(level, playerPos.getX(), playerPos.getY() + 1.5, playerPos.getZ(), player);
            tnt.setFuse(80); // Set fuse timer (in ticks, 4 seconds default)

            // Launch the TNT
            tnt.setDeltaMovement(player.getLookAngle().scale(10));

            // Add the TNT to the world
            level.addFreshEntity(tnt);

            // Make the TNT 10x more explosive
            level.explode(tnt, tnt.getX(), tnt.getY(), tnt.getZ(), 40.0F, Level.ExplosionInteraction.TNT);

            // Play a sound
            level.playSound(null, playerPos, SoundEvents.TNT_PRIMED, player.getSoundSource(), 1.0F, 1.0F);


        }

        return InteractionResultHolder.sidedSuccess(player.getItemInHand(hand), level.isClientSide());
    }
}
