package com.andrei1058.ageofempire.listeners;

import com.andrei1058.ageofempire.game.Status;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import static com.andrei1058.ageofempire.Main.*;
import static com.andrei1058.ageofempire.configuration.Messages.getMsg;

public class ChatListener implements Listener{

    @EventHandler
    public void c(AsyncPlayerChatEvent e){
        if (SETUP) return;
        if (STATUS == Status.PLAYING) {
            if (e.getMessage().startsWith("!")){
                e.setFormat(getMsg("chat.lobby").replace("{v_prefix}", getPrefix(e.getPlayer())).replace("{v_suffix}", getSuffix(e.getPlayer())).replace("{player}", e.getPlayer().getDisplayName()).replace("{message}", e.getMessage().replaceFirst("!", "")));
            } else {
                e.getRecipients().clear();
                if (bluePlayers.contains(e.getPlayer())) {
                    e.getRecipients().addAll(bluePlayers);
                    e.setFormat(getMsg("chat.game").replace("{v_prefix}", getPrefix(e.getPlayer())).replace("{v_suffix}", getSuffix(e.getPlayer())).replace("{player}", "§9"+e.getPlayer().getDisplayName()).replace("{message}", e.getMessage()));
                } else if (greenPlayers.contains(e.getPlayer())) {
                    e.getRecipients().addAll(greenPlayers);
                    e.setFormat(getMsg("chat.game").replace("{v_prefix}", getPrefix(e.getPlayer())).replace("{v_suffix}", getSuffix(e.getPlayer())).replace("{player}", "§a"+e.getPlayer().getDisplayName()).replace("{message}", e.getMessage()));
                } else if (yellowPlayers.contains(e.getPlayer())) {
                    e.getRecipients().addAll(yellowPlayers);
                    e.setFormat(getMsg("chat.game").replace("{v_prefix}", getPrefix(e.getPlayer())).replace("{v_suffix}", getSuffix(e.getPlayer())).replace("{player}", "§e"+e.getPlayer().getDisplayName()).replace("{message}", e.getMessage()));
                } else if (redPlayers.contains(e.getPlayer())) {
                    e.getRecipients().addAll(redPlayers);
                    e.setFormat(getMsg("chat.game").replace("{v_prefix}", getPrefix(e.getPlayer())).replace("{v_suffix}", getSuffix(e.getPlayer())).replace("{player}", "§c"+e.getPlayer().getDisplayName()).replace("{message}", e.getMessage()));
                }
            }
        } else {
            e.setFormat(getMsg("chat.lobby").replace("{v_prefix}", getPrefix(e.getPlayer())).replace("{v_suffix}", getSuffix(e.getPlayer())).replace("{player}", e.getPlayer().getDisplayName()).replace("{message}", e.getMessage()));
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
