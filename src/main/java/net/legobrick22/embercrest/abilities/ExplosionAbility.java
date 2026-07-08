package net.legobrick22.embercrest.abilities;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.threetag.palladium.power.ability.*;
import net.threetag.palladium.power.energybar.EnergyBarUsage;
import java.util.List;

public class ExplosionAbility extends Ability {

    public static final MapCodec<ExplosionAbility> CODEC = RecordCodecBuilder.mapCodec(instance -> instance
            .group(
                    propertiesCodec(),
                    stateCodec(),
                    energyBarUsagesCodec(),
                    Codec.FLOAT.fieldOf("radius")
                            .orElse(5.0f)
                            .forGetter(a -> a.radius),
                    Codec.BOOL.fieldOf("fire")
                            .orElse(false)
                            .forGetter(a -> a.fire),
                    Codec.BOOL.fieldOf("self_damage")
                            .orElse(false)
                            .forGetter(a -> a.selfdamage))
            .apply(instance, ExplosionAbility::new));

    public final float radius;
    public final boolean fire;
    public final boolean selfdamage;

    public ExplosionAbility(
            AbilityProperties properties,
            AbilityStateManager stateManager,
            List<EnergyBarUsage> energyBarUsages,
            Float radius,
            Boolean fire,
            Boolean selfdamage) {
        super(properties, stateManager, energyBarUsages);
        this.radius = radius;
        this.fire = fire;
        this.selfdamage = selfdamage;
    }

    @Override
    public AbilitySerializer<?> getSerializer() {
        return EmbercrestAbilities.EXPLOSION.get();
    }
    @Override
    public boolean tick(LivingEntity entity, AbilityInstance entry, boolean enabled) {
        if (enabled && !entity.level().isClientSide()) {
            Level level = entity.level();
                // Get the position of the attacked entity
                double x = entity.getX();
                double y = entity.getY();
                double z = entity.getZ();

                // Trigger a standard explosion (Level, Exploder, x, y, z, Power, Causes Fire, Block Interaction)
                level.explode(entity, x, y, z, radius, fire, Level.ExplosionInteraction.MOB);
            return true;

        } else {
            return false;}

    }


}