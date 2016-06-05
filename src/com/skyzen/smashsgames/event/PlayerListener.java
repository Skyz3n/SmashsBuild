package com.skyzen.smashsgames.event;

import com.skyzen.smashsgames.object.Scoreboards;
import com.skyzen.smashsgames.utils.ItemModifier;
import com.skyzen.smashsgames.utils.Title;
import net.smashs.api.SmashsAPI;
import net.smashs.api.object.concret.Joueur;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PlayerListener implements Listener {

    @EventHandler
    public void messages(PlayerJoinEvent event) {
        final Player p = event.getPlayer();
        final Joueur j = SmashsAPI.instance.getJoueur(p);

        Title.sendTitle(p, 20, 100, 20, ChatColor.YELLOW + "SmashsBuild", ChatColor.AQUA + "Construisez-bien !");
        Title.sendTabTitle(p, "&5|=| &fVous êtes connecté sur &6le serveur Build &5|=|", "&fPlus d'informations sur &6§nhttps://smashs.net/");
        p.setPlayerListName(ChatColor.GRAY + " " + p.getName());
        p.setWalkSpeed(0.25f);

        final PlayerInventory inv = p.getInventory();
        inv.setItem(0, ItemModifier.setText(new ItemStack(Material.WOOD_AXE, 1), org.bukkit.ChatColor.GOLD + "Outil: WorldEdit", org.bukkit.ChatColor.GRAY + "La Hache magique"));
        inv.setItem(1, ItemModifier.setText(new ItemStack(Material.WOOL, 1, (byte) 10), org.bukkit.ChatColor.DARK_PURPLE + "Outil: Laine", org.bukkit.ChatColor.GRAY + "Pour définir vos structures"));

        event.setJoinMessage(p.getDisplayName() + ChatColor.GRAY + " a rejoint le serveur.");

        p.teleport(new Location(Bukkit.getWorld("world"), 0.500, 70.0, 0.500), PlayerTeleportEvent.TeleportCause.PLUGIN);

        Scoreboards.updatePlayer(false);

        j.setDisplayName();

        if (j.getRank().isDisplay())
            event.setJoinMessage(event.getPlayer().getDisplayName() + " a rejoint le serveur.");
        else
            event.setJoinMessage(null);
    }

    @EventHandler
    public void messages(PlayerQuitEvent event) {
        Scoreboards.updatePlayer(true);

        if (SmashsAPI.instance.getJoueur(event.getPlayer()).getRank().isDisplay())
            event.setQuitMessage(event.getPlayer().getDisplayName() + " a quitté le serveur.");
        else
            event.setQuitMessage(null);
    }

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void cancelHunger(FoodLevelChangeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void chat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        e.setFormat(ChatColor.GRAY + time + " " + ChatColor.YELLOW + p.getDisplayName() + ChatColor.GRAY + ": " + e.getMessage());
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onPlayerBreakBlock(BlockBreakEvent event) {
        if (!SmashsAPI.instance.getJoueur(event.getPlayer()).hasPermission("block.break")) {
            event.setExpToDrop(0);
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onPlayerPlaceBlock(BlockPlaceEvent event) {
        if (!SmashsAPI.instance.getJoueur(event.getPlayer()).hasPermission("block.place")) {
            event.setBuild(false);
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void cancelDrop(PlayerDropItemEvent event) {
        if (!event.getPlayer().isOp())
            event.setCancelled(true);

    }

    @EventHandler
    public void cancelPickup(PlayerPickupItemEvent event) {
        if (!event.getPlayer().isOp())
            event.setCancelled(true);


    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onRainStart(WeatherChangeEvent event) {
        if (event.isCancelled())
            return;

        event.setCancelled(true);
    }

    @EventHandler
    public void onInventoryEvent(InventoryClickEvent e) {
        if (!((Player) e.getWhoClicked()).isOp()) {
            e.setCancelled(true);
            e.getWhoClicked().closeInventory();
        }
    }

}
