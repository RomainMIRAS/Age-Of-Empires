package com.andrei1058.ageofempire.runnables;

import com.andrei1058.ageofempire.Main;
import com.andrei1058.ageofempire.configuration.Settings;
import com.andrei1058.ageofempire.game.Action;
import com.andrei1058.ageofempire.game.Scoreboard;
import com.andrei1058.ageofempire.game.Status;
import com.andrei1058.ageofempire.game.Hologram;
import com.andrei1058.ageofempire.locations.Locations;
import com.andrei1058.ageofempire.nms.VillagerNMS;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import static com.andrei1058.ageofempire.Main.*;
import static com.andrei1058.ageofempire.Misc.forumPaper;
import static com.andrei1058.ageofempire.Misc.leatherArmor;
import static com.andrei1058.ageofempire.Misc.slotlocked;
import static com.andrei1058.ageofempire.configuration.Messages.getMsg;

public class PreGame extends BukkitRunnable {
    @Override
    public void run() {
        if (pregame_time != 0){
            pregame_time--;
        }
        if (min_players >= Bukkit.getOnlinePlayers().size()){
            this.cancel();
            new Restart().runTaskTimer(plugin, 0, 20);
            STATUS = Status.RESTARTING;
        }
        if (pregame_time == 1){
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (!greenPlayers.contains(p.getUniqueId()) && !redPlayers.contains(p.getUniqueId()) &&
                        !greenPlayers.contains(p.getUniqueId()) && !bluePlayers.contains(p.getUniqueId())) {
                    if (Bukkit.getOnlinePlayers().size() >= max_in_team * 3) {
                        if (bluePlayers.size() < max_in_team) {
                            bluePlayers.add(p.getUniqueId());
                        } else if (greenPlayers.size() < max_in_team) {
                            greenPlayers.add(p.getUniqueId());
                        } else if (yellowPlayers.size() < max_in_team) {
                            yellowPlayers.add(p.getUniqueId());
                        } else if (redPlayers.size() < max_in_team) {
                            redPlayers.add(p.getUniqueId());
                        } else {
                            p.kickPlayer("Teams are full");
                        }
                    } else if (Bukkit.getOnlinePlayers().size() >= max_in_team * 2) {
                        if (bluePlayers.size() < max_in_team) {
                            bluePlayers.add(p.getUniqueId());
                        } else if (greenPlayers.size() < max_in_team) {
                            greenPlayers.add(p.getUniqueId());
                        } else if (yellowPlayers.size() < max_in_team) {
                            yellowPlayers.add(p.getUniqueId());
                        } else {
                            p.kickPlayer("Teams are full");
                        }
                    } else if (Bukkit.getOnlinePlayers().size() >= max_in_team) {
                        if (bluePlayers.size() < max_in_team) {
                            bluePlayers.add(p.getUniqueId());
                        } else if (greenPlayers.size() < max_in_team) {
                            greenPlayers.add(p.getUniqueId());
                        } else {
                            p.kickPlayer("Teams are full");
                        }
                    }
                }
            }
        }
        if (pregame_time > 0){
            for (Player p : Bukkit.getOnlinePlayers()){
                Action.actionMsg(p, getMsg("game-start").replace("{time}", String.valueOf(pregame_time)));
            }
        }
        if (pregame_time == 0){
            cancel();
            for (Player p : Bukkit.getOnlinePlayers()) {
                players.add(p.getUniqueId());
                p.getInventory().clear();
                p.setHealth(20);
                p.setFoodLevel(20);
                p.setGameMode(GameMode.SURVIVAL);
                if (bluePlayers.contains(p.getUniqueId())){
                    p.teleport(Locations.getLoc("Spawns."+choosenMap+".Blue"));
                    p.getInventory().setHelmet(leatherArmor(Material.LEATHER_HELMET, Color.BLUE));
                    p.getInventory().setChestplate(leatherArmor(Material.LEATHER_CHESTPLATE, Color.BLUE));
                    p.getInventory().setBoots(leatherArmor(Material.LEATHER_BOOTS, Color.BLUE));
                    p.getInventory().setLeggings(leatherArmor(Material.LEATHER_LEGGINGS, Color.BLUE));
                    p.setDisplayName("§9"+p.getName());
                } else if (greenPlayers.contains(p.getUniqueId())){
                    p.teleport(Locations.getLoc("Spawns."+choosenMap+".Green"));
                    p.getInventory().setHelmet(leatherArmor(Material.LEATHER_HELMET, Color.GREEN));
                    p.getInventory().setChestplate(leatherArmor(Material.LEATHER_CHESTPLATE, Color.GREEN));
                    p.getInventory().setBoots(leatherArmor(Material.LEATHER_BOOTS, Color.GREEN));
                    p.getInventory().setLeggings(leatherArmor(Material.LEATHER_LEGGINGS, Color.GREEN));
                    p.setDisplayName("§a"+p.getDisplayName());
                } else if (yellowPlayers.contains(p.getUniqueId())){
                    p.teleport(Locations.getLoc("Spawns."+choosenMap+".Yellow"));
                    p.getInventory().setHelmet(leatherArmor(Material.LEATHER_HELMET, Color.YELLOW));
                    p.getInventory().setChestplate(leatherArmor(Material.LEATHER_CHESTPLATE, Color.YELLOW));
                    p.getInventory().setBoots(leatherArmor(Material.LEATHER_BOOTS, Color.YELLOW));
                    p.getInventory().setLeggings(leatherArmor(Material.LEATHER_LEGGINGS, Color.YELLOW));
                    p.setDisplayName("§e"+p.getName());
                } else if (redPlayers.contains(p.getUniqueId())){
                    p.teleport(Locations.getLoc("Spawns."+choosenMap+".Red"));
                    p.getInventory().setHelmet(leatherArmor(Material.LEATHER_HELMET, Color.RED));
                    p.getInventory().setChestplate(leatherArmor(Material.LEATHER_CHESTPLATE, Color.RED));
                    p.getInventory().setBoots(leatherArmor(Material.LEATHER_BOOTS, Color.RED));
                    p.getInventory().setLeggings(leatherArmor(Material.LEATHER_LEGGINGS, Color.RED));
                    p.setDisplayName("§c"+p.getName());
                }
                p.getInventory().addItem(new ItemStack(Material.STONE_PICKAXE));
                p.getInventory().addItem(new ItemStack(Material.STONE_AXE));
                p.getInventory().setItem(8, slotlocked());
                p.getInventory().setItem(7, slotlocked());
                p.getInventory().setItem(6, forumPaper());
            }
            STATUS = Status.PLAYING;
            new Game().runTaskTimer(plugin, 0, 20);
            Scoreboard.register();
            if (!bluePlayers.isEmpty()){
                blue_villager = VillagerNMS.spawnVillager(Locations.getLoc("Forums."+choosenMap+".Blue"), 5000);
                new Hologram(Locations.getLoc("Forums."+choosenMap+".Blue").clone().add(0,+1.2,0),
                        Locations.getLoc("Forums."+choosenMap+".Blue").clone(),
                        getMsg("villagers.forum"), getMsg("villagers.buy-buildings"), blue_villager);
            }
            if (!greenPlayers.isEmpty()){
                green_villager = VillagerNMS.spawnVillager(Locations.getLoc("Forums."+choosenMap+".Green"), 5000);
                new Hologram(Locations.getLoc("Forums."+choosenMap+".Green").clone().add(0,+1.2,0),
                        Locations.getLoc("Forums."+choosenMap+".Green").clone(),
                        getMsg("villagers.forum"), getMsg("villagers.buy-buildings"), green_villager);
            }
            if (!yellowPlayers.isEmpty()){
                yellow_villager = VillagerNMS.spawnVillager(Locations.getLoc("Forums."+choosenMap+".Yellow"), 5000);
                new Hologram(Locations.getLoc("Forums."+choosenMap+".Yellow").clone().add(0,+1.2,0),
                        Locations.getLoc("Forums."+choosenMap+".Yellow").clone(),
                        getMsg("villagers.forum"), getMsg("villagers.buy-buildings"), yellow_villager);
            }
            if (!redPlayers.isEmpty()){
                red_villager = VillagerNMS.spawnVillager(Locations.getLoc("Forums."+choosenMap+".Red"), 5000);
                new Hologram(Locations.getLoc("Forums."+choosenMap+".Red").clone().add(0,+1.2,0),
                        Locations.getLoc("Forums."+choosenMap+".Red").clone(),
                        getMsg("villagers.forum"), getMsg("villagers.buy-buildings"), red_villager);
            }
            Main.pvp_assault = 60000*Settings.load().getInt("countdowns.pvp");
        }
    }
}
