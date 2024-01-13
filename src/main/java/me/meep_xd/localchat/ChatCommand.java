package me.meep_xd.localchat;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChatCommand implements CommandExecutor {

    public ChatCommand(LocalChat plugin) {
        this.plugin = plugin;
    }

    public LocalChat plugin;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (label.equalsIgnoreCase("localchat") && args.length == 2) {
                if (args[0].equalsIgnoreCase("radius")) {
                    if (!args[1].isEmpty()) {
                        plugin.getConfig().set("radius", Integer.valueOf(args[1]));
                        plugin.saveConfig();
                        sender.sendMessage(ChatColor.GOLD + "[LocalChat]" + ChatColor.GREEN + " Local chat radius set to " + args[1] + " blocks!");
                    }
                }
            }
            else {
                sender.sendMessage(ChatColor.GOLD+"[LocalChat]" + ChatColor.RED + " Usage: /localchat radius <block radius>");
                return true;
            }
        }
        return true;
    }
}
