package com.andrei1058.ageofempire.listeners;

import com.andrei1058.ageofempire.game.Status;
import com.andrei1058.ageofempire.locations.BuildSchematic;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import java.util.UUID;

import static com.andrei1058.ageofempire.Main.*;
import static com.andrei1058.ageofempire.configuration.Messages.getMsg;
import static com.andrei1058.ageofempire.game.Buildings.construct_in_inv;
import static com.andrei1058.ageofempire.game.Buildings.forge;

public class ItemDropListener implements Listener {

    @EventHandler
    public void d(PlayerDropItemEvent e){
        if (SETUP) return;
        if (STATUS != Status.PLAYING){
            e.setCancelled(true);
        }
        if (e.getItemDrop().getItemStack().getType() == Material.SPRUCE_DOOR_ITEM){
            if (construct_in_inv.containsKey(e.getPlayer().getUniqueId())){
                switch (construct_in_inv.get(e.getPlayer().getUniqueId())){
                    case forge:
                        if (bluePlayers.contains(e.getPlayer().getUniqueId())){
                            for (UUID u : bluePlayers){
                                Bukkit.getPlayer(u).sendMessage(getMsg("build-canceled").replace("{player}", e.getPlayer().getName()).replace("{building}", construct_in_inv.get(e.getPlayer().getUniqueId())));
                            }
                        } else if (greenPlayers.contains(e.getPlayer().getUniqueId())){
                            for (UUID u : greenPlayers){
                                Bukkit.getPlayer(u).sendMessage(getMsg("build-canceled").replace("{player}", e.getPlayer().getName()).replace("{building}", construct_in_inv.get(e.getPlayer().getUniqueId())));
                            }
                        } else if (yellowPlayers.contains(e.getPlayer().getUniqueId())){
                            for (UUID u : yellowPlayers){
                                Bukkit.getPlayer(u).sendMessage(getMsg("build-canceled").replace("{player}", e.getPlayer().getName()).replace("{building}", construct_in_inv.get(e.getPlayer().getUniqueId())));
                            }
                        } else if (redPlayers.contains(e.getPlayer().getUniqueId())){
                            for (UUID u : redPlayers){
                                Bukkit.getPlayer(u).sendMessage(getMsg("build-canceled").replace("{player}", e.getPlayer().getName()).replace("{building}", construct_in_inv.get(e.getPlayer().getUniqueId())));
                            }
                        }
                        construct_in_inv.remove(e.getPlayer().getUniqueId());
                        if (BuildSchematic.getUUID(e.getPlayer().getUniqueId()) != null) {
                            BuildSchematic.getUUID(e.getPlayer().getUniqueId()).end();
                        }
                        break;
                }
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
