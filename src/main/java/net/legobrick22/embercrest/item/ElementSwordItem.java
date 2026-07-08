package net.legobrick22.embercrest.item;

import it.crystalnest.prometheus.api.FireManager;
import net.legobrick22.embercrest.block.custom.RuinFireBlock;
import net.legobrick22.embercrest.data.ModDataComponents;
import net.legobrick22.embercrest.registry.ModComponentTypes;
import net.legobrick22.embercrest.registry.ModEffects;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.component.CustomModelData;
import net.minecraft.world.item.component.KineticWeapon;
import net.minecraft.world.item.component.LodestoneTracker;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.threetag.palladium.power.SuperpowerUtil;
import net.threetag.palladium.registry.PalladiumRegistryKeys;
import org.jspecify.annotations.Nullable;


import java.util.Objects;
import java.util.function.Supplier;

import static com.ibm.icu.impl.ValidIdentifiers.Datatype.t;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static net.legobrick22.embercrest.fire.FireTypes.RUIN_FIRE_TYPE;

public class ElementSwordItem extends Item {

    public ElementSwordItem(Properties properties) {
        super(properties.sword(ToolMaterial.DIAMOND, 5.0f, -2.4f)
        );
    }
    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        var selection = NeoForgeRegistries.ATTACHMENT_TYPES.getValue(Identifier.fromNamespaceAndPath("embercrest", "contender_charge_selected"));
        int slotIndex = player.getInventory().findSlotMatchingItem(new ItemStack(ModItems.ELEMENT_SWORD.get()));

       if (Objects.equals(player.getData(selection), 1)){


        ItemStack elementSword = player.getInventory().getItem(slotIndex);
        elementSword.set(DataComponents.MAX_DAMAGE, 20);}
        return null;
    }

    @Override
    public void hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        var attachment = NeoForgeRegistries.ATTACHMENT_TYPES.getValue(Identifier.fromNamespaceAndPath("embercrest", "contender_charge"));
        Level level = target.level();

        if (Objects.equals(attacker.getData(attachment), 1)) {
            FireManager.setOnFire(target, 12, FireManager.DEFAULT_FIRE_TYPE);
        }
        else {
            if (Objects.equals(attacker.getData(attachment), 2)) {
                target.setTicksFrozen(2400);
            }
            else {
                if (Objects.equals(attacker.getData(attachment), 6)) {
                    FireManager.setOnFire(target, 12, RUIN_FIRE_TYPE);
                }
            else {
                if (Objects.equals(attacker.getData(attachment), 4)) {
                    if (!level.isClientSide()) {
                        // Get the position of the attacked entity
                        double x = target.getX();
                        double y = target.getY();
                        double z = target.getZ();

                        // Trigger a standard explosion (Level, Exploder, x, y, z, Power, Causes Fire, Block Interaction)
                        level.explode(target, x, y, z, 4.0F, true, Level.ExplosionInteraction.MOB);

                    }                                target.addEffect(new MobEffectInstance(ModEffects.DECAY_CHARGE, 120, 0, false, false));

                }
                else {
                    if (Objects.equals(attacker.getData(attachment), 5)) {
                        target.addEffect(new MobEffectInstance(ModEffects.VOID_CHARGE, 120, 0, false, false));
                        attacker.addEffect(new MobEffectInstance(ModEffects.SOUL_HARVEST, 180, 0, false, false));
                        if (attacker.getAbsorptionAmount() < 4){
                            attacker.setAbsorptionAmount(attacker.getAbsorptionAmount()+1);}
                        if (attacker.getHealth() < attacker.getMaxHealth()/2 && target.getHealth() > 3*attacker.getMaxHealth()/4) {
                            attacker.heal(2);
                        }
                        }


                    else {
                        if (Objects.equals(attacker.getData(attachment), 3)) {
                            AttachmentType<Integer> surge = (AttachmentType<Integer>) NeoForgeRegistries.ATTACHMENT_TYPES.getValue(Identifier.fromNamespaceAndPath("embercrest", "surge"));
                            if (target.getData(surge) != null) {
                                int currentsurge = target.getData(surge);
                                target.setData(surge, currentsurge + 60);
                                SuperpowerUtil.addSuperpower(target, target.registryAccess().lookupOrThrow(PalladiumRegistryKeys.POWER).get(Identifier.fromNamespaceAndPath("embercrest", "surge")).get());
                            } else {
                                target.setData(surge, 60);
                                SuperpowerUtil.addSuperpower(target, target.registryAccess().lookupOrThrow(PalladiumRegistryKeys.POWER).get(Identifier.fromNamespaceAndPath("embercrest", "surge")).get());
                            }
                        }

                    }

            super.postHurtEnemy(stack, target, attacker);
        }

        ///public void inventoryTick(ItemStack itemStack, ServerLevel level, Entity owner, @Nullable EquipmentSlot slot) {

        ///     if (!level.isClientSide() && owner instanceof Player player) {

        ///      var attachment = NeoForgeRegistries.ATTACHMENT_TYPES.getValue(Identifier.fromNamespaceAndPath("embercrest", "contender_charge"));
        ///   float attachment_float = ((Number) player.getData(attachment)).floatValue();

        ///itemStack.set(DataComponents.CUSTOM_MODEL_DATA, new CustomModelData());

        /// }


        ///  }

    }
}}}}
