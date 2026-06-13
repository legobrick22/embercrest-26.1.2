/* package net.legobrick22.embercrest.abilities;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.threetag.palladium.power.ability.Ability;
import net.threetag.palladium.power.ability.AbilityProperties;
import net.threetag.palladium.power.ability.AbilitySerializer;
import net.threetag.palladium.power.ability.AbilityStateManager;
import net.threetag.palladium.power.energybar.EnergyBarUsage;

/* import java.util.List;

import static net.minecraft.world.level.block.state.BlockBehaviour.propertiesCodec;

public class ElementalAbility extends Ability {

    public static final MapCodec<ElementalAbility> CODEC = RecordCodecBuilder.mapCodec(instance -> instance
            .group(
                    propertiesCodec(),
                    stateCodec(),
                    energyBarUsagesCodec(),

            .apply(instance, ElementalAbility::new));

    public final String delayTicks;
    public final double initialDamage;
    public final double extraDamage;
    public final int particleColor;

    public ElementalAbility(
            AbilityProperties properties,
            AbilityStateManager stateManager,
            List<EnergyBarUsage> energyBarUsages,
            Integer delayTicks,
            Double initialDamage,
            Double extraDamage,
            Integer particleColor) {
        super(properties, stateManager, energyBarUsages);
        this.delayTicks = delayTicks;
        this.initialDamage = initialDamage;
        this.extraDamage = extraDamage;
        this.particleColor = particleColor;
    }

    @Override
    public AbilitySerializer<?> getSerializer() {
        return EmbercrestAbilities.ELEMENTAL.get();
    }
}
*/