package me.meep_xd.localchat;

import org.bukkit.plugin.java.JavaPlugin;

public final class LocalChat extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();

        // Initialize the ChatListener and register events
        new ChatListener(this);

        // Log a message indicating the plugin has been enabled
        getLogger().info("LocalChat has been enabled!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("LocalChat has been disabled!");
    }
}
