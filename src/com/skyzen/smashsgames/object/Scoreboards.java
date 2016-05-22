package com.skyzen.smashsgames.object;

import com.skyzen.smashsgames.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class Scoreboards {

    private static String ip[] = {"§ep§6lay.smashs.net", "§fp§el§6ay.smashs.net", "§ep§fl§ea§6y.smashs.net", "§6p§el§fa§ey§6.smashs.net", "§6pl§ea§fy§e.§6smashs.net", "§6pla§ey§f.§es§6mashs.net", "§6play§e.§fs§em§6ashs.net", "§6play.§es§fm§ea§6shs.net", "§6play.s§em§fa§es§6hs.net", "§6play.sm§ea§fs§eh§6s.net", "§6play.sma§es§fh§es§6.net", "§6play.smas§eh§fs§e.§6net", "§6play.smash§es§f.§en§6et", "§6play.smashs§e.§fn§ee§6t", "§6play.smashs.§en§fe§et", "§ep§6lay.smashs.n§ee§ft", "§fp§el§6ay.smashs.ne§et"};
    private static String title[] = {"§b§lSmashsBuild"};
    private Scoreboard board;
    private Objective obj;

    public Scoreboards() {
        init();
    }

    public void init() {
        this.board = Bukkit.getScoreboardManager().getNewScoreboard();
        this.obj = board.registerNewObjective("dummy", "title");

        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName(title[0]);

        obj.getScore(" ").setScore(10);

        obj.getScore("§7Objectif: §5ElytraRace").setScore(9);

        obj.getScore("  ").setScore(8);

        obj.getScore("§7Joueurs: §3" + Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getMaxPlayers()).setScore(7);

        obj.getScore("   ").setScore(2);

        obj.getScore(ip[0]).setScore(1);

        flashing();
    }

    public void updatePlayer(boolean deconnection) {
        for (int n = 0; n < 10; n++)
            Bukkit.broadcastMessage("spam 10 !");
        if (deconnection) {
            board.resetScores("§7Joueurs: §3" + Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getMaxPlayers());
            obj.getScore("§7Joueurs: §3" + (Bukkit.getOnlinePlayers().size() - 1) + "/" + Bukkit.getMaxPlayers()).setScore(4);
        } else {
            board.resetScores("§7Joueurs: §3" + (Bukkit.getOnlinePlayers().size() - 1) + "/" + Bukkit.getMaxPlayers());
            obj.getScore("§7Joueurs: §3" + Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getMaxPlayers()).setScore(4);
        }
        for (Player online : Bukkit.getOnlinePlayers())
            online.setScoreboard(board);
    }

    public void flashing() {
        new Runnable() {
            public int taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.instance, this, 0L, 2L);
            private int ipID = 0;
            private int titleID = 0;

            @Override
            public void run() {
                obj.setDisplayName(title[titleID]);

                board.resetScores(ip[ipID]);
                ipID++;
                if (ipID > ip.length - 1) {
                    ipID = 0;
                }
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
