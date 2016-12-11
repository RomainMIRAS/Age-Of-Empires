package com.andrei1058.ageofempire.listeners;

import com.andrei1058.ageofempire.game.Status;
import com.andrei1058.ageofempire.locations.Locations;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import static com.andrei1058.ageofempire.Main.*;

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
            if (!assualt){
                e.setCancelled(true);
                return;
            }
            if (e.getEntity().getLocation() == Locations.getLoc("Forum."+choosenMap+".Yellow")){
                if (yellowPlayers.contains(e.getDamager().getUniqueId())){
                    e.setCancelled(true);
                    //trimite mesaj
                    return;
                }
            } else if (e.getEntity().getLocation() == Locations.getLoc("Forum."+choosenMap+".Blue")){
                if (bluePlayers.contains(e.getDamager().getUniqueId())){
                    e.setCancelled(true);
                    //send message
                    return;
                }
            } else if (e.getEntity().getLocation() == Locations.getLoc("Forum."+choosenMap+".Green")){
                if (greenPlayers.contains(e.getDamager().getUniqueId())){
                    e.setCancelled(true);
                    //send message
                    return;
                }
            } else if (e.getEntity().getLocation() == Locations.getLoc("Forun."+choosenMap+".Red")){
                if (redPlayers.contains(e.getDamager().getUniqueId())){
                    e.setCancelled(true);
                    //send message
                    return;
                }
            }
        }
    }
}