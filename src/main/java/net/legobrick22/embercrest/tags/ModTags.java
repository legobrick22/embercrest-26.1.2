package net.legobrick22.embercrest.tags;

import net.legobrick22.embercrest.Embercrest;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {


        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(Identifier.fromNamespaceAndPath(Embercrest.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> TEST_INTERACT = createTag("test_interact");

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(Identifier.fromNamespaceAndPath(Embercrest.MOD_ID, name));

        }
    }
}

