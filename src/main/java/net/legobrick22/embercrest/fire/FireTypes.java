package net.legobrick22.embercrest.fire;

import it.crystalnest.prometheus.api.Fire;
import it.crystalnest.prometheus.api.FireManager;
import net.legobrick22.embercrest.Embercrest;
import net.minecraft.resources.Identifier;



public final class FireTypes {

    public static final Identifier RUIN_FIRE_TYPE = Identifier.fromNamespaceAndPath(Embercrest.MOD_ID, "ruin");

    public static void init() {
        Fire.Builder fireBuilder = FireManager.fireBuilder(RUIN_FIRE_TYPE).setDefaultComponents().setLight(10).setDamage(3).removeComponents(
                Fire.Component.FIRE_CHARGE_ITEM,
                Fire.Component.TORCH_BLOCK,
                Fire.Component.TORCH_ITEM,
                Fire.Component.FLAME_PARTICLE

                );        FireManager.registerFire(fireBuilder.build());

    }
}