package net.legobrick22.embercrest.item;

import net.legobrick22.embercrest.entity.ThrownHoly;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.Position;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.entity.projectile.arrow.ThrownTrident;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.KineticWeapon;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.lwjgl.system.ffm.mapping.Mapping;

import java.util.List;
import java.util.Objects;


public class HolySpearItem extends Item implements ProjectileItem {
    public static final int THROW_THRESHOLD_TIME = 10;
    public static final float BASE_DAMAGE = 8.0F;
    public static final float PROJECTILE_SHOOT_POWER = 2.5F;

    public HolySpearItem(Properties properties) {
        super(properties);
    }

    public static ItemAttributeModifiers createAttributes() {
        return ItemAttributeModifiers.builder()
            .add(
                Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_ID, 8.0, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND
            )
            .add(
                Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_ID, -2.9F, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND
            )
            .build();
    }

    public static Tool createToolProperties() {
        return new Tool(List.of(), 1.0F, 2, false);
    }

    @Override
    public ItemUseAnimation getUseAnimation(ItemStack itemStack) {

        return ItemUseAnimation.SPEAR;
    }

    @Override
    public int getUseDuration(ItemStack itemStack, LivingEntity user) {
        return 72000;
    }


    @Override
    public boolean releaseUsing(ItemStack itemStack, Level level, LivingEntity entity, int remainingTime) {
        var attachment = NeoForgeRegistries.ATTACHMENT_TYPES.getValue(Identifier.fromNamespaceAndPath("embercrest", "holy_blade"));

        if (entity instanceof Player player && Objects.equals(entity.getData(attachment), 1)) {
            int timeHeld = this.getUseDuration(itemStack, entity) - remainingTime;
            if (timeHeld < 10) {
                return false;
            } else {

                        Holder<SoundEvent> sound = EnchantmentHelper.pickHighestLevel(itemStack, EnchantmentEffectComponents.TRIDENT_SOUND)
                            .orElse(SoundEvents.TRIDENT_THROW);
                        player.awardStat(Stats.ITEM_USED.get(this));
                        if (level instanceof ServerLevel serverLevel) {
                            itemStack.hurtWithoutBreaking(1, player);
                                ItemStack thrownItemStack = itemStack.consumeAndReturn(1, player);
                                ThrownHoly trident = Projectile.spawnProjectileFromRotation(
                                        ThrownHoly::new, serverLevel, thrownItemStack, player, 0.0F, 2.5F, 1.0F
                                );
                                if (player.hasInfiniteMaterials()) {
                                    trident.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                                }

                                level.playSound(null, trident, sound.value(), SoundSource.PLAYERS, 1.0F, 1.0F);
                                return true;

                        }

                         else {
                            return false;
                        }


            }
        } else {
            return false;
        }
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack itemInHand = player.getItemInHand(hand);
        var attachment = NeoForgeRegistries.ATTACHMENT_TYPES.getValue(Identifier.fromNamespaceAndPath("embercrest", "holy_blade"));

            if (Objects.equals(player.getData(attachment), 1)){
        if (itemInHand.nextDamageWillBreak()) {
            return InteractionResult.FAIL;
        }  else {
            player.startUsingItem(hand);
            return InteractionResult.CONSUME;
        }
    }
       else if (Objects.equals(player.getData(attachment), 0)){
           KineticWeapon kineticWeapon = (KineticWeapon)itemInHand.get(DataComponents.KINETIC_WEAPON);
           if (kineticWeapon != null && !Objects.equals(player.getData(attachment), 1)) {
               player.startUsingItem(hand);
               kineticWeapon.makeSound(player);
               return InteractionResult.CONSUME;
           }
    }
       else return InteractionResult.FAIL;
        return null;
    }

    @Override
    public Projectile asProjectile(Level level, Position position, ItemStack itemStack, Direction direction) {
        ThrownHoly trident = new ThrownHoly(level, position.x(), position.y(), position.z(), itemStack.copyWithCount(1));
        trident.pickup = AbstractArrow.Pickup.ALLOWED;
        return trident;
    }

    @Override
    public boolean canPerformAction(ItemInstance stack, net.neoforged.neoforge.common.ItemAbility itemAbility) {
        return net.neoforged.neoforge.common.ItemAbilities.DEFAULT_TRIDENT_ACTIONS.contains(itemAbility);
    }
}
