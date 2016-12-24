package com.andrei1058.ageofempire.listeners;

import com.andrei1058.ageofempire.Main;
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
import static com.andrei1058.ageofempire.game.Buildings.*;

public class EntityDamageByEntityListener implements Listener {

    @EventHandler
    public void d(EntityDamageByEntityEvent e){
        if (SETUP) return;
        if (STATUS != Status.PLAYING){
            e.setCancelled(true);
        }
        if (!pvp){
            e.setCancelled(true);
            e.getDamager().sendMessage(getMsg("pvp-disabled"));
            return;
        }
        if (e.getEntity().getType() == EntityType.VILLAGER && e.getDamager().getType() == EntityType.PLAYER){
            Villager v = (Villager) e.getEntity();
            if (!assualt){
                e.setCancelled(true);
                return;
            }
            if (v.getCustomName() != null){
                v.setCustomName("§9"+(int)v.getHealth());
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
            } else if (v == blue_villager){
                if (bluePlayers.contains(e.getDamager().getUniqueId())){
                    e.setCancelled(true);
                    e.getDamager().sendMessage(getMsg("forum.violence"));
                    return;
                }
                for (UUID u : bluePlayers){
                    Titles.sendFullTitle(Bukkit.getPlayer(u), 0, 20, 0, "",getMsg("villager.forum-attacked"));
                }
            } else if (v == green_villager){
                if (greenPlayers.contains(e.getDamager().getUniqueId())){
                    e.setCancelled(true);
                    e.getDamager().sendMessage(getMsg("forum.violence"));
                    return;
                }
                for (UUID u : greenPlayers){
                    Titles.sendFullTitle(Bukkit.getPlayer(u), 0, 20, 0, "",getMsg("villager.forum-attacked"));
                }
            } else if (v == red_villager){
                if (redPlayers.contains(e.getDamager().getUniqueId())){
                    e.setCancelled(true);
                    e.getDamager().sendMessage(getMsg("forum.violence"));
                    return;
                }
                for (UUID u : redPlayers){
                    Titles.sendFullTitle(Bukkit.getPlayer(u), 0, 20, 0, "",getMsg("villager.forum-attacked"));
                }
            } else if (v == blue_forge){
                if (bluePlayers.contains(e.getDamager())){
                    e.getDamager().sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (UUID u : bluePlayers){
                    Titles.sendFullTitle(Bukkit.getPlayer(u), 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+forge+".displayname")));
                }
            } else if (v == green_forge){
                if (greenPlayers.contains(e.getDamager())){
                    e.getDamager().sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (UUID u : greenPlayers){
                    Titles.sendFullTitle(Bukkit.getPlayer(u), 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+forge+".displayname")));
                }
            } else if (v == yellow_forge){
                if (yellowPlayers.contains(e.getDamager())){
                    e.getDamager().sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (UUID u : yellowPlayers){
                    Titles.sendFullTitle(Bukkit.getPlayer(u), 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+forge+".displayname")));
                }
            } else if (v == red_forge){
                if (redPlayers.contains(e.getDamager())){
                    e.getDamager().sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (UUID u : redPlayers){
                    Titles.sendFullTitle(Bukkit.getPlayer(u), 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+forge+".displayname")));
                }
            } else if (v == blue_smine){
                if (bluePlayers.contains(e.getDamager())){
                    e.getDamager().sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (UUID u : bluePlayers){
                    Titles.sendFullTitle(Bukkit.getPlayer(u), 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+stone_mine+".displayname")));
                }
            } else if (v == green_smine){
                if (greenPlayers.contains(e.getDamager())){
                    e.getDamager().sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (UUID u : greenPlayers){
                    Titles.sendFullTitle(Bukkit.getPlayer(u), 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+stone_mine+".displayname")));
                }
            } else if (v == yellow_smine){
                if (yellowPlayers.contains(e.getDamager())){
                    e.getDamager().sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (UUID u : yellowPlayers){
                    Titles.sendFullTitle(Bukkit.getPlayer(u), 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+stone_mine+".displayname")));
                }
            } else if (v == red_smine){
                if (redPlayers.contains(e.getDamager())){
                    e.getDamager().sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (UUID u : redPlayers){
                    Titles.sendFullTitle(Bukkit.getPlayer(u), 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+stone_mine+".displayname")));
                }
            } else if (v == blue_gmine){
                if (bluePlayers.contains(e.getDamager())){
                    e.getDamager().sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (UUID u : bluePlayers){
                    Titles.sendFullTitle(Bukkit.getPlayer(u), 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+gold_mine+".displayname")));
                }
            } else if (v == green_gmine){
                if (greenPlayers.contains(e.getDamager())){
                    e.getDamager().sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (UUID u : greenPlayers){
                    Titles.sendFullTitle(Bukkit.getPlayer(u), 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+gold_mine+".displayname")));
                }
            } else if (v == yellow_gmine){
                if (yellowPlayers.contains(e.getDamager())){
                    e.getDamager().sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (UUID u : yellowPlayers){
                    Titles.sendFullTitle(Bukkit.getPlayer(u), 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+gold_mine+".displayname")));
                }
            } else if (v == red_gmine){
                if (redPlayers.contains(e.getDamager())){
                    e.getDamager().sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (UUID u : redPlayers){
                    Titles.sendFullTitle(Bukkit.getPlayer(u), 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+gold_mine+".displayname")));
                }
            } else if (v == blue_mill){
                if (bluePlayers.contains(e.getDamager())){
                    e.getDamager().sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (UUID u : bluePlayers){
                    Titles.sendFullTitle(Bukkit.getPlayer(u), 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+mill+".displayname")));
                }
            } else if (v == green_mill){
                if (greenPlayers.contains(e.getDamager())){
                    e.getDamager().sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (UUID u : greenPlayers){
                    Titles.sendFullTitle(Bukkit.getPlayer(u), 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+mill+".displayname")));
                }
            } else if (v == yellow_mill){
                if (yellowPlayers.contains(e.getDamager())){
                    e.getDamager().sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (UUID u : yellowPlayers){
                    Titles.sendFullTitle(Bukkit.getPlayer(u), 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+mill+".displayname")));
                }
            } else if (v == red_mill){
                if (redPlayers.contains(e.getDamager())){
                    e.getDamager().sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (UUID u : redPlayers){
                    Titles.sendFullTitle(Bukkit.getPlayer(u), 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+mill+".displayname")));
                }
            } else if (v == blue_vsawmill){
                if (bluePlayers.contains(e.getDamager())){
                    e.getDamager().sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (UUID u : bluePlayers){
                    Titles.sendFullTitle(Bukkit.getPlayer(u), 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+sawmill+".displayname")));
                }
            } else if (v == green_vsawmill){
                if (greenPlayers.contains(e.getDamager())){
                    e.getDamager().sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (UUID u : greenPlayers){
                    Titles.sendFullTitle(Bukkit.getPlayer(u), 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+sawmill+".displayname")));
                }
            } else if (v == yellow_vsawmill){
                if (yellowPlayers.contains(e.getDamager())){
                    e.getDamager().sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (UUID u : yellowPlayers){
                    Titles.sendFullTitle(Bukkit.getPlayer(u), 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+sawmill+".displayname")));
                }
            } else if (v == red_vsawmill){
                if (redPlayers.contains(e.getDamager())){
                    e.getDamager().sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (UUID u : redPlayers){
                    Titles.sendFullTitle(Bukkit.getPlayer(u), 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+sawmill+".displayname")));
                }
            } else if (v == blue_workshop){
                if (bluePlayers.contains(e.getDamager())){
                    e.getDamager().sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (UUID u : bluePlayers){
                    Titles.sendFullTitle(Bukkit.getPlayer(u), 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+workshop+".displayname")));
                }
            } else if (v == green_workshop){
                if (greenPlayers.contains(e.getDamager())){
                    e.getDamager().sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (UUID u : greenPlayers){
                    Titles.sendFullTitle(Bukkit.getPlayer(u), 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+workshop+".displayname")));
                }
            } else if (v == yellow_workshop){
                if (yellowPlayers.contains(e.getDamager())){
                    e.getDamager().sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (UUID u : yellowPlayers){
                    Titles.sendFullTitle(Bukkit.getPlayer(u), 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+workshop+".displayname")));
                }
            } else if (v == red_workshop){
                if (redPlayers.contains(e.getDamager())){
                    e.getDamager().sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (UUID u : redPlayers){
                    Titles.sendFullTitle(Bukkit.getPlayer(u), 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+workshop+".displayname")));
                }
            } else if (v == blue_market){
                if (bluePlayers.contains(e.getDamager())){
                    e.getDamager().sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (UUID u : bluePlayers){
                    Titles.sendFullTitle(Bukkit.getPlayer(u), 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+market+".displayname")));
                }
            } else if (v == green_market){
                if (greenPlayers.contains(e.getDamager())){
                    e.getDamager().sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (UUID u : greenPlayers){
                    Titles.sendFullTitle(Bukkit.getPlayer(u), 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+market+".displayname")));
                }
            } else if (v == yellow_market){
                if (yellowPlayers.contains(e.getDamager())){
                    e.getDamager().sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (UUID u : yellowPlayers){
                    Titles.sendFullTitle(Bukkit.getPlayer(u), 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+market+".displayname")));
                }
            } else if (v == red_market){
                if (redPlayers.contains(e.getDamager())){
                    e.getDamager().sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (UUID u : redPlayers){
                    Titles.sendFullTitle(Bukkit.getPlayer(u), 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+market+".displayname")));
                }
            } else if (v == blue_sabotage){
                if (bluePlayers.contains(e.getDamager())){
                    e.getDamager().sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (UUID u : bluePlayers){
                    Titles.sendFullTitle(Bukkit.getPlayer(u), 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+sabotage+".displayname")));
                }
            } else if (v == green_sabotage){
                if (greenPlayers.contains(e.getDamager())){
                    e.getDamager().sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (UUID u : greenPlayers){
                    Titles.sendFullTitle(Bukkit.getPlayer(u), 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+sabotage+".displayname")));
                }
            } else if (v == yellow_sabotage){
                if (yellowPlayers.contains(e.getDamager())){
                    e.getDamager().sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (UUID u : yellowPlayers){
                    Titles.sendFullTitle(Bukkit.getPlayer(u), 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+sabotage+".displayname")));
                }
            } else if (v == red_sabotage){
                if (redPlayers.contains(e.getDamager())){
                    e.getDamager().sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (UUID u : redPlayers){
                    Titles.sendFullTitle(Bukkit.getPlayer(u), 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+sabotage+".displayname")));
                }
            } else if (v == blue_kennel){
                if (bluePlayers.contains(e.getDamager())){
                    e.getDamager().sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (UUID u : bluePlayers){
                    Titles.sendFullTitle(Bukkit.getPlayer(u), 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+kennel+".displayname")));
                }
            } else if (v == yellow_kennel){
                if (yellowPlayers.contains(e.getDamager())){
                    e.getDamager().sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (UUID u : yellowPlayers){
                    Titles.sendFullTitle(Bukkit.getPlayer(u), 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+kennel+".displayname")));
                }
            } else if (v == green_kennel){
                if (greenPlayers.contains(e.getDamager())){
                    e.getDamager().sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (UUID u : greenPlayers){
                    Titles.sendFullTitle(Bukkit.getPlayer(u), 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+kennel+".displayname")));
                }
            } else if (v == red_kennel){
                if (redPlayers.contains(e.getDamager())){
                    e.getDamager().sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (UUID u : redPlayers){
                    Titles.sendFullTitle(Bukkit.getPlayer(u), 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+kennel+".displayname")));
                }
            }
        }
    }
}