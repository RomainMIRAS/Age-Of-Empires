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

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void q(PlayerQuitEvent e){
        Player p = e.getPlayer();
        if (SETUP) return;
        e.setQuitMessage(null);
        if (STATUS == Status.STARTING){
            if (Bukkit.getOnlinePlayers().size() < min_players){
                Bukkit.getScheduler().cancelAllTasks();
                STATUS = Status.LOBBY;
                lobby_time = Settings.load().getInt("countdowns.lobby");
            }
        }
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

    }

    public static void checkWinner(){
        boolean win = false;
        if (!bluePlayers.isEmpty() && greenPlayers.isEmpty() && bluePlayers.isEmpty() && yellowPlayers.isEmpty()){
            //blue win
            STATUS = Status.RESTARTING;
        } else if (bluePlayers.isEmpty() && !greenPlayers.isEmpty() && bluePlayers.isEmpty() && yellowPlayers.isEmpty()) {
            //green win
        } else if (bluePlayers.isEmpty() && greenPlayers.isEmpty() && !redPlayers.isEmpty() && yellowPlayers.isEmpty()) {
            //red win
        } else if (bluePlayers.isEmpty() && greenPlayers.isEmpty() && redPlayers.isEmpty() && !yellowPlayers.isEmpty()) {
            //yellow win
        }
        if (win){
            STATUS = Status.RESTARTING;
            new Restart().runTaskTimer(plugin, 0, 20);
        }
    }
}
