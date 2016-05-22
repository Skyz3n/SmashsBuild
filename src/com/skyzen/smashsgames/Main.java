package com.skyzen.smashsgames;

import com.skyzen.smashsgames.object.Scoreboards;
import com.skyzen.smashsgames.utils.ItemModifier;
import com.skyzen.smashsgames.utils.Title;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Objective;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Main extends JavaPlugin implements Listener {

    public static Main instance;

    public void onEnable() {
        instance = this;
        getServer().getPluginManager().registerEvents(this, this);

        getLogger().info("----------------------------------");
        getLogger().info("");
        getLogger().info("Nom du plugin: " + getDescription().getName());
        getLogger().info("Version: " + getDescription().getVersion());
        getLogger().info("");
        getLogger().info("----------------------------------");
    }

    SimpleDateFormat date = new SimpleDateFormat("HH:mm");

    @EventHandler
    public void messages(PlayerJoinEvent event) {
        Player p = event.getPlayer();

        Title.sendTabTitle(p, "&5|=| &fVous êtes connecté sur &6SmashsBuild &5|=|", "&fPlus d'informations sur &6§nhttp://smashs.net/");
        p.setPlayerListName(ChatColor.GRAY + " " + p.getName());
        p.setWalkSpeed(0.25f);

        PlayerInventory inv = p.getInventory();
        inv.setItem(0, ItemModifier.setText(new ItemStack(Material.WOOD_AXE, 1), org.bukkit.ChatColor.GOLD + "WorldEdit", org.bukkit.ChatColor.GRAY + "La Hache magique"));
        inv.setItem(1, ItemModifier.setText(new ItemStack(Material.WOOL, 1, (byte) 10), org.bukkit.ChatColor.DARK_PURPLE + "Laine d'outil", org.bukkit.ChatColor.GRAY + "Pour définir vos structures"));

        event.setJoinMessage(ChatColor.YELLOW + p.getName() + ChatColor.GRAY + " a rejoint le serveur " + ChatColor.GREEN + "(" + Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getMaxPlayers() + ")");

        Scoreboards.build(false);

    }

    @EventHandler
    public void messages(PlayerQuitEvent event) {
        Player p = event.getPlayer();
        event.setQuitMessage(ChatColor.YELLOW + p.getName() + ChatColor.GRAY + " a quitté le serveur " + ChatColor.GREEN + "(" + (Bukkit.getOnlinePlayers().size() - 1) + "/" + Bukkit.getMaxPlayers() + ")");
        Scoreboards.build(true);
    }

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void cancelHunger(FoodLevelChangeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void chat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        String time = date.format(new Date());
        e.setFormat(ChatColor.GOLD + time + ChatColor.GRAY + " > " + ChatColor.BLUE + p.getName() + ChatColor.GRAY + ": " + e.getMessage());
    }

    @EventHandler
    public void cancelBreak(BlockBreakEvent event) {
        if (!event.getPlayer().isOp()) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void cancelPlace(BlockPlaceEvent event) {
        if (!event.getPlayer().isOp()) {
            event.setCancelled(true);
        }

    }

    int changeText = 0;

    public void changeText(final Objective o, long time, final String... text) {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.instance, new Runnable() {
                    public void run() {
                        if (changeText >= text.length)
                            changeText = 0;
                        o.setDisplayName(text[changeText]);
                        changeText++;
                    }
                }
                , 0, time);
    }

}