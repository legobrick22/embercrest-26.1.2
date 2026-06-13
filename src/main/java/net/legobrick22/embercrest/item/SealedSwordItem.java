package net.legobrick22.embercrest.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;

public class SealedSwordItem extends Item {
    public SealedSwordItem(Properties properties) {
        super(properties.sword(ToolMaterial.NETHERITE,6.0f,-2.4f)
        );
    }
    @Override
    public void hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        // Apply frostbite on hit (thematic for Shimmersteel)

                target.forceAddEffect(new MobEffectInstance(MobEffects.GLOWING), attacker);

            target.setRemainingFireTicks(240);
            target.setTicksFrozen(240);


            super.postHurtEnemy(stack, target, attacker);
    }

}
