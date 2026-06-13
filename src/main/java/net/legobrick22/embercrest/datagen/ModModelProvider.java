package net.legobrick22.embercrest.datagen;

import net.legobrick22.embercrest.Embercrest;
import net.legobrick22.embercrest.block.ModBlocks;
import net.legobrick22.embercrest.item.ModItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.data.PackOutput;

public class ModModelProvider extends ModelProvider {
    public ModModelProvider(PackOutput output) {
        super(output, Embercrest.MOD_ID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
itemModels.generateFlatItem(ModItems.ANCIENT_TABLET.get(), ModelTemplates.FLAT_ITEM);
    itemModels.generateFlatItem(ModItems.ELEMENT_SWORD.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.TITANITE_SHARD.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.TITANITE_SLAB.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.TITANITE_SLAB_WHITE.get(), ModelTemplates.FLAT_ITEM);

        itemModels.generateFlatItem(ModItems.HOLY_TRIDENT.get(), ModelTemplates.FLAT_ITEM);

        itemModels.generateFlatItem(ModItems.RUNESTONE_1.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.RUNESTONE_2.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.RUNESTONE_3.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.RUNESTONE_4.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.RUNESTONE_5.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.RUNESTONE_6.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.RUNESTONE_7.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.RUNESTONE_8.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.RUNESTONE_9.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.RUNESTONE_10.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.RUNESTONE_11.get(), ModelTemplates.FLAT_ITEM);

        itemModels.generateFlatItem(ModItems.BLOOD_VIAL.get(), ModelTemplates.FLAT_ITEM);



        blockModels.createTrivialCube(ModBlocks.TITANITE_ORE.get());
        blockModels.createTrivialCube(ModBlocks.TEST_BLOCK.get());

        blockModels.createTrivialCube(ModBlocks.DEEPSLATE_TITANITE_ORE.get());

        itemModels.generateFlatItem(ModItems.CUT_DIAMOND.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.CUT_EMERALD.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.CUT_RUBY.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.CUT_QUARTZ.get(), ModelTemplates.FLAT_ITEM);


    }
}
