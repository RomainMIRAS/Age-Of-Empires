package com.andrei1058.ageofempire.listeners;

import com.andrei1058.ageofempire.game.Status;
import com.andrei1058.ageofempire.locations.Locations;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import static com.andrei1058.ageofempire.Main.SETUP;
import static com.andrei1058.ageofempire.Main.STATUS;

public class PlayerMoveListener implements Listener {

    @EventHandler
    public void m(PlayerMoveEvent e){
        if (SETUP) return;
        if (STATUS != Status.PLAYING){
            if (e.getPlayer().getLocation().getY() < 0){
                e.getPlayer().teleport(Locations.getLoc("Spawns.Lobby"));
            }
        }
    }
}
