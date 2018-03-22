package com.andrei1058.ageofempire.runnables;

import com.andrei1058.ageofempire.configuration.Settings;
import com.andrei1058.ageofempire.game.OreHologram;
import com.andrei1058.ageofempire.game.Scoreboard;
import com.andrei1058.ageofempire.game.Vote;
import com.andrei1058.ageofempire.game.Hologram;
import com.andrei1058.ageofempire.locations.Locations;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Map;

import static com.andrei1058.ageofempire.Main.*;
import static com.andrei1058.ageofempire.commands.Stuck.stuck;
import static com.andrei1058.ageofempire.configuration.Messages.getMsg;

public class Game extends BukkitRunnable {
    public static int secPlayed = 0;
    private static int stonecheck = 3;
    private static int holo = 30;
    public static boolean blue_change_age = false;
    public static boolean green_change_age = false;
    public static boolean yellow_change_age = false;
    public static boolean red_change_age = false;
    @Override
    public void run() {
        secPlayed++;
        if (secPlayed == 2){
            for (Player u : help){
                u.sendMessage(getMsg("help.start-guide"));
                u.sendMessage(getMsg("help.start-buildings"));
                u.sendMessage(getMsg("help.start-resources"));
            }
        }
        if (secPlayed == stonecheck){
            stonecheck+=3;
            if (blue_stonemine)
                blue_stone+=3;

            if (yellow_stonemine)
                yellow_stone+=3;

            if (red_stonemine)
                red_stone+=3;

            if (green_stonemine)
                green_stone+=3;

            if (blue_goldmine)
                for (Player u : bluePlayers){
                    gold.replace(u, gold.get(u)+3);
                }

            if (yellow_goldmine)
                for (Player u : yellowPlayers){
                    gold.replace(u, gold.get(u)+3);
                }

            if (green_goldmine)
                for (Player u : greenPlayers){
                    gold.replace(u, gold.get(u)+3);
                }

            if (red_goldmine)
                for (Player u : redPlayers){
                    gold.replace(u, gold.get(u)+3);
                }

            if (blue_sawmill)
                blue_wood+=3;

            if (green_sawmill)
                green_wood+=3;

            if (yellow_sawmill)
                yellow_wood+=3;

            if (red_sawmill)
                red_wood+=3;
        }
        if (!assualt) {
            pvp_assault -= 1000;
            if (pvp_assault == 0) {
                if (!pvp) {
                    Bukkit.broadcastMessage(getMsg("pvp-on"));
                    pvp = true;
                    pvp_assault = 60000 * Settings.load().getInt("countdowns.assault");
                    for (Player u : players) {
                        u.playSound(u.getLocation(), nms.wolfDeath(), 1, 1);
                        u.getScoreboard().getTeam("pvp_assault").setPrefix(getMsg("scoreboard.3_2"));
                    }
                    for (String st : Locations.load().getConfigurationSection("xp."+choosenMap).getKeys(false)){
                        Block b = Bukkit.getWorld(choosenMap).getBlockAt(Locations.getLoc("xp."+choosenMap+"."+st));
                        b.setType(Material.SEA_LANTERN);
                        xp.add(new Location(Bukkit.getWorld(choosenMap), b.getLocation().getBlockX(), b.getLocation().getBlockY(), b.getLocation().getBlockZ()));
                    }
                } else {
                    if (!assualt) {
                        assualt = true;
                        Bukkit.broadcastMessage(getMsg("assaults-on"));
                        for (Player u : players) {
                            u.getScoreboard().resetScores(getMsg("scoreboard.4"));
                            u.getScoreboard().resetScores(ChatColor.MAGIC.toString()+ChatColor.RESET.toString());
                        }
                    }
                }
            }
        }
        Scoreboard.Refresh();
        try {
            Vote.votes.stream().forEach(Vote::stuff);
            OreHologram.list().stream().forEach(OreHologram::remove);
            if (secPlayed == holo){
                holo +=30;
                Hologram.list().stream().forEach(Hologram::stuff);
            }
            for (Map.Entry<Player, Long> l : stuck.entrySet()){
                if (System.currentTimeMillis()-l.getValue()>=5000){
                    stuck.remove(l.getKey());
                    if (bluePlayers.contains(l.getKey())){
                        l.getKey().teleport(Locations.getLoc("Spawns."+choosenMap+".Blue"));
                    } else if (redPlayers.contains(l.getKey())){
                        l.getKey().teleport(Locations.getLoc("Spawns."+choosenMap+".Red"));
                    } else if (yellowPlayers.contains(l.getKey())){
                        l.getKey().teleport(Locations.getLoc("Spawns."+choosenMap+".Yellow"));
                    } else if (greenPlayers.contains(l.getKey())){
                        l.getKey().teleport(Locations.getLoc("Spawns."+choosenMap+".Green"));
                    }
                }
            }
        } catch (Exception e){
        }
    }
}
