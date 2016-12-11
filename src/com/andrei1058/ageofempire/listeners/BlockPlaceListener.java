package com.andrei1058.ageofempire.listeners;

import com.andrei1058.ageofempire.locations.Locations;
import com.andrei1058.ageofempire.locations.StructureBuilder;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import static com.andrei1058.ageofempire.Main.SETUP;
import static com.andrei1058.ageofempire.Main.choosenMap;

public class BlockPlaceListener implements Listener {

    @EventHandler
    public void b(BlockPlaceEvent e){
        if (SETUP) return;
        //momentan asa, dupa sunt si cuburi de la shop de aprobat
        if (e.getBlock().getType() == Material.SPRUCE_DOOR){
            StructureBuilder st = new StructureBuilder();
            st.paste(st.load("arrowshop"), Locations.getLoc("Plots."+choosenMap+".Blue.Medium.1"));
        }
        e.setCancelled(true);
    }
}
