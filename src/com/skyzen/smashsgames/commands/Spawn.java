package com.skyzen.smashsgames.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Spawn implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String args[]) {

        if (sender instanceof Player) {

            ((Player) sender).teleport(new Location(Bukkit.getWorld("world"), 952.500, 78.0, 456.500));
            return true;

        }
        return false;

    }

}
