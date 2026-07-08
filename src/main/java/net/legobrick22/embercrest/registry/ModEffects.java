package net.legobrick22.embercrest.registry;

import net.legobrick22.embercrest.Embercrest;
import net.legobrick22.embercrest.effect.*;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(Registries.MOB_EFFECT, Embercrest.MOD_ID);

    public static final Holder<MobEffect> RUIN = MOB_EFFECTS.register("ruin",
            () -> new RuinEffect(MobEffectCategory.HARMFUL, 0x7DD3FC)
    );

    public static final Holder<MobEffect> GRAVITY = MOB_EFFECTS.register("gravity",
            () -> new GravityEffect(MobEffectCategory.NEUTRAL, 0x7DD3FC)
    );
    public static final Holder<MobEffect> INSTANT_GRAVITY = MOB_EFFECTS.register("instant_gravity",
            () -> new InstantGravityEffect(MobEffectCategory.NEUTRAL, 0x7DD3FC));

    public static final Holder<MobEffect> ANTI_GRAVITY = MOB_EFFECTS.register("anti_gravity",
            () -> new AntiGravityEffect(MobEffectCategory.NEUTRAL, 0x7DD3FC)

    );
    public static final Holder<MobEffect> CUT_RUBY = MOB_EFFECTS.register("cut_diamond",
            () -> new CutRubyEffect(MobEffectCategory.HARMFUL, 0x0000FF) // Light ice blue color
    );
    public static final Holder<MobEffect> VOID_CHARGE = MOB_EFFECTS.register("void_charge",
            () -> new VoidChargeEffect(MobEffectCategory.HARMFUL, 0x0000FF)); // Light ice blue color

    public static final Holder<MobEffect> SOUL_HARVEST = MOB_EFFECTS.register("soul_harvest",
            () -> new SoulHarvestEffect(MobEffectCategory.BENEFICIAL, 0x0000FF)); // Light ice blue color

    public static final Holder<MobEffect> SURGE = MOB_EFFECTS.register("surge",
            () -> new SurgeEffect(MobEffectCategory.HARMFUL, 0x0000FF)); // Light ice blue color
    public static final Holder<MobEffect> DECAY_CHARGE = MOB_EFFECTS.register("decay_charge",
            () -> new DecayChargeEffect(MobEffectCategory.HARMFUL, 0x0000FF)); // Light ice blue color
}
