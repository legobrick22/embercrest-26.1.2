package net.legobrick22.embercrest.entity;

import net.legobrick22.embercrest.Embercrest;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEntityTypes {



    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(Registries.ENTITY_TYPE,
            Embercrest.MOD_ID);
  ///  public static final EntityType<? extends AbstractArrow>, DeferredHolder<EntityType<?>, EntityType<ThrownHolySpear>> THROWNHOLY= ENTITIES.register("thrown_holy_trident",
     ///       () -> {
        ///        return EntityType.Builder.<ThrownHolySpear>of(
           ///                     ThrownHolySpear::new,
              ///                  MobCategory.MISC)
                 ///       .sized(0.8F, 3.7F)
                    ///    .build(
                       ///         ResourceKey.create(
                          ///              Registries.ENTITY_TYPE,
                             ///           Identifier.fromNamespaceAndPath(
                                ///                Embercrest.MOD_ID,
                                   ///             "testentity")));
        ///    });

    public static void register(IEventBus eventBus) {
        ENTITIES.register(eventBus);



    }
}
