package com.andrei1058.ageofempire.configuration;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Building {
    public static void save(String filename, List<Block> blocks){
        ArrayList<String> strings = new ArrayList<>();
        for (Block b : blocks){
            if (b.getType() != Material.AIR) {
                strings.add(b.getLocation().getX() + "," + b.getLocation().getY() + "," + b.getLocation().getZ() + "," + b.getType() + "," + b.getData());
            }
        }
        File file = new File("plugins/Age-Of-Empire/buildings/"+filename+".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(file);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        yml.set("Blocks", strings);
        try {
            yml.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> load(String filename){
        File file = new File("plugins/Age-Of-Empire/buildings/"+filename+".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(file);
        return (ArrayList<String>) yml.getStringList("Block");
    }
}
