package net.legobrick22.embercrest.effect;

import net.legobrick22.embercrest.Embercrest;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.InstantenousMobEffect;
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

public class InstantGravityEffect extends InstantenousMobEffect {
    public InstantGravityEffect(MobEffectCategory category, int color) {
        super(category, color);


    }

    public boolean applyEffectTick(ServerLevel level, LivingEntity mob, int amplification) {

        Scoreboard score = level.getScoreboard();
        Objective rotate_x = score.getObjective("gravity_rotation_x");
        Objective rotate_y = score.getObjective("gravity_rotation_y");


        ScoreAccess score_x = score.getOrCreatePlayerScore(mob, rotate_x);
        ScoreAccess score_y = score.getOrCreatePlayerScore(mob, rotate_y);

        int scoreValue_x = score_x.get();
        int scoreValue_y = score_y.get();

        Vec3 delta = mob.getDeltaMovement();


        double gravity = mob.getPersistentData().getDoubleOr("embercrest:gravity", 0.8);
        float fallspeed = 0;

        if (gravity != (double) 0.0F && mob instanceof LivingEntity) {
                mob.setDeltaMovement(mob.getDeltaMovement().add(-sin(Math.toRadians(scoreValue_x)) * cos(Math.toRadians(scoreValue_y)) * gravity,
                        -sin(Math.toRadians(scoreValue_y)) * gravity*2, cos(Math.toRadians(scoreValue_x)) * cos(Math.toRadians(scoreValue_y)) * gravity));
                mob.hurtMarked = true;

        }
        return true;


    }
}