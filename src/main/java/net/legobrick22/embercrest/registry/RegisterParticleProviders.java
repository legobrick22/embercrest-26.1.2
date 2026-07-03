package net.legobrick22.embercrest.registry;


import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import org.jspecify.annotations.Nullable;

public class RegisterParticleProviders implements ParticleProvider<SimpleParticleType> {

    private final SpriteSet spriteSet;

    // Take in the sprite set provided by the `ParticleResources`.
    public RegisterParticleProviders(SpriteSet spriteSet) {
        this.spriteSet = spriteSet;
    }

    @Override
    public @Nullable Particle createParticle(SimpleParticleType simpleParticleType, ClientLevel clientLevel, double v, double v1, double v2, double v3, double v4, double v5, RandomSource randomSource) {
        return null;
    }
    @SubscribeEvent // on the mod event bus only on the physical client
    public static void registerParticleProviders(RegisterParticleProvidersEvent event) {
        // #registerSpriteSet MUST be used when dealing with particle descriptions.
        event.registerSpriteSet(ModParticleTypes.GRAVITY.get(), RegisterParticleProviders::new);

    // ...
}}
