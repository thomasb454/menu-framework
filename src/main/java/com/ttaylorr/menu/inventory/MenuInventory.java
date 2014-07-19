package com.ttaylorr.menu.inventory;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public interface MenuInventory {
    public Player getHolder();

    public Inventory getInventory();
}
