package com.skyzen.smashsgames.commands;

import com.skyzen.smashsgames.Main;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class SetWarp implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String args[]) {

        if (sender instanceof Player) {

            if (args.length >= 1) {

                FileConfiguration config = Main.instance.getConfig();

                if (!config.isSet("warp." + args[0])) {

                    Location location = ((Player) sender).getLocation();
                    config.set("warp." + args[0] + ".x", location.getX());
                    config.set("warp." + args[0] + ".y", location.getY());
                    config.set("warp." + args[0] + ".z", location.getZ());
                    config.set("warp." + args[0] + ".pitch", location.getPitch());
                    config.set("warp." + args[0] + ".yaw", location.getYaw());
                    Main.instance.saveConfig();
                    sender.sendMessage(Main.SERVER_PREFIX + ChatColor.GRAY + "Votre warp " + args[0] + " a bien été crée !");
                    return true;

                } else {

                    sender.sendMessage(Main.SERVER_PREFIX + ChatColor.GRAY + "Ce warp existe déjà !");

                }

            } else {

                sender.sendMessage(Main.SERVER_PREFIX + ChatColor.GRAY + "Il faut mettre le nom d'un warp !");

            }

        }

        return false;
    }

}
