package net.legobrick22.embercrest.datagen;

import net.legobrick22.embercrest.Embercrest;
import net.legobrick22.embercrest.block.ModBlocks;
import net.legobrick22.embercrest.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    public static class Runner extends RecipeProvider.Runner {

        public Runner(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries) {
            super(packOutput, registries);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
            return new ModRecipeProvider(registries, output);
        }

        @Override
        public String getName() {
            return "Embercrest Recipes";
        }
    }

    @Override
    protected void buildRecipes() {
        shaped(RecipeCategory.MISC, ModItems.TITANITE_SLAB.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.TITANITE_SHARD.get())
                .unlockedBy(getHasName(ModItems.TITANITE_SHARD.get()), has(ModItems.TITANITE_SHARD))
                .group("titanite")
                .save(output, "embercrest:titanite_slab_from_shard");

        shapeless(RecipeCategory.MISC, ModItems.TITANITE_SHARD.get(), 9)
                .requires(ModItems.TITANITE_SLAB)
                .unlockedBy(getHasName(ModItems.TITANITE_SLAB.get()), has(ModItems.TITANITE_SLAB))
                .group("titanite")
                .save(output, "embercrest:titanite_shard_from_slab");

        shaped(RecipeCategory.MISC, ModItems.TITANITE_SLAB.get())
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', Items.GLOWSTONE_DUST)
                .define('B', ModItems.TITANITE_SLAB.get())

                .unlockedBy(getHasName(ModItems.TITANITE_SLAB.get()), has(ModItems.TITANITE_SLAB))
                .group("titanite")
                .save(output, "embercrest:white_titanite_from_slab");


        List<ItemLike> TITANITE_SMELTABLES = List.of(ModBlocks.TITANITE_ORE, ModBlocks.DEEPSLATE_TITANITE_ORE);

        oreSmelting(TITANITE_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.MISC, ModItems.TITANITE_SHARD.get(), 0.4f, 300, "titanite");
        oreBlasting(TITANITE_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.MISC, ModItems.TITANITE_SHARD.get(), 0.4f, 150, "titanite");


    }

    @Override
    protected <T extends AbstractCookingRecipe> void oreCooking(AbstractCookingRecipe.Factory<T> factory, List<ItemLike> smeltables,
                                                                RecipeCategory craftingCategory, CookingBookCategory cookingCategory, ItemLike result,
                                                                float experience, int cookingTime, String group, String fromDesc) {
        for (ItemLike itemlike : smeltables) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), craftingCategory, cookingCategory, result, experience, cookingTime, factory).group(group).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(output, Embercrest.MOD_ID + ":" + getItemName(result) + fromDesc + "_" + getItemName(itemlike));

        }
    }
}
