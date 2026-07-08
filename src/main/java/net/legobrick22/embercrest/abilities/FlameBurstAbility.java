package net.legobrick22.embercrest.abilities;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.threetag.palladium.power.ability.*;
import net.threetag.palladium.power.energybar.EnergyBarUsage;
import java.util.List;

public class FlameBurstAbility extends Ability {

    public static final MapCodec<FlameBurstAbility> CODEC = RecordCodecBuilder.mapCodec(instance -> instance
            .group(
                    propertiesCodec(),
                    stateCodec(),
                    energyBarUsagesCodec(),
                    Codec.FLOAT.fieldOf("radius")
                            .orElse(5.0f)
                            .forGetter(a -> a.radius))
            .apply(instance, FlameBurstAbility::new));

    public final float radius;

    public FlameBurstAbility(
            AbilityProperties properties,
            AbilityStateManager stateManager,
            List<EnergyBarUsage> energyBarUsages,
            Float radius) {
        super(properties, stateManager, energyBarUsages);
        this.radius = radius;
    }

    @Override
    public AbilitySerializer<?> getSerializer() {
        return EmbercrestAbilities.FLAMEBURST.get();
    }
    @Override
    public boolean tick(LivingEntity entity, AbilityInstance entry, boolean enabled) {
        if (enabled && !entity.level().isClientSide()) {
            Level level = entity.level();
                // Get the position of the attacked entity
                double x = entity.getX();
                double y = entity.getY();
                double z = entity.getZ();

            AABB auraradius = new AABB(
                    x-radius, y-radius, z-radius,
                    x+radius, y+radius, z+radius
                    );

            List<Entity> inrange = level.getEntities(null, auraradius);


            for (Entity livingentity : inrange) {
                if (livingentity instanceof LivingEntity && livingentity.distanceToSqr(x, y, z) <= radius*radius){
                    livingentity.hurtServer((ServerLevel) level, livingentity.damageSources().inFire(), 2);
                };
            }
            return true;

        } else {
            return false;}

    }


}