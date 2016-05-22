package com.skyzen.smashsgames.object;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class Scoreboards {

    private static int ipID = 0;
    private static String ip[] = {"§b§lplay.smashs.net", "§b§lplay.smashs.net", "§b§lplay.smashs.net", "§b§lplay.smashs.net", "§b§lplay.smashs.net", "§b§lplay.smashs.net", "§b§lplay.smashs.net", "§b§lplay.smashs.net", "§b§lplay.smashs.net", "§b§lplay.smashs.net",};

    public static void build(boolean deconnection) {
        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();

        Objective obj = board.registerNewObjective("dummy", "title");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName("§7☢ §b§lSmashsBuild §7☢");

        obj.getScore(" ").setScore(10);

        obj.getScore("§b§lObjectif Principal:").setScore(9);
        obj.getScore("§fCarte de ElytraRace").setScore(8);

        obj.getScore("  ").setScore(7);

        obj.getScore("§b§lJoueurs:").setScore(6);
        if (deconnection)
            obj.getScore("§f" + (Bukkit.getOnlinePlayers().size() - 1) + "/" + Bukkit.getMaxPlayers()).setScore(5);
        else
            obj.getScore("§f" + Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getMaxPlayers()).setScore(4);

        obj.getScore("   ").setScore(2);

        obj.getScore(ip[ipID]).setScore(1);

        for (Player online : Bukkit.getOnlinePlayers())
            online.setScoreboard(board);

        ipID++;
        if (ipID > ip.length - 1) {
            ipID = 0;
        }
    }

}
