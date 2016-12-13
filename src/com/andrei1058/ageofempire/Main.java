package com.andrei1058.ageofempire;

import com.andrei1058.ageofempire.commands.Builds;
import com.andrei1058.ageofempire.commands.Setup;
import com.andrei1058.ageofempire.configuration.Leave;
import com.andrei1058.ageofempire.game.Status;
import com.andrei1058.ageofempire.listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

import static com.andrei1058.ageofempire.configuration.Settings.setupSettings;

public class Main extends JavaPlugin {

    public static Main plugin;
    public static String PREFIX = "§9[§7AOE§9]";
    private static File directory = new File("plugins/Age-Of-Empire");
    public static ArrayList<UUID> bluePlayers = new ArrayList<>();
    public static ArrayList<UUID> greenPlayers = new ArrayList<>();
    public static ArrayList<UUID> yellowPlayers = new ArrayList<>();
    public static ArrayList<UUID> redPlayers = new ArrayList<>();
    public static ArrayList<UUID> help = new ArrayList<>();
    public static ArrayList<UUID> players = new ArrayList<>();
    public static String choosenMap = "";
    public static boolean SETUP = false;
    public static Status STATUS = Status.LOBBY;
    public static boolean pvp = false;
    public static boolean assualt = false;
    public static int max_in_team = 6;
    public static int min_players = 6;
    public static int lobby_time = 60;
    public static int restart_time = 11;
    public static int pregame_time = 20;
    public static int blue_gold = 100;
    public static int green_gold = 100;
    public static int yellow_gold = 100;
    public static int red_gold = 100;
    public static int blue_wood = 150;
    public static int green_wood = 150;
    public static int yellow_wood = 150;
    public static int red_wood = 150;
    public static int blue_stone = 200;
    public static int green_stone = 200;
    public static int yellow_stone = 200;
    public static int red_stone = 200;
    public static int blue_small_plots = 5;
    public static int green_small_plots = 5;
    public static int yellow_small_plots = 5;
    public static int red_small_plots = 5;
    public static int blue_medium_plots = 3;
    public static int green_medium_plots = 3;
    public static int yellow_medium_plots = 3;
    public static int red_medium_plots = 3;
    public static int blue_large_plots = 2;
    public static int green_large_plots = 2;
    public static int yellow_large_plots = 2;
    public static int red_large_plots = 2;
    public static int blue_age = 1;
    public static int green_age = 1;
    public static int yellow_age = 1;
    public static int red_age = 1;
    public static long pvp_assault = 0;

    @Override
    public void onEnable() {
        plugin = this;
        if (!directory.exists()){
            directory.mkdir();
        }
        setupSettings();
        getCommand("setup").setExecutor(new Setup());
        getCommand("leave").setExecutor(new Leave());
        getCommand("build").setExecutor(new Builds());
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PlayerJoinListener(), this);
        pm.registerEvents(new PlayerInteractListener(), this);
        pm.registerEvents(new ItemDropListener(), this);
        pm.registerEvents(new ItemPickUpListener(), this);
        pm.registerEvents(new InventoryClickListener(), this);
        pm.registerEvents(new EntityDamageByEntityListener(), this);
        pm.registerEvents(new FoodLevelChangeListener(), this);
        pm.registerEvents(new EntityDamageListener(), this);
        pm.registerEvents(new PlayerMoveListener(), this);
        pm.registerEvents(new BlockBreakListener(), this);
        pm.registerEvents(new PlayerQuitListener(), this);
        pm.registerEvents(new BlockPlaceListener(), this);
        pm.registerEvents(new ItemConsumeListener(), this);
        pm.registerEvents(new PlayerInteractEntityListener(), this);
        plugin.getServer().getMessenger().registerOutgoingPluginChannel(plugin, "BungeeCord");
    }
}
