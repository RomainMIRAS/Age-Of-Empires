package com.andrei1058.ageofempire.commands;

import com.andrei1058.ageofempire.locations.StructureBuilder;
import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import static com.andrei1058.ageofempire.Main.SETUP;

public class Builds implements CommandExecutor {
    public static Block loc1 = null;
    public static Block loc2 = null;
    @Override
    public boolean onCommand(CommandSender s, Command c, String st, String[] args) {
        if (s instanceof ConsoleCommandSender) return true;
        if (!SETUP) return true;
        Player p = (Player) s;
        if (c.getName().equalsIgnoreCase("build")){
            if (args.length==0) {
                p.sendMessage("§c§lAgeOfEmpire §e§lBuildings");
                p.sendMessage("§e/build gotomap <name>");
                p.sendMessage("§e/build pos1");
                p.sendMessage("§e/build pos2");
                p.sendMessage("§e/build save <name>");
            } else if (args.length==1){
                switch (args[0].toLowerCase()){
                    case "pos1":
                        loc1 = p.getLocation().getBlock();
                        p.sendMessage("Pos selected!");
                        if (loc2 != null){
                            p.sendMessage("§eSave your building with /build save <name>");
                            p.sendMessage("§cP.s. Stay on the middle of the building when you do it!");
                        }
                        break;
                    case "pos2":
                        p.sendMessage("Pos selected!");
                        loc2 = p.getLocation().getBlock();
                        if (loc1 != null){
                            p.sendMessage("§eSave your building with /build save <name>");
                            p.sendMessage("§cP.s. Stay on the middle of the building when you do it!");
                        }
                        break;
                    case "save":
                        p.sendMessage("§cUsage: /build save <name>");
                        break;
                    case "gotomap":
                        p.sendMessage("&cUsage: /build gotomap <name>");
                        break;
                    default:
                        p.sendMessage("Not found!");
                        break;
                }
            } else if (args.length==2){
                switch (args[0].toLowerCase()){
                    case "save":
                        if (loc1 == null && loc2 == null){
                            p.sendMessage("§ePlease select pos1 and pos2 first!");
                        } else {
                            StructureBuilder bu = new StructureBuilder();
                            bu.save(args[1], bu.getStructure(loc1, loc2));
                            p.sendMessage(args[1]+" saved!");
                            loc1 = null;
                            loc2 = null;
                        }
                        break;
                    case "gotomap":
                        Bukkit.createWorld(new WorldCreator(args[1]));
                        p.teleport(Bukkit.getWorld(args[1]).getSpawnLocation());
                        p.sendMessage("§eYou were teleported to the "+args[1]+"'s spawn!");
                        break;
                    default:
                        p.sendMessage("Command not found!");
                        break;
                }
            }
        }
        return false;
    }
}
