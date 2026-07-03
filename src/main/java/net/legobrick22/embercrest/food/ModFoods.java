package net.legobrick22.embercrest.food;

import net.legobrick22.embercrest.registry.ModEffects;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

public class ModFoods {
    public static final FoodProperties BLOOD_VIAL = new FoodProperties.Builder().nutrition(1).saturationModifier(0.1f).build();

    public static final Consumable BLOOD_VIAL_CONSUMABLE = Consumables.defaultDrink()
            .consumeSeconds(1.5f)
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.REGENERATION, 60, 2), 1f))
            .build();

    public static final FoodProperties GREEN_BLOOD_VIAL = new FoodProperties.Builder().nutrition(0).saturationModifier(0f).build();

    public static final Consumable GREEN_BLOOD_VIAL_CONSUMABLE = Consumables.defaultDrink()
            .consumeSeconds(1.5f)
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.HUNGER, 400, 1), 1f))
            .build();

    public static final FoodProperties PUTREFIED_FLESH = new FoodProperties.Builder().nutrition(0).saturationModifier(0f).build();

    public static final Consumable PUTREFIED_FLESH_CONSUMABLE = Consumables.defaultDrink()
            .consumeSeconds(2.5f)
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.POISON, 400, 2), 1f))
            .build();

    public static final FoodProperties LIQUIFIED_FLESH = new FoodProperties.Builder().nutrition(1).saturationModifier(0.1f).build();

    public static final Consumable LIQUIFIED_FLESH_CONSUMABLE = Consumables.defaultDrink()
            .consumeSeconds(2.5f)
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.NAUSEA, 100, 0), 1f))
            .build();



    public static final FoodProperties CUT_RUBY = new FoodProperties.Builder().alwaysEdible().nutrition(0).saturationModifier(0f)
            .build();

    public static final Consumable CUT_RUBY_CONSUMABLE = Consumables.defaultFood()
            .animation(ItemUseAnimation.SPEAR)
            .consumeSeconds(2f)
            .sound(BuiltInRegistries.SOUND_EVENT.wrapAsHolder(SoundEvents.AMETHYST_CLUSTER_HIT))
            .soundAfterConsume(BuiltInRegistries.SOUND_EVENT.wrapAsHolder(SoundEvents.AMETHYST_BLOCK_BREAK))
            .hasConsumeParticles(false)
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(ModEffects.RUIN, 600, 0), 1f))
            .build();

}
