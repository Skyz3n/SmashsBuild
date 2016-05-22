package com.skyzen.smashsgames;

import com.skyzen.smashsgames.event.PlayerListener;
import org.bukkit.event.Listener;
import com.skyzen.smashsgames.object.Scoreboards;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static Main instance;

    public void onEnable() {
        getConfig().options().copyDefaults(true);
        this.saveConfig();
        instance = this;

        //Initialisation des listeners
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);

        //Support du reload complet du serveur -> a remplacer par /smashsbuild reload
        PlayerListener.scoreboards.updatePlayer(false);

        getLogger().info("----------------------------------");
        getLogger().info("");
        getLogger().info("Nom du plugin: " + getDescription().getName());
        getLogger().info("Version: " + getDescription().getVersion());
        getLogger().info("");
        getLogger().info("----------------------------------");
    }

}