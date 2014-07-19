package com.ttaylorr.menu.context;

import com.google.common.base.Preconditions;
import com.ttaylorr.menu.inventory.MenuInventory;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryEvent;

public class ActionContext {
    protected final HumanEntity player;
    protected final InventoryEvent event;
    protected final MenuInventory inventory;

    public ActionContext(HumanEntity who, InventoryEvent event, MenuInventory inventory) {
        this.player = who;
        this.event = Preconditions.checkNotNull(event, "inventory event");
        this.inventory = Preconditions.checkNotNull(inventory, "inventory");
    }

    public Player getPlayer() {
        return (this.player instanceof Player) ? (Player) this.player : null;
    }

    public HumanEntity getEntity() {
        return this.player;
    }

    public InventoryEvent getEvent() {
        return this.event;
    }

    public MenuInventory getInventory() {
        return this.inventory;
    }
}
