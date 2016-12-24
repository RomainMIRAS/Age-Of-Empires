package com.andrei1058.ageofempire.listeners;

import com.andrei1058.ageofempire.game.Status;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import static com.andrei1058.ageofempire.Main.SETUP;
import static com.andrei1058.ageofempire.Main.STATUS;

public class PlayerLoginListener implements Listener{
    @EventHandler
    public void l(PlayerLoginEvent e){
        if (SETUP) return;
        if (!(STATUS == Status.LOBBY || STATUS == Status.STARTING)){
            e.disallow(PlayerLoginEvent.Result.KICK_OTHER, "You can't join right now!");
        }
    }
}
