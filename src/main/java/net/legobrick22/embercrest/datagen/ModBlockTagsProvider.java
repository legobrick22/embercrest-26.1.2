package net.legobrick22.embercrest.datagen;

import net.legobrick22.embercrest.Embercrest;
import net.legobrick22.embercrest.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.BlockTagsProvider;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, Embercrest.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.TITANITE_ORE.get())
                .add(ModBlocks.DEEPSLATE_TITANITE_ORE.get())
        .add(ModBlocks.TEST_BLOCK.get());
        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.TITANITE_ORE.get())
                .add(ModBlocks.DEEPSLATE_TITANITE_ORE.get());
        tag(BlockTags.FIRE)
        .add(ModBlocks.RUIN_FIRE.get());
    }
}
