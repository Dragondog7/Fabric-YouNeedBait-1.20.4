package net.cookiebrain.youneedbait.util;

import net.cookiebrain.youneedbait.YouNeedBait;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static class Blocks {
        public static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, new Identifier(YouNeedBait.MOD_ID,name));
        }
    }

    public static class Items {
        public static final TagKey<Item> FISH_BAIT_ITEMS =
                createTag("fishing_bait_items");

        public static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, new Identifier(YouNeedBait.MOD_ID,name));
        }
    }
}
