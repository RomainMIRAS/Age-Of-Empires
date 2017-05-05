package com.andrei1058.ageofempire.nms.v1_11_R1;


import net.minecraft.server.v1_11_R1.*;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_11_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_11_R1.util.UnsafeList;
import org.bukkit.entity.Villager;
import org.bukkit.event.entity.CreatureSpawnEvent;

import java.lang.reflect.Field;

public class VillagerNMS extends EntityVillager {

    public VillagerNMS(World world) {
        super(world);
        try {
            Field bField = PathfinderGoalSelector.class.getDeclaredField("b");
            bField.setAccessible(true);
            Field cField = PathfinderGoalSelector.class.getDeclaredField("c");
            cField.setAccessible(true);
            bField.set(this.goalSelector, new UnsafeList());
            bField.set(this.targetSelector, new UnsafeList());
            cField.set(this.goalSelector, new UnsafeList());
            cField.set(this.targetSelector, new UnsafeList());
        }
        catch (Exception bField) {
        }
        this.goalSelector.a(0, new PathfinderGoalFloat(this));
        this.goalSelector.a(9, new PathfinderGoalInteract(this, EntityHuman.class, 3.0f, 1.0f));
        this.goalSelector.a(10, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0f));
    }

    public static Villager spawnVillager(Location loc, Integer health){
        World mcWorld = ((CraftWorld) loc.getWorld()).getHandle();
        VillagerNMS customEnt = new VillagerNMS(mcWorld);
        //customEnt.getAttributeInstance(GenericAttributes.maxHealth).setValue(3524.0);
        customEnt.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
        ((CraftLivingEntity) customEnt.getBukkitEntity()).setRemoveWhenFarAway(false);
        customEnt.setCustomName("§9"+health);
        customEnt.setCustomNameVisible(true);
        customEnt.setHealth(health);
        mcWorld.addEntity(customEnt, CreatureSpawnEvent.SpawnReason.CUSTOM);
        return (Villager) customEnt.getBukkitEntity();
    }
}