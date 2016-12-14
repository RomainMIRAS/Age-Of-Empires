package com.andrei1058.ageofempire.listeners;

import com.andrei1058.ageofempire.game.Status;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.UUID;

import static com.andrei1058.ageofempire.Main.*;
import static com.andrei1058.ageofempire.configuration.Messages.getMsg;

public class ChatListener implements Listener{

    @EventHandler
    public void c(AsyncPlayerChatEvent e){
        if (SETUP) return;
        e.setCancelled(true);
        if (STATUS == Status.PLAYING) {
            if (bluePlayers.contains(e.getPlayer().getUniqueId())) {
                for (UUID u : bluePlayers) {
                    Bukkit.getPlayer(u).sendMessage(getMsg("chat.game").replace("{player}", "§9"+e.getPlayer().getName()).replace("{message}", e.getMessage()));
                }
            } else if (greenPlayers.contains(e.getPlayer().getUniqueId())) {
                for (UUID u : greenPlayers) {
                    Bukkit.getPlayer(u).sendMessage(getMsg("chat.game").replace("{player}", "§a"+e.getPlayer().getName()).replace("{message}", e.getMessage()));
                }
            } else if (yellowPlayers.contains(e.getPlayer().getUniqueId())) {
                for (UUID u : yellowPlayers) {
                    Bukkit.getPlayer(u).sendMessage(getMsg("chat.game").replace("{player}", "§e"+e.getPlayer().getName()).replace("{message}", e.getMessage()));
                }
            } else if (redPlayers.contains(e.getPlayer().getUniqueId())) {
                for (UUID u : redPlayers) {
                    Bukkit.getPlayer(u).sendMessage(getMsg("chat.game").replace("{player}", "§c"+e.getPlayer().getName()).replace("{message}", e.getMessage()));
                }
            }
        }
    }
}
