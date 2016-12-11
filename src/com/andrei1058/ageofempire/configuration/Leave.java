package com.andrei1058.ageofempire.configuration;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.andrei1058.ageofempire.Main.plugin;

public class Leave implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender s, Command c, String label, String[] args) {
        if(!(s instanceof Player)) return true;
        Player p = (Player) s;
        if(c.getName().equalsIgnoreCase("leave")){
            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeUTF("Connect");
            out.writeUTF(Settings.load().getString("lobby-server"));
            p.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
        }
        return false;
    }
}
