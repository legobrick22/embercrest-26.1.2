package net.legobrick22.embercrest.abilities;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.threetag.palladium.power.ability.AbilitySerializer;
import net.threetag.palladium.registry.PalladiumRegistries;

public class EmbercrestAbilities {

    public static final DeferredRegister<AbilitySerializer<?>> ABILITIES = DeferredRegister
            .create(PalladiumRegistries.ABILITY_SERIALIZER, "embercrest");

    public static final DeferredHolder<AbilitySerializer<?>, ExplosionSerializer> EXPLOSION = ABILITIES
            .register("explosion", ExplosionSerializer::new);
    public static final DeferredHolder<AbilitySerializer<?>, FlameBurstSerializer> FLAMEBURST = ABILITIES
            .register("fire_aura", FlameBurstSerializer::new);


    public static void register(IEventBus modEventBus) {
        ABILITIES.register(modEventBus);
    }

}

