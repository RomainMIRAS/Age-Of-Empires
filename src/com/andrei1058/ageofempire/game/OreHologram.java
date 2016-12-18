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
    ArmorStand as;
    ArmorStand as2;
    public OreHologram(Location loc, Integer amount, boolean stone) {
        if (stone) {
            if (amount != 0) {
                as = (ArmorStand) loc.getWorld().spawnEntity(loc.clone().add(0, +0.6, +0), EntityType.ARMOR_STAND);
                as.setGravity(false);
                as.setCanPickupItems(false);
                as.setCustomName(txt.replace("{amount}", String.valueOf(amount)));
                as.setCustomNameVisible(true);
                as.setVisible(false);
                as.setSmall(true);
            }

            as2 = (ArmorStand) loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
            as2.setGravity(false);
            as2.setCanPickupItems(false);
            as2.setCustomName(txt2.replace("{amount}", "3"));
            as2.setCustomNameVisible(true);
            as2.setVisible(false);
            as2.setSmall(true);
            holos.add(this);
        } else {
            if (amount != 0) {
                as = (ArmorStand) loc.getWorld().spawnEntity(loc.clone().add(0, +0.6, +0), EntityType.ARMOR_STAND);
                as.setGravity(false);
                as.setCanPickupItems(false);
                as.setCustomName(txt.replace("{amount}", String.valueOf(amount)));
                as.setCustomNameVisible(true);
                as.setVisible(false);
                as.setSmall(true);
            }
            as2 = (ArmorStand) loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
            as2.setGravity(false);
            as2.setCanPickupItems(false);
            as2.setCustomName(txt3.replace("{amount}", "3"));
            as2.setCustomNameVisible(true);
            as2.setVisible(false);
            as2.setSmall(true);
            holos.add(this);
        }

    }

    public void remove(){
        if (as != null) {
            as.remove();
        }
        as2.remove();
        holos.remove(this);
    }

    public static ArrayList<OreHologram> list(){
        return holos;
    }
}
