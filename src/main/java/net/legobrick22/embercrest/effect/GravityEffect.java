package net.legobrick22.embercrest.effect;

import net.legobrick22.embercrest.Embercrest;
import net.legobrick22.embercrest.registry.ModEffects;
import net.minecraft.network.chat.Component;
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
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static net.legobrick22.embercrest.Embercrest.LOGGER;

public class GravityEffect extends MobEffect {
    public static final double GRAV_REDUCTION = -1;

    private static final Identifier GRAV_ID = Embercrest.id("gravity_reduction");

    public GravityEffect(MobEffectCategory category, int color) {
        super(category, color);

        addAttributeModifier(
                Attributes.GRAVITY,GRAV_ID, GRAV_REDUCTION
                , AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
        );

    }

    public boolean applyEffectTick(ServerLevel level, LivingEntity mob, int amplification) {
        var attachment_x = NeoForgeRegistries.ATTACHMENT_TYPES.getValue(Identifier.fromNamespaceAndPath("embercrest", "degrees_x"));
        var attachment_y = NeoForgeRegistries.ATTACHMENT_TYPES.getValue(Identifier.fromNamespaceAndPath("embercrest", "degrees_y"));

        Scoreboard score = level.getScoreboard();
        Objective rotate_x = score.getObjective("gravity_rotation_x");
        Objective rotate_y = score.getObjective("gravity_rotation_y");
        Objective rotate_r = score.getObjective("gravity_rotation_r");


        ScoreAccess score_x = score.getOrCreatePlayerScore(mob, rotate_x);
        ScoreAccess score_y = score.getOrCreatePlayerScore(mob, rotate_y);
        ScoreAccess score_r = score.getOrCreatePlayerScore(mob, rotate_r);

        int scoreValue_x = score_x.get();
        int scoreValue_y = score_y.get()-180*score_r.get();

        Vec3 delta = mob.getDeltaMovement();


        double gravity = mob.getPersistentData().getDoubleOr("embercrest:gravity", 0.04);
        float fallspeed = 0;

        if (gravity != (double) 0.0F && mob instanceof LivingEntity) {
            if (mob.horizontalCollision) {
                mob.hurt(mob.damageSources().fall(), (float) (2+((gravity-0.04)*500)));
                mob.removeEffect(ModEffects.GRAVITY);
            }

            else{
                mob.setDeltaMovement(mob.getDeltaMovement().add(-sin(Math.toRadians(scoreValue_x)) * cos(Math.toRadians(scoreValue_y)) * gravity,
                        -sin(Math.toRadians(scoreValue_y)) * gravity, cos(Math.toRadians(scoreValue_x)) * cos(Math.toRadians(scoreValue_y)) * gravity));
                mob.hurtMarked = true;
                fallspeed = (float) delta.length();
            }}


        mob.getPersistentData().putDouble("embercrest:gravity", gravity + 0.0004);

        return true;
    }

    public boolean shouldApplyEffectTickThisTick(int tickCount, int amplification) {
        int interval = 1 >> amplification;
        return true;


    }
}