package com.andrei1058.ageofempire.commands;

import com.andrei1058.ageofempire.game.Status;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static com.andrei1058.ageofempire.Main.STATUS;
import static com.andrei1058.ageofempire.Main.lobby_time;
import static com.andrei1058.ageofempire.configuration.Messages.getMsg;

public class Start implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender s, Command c, String st, String[] args) {
        if (c.getName().equalsIgnoreCase("start")){
            if (s.hasPermission("aoe.start") || s.hasPermission("aoe.forcestart") || s.hasPermission("aoe.*")){
                if (STATUS == Status.STARTING){
                    if (Bukkit.getOnlinePlayers().size() > 1){
                        lobby_time = 10;
                        return true;
                    }
                }
                s.sendMessage(getMsg("cantDoNow"));
            } else {
                s.sendMessage(getMsg("cantDo"));
            }
        }
        return false;
    }
}
