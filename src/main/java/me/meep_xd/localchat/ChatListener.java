package me.meep_xd.localchat;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {
    public ChatListener(LocalChat plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public LocalChat plugin;

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player sender = event.getPlayer();
        String originalFormat = event.getFormat();
        String message = event.getMessage();
        FileConfiguration config = this.plugin.getConfig();

        // Check if the player has the global chat permission
        boolean hasGlobalPermission = sender.hasPermission("localchat.global");

        // If the player has the global chat permission, send the message globally
        if (hasGlobalPermission) {
            return;
        }

        // Send the message to players in a 50 block radius.
        for (Player receiver : Bukkit.getOnlinePlayers()) {
            // Check if the receiver is within the specified radius.
            if (receiver.getWorld() == sender.getWorld() && receiver.getLocation().distanceSquared(sender.getLocation()) <= config.getInt("radius") * config.getInt("radius")) {
                // Construct the new format preserving original chat formatting.
                String newFormat = originalFormat.replace("%1$s", sender.getDisplayName()).replace("%2$s", message);

                // Send the message to the receiver with the original format.
                receiver.sendMessage(newFormat);
            }
        }

        // Cancel the original chat event to prevent it from being broadcasted globally.
        event.setCancelled(true);
    }
}
