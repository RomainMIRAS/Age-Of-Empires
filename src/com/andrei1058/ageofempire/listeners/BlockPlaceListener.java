package com.andrei1058.ageofempire.listeners;

import com.andrei1058.ageofempire.locations.Region;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import static com.andrei1058.ageofempire.Main.*;
import static com.andrei1058.ageofempire.game.Buildings.construct_in_inv;

public class BlockPlaceListener implements Listener {

    @EventHandler
    public void b(BlockPlaceEvent e){
        if (SETUP) return;
        //momentan asa, dupa sunt si cuburi de la shop de aprobat
        if (e.getBlock().getType() == Material.SPRUCE_DOOR){
            if (construct_in_inv.containsKey(e.getPlayer().getUniqueId())){
                Region.check(e.getBlock().getLocation(), e.getPlayer().getUniqueId());
            }
        }
        e.setCancelled(true);
    }
}
