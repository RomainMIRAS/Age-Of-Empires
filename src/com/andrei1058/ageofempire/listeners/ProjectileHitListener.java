package com.andrei1058.ageofempire.listeners;

import org.bukkit.entity.Arrow;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class ProjectileHitListener implements Listener {

    @EventHandler
    public void e(ProjectileHitEvent e){
        if (e.getEntity() instanceof Arrow) {
            Arrow a = (Arrow) e.getEntity();
            if (a.getCustomName() != null && a.getCustomName().equalsIgnoreCase("§cExplosive")) {
                e.getEntity().getWorld().createExplosion(e.getEntity().getLocation(), 0.0F, false);
            }
        }
    }
}
