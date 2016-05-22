package com.skyzen.smashsgames.object;

import com.skyzen.smashsgames.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class Scoreboards {

    private static int ipID = 0;
    private static String ip[] = {"§ep§6lay.smashs.net", "§fp§el§6ay.smashs.net", "§ep§fl§ea§6y.smashs.net", "§6p§el§fa§ey§6.smashs.net", "§6pl§ea§fy§e.§6smashs.net", "§6pla§ey§f.§es§6mashs.net", "§6play§e.§fs§em§6ashs.net", "§6play.§es§fm§ea§6shs.net", "§6play.s§em§fa§es§6hs.net", "§6play.sm§ea§fs§eh§6s.net", "§6play.sma§es§fh§es§6.net", "§6play.smas§eh§fs§e.§6net", "§6play.smash§es§f.§en§6et", "§6play.smashs§e.§fn§ee§6t", "§6play.smashs.§en§fe§et", "§ep§6lay.smashs.n§ee§ft", "§fp§el§6ay.smashs.ne§et"};
    private static int titleID = 0;
    private static String title[] = {"§7☢ §b§lSmashsBuild §7☢"};

    public static void build(boolean deconnection) {
        new Runnable() {
            public int taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.instance, this, 0L, 5L);

            @Override
            public void run() {
                Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();

                Objective obj = board.registerNewObjective("dummy", "title");
                obj.setDisplaySlot(DisplaySlot.SIDEBAR);
                obj.setDisplayName(title[titleID]);

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
                titleID++;
                if (titleID > title.length - 1) {
                    titleID = 0;
                }
            }
        };
    }
}
