package com.andrei1058.ageofempire.runnables;

import com.andrei1058.ageofempire.configuration.Settings;
import com.andrei1058.ageofempire.game.Scoreboard;
import com.andrei1058.ageofempire.game.Vote;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

import static com.andrei1058.ageofempire.Main.*;
import static com.andrei1058.ageofempire.configuration.Messages.getMsg;

public class Game extends BukkitRunnable {
    public static int secPlayed = 0;
    @Override
    public void run() {
        secPlayed++;
        pvp_assault-=1000;
        if (secPlayed == 2){
            for (UUID u : help){
                Bukkit.getPlayer(u).sendMessage(getMsg("help.start-guide"));
                Bukkit.getPlayer(u).sendMessage(getMsg("help.start-buildings"));
                Bukkit.getPlayer(u).sendMessage(getMsg("help.start-resources"));
            }
        }
        if (pvp_assault == 0){
            if (!pvp){
                Bukkit.broadcastMessage(getMsg("pvp-on"));
                pvp = true;
                pvp_assault = 60000* Settings.load().getInt("countdowns.assault");
                for (UUID u : players){
                    Bukkit.getPlayer(u).playSound(Bukkit.getPlayer(u).getLocation(), Sound.WOLF_BARK, 1, 1);
                }
            } else {
                if (!assualt){
                    assualt = true;
                    Bukkit.broadcastMessage(getMsg("assaults-on"));
                }
            }
        }
        Scoreboard.Refresh();

        Vote.votes.forEach(Vote::stuff);
    }
}
