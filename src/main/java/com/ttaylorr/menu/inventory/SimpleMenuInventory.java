package com.ttaylorr.menu.inventory;

import com.google.common.base.Preconditions;
import com.ttaylorr.menu.MenuFrameworkPlugin;
import com.ttaylorr.menu.context.InventoryResponse;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class SimpleMenuInventory implements MenuInventory {
    protected final Player holder;
    protected final Inventory inventory;
    protected final ActionDispatcher dispatcher;

    public SimpleMenuInventory(Player holder, int rows, int cols) {
        this.holder = Preconditions.checkNotNull(holder, "inventory holder");
        this.inventory = Bukkit.createInventory(this.holder, rows * cols);

        this.dispatcher = new ActionDispatcher(this);
        Bukkit.getPluginManager().registerEvents(this.dispatcher, MenuFrameworkPlugin.get());
    }

    @Override
    public Player getHolder() {
        return this.holder;
    }

    @Override
    public Inventory getInventory() {
        return this.inventory;
    }

    public void addItem(ItemStack stack, InventoryResponse response) {
        this.inventory.addItem(stack);
        this.delegate().attachResponder(stack, response);
    }

    public void addItem(ItemStack stack, int pos, InventoryResponse response) {
        this.inventory.setItem(pos, stack);
        this.delegate().attachResponder(stack, response);
    }

    public void addGlobalResponder(InventoryResponse response) {
        this.delegate().attachGlobalResponder(response);
    }

    private ActionDispatcher delegate() {
        return this.dispatcher;
    }
}
