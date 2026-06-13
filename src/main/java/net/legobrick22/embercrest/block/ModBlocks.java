package net.legobrick22.embercrest.block;

import it.crystalnest.cobweb.api.registry.CobwebEntry;
import it.crystalnest.prometheus.api.FireRegistrar;
import it.crystalnest.prometheus.api.block.CustomCampfireBlock;
import net.legobrick22.embercrest.Embercrest;
import net.legobrick22.embercrest.block.custom.RuinFireBlock;
import net.legobrick22.embercrest.block.custom.TestBlock;
import net.legobrick22.embercrest.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;

import static net.legobrick22.embercrest.fire.FireTypes.RUIN_FIRE_TYPE;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(Embercrest.MOD_ID);

    public static final DeferredBlock<Block> TITANITE_ORE = registerBlock("titanite_ore",
            properties -> new Block(properties
                    .strength(4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)
                    ));

    public static final DeferredBlock<Block> DEEPSLATE_TITANITE_ORE = registerBlock("deepslate_titanite_ore",
            properties -> new Block(properties
                    .strength(4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)
            ));

    public static final DeferredBlock<Block> TEST_BLOCK = registerBlock("test_block",
            properties -> new TestBlock(properties
                    .strength(4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL)
            ));

    public static final DeferredBlock<RuinFireBlock> RUIN_FIRE = registerBlock("ruin",
            properties -> new RuinFireBlock(properties
                    .mapColor(MapColor.EMERALD)
                    .replaceable()
                    .noCollision()
                    .instabreak()
                    .lightLevel((statex) -> 10)
                    .sound(SoundType.WOOL)
                    .pushReaction(PushReaction.DESTROY)));

    public static final CobwebEntry<CustomCampfireBlock> RUIN_CAMPFIRE = FireRegistrar.registerCampfire(RUIN_FIRE_TYPE, true, CustomCampfireBlock::new);


    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Function<BlockBehaviour.Properties, T> function){
        DeferredBlock<T> toReturn = BLOCKS.registerBlock(name, function);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.registerItem(name, properties -> new BlockItem(block.get(), properties.useBlockDescriptionPrefix()));
    }


    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
