package com.andrei1058.ageofempire.commands;

import com.andrei1058.ageofempire.configuration.Messages;
import com.andrei1058.ageofempire.game.Status;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

import static com.andrei1058.ageofempire.Main.STATUS;

public class Stuck implements CommandExecutor {

    public static HashMap<Player, Long> stuck = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender s, Command c, String st, String[] args) {
        if (c.getName().equalsIgnoreCase("stuck")){
            if (STATUS != Status.PLAYING){
                s.sendMessage(Messages.getMsg("notInGame"));
                return true;
            }
            if (s instanceof Player){
                Player p = (Player) s;
                if (!stuck.containsKey(p)){
                    stuck.put(p, System.currentTimeMillis());
                    p.sendMessage(Messages.getMsg("stuckTp"));
                }
            }
        }
        return false;
    }
}
