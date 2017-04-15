package com.andrei1058.ageofempire;

import com.andrei1058.ageofempire.commands.Help;
import com.andrei1058.ageofempire.commands.Setup;
import com.andrei1058.ageofempire.commands.Leave;
import com.andrei1058.ageofempire.game.Status;
import com.andrei1058.ageofempire.listeners.*;
import com.andrei1058.ageofempire.nms.NMS;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Villager;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
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
    public static ArrayList<Location> xp = new ArrayList<>();
    public static String choosenMap = "";
    public static boolean SETUP = false;
    public static Status STATUS = Status.LOBBY;
    public static boolean pvp = false, assualt = false;
    public static int max_in_team = 6, min_players = 6;
    public static int lobby_time = 60, restart_time = 15, pregame_time = 20;
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
    public static boolean blue_xp = false, green_xp = false, yellow_xp = false, red_xp;
    public static Villager blue_forge, green_forge, yellow_forge, red_forge;
    public static Villager blue_smine, green_smine, yellow_smine, red_smine;
    public static Villager blue_gmine, green_gmine, yellow_gmine, red_gmine;
    public static Villager blue_mill, green_mill, yellow_mill, red_mill;
    public static Villager blue_vsawmill, green_vsawmill, yellow_vsawmill, red_vsawmill;
    public static Villager blue_workshop, green_workshop, yellow_workshop, red_workshop;
    public static Villager blue_market, green_market, yellow_market, red_market;
    public static Villager blue_sabotage, green_sabotage, yellow_sabotage, red_sabotage;
    public static Villager blue_kennel, green_kennel, yellow_kennel, red_kennel;
    //age 3
    public static Villager blue_archery, green_archery, yellow_archery, red_archery;
    public static Villager blue_trifarrow, green_trifarrow, yellow_trifarrow, red_trifarrow;
    public static Villager blue_stable, green_stable, yellow_stable, red_stable;
    public static Villager blue_armory, green_armory, yellow_armory, red_armory;
    public static Villager blue_lab, green_lab, yellow_lab, red_lab;
    //age 4
    public static Villager blue_guild, green_guild, yellow_guild, red_guild;
    public static Villager blue_tcenter, green_tcenter, yellow_tcenter, red_tcenter;

    public static ArrayList<Block> placedBlocks = new ArrayList<>();
    public static HashMap<UUID, Integer> gold = new HashMap<>();
    public static ArrayList<UUID> teamchoose = new ArrayList<>();
    public static Chat chat = null;
    public static Boolean vaultHook = false;
    public static Boolean mysql = false;
    public static NMS nms;

    @Override
    public void onEnable() {
        plugin = this;
        if (!directory.exists()){
            directory.mkdir();
        }
        String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
        if (version.equalsIgnoreCase("v1_9_R2")){
            try {
                nms = com.andrei1058.ageofempire.nms.v1_9_R2.Main.class.newInstance();
            } catch (InstantiationException e) {
                this.getLogger().severe("An error occurred!");
                this.setEnabled(false);
                return;
            } catch (IllegalAccessException e) {
                this.getLogger().severe("An error occurred!");
                this.setEnabled(false);
                return;
            }
        } else if(version.equalsIgnoreCase("v1_8_R3")){
            try {
                nms = com.andrei1058.ageofempire.nms.v1_8_R3.Main.class.newInstance();
            } catch (InstantiationException e) {
                this.getLogger().severe("An error occurred!");
                this.setEnabled(false);
                return;
            } catch (IllegalAccessException e) {
                this.getLogger().severe("An error occurred!");
                this.setEnabled(false);
                return;
            }
        } else if(version.equalsIgnoreCase("v1_9_R1")){
            try {
                nms = com.andrei1058.ageofempire.nms.v1_9_R1.Main.class.newInstance();
            } catch (InstantiationException e) {
                this.getLogger().severe("An error occurred!");
                this.setEnabled(false);
                return;
            } catch (IllegalAccessException e) {
                this.getLogger().severe("An error occurred!");
                this.setEnabled(false);
                return;
            }
        } else if(version.equalsIgnoreCase("v1_10_R1")){
            try {
                nms = com.andrei1058.ageofempire.nms.v1_10_R1.Main.class.newInstance();
            } catch (InstantiationException e) {
                this.getLogger().severe("An error occurred!");
                this.setEnabled(false);
                return;
            } catch (IllegalAccessException e) {
                this.getLogger().severe("An error occurred!");
                this.setEnabled(false);
                return;
            }
        } else if(version.equalsIgnoreCase("v1_11_R1")){
            try {
                nms = com.andrei1058.ageofempire.nms.v1_11_R1.Main.class.newInstance();
            } catch (InstantiationException e) {
                this.getLogger().severe("An error occurred!");
                this.setEnabled(false);
                return;
            } catch (IllegalAccessException e) {
                this.getLogger().severe("An error occurred!");
                this.setEnabled(false);
                return;
            }
        } else {
            this.getLogger().severe("Could not find support for your server.");
            this.setEnabled(false);
            return;
        }
        this.getLogger().info("Loading support for " + version);
        setupSettings();
        getCommand("setup").setExecutor(new Setup());
        getCommand("leave").setExecutor(new Leave());
        getCommand("help").setExecutor(new Help());
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
        pm.registerEvents(new PlayerInteractEntityListener(), this);
        pm.registerEvents(new ChatListener(), this);
        pm.registerEvents(new PlayerArmorStandManipulateListener(), this);
        pm.registerEvents(new ServerPingListener(), this);
        pm.registerEvents(new EntityDeathListener(), this);
        pm.registerEvents(new PlayerDeathListener(), this);
        pm.registerEvents(new PlayerRespawnListener(), this);
        pm.registerEvents(new CreatureSpawnListener(), this);
        pm.registerEvents(new PlayerLoginListener(), this);
        pm.registerEvents(new ProjectileHitListener(), this);
        pm.registerEvents(new EntityShootBowListener(), this);
        plugin.getServer().getMessenger().registerOutgoingPluginChannel(plugin, "BungeeCord");
        try {
            setupChat();
        } catch (Exception e){
        }
    }
    private static String readString(String url) throws IOException {
        URL urll= new URL(url);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(urll.openStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null){
            return inputLine;
        }
        in.close();

        return null;
    }

    private boolean setupChat() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Chat> rsp = null;
        try {
            rsp = getServer().getServicesManager().getRegistration(Chat.class);
        } catch (Exception e) {
        }
        chat = rsp.getProvider();
        vaultHook = true;
        plugin.getLogger().info("Loaded Vault support!");
        return chat != null;
    }
}