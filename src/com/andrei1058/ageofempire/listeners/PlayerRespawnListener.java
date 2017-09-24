package com.andrei1058.ageofempire.listeners;

import com.andrei1058.ageofempire.locations.Locations;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

import static com.andrei1058.ageofempire.Main.*;
import static com.andrei1058.ageofempire.Misc.*;
import static com.andrei1058.ageofempire.listeners.PlayerDeathListener.constructor;

public class PlayerRespawnListener implements Listener {

    @EventHandler
    public void r(PlayerRespawnEvent e){
        if (SETUP) return;
        Player p = e.getPlayer();
        e.setRespawnLocation(p.getLocation());
        p.setGameMode(GameMode.SPECTATOR);

        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            if (bluePlayers.contains(p)){
                p.teleport(Locations.getLoc("Spawns."+choosenMap+".Blue"));
                p.setGameMode(GameMode.SURVIVAL);
                p.getInventory().setHelmet(leatherArmor(Material.LEATHER_HELMET, Color.BLUE));
                p.getInventory().setChestplate(leatherArmor(Material.LEATHER_CHESTPLATE, Color.BLUE));
                p.getInventory().setBoots(leatherArmor(Material.LEATHER_BOOTS, Color.BLUE));
                p.getInventory().setLeggings(leatherArmor(Material.LEATHER_LEGGINGS, Color.BLUE));
                p.getInventory().addItem(new ItemStack(Material.STONE_PICKAXE));
                p.getInventory().addItem(new ItemStack(Material.STONE_AXE));
                p.getInventory().setItem(8, slotlocked());
                if (constructor.contains(p)){
                    constructor.remove(p);
                    p.getInventory().setItem(7, constructor());
                } else {
                    p.getInventory().setItem(7, slotlocked());
                }
                p.getInventory().setItem(6, forumPaper());
            } else if (greenPlayers.contains(p)){
                p.teleport(Locations.getLoc("Spawns."+choosenMap+".Green"));
                p.setGameMode(GameMode.SURVIVAL);
                p.getInventory().setHelmet(leatherArmor(Material.LEATHER_HELMET, Color.GREEN));
                p.getInventory().setChestplate(leatherArmor(Material.LEATHER_CHESTPLATE, Color.GREEN));
                p.getInventory().setBoots(leatherArmor(Material.LEATHER_BOOTS, Color.GREEN));
                p.getInventory().setLeggings(leatherArmor(Material.LEATHER_LEGGINGS, Color.GREEN));
                p.getInventory().addItem(new ItemStack(Material.STONE_PICKAXE));
                p.getInventory().addItem(new ItemStack(Material.STONE_AXE));
                p.getInventory().setItem(8, slotlocked());
                if (constructor.contains(p)){
                    constructor.remove(p);
                    p.getInventory().setItem(7, constructor());
                } else {
                    p.getInventory().setItem(7, slotlocked());
                }
                p.getInventory().setItem(6, forumPaper());
            } else if (yellowPlayers.contains(p)){
                p.teleport(Locations.getLoc("Spawns."+choosenMap+".Yellow"));
                p.setGameMode(GameMode.SURVIVAL);
                p.getInventory().setHelmet(leatherArmor(Material.LEATHER_HELMET, Color.YELLOW));
                p.getInventory().setChestplate(leatherArmor(Material.LEATHER_CHESTPLATE, Color.YELLOW));
                p.getInventory().setBoots(leatherArmor(Material.LEATHER_BOOTS, Color.YELLOW));
                p.getInventory().setLeggings(leatherArmor(Material.LEATHER_LEGGINGS, Color.YELLOW));
                p.getInventory().addItem(new ItemStack(Material.STONE_PICKAXE));
                p.getInventory().addItem(new ItemStack(Material.STONE_AXE));
                p.getInventory().setItem(8, slotlocked());
                if (constructor.contains(p)){
                    constructor.remove(p);
                    p.getInventory().setItem(7, constructor());
                } else {
                    p.getInventory().setItem(7, slotlocked());
                }
                p.getInventory().setItem(6, forumPaper());
            } else if (redPlayers.contains(p)){
                p.teleport(Locations.getLoc("Spawns."+choosenMap+".Red"));
                p.setGameMode(GameMode.SURVIVAL);
                p.getInventory().setHelmet(leatherArmor(Material.LEATHER_HELMET, Color.RED));
                p.getInventory().setChestplate(leatherArmor(Material.LEATHER_CHESTPLATE, Color.RED));
                p.getInventory().setBoots(leatherArmor(Material.LEATHER_BOOTS, Color.RED));
                p.getInventory().setLeggings(leatherArmor(Material.LEATHER_LEGGINGS, Color.RED));
                p.getInventory().addItem(new ItemStack(Material.STONE_PICKAXE));
                p.getInventory().addItem(new ItemStack(Material.STONE_AXE));
                p.getInventory().setItem(8, slotlocked());
                if (constructor.contains(p)){
                    constructor.remove(p);
                    p.getInventory().setItem(7, constructor());
                } else {
                    p.getInventory().setItem(7, slotlocked());
                }
                p.getInventory().setItem(6, forumPaper());
            } else {
                players.remove(p);
            }
        }, 100);
    }
}
