package net.legobrick22.embercrest;

import net.legobrick22.embercrest.abilities.EmbercrestAbilities;
import net.legobrick22.embercrest.block.ModBlocks;
import net.legobrick22.embercrest.client.model.ContenderCharge;
import net.legobrick22.embercrest.creativemodetab.ModCreativeModeTabs;
import net.legobrick22.embercrest.data.ModDataComponents;
import net.legobrick22.embercrest.fire.FireTypes;
import net.legobrick22.embercrest.item.ModItems;
import net.legobrick22.embercrest.registry.ModComponentTypes;
import net.legobrick22.embercrest.registry.ModEffects;
import net.legobrick22.embercrest.registry.ModParticles;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.resources.Identifier;
import net.neoforged.fml.loading.FMLEnvironment;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.slf4j.LoggerFactory;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(Embercrest.MOD_ID)
public class Embercrest {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "embercrest";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    // Create a Deferred Register to hold Blocks which will all be registered under the "embercrest" namespace

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public Embercrest(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        FireTypes.init();

        ModBlocks.register(modEventBus);
        ModItems.register(modEventBus);
        ModDataComponents.register(modEventBus);
        EmbercrestAbilities.ABILITIES.register(modEventBus);
        ModParticles.PARTICLE.register(modEventBus);

        if (FMLEnvironment.getDist().isClient()) {
            modEventBus.addListener(ContenderCharge::registerRangeProperties);
        }
            ModCreativeModeTabs.register(modEventBus);
        ModEffects.MOB_EFFECTS.register(modEventBus);

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (Embercrest) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS){
            event.accept(ModItems.ANCIENT_TABLET);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }
}
