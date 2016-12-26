package com.andrei1058.ageofempire.locations;

import com.andrei1058.ageofempire.configuration.Settings;
import com.andrei1058.ageofempire.game.BuildSchematic;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;

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
    ArmorStand as;

    public Region(Location center, boolean small, boolean medium, boolean large, String name) {
        this.small = small;
        this.medium = medium;
        this.large = large;
        this.name = name;
        String string = null;
        if (this.small) {
            this.loc1 = center.clone().add(-Settings.load().getInt("plot-radius.small"), -2, -Settings.load().getInt("plot-radius.small"));
            this.loc2 = center.clone().add(+Settings.load().getInt("plot-radius.small"), +1, +Settings.load().getInt("plot-radius.small"));
            string = getMsg("plot.small");
        } else if (medium){
            this.loc1 = center.clone().add(-Settings.load().getInt("plot-radius.medium"), -2, -Settings.load().getInt("plot-radius.medium"));
            this.loc2 = center.clone().add(+Settings.load().getInt("plot-radius.medium"), +1, +Settings.load().getInt("plot-radius.medium"));
            string = getMsg("plot.medium");
        } else if (large){
            this.loc1 = center.clone().add(-Settings.load().getInt("plot-radius.large"), -2, -Settings.load().getInt("plot-radius.large"));
            this.loc2 = center.clone().add(+Settings.load().getInt("plot-radius.large"), +1, +Settings.load().getInt("plot-radius.large"));
            string = getMsg("plot.large");
        }
        as = (ArmorStand) center.getWorld().spawnEntity(center.clone().add(0, +7, +0), EntityType.ARMOR_STAND);
        as.setGravity(false);
        as.setCanPickupItems(false);
        as.setCustomName(string);
        as.setCustomNameVisible(true);
        as.setVisible(false);
        as.setSmall(false);
        regions.add(this);
    }

    public void removeHologram(){
        as.remove();
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
                            removeHologram();
                            blue_small_plots--;
                            regions.remove(this);
                        } else {
                            Bukkit.getPlayer(player).getInventory().setItem(7, slotlocked());
                            BuildSchematic.getUUID(player).end();
                        }
                    } else if (greenPlayers.contains(player)){
                        if (green_small_plots > 0){
                            Bukkit.getPlayer(player).getInventory().setItem(7, slotlocked());
                            BuildSchematic.getUUID(player).ok(loc1);
                            removeHologram();
                            green_small_plots--;
                            regions.remove(this);
                        } else {
                            Bukkit.getPlayer(player).getInventory().setItem(7, slotlocked());
                            BuildSchematic.getUUID(player).end();
                        }
                    } else if (yellowPlayers.contains(player)){
                        if (yellow_small_plots > 0){
                            Bukkit.getPlayer(player).getInventory().setItem(7, slotlocked());
                            BuildSchematic.getUUID(player).ok(loc1);
                            removeHologram();
                            yellow_small_plots--;
                            regions.remove(this);
                        } else {
                            Bukkit.getPlayer(player).getInventory().setItem(7, slotlocked());
                            BuildSchematic.getUUID(player).end();
                        }
                    } else if (redPlayers.contains(player)){
                        if (red_small_plots > 0){
                            Bukkit.getPlayer(player).getInventory().setItem(7, slotlocked());
                            BuildSchematic.getUUID(player).ok(loc1);
                            removeHologram();
                            red_small_plots--;
                            regions.remove(this);
                        } else {
                            Bukkit.getPlayer(player).getInventory().setItem(7, slotlocked());
                            BuildSchematic.getUUID(player).end();
                        }
                    }
                } else {
                    Bukkit.getPlayer(player).sendMessage(getMsg("cant-construct-size"));
                }
                break;
            case archery:
            case trifarrow:
            case stable:
            case armory:
            case laboratory:
                if (isMedium()){
                    if (bluePlayers.contains(player)){
                        if (blue_medium_plots > 0){
                            Bukkit.getPlayer(player).getInventory().setItem(7, slotlocked());
                            BuildSchematic.getUUID(player).ok(loc1);
                            removeHologram();
                            blue_medium_plots--;
                            regions.remove(this);
                        } else {
                            Bukkit.getPlayer(player).getInventory().setItem(7, slotlocked());
                            BuildSchematic.getUUID(player).end();
                        }
                    } else if (greenPlayers.contains(player)){
                        if (green_medium_plots > 0){
                            Bukkit.getPlayer(player).getInventory().setItem(7, slotlocked());
                            BuildSchematic.getUUID(player).ok(loc1);
                            removeHologram();
                            green_medium_plots--;
                            regions.remove(this);
                        } else {
                            Bukkit.getPlayer(player).getInventory().setItem(7, slotlocked());
                            BuildSchematic.getUUID(player).end();
                        }
                    } else if (yellowPlayers.contains(player)){
                        if (yellow_medium_plots > 0){
                            Bukkit.getPlayer(player).getInventory().setItem(7, slotlocked());
                            BuildSchematic.getUUID(player).ok(loc1);
                            removeHologram();
                            yellow_medium_plots--;
                            regions.remove(this);
                        } else {
                            Bukkit.getPlayer(player).getInventory().setItem(7, slotlocked());
                            BuildSchematic.getUUID(player).end();
                        }
                    } else if (redPlayers.contains(player)){
                        if (red_medium_plots > 0){
                            Bukkit.getPlayer(player).getInventory().setItem(7, slotlocked());
                            BuildSchematic.getUUID(player).ok(loc1);
                            removeHologram();
                            red_medium_plots--;
                            regions.remove(this);
                        } else {
                            Bukkit.getPlayer(player).getInventory().setItem(7, slotlocked());
                            BuildSchematic.getUUID(player).end();
                        }
                    }
                } else {
                    Bukkit.getPlayer(player).sendMessage(getMsg("cant-construct-size"));
                }
                break;
            case guild:
            case training_center:
                if (isLarge()){
                    if (bluePlayers.contains(player)){
                        if (blue_large_plots > 0){
                            Bukkit.getPlayer(player).getInventory().setItem(7, slotlocked());
                            BuildSchematic.getUUID(player).ok(loc1);
                            removeHologram();
                            blue_large_plots--;
                            regions.remove(this);
                        } else {
                            Bukkit.getPlayer(player).getInventory().setItem(7, slotlocked());
                            BuildSchematic.getUUID(player).end();
                        }
                    } else if (greenPlayers.contains(player)){
                        if (green_large_plots > 0){
                            Bukkit.getPlayer(player).getInventory().setItem(7, slotlocked());
                            BuildSchematic.getUUID(player).ok(loc1);
                            removeHologram();
                            green_large_plots--;
                            regions.remove(this);
                        } else {
                            Bukkit.getPlayer(player).getInventory().setItem(7, slotlocked());
                            BuildSchematic.getUUID(player).end();
                        }
                    } else if (yellowPlayers.contains(player)){
                        if (yellow_large_plots > 0){
                            Bukkit.getPlayer(player).getInventory().setItem(7, slotlocked());
                            BuildSchematic.getUUID(player).ok(loc1);
                            removeHologram();
                            yellow_large_plots--;
                            regions.remove(this);
                        } else {
                            Bukkit.getPlayer(player).getInventory().setItem(7, slotlocked());
                            BuildSchematic.getUUID(player).end();
                        }
                    } else if (redPlayers.contains(player)){
                        if (red_large_plots > 0){
                            Bukkit.getPlayer(player).getInventory().setItem(7, slotlocked());
                            BuildSchematic.getUUID(player).ok(loc1);
                            removeHologram();
                            red_large_plots--;
                            regions.remove(this);
                        } else {
                            Bukkit.getPlayer(player).getInventory().setItem(7, slotlocked());
                            BuildSchematic.getUUID(player).end();
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
            for (String key : Locations.load().getConfigurationSection("Plots."+choosenMap+"."+s+".Small").getKeys(false)) {
                new Region(Locations.getLoc("Plots." + choosenMap + "." + s + ".Small." + key), true, false, false, s);
            }
            for (String key : Locations.load().getConfigurationSection("Plots."+choosenMap+"."+s+".Medium").getKeys(false)) {
                new Region(Locations.getLoc("Plots." + choosenMap + "." + s + ".Medium." + key), false, true, false, s);
            }
            for (String key : Locations.load().getConfigurationSection("Plots."+choosenMap+"."+s+".Large").getKeys(false)) {
                new Region(Locations.getLoc("Plots." + choosenMap + "." + s + ".Large." + key), false, false, true, s);
            }
        }
    }
}
