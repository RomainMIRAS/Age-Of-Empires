package com.andrei1058.ageofempire.listeners;

import com.andrei1058.ageofempire.game.Status;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

import static com.andrei1058.ageofempire.Main.STATUS;
import static com.andrei1058.ageofempire.locations.Locations.getLoc;

public class CreatureSpawnListener implements Listener {
    public void s(CreatureSpawnEvent e){
        if (STATUS != Status.PLAYING){
            return;
        }
        if (e.getLocation().getWorld() == getLoc("Spawns.Lobby").getWorld()) {
            e.setCancelled(true);
        }
    }
}
