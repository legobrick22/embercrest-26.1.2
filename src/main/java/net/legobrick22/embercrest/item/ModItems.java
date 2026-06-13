package net.legobrick22.embercrest.item;

import it.crystalnest.cobweb.api.registry.CobwebEntry;
import it.crystalnest.prometheus.api.FireManager;
import it.crystalnest.prometheus.api.FireRegistrar;
import it.crystalnest.prometheus.api.block.CustomCampfireBlock;
import net.legobrick22.embercrest.Embercrest;
import net.legobrick22.embercrest.block.custom.TestBlock;
import net.legobrick22.embercrest.food.ModFoods;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.Weapon;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import static net.legobrick22.embercrest.fire.FireTypes.RUIN_FIRE_TYPE;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Embercrest.MOD_ID);

    public static final DeferredItem<Item> ANCIENT_TABLET = ITEMS.registerSimpleItem("ancient_tablet");

    public static final DeferredItem<Item> ELEMENT_SWORD = ITEMS.registerItem("element_sword",
            ElementSwordItem::new);

    public static final DeferredItem<Item> SEALED_SWORD = ITEMS.registerItem("sealed_sword",
            SealedSwordItem::new);


    public static final DeferredItem<Item> HOLY_TRIDENT = ITEMS.registerItem("holy_trident",
            props ->
                    new HolySpearItem(props
                            .rarity(Rarity.RARE)
                            .durability(250)
                            .attributes(TridentItem.createAttributes())
                            .component(DataComponents.TOOL, TridentItem.createToolProperties())
                            .enchantable(1)
                            .component(DataComponents.WEAPON, new Weapon(1))
                            .spear(ToolMaterial.DIAMOND, 1.05F, 1.075F, 0.5F, 3.0F, 10.0F, 6.5F, 5.1F, 10.0F, 4.6F)

                    ));


    public static final DeferredItem<Item> TITANITE_SHARD = ITEMS.registerSimpleItem("titanite_shard");
    public static final DeferredItem<Item> TITANITE_SLAB = ITEMS.registerSimpleItem("titanite_slab");
    public static final DeferredItem<Item> TITANITE_SLAB_WHITE = ITEMS.registerSimpleItem("titanite_slab_white");

    public static final DeferredItem<Item> RUNESTONE_1 = ITEMS.registerSimpleItem("rune_1");
    public static final DeferredItem<Item> RUNESTONE_2 = ITEMS.registerSimpleItem("rune_2");
    public static final DeferredItem<Item> RUNESTONE_3 = ITEMS.registerSimpleItem("rune_3");
    public static final DeferredItem<Item> RUNESTONE_4 = ITEMS.registerSimpleItem("rune_4");
    public static final DeferredItem<Item> RUNESTONE_5 = ITEMS.registerSimpleItem("rune_5");
    public static final DeferredItem<Item> RUNESTONE_6 = ITEMS.registerSimpleItem("rune_6");
    public static final DeferredItem<Item> RUNESTONE_7 = ITEMS.registerSimpleItem("rune_7");
    public static final DeferredItem<Item> RUNESTONE_8 = ITEMS.registerSimpleItem("rune_8");
    public static final DeferredItem<Item> RUNESTONE_9 = ITEMS.registerSimpleItem("rune_9");
    public static final DeferredItem<Item> RUNESTONE_10 = ITEMS.registerSimpleItem("rune_10");
    public static final DeferredItem<Item> RUNESTONE_11 = ITEMS.registerSimpleItem("rune_11");

    public static final DeferredItem<Item> CUT_DIAMOND = ITEMS.registerSimpleItem("cut_diamond");
    public static final DeferredItem<Item> CUT_RUBY = ITEMS.registerItem("cut_ruby",
            properties -> new Item(properties
                    .food(ModFoods.CUT_RUBY, ModFoods.CUT_RUBY_CONSUMABLE)
                    ));

    public static final DeferredItem<Item> CUT_EMERALD = ITEMS.registerSimpleItem("cut_emerald");
    public static final DeferredItem<Item> CUT_QUARTZ = ITEMS.registerSimpleItem("cut_quartz");

    public static final DeferredItem<Item> BLOOD_VIAL = ITEMS.registerItem("blood_vial",
            properties -> new Item(properties.food(ModFoods.BLOOD_VIAL, ModFoods.BLOOD_VIAL_CONSUMABLE)));


    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);

    }

    }