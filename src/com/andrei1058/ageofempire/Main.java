package com.andrei1058.ageofempire;

import com.andrei1058.ageofempire.commands.Setup;
import com.andrei1058.ageofempire.commands.Leave;
import com.andrei1058.ageofempire.game.Status;
import com.andrei1058.ageofempire.listeners.*;
import net.minecraft.server.v1_8_R3.Village;
import org.bukkit.Bukkit;
import org.bukkit.entity.Villager;
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
    public static Status STATUS = Status.RESTARTING;
    public static boolean pvp = false, assualt = false;
    public static int max_in_team = 6, min_players = 6;
    public static int lobby_time = 60, restart_time = 11, pregame_time = 20;
    public static int blue_gold = 100, green_gold = 100, yellow_gold = 100, red_gold = 100;
    public static int blue_wood = 100, green_wood = 100, yellow_wood = 100, red_wood = 100;
    public static int blue_stone = 100, green_stone = 100, yellow_stone = 100, red_stone = 100;
    public static int blue_small_plots = 0, green_small_plots = 0, yellow_small_plots = 0, red_small_plots = 0;
    public static int blue_medium_plots = 0, green_medium_plots = 0, yellow_medium_plots = 0, red_medium_plots = 0;
    public static int blue_large_plots = 0, green_large_plots = 0, yellow_large_plots = 0, red_large_plots = 0;
    public static int blue_age = 1, green_age = 1, yellow_age = 1, red_age = 1;
    public static long pvp_assault = 0;
    public static final String blue_team = "Blue", green_team = "Green", yellow_team = "Yellow", red_team = "Red";
    public static Villager blue_villager, green_villager, yellow_villager, red_villager;
    public static boolean blue_stonemine=false, green_stonemine=false, yellow_stonemine =false, red_stonemine=false;
    public static boolean blue_goldmine=false, green_goldmine=false, yellow_goldmine=false, red_goldmine=false;
    public static boolean blue_sawmill=false, green_sawmill=false, yellow_sawmill=false, red_sawmill=false;
    public static Villager blue_forge, green_forge, yellow_forge, red_forge;
    public static Villager blue_smine, green_smine, yellow_smine, red_smine;
    public static Villager blue_gmine, green_gmine, yellow_gmine, red_gmine;
    public static Villager blue_smill, green_smill, yellow_smill, red_smill;

    @Override
    public void onEnable() {
        plugin = this;
        if (!directory.exists()){
            directory.mkdir();
        }
        setupSettings();
        getCommand("setup").setExecutor(new Setup());
        getCommand("leave").setExecutor(new Leave());
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
        pm.registerEvents(new ChatListener(), this);
        pm.registerEvents(new PlayerArmorStandManipulateListener(), this);
        pm.registerEvents(new ServerPingListener(), this);
        pm.registerEvents(new EntityDeathListener(), this);
        pm.registerEvents(new PlayerDeathListener(), this);
        pm.registerEvents(new PlayerRespawnListener(), this);
        pm.registerEvents(new CreatureSpawnListener(), this);
        pm.registerEvents(new PlayerMoveListener(), this);
        plugin.getServer().getMessenger().registerOutgoingPluginChannel(plugin, "BungeeCord");
    }
}
