package com.skyzen.smashsgames.object;

import com.skyzen.smashsgames.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class Scoreboards {

    private static int ipID = 0;
    private static String ip[] = {"§bplay.smashs.net", "§9p§blay.smashs.net", "§fp§9l§bay.smashs.net", "§9p§fl§9a§by.smashs.net", "§bp§9l§fa§9y§b.smashs.net", "§bpl§9a§fy§9.§bsmashs.net", "§bpla§9y§f.§9s§bmashs.net", "§bplay§9.§fs§9m§bashs.net", "§bplay.§9s§fm§9a§bshs.net", "§bplay.s§9m§fa§9s§bhs.net", "§bplay.sm§9a§fs§9h§bs.net", "§bplay.sma§9s§fh§9s§b.net", "§bplay.smas§9h§fs§9.§bnet", "§bplay.smash§9s§f.§9n§bet", "§bplay.smashs§9.§fn§9e§bt", "§bplay.smashs.§9n§fe§9t", "§bplay.smashs.n§9e§ft", "§bplay.smashs.ne§9t", "§bplay.smashs.net"};

    public static void build(boolean deconnection) {
        new Runnable() {
            public int taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.instance, this, 0L, 5L);

            @Override
            public void run() {
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
        };
    }
}
