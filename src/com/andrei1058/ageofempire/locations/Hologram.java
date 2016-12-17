package com.andrei1058.ageofempire.locations;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;

public class Hologram {

    private static ArrayList<Hologram> holograms = new ArrayList<>();
    ArmorStand as;

    public Hologram(Location location, String text){
        ArmorStand as = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
        as.setGravity(false);
        as.setCanPickupItems(false);
        as.setCustomName(text);
        as.setCustomNameVisible(true);
        as.setVisible(false);
        this.as = as;
        holograms.add(this);
    }

    public void remove(){
        this.as.remove();
    }

    public void hide(){
        this.as.setVisible(false);
    }

    public void show(){
        this.as.setVisible(true);
    }

    public static ArrayList<Hologram> list(){
        return holograms;
    }
}
