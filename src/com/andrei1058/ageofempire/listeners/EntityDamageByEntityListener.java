package com.andrei1058.ageofempire.listeners;

import com.andrei1058.ageofempire.game.Action;
import com.andrei1058.ageofempire.game.Status;
import com.andrei1058.ageofempire.game.Titles;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.UUID;

import static com.andrei1058.ageofempire.Main.*;
import static com.andrei1058.ageofempire.configuration.Messages.getMsg;

public class EntityDamageByEntityListener implements Listener {

    @EventHandler
    public void d(EntityDamageByEntityEvent e){
        if (SETUP) return;
        if (STATUS != Status.PLAYING){
            e.setCancelled(true);
        }
        if (!pvp){
            e.setCancelled(true);
        }
        if (e.getEntity().getType() == EntityType.VILLAGER && e.getDamager().getType() == EntityType.PLAYER){
            Villager v = (Villager) e.getEntity();
            if (!assualt){
                e.setCancelled(true);
                return;
            }
            if (v == yellow_villager){
                if (yellowPlayers.contains(e.getDamager().getUniqueId())){
                    e.setCancelled(true);
                    e.getDamager().sendMessage(getMsg("forum.violence"));
                    return;
                }
                for (UUID u : yellowPlayers){
                    Titles.sendFullTitle(Bukkit.getPlayer(u), 0, 20, 0, "",getMsg("villager.forum-attacked"));
                }
                v.setCustomName("§9"+v.getHealth());
            } else if (v == blue_villager){
                if (bluePlayers.contains(e.getDamager().getUniqueId())){
                    e.setCancelled(true);
                    e.getDamager().sendMessage(getMsg("forum.violence"));
                    return;
                }
                for (UUID u : bluePlayers){
                    Titles.sendFullTitle(Bukkit.getPlayer(u), 0, 20, 0, "",getMsg("villager.forum-attacked"));
                }
                v.setCustomName("§9"+v.getHealth());
            } else if (v == green_villager){
                if (greenPlayers.contains(e.getDamager().getUniqueId())){
                    e.setCancelled(true);
                    e.getDamager().sendMessage(getMsg("forum.violence"));
                    return;
                }
                for (UUID u : greenPlayers){
                    Titles.sendFullTitle(Bukkit.getPlayer(u), 0, 20, 0, "",getMsg("villager.forum-attacked"));
                }
                v.setCustomName("§9"+v.getHealth());
            } else if (v == red_villager){
                if (redPlayers.contains(e.getDamager().getUniqueId())){
                    e.setCancelled(true);
                    e.getDamager().sendMessage(getMsg("forum.violence"));
                    return;
                }
                for (UUID u : redPlayers){
                    Titles.sendFullTitle(Bukkit.getPlayer(u), 0, 20, 0, "",getMsg("villager.forum-attacked"));
                }
                v.setCustomName("§9"+v.getHealth());
            }
        }
    }
}