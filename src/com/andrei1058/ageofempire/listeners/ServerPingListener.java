package com.andrei1058.ageofempire.listeners;

import com.andrei1058.ageofempire.game.Status;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import static com.andrei1058.ageofempire.Main.SETUP;
import static com.andrei1058.ageofempire.Main.STATUS;
import static com.andrei1058.ageofempire.configuration.Messages.getMsg;

public class ServerPingListener implements Listener {
    @EventHandler
    public void ping(ServerListPingEvent e){
        if (SETUP){
            e.setMotd(getMsg("motd.setup"));
            return;
        }else
        if (STATUS == Status.STARTING ){
            e.setMotd(getMsg("motd.starting"));
        } else if (STATUS == Status.LOBBY){
            e.setMotd(getMsg("motd.lobby"));
        } else if (STATUS == Status.PLAYING || STATUS == Status.PRE_GAME){
            e.setMotd(getMsg("motd.playing"));
        } else if (STATUS == Status.RESTARTING){
            e.setMotd(getMsg("motd.restarting"));
        }
    }
}
