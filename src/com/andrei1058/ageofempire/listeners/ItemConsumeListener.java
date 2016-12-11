package com.andrei1058.ageofempire.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class ItemConsumeListener implements Listener {

    @EventHandler
    public void c(PlayerItemConsumeEvent e){
        if (e.getItem().getType() == Material.STONE_AXE || e.getItem().getType() == Material.STONE_PICKAXE){
            e.setCancelled(true);
        }
    }
}
