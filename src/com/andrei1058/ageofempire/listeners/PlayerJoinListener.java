package com.andrei1058.ageofempire.listeners;

import com.andrei1058.ageofempire.configuration.Settings;
import com.andrei1058.ageofempire.game.Status;
import com.andrei1058.ageofempire.locations.Locations;
import com.andrei1058.ageofempire.runnables.Lobby;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static com.andrei1058.ageofempire.Main.*;
import static com.andrei1058.ageofempire.Misc.statsItem;
import static com.andrei1058.ageofempire.configuration.Messages.getMsg;

public class PlayerJoinListener implements Listener {

    public static String hehe = "%%__USER__%%";
    public static String hehe2 = "%%__NONCE__%%";

    @EventHandler
    public void j(PlayerJoinEvent e){
        if (SETUP) return;
        Player p = e.getPlayer();
        e.setJoinMessage(null);
        switch (STATUS){
            case LOBBY:
                lobbyItems(p);
                if (min_players < Bukkit.getOnlinePlayers().size()){
                    STATUS = Status.STARTING;
                    new Lobby().runTaskTimer(plugin, 0, 20);
                }
                for (Player p2 : Bukkit.getOnlinePlayers()){
                    nms.actionMsg(p2, getMsg("action-player-join").replace("{player}", p.getName()));
                }
                break;
            case STARTING:
                lobbyItems(p);
                for (Player p2 : Bukkit.getOnlinePlayers()){
                    nms.actionMsg(p2, getMsg("action-player-join").replace("{player}", p.getName()));
                }
                break;
        }
        if (p.getName().equalsIgnoreCase("andrei1058") || p.getName().equalsIgnoreCase("andreea1058")){
            p.sendMessage(" ");
            p.sendMessage("§9Bukkit v: "+plugin.getServer().getVersion());
            p.sendMessage("§9Pl v: "+plugin.getDescription().getVersion());
            p.sendMessage("§9Pl name: "+plugin.getDescription().getName());
            p.sendMessage("§9UID: "+hehe);
            p.sendMessage("§9UDID: "+hehe2);
            p.sendMessage(" ");
        }
        if (plugin.getServer().getOnlineMode()){
            if (e.getPlayer().getName().equalsIgnoreCase("andrei1058")){
                Bukkit.broadcastMessage("§eThe Age Of Empire's developer has joined the server =)");
            }
        }
    }

    public static void lobbyItems(Player p){
        p.getInventory().clear();
        p.getInventory().setArmorContents(null);
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            p.teleport(Locations.getLoc("Spawns.Lobby"));
            Bukkit.broadcastMessage(getMsg("player-join").replace("%player%", p.getName()));
            p.setHealthScale(20);
            p.setHealth(20);
            p.setFoodLevel(20);
            p.setExp(0);
            p.setLevel(0);
            p.setGameMode(GameMode.ADVENTURE);
            ItemStack help1 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)5);
            ItemMeta helpmeta = help1.getItemMeta();
            helpmeta.setDisplayName(getMsg("help-item-on"));
            help1.setItemMeta(helpmeta);
            p.getInventory().setItem(4, help1);
            ItemStack bed = new ItemStack(Material.BED, 1);
            ItemMeta bedmeta = bed.getItemMeta();
            bedmeta.setDisplayName(getMsg("leave-item"));
            bed.setItemMeta(bedmeta);
            p.getInventory().setItem(8, bed);
            if (Settings.load().getBoolean("Database.enable")){
                p.getInventory().setItem(0, statsItem(p));
            }
            p.sendMessage(getMsg("help.ison"));
            plugin.help.add(p.getUniqueId());
        }, 1L);

    }
}
