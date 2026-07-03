package net.legobrick22.embercrest.effect;

import net.legobrick22.embercrest.Embercrest;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class VoidChargeEffect extends MobEffect {

    /**
     * Movement speed reduction per level (0.15 = 15% reduction per level, similar to Slowness I).
     */
    public static final double TOUGHNESS_REDUCTION_PER_LEVEL = 1.0;

    /**
     * Healing reduction multiplier per level (0.25 = 25% reduction per level).
     * At level 1: 75% healing, level 2: 50% healing, level 3: 25% healing, level 4: no healing
     */

    /**
     * Armor reduction per level in armor points.
     */
    public static final double ARMOR_REDUCTION_PER_LEVEL = 3.0;

    private static final Identifier ARMOR_MODIFIER_ID = Embercrest.id("void_armor_reduction");
    private static final Identifier TOUGHNESS_MODIFIER_ID = Embercrest.id("void_toughness_reduction");

    public VoidChargeEffect(MobEffectCategory category, int color) {
        super(category, color);

        // Apply armor reduction as an attribute modifier
        // This automatically scales with effect level
        addAttributeModifier(
                Attributes.ARMOR,
                ARMOR_MODIFIER_ID,
                -ARMOR_REDUCTION_PER_LEVEL,
                AttributeModifier.Operation.ADD_VALUE
        );

        // Apply movement speed reduction (similar to Slowness I)
        addAttributeModifier(
                Attributes.ARMOR_TOUGHNESS,
                TOUGHNESS_MODIFIER_ID,
                TOUGHNESS_REDUCTION_PER_LEVEL,
                AttributeModifier.Operation.ADD_VALUE
        );
    }


    public boolean applyEffectTick(ServerLevel level, LivingEntity mob, int amplification) {
        float damageAmount = 2.0f + amplification;

        mob.hurtServer(level, mob.damageSources().magic(), damageAmount);
        return true;

        /**
         * Calculates the shield damage multiplier for a given effect amplifier.
         * @param amplifier The effect amplifier (0 = level 1, 1 = level 2, etc.)
         * @return The multiplier to apply to shield durability damage (1.0 = normal, 1.5 = 50% more, etc.)
         */

    }
    public boolean shouldApplyEffectTickThisTick(int tickCount, int amplification) {
        int interval = 25 >> amplification;
        return interval <= 0 || tickCount % interval == 0;
    }

    }