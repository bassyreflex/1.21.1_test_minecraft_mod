package com.nathan.tutorialmod.event;

import com.nathan.tutorialmod.TutorialMod;
import com.nathan.tutorialmod.item.custom.LoggerItem;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Mod.EventBusSubscriber(modid = TutorialMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEvents {
    private static final Set<BlockPos> HARVESTED_BLOCKS = new HashSet<>();


    @SubscribeEvent
    public static void onLoggerUsage(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        ItemStack mainHandItem = player.getMainHandItem();

        if (mainHandItem.getItem() instanceof LoggerItem logger && player instanceof ServerPlayer serverPlayer) {
            BlockPos initialBlockPos = event.getPos();
            if (HARVESTED_BLOCKS.contains(initialBlockPos)) {
                return;
            }
            if (event.getLevel() instanceof Level level) {
                List<BlockPos> treeBlocks = LoggerItem.findTreeBlocks(level, initialBlockPos);
                if (treeBlocks.isEmpty()){
                    return;
                }
                else{
                    for (BlockPos pos : treeBlocks) {
                        if (pos == initialBlockPos || !logger.isCorrectToolForDrops(mainHandItem, event.getLevel().getBlockState(pos))) {
                            continue;
                        }

                        HARVESTED_BLOCKS.add(pos);
                        serverPlayer.gameMode.destroyBlock(pos);
                        HARVESTED_BLOCKS.remove(pos);
                    }
                }
            }
        }
    }
}
