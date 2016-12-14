package com.andrei1058.ageofempire.locations;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.ArrayList;

import static com.andrei1058.ageofempire.Main.choosenMap;

public class Region {
    private static ArrayList<Region> regions = new ArrayList<>();

    public static ArrayList<Region> getList(){
        return regions;
    }
    public boolean check(){
        return true;
    }
    public Location center(){
        return new Location(Bukkit.getWorld(choosenMap), 0,0,0);
    }
}
