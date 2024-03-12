package net.cookiebrain.youneedbait.util;

import net.cookiebrain.youneedbait.YouNeedBait;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static class Blocks {

    }

    public static class Items {
        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, new Identifier(YouNeedBait.MOD_ID,name));
        }
    }
}
