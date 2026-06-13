package net.legobrick22.embercrest.entity;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.arrow.ThrownTrident;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TridentItem;
import net.minecraft.world.level.Level;

public class ThrownHolySpear extends ThrownTrident {


    public ThrownHolySpear(EntityType<? extends ThrownTrident> type, Level level) {
        super(type, level);
    }
}
