package net.legobrick22.embercrest.client;

import net.legobrick22.embercrest.Embercrest;
import net.legobrick22.embercrest.registry.ModParticleTypes;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;

@EventBusSubscriber(modid = Embercrest.MOD_ID, value = Dist.CLIENT)
public class EmbercrestClient {

    /**
     * Initialize client-side systems.
     * Called from main mod class during construction.
     *
     * @param eventBus The mod event bus
     */
    public static void init(IEventBus eventBus) {
        // Register client-specific event listeners on the MOD bus
        eventBus.addListener(EmbercrestClient::registerParticleProviders);

        Embercrest.LOGGER.debug("Auroral client initialized");
    }


    /**
     * Register entity renderers.
     */

    /**
     * Register particle providers for custom particles.
     */
    public static void registerParticleProviders(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ModParticleTypes.GRAVITY_PARTICLE.get(), RegisterParticle.Provider::new);
    }

    /**
     * Register menu screens for custom menus.
     */
    }

    /**
     * Reset client state when disconnecting from server.
     * Releases GPU resources and clears cached state.
     */

}

