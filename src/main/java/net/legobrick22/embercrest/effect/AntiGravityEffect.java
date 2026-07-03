package net.legobrick22.embercrest.effect;

import net.legobrick22.embercrest.Embercrest;
import net.legobrick22.embercrest.registry.ModEffects;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.scores.ScoreAccess;
import net.minecraft.world.scores.Scoreboard;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class AntiGravityEffect extends MobEffect {
    public static final double ANTI_GRAV_REDUCTION = -1;

    private static final Identifier GRAV_ID = Embercrest.id("anti_gravity_reduction");

    public AntiGravityEffect(MobEffectCategory category, int color) {
        super(category, color);

        addAttributeModifier(
                Attributes.GRAVITY,GRAV_ID, ANTI_GRAV_REDUCTION
                , AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
        );

    }
    public boolean applyEffectTick(ServerLevel level, LivingEntity mob, int amplification) {

        Vec3 delta = mob.getDeltaMovement();

                mob.setDeltaMovement(mob.getDeltaMovement().add(delta.x/12,((-delta.y)/36), delta.z/12));

        mob.hurtMarked = true;



        return true;
    }

    public boolean shouldApplyEffectTickThisTick(int tickCount, int amplification) {
        int interval = 1 >> amplification;
        return true;


    }


}