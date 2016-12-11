package com.andrei1058.ageofempire.locations;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Locations {

    private static File file = new File("plugins/Age-Of-Empire/locations.yml");
    private static YamlConfiguration yml = YamlConfiguration.loadConfiguration(file);

    public static void saveLoc(Location location, String ymlData){
        if (file.exists()){
            yml.set(ymlData+".World",location.getWorld().getName());
            yml.set(ymlData+".X",location.getX());
            yml.set(ymlData+".Y",location.getY());
            yml.set(ymlData+".Z",location.getZ());
            yml.set(ymlData+".Yaw",location.getYaw());
            yml.set(ymlData+".Pitch",location.getPitch());
            try {
                yml.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            yml.set(ymlData+".World",location.getWorld().getName());
            yml.set(ymlData+".X",location.getX());
            yml.set(ymlData+".Y",location.getY());
            yml.set(ymlData+".Z",location.getZ());
            yml.set(ymlData+".Yaw",location.getYaw());
            yml.set(ymlData+".Pitch",location.getPitch());
            try {
                yml.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Location getLoc(String ymlData){
        return new Location(Bukkit.getWorld(yml.getString(ymlData+".World")), yml.getDouble(ymlData+".X"), yml.getDouble(ymlData+".Y"),
                yml.getDouble(ymlData+".Z"), (float)yml.getDouble(ymlData+".Yaw"), (float)yml.getDouble(ymlData+".Pitch"));
    }

    public static void savePlot(String ymlData, Location pos1, Location middle, Location pos2){
        yml.set(ymlData+".Mid.X", middle.getBlockX());
        yml.set(ymlData+".Mid.Y", middle.getBlockY());
        yml.set(ymlData+".Mid.Z", middle.getBlockZ());

        yml.set(ymlData+".Pos1.X", pos1.getBlockX());
        yml.set(ymlData+".Pos1.Y", pos1.getBlockY());
        yml.set(ymlData+".Pos1.Z", pos1.getBlockZ());

        yml.set(ymlData+".Pos2.X", pos2.getBlockX());
        yml.set(ymlData+".Pos2.Y", pos2.getBlockY());
        yml.set(ymlData+".Pos2.Z", pos2.getBlockZ());
    }

    public static YamlConfiguration load(){
        return yml;
    }
}
