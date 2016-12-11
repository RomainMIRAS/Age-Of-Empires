package com.andrei1058.ageofempire.listeners;

import com.andrei1058.ageofempire.game.Status;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import static com.andrei1058.ageofempire.Main.SETUP;
import static com.andrei1058.ageofempire.Main.STATUS;

public class ItemDropListener implements Listener {

    @EventHandler
    public void d(PlayerDropItemEvent e){
        if (SETUP) return;
        if (STATUS != Status.PLAYING){
            e.setCancelled(true);
        }
    }
}
