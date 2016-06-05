package com.skyzen.smashsgames.utils;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class ItemModifier {

    public static ItemStack setText(ItemStack item, String name, String... lore) {
        ItemMeta meta = (ItemMeta) item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(Arrays.asList(lore));
        item.setItemMeta(meta);
        return item;
    }
}