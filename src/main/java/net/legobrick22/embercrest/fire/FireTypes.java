package net.legobrick22.embercrest.fire;

import it.crystalnest.cobweb.Constants;
import it.crystalnest.cobweb.api.registry.CobwebEntry;
import it.crystalnest.prometheus.api.Fire;
import it.crystalnest.prometheus.api.FireManager;
import it.crystalnest.prometheus.api.FireRegistrar;
import it.crystalnest.prometheus.api.block.CustomCampfireBlock;
import net.legobrick22.embercrest.Embercrest;
import net.minecraft.resources.Identifier;

import static net.legobrick22.embercrest.Embercrest.MOD_ID;


public final class FireTypes {

    public static final Identifier RUIN_FIRE_TYPE = Identifier.fromNamespaceAndPath(Embercrest.MOD_ID, "ruin");

    public static void init() {
        Fire.Builder fireBuilder = FireManager.fireBuilder(RUIN_FIRE_TYPE).setLight(10).setDamage(3);
        FireManager.registerFire(fireBuilder.build());

        FireManager.fireBuilder(RUIN_FIRE_TYPE).setDefaultComponents()
                .removeComponents(
                        Fire.Component.LANTERN_ITEM,
                        Fire.Component.FIRE_CHARGE_ITEM,
                        Fire.Component.FLAME_PARTICLE,
                        Fire.Component.TORCH_BLOCK,
                        Fire.Component.WALL_TORCH_BLOCK,
                        Fire.Component.LANTERN_BLOCK
                )
                .setComponent(Fire.Component.CAMPFIRE_BLOCK, RUIN_FIRE_TYPE);

        ;

    }
}