package com.ttaylorr.menu.inventory;

import com.google.common.base.Predicate;
import com.google.common.collect.Maps;
import com.ttaylorr.menu.context.ActionContext;
import com.ttaylorr.menu.context.InventoryResponse;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ActionDispatcher implements Listener {
    protected final MenuInventory inventory;
    protected final Map<ItemStack, InventoryResponse> responders;
    protected final Set<InventoryResponse> globalResponders;

    public ActionDispatcher(MenuInventory inventory) {
        this.inventory = inventory;

        this.responders = new HashMap<>();
        this.globalResponders = new HashSet<>();
    }

    public void attachResponder(ItemStack item, InventoryResponse response) {
        this.responders.put(item, response);
    }

    public void attachGlobalResponder(InventoryResponse response) {
        this.globalResponders.add(response);
    }

    @EventHandler
    public void handleInventoryClick(InventoryClickEvent event) {
        final ItemStack stack = event.getCurrentItem();
        ActionContext context = new ActionContext(event.getWhoClicked(), event, this.inventory);

        Map<ItemStack, InventoryResponse> responders = Maps.filterKeys(this.responders, new Predicate<ItemStack>() {
            @Override
            public boolean apply(ItemStack itemStack) {
                return itemStack.isSimilar(stack);
            }
        });

        for (InventoryResponse responder : responders.values()) {
            responder.respond(context);
        }

        for (InventoryResponse globalResponder : this.globalResponders) {
            globalResponder.respond(context);
        }
    }
}
