package com.andrei1058.ageofempire.configuration;
import com.andrei1058.ageofempire.locations.Locations;
import com.andrei1058.ageofempire.locations.Region;
import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

import static com.andrei1058.ageofempire.Main.*;
import static com.andrei1058.ageofempire.configuration.Messages.getMsg;
import static com.andrei1058.ageofempire.configuration.Messages.setupMessages;
import static com.andrei1058.ageofempire.configuration.MySQL.setupDatabase;

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
        yml.addDefault("max-in-team", 6);
        yml.addDefault("min-players", 6);
        yml.addDefault("countdowns.lobby", 60);
        yml.addDefault("countdowns.pregame", 20);
        yml.addDefault("countdowns.pvp", 14);
        yml.addDefault("countdowns.assault", 12);
        yml.addDefault("restart-cmd", "restart");
        yml.addDefault("plot-radius.small", 9);
        yml.addDefault("plot-radius.medium", 12);
        yml.addDefault("plot-radius.large", 16);
        yml.addDefault("Database.enable", false);
        yml.addDefault("Database.host", "localhost");
        yml.addDefault("Database.port", 3306);
        yml.addDefault("Database.database", "AgeOfEmpire");
        yml.addDefault("Database.table", "stats_");
        yml.addDefault("Database.username", "root");
        yml.addDefault("Database.password", "pass");
        yml.addDefault("health.forum", 1400);
        yml.addDefault("health.other", 500);
        yml.options().header("Age Of Empire plugin by andrei1058 | https://www.spigotmc.org/members/andrei1058.39904/\n---------------------------------------------\n" +
                "Well, read this if you need help...\n" +
                "Setup-Mode: false |  Set this to true if you're setting up the server.\n" +
                "lobby-server: ageofempire | Set this to false if you want a multi-arena server.\n" +
                "max-in-team: 2 | How may players can join a team?\n" +
                "min-players: 2 | How may players should be connected to start the lobby countdown?\n" +
                "countdowns: | Various countdowns.\n" +
                "  lobby: 40\n" +
                "  pregame: 10\n" +
                "  pvp: 5\n" +
                "  assault: 5\n" +
                "restart-cmd: restart | Which command should be executed when the game is over?\n" +
                "plot-radius: | Various plots radius. Dimensions 9 x 9 etc." +
                "  small: 9\n" +
                "  medium: 12\n" +
                "  large: 16\n" +
                "Database: | Database credentials. Needed for stats." +
                "  enable: true\n" +
                "  host: localhost\n" +
                "  port: 3306\n" +
                "  database: AOE\n" +
                "  table: stats_\n" +
                "  username: root\n" +
                "  password: p4ss2\n" +
                "health: | Various health stuff." +
                "  forum: 1400\n" +
                "  other:  500"+
                "Arenas: | This is the maps list.\n" +
                "- mappav2\n");
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
        forum_health = yml.getInt("health.forum");
        other_health = yml.getInt("health.other");

        if (Settings.load().get("Arenas") != null && !SETUP){
            nms.registerVillagers();
            Random r = new Random();
            int a = Settings.load().getStringList("Arenas").size();
            int mapid = r.nextInt(a);
            choosenMap = Settings.load().getStringList("Arenas").get(mapid);
            Bukkit.createWorld(new WorldCreator(choosenMap));
            Locations.getLoc("Spawns.Lobby").getWorld().getEntities().forEach(Entity::remove);
            Bukkit.getWorld(choosenMap).getEntities().forEach(Entity::remove);
            Bukkit.getWorld(choosenMap).setGameRuleValue("keepInventory", "false");
            Bukkit.getWorld(choosenMap).setAutoSave(false);
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
                Region.loadRegions();
        }
        File schem = new File("plugins/Age-Of-Empire/schematics");
        if (!schem.exists()){
            schem.mkdir();
        }
        saveschem("ARMORY");
        saveschem("ARCHERY_STORE");
        saveschem("FORGE");
        saveschem("GOLD_MINE");
        saveschem("GUILD");
        saveschem("KENNEL");
        saveschem("LABORATORY");
        saveschem("MARKET");
        saveschem("MILL");
        saveschem("SABOTAGE_WORKSHOP");
        saveschem("SAWMILL");
        saveschem("STABLE");
        saveschem("STONE_MINE");
        saveschem("TRAINING_CENTER");
        saveschem("TRIFARROW");
        saveschem("WORKSHOP");
        if (yml.getBoolean("Database.enable")){
            setupDatabase(yml.getString("Database.host"), yml.getInt("Database.port"), yml.getString("Database.database"), yml.getString("Database.username"), yml.getString("Database.password"), yml.getString("Database.table"));
        }
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

    private static void saveschem(String name){
        InputStream is = plugin.getResource("schematics/"+name+".schematic");
        OutputStream os;
        try {
            os = new FileOutputStream("plugins/Age-Of-Empire/schematics/"+name+".schematic", false);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
            is.close();
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
