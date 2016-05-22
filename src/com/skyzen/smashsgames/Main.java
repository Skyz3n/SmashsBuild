package com.skyzen.smashsgames;

import com.skyzen.smashsgames.commands.SbReload;
import com.skyzen.smashsgames.event.PlayerListener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static Main instance;

    @Override
    public void onEnable() {
        instance = this;

        // Enregistrement de la config
        getConfig().options().copyDefaults(true);

        // Initialisation des commandes
        getCommand("sb-reload").setExecutor(new SbReload());

        //Initialisation des listeners
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);

        //Support du reload complet du serveur -> a remplacer par /smashsbuild reload
        PlayerListener.scoreboards.updatePlayer(false);

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