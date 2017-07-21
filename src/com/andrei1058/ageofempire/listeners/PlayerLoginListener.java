package com.andrei1058.ageofempire.listeners;

import com.andrei1058.ageofempire.configuration.Messages;
import com.andrei1058.ageofempire.configuration.Settings;
import com.andrei1058.ageofempire.game.Status;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import static com.andrei1058.ageofempire.Main.*;

public class PlayerLoginListener implements Listener{
    @EventHandler
    public void l(PlayerLoginEvent e){
        if (SETUP) return;
        if (Bukkit.getOnlinePlayers().size() == max_in_team*4){
            if (e.getPlayer().hasPermission("aoe.vipkick")){
                boolean allow = false;
                for (Player p : Bukkit.getOnlinePlayers()){
                    if (!p.hasPermission("ageofempire.vipkick")){
                        allow = true;
                        p.sendMessage(Messages.getMsg("vip-kick"));
                        ByteArrayDataOutput out = ByteStreams.newDataOutput();
                        out.writeUTF("Connect");
                        out.writeUTF(Settings.load().getString("lobby-server"));
                        p.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
                        return;
                    }
                }
                if (!allow){
                    e.disallow(PlayerLoginEvent.Result.KICK_OTHER, "You can't join right now!");
                }
            } else {
                e.disallow(PlayerLoginEvent.Result.KICK_OTHER, "You can't join right now!");
            }
        }
        if (!(STATUS == Status.LOBBY || STATUS == Status.STARTING)){
            e.disallow(PlayerLoginEvent.Result.KICK_OTHER, "You can't join right now!");
        }
    }
}
