package com.andrei1058.ageofempire.listeners;

import com.andrei1058.ageofempire.locations.BuildSchematic;
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
                if (BuildSchematic.getUUID().containsKey(e.getPlayer().getUniqueId())){
                    BuildSchematic.getUUID().get(e.getPlayer().getUniqueId()).placed(e.getBlock().getLocation());
                }
            }
            /*Schematic schematic = null;
            try {
                schematic = BuildSchematic.loadSchematic(new File("plugins/Age-Of-Empire/schematics/test.schematic"));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            Location loc = Locations.getLoc("Plots."+choosenMap+".Blue.Small.1");
            BuildSchematic.pasteSchematic(Bukkit.getWorld(choosenMap), loc.add(-8, -1, -8), schematic);*/
        }
        e.setCancelled(true);
    }
}
