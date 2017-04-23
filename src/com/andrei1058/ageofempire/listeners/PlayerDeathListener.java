package com.andrei1058.ageofempire.listeners;

import org.bukkit.Bukkit;
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
import static com.andrei1058.ageofempire.configuration.Messages.getMsg;

public class PlayerDeathListener implements Listener {

    public static ArrayList<Player> constructor = new ArrayList<>();
    @EventHandler
    public void d(PlayerDeathEvent e){
        if (SETUP) return;
        Player p = e.getEntity();
        List<ItemStack> drops = e.getDrops();
        ListIterator<ItemStack> litr = drops.listIterator();
        while( litr.hasNext() ) {
            ItemStack stack = litr.next();
            if (stack.getType() == Material.SPRUCE_DOOR_ITEM){
                constructor.add(p);
            }
            if (stack.getType().equals(Material.PAPER) || stack.getType().equals(Material.MOB_SPAWNER)
                    || stack.getType() == Material.STONE_AXE || stack.getType() == Material.STONE_PICKAXE
                    || stack.getType() == Material.LEATHER_BOOTS || stack.getType() == Material.LEATHER_CHESTPLATE
                    || stack.getType() == Material.LEATHER_HELMET || stack.getType() == Material.LEATHER_LEGGINGS
                    || stack.getType() == Material.SPRUCE_DOOR_ITEM) {
                litr.remove();
            }
        }
        if (e.getEntity().getKiller() instanceof Player) {
            e.setDeathMessage(getMsg("new-kill").replace("{player}", e.getEntity().getName()).replace("{killer}", e.getEntity().getKiller().getName()));
            addKill(e.getEntity().getKiller());
            addDeath(e.getEntity());
        } else if (e.getEntity().getKiller() instanceof Projectile) {
            Projectile proj = (Projectile) e.getEntity().getKiller();
            Player pl = (Player) proj.getShooter();
            e.setDeathMessage(getMsg("new-kill").replace("{player}", e.getEntity().getName()).replace("{killer}", pl.getName()));
            addKill(pl);
            addDeath(e.getEntity());
        } else {
            addDeath(e.getEntity());
            e.setDeathMessage(getMsg("player-died").replace("{player}", e.getEntity().getDisplayName()));
        }
        Bukkit.getScheduler().runTaskLater(plugin, () -> p.spigot().respawn(), 2L);
    }
}
