package net.legobrick22.embercrest.registry;

import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModComponentTypes {
    public static final DeferredRegister.DataComponents REGISTRAR = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, "embercrest");

    public static final Supplier<DataComponentType<Integer>> CONTENDER_CHARGE = REGISTRAR.registerComponentType(
            "contender_charge",
            builder -> builder
                    .persistent(Codec.INT)
    );

}