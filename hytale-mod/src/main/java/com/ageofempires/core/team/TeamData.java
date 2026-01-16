package com.ageofempires.core.team;

import com.ageofempires.core.game.GameConfig;
import com.ageofempires.gameplay.resources.ResourceType;

import java.util.*;

/**
 * Holds data for a team in a game session
 * 
 * <p>Includes resources, buildings, age, and players</p>
 */
public class TeamData {
    
    private final Team team;
    private final Set<UUID> players;
    private final Map<ResourceType, Double> resources;
    
    private int age;
    private boolean forumAlive;
    private int forumHealth;
    
    // Building counts by plot size
    private int smallPlotsUsed;
    private int mediumPlotsUsed;
    private int largePlotsUsed;
    
    // Building availability flags
    private final Map<String, Boolean> buildings;
    
    public TeamData(Team team) {
        this.team = team;
        this.players = new HashSet<>();
        this.resources = new EnumMap<>(ResourceType.class);
        this.buildings = new HashMap<>();
        
        this.age = 1;
        this.forumAlive = true;
        
        // Initialize resources
        for (ResourceType type : ResourceType.values()) {
            resources.put(type, 0.0);
        }
    }
    
    /**
     * Initialize resources from config
     * @param config The game config
     */
    public void initializeResources(GameConfig config) {
        resources.put(ResourceType.WOOD, config.getStartingWood());
        resources.put(ResourceType.STONE, config.getStartingStone());
        resources.put(ResourceType.GOLD, config.getStartingGold());
        forumHealth = config.getForumHealth();
    }
    
    /**
     * Add a player to the team
     * @param playerUuid The player UUID
     */
    public void addPlayer(UUID playerUuid) {
        players.add(playerUuid);
    }
    
    /**
     * Remove a player from the team
     * @param playerUuid The player UUID
     */
    public void removePlayer(UUID playerUuid) {
        players.remove(playerUuid);
    }
    
    /**
     * Add resources
     * @param type Resource type
     * @param amount Amount to add
     */
    public void addResource(ResourceType type, double amount) {
        resources.merge(type, amount, Double::sum);
    }
    
    /**
     * Remove resources
     * @param type Resource type
     * @param amount Amount to remove
     * @return true if successful, false if not enough resources
     */
    public boolean removeResource(ResourceType type, double amount) {
        double current = resources.getOrDefault(type, 0.0);
        if (current < amount) {
            return false;
        }
        resources.put(type, current - amount);
        return true;
    }
    
    /**
     * Get resource amount
     * @param type Resource type
     * @return The amount
     */
    public double getResource(ResourceType type) {
        return resources.getOrDefault(type, 0.0);
    }
    
    /**
     * Check if team has enough resources
     * @param type Resource type
     * @param amount Amount needed
     * @return true if enough
     */
    public boolean hasResource(ResourceType type, double amount) {
        return getResource(type) >= amount;
    }
    
    /**
     * Advance to next age
     * @return true if advanced
     */
    public boolean advanceAge() {
        if (age >= 4) return false;
        age++;
        return true;
    }
    
    /**
     * Reset team data
     */
    public void reset() {
        players.clear();
        resources.clear();
        buildings.clear();
        age = 1;
        forumAlive = true;
        forumHealth = 0;
        smallPlotsUsed = 0;
        mediumPlotsUsed = 0;
        largePlotsUsed = 0;
    }
    
    // ==================== Getters ====================
    
    public Team getTeam() { return team; }
    public int getPlayerCount() { return players.size(); }
    public Set<UUID> getPlayers() { return Collections.unmodifiableSet(players); }
    public int getAge() { return age; }
    public boolean isForumAlive() { return forumAlive; }
    public int getForumHealth() { return forumHealth; }
    
    public void setForumAlive(boolean forumAlive) { this.forumAlive = forumAlive; }
    public void setForumHealth(int health) { this.forumHealth = health; }
}
