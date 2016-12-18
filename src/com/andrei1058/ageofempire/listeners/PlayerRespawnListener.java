package com.andrei1058.ageofempire.listeners;

import com.andrei1058.ageofempire.locations.Locations;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import static com.andrei1058.ageofempire.Main.*;
import static com.andrei1058.ageofempire.Misc.leatherArmor;

public class PlayerRespawnListener implements Listener {

    @EventHandler
    public void r(PlayerRespawnEvent e){
        if (SETUP) return;
        Player p = e.getPlayer();
        e.setRespawnLocation(p.getLocation());
        p.setGameMode(GameMode.SPECTATOR);
    }
}
