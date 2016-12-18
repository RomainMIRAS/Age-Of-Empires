package com.andrei1058.ageofempire.listeners;

import com.andrei1058.ageofempire.game.OreHologram;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Random;
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
        if (e.getBlock().getType() == Material.LOG && e.getBlock().getData() == 13 || e.getBlock().getType() == Material.LOG_2 && e.getBlock().getData() == 13){ //6
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
            int gold = 0;
            Random r = new Random();
            gold = r.nextInt(2);
            new OreHologram(e.getBlock().getLocation(), gold, false);
            if (bluePlayers.contains(e.getPlayer().getUniqueId())){
                blue_wood += 5;
                blue_gold += gold;
            } else if (greenPlayers.contains(e.getPlayer().getUniqueId())){
                green_wood += 5;
                green_gold += gold;
            } else if (yellowPlayers.contains(e.getPlayer().getUniqueId())){
                yellow_wood += 5;
                yellow_gold += gold;
            } else if (redPlayers.contains(e.getPlayer().getUniqueId())){
                red_wood += 5;
                red_gold += gold;
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
            int gold = 0;
            Random r = new Random();
            gold = r.nextInt(2);
            new OreHologram(e.getBlock().getLocation(), gold, true);
            if (bluePlayers.contains(e.getPlayer().getUniqueId())){
                blue_stone += 3;
                blue_gold += gold;
            } else if (greenPlayers.contains(e.getPlayer().getUniqueId())){
                green_stone += 3;
                green_gold += gold;
            } else if (yellowPlayers.contains(e.getPlayer().getUniqueId())){
                yellow_stone += 3;
                yellow_gold += gold;
            } else if (redPlayers.contains(e.getPlayer().getUniqueId())){
                red_stone += 3;
                red_gold += gold;
            }
        } else {
            if (e.getBlock().getType() == Material.MELON_BLOCK){
                return;
            }
            e.getPlayer().sendMessage(getMsg("cant-break"));
            e.setCancelled(true);
        }
    }
}
