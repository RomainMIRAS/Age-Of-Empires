package com.andrei1058.ageofempire.listeners;

import com.andrei1058.ageofempire.configuration.Settings;
import com.andrei1058.ageofempire.game.Status;
import com.andrei1058.ageofempire.runnables.Restart;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import static com.andrei1058.ageofempire.Main.*;
import static com.andrei1058.ageofempire.game.Buildings.construct_in_inv;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void q(PlayerQuitEvent e){
        Player p = e.getPlayer();
        if (SETUP) return;
        e.setQuitMessage(null);
        if (players.contains(p.getUniqueId())){
            players.remove(p.getUniqueId());
        }
        if (bluePlayers.contains(p.getUniqueId())){
            bluePlayers.remove(p.getUniqueId());
        } else if (greenPlayers.contains(p.getUniqueId())){
            greenPlayers.remove(p.getUniqueId());
        } else if (yellowPlayers.contains(p.getUniqueId())){
            yellowPlayers.remove(p.getUniqueId());
        } else if (redPlayers.contains(p.getUniqueId())){
            redPlayers.remove(p.getUniqueId());
        }
        construct_in_inv.remove(p.getUniqueId());
        checkWinner();
    }

    public static void checkWinner(){
        if (!bluePlayers.isEmpty() && greenPlayers.isEmpty() && bluePlayers.isEmpty() && yellowPlayers.isEmpty()){
            //blue win
            stopserver();
            STATUS = Status.RESTARTING;
        } else if (bluePlayers.isEmpty() && !greenPlayers.isEmpty() && bluePlayers.isEmpty() && yellowPlayers.isEmpty()) {
            //green win
            stopserver();
        } else if (bluePlayers.isEmpty() && greenPlayers.isEmpty() && !redPlayers.isEmpty() && yellowPlayers.isEmpty()) {
            //red win
            stopserver();
        } else if (bluePlayers.isEmpty() && greenPlayers.isEmpty() && redPlayers.isEmpty() && !yellowPlayers.isEmpty()) {
            //yellow win
            stopserver();
        } else if (Bukkit.getOnlinePlayers().isEmpty()){
            if (STATUS == Status.LOBBY || STATUS == Status.STARTING) return;
            stopserver();
        }
    }

    private static void stopserver(){
        new Restart().runTaskTimer(plugin, 0, 20);
        STATUS = Status.RESTARTING;
    }
}
