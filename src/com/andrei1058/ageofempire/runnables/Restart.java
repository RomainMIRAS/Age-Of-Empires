package com.andrei1058.ageofempire.runnables;

import com.andrei1058.ageofempire.configuration.Settings;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import static com.andrei1058.ageofempire.Main.plugin;
import static com.andrei1058.ageofempire.Main.restart_time;

public class Restart extends BukkitRunnable {
    @Override
    public void run() {
        if (restart_time != 0) {
            restart_time--;
        }
        if (restart_time == 3){
            for (Player p : Bukkit.getOnlinePlayers()){
                ByteArrayDataOutput out = ByteStreams.newDataOutput();
                out.writeUTF("Connect");
                out.writeUTF(Settings.load().getString("lobby-server"));
                p.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
            }
        }
        if (restart_time == 0){
            cancel();
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), Settings.load().getString("restart-cmd"));
        }
    }
}
