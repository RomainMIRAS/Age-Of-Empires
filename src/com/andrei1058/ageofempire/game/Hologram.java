package com.andrei1058.ageofempire.game;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;

import java.util.ArrayList;
import java.util.HashMap;

public class Hologram {

    private static ArrayList<Hologram> holograms = new ArrayList<>();
    private static HashMap<Villager, Hologram> hash = new HashMap<>();
    ArmorStand as;
    ArmorStand as2;
    public boolean shown = false;

    public Hologram(Location location, Location location2, String text, String text2, Villager villager){
        ArmorStand as = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
        as.setGravity(false);
        as.setCanPickupItems(false);
        as.setCustomName(text);
        as.setCustomNameVisible(true);
        as.setVisible(false);
        as.setSmall(true);
        this.as = as;

        ArmorStand as2 = (ArmorStand) location.getWorld().spawnEntity(location2, EntityType.ARMOR_STAND);
        as2.setGravity(false);
        as2.setCanPickupItems(false);
        as2.setCustomName(text2);
        as2.setCustomNameVisible(true);
        as2.setVisible(false);
        this.as2 = as2;

        holograms.add(this);
        hash.put(villager, this);
    }

    public Hologram(Location loc, String text, String txt){

    }

    public void remove(){
        this.as.remove();
        this.as2.remove();
    }

    public void hide(){
        this.as.setCustomNameVisible(false);
        this.as2.setCustomNameVisible(false);
    }

    public void show(){
        this.as.setCustomNameVisible(true);
        this.as2.setCustomNameVisible(true);
    }

    public void stuff(){
        if (shown){
            hide();
            shown = false;
        } else {
            show();
            shown = true;
        }
    }

    public static ArrayList<Hologram> list(){
        return holograms;
    }

    public static Hologram get(Villager villager){
        return hash.get(villager);
    }
}
