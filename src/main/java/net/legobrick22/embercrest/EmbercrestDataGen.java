package net.legobrick22.embercrest;

import net.legobrick22.embercrest.block.ModBlockLootTableProvider;
import net.legobrick22.embercrest.datagen.ModBlockTagsProvider;
import net.legobrick22.embercrest.datagen.ModItemTagsProvider;
import net.legobrick22.embercrest.datagen.ModModelProvider;
import net.legobrick22.embercrest.datagen.ModRecipeProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Collections;
import java.util.List;

@EventBusSubscriber(modid = Embercrest.MOD_ID)
public class EmbercrestDataGen {
    @SubscribeEvent
    public static void gatherClientData(GatherDataEvent.Client event){
        DataGenerator generator = event.getGenerator();
        PackOutput PackOutput = generator.getPackOutput();
        var lookupProvider = event.getLookupProvider();

        generator.addProvider(true, new ModModelProvider(PackOutput));
        generator.addProvider(true, new ModBlockTagsProvider(PackOutput, lookupProvider));
        generator.addProvider(true, new ModItemTagsProvider(PackOutput, lookupProvider));

        generator.addProvider(true, new LootTableProvider(PackOutput, Collections.emptySet(),
                List.of(new LootTableProvider.SubProviderEntry(ModBlockLootTableProvider::new, LootContextParamSets.BLOCK)), lookupProvider));

        generator.addProvider(true, new ModRecipeProvider.Runner(PackOutput, lookupProvider));
    }
}
