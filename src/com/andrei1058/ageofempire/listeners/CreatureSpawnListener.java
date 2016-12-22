package com.andrei1058.ageofempire.listeners;

import com.andrei1058.ageofempire.game.Status;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

import static com.andrei1058.ageofempire.Main.STATUS;

public class CreatureSpawnListener implements Listener {
    public void s(CreatureSpawnEvent e){
        if (STATUS != Status.PLAYING || e.getSpawnReason() == e.getSpawnReason().CUSTOM){
            return;
        }
        e.setCancelled(true);
    }
}
