import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.properties.select.SelectItemModelProperty;// The select property class
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RegisterSelectItemModelPropertyEvent;

import javax.annotation.Nullable;

public record ContenderCharge() implements SelectItemModelProperty<Rarity> {

    // The object to register that contains the relevant codecs
    public static final SelectItemModelProperty.Type<ContenderCharge, Rarity> TYPE = SelectItemModelProperty.Type.create(
            // The map codec for this property
            MapCodec.unit(new ContenderCharge()),
            // The codec for the object being selected
            // Used to serialize the case entries ("when": <property value>)
            Rarity.CODEC
    );

    @Nullable
    @Override
    public Rarity get(ItemStack stack, @Nullable ClientLevel level, @Nullable LivingEntity entity, int seed, ItemDisplayContext displayContext) {
        // When null, uses the fallback model
        return stack.get(DataComponents.RARITY);
    }

    @Override
    public Codec<Rarity> valueCodec() {
        return null;
    }

    @Override
    public SelectItemModelProperty.Type<ContenderCharge, Rarity> type() {
        return TYPE;
    }
}

// In some event handler class
@SubscribeEvent // on the mod event bus only on the physical client
public static void registerSelectProperties(RegisterSelectItemModelPropertyEvent event) {
    event.register(
            // The name to reference as the type
            Identifier.fromNamespaceAndPath("embercrest", "charge"),
            // The property type
            ContenderCharge.TYPE
    );
}

void main() {
}