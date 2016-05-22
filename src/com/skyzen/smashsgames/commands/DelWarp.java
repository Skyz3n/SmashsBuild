package com.skyzen.smashsgames.commands;

import com.skyzen.smashsgames.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

public class DelWarp implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String args[]) {

        if (args.length >= 1) {

            FileConfiguration config = Main.instance.getConfig();

            if (config.isSet("warp." + args[0])) {

                config.set("warp." + args[0], null);
                Main.instance.saveConfig();
                sender.sendMessage(Main.SERVER_PREFIX + ChatColor.GRAY + "Le warp " + args[0] + " a bien été supprimé !");
                return true;

            } else {

                sender.sendMessage(Main.SERVER_PREFIX + ChatColor.GRAY + "Le warp " + args[0] + " n'existe pas !");

            }


        } else {

            sender.sendMessage(Main.SERVER_PREFIX + ChatColor.GRAY + "Vous devez préciser un warp !");

        }

        return false;

    }
}