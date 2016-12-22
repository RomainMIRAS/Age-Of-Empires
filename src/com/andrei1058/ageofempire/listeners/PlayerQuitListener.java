package com.andrei1058.ageofempire.listeners;

import com.andrei1058.ageofempire.configuration.Settings;
import com.andrei1058.ageofempire.game.Action;
import com.andrei1058.ageofempire.game.Status;
import com.andrei1058.ageofempire.game.Titles;
import com.andrei1058.ageofempire.runnables.Restart;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import static com.andrei1058.ageofempire.Main.*;
import static com.andrei1058.ageofempire.configuration.Messages.getMsg;
import static com.andrei1058.ageofempire.game.Buildings.construct_in_inv;
import static com.andrei1058.ageofempire.game.Buildings.forge;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void q(PlayerQuitEvent e){
        if (STATUS == Status.LOBBY || STATUS == Status.STARTING){
            for (Player p2 : Bukkit.getOnlinePlayers()){
                Action.actionMsg(p2, getMsg("action-player-left").replace("{player}", e.getPlayer().getName()));
            }
        }
        Player p = e.getPlayer();
        if (SETUP) return;
        e.setQuitMessage(null);
        players.remove(p.getUniqueId());
        bluePlayers.remove(p.getUniqueId());
        greenPlayers.remove(p.getUniqueId());
        yellowPlayers.remove(p.getUniqueId());
        redPlayers.remove(p.getUniqueId());
        construct_in_inv.remove(p.getUniqueId());
        help.remove(p.getUniqueId());
        checkWinner();
    }

    public static void checkWinner(){
        if (STATUS == Status.LOBBY || STATUS == Status.STARTING) return;
        Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
            @Override
            public void run() {
                if (!bluePlayers.isEmpty() && greenPlayers.isEmpty() && redPlayers.isEmpty() && yellowPlayers.isEmpty()){
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        Titles.sendFullTitle(p, 0, 100, 0, getMsg("victory.blue"), "");
                    }
                    stopserver();
                } else if (redPlayers.isEmpty() && !greenPlayers.isEmpty() && bluePlayers.isEmpty() && yellowPlayers.isEmpty()) {
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        Titles.sendFullTitle(p, 0, 100, 0, getMsg("victory.green"), "");
                    }
                    stopserver();
                } else if (bluePlayers.isEmpty() && greenPlayers.isEmpty() && !redPlayers.isEmpty() && yellowPlayers.isEmpty()) {
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        Titles.sendFullTitle(p, 0, 100, 0, getMsg("victory.red"), "");
                    }
                    stopserver();
                } else if (bluePlayers.isEmpty() && greenPlayers.isEmpty() && redPlayers.isEmpty() && !yellowPlayers.isEmpty()) {
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        Titles.sendFullTitle(p, 0, 100, 0, getMsg("victory.yellow"), "");
                    }
                    stopserver();
                } else {
                    if (Bukkit.getOnlinePlayers().isEmpty())
                        stopserver();
                }
            }
        }, 5L);
    }

    private static void stopserver(){
        new Restart().runTaskTimer(plugin, 0, 20);
        STATUS = Status.RESTARTING;
    }
}
