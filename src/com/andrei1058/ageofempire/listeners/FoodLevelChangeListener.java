package com.andrei1058.ageofempire.listeners;

import com.andrei1058.ageofempire.game.Status;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

import static com.andrei1058.ageofempire.Main.STATUS;

/**
 * Created by andrei1058 on 05/12/2016.
 */
public class FoodLevelChangeListener implements Listener {

    @EventHandler
    public void f(FoodLevelChangeEvent e){
        if (STATUS != Status.PLAYING){
            e.setCancelled(true);
        }
    }
}
