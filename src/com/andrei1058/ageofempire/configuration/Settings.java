package com.andrei1058.ageofempire.configuration;
import com.andrei1058.ageofempire.game.Status;
import com.andrei1058.ageofempire.locations.Locations;
import com.andrei1058.ageofempire.locations.Region;
import com.andrei1058.ageofempire.nms.RegisterNMS;
import com.andrei1058.ageofempire.nms.VillagerNMS;
import net.minecraft.server.v1_8_R3.EntityVillager;
import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import static com.andrei1058.ageofempire.Main.*;
import static com.andrei1058.ageofempire.configuration.Messages.getMsg;
import static com.andrei1058.ageofempire.configuration.Messages.setupMessages;

public class Settings {

    private static File file = new File("plugins/Age-Of-Empire/config.yml");
    private static YamlConfiguration yml = YamlConfiguration.loadConfiguration(file);

    public static void setupSettings(){
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ArrayList list = new ArrayList();
        yml.addDefault("Setup-Mode", true);
        yml.addDefault("lobby-server", "aoe");
        yml.addDefault("max-in-team", 4);
        yml.addDefault("min-players", 6);
        yml.addDefault("countdowns.lobby", 60);
        yml.addDefault("countdowns.pregame", 20);
        yml.addDefault("countdowns.pvp", 14);
        yml.addDefault("countdowns.assault", 12);
        yml.addDefault("restart-cmd", "restart");
        yml.addDefault("plot-radius.small", 9);
        yml.addDefault("plot-radius.medium", 12);
        yml.addDefault("plot-radius.large", 16);
        yml.addDefault("Arenas", list);
        yml.options().copyDefaults(true);
        try {
            yml.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        setupMessages();
        SETUP = yml.getBoolean("Setup-Mode");
        PREFIX = getMsg("prefix");
        max_in_team = yml.getInt("max-in-team")-1;
        min_players = yml.getInt("min-players")-1;
        lobby_time = yml.getInt("countdowns.lobby");
        pregame_time = yml.getInt("countdowns.pregame");
        if (yml.get("Arenas") != null && !SETUP){
            Random r = new Random();
            int a = yml.getStringList("Arenas").size();
            int mapid = r.nextInt(a);
            choosenMap = yml.getStringList("Arenas").get(mapid);
            Bukkit.createWorld(new WorldCreator(choosenMap));
            Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                @Override
                public void run() {
                    Locations.getLoc("Spawns.Lobby").getWorld().getEntities().forEach(Entity::remove);
                    Bukkit.getWorld(choosenMap).getEntities().forEach(Entity::remove);
                    Bukkit.getWorld(choosenMap).setGameRuleValue("keepInventory", "false");
                    Bukkit.getWorld(choosenMap).setAutoSave(false);
                }
            },100L);
            RegisterNMS.registerEntity("Villager", 120, EntityVillager.class, VillagerNMS.class);
            try {
                blue_large_plots = Locations.load().getConfigurationSection("Plots."+choosenMap+".Blue.Large").getKeys(false).size();
                blue_medium_plots = Locations.load().getConfigurationSection("Plots."+choosenMap+".Blue.Medium").getKeys(false).size();
                blue_small_plots = Locations.load().getConfigurationSection("Plots."+choosenMap+".Blue.Small").getKeys(false).size();

                green_large_plots = Locations.load().getConfigurationSection("Plots."+choosenMap+".Green.Large").getKeys(false).size();
                green_medium_plots = Locations.load().getConfigurationSection("Plots."+choosenMap+".Green.Medium").getKeys(false).size();
                green_small_plots = Locations.load().getConfigurationSection("Plots."+choosenMap+".Green.Small").getKeys(false).size();

                red_large_plots = Locations.load().getConfigurationSection("Plots."+choosenMap+".Red.Large").getKeys(false).size();
                red_medium_plots = Locations.load().getConfigurationSection("Plots."+choosenMap+".Red.Medium").getKeys(false).size();
                red_small_plots = Locations.load().getConfigurationSection("Plots."+choosenMap+".Red.Small").getKeys(false).size();

                yellow_large_plots = Locations.load().getConfigurationSection("Plots."+choosenMap+".Yellow.Large").getKeys(false).size();
                yellow_medium_plots = Locations.load().getConfigurationSection("Plots."+choosenMap+".Yellow.Medium").getKeys(false).size();
                yellow_small_plots = Locations.load().getConfigurationSection("Plots."+choosenMap+".Yellow.Small").getKeys(false).size();
            } catch (NullPointerException e){
                plugin.getLogger().warning("There is a problem with your plots :(");
            }
            Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                @Override
                public void run() {
                    STATUS = Status.LOBBY;
                }
            }, 30L);
        }
        Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
            @Override
            public void run() {
                Region.loadRegions();
            }
        }, 200);
    }

    public static void addMap(String name){
        ArrayList<String> arene = (ArrayList<String>) yml.getStringList("Arenas");
        arene.add(name);
        yml.set("Arenas", arene);
        try {
            yml.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static YamlConfiguration load(){
        return YamlConfiguration.loadConfiguration(file);
    }

    public static void togglesetup(boolean b){
        yml.set("Setup-Mode", b);
        try {
            yml.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
