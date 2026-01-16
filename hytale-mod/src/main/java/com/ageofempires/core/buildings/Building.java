package com.ageofempires.core.buildings;

import com.ageofempires.core.team.Team;
import com.hypixel.hytale.server.math.vector.Vector3d;

import java.util.UUID;

/**
 * Represents a constructed building in the game
 */
public class Building {

    private final UUID id;
    private final BuildingType type;
    private final Team ownerTeam;
    private final Vector3d position;
    private final long constructedAt;
    
    private int level;
    private int health;
    private int maxHealth;
    private boolean active;
    private long lastProductionTick;
    
    public Building(BuildingType type, Team ownerTeam, Vector3d position) {
        this.id = UUID.randomUUID();
        this.type = type;
        this.ownerTeam = ownerTeam;
        this.position = position;
        this.constructedAt = System.currentTimeMillis();
        
        this.level = 1;
        this.maxHealth = calculateMaxHealth();
        this.health = maxHealth;
        this.active = true;
        this.lastProductionTick = System.currentTimeMillis();
    }
    
    // Getters
    public UUID getId() { return id; }
    public BuildingType getType() { return type; }
    public Team getOwnerTeam() { return ownerTeam; }
    public Vector3d getPosition() { return position; }
    public long getConstructedAt() { return constructedAt; }
    public int getLevel() { return level; }
    public int getHealth() { return health; }
    public int getMaxHealth() { return maxHealth; }
    public boolean isActive() { return active; }
    public long getLastProductionTick() { return lastProductionTick; }
    
    /**
     * Calculate max health based on building type and level
     */
    private int calculateMaxHealth() {
        int baseHealth = 100;
        
        // Larger buildings have more health
        switch (type.getRequiredPlotSize()) {
            case SMALL: baseHealth = 100; break;
            case MEDIUM: baseHealth = 200; break;
            case LARGE: baseHealth = 350; break;
        }
        
        // Add level bonus
        return baseHealth + (level - 1) * 50;
    }
    
    /**
     * Upgrade the building
     */
    public boolean upgrade() {
        if (level >= 3) {
            return false; // Max level
        }
        
        level++;
        maxHealth = calculateMaxHealth();
        health = maxHealth; // Full heal on upgrade
        return true;
    }
    
    /**
     * Damage the building
     */
    public void damage(int amount) {
        health = Math.max(0, health - amount);
        
        if (health <= 0) {
            destroy();
        }
    }
    
    /**
     * Repair the building
     */
    public void repair(int amount) {
        health = Math.min(maxHealth, health + amount);
    }
    
    /**
     * Destroy the building
     */
    public void destroy() {
        active = false;
        health = 0;
    }
    
    /**
     * Mark production tick
     */
    public void markProduction() {
        lastProductionTick = System.currentTimeMillis();
    }
    
    /**
     * Check if building can produce (cooldown passed)
     */
    public boolean canProduce(long productionInterval) {
        return active && (System.currentTimeMillis() - lastProductionTick >= productionInterval);
    }
    
    /**
     * Get production amount based on level
     */
    public int getProductionAmount(int baseAmount) {
        return baseAmount * level;
    }
    
    /**
     * Get the upgrade cost multiplier
     */
    public double getUpgradeCostMultiplier() {
        return 1.0 + (level * 0.5); // 1.5x for level 2, 2x for level 3
    }
    
    /**
     * Check if this building is a resource producer
     */
    public boolean isResourceProducer() {
        switch (type) {
            case SAWMILL:
            case STONE_MINE:
            case GOLD_MINE:
            case MILL:
            case FORGE:
                return true;
            default:
                return false;
        }
    }
    
    /**
     * Check if this building is a military building
     */
    public boolean isMilitaryBuilding() {
        switch (type) {
            case TRAINING_CENTER:
            case ARCHERY_STORE:
            case STABLE:
            case KENNEL:
                return true;
            default:
                return false;
        }
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Building building = (Building) o;
        return id.equals(building.id);
    }
    
    @Override
    public int hashCode() {
        return id.hashCode();
    }
    
    @Override
    public String toString() {
        return type.getDisplayName() + " (Lvl " + level + ") - " + 
               ownerTeam.getDisplayName() + " - HP: " + health + "/" + maxHealth;
    }
}
