package com.andrei1058.ageofempire.listeners;

import com.andrei1058.ageofempire.game.Action;
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
import static com.andrei1058.ageofempire.configuration.Messages.getMsg;

public class PlayerJoinListener implements Listener {

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
                    Action.actionMsg(p2, getMsg("action-player-join").replace("{player}", p.getName()));
                }
                break;
            case STARTING:
                lobbyItems(p);
                for (Player p2 : Bukkit.getOnlinePlayers()){
                    Action.actionMsg(p2, getMsg("action-player-join").replace("{player}", p.getName()));
                }
                break;
        }
    }

    public static void lobbyItems(Player p){
        Bukkit.broadcastMessage(getMsg("player-join").replace("%player%", p.getName()));
        p.teleport(Locations.getLoc("Spawns.Lobby"));
        p.setHealthScale(20);
        p.setHealth(20);
        p.setFoodLevel(20);
        p.setExp(0);
        p.setLevel(0);
        p.setGameMode(GameMode.ADVENTURE);
        p.getInventory().clear();
        p.getInventory().setArmorContents(null);
        ItemStack help = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)5);
        ItemMeta helpmeta = help.getItemMeta();
        helpmeta.setDisplayName(getMsg("help-item-on"));
        help.setItemMeta(helpmeta);
        p.getInventory().setItem(4, help);
        ItemStack bed = new ItemStack(Material.BED, 1);
        ItemMeta bedmeta = bed.getItemMeta();
        bedmeta.setDisplayName(getMsg("leave-item"));
        bed.setItemMeta(bedmeta);
        p.getInventory().setItem(8, bed);
        p.sendMessage(getMsg("help.ison"));
        plugin.help.add(p.getUniqueId());
    }
}
