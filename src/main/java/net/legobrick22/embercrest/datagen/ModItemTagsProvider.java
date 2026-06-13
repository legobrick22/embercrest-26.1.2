package net.legobrick22.embercrest.datagen;

import net.legobrick22.embercrest.Embercrest;
import net.legobrick22.embercrest.item.ModItems;
import net.legobrick22.embercrest.tags.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.data.ItemTagsProvider;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends ItemTagsProvider {
    public ModItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, Embercrest.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        tag(ModTags.Items.TEST_INTERACT)
                .add(Items.IRON_INGOT)
                .add(Items.REDSTONE)
                .add(Items.COPPER_INGOT);


        tag(ItemTags.SWORDS).add(ModItems.ELEMENT_SWORD.get());
        tag(ItemTags.SWORDS).add(ModItems.SEALED_SWORD.get());

        tag(ItemTags.SPEARS).add(ModItems.HOLY_TRIDENT.get());


    }
}