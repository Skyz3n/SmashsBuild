package com.skyzen.smashsgames;

import com.skyzen.smashsgames.commands.SbObjectif;
import com.skyzen.smashsgames.commands.Spawn;
import com.skyzen.smashsgames.event.PlayerListener;
import com.skyzen.smashsgames.object.Scoreboards;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static Main instance;
    public static String SERVER_PREFIX = ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "SmashsBuild" + ChatColor.DARK_GRAY + "] " + ChatColor.RESET;

    @Override
    public void onEnable() {
        instance = this;

        // Enregistrement de la config
        getConfig().options().copyDefaults(true);

        // Initialisation des commandes
        getCommand("sb-objectif").setExecutor(new SbObjectif());
        getCommand("spawn").setExecutor(new Spawn());

        //Initialisation des listeners
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);

        Scoreboards.init();

        //Support du reload complet du serveur -> a remplacer par /smashsbuild reload
        Scoreboards.updatePlayer(false);

        getLogger().info("----------------------------------");
        getLogger().info("");
        getLogger().info("Nom du plugin: " + getDescription().getName());
        getLogger().info("Version: " + getDescription().getVersion());
        getLogger().info("Successfull enable");
        getLogger().info("");
        getLogger().info("----------------------------------");
    }

    @Override
    public void onDisable() {
        getLogger().info("----------------------------------");
        getLogger().info("");
        getLogger().info("Nom du plugin: " + getDescription().getName());
        getLogger().info("Version: " + getDescription().getVersion());
        getLogger().info("Successfull disable");
        getLogger().info("");
        getLogger().info("----------------------------------");
    }
}