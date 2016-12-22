package com.andrei1058.ageofempire.listeners;

import com.andrei1058.ageofempire.locations.Locations;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import static com.andrei1058.ageofempire.Main.*;
import static com.andrei1058.ageofempire.Misc.*;
import static com.andrei1058.ageofempire.configuration.Messages.getMsg;
import static com.andrei1058.ageofempire.listeners.PlayerQuitListener.checkWinner;

public class PlayerDeathListener implements Listener {

    @EventHandler
    public void d(PlayerDeathEvent e){
        if (SETUP) return;
        Player p = e.getEntity();
        List<ItemStack> drops = e.getDrops();
        ListIterator<ItemStack> litr = drops.listIterator();
        ArrayList<Player> constructor = new ArrayList<>();
        while( litr.hasNext() ) {
            ItemStack stack = litr.next();
            if (stack.getType().equals(Material.PAPER) || stack.getType().equals(Material.MOB_SPAWNER)
                    || stack.getType() == Material.STONE_AXE || stack.getType() == Material.STONE_PICKAXE
                    || stack.getType() == Material.LEATHER_BOOTS || stack.getType() == Material.LEATHER_CHESTPLATE
                    || stack.getType() == Material.LEATHER_HELMET || stack.getType() == Material.LEATHER_LEGGINGS) {
                litr.remove();
            }
            if (stack.getType() == Material.SPRUCE_DOOR_ITEM){
                constructor.add(p);
            }
        }
        if (e.getEntity().getKiller() instanceof Player) {
            e.setDeathMessage(getMsg("new-kill").replace("{player}", e.getEntity().getName()).replace("{killer}", e.getEntity().getKiller().getName()));
        }else if (e.getEntity().getKiller() instanceof Projectile) {
            Projectile proj = (Projectile) e.getEntity().getKiller();
            Player pl = (Player) proj.getShooter();
            e.setDeathMessage(getMsg("new-kill").replace("{player}", e.getEntity().getName()).replace("{killer}", pl.getName()));
        }
        e.getEntity().spigot().respawn();
        Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
            @Override
            public void run() {
                if (bluePlayers.contains(p.getUniqueId())){
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
                } else if (greenPlayers.contains(p.getUniqueId())){
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
                } else if (yellowPlayers.contains(p.getUniqueId())){
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
                } else if (redPlayers.contains(p.getUniqueId())){
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
                    players.remove(p.getUniqueId());
                }
                checkWinner();
            }
        }, 100);
    }
}
