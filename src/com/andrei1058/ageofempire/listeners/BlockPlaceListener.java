package com.andrei1058.ageofempire.listeners;

import com.andrei1058.ageofempire.locations.Locations;
import com.andrei1058.ageofempire.locations.Schematic;
import com.andrei1058.ageofempire.locations.SchematicMethods;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.io.File;
import java.io.IOException;

import static com.andrei1058.ageofempire.Main.SETUP;
import static com.andrei1058.ageofempire.Main.choosenMap;

public class BlockPlaceListener implements Listener {

    @EventHandler
    public void b(BlockPlaceEvent e){
        if (SETUP) return;
        //momentan asa, dupa sunt si cuburi de la shop de aprobat
        if (e.getBlock().getType() == Material.SPRUCE_DOOR){
            Schematic schematic = null;
            try {
                schematic = SchematicMethods.loadSchematic(new File("plugins/Age-Of-Empire/schematics/test.schematic"));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            Location loc = Locations.getLoc("Plots."+choosenMap+".Blue.Small.1");
            SchematicMethods.pasteSchematic(Bukkit.getWorld(choosenMap), loc.add(-8, -1, -8), schematic);
        }
        e.setCancelled(true);
    }
}
