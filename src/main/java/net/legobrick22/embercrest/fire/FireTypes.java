package net.legobrick22.embercrest.fire;

import it.crystalnest.prometheus.api.Fire;
import it.crystalnest.prometheus.api.FireManager;
import net.legobrick22.embercrest.Embercrest;
import net.legobrick22.embercrest.registry.ModEffects;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.NeoForgeRegistries;


public final class FireTypes {

    public static final Identifier RUIN_FIRE_TYPE = Identifier.fromNamespaceAndPath(Embercrest.MOD_ID, "ruin");

    public static void init() {
        Fire.Builder fireBuilder = FireManager.fireBuilder(RUIN_FIRE_TYPE)
                .setBehavior((Entity entity) -> {
                    var doruindamage = false;
                    AttachmentType<Integer> ruin_stacks = (AttachmentType<Integer>) NeoForgeRegistries.ATTACHMENT_TYPES.getValue(Identifier.fromNamespaceAndPath("embercrest", "ruin_stacks"));
                    if (entity.isOnFire() && entity instanceof LivingEntity livingEntity) {
                        MobEffectInstance ruineffect = ((LivingEntity) entity).getEffect(ModEffects.RUIN);
                        if (ruineffect != null) {
                            int ruinstack = ruineffect.getAmplifier();
                            ((LivingEntity) entity).addEffect(new MobEffectInstance(ModEffects.RUIN, 80, ruinstack + 1));
                            if (((LivingEntity) entity).getArmorValue() == 0){
                                doruindamage = true;
                            } else {
                                doruindamage = false;
                            }
                        }
                        else {
                            ((LivingEntity) entity).addEffect(new MobEffectInstance(ModEffects.RUIN, 80, 0));
                        }
                    }



                    return doruindamage;
                })

                .setDefaultComponents().setLight(10).setDamage(4).removeComponents(


                        Fire.Component.FIRE_CHARGE_ITEM,
                        Fire.Component.TORCH_BLOCK,
                        Fire.Component.TORCH_ITEM,
                        Fire.Component.FLAME_PARTICLE

                );

        FireManager.registerFire(fireBuilder.build());

    }
}