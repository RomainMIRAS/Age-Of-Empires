package com.andrei1058.ageofempire.listeners;

import com.andrei1058.ageofempire.game.Hologram;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.UUID;

import static com.andrei1058.ageofempire.Main.*;
import static com.andrei1058.ageofempire.configuration.Messages.getMsg;
import static com.andrei1058.ageofempire.listeners.PlayerQuitListener.checkWinner;

public class EntityDeathListener implements Listener {

    @EventHandler
    public void d(EntityDeathEvent e){
        if (SETUP) return;
        if (e.getEntity() instanceof Villager){
            Villager v = (Villager) e.getEntity();
            String team = "";
            if (bluePlayers.contains(e.getEntity().getKiller().getUniqueId())){
                team = "§9Blue";
            } else if (greenPlayers.contains(e.getEntity().getKiller().getUniqueId())){
                team = "§aGreen";
            } else if (yellowPlayers.contains(e.getEntity().getKiller().getUniqueId())){
                team = "§eYellow";
            } else if (redPlayers.contains(e.getEntity().getKiller().getUniqueId())){
                team = "§cRed";
            }
            if (v == blue_villager){
                Hologram.get(v).remove();
                for (UUID u : bluePlayers){
                    players.remove(u);
                    bluePlayers.remove(u);
                    Bukkit.getPlayer(u).getInventory().clear();
                    Bukkit.getPlayer(u).setGameMode(GameMode.SPECTATOR);
                }
                Bukkit.broadcastMessage(getMsg("base-destroyed.blue").replace("{team}", team));
            } else if (v == green_villager){
                for (UUID u : greenPlayers){
                    players.remove(u);
                    greenPlayers.remove(u);
                    Bukkit.getPlayer(u).getInventory().clear();
                    Bukkit.getPlayer(u).setGameMode(GameMode.SPECTATOR);
                }
                Bukkit.broadcastMessage(getMsg("base-destroyed.green").replace("{team}", team));
            } else if (v == yellow_villager){
                for (UUID u : yellowPlayers){
                    players.remove(u);
                    yellowPlayers.remove(u);
                    Bukkit.getPlayer(u).getInventory().clear();
                    Bukkit.getPlayer(u).setGameMode(GameMode.SPECTATOR);
                }
                Bukkit.broadcastMessage(getMsg("base-destroyed.yellow").replace("{team}", team));
            } else if (v == red_villager){
                for (UUID u : redPlayers){
                    players.remove(u);
                    redPlayers.remove(u);
                    Bukkit.getPlayer(u).getInventory().clear();
                    Bukkit.getPlayer(u).setGameMode(GameMode.SPECTATOR);
                }
                Bukkit.broadcastMessage(getMsg("base-destroyed.red").replace("{team}", team));
            }
            try {
                Hologram.get(v).remove();
            } catch (Exception ex){}
            checkWinner();
        }
    }
}
