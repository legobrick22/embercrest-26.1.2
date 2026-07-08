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
    public static final double SPEED_REDUCTION = -0.1;

    public static final double BURN_MULT = 0.25;

    private static final Identifier BURN_MULT_ID = Embercrest.id("void_burn_increase");
    private static final Identifier SPEED_MODIFIER_ID = Embercrest.id("void_speed_reduction");

    public VoidChargeEffect(MobEffectCategory category, int color) {
        super(category, color);

        // Apply armor reduction as an attribute modifier
        // This automatically scales with effect level
        addAttributeModifier(
                Attributes.BURNING_TIME,
                BURN_MULT_ID,
                BURN_MULT,
                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
        );

        // Apply movement speed reduction (similar to Slowness I)
        addAttributeModifier(
                Attributes.MOVEMENT_SPEED,
                SPEED_MODIFIER_ID,
                SPEED_REDUCTION,
                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
        );
    }

    }