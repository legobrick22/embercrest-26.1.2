package net.legobrick22.embercrest.abilities;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.HolderLookup;
import net.threetag.palladium.documentation.CodecDocumentationBuilder;
import net.threetag.palladium.power.ability.Ability;
import net.threetag.palladium.power.ability.AbilityProperties;
import net.threetag.palladium.power.ability.AbilitySerializer;
import net.threetag.palladium.power.ability.AbilityStateManager;

import java.util.List;

public class FlameBurstSerializer extends AbilitySerializer<FlameBurstAbility> {

    @Override
    public MapCodec<FlameBurstAbility> codec() {
        return FlameBurstAbility.CODEC;
    }

    @Override
    public void addDocumentation(
            CodecDocumentationBuilder<Ability, FlameBurstAbility> builder,
            HolderLookup.Provider provider) {
        builder.setDescription(
                        "Creates an aura of fire damage'.")
                .addOptional("radius", TYPE_DOUBLE,
                        "Radius in blocks of the aura.",
                        5.0)
                .addExampleObject(new FlameBurstAbility(
                        AbilityProperties.BASIC,
                        AbilityStateManager.EMPTY,
                        List.of(),
                        5f));
    }
}