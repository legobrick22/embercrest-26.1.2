package net.legobrick22.embercrest.effect;

import net.legobrick22.embercrest.Embercrest;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class SoulHarvestEffect extends MobEffect {

    public static final double ABSORB_BUFF = 4.0;


    private static final Identifier ABSORB_BUFF_ID = Embercrest.id("absorption_buff");

    public SoulHarvestEffect(MobEffectCategory category, int color) {
        super(category, color);

        addAttributeModifier(
                Attributes.MAX_ABSORPTION,
                ABSORB_BUFF_ID,
                +ABSORB_BUFF,
                AttributeModifier.Operation.ADD_VALUE
        );

    }
    public boolean shouldApplyEffectTickThisTick(int tickCount, int amplification) {
        int interval = 20 >> amplification;
        return interval <= 0 || tickCount % interval == 0;
    }



    }