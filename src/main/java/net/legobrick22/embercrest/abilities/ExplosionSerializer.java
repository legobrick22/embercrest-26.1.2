package net.legobrick22.embercrest.abilities;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.HolderLookup;
import net.threetag.palladium.documentation.CodecDocumentationBuilder;
import net.threetag.palladium.power.ability.Ability;
import net.threetag.palladium.power.ability.AbilityProperties;
import net.threetag.palladium.power.ability.AbilitySerializer;
import net.threetag.palladium.power.ability.AbilityStateManager;

import java.util.List;

public class ExplosionSerializer extends AbilitySerializer<ExplosionAbility> {

    @Override
    public MapCodec<ExplosionAbility> codec() {
        return ExplosionAbility.CODEC;
    }

    @Override
    public void addDocumentation(
            CodecDocumentationBuilder<Ability, ExplosionAbility> builder,
            HolderLookup.Provider provider) {
        builder.setDescription(
                        "Creates an explosion'.")
                .addOptional("radius", TYPE_DOUBLE,
                        "Radius in blocks of the explosion.",
                        5.0)
                .addOptional("fire", TYPE_BOOLEAN,
                        "Whether the explosion generates fire.",
                        false)
                .addOptional("self_damage", TYPE_BOOLEAN,
                        "Whether the explosion causes self-damage.",
                        true)
                .addExampleObject(new ExplosionAbility(
                        AbilityProperties.BASIC,
                        AbilityStateManager.EMPTY,
                        List.of(),
                        5f, false, false));
    }
}