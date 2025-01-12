package com.andrei1058.ageofempire.game;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;

import static com.andrei1058.ageofempire.Main.plugin;
import static com.andrei1058.ageofempire.configuration.Messages.getMsg;
import static org.bukkit.Bukkit.broadcastMessage;

public class OreHologram {

    // Time before removing hologram in ticks.
    // Will be removed after 16 ticks. (0.8 seconds)
    public final static int TIME_BEFORE_REMOVE = 16;

    private final String txt = getMsg("holo.gold");
    private final String txt2 = getMsg("holo.stone");
    private final String txt3 = getMsg("holo.wood");
    private long time;
    ArmorStand as;
    ArmorStand as2;
    ArmorStand as3;
    public OreHologram(Location loc,  Material from, double goldAmount, double stoneAmount, double woodAmount){
        switch (from){
            case SEA_LANTERN:
                if (goldAmount != 0) {
                    as = (ArmorStand) loc.getWorld().spawnEntity(loc.getBlock().getLocation().clone().add(0, +1.5, +0), EntityType.ARMOR_STAND);
                    as.setVisible(false);
                    as.setGravity(false);
                    as.setCanPickupItems(false);
                    as.setCustomName(txt.replace("{amount}", String.valueOf(goldAmount)));
                    as.setCustomNameVisible(true);
                    as.setSmall(true);
                    as.setMarker(true);
                }
                as2 = (ArmorStand) loc.getWorld().spawnEntity(loc.getBlock().getLocation().add(0, +1.2, 0), EntityType.ARMOR_STAND);
                as2.setVisible(false);
                as2.setGravity(false);
                as2.setCanPickupItems(false);
                as2.setCustomName(txt2.replace("{amount}", String.valueOf(stoneAmount)));
                as2.setCustomNameVisible(true);
                as2.setSmall(true);
                as2.setMarker(true);

                as3 = (ArmorStand) loc.getWorld().spawnEntity(loc.getBlock().getLocation().add(0, +0.9, 0), EntityType.ARMOR_STAND);
                as3.setVisible(false);
                as3.setGravity(false);
                as3.setCanPickupItems(false);
                as3.setCustomName(txt3.replace("{amount}", String.valueOf(woodAmount)));
                as3.setCustomNameVisible(true);
                as3.setSmall(true);
                as3.setMarker(true);
                break;
            case STONE:
                if (goldAmount != 0) {
                    as = (ArmorStand) loc.getWorld().spawnEntity(loc.getBlock().getLocation().clone().add(0, +1.2, +0), EntityType.ARMOR_STAND);
                    as.setVisible(false);
                    as.setGravity(false);
                    as.setCanPickupItems(false);
                    as.setCustomName(txt.replace("{amount}", String.valueOf(goldAmount)));
                    as.setCustomNameVisible(true);
                    as.setSmall(true);
                    as.setMarker(true);
                }

                as2 = (ArmorStand) loc.getWorld().spawnEntity(loc.getBlock().getLocation().add(0, +0.9, 0), EntityType.ARMOR_STAND);
                as2.setVisible(false);
                as2.setGravity(false);
                as2.setCanPickupItems(false);
                as2.setCustomName(txt2.replace("{amount}", String.valueOf(stoneAmount)));
                as2.setCustomNameVisible(true);
                as2.setSmall(true);
                as2.setMarker(true);
                break;
            case LOG:
                if (goldAmount != 0) {
                    as = (ArmorStand) loc.getWorld().spawnEntity(loc.getBlock().getLocation().clone().add(0, +0.9, +0), EntityType.ARMOR_STAND);
                    as.setVisible(false);
                    as.setGravity(false);
                    as.setCanPickupItems(false);
                    as.setCustomName(txt.replace("{amount}", String.valueOf(goldAmount)));
                    as.setCustomNameVisible(true);
                    as.setSmall(true);
                    as.setMarker(true);
                }
                as2 = (ArmorStand) loc.getWorld().spawnEntity(loc.getBlock().getLocation().add(0, 1.2, 0), EntityType.ARMOR_STAND);
                as2.setVisible(false);
                as2.setGravity(false);
                as2.setCanPickupItems(false);
                as2.setCustomName(txt3.replace("{amount}", String.valueOf(woodAmount)));
                as2.setCustomNameVisible(true);
                as2.setSmall(true);
                as2.setMarker(true);
                break;
        }
        new removeHologram().runTaskLater(plugin, TIME_BEFORE_REMOVE);
    }

    private class removeHologram extends BukkitRunnable {
        @Override
        public void run() {
            if (as != null){
                as.remove();
            }
            if (as2 != null){
                as2.remove();
            }
            if (as3 != null){
                as3.remove();
            }
        }
    }
}
