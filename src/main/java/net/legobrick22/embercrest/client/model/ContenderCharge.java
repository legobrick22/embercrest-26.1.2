package net.legobrick22.embercrest.client.model;

import com.mojang.serialization.MapCodec;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.properties.numeric.RangeSelectItemModelProperty;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ItemOwner;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RegisterRangeSelectItemModelPropertyEvent;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.jspecify.annotations.Nullable;

public record ContenderCharge() implements RangeSelectItemModelProperty {

    public static final MapCodec<ContenderCharge> MAP_CODEC = MapCodec.unit(new ContenderCharge());

    @Override
    public float get(ItemStack stack, @Nullable ClientLevel level, @Nullable ItemOwner owner, int seed) {
        var selection = NeoForgeRegistries.ATTACHMENT_TYPES.getValue(Identifier.fromNamespaceAndPath("embercrest", "contender_charge"));
       if (owner instanceof Entity entity){
        Object data = entity.getData(selection);
           if (data instanceof Number number) {
               return number.floatValue();
           }
    }
        return 0.0F;
    };



    @Override
    public MapCodec<ContenderCharge> type() {
        return MAP_CODEC;
    }


@SubscribeEvent
public static void registerRangeProperties(RegisterRangeSelectItemModelPropertyEvent event) {
    event.register(
            Identifier.fromNamespaceAndPath("embercrest", "contender_charge"),
            ContenderCharge.MAP_CODEC
    );
}}