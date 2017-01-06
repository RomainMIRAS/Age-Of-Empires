package com.andrei1058.ageofempire.listeners;

import com.andrei1058.ageofempire.game.Status;
import com.andrei1058.ageofempire.game.BuildSchematic;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import java.util.UUID;

import static com.andrei1058.ageofempire.Main.*;
import static com.andrei1058.ageofempire.Misc.slotlocked;
import static com.andrei1058.ageofempire.configuration.Messages.getMsg;
import static com.andrei1058.ageofempire.game.Buildings.construct_in_inv;

public class ItemDropListener implements Listener {

    @EventHandler
    public void d(PlayerDropItemEvent e){
        if (SETUP) return;
        if (STATUS != Status.PLAYING){
            e.setCancelled(true);
        }
        if (e.getItemDrop().getItemStack().getType() == Material.SPRUCE_DOOR_ITEM){
            if (construct_in_inv.containsKey(e.getPlayer().getUniqueId())) {
                e.setCancelled(true);
                e.getItemDrop().remove();
                e.getPlayer().getInventory().setItem(7, slotlocked());
                if (bluePlayers.contains(e.getPlayer().getUniqueId())) {
                    for (UUID u : bluePlayers) {
                        Bukkit.getPlayer(u).sendMessage(getMsg("build-canceled").replace("{player}", e.getPlayer().getName()).replace("{building}", BuildSchematic.getUUID(e.getPlayer().getUniqueId()).chat_build_name));
                    }
                } else if (greenPlayers.contains(e.getPlayer().getUniqueId())) {
                    for (UUID u : greenPlayers) {
                        Bukkit.getPlayer(u).sendMessage(getMsg("build-canceled").replace("{player}", e.getPlayer().getName()).replace("{building}", BuildSchematic.getUUID(e.getPlayer().getUniqueId()).chat_build_name));
                    }
                } else if (yellowPlayers.contains(e.getPlayer().getUniqueId())) {
                    for (UUID u : yellowPlayers) {
                        Bukkit.getPlayer(u).sendMessage(getMsg("build-canceled").replace("{player}", e.getPlayer().getName()).replace("{building}", BuildSchematic.getUUID(e.getPlayer().getUniqueId()).chat_build_name));
                    }
                } else if (redPlayers.contains(e.getPlayer().getUniqueId())) {
                    for (UUID u : redPlayers) {
                        Bukkit.getPlayer(u).sendMessage(getMsg("build-canceled").replace("{player}", e.getPlayer().getName()).replace("{building}", BuildSchematic.getUUID(e.getPlayer().getUniqueId()).chat_build_name));
                    }
                }
                construct_in_inv.remove(e.getPlayer().getUniqueId());
                BuildSchematic.getUUID(e.getPlayer().getUniqueId()).end();
                e.getItemDrop().remove();
            }
        }
        if (e.getItemDrop().getItemStack().getType() == Material.MOB_SPAWNER){
            e.setCancelled(true);
        }
        if (e.getItemDrop().getItemStack().getType() == Material.PAPER){
            e.setCancelled(true);
        }
    }
}
