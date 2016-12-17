package com.andrei1058.ageofempire.locations;

import com.andrei1058.ageofempire.configuration.Settings;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.*;

import static com.andrei1058.ageofempire.Main.*;
import static com.andrei1058.ageofempire.Misc.slotlocked;
import static com.andrei1058.ageofempire.configuration.Messages.getMsg;
import static com.andrei1058.ageofempire.game.Buildings.*;

public class Region {
    private static ArrayList<Region> regions = new ArrayList<>();

    private Location loc1;
    private Location loc2;
    private boolean small;
    private boolean medium;
    private boolean large;
    private String name;

    public Region(Location center, boolean small, boolean medium, boolean large, String name) {
        this.small = small;
        this.medium = medium;
        this.large = large;
        this.name = name;
        if (this.small) {
            this.loc1 = center.clone().add(-Settings.load().getInt("plot-radius.small"), -2, -Settings.load().getInt("plot-radius.small"));
            this.loc2 = center.clone().add(+Settings.load().getInt("plot-radius.small"), +1, +Settings.load().getInt("plot-radius.small"));
        } else if (this.medium){
            this.loc1 = center.clone().add(-Settings.load().getInt("plot-radius.medium"), -2, -Settings.load().getInt("plot-radius.medium"));
            this.loc2 = center.clone().add(+Settings.load().getInt("plot-radius.medium"), +1, +Settings.load().getInt("plot-radius.medium"));
        } else if (this.large){
            this.loc1 = center.clone().add(-Settings.load().getInt("plot-radius.large"), -2, -Settings.load().getInt("plot-radius.large"));
            this.loc2 = center.clone().add(+Settings.load().getInt("plot-radius.large"), +1, +Settings.load().getInt("plot-radius.large"));
        }
        regions.add(this);
    }

    public boolean isInRegion(Location loc) {
        Location low = new Location(this.loc1.getWorld(), Math.min(this.loc1.getX(), this.loc2.getX()), Math.min(this.loc1.getY(), this.loc2.getY()), Math.min(this.loc1.getZ(), this.loc2.getZ()));
        Location high = new Location(this.loc1.getWorld(), Math.max(this.loc1.getX(), this.loc2.getX()), Math.max(this.loc1.getY(), this.loc2.getY()), Math.max(this.loc1.getZ(), this.loc2.getZ()));
        return loc.getX() <= high.getX() && loc.getX() >= low.getX() && loc.getY() <= high.getY() && loc.getY() >= low.getY() && loc.getZ() <= high.getZ() && loc.getZ() >= low.getZ();
    }

    public void allowed(UUID player) {
        switch (name){
            case blue_team:
                if (bluePlayers.contains(player)){
                    rightPlot(player);
                } else {
                    Bukkit.getPlayer(player).sendMessage(getMsg("cant-construct-outside"));
                }
                break;
            case green_team:
                if (greenPlayers.contains(player)){
                    rightPlot(player);
                } else {
                    Bukkit.getPlayer(player).sendMessage(getMsg("cant-construct-outside"));
                }
                break;
            case yellow_team:
                if (yellowPlayers.contains(player)){
                    rightPlot(player);
                } else {
                    Bukkit.getPlayer(player).sendMessage(getMsg("cant-construct-outside"));
                }
                break;
            case red_team:
                if (redPlayers.contains(player)){
                    rightPlot(player);
                } else {
                    Bukkit.getPlayer(player).sendMessage(getMsg("cant-construct-outside"));
                }
                break;
        }
    }

    public static void check(Location loc, UUID player) {
        if (getRegion(loc) != null) {
            getRegion(loc).allowed(player);
        } else {
            Bukkit.getPlayer(player).sendMessage(getMsg("cant-construct-here"));
        }
    }

    private static Region getRegion(Location loc){
        for (Region r: regions){
            if (r.isInRegion(loc)){
                return r;
            }
        }
        return null;
    }

    private boolean isSmall(){
        return small;
    }
    private boolean isMedium(){
        return medium;
    }
    private boolean isLarge(){
        return large;
    }

    private void rightPlot(UUID player){
        switch (construct_in_inv.get(player)){
            case forge:
            case mill:
            case stone_mine:
            case gold_mine:
            case sawmill:
            case workshop:
            case market:
            case kennel:
            case sabotage:
                if (isSmall()){
                    if (bluePlayers.contains(player)){
                        if (blue_small_plots > 0){
                            Bukkit.getPlayer(player).getInventory().setItem(7, slotlocked());
                            BuildSchematic.getUUID(player).ok(loc1);
                            blue_small_plots--;
                        } else {
                            //not available small plots
                        }
                    } else if (greenPlayers.contains(player)){
                        if (green_small_plots > 0){
                            Bukkit.getPlayer(player).getInventory().setItem(7, slotlocked());
                            BuildSchematic.getUUID(player).ok(loc1);
                            green_small_plots--;
                        } else {
                            //not available small plots
                        }
                    } else if (yellowPlayers.contains(player)){
                        if (yellow_small_plots > 0){
                            Bukkit.getPlayer(player).getInventory().setItem(7, slotlocked());
                            BuildSchematic.getUUID(player).ok(loc1);
                            yellow_small_plots--;
                        } else {
                            //not available small plots
                        }
                    } else if (redPlayers.contains(player)){
                        if (red_small_plots > 0){
                            Bukkit.getPlayer(player).getInventory().setItem(7, slotlocked());
                            BuildSchematic.getUUID(player).ok(loc1);
                            red_small_plots--;
                        } else {
                            //not available small plots
                        }
                    }
                } else {
                    Bukkit.getPlayer(player).sendMessage(getMsg("cant-construct-size"));
                }
                break;
        }
    }

    public static void loadRegions(){
        List<String> teams = Arrays.asList(blue_team, green_team, yellow_team, red_team);
        for (String s : teams){
            //de setat marimea fiecarui plot
            for (String key : Locations.load().getConfigurationSection("Plots."+choosenMap+"."+s+".Small").getKeys(false)) {
                new Region(Locations.getLoc("Plots." + choosenMap + "." + s + ".Small." + key), true, false, false, s);
            }
        }
    }
}
