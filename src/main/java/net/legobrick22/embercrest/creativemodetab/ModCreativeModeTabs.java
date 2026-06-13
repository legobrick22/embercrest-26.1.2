package net.legobrick22.embercrest.creativemodetab;

import net.legobrick22.embercrest.Embercrest;
import net.legobrick22.embercrest.block.ModBlocks;
import net.legobrick22.embercrest.item.ElementSwordItem;
import net.legobrick22.embercrest.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Embercrest.MOD_ID);

    public static final Supplier<CreativeModeTab> EMBERCREST_TAB = CREATIVE_MODE_TABS.register("embercrest_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.ANCIENT_TABLET.get()))
                    .title(Component.translatable("creativetab.embercrest.embercrest"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.ANCIENT_TABLET);

                        output.accept(ModItems.ELEMENT_SWORD);
                        output.accept(ModItems.SEALED_SWORD);
                        output.accept(ModItems.HOLY_TRIDENT);

                        output.accept(ModItems.RUNESTONE_1);
                        output.accept(ModItems.RUNESTONE_2);
                        output.accept(ModItems.RUNESTONE_3);
                        output.accept(ModItems.RUNESTONE_4);
                        output.accept(ModItems.RUNESTONE_5);
                        output.accept(ModItems.RUNESTONE_6);
                        output.accept(ModItems.RUNESTONE_7);
                        output.accept(ModItems.RUNESTONE_8);
                        output.accept(ModItems.RUNESTONE_9);
                        output.accept(ModItems.RUNESTONE_10);
                        output.accept(ModItems.RUNESTONE_11);


                        output.accept(ModItems.TITANITE_SHARD);
                        output.accept(ModItems.TITANITE_SLAB_WHITE);
                        output.accept(ModItems.TITANITE_SLAB);
                        output.accept(ModBlocks.TITANITE_ORE);
                        output.accept(ModBlocks.DEEPSLATE_TITANITE_ORE);

                        output.accept(ModItems.CUT_DIAMOND);
                        output.accept(ModItems.CUT_EMERALD);
                        output.accept(ModItems.CUT_RUBY);
                        output.accept(ModItems.CUT_QUARTZ);

                        output.accept(ModItems.BLOOD_VIAL);


                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
