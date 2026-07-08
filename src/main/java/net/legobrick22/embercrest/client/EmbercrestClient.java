package net.legobrick22.embercrest.client;

import net.legobrick22.embercrest.Embercrest;
import net.legobrick22.embercrest.registry.ModParticles;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

// This class will not load on dedicated servers. Accessing client side code from here is safe.
@Mod(value = Embercrest.MOD_ID, dist = Dist.CLIENT)
// You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
@EventBusSubscriber(modid = Embercrest.MOD_ID, value = Dist.CLIENT)
public class EmbercrestClient {
    public EmbercrestClient(ModContainer container) {
        // Allows NeoForge to create a config screen for this mod's configs.
        // The config screen is accessed by going to the Mods screen > clicking on your mod > clicking on config.
        // Do not forget to add translations for your config options to the en_us.json file.
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        // Some client setup code
        Embercrest.LOGGER.info("HELLO FROM CLIENT SETUP");
        Embercrest.LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
    }



    public static void init(IEventBus eventBus) {
        // Register client-specific event listeners on the MOD bus
        eventBus.addListener(net.legobrick22.embercrest.client.EmbercrestClient::registerParticleProviders);

        Embercrest.LOGGER.debug("Embercrest client registration initialized");
    }
    public static void registerParticleProviders(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ModParticles.GRAVITY_PARTICLE.get(), GravityParticleProvider::new);
    }


}