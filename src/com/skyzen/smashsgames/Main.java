package com.skyzen.smashsgames;

import com.skyzen.smashsgames.event.PlayerListener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static Main instance;

    public void onEnable() {
        instance = this;
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);

        getLogger().info("----------------------------------");
        getLogger().info("");
        getLogger().info("Nom du plugin: " + getDescription().getName());
        getLogger().info("Version: " + getDescription().getVersion());
        getLogger().info("");
        getLogger().info("----------------------------------");
    }

}