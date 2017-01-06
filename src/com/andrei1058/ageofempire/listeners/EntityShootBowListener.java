package com.andrei1058.ageofempire.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class EntityShootBowListener implements Listener {

    @EventHandler
    public void s(EntityShootBowEvent e){
        if (e.getProjectile() instanceof Arrow) {
            Vector v = e.getProjectile().getVelocity();
            Player p;
            if (e.getEntity() instanceof Player) {
                p = (Player) e.getEntity();
            } else {
                return;
            }
            e.setCancelled(true);
            for (ItemStack i : p.getInventory().getContents()) {
                if (i != null)
                    if (i.hasItemMeta())
                        if (i.getItemMeta().hasDisplayName())
                            if (i.getItemMeta().getDisplayName().equalsIgnoreCase("§cExplosive")) {
                                if (i.getAmount() > 1) {
                                    i.setAmount(i.getAmount() - 1);
                                } else {
                                    i.setType(Material.AIR);
                                }
                                Projectile proj = p.launchProjectile(Arrow.class, v);
                                proj.setCustomName("§cExplosive");
                            } else {
                                p.launchProjectile(Arrow.class, v);
                            }

            }
        }
    }
}
