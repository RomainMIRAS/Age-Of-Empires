package com.andrei1058.ageofempire.listeners;

import com.andrei1058.ageofempire.game.Status;
import com.andrei1058.ageofempire.locations.Locations;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;

import static com.andrei1058.ageofempire.Main.*;
import static com.andrei1058.ageofempire.configuration.Messages.getMsg;

public class PlayerInteractEntityListener implements Listener {

    @EventHandler
    public void i(PlayerInteractEntityEvent e){
        if (SETUP) return;
        if (STATUS == Status.PLAYING){
            if (e.getRightClicked().getType() == EntityType.VILLAGER){
                e.setCancelled(true);
                if (e.getRightClicked().getCustomName() == null) return;
                if (e.getRightClicked().getCustomName().equalsIgnoreCase(getMsg("villagers.forum"))) {
                    if (e.getRightClicked().getLocation() == Locations.getLoc("Forums." + choosenMap + ".Blue")) {
                        if (bluePlayers.contains(e.getPlayer().getUniqueId())){
                            //open inventory
                            e.getPlayer().openInventory(forum());
                        } else {
                            //can't open this inventory
                        }
                    } else if (e.getRightClicked().getLocation() == Locations.getLoc("Forums." + choosenMap + ".Yellow")) {
                        if (yellowPlayers.contains(e.getPlayer().getUniqueId())){
                            //open inventory
                            e.getPlayer().openInventory(forum());
                        } else {
                            //can't open this inventory
                        }
                    } else if (e.getRightClicked().getLocation() == Locations.getLoc("Forums." + choosenMap + ".Green")) {
                        if (greenPlayers.contains(e.getPlayer().getUniqueId())){
                            //open inventory
                            e.getPlayer().openInventory(forum());
                        } else {
                            //can't open this inventory
                        }
                    } else if (e.getRightClicked().getLocation() == Locations.getLoc("Forums." + choosenMap + ".Red")) {
                        if (redPlayers.contains(e.getPlayer().getUniqueId())){
                            //open inventory
                            e.getPlayer().openInventory(forum());
                        } else {
                            //can't open this inventory
                        }
                    }
                }
            }
        }
    }

    public static Inventory forum(){
        Inventory inv = Bukkit.createInventory(null, 53, "Forum");

        return inv;
    }
}
