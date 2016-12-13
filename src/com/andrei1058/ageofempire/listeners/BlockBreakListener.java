package com.andrei1058.ageofempire.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.UUID;

import static com.andrei1058.ageofempire.Main.*;
import static com.andrei1058.ageofempire.configuration.Messages.getMsg;

public class BlockBreakListener implements Listener {

    private static ArrayList<UUID> woodAnnounced = new ArrayList();
    private static ArrayList<UUID> goldAnnounced = new ArrayList<>();
    private static ArrayList<UUID> stoneAnnounced = new ArrayList<>();

    @EventHandler
    public void b(BlockBreakEvent e){
        if (SETUP) return;
        if (e.getBlock().getType() == Material.WOOD || e.getBlock().getData() == 1){
            e.setCancelled(true);
            e.getBlock().setType(Material.AIR);
            if (help.contains(e.getPlayer().getUniqueId())){
                if (!(woodAnnounced.contains(e.getPlayer().getUniqueId()))){
                    e.getPlayer().sendMessage(getMsg("help.cutting-wood"));
                    woodAnnounced.add(e.getPlayer().getUniqueId());
                }
                if (!goldAnnounced.contains(e.getPlayer().getUniqueId())){
                    e.getPlayer().sendMessage(getMsg("help.gold-stuff"));
                    goldAnnounced.add(e.getPlayer().getUniqueId());
                }
            }
            if (bluePlayers.contains(e.getPlayer().getUniqueId())){
                blue_wood += 5;
            } else if (greenPlayers.contains(e.getPlayer().getUniqueId())){
                green_wood += 5;
            } else if (yellowPlayers.contains(e.getPlayer().getUniqueId())){
                yellow_wood += 5;
            } else if (redPlayers.contains(e.getPlayer().getUniqueId())){
                red_wood += 5;
            }
        } else if (e.getBlock().getType() == Material.STONE && e.getBlock().getData() == 5){
            if (help.contains(e.getPlayer().getUniqueId())){
                if (!stoneAnnounced.contains(e.getPlayer().getUniqueId())){
                    e.getPlayer().sendMessage(getMsg("help.stone"));
                    stoneAnnounced.add(e.getPlayer().getUniqueId());
                }
                if (!goldAnnounced.contains(e.getPlayer().getUniqueId())){
                    e.getPlayer().sendMessage(getMsg("help.gold-stuff"));
                    goldAnnounced.add(e.getPlayer().getUniqueId());
                }
            }
            e.getBlock().breakNaturally(new ItemStack(Material.STICK));
            if (bluePlayers.contains(e.getPlayer().getUniqueId())){
                blue_stone += 3;
            } else if (greenPlayers.contains(e.getPlayer().getUniqueId())){
                green_stone += 3;
            } else if (yellowPlayers.contains(e.getPlayer().getUniqueId())){
                yellow_stone += 3;
            } else if (redPlayers.contains(e.getPlayer().getUniqueId())){
                red_stone += 3;
            }
        } else {
            if (e.getBlock().getType() == Material.MELON_BLOCK){
                return;
            }
            e.setCancelled(true);
        }
    }
}
