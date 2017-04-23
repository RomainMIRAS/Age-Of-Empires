package com.andrei1058.ageofempire.listeners;

import com.andrei1058.ageofempire.configuration.MySQL;
import com.andrei1058.ageofempire.game.Status;
import com.andrei1058.ageofempire.game.Titles;
import com.andrei1058.ageofempire.runnables.Restart;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;
import java.util.UUID;

import static com.andrei1058.ageofempire.Main.*;
import static com.andrei1058.ageofempire.configuration.Messages.getMsg;
import static com.andrei1058.ageofempire.game.Buildings.construct_in_inv;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void q(PlayerQuitEvent e){
        if (STATUS == Status.LOBBY || STATUS == Status.STARTING){
            for (Player p2 : Bukkit.getOnlinePlayers()){
                nms.actionMsg(p2, getMsg("action-player-left").replace("{player}", e.getPlayer().getName()));
            }
        } else if (STATUS == Status.PLAYING){
            if (winner == null){
                int k = 0;
                int d = 0;
                int kd = 0;
                if (kills.containsKey(e.getPlayer())){
                    k = kills.get(e.getPlayer());
                }
                if (deaths.containsKey(e.getPlayer())){
                    d = deaths.get(e.getPlayer());
                }
                if (kingskilled.containsKey(e.getPlayer())){
                    kd = kingskilled.get(e.getPlayer());
                }
                new MySQL().addStats(e.getPlayer().getUniqueId(), 0, 1, k, d, kd);
            } else {
                if (!winner.contains(e.getPlayer().getUniqueId())){
                    int k = 0;
                    int d = 0;
                    int kd = 0;
                    if (kills.containsKey(e.getPlayer())){
                        k = kills.get(e.getPlayer());
                    }
                    if (deaths.containsKey(e.getPlayer())){
                        d = deaths.get(e.getPlayer());
                    }
                    if (kingskilled.containsKey(e.getPlayer())){
                        kd = kingskilled.get(e.getPlayer());
                    }
                    new MySQL().addStats(e.getPlayer().getUniqueId(), 0, 1, k, d, kd);
                }
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
    public static ArrayList winner = null;
    public static void checkWinner(){
        if (winner != null) return;
        if (STATUS == Status.LOBBY || STATUS == Status.STARTING || STATUS == Status.PRE_GAME) return;
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            if (!bluePlayers.isEmpty() && greenPlayers.isEmpty() && redPlayers.isEmpty() && yellowPlayers.isEmpty()){
                for (Player p : Bukkit.getOnlinePlayers()) {
                    Titles.sendFullTitle(p, 0, 100, 0, getMsg("victory.blue"), "");
                }
                for (UUID p : bluePlayers){
                    Player p2 = Bukkit.getPlayer(p);
                    int k = 0;
                    int d = 0;
                    int kd = 0;
                    if (kills.containsKey(p2)){
                        k = kills.get(p2);
                    }
                    if (deaths.containsKey(p2)){
                        d = deaths.get(p2);
                    }
                    if (kingskilled.containsKey(p2)){
                        kd = kingskilled.get(p2);
                    }
                    new MySQL().addStats(p, 1, 1, k, d, kd);
                }
                winner = bluePlayers;
                Bukkit.broadcastMessage(PREFIX+" "+getMsg("victory.blue"));
                stopserver();
            } else if (redPlayers.isEmpty() && !greenPlayers.isEmpty() && bluePlayers.isEmpty() && yellowPlayers.isEmpty()) {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    Titles.sendFullTitle(p, 0, 100, 0, getMsg("victory.green"), "");
                }
                for (UUID p : greenPlayers){
                    Player p2 = Bukkit.getPlayer(p);
                    int k = 0;
                    int d = 0;
                    int kd = 0;
                    if (kills.containsKey(p2)){
                        k = kills.get(p2);
                    }
                    if (deaths.containsKey(p2)){
                        d = deaths.get(p2);
                    }
                    if (kingskilled.containsKey(p2)){
                        kd = kingskilled.get(p2);
                    }
                    new MySQL().addStats(p, 1, 1, k, d, kd);
                }
                winner = greenPlayers;
                Bukkit.broadcastMessage(PREFIX+" "+getMsg("victory.green"));
                stopserver();
            } else if (bluePlayers.isEmpty() && greenPlayers.isEmpty() && !redPlayers.isEmpty() && yellowPlayers.isEmpty()) {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    Titles.sendFullTitle(p, 0, 100, 0, getMsg("victory.red"), "");
                }
                for (UUID p : redPlayers){
                    Player p2 = Bukkit.getPlayer(p);
                    int k = 0;
                    int d = 0;
                    int kd = 0;
                    if (kills.containsKey(p2)){
                        k = kills.get(p2);
                    }
                    if (deaths.containsKey(p2)){
                        d = deaths.get(p2);
                    }
                    if (kingskilled.containsKey(p2)){
                        kd = kingskilled.get(p2);
                    }
                    new MySQL().addStats(p, 1, 1, k, d, kd);
                }
                winner = greenPlayers;
                Bukkit.broadcastMessage(PREFIX+" "+getMsg("victory.red"));
                stopserver();
            } else if (bluePlayers.isEmpty() && greenPlayers.isEmpty() && redPlayers.isEmpty() && !yellowPlayers.isEmpty()) {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    Titles.sendFullTitle(p, 0, 100, 0, getMsg("victory.yellow"), "");
                }
                for (UUID p : yellowPlayers){
                    Player p2 = Bukkit.getPlayer(p);
                    int k = 0;
                    int d = 0;
                    int kd = 0;
                    if (kills.containsKey(p2)){
                        k = kills.get(p2);
                    }
                    if (deaths.containsKey(p2)){
                        d = deaths.get(p2);
                    }
                    if (kingskilled.containsKey(p2)){
                        kd = kingskilled.get(p2);
                    }
                    new MySQL().addStats(p, 1, 1, k, d, kd);
                }
                winner = yellowPlayers;
                Bukkit.broadcastMessage(PREFIX+" "+getMsg("victory.yellow"));
                stopserver();
            } else {
                if (Bukkit.getOnlinePlayers().isEmpty())
                    stopserver();
            }
        }, 5L);
    }

    private static void stopserver(){
        new Restart().runTaskTimer(plugin, 0, 20);
        STATUS = Status.RESTARTING;
    }
}
