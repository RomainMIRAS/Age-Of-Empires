package com.andrei1058.ageofempire.game;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;

import static com.andrei1058.ageofempire.configuration.Messages.getMsg;

public class OreHologram {
    private static ArrayList<OreHologram> holos = new ArrayList<>();
    private final String txt = getMsg("holo.gold");
    private final String txt2 = getMsg("holo.stone");
    private final String txt3 = getMsg("holo.wood");
    private long time;
    ArmorStand as;
    ArmorStand as2;
    public OreHologram(Location loc, Integer amount, boolean stone) {
        if (stone) {
            if (amount != 0) {
                as = (ArmorStand) loc.getWorld().spawnEntity(loc.getBlock().getLocation().clone().add(0, +1.2, +0), EntityType.ARMOR_STAND);
                as.setVisible(false);
                as.setGravity(false);
                as.setCanPickupItems(false);
                as.setCustomName(txt.replace("{amount}", String.valueOf(amount)));
                as.setCustomNameVisible(true);
                as.setSmall(true);
                as.setMarker(true);
            }

            as2 = (ArmorStand) loc.getWorld().spawnEntity(loc.getBlock().getLocation().add(0, +0.9, 0), EntityType.ARMOR_STAND);
            as2.setVisible(false);
            as2.setGravity(false);
            as2.setCanPickupItems(false);
            as2.setCustomName(txt2.replace("{amount}", String.valueOf(amount)));
            as2.setCustomNameVisible(true);
            as2.setSmall(true);
            as2.setMarker(true);
            holos.add(this);
        } else {
            if (amount != 0) {
                as = (ArmorStand) loc.getWorld().spawnEntity(loc.getBlock().getLocation().clone().add(0, +1.2, +0), EntityType.ARMOR_STAND);
                as.setVisible(false);
                as.setGravity(false);
                as.setCanPickupItems(false);
                as.setCustomName(txt.replace("{amount}", String.valueOf(amount)));
                as.setCustomNameVisible(true);
                as.setSmall(true);
                as.setMarker(true);
            }
            as2 = (ArmorStand) loc.getWorld().spawnEntity(loc.getBlock().getLocation().add(0, 1.2, 0), EntityType.ARMOR_STAND);
            as2.setVisible(false);
            as2.setGravity(false);
            as2.setCanPickupItems(false);
            as2.setCustomName(txt3.replace("{amount}", String.valueOf(amount)));
            as2.setCustomNameVisible(true);
            as2.setSmall(true);
            as2.setMarker(true);
            holos.add(this);
        }
        time = System.currentTimeMillis();
    }

    public void remove(){
        if (System.currentTimeMillis()-time>=800){
            if (as != null) {
                as.remove();
            }
            as2.remove();
            holos.remove(this);
        }
    }

    public static ArrayList<OreHologram> list(){
        return holos;
    }
}
