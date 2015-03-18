package com.connorlinfoot.cratesplus.Commands;

import com.connorlinfoot.cratesplus.CrateHandler;
import com.connorlinfoot.cratesplus.CrateType;
import com.connorlinfoot.cratesplus.CratesPlus;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CrateCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String string, String[] args) {
        if (sender instanceof Player && !sender.hasPermission("cratesplus.admin")) {
            sender.sendMessage(CratesPlus.pluginPrefix + ChatColor.RED + "You do not have the correct permission to run this command");
            return false;
        }

        if (args.length >= 1 && args[0].equalsIgnoreCase("key")) {
            if (args.length != 2) {
                return false;
            }

            Player player = Bukkit.getPlayer(args[1]);
            if (player == null) {
                sender.sendMessage(ChatColor.RED + "The player " + args[1] + " was not found");
                return false;
            }

            CrateHandler.giveCrateKey(player);
            sender.sendMessage(ChatColor.GREEN + "Given " + player.getDisplayName() + ChatColor.RESET + ChatColor.GREEN + " a crate key");
            return true;
        }

        if (args.length >= 1 && args[0].equalsIgnoreCase("crate")) {
            Player player;
            CrateType crateType;
            if (args.length == 1) {
                sender.sendMessage(ChatColor.RED + "Correct Usage: /crate crate <type> [player]");
                return false;
            }

            if (args.length == 3) {
                player = Bukkit.getPlayer(args[2]);
            } else if (sender instanceof Player) {
                player = (Player) sender;
            } else {
                sender.sendMessage(ChatColor.RED + "Correct Usage: /crate crate <type> [player]");
                return false;
            }

            if (player == null) {
                sender.sendMessage(ChatColor.RED + "The player " + args[1] + " was not found");
                return false;
            }

            try {
                crateType = CrateType.valueOf(args[1].toUpperCase());
            } catch (IllegalArgumentException e) {
                sender.sendMessage(ChatColor.RED + "Please specify a valid crate type");
                return false;
            }

            CrateHandler.giveCrate(player, crateType);
            sender.sendMessage(ChatColor.GREEN + "Given " + player.getDisplayName() + ChatColor.RESET + ChatColor.GREEN + " a crate");
            return true;
        }

        // Help Messages
        sender.sendMessage(CratesPlus.pluginPrefix + ChatColor.AQUA + "/crate key <player> - Give player a random crate key");
        sender.sendMessage(CratesPlus.pluginPrefix + ChatColor.AQUA + "/crate crate <type> [player] - Give player a crate to be placed");

        return true;
    }

}