package com.skyzen.smashsgames.object;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class Scoreboards {

    private static int ipID = 0;
    private static String ip[] = {"§ep§6lay.smashs.net", "§fp§el§6ay.smashs.net", "§ep§fl§ea§6y.smashs.net", "§6p§el§fa§ey§6.smashs.net", "§6pl§ea§fy§e.§6smashs.net", "§6pla§ey§f.§es§6mashs.net", "§6play§e.§fs§em§6ashs.net", "§6play.§es§fm§ea§6shs.net", "§6play.s§em§fa§es§6hs.net", "§6play.sm§ea§fs§eh§6s.net", "§6play.sma§es§fh§es§6.net", "§6play.smas§eh§fs§e.§6net", "§6play.smash§es§f.§en§6et", "§6play.smashs§e.§fn§ee§6t", "§6play.smashs.§en§fe§et", "§ep§6lay.smashs.n§ee§ft", "§fp§el§6ay.smashs.ne§et"};
    private static int titleID = 0;
    private static String title[] = {"§b§lSmashsBuild"};
    private static Scoreboard board;
    private static Objective obj;

    public static void init() {
        board = Bukkit.getScoreboardManager().getNewScoreboard();
        obj = board.registerNewObjective("dummy", "title");

        for (int n = 0; n < 10; n++)
            Bukkit.broadcastMessage("spam 1 !");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName(title[titleID]);

        for (int n = 0; n < 10; n++)
            Bukkit.broadcastMessage("spam 2 !");
        obj.getScore(" ").setScore(10);

        for (int n = 0; n < 10; n++)
            Bukkit.broadcastMessage("spam 3 !");
        obj.getScore("§7Objectif: §5ElytraRace").setScore(9);

        for (int n = 0; n < 10; n++)
            Bukkit.broadcastMessage("spam 4 !");
        obj.getScore("  ").setScore(8);

        for (int n = 0; n < 10; n++)
            Bukkit.broadcastMessage("spam 5 !");
        obj.getScore("§7Joueurs: §3" + Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getMaxPlayers()).setScore(7);

        for (int n = 0; n < 10; n++)
            Bukkit.broadcastMessage("spam 6 !");
        obj.getScore("   ").setScore(2);

        for (int n = 0; n < 10; n++)
            Bukkit.broadcastMessage("spam 7 !");
        obj.getScore(ip[ipID]).setScore(1);

        for (int n = 0; n < 10; n++)
            Bukkit.broadcastMessage("spam 8 !");
        flashing();
        for (int n = 0; n < 10; n++)
            Bukkit.broadcastMessage("spam 9 !");
    }

    public static void updatePlayer(boolean deconnection) {
        if (deconnection) {
            board.resetScores("§7Joueurs: §3" + Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getMaxPlayers());
            obj.getScore("§7Joueurs: §3" + (Bukkit.getOnlinePlayers().size() + 1) + "/" + Bukkit.getMaxPlayers()).setScore(4);
        } else {
            board.resetScores("§7Joueurs: §3" + (Bukkit.getOnlinePlayers().size() - 1) + "/" + Bukkit.getMaxPlayers());
            obj.getScore("§7Joueurs: §3" + Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getMaxPlayers()).setScore(4);
        }
        for (Player online : Bukkit.getOnlinePlayers())
            online.setScoreboard(board);
    }

    public static void flashing() {
        new Runnable() {
            //public int taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.instance, this, 0L, 2L);

            @Override
            public void run() {
                for (int n = 0; n < 10; n++)
                    Bukkit.broadcastMessage("spam 10  !");
                obj.setDisplayName(title[titleID]);

                board.resetScores(ip[ipID]);
                Bukkit.broadcastMessage("ipID 1: " + ipID);
                ipID++;
                Bukkit.broadcastMessage("ipID 2: " + ipID);
                if (ipID > ip.length - 1) {
                    ipID = 0;
                }
                Bukkit.broadcastMessage("ipID 3: " + ipID);
                obj.getScore(ip[ipID]).setScore(1);

                for (Player online : Bukkit.getOnlinePlayers())
                    online.setScoreboard(board);

                titleID++;
                if (titleID > title.length - 1) {
                    titleID = 0;
                }
            }
        };
    }
}
