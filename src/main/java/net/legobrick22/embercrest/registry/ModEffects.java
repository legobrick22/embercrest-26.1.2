package net.legobrick22.embercrest.registry;

import net.legobrick22.embercrest.Embercrest;
import net.legobrick22.embercrest.effect.RuinEffect;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(Registries.MOB_EFFECT, Embercrest.MOD_ID);

    public static final Holder<MobEffect> RUIN = MOB_EFFECTS.register("ruin",
            () -> new RuinEffect(MobEffectCategory.HARMFUL, 0x7DD3FC)
    );
    public static final Holder<MobEffect> CUT_RUBY = MOB_EFFECTS.register("cut_diamond",
            () -> new RuinEffect(MobEffectCategory.HARMFUL, 0x0000FF) // Light ice blue color
    );
}
