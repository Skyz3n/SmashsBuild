package com.skyzen.smashsgames.commands;

import com.skyzen.smashsgames.Main;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetWarp implements CommandExecutor{

    public boolean onCommand(CommandSender sender, Command cmd, String label, String args[]) {

        if (sender instanceof Player) {

            if (args.length >= 1) {

                Location location = ((Player) sender).getLocation();
                Main.instance.getConfig().set("warp." + args[0] + "x", location.getX());
                Main.instance.getConfig().set("warp." + args[0] + "y", location.getY());
                Main.instance.getConfig().set("warp." + args[0] + "z", location.getZ());
                Main.instance.getConfig().set("warp." + args[0] + "pitch", location.getPitch());
                Main.instance.getConfig().set("warp." + args[0] + "yaw", location.getYaw());
                sender.sendMessage(Main.SERVER_PREFIX + ChatColor.YELLOW + "Votre warp: " + args[0] + "à bien été crée");
                return true;

            } else {

                sender.sendMessage(Main.SERVER_PREFIX + ChatColor.RED + "Il faut mettre le nom d'un warp");

            }

        }

        return false;
    }

}
