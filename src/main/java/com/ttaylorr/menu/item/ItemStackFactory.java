package com.ttaylorr.menu.item;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public final class ItemStackFactory {

    protected Material material;
    protected int count;
    protected byte data;
    protected String name;
    protected List<String> lore = new ArrayList<>();

    public static ItemStackFactory create() {
        return new ItemStackFactory();
    }

    public ItemStackFactory withMaterial(Material material) {
        this.material = material;
        return this;
    }

    public ItemStackFactory withCount(int count) {
        this.count = count;
        return this;
    }

    public ItemStackFactory withData(byte data) {
        this.data = data;
        return this;
    }

    public ItemStackFactory withTitle(String title) {
        this.name = title;
        return this;
    }

    public ItemStackFactory withLore(String... lines) {
        for (String line : lines) {
            this.lore.add(line);
        }
        return this;
    }

    public ItemStack build() {
        ItemStack item = new ItemStack(this.material, this.count, this.data);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(this.name);
        meta.setLore(this.lore);
        item.setItemMeta(meta);

        return item;
    }

}
