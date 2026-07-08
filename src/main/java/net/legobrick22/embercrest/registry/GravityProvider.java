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

public class GravityProvider implements ParticleProvider<SimpleParticleType> {



    private final SpriteSet spriteSet;

    // Take in the sprite set provided by the `ParticleResources`.
    public GravityProvider(SpriteSet spriteSet) {
        this.spriteSet = spriteSet;
    }

    @Override
    public @Nullable Particle createParticle(SimpleParticleType simpleParticleType, ClientLevel clientLevel, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, RandomSource randomSource) {
        // 1. Create the particle instance (Use your actual particle class name here, e.g., GravityParticle)
        GravityProvider particle = new GravityProvider(spriteSet);

        // 2. Assign the sprite textures from your SpriteSet
        particle.pickSprite(this.spriteSet);

        // 3. Return the fully configured particle at the very end
        return particle;
    }
    @SubscribeEvent // on the mod event bus only on the physical client
    public static void registerParticleProviders(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ModParticleTypes.GRAVITY_PARTICLE.get(), GravityProvider::new);


    // ...
}

}
