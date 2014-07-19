package com.ttaylorr.menu;

import org.bukkit.plugin.java.JavaPlugin;

public class MenuFrameworkPlugin extends JavaPlugin {
    private static MenuFrameworkPlugin _this;

    public static MenuFrameworkPlugin get() {
        return _this;
    }

    @Override
    public void onEnable() {
        _this = this;
    }

    @Override
    public void onDisable() {
        _this = null;
    }
}
