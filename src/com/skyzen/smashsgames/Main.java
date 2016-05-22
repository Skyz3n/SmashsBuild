package com.skyzen.smashsgames;

import com.skyzen.smashsgames.event.PlayerListener;
import com.skyzen.smashsgames.object.Scoreboards;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static Main instance;

    public void onEnable() {
        instance = this;

        //Initialisation des listeners
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);

        getLogger().info("----------------------------------");
        getLogger().info("");
        getLogger().info("Nom du plugin: " + getDescription().getName());
        getLogger().info("Version: " + getDescription().getVersion());
        getLogger().info("");
        getLogger().info("----------------------------------");
    }

}