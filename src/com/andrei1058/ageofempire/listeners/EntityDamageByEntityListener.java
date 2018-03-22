package com.andrei1058.ageofempire.listeners;

import com.andrei1058.ageofempire.configuration.Messages;
import com.andrei1058.ageofempire.game.Status;
import com.andrei1058.ageofempire.game.Titles;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import static com.andrei1058.ageofempire.Main.*;
import static com.andrei1058.ageofempire.commands.Stuck.stuck;
import static com.andrei1058.ageofempire.configuration.Messages.getMsg;
import static com.andrei1058.ageofempire.game.Buildings.*;

public class EntityDamageByEntityListener implements Listener {

    @EventHandler
    public void d(EntityDamageByEntityEvent e){
        if (SETUP) return;
        if (STATUS != Status.PLAYING){
            e.setCancelled(true);
            return;
        }
        if (!pvp){
            e.setCancelled(true);
            e.getDamager().sendMessage(getMsg("pvp-disabled"));
            return;
        }
        if (e.getEntity() instanceof Player && e.getDamager() instanceof Player){
            if (bluePlayers.contains(e.getEntity()) && bluePlayers.contains(e.getDamager())){
                e.setCancelled(true);
            } else if (greenPlayers.contains(e.getEntity()) && greenPlayers.contains(e.getDamager())){
                e.setCancelled(true);
            } else if (yellowPlayers.contains(e.getEntity()) && yellowPlayers.contains(e.getDamager())){
                e.setCancelled(true);
            } else if (redPlayers.contains(e.getEntity()) && redPlayers.contains(e.getDamager())){
                e.setCancelled(true);
            }
            if (stuck.containsKey(e.getEntity())) {
                stuck.remove(e.getEntity());
                e.getEntity().sendMessage(Messages.getMsg("stuckMove"));
            }
        }
        if (stuck.containsKey(e.getDamager())) {
            stuck.remove(e.getDamager());
            e.getDamager().sendMessage(Messages.getMsg("stuckMove"));
        }
        if (e.getEntity().getType() == EntityType.VILLAGER){
            Player p;
            if (e.getDamager() instanceof Player){
                p = (Player) e.getDamager();
            } else if (e.getDamager() instanceof Projectile){
                Projectile proj = (Projectile) e.getDamager();
                p = (Player) proj.getShooter();
            } else if (e.getDamager() instanceof Wolf){
                Wolf w = (Wolf) e.getDamager();
                p = (Player) w.getOwner();
            } else {
                e.setCancelled(true);
                return;
            }
            Villager v = (Villager) e.getEntity();
            if (!assualt){
                e.setCancelled(true);
                return;
            }
            if (v.getCustomName() != null){
                v.setCustomName("§9"+(int)v.getHealth());
            }
            if (v == yellow_villager){
                if (yellowPlayers.contains(p)){
                    e.setCancelled(true);
                    p.sendMessage(getMsg("forum.violence"));
                    return;
                }
                for (Player u : yellowPlayers){
                    Titles.sendFullTitle(u, 0, 20, 0, "",getMsg("villager.forum-attacked"));
                }
            } else if (v == blue_villager){
                if (bluePlayers.contains(p)){
                    e.setCancelled(true);
                    p.sendMessage(getMsg("forum.violence"));
                    return;
                }
                for (Player u : bluePlayers){
                    Titles.sendFullTitle(u, 0, 20, 0, "",getMsg("villager.forum-attacked"));
                }
            } else if (v == green_villager){
                if (greenPlayers.contains(p)){
                    e.setCancelled(true);
                    p.sendMessage(getMsg("forum.violence"));
                    return;
                }
                for (Player u : greenPlayers){
                    Titles.sendFullTitle(u, 0, 20, 0, "",getMsg("villager.forum-attacked"));
                }
            } else if (v == red_villager){
                if (redPlayers.contains(p)){
                    e.setCancelled(true);
                    p.sendMessage(getMsg("forum.violence"));
                    return;
                }
                for (Player u : redPlayers){
                    Titles.sendFullTitle(u, 0, 20, 0, "",getMsg("villager.forum-attacked"));
                }
            } else if (v == blue_forge){
                if (bluePlayers.contains(p)){
                    p.sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (Player u : bluePlayers){
                    Titles.sendFullTitle(u, 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+forge+".displayname")));
                }
            } else if (v == green_forge){
                if (greenPlayers.contains(p)){
                    p.sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (Player u : greenPlayers){
                    Titles.sendFullTitle(u, 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+forge+".displayname")));
                }
            } else if (v == yellow_forge){
                if (yellowPlayers.contains(p)){
                    p.sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (Player u : yellowPlayers){
                    Titles.sendFullTitle(u, 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+forge+".displayname")));
                }
            } else if (v == red_forge){
                if (redPlayers.contains(p)){
                    p.sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (Player u : redPlayers){
                    Titles.sendFullTitle(u, 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+forge+".displayname")));
                }
            } else if (v == blue_smine){
                if (bluePlayers.contains(p)){
                    p.sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (Player u : bluePlayers){
                    Titles.sendFullTitle(u, 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+stone_mine+".displayname")));
                }
            } else if (v == green_smine){
                if (greenPlayers.contains(p)){
                    p.sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (Player u : greenPlayers){
                    Titles.sendFullTitle(u, 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+stone_mine+".displayname")));
                }
            } else if (v == yellow_smine){
                if (yellowPlayers.contains(p)){
                    p.sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (Player u : yellowPlayers){
                    Titles.sendFullTitle(u, 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+stone_mine+".displayname")));
                }
            } else if (v == red_smine){
                if (redPlayers.contains(p)){
                    p.sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (Player u : redPlayers){
                    Titles.sendFullTitle(u, 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+stone_mine+".displayname")));
                }
            } else if (v == blue_gmine){
                if (bluePlayers.contains(p)){
                    p.sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (Player u : bluePlayers){
                    Titles.sendFullTitle(u, 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+gold_mine+".displayname")));
                }
            } else if (v == green_gmine){
                if (greenPlayers.contains(p)){
                    p.sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (Player u : greenPlayers){
                    Titles.sendFullTitle(u, 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+gold_mine+".displayname")));
                }
            } else if (v == yellow_gmine){
                if (yellowPlayers.contains(p)){
                    p.sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (Player u : yellowPlayers){
                    Titles.sendFullTitle(u, 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+gold_mine+".displayname")));
                }
            } else if (v == red_gmine){
                if (redPlayers.contains(p)){
                    p.sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (Player u : redPlayers){
                    Titles.sendFullTitle(u, 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+gold_mine+".displayname")));
                }
            } else if (v == blue_mill){
                if (bluePlayers.contains(p)){
                    p.sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (Player u : bluePlayers){
                    Titles.sendFullTitle(u, 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+mill+".displayname")));
                }
            } else if (v == green_mill){
                if (greenPlayers.contains(p)){
                    p.sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (Player u : greenPlayers){
                    Titles.sendFullTitle(u, 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+mill+".displayname")));
                }
            } else if (v == yellow_mill){
                if (yellowPlayers.contains(p)){
                    p.sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (Player u : yellowPlayers){
                    Titles.sendFullTitle(u, 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+mill+".displayname")));
                }
            } else if (v == red_mill){
                if (redPlayers.contains(p)){
                    p.sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (Player u : redPlayers){
                    Titles.sendFullTitle(u, 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+mill+".displayname")));
                }
            } else if (v == blue_vsawmill){
                if (bluePlayers.contains(p)){
                    p.sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (Player u : bluePlayers){
                    Titles.sendFullTitle(u, 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+sawmill+".displayname")));
                }
            } else if (v == green_vsawmill){
                if (greenPlayers.contains(p)){
                    p.sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (Player u : greenPlayers){
                    Titles.sendFullTitle(u, 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+sawmill+".displayname")));
                }
            } else if (v == yellow_vsawmill){
                if (yellowPlayers.contains(p)){
                    p.sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (Player u : yellowPlayers){
                    Titles.sendFullTitle(u, 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+sawmill+".displayname")));
                }
            } else if (v == red_vsawmill){
                if (redPlayers.contains(p)){
                    p.sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (Player u : redPlayers){
                    Titles.sendFullTitle(u, 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+sawmill+".displayname")));
                }
            } else if (v == blue_workshop){
                if (bluePlayers.contains(p)){
                    p.sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (Player u : bluePlayers){
                    Titles.sendFullTitle(u, 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+workshop+".displayname")));
                }
            } else if (v == green_workshop){
                if (greenPlayers.contains(p)){
                    p.sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (Player u : greenPlayers){
                    Titles.sendFullTitle(u, 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+workshop+".displayname")));
                }
            } else if (v == yellow_workshop){
                if (yellowPlayers.contains(p)){
                    p.sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (Player u : yellowPlayers){
                    Titles.sendFullTitle(u, 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+workshop+".displayname")));
                }
            } else if (v == red_workshop){
                if (redPlayers.contains(p)){
                    p.sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (Player u : redPlayers){
                    Titles.sendFullTitle(u, 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+workshop+".displayname")));
                }
            } else if (v == blue_market){
                if (bluePlayers.contains(p)){
                    p.sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (Player u : bluePlayers){
                    Titles.sendFullTitle(u, 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+market+".displayname")));
                }
            } else if (v == green_market){
                if (greenPlayers.contains(p)){
                    p.sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (Player u : greenPlayers){
                    Titles.sendFullTitle(u, 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+market+".displayname")));
                }
            } else if (v == yellow_market){
                if (yellowPlayers.contains(p)){
                    p.sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (Player u : yellowPlayers){
                    Titles.sendFullTitle(u, 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+market+".displayname")));
                }
            } else if (v == red_market){
                if (redPlayers.contains(p)){
                    p.sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (Player u : redPlayers){
                    Titles.sendFullTitle(u, 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+market+".displayname")));
                }
            } else if (v == blue_sabotage){
                if (bluePlayers.contains(p)){
                    p.sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (Player u : bluePlayers){
                    Titles.sendFullTitle(u, 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+sabotage+".displayname")));
                }
            } else if (v == green_sabotage){
                if (greenPlayers.contains(p)){
                    p.sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (Player u : greenPlayers){
                    Titles.sendFullTitle(u, 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+sabotage+".displayname")));
                }
            } else if (v == yellow_sabotage){
                if (yellowPlayers.contains(p)){
                    p.sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (Player u : yellowPlayers){
                    Titles.sendFullTitle(u, 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+sabotage+".displayname")));
                }
            } else if (v == red_sabotage){
                if (redPlayers.contains(p)){
                    p.sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (Player u : redPlayers){
                    Titles.sendFullTitle(u, 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+sabotage+".displayname")));
                }
            } else if (v == blue_kennel){
                if (bluePlayers.contains(p)){
                    p.sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (Player u : bluePlayers){
                    Titles.sendFullTitle(u, 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+kennel+".displayname")));
                }
            } else if (v == yellow_kennel){
                if (yellowPlayers.contains(p)){
                    p.sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (Player u : yellowPlayers){
                    Titles.sendFullTitle(u, 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+kennel+".displayname")));
                }
            } else if (v == green_kennel){
                if (greenPlayers.contains(p)){
                    p.sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (Player u : greenPlayers){
                    Titles.sendFullTitle(u, 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+kennel+".displayname")));
                }
            } else if (v == red_kennel){
                if (redPlayers.contains(p)){
                    p.sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (Player u : redPlayers){
                    Titles.sendFullTitle(u, 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+kennel+".displayname")));
                }
            } else if (v == blue_archery){
                if (bluePlayers.contains(p)){
                    p.sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (Player u : bluePlayers){
                    Titles.sendFullTitle(u, 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+archery+".displayname")));
                }
            } else if (v == green_archery){
                if (greenPlayers.contains(p)){
                    p.sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (Player u : greenPlayers){
                    Titles.sendFullTitle(u, 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+archery+".displayname")));
                }
            } else if (v == yellow_archery){
                if (yellowPlayers.contains(p)){
                    p.sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (Player u : yellowPlayers){
                    Titles.sendFullTitle(u, 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+archery+".displayname")));
                }
            } else if (v == red_archery){
                if (redPlayers.contains(p)){
                    p.sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (Player u : redPlayers){
                    Titles.sendFullTitle(u, 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+archery+".displayname")));
                }
            } else if (v == blue_trifarrow){
                if (bluePlayers.contains(p)){
                    p.sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (Player u : bluePlayers){
                    Titles.sendFullTitle(u, 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+trifarrow+".displayname")));
                }
            } else if (v == green_trifarrow){
                if (greenPlayers.contains(p)){
                    p.sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (Player u : greenPlayers){
                    Titles.sendFullTitle(u, 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+trifarrow+".displayname")));
                }
            } else if (v == yellow_trifarrow){
                if (yellowPlayers.contains(p)){
                    p.sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (Player u : yellowPlayers){
                    Titles.sendFullTitle(u, 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+trifarrow+".displayname")));
                }
            } else if (v == red_trifarrow){
                if (redPlayers.contains(p)){
                    p.sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (Player u : redPlayers){
                    Titles.sendFullTitle(u, 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+trifarrow+".displayname")));
                }
            } else if (v == blue_stable){
                if (bluePlayers.contains(p)){
                    p.sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (Player u : bluePlayers){
                    Titles.sendFullTitle(u, 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+stable+".displayname")));
                }
            } else if (v == green_stable){
                if (greenPlayers.contains(p)){
                    p.sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (Player u : greenPlayers){
                    Titles.sendFullTitle(u, 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+stable+".displayname")));
                }
            } else if (v == yellow_stable){
                if (yellowPlayers.contains(p)){
                    p.sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (Player u : yellowPlayers){
                    Titles.sendFullTitle(u, 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+stable+".displayname")));
                }
            } else if (v == red_stable){
                if (redPlayers.contains(p)){
                    p.sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (Player u : redPlayers){
                    Titles.sendFullTitle(u, 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+stable+".displayname")));
                }
            } else if (v == blue_armory){
                if (bluePlayers.contains(p)){
                    p.sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (Player u : bluePlayers){
                    Titles.sendFullTitle(u, 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+armory+".displayname")));
                }
            } else if (v == green_archery){
                if (greenPlayers.contains(p)){
                    p.sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (Player u : greenPlayers){
                    Titles.sendFullTitle(u, 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+armory+".displayname")));
                }
            } else if (v == yellow_armory){
                if (yellowPlayers.contains(p)){
                    p.sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (Player u : yellowPlayers){
                    Titles.sendFullTitle(u, 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+armory+".displayname")));
                }
            } else if (v == red_armory){
                if (redPlayers.contains(p)){
                    p.sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (Player u : redPlayers){
                    Titles.sendFullTitle(u, 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+armory+".displayname")));
                }
            } else if (v == blue_lab){
                if (bluePlayers.contains(p)){
                    p.sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (Player u : bluePlayers){
                    Titles.sendFullTitle(u, 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+laboratory+".displayname")));
                }
            }  else if (v == green_lab){
                if (greenPlayers.contains(p)){
                    p.sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (Player u : greenPlayers){
                    Titles.sendFullTitle(u, 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+laboratory+".displayname")));
                }
            }  else if (v == yellow_lab){
                if (yellowPlayers.contains(p)){
                    p.sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (Player u : yellowPlayers){
                    Titles.sendFullTitle(u, 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+laboratory+".displayname")));
                }
            }  else if (v == red_lab){
                if (redPlayers.contains(p)){
                    p.sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (Player u : redPlayers){
                    Titles.sendFullTitle(u, 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+laboratory+".displayname")));
                }
            }  else if (v == blue_guild){
                if (bluePlayers.contains(p)){
                    p.sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (Player u : bluePlayers){
                    Titles.sendFullTitle(u, 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+guild+".displayname")));
                }
            }  else if (v == green_guild){
                if (greenPlayers.contains(p)){
                    p.sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (Player u : greenPlayers){
                    Titles.sendFullTitle(u, 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+guild+".displayname")));
                }
            }  else if (v == yellow_guild){
                if (yellowPlayers.contains(p)){
                    p.sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (Player u : yellowPlayers){
                    Titles.sendFullTitle(u, 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+guild+".displayname")));
                }
            }  else if (v == red_guild){
                if (redPlayers.contains(p)){
                    p.sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (Player u : redPlayers){
                    Titles.sendFullTitle(u, 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+guild+".displayname")));
                }
            }  else if (v == blue_tcenter){
                if (bluePlayers.contains(p)){
                    p.sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (Player u : bluePlayers){
                    Titles.sendFullTitle(u, 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+training_center+".displayname")));
                }
            }  else if (v == green_tcenter){
                if (greenPlayers.contains(p)){
                    p.sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (Player u : greenPlayers){
                    Titles.sendFullTitle(u, 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+training_center+".displayname")));
                }
            }  else if (v == yellow_tcenter){
                if (yellowPlayers.contains(p)){
                    p.sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (Player u : yellowPlayers){
                    Titles.sendFullTitle(u, 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+training_center+".displayname")));
                }
            }  else if (v == red_tcenter){
                if (redPlayers.contains(p)){
                    p.sendMessage(getMsg("forum.violence"));
                    e.setCancelled(true);
                    return;
                }
                for (Player u : redPlayers){
                    Titles.sendFullTitle(u, 0, 20, 0, "", getMsg("x-attacked").replace("{villager}", getMsg("forum."+training_center+".displayname")));
                }
            }
        }
    }
}