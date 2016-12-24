package com.andrei1058.ageofempire.commands;

import com.andrei1058.ageofempire.configuration.Settings;
import com.andrei1058.ageofempire.locations.Locations;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static com.andrei1058.ageofempire.Main.SETUP;
import static com.andrei1058.ageofempire.Main.plugin;

public class Setup implements CommandExecutor {
    private static ArrayList<Player> setup = new ArrayList();
    private static HashMap<Player, Location> loc = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender s, Command c, String st, String[] args) {
        if (s instanceof ConsoleCommandSender){
            s.sendMessage("This plugin is for players!");
            return true;
        }
        if (!SETUP) return true;
        Player p = (Player) s;
        if (c.getName().equalsIgnoreCase("setup")){
            if (args.length == 0){
                if (setup.contains(p)){
                    //daca este in setup trimite astea
                    p.sendMessage("§9§lAge Of Empire §c§lSetup:");
                    p.sendMessage("§7/s setSpawn <Blue/Red/Green/Yellow>");
                    //p.sendMessage("§7/s setPos <1/2> <Blue/Red/Green/Yellow>");
                    p.sendMessage("§7/s addSmallPlot <team> <1/2>");
                    p.sendMessage("§7/s addMediumPlot <team> <1/2>");
                    p.sendMessage("§7/s addLargePlot <team> <1/2>");
                    p.sendMessage("§7/s setForum <Blue/Red/Green/Yellow>");
                    p.sendMessage("§7/s addXp");
                    p.sendMessage("§e/s saveMap");
                } else {
                    //daca nu este in setup trimite astea
                    p.sendMessage("§9§lAge Of Empire §c§lSetup:");
                    p.sendMessage("§7/s setLobby");
                    p.sendMessage("§7/s addMap <worldName>");
                    p.sendMessage("§7/s editMap <worldName>");
                    p.sendMessage("§7/s mapsList");
                    p.sendMessage("§c/s finishSetup");
                }
                return true;
            } else {
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("setlobby")) {
                        Locations.saveLoc(p.getLocation(), "Spawns.Lobby");
                        p.sendMessage("§3Lobby set!");
                    }
                    else if (args[0].equalsIgnoreCase("addmap")) {
                        p.sendMessage("§eUsage: §7/s addMap <worldName>");
                    }
                    else if (args[0].equalsIgnoreCase("editmap")) {
                            p.sendMessage("§eUsage: §7/s editMap <worldName>");
                    }
                    else if (args[0].equalsIgnoreCase("mapslist")){
                        p.sendMessage("§a§lAvailable Maps:");
                        for (String str : Settings.load().getStringList("Arenas")){
                            p.sendMessage("§7- "+str);
                        }
                    }
                    else if (args[0].equalsIgnoreCase("setspawn")){
                        p.sendMessage("§eUsage: §7/s setspawn <Blue/Red/Green/Yellow>");
                    }
                    else if (args[0].equalsIgnoreCase("finishsetup")){
                        Settings.togglesetup(false);
                        p.sendMessage("§cSetup done!");
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "restart");
                    }
                    else if (args[0].equalsIgnoreCase("setpos")){
                        p.sendMessage("§eUsage: §7/s setPos <1/2> <Blue/Red/Green/Yellow>");
                    }
                    if (setup.contains(p)){
                        if (args[0].equalsIgnoreCase("savemap")){
                            p.teleport(loc.get(p));
                            setup.remove(p);
                        }
                        else if (args[0].equalsIgnoreCase("setpos")){
                            p.sendMessage("§eUsage: §7/s setPos <1/2> <Blue/Red/Green/Yellow>");
                        }
                        else if (args[0].equalsIgnoreCase("addsmallplot")){
                            p.sendMessage("§cUsage: §7/s addSmallPlot <team> <1/2/>");
                        }
                        else if (args[0].equalsIgnoreCase("addmediumplot")){
                            p.sendMessage("§cUsage: §7/s addMediumPlot <team> <1/2>");
                        }
                        else if (args[0].equalsIgnoreCase("addlargeplot")){
                            p.sendMessage("§cUsage: §7/s addLargePlot <team> <1/2>");
                        }
                        else if (args[0].equalsIgnoreCase("setforum")){
                            p.sendMessage("§cUsage: §7/s setForum <Blue/Red/Yellow/Green>");
                        }
                        else if (args[0].equalsIgnoreCase("addxp")){
                            p.sendMessage("§cUsage: §7/s addxp <number>");
                        }
                    }
                } else if (args.length == 2) {
                    if (args[0].equalsIgnoreCase("addmap")){
                        if (new File(args[1]).exists()) {
                            if (Settings.load().getStringList("Arenas").contains(args[1])) {
                                p.sendMessage("§ePlease use: §7/s editMap " + args[1]);
                                return true;
                            }
                            p.sendMessage("§ePlease wait!");
                            Bukkit.createWorld(new WorldCreator(args[1]));
                            Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                                @Override
                                public void run() {
                                    loc.put(p, p.getLocation());
                                    p.teleport(Bukkit.getWorld(args[1]).getSpawnLocation());
                                    p.sendMessage("§eTeleported to §7" + args[1] + "§e's spawn!");
                                    setup.add(p);
                                    Settings.addMap(args[1]);
                                }
                            }, 100L);
                        } else {
                            p.sendMessage("§cI can't find this map!");
                        }
                    }
                    else if (args[0].equalsIgnoreCase("editmap")){
                        if (new File(args[1]).exists() && Settings.load().getStringList("Arenas").contains(args[1])){
                            p.sendMessage("§ePlease wait!");
                            Bukkit.createWorld(new WorldCreator(args[1]));
                            Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                                @Override
                                public void run() {
                                    loc.put(p, p.getLocation());
                                    p.teleport(Bukkit.getWorld(args[1]).getSpawnLocation());
                                    p.sendMessage("§eTeleported to §7" + args[1] + "§e's spawn!");
                                    setup.add(p);
                                }
                            }, 100L);
                        } else {
                            p.sendMessage("§cUnknown map!");
                        }
                    }
                    if (setup.contains(p)){
                        if (args[0].equalsIgnoreCase("setspawn")){
                            switch (args[1].toLowerCase()){
                                default:
                                    p.sendMessage("§cChoices: <Blue/Red/Green/Yellow>");
                                    break;
                                case "blue":
                                    Locations.saveLoc(p.getLocation(), "Spawns."+p.getWorld().getName()+".Blue");
                                    p.sendMessage("§7Blue spawn set!");
                                    break;
                                case "red":
                                    Locations.saveLoc(p.getLocation(), "Spawns."+p.getWorld().getName()+".Red");
                                    p.sendMessage("§7Red spawn set!");
                                    break;
                                case "yellow":
                                    Locations.saveLoc(p.getLocation(), "Spawns."+p.getWorld().getName()+".Yellow");
                                    p.sendMessage("§7Yellow spawn set!");
                                    break;
                                case "green":
                                    Locations.saveLoc(p.getLocation(), "Spawns."+p.getWorld().getName()+".Green");
                                    p.sendMessage("§7Green spawn set!");
                                    break;
                            }
                        }
                        else if (args[0].equalsIgnoreCase("addsmallplot")){
                            p.sendMessage("§cUsage: §7/s addSmallPlot <team> <1/2>");
                        }
                        else if (args[0].equalsIgnoreCase("addmediumplot")){
                            p.sendMessage("§cUsage: §7/s addMediumPlot <team> <1/2>");
                        }
                        else if (args[0].equalsIgnoreCase("addlargeplot")){
                            p.sendMessage("§cUsage: §7/s addLargePlot <team> <1/2>");
                        }
                        else if (args[0].equalsIgnoreCase("setforum")){
                            switch (args[1].toLowerCase()){
                                default:
                                    p.sendMessage("§cUsage: §7/s setForum <Blue/Red/Yellow/Green>");
                                    break;
                                case "blue":
                                    Locations.saveLoc(p.getLocation(), "Forums."+p.getWorld().getName()+".Blue");
                                    p.sendMessage("§7Blue forum set!");
                                    break;
                                case "red":
                                    Locations.saveLoc(p.getLocation(), "Forums."+p.getWorld().getName()+".Red");
                                    p.sendMessage("§7Red forum set!");
                                    break;
                                case "yellow":
                                    Locations.saveLoc(p.getLocation(), "Forums."+p.getWorld().getName()+".Yellow");
                                    p.sendMessage("§7Yellow forum set!");
                                    break;
                                case "green":
                                    Locations.saveLoc(p.getLocation(), "Forums."+p.getWorld().getName()+".Green");
                                    p.sendMessage("§7Green forum set!");
                                    break;
                            }
                        }
                        else if (args[0].equalsIgnoreCase("addxp")){
                            p.sendMessage("§eXp "+args[1]+" lantern set!");
                            Locations.saveLoc(p.getLocation().add(0, -1, 0), "xp."+p.getWorld().getName()+"."+args[1]);
                        }
                    }
                }
                else if (args.length == 3){
                    if (setup.contains(p));
                    if (args[0].equalsIgnoreCase("setpos")){
                        if (!isInt(args[1])){
                            p.sendMessage("§cUsage: §7/s setPos <1/2> <Blue/Red/Green/Yellow>");
                            return true;
                        }
                        switch (args[2].toLowerCase()){
                            default:
                                p.sendMessage("§cChoices: <Blue/Red/Green/Yellow>");
                                break;
                            case "blue":
                                Locations.saveLoc(p.getLocation(), "Region."+p.getWorld().getName()+".Blue.Pos"+args[1]);
                                p.sendMessage("§7Blue pos"+args[1]+" set!");
                                break;
                            case "red":
                                Locations.saveLoc(p.getLocation(), "Region."+p.getWorld().getName()+".Red.Pos"+args[1]);
                                p.sendMessage("§7Red pos"+args[1]+" set!");
                                break;
                            case "green":
                                Locations.saveLoc(p.getLocation(), "Region."+p.getWorld().getName()+".Green.Pos"+args[1]);
                                p.sendMessage("§7Green pos"+args[1]+" set!");
                                break;
                            case "yellow":
                                Locations.saveLoc(p.getLocation(), "Region."+p.getWorld().getName()+".Yellow.Pos"+args[1]);
                                p.sendMessage("§7Yellow pos"+args[1]+" set!");
                                break;
                        }
                    }
                    else if (args[0].equalsIgnoreCase("addsmallplot")){
                        if (!isInt(args[2])){
                            p.sendMessage("§cUsage: §7/s addSmallPlot <Red/Blue/Yellow/Green> <1/2..>");
                            return true;
                        }
                        switch (args[1].toLowerCase()){
                            default:
                                p.sendMessage("§cUsage: §7/s addSmallPlot <Red/Blue/Yellow/Green> <1/2...>");
                                break;
                            case "blue":
                                Locations.saveLoc(p.getLocation(), "Plots."+p.getWorld().getName()+".Blue.Small."+args[2]);
                                p.sendMessage("§7Small Plot "+args[2]+" was added to the Blue Team");
                                break;
                            case "green":
                                Locations.saveLoc(p.getLocation(), "Plots."+p.getWorld().getName()+".Green.Small."+args[2]);
                                p.sendMessage("§7Small Plot "+args[2]+" was added to the Green Team");
                                break;
                            case "red":
                                Locations.saveLoc(p.getLocation(), "Plots."+p.getWorld().getName()+".Red.Small."+args[2]);
                                p.sendMessage("§7Small Plot "+args[2]+" was added to the Red Team");
                                break;
                            case "yellow":
                                Locations.saveLoc(p.getLocation(), "Plots."+p.getWorld().getName()+".Yellow.Small."+args[2]);
                                p.sendMessage("§7Small Plot "+args[2]+" was added to the Yellow Team");
                                break;
                        }
                    }
                    else if (args[0].equalsIgnoreCase("addmediumplot")){
                        if (!isInt(args[2])) {
                            p.sendMessage("§cUsage: §7/s addMediumPlot <team> <1/2/3..>");
                        }
                        switch (args[1].toLowerCase()){
                            default:
                                p.sendMessage("§cUsage: §7/s addMediumPlot <team> <1/2/3>");
                                break;
                            case "blue":
                                Locations.saveLoc(p.getLocation(), "Plots."+p.getWorld().getName()+".Blue.Medium."+args[2]);
                                p.sendMessage("§7Medium Plot "+args[2]+" was added to the Blue Team");
                                break;
                            case "red":
                                Locations.saveLoc(p.getLocation(), "Plots."+p.getWorld().getName()+".Red.Medium."+args[2]);
                                p.sendMessage("§7Medium Plot "+args[2]+" was added to the Red Team");
                                break;
                            case "yellow":
                                Locations.saveLoc(p.getLocation(), "Plots."+p.getWorld().getName()+".Yellow.Medium."+args[2]);
                                p.sendMessage("§7Medium Plot "+args[2]+" was added to the Yellow Team");
                                break;
                            case "green":
                                Locations.saveLoc(p.getLocation(), "Plots."+p.getWorld().getName()+".Green.Medium."+args[2]);
                                p.sendMessage("§7Medium Plot "+args[2]+" was added to the Green Team");
                                break;
                        }
                        return true;
                    }
                    else if (args[0].equalsIgnoreCase("addlargeplot")){
                        if (!isInt(args[2])) {
                            p.sendMessage("§cUsage: §7/s addLargePlot <team> <1/2...>");
                        }
                        switch (args[1].toLowerCase()){
                            default:
                                p.sendMessage("§cUsage: §7/s addLargePlot <team> <1/2...>");
                                break;
                            case "blue":
                                Locations.saveLoc(p.getLocation(), "Plots."+p.getWorld().getName()+".Blue.Large."+args[2]);
                                p.sendMessage("§7Large Plot "+args[2]+" was added to the Blue Team");
                                break;
                            case "red":
                                Locations.saveLoc(p.getLocation(), "Plots."+p.getWorld().getName()+".Red.Large."+args[2]);
                                p.sendMessage("§7Large Plot "+args[2]+" was added to the Red Team");
                                break;
                            case "yellow":
                                Locations.saveLoc(p.getLocation(), "Plots."+p.getWorld().getName()+".Yellow.Large."+args[2]);
                                p.sendMessage("§7Large Plot "+args[2]+" was added to the Yellow Team");
                                break;
                            case "green":
                                Locations.saveLoc(p.getLocation(), "Plots."+p.getWorld().getName()+".Green.Large."+args[2]);
                                p.sendMessage("§7Large Plot "+args[2]+" was added to the Green Team");
                                break;
                        }
                    }
                } else {
                    p.sendMessage("§cToo many arguments!");
                }
            }
        }
        return false;
    }

    public static boolean isInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
