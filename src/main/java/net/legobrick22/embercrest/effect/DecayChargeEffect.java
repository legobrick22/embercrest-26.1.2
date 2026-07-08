package net.legobrick22.embercrest.effect;

import net.legobrick22.embercrest.Embercrest;
import net.legobrick22.embercrest.registry.ModEffects;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class DecayChargeEffect extends MobEffect {

    public DecayChargeEffect(MobEffectCategory category, int color) {
        super(category, color);
        }

    public boolean applyEffectTick(ServerLevel level, LivingEntity mob, int amplification) {
        float damageAmount = 0.25f+amplification/4.0f;

        mob.hurtServer(level, mob.damageSources().wither(), damageAmount);
        mob.addEffect(new MobEffectInstance(ModEffects.DECAY_CHARGE, mob.getEffect(ModEffects.DECAY_CHARGE).getDuration(), amplification + 1));
        return true;

        /**
         * Calculates the shield damage multiplier for a given effect amplifier.
         * @param amplifier The effect amplifier (0 = level 1, 1 = level 2, etc.)
         * @return The multiplier to apply to shield durability damage (1.0 = normal, 1.5 = 50% more, etc.)
         */

    }
    public boolean shouldApplyEffectTickThisTick(int tickCount, int amplification) {
            return tickCount % 20 == 0;
        }

    }