package com.andrei1058.ageofempire.configuration;

import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import static com.andrei1058.ageofempire.Main.plugin;

/**
 * Copyright Andrei Dascalu - andrei1058 @spigotmc.org
 * SkyFall1058 class written on 23/03/2017
 */
public class MySQL {

    private static int port;
    private static String host;
    private static String user;
    private static String db_name;
    private static String password;
    private static String table;
    private Connection connection;

    public static void setupDatabase(String host1, int port1, String database1, String user1, String password1, String prefixx){
        host = host1;
        port = port1;
        db_name = database1;
        user = user1;
        password = password1;
        table = prefixx+"Gravity";
        new MySQL().createTable();
    }

    public boolean isConnected(){
        return this.connection != null;
    }
    public boolean connect(){
        if (!isConnected()){
            try {
                connection = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+db_name+"?user="+user+"&password="+password);
                return true;
            } catch (SQLException e) {
                plugin.getLogger().severe("Cannot connect to database!");
                return false;
            }
        }
        return false;
    }
    public void disconnect(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean createTable(){
        connect();
        if (isConnected()){
            try {
                connection.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS `" + table + "` (Player varchar(100),UUID varchar(100),Wins int(100),GamesPlayed int(100),Kills int(100),Deaths int(100),KingsKilled int(100));");
                disconnect();
                return true;
            }
            catch (SQLException e) {
                plugin.getLogger().severe(e.getMessage());
                return false;
            }
        }
        return false;
    }
    public ArrayList<Integer> getStats(UUID uuid){
        connect();
        if (!isConnected()){
            return null_stats;
        }
        try {
            ArrayList<Integer> data = new ArrayList<>();
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM `"+table+"` WHERE UUID='"+uuid+"';");
            if (rs.next()){
                data.add(rs.getInt("Wins"));
                data.add(rs.getInt("GamesPlayed"));
                data.add(rs.getInt("Kills"));
                data.add(rs.getInt("Deaths"));
                data.add(rs.getInt("KingsKilled"));
                return data;
            } else {
                return null_stats;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null_stats;
    }

    public void addStats(UUID uuid, int wins, int gamesplayed, int kills, int deaths, int kinskilled){
        connect();
        if (isConnected()){
            if (exists(uuid)){
                try {
                    connection.createStatement().executeUpdate("UPDATE `"+table+"` SET Wins=Wins+"+wins+", GamesPlayed=GamesPlayed+"+gamesplayed+", Kills=Kills+"+kills+", Deaths=Deaths+"+deaths+", KingsKilled=KingsKilled+"+kinskilled+" WHERE UUID='"+uuid+"';");
                } catch (Exception e){
                    e.printStackTrace();
                }
            } else {
                try {
                    connection.createStatement().executeUpdate("INSERT INTO `"+table+"` VALUES ('"+Bukkit.getPlayer(uuid).getName()+"','"+ uuid.toString()+"','"+wins+"','"+gamesplayed+"','"+kills+"','"+deaths+"','"+kinskilled+"') ");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean exists(UUID uuid){
        connect();
        if (isConnected()){
            try {
                ResultSet rs = connection.createStatement().executeQuery("SELECT Player FROM `"+table+"` WHERE UUID='"+uuid+"';");
                if (rs.next()){
                    return true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private static final ArrayList<Integer> null_stats = new ArrayList(Arrays.asList(0, 0, 0, 0, 0));
}

