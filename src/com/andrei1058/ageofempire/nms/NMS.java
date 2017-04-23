package com.andrei1058.ageofempire.nms;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.ItemStack;

/**
 * Copyright Andrei Dascalu - andrei1058 @spigotmc.org
 * Age-Of-Empire class written on 13/04/2017
 */
public interface NMS {
    void registerVillagers();
    void actionMsg(Player p , String message);
    Sound witherDeath();
    Sound levelUp();
    Sound wolfDeath();
    Sound click();
    Villager spawnVillager(Location loc, Integer health);
    ItemStack itemInHand(Player p);
}
