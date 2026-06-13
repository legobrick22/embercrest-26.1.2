package net.legobrick22.embercrest.event;

import net.legobrick22.embercrest.Embercrest;
import net.minecraft.client.renderer.item.properties.select.MainHand;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityEvent;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.sheep.Sheep;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;
import net.neoforged.neoforge.event.entity.EntityStruckByLightningEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingEquipmentChangeEvent;
import net.neoforged.neoforge.event.entity.living.LivingEvent;

import static net.legobrick22.embercrest.block.ModBlocks.RUIN_CAMPFIRE;

@EventBusSubscriber(modid = Embercrest.MOD_ID)
public class ModEvents {
    @SubscribeEvent
    public static void livingDamage(LivingDamageEvent.Pre event) {
        if(event.getEntity() instanceof Sheep sheep && event.getSource().getDirectEntity() instanceof Player player) {
            if(player.getMainHandItem().getItem() == Items.END_ROD) {
                player.sendSystemMessage(Component.literal(player.getName().getString() + " just hit a sheep with an End Rod. Where did it go?"));
                player.getMainHandItem().shrink(1);
                sheep.addEffect(new MobEffectInstance(MobEffects.POISON, 600, 5));
            }
        }
    }
    @SubscribeEvent
    public static void livingDamage(LivingEvent.LivingJumpEvent event) {
        LivingEntity entity =  event.getEntity();

        if (!entity.level().isClientSide()) {
                entity.getMainHandItem().shrink(1);
                entity.addEffect(new MobEffectInstance(MobEffects.POISON, 600, 5));
            }
        }
    @SubscribeEvent
    public static void livingDamage(EntityStruckByLightningEvent event) {
        Entity entity =  event.getEntity();

        if (entity instanceof LivingEntity) {
            LivingEntity livingentity = (LivingEntity) event.getEntity();
            HumanoidArm mainHand = ((LivingEntity) entity).getMainArm();
            if (mainHand == HumanoidArm.RIGHT){
                livingentity.addEffect(new MobEffectInstance(MobEffects.POISON, 600, 5));

            }
            else {
                livingentity.addEffect(new MobEffectInstance(MobEffects.WITHER, 600, 5));

            }

        }
    }



}
