package com.andrei1058.ageofempire.listeners;

import com.andrei1058.ageofempire.game.Status;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import static com.andrei1058.ageofempire.Main.SETUP;
import static com.andrei1058.ageofempire.Main.STATUS;

/**
 * Created by andrei1058 on 05/12/2016.
 */
public class InventoryClickListener implements Listener {

    @EventHandler
    public void i(InventoryClickEvent e){
        if (SETUP) return;
        if (STATUS != Status.PLAYING) {
            e.setCancelled(true);
        }
    }
}
