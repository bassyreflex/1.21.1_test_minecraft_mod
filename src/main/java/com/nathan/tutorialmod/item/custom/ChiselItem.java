package com.nathan.tutorialmod.item.custom;

import com.nathan.tutorialmod.block.ModBlocks;
import com.nathan.tutorialmod.component.ModDataComponentTypes;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.List;
import java.util.Map;

public class ChiselItem extends Item {

    private static final Map<Block, Block> CHISEL_MAP =
            Map.of(
                    Blocks.STONE, Blocks.STONE_BRICKS,
                    Blocks.END_STONE, Blocks.END_STONE_BRICKS,
                    Blocks.DEEPSLATE, Blocks.DEEPSLATE_BRICKS,
                    Blocks.GRASS_BLOCK, ModBlocks.SUPERITE_ORE.get()
                    );

    public ChiselItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {

        Level level = pContext.getLevel();
        Block clickedBlock = level.getBlockState(pContext.getClickedPos()).getBlock();

        if(CHISEL_MAP.containsKey(clickedBlock)){
            if(!level.isClientSide){
                level.setBlockAndUpdate(pContext.getClickedPos(), CHISEL_MAP.get(clickedBlock).defaultBlockState());

                pContext.getItemInHand().hurtAndBreak(1,((ServerLevel) level), ((ServerPlayer) pContext.getPlayer()),
                        item -> pContext.getPlayer().onEquippedItemBroken(item,  EquipmentSlot.MAINHAND));

                level.playSound(null,pContext.getClickedPos(), SoundEvents.GRINDSTONE_USE, SoundSource.BLOCKS);

                pContext.getItemInHand().set(ModDataComponentTypes.NUMBER_USES.get(), pContext.getItemInHand().get(ModDataComponentTypes.NUMBER_USES.get()) != null ?
                        pContext.getItemInHand().get(ModDataComponentTypes.NUMBER_USES.get())+1 : 1);
            }
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        if(pStack.get(ModDataComponentTypes.NUMBER_USES.get())!=null){
            int numberOfUses = pStack.get(ModDataComponentTypes.NUMBER_USES.get());
            Component tooltip = Component.literal("Number of Uses: " + numberOfUses)
                    .withStyle(style -> style.withItalic(true).withColor(ChatFormatting.BLUE));
            pTooltipComponents.add(tooltip);
        }
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
    }
}
