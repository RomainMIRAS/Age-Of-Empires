package com.ageofempires.core.kits;

import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.item.ItemStack;

import java.util.List;
import java.util.ArrayList;

/**
 * Represents a player kit with specific items and abilities
 */
public class Kit {

    private final String id;
    private final String name;
    private final String description;
    private final String permission;
    private final boolean requiresPermission;
    private final List<ItemStack> items;
    private final KitAbilities abilities;
    
    private Kit(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
        this.permission = builder.permission;
        this.requiresPermission = builder.requiresPermission;
        this.items = builder.items;
        this.abilities = builder.abilities;
    }
    
    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getPermission() { return permission; }
    public boolean requiresPermission() { return requiresPermission; }
    public List<ItemStack> getItems() { return new ArrayList<>(items); }
    public KitAbilities getAbilities() { return abilities; }
    
    /**
     * Check if this kit is available to a player
     */
    public boolean isAvailableTo(Player player) {
        if (!requiresPermission) {
            return true;
        }
        return player.hasPermission(permission);
    }
    
    // Builder pattern
    public static Builder builder(String id) {
        return new Builder(id);
    }
    
    public static class Builder {
        private final String id;
        private String name;
        private String description = "";
        private String permission = "";
        private boolean requiresPermission = false;
        private List<ItemStack> items = new ArrayList<>();
        private KitAbilities abilities = new KitAbilities();
        
        private Builder(String id) {
            this.id = id;
            this.name = id;
        }
        
        public Builder name(String name) {
            this.name = name;
            return this;
        }
        
        public Builder description(String description) {
            this.description = description;
            return this;
        }
        
        public Builder permission(String permission) {
            this.permission = permission;
            this.requiresPermission = permission != null && !permission.isEmpty();
            return this;
        }
        
        public Builder addItem(ItemStack item) {
            this.items.add(item);
            return this;
        }
        
        public Builder items(List<ItemStack> items) {
            this.items = new ArrayList<>(items);
            return this;
        }
        
        public Builder abilities(KitAbilities abilities) {
            this.abilities = abilities;
            return this;
        }
        
        public Kit build() {
            return new Kit(this);
        }
    }
    
    /**
     * Kit special abilities
     */
    public static class KitAbilities {
        private double damageMultiplier = 1.0;
        private double speedMultiplier = 1.0;
        private double healthBonus = 0.0;
        private int resourceGatherBonus = 0;
        private boolean canUseBow = true;
        private boolean canBuild = true;
        private int extraBuildRange = 0;
        
        public double getDamageMultiplier() { return damageMultiplier; }
        public double getSpeedMultiplier() { return speedMultiplier; }
        public double getHealthBonus() { return healthBonus; }
        public int getResourceGatherBonus() { return resourceGatherBonus; }
        public boolean canUseBow() { return canUseBow; }
        public boolean canBuild() { return canBuild; }
        public int getExtraBuildRange() { return extraBuildRange; }
        
        public KitAbilities setDamageMultiplier(double multiplier) {
            this.damageMultiplier = multiplier;
            return this;
        }
        
        public KitAbilities setSpeedMultiplier(double multiplier) {
            this.speedMultiplier = multiplier;
            return this;
        }
        
        public KitAbilities setHealthBonus(double bonus) {
            this.healthBonus = bonus;
            return this;
        }
        
        public KitAbilities setResourceGatherBonus(int bonus) {
            this.resourceGatherBonus = bonus;
            return this;
        }
        
        public KitAbilities setCanUseBow(boolean canUse) {
            this.canUseBow = canUse;
            return this;
        }
        
        public KitAbilities setCanBuild(boolean canBuild) {
            this.canBuild = canBuild;
            return this;
        }
        
        public KitAbilities setExtraBuildRange(int range) {
            this.extraBuildRange = range;
            return this;
        }
    }
}
