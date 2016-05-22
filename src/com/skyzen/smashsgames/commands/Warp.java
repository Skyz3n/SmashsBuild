package com.skyzen.smashsgames.commands;

import com.skyzen.smashsgames.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Warp implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String args[]) {

        if (sender instanceof Player) {

            if (args.length >= 1) {

                FileConfiguration config = Main.instance.getConfig();

                if (config.isSet("warp." + args[0])) {

                    double x = config.getDouble("warp." + args[0] + ".x");
                    double y = config.getDouble("warp." + args[0] + ".y");
                    double z = config.getDouble("warp." + args[0] + ".z");
                    float pitch = (float) config.getDouble("warp." + args[0] + ".pitch");
                    float yaw = (float) config.getDouble("warp." + args[0] + ".yaw");
                    ((Player) sender).teleport(new Location(Bukkit.getWorld("world"), x, y, z, yaw, pitch));
                    return true;

                } else {
                    sender.sendMessage(Main.SERVER_PREFIX + ChatColor.GRAY + "Le warp " + args[0] + " n'existe pas !");
                }

            } else {
                sender.sendMessage(Main.SERVER_PREFIX + ChatColor.GRAY + "Il faut indiquer le warp !");
            }

        }

        return false;
    }

}
