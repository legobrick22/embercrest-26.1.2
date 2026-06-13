package net.legobrick22.embercrest.item;

import it.crystalnest.prometheus.api.FireManager;
import net.legobrick22.embercrest.block.custom.RuinFireBlock;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;

import static net.legobrick22.embercrest.fire.FireTypes.RUIN_FIRE_TYPE;

public class ElementSwordItem extends Item {
    public ElementSwordItem(Properties properties) {
        super(properties.sword(ToolMaterial.DIAMOND,6.0f,-2.4f)
        );
    }
    @Override
    public void hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        // Apply frostbite on hit (thematic for Shimmersteel)

                target.forceAddEffect(new MobEffectInstance(MobEffects.GLOWING), attacker);

            target.setTicksFrozen(240);
        FireManager.setOnFire(target, 240, RUIN_FIRE_TYPE);

            super.postHurtEnemy(stack, target, attacker);
    }

}
