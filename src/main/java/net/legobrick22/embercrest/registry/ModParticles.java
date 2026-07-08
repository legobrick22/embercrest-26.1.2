package net.legobrick22.embercrest.registry;

import net.legobrick22.embercrest.Embercrest;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE =
            DeferredRegister.create(Registries.PARTICLE_TYPE, Embercrest.MOD_ID);

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> GRAVITY_PARTICLE = PARTICLE.register(
            "gravity",
            () -> new SimpleParticleType(false)
    );
}


