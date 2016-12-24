package com.andrei1058.ageofempire.listeners;

import com.andrei1058.ageofempire.game.Status;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import static com.andrei1058.ageofempire.Main.STATUS;

public class EntityDamageListener implements Listener {

    @EventHandler
    public void d(EntityDamageEvent e){
        if (STATUS != Status.PLAYING){
            e.setCancelled(true);
        }
    }
}
