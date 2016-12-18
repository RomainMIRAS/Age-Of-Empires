package com.andrei1058.ageofempire.nms;

import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_8_R3.util.UnsafeList;
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

            bField.set(goalSelector, new UnsafeList<PathfinderGoalSelector>());
            bField.set(targetSelector, new UnsafeList<PathfinderGoalSelector>());
            cField.set(goalSelector, new UnsafeList<PathfinderGoalSelector>());
            cField.set(targetSelector, new UnsafeList<PathfinderGoalSelector>());
        } catch (Exception e) {
        }

        this.goalSelector.a(0, new PathfinderGoalFloat(this));
        this.goalSelector.a(9, new PathfinderGoalInteract(this, EntityHuman.class, 3.0F, 1.0F));
        this.goalSelector.a(10, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
    }

    @Override
    public void move(double d0, double d1, double d2) {

    }

    @Override
    public void collide(Entity entity) {
    }

    /*@Override
    public boolean damageEntity(DamageSource damagesource, float f) {
        return false;
    }*/

    @Override
    public void g(double d0, double d1, double d2) {
    }

    public static Villager spawnVillager(Location loc, Integer health){
        World mcWorld = ((CraftWorld) loc.getWorld()).getHandle();
        final VillagerNMS customEnt = new VillagerNMS(mcWorld);
        customEnt.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
        ((CraftLivingEntity) customEnt.getBukkitEntity()).setRemoveWhenFarAway(false);
        customEnt.setCustomName("§9"+health);
        customEnt.setCustomNameVisible(true);
        customEnt.getAttributeInstance(GenericAttributes.maxHealth).setValue(health*2);
        customEnt.setHealth(health);
        mcWorld.addEntity(customEnt, CreatureSpawnEvent.SpawnReason.CUSTOM);
        return (org.bukkit.entity.Villager) customEnt.getBukkitEntity();
    }
}