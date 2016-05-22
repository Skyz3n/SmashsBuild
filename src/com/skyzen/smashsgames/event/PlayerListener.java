package com.skyzen.smashsgames.event;

import com.skyzen.smashsgames.object.Scoreboards;
import com.skyzen.smashsgames.utils.ItemModifier;
import com.skyzen.smashsgames.utils.Title;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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

import java.text.SimpleDateFormat;
import java.util.Date;

public class PlayerListener implements Listener {

    @EventHandler
    public void messages(PlayerJoinEvent event) {
        Player p = event.getPlayer();

        Title.sendTitle(p, 20, 50, 20, ChatColor.YELLOW + "SmashsBuild", ChatColor.AQUA + "Amusez-vous bien !");
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
        String time = new SimpleDateFormat("HH:mm").format(new Date());
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

}
