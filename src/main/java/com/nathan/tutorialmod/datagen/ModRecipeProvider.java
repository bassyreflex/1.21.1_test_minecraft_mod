package com.nathan.tutorialmod.datagen;

import com.nathan.tutorialmod.TutorialMod;
import com.nathan.tutorialmod.block.ModBlocks;
import com.nathan.tutorialmod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(pOutput, pRegistries);
    }

    @Override
    protected void buildRecipes(RecipeOutput pRecipeOutput) {
        List<ItemLike> SUPERITE_SMELTABLES = List.of(ModItems.RAW_SUPERITE.get(),
                ModBlocks.SUPERITE_ORE.get(), ModBlocks.SUPERITE_DEEPSLATE_ORE.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SUPERITE_BLOCK.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.SUPERITE.get())
                .unlockedBy(getHasName(ModItems.SUPERITE.get()), has(ModItems.SUPERITE.get()))
                .save(pRecipeOutput);

        ninePacker(pRecipeOutput, ModBlocks.SUPERITE_BLOCK.get(), ModItems.SUPERITE.get(), "superite");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.SUPERITE.get(), 9)
                .requires(ModBlocks.SUPERITE_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.SUPERITE_BLOCK.get()), has(ModBlocks.SUPERITE_BLOCK.get()))
                .save(pRecipeOutput);

        oreSmelting(pRecipeOutput, SUPERITE_SMELTABLES, RecipeCategory.MISC, ModItems.SUPERITE.get(), 0.25f, 200, "superite");
        oreBlasting(pRecipeOutput, SUPERITE_SMELTABLES, RecipeCategory.MISC, ModItems.SUPERITE.get(), 0.25f, 100, "superite");


        swordRecipe(pRecipeOutput, ModItems.SUPERITE.get(), ModItems.SUPERITE_SWORD.get());
    }

    //HELPER METHODS FOR STANDARDIZED RECIPE TYPES

    //ninePacker with default RecipeCategory.MISC and default "item_block" name for packed block
    protected static void ninePacker(RecipeOutput pRecipeOutput,
                                     ItemLike pUnpacked, ItemLike pPacked,
                                     String pUnpackedName) {
        ninePacker(pRecipeOutput, RecipeCategory.MISC, pUnpacked, pPacked,
                pUnpackedName, pUnpackedName+"_block");
    }
    //ninePacker with specified RecipeCategory and default "item_block" name for packed block
    protected static void ninePacker(RecipeOutput pRecipeOutput, RecipeCategory recipeCategory,
                                     ItemLike pUnpacked, ItemLike pPacked,
                                     String pUnpackedName) {
        ninePacker(pRecipeOutput, recipeCategory, pUnpacked, pPacked,
                pUnpackedName, pUnpackedName+"_block");
    }
    //ninePacker with default RecipeCategory.MISC & specified ids for unpacked and packed items
    protected static void ninePacker(RecipeOutput pRecipeOutput,
                                     ItemLike pUnpacked, ItemLike pPacked,
                                     String pUnpackedName, String pPackedName){
        ninePacker( pRecipeOutput, RecipeCategory.MISC, pUnpacked, pPacked,
                pUnpackedName, pPackedName);
    }
    protected static void ninePacker(RecipeOutput pRecipeOutput, RecipeCategory recipeCategory,
                                     ItemLike pUnpacked, ItemLike pPacked,
                                     String pUnpackedName, String pPackedName){
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, pPacked)
                .requires(pUnpacked, 9)
                .unlockedBy(getHasName(pUnpacked), has(pUnpacked))
                .save(pRecipeOutput, TutorialMod.MOD_ID+":"+pPackedName+"_from_"+pUnpackedName);
        ShapelessRecipeBuilder.shapeless(recipeCategory, pUnpacked, 9)
                .requires(pPacked)
                .unlockedBy(getHasName(pPacked), has(pPacked))
                .save(pRecipeOutput, TutorialMod.MOD_ID+":"+pUnpackedName+"_from_"+pPackedName);
    }

    //stonecutter recipes given a map of results(to quantity of result)
    protected static void stonecutterResultFromMap(RecipeOutput pRecipeOutput, RecipeCategory recipeCategory,
                                                   ItemLike pMaterial, Map<ItemLike,Integer> pResultMap){
        for(Map.Entry<ItemLike, Integer> entry: pResultMap.entrySet()){
            ItemLike pResult=entry.getKey();
            int pResultCount=entry.getValue();
            stonecutterResultFromBase(pRecipeOutput, recipeCategory, pResult, pMaterial, pResultCount);
        }

    }

    //Standard Tool Recipes
    protected static void swordRecipe (RecipeOutput pRecipeOutput,
                                       ItemLike pMaterial, ItemLike tool){
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, tool)
                .pattern("#")
                .pattern("#")
                .pattern("I")
                .define('#', pMaterial)
                .define('I', Items.STICK)
                .unlockedBy(getHasName(pMaterial), has(pMaterial))
                .save(pRecipeOutput);
    }
    protected static void shovelRecipe (RecipeOutput pRecipeOutput,
                                        ItemLike pMaterial, ItemLike tool){
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, tool)
                .pattern("#")
                .pattern("I")
                .pattern("I")
                .define('#', pMaterial)
                .define('I', Items.STICK)
                .unlockedBy(getHasName(pMaterial), has(pMaterial))
                .save(pRecipeOutput);
    }
    protected static void pickaxeRecipe (RecipeOutput pRecipeOutput,
                                         ItemLike pMaterial, ItemLike tool){
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, tool)
                .pattern("###")
                .pattern(" I ")
                .pattern(" I ")
                .define('#', pMaterial)
                .define('I', Items.STICK)
                .unlockedBy(getHasName(pMaterial), has(pMaterial))
                .save(pRecipeOutput);
    }
    protected static void axeRecipe (RecipeOutput pRecipeOutput,
                                     ItemLike pMaterial, ItemLike tool){
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, tool)
                .pattern("##")
                .pattern("I#")
                .pattern("I ")
                .define('#', pMaterial)
                .define('I', Items.STICK)
                .unlockedBy(getHasName(pMaterial), has(pMaterial))
                .save(pRecipeOutput);
    }
    protected static void hoeRecipe (RecipeOutput pRecipeOutput,
                                     ItemLike pMaterial, ItemLike tool){
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, tool)
                .pattern("##")
                .pattern(" I")
                .pattern(" I")
                .define('#', pMaterial)
                .define('I', Items.STICK)
                .unlockedBy(getHasName(pMaterial), has(pMaterial))
                .save(pRecipeOutput);
    }
    protected static void helmetRecipe(RecipeOutput pRecipeOutput,
                                       ItemLike pMaterial, ItemLike armor){
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, armor)
                .pattern("###")
                .pattern("# #")
                .define('#', pMaterial)
                .unlockedBy(getHasName(pMaterial), has(pMaterial))
                .save(pRecipeOutput);
    }
    protected static void chestplateRecipe(RecipeOutput pRecipeOutput,
                                           ItemLike pMaterial, ItemLike armor){
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, armor)
                .pattern("# #")
                .pattern("###")
                .pattern("###")
                .define('#', pMaterial)
                .unlockedBy(getHasName(pMaterial), has(pMaterial))
                .save(pRecipeOutput);
    }
    protected static void leggingsRecipe(RecipeOutput pRecipeOutput,
                                         ItemLike pMaterial, ItemLike armor){
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, armor)
                .pattern("###")
                .pattern("# #")
                .pattern("# #")
                .define('#', pMaterial)
                .unlockedBy(getHasName(pMaterial), has(pMaterial))
                .save(pRecipeOutput);
    }
    protected static void bootsRecipe(RecipeOutput pRecipeOutput,
                                      ItemLike pMaterial, ItemLike armor){
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, armor)
                .pattern("# #")
                .pattern("# #")
                .define('#', pMaterial)
                .unlockedBy(getHasName(pMaterial), has(pMaterial))
                .save(pRecipeOutput);
    }

    protected static void oreSmelting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTime, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput recipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory,
                                                                       List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, TutorialMod.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }
}

