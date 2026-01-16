package com.ageofempires.data.player;

import com.ageofempires.gameplay.resources.ResourceType;
import com.hypixel.hytale.server.core.universe.PlayerRef;

import java.util.EnumMap;
import java.util.Map;

/**
 * Represents a player's session data within a game
 */
public class PlayerSession {
    
    private final PlayerRef playerRef;
    private final Map<ResourceType, Double> personalResources;
    
    private int kills;
    private int deaths;
    private int forumKills;
    private boolean helpEnabled;
    
    public PlayerSession(PlayerRef playerRef) {
        this.playerRef = playerRef;
        this.personalResources = new EnumMap<>(ResourceType.class);
        
        // Initialize personal resources (mainly gold)
        for (ResourceType type : ResourceType.values()) {
            if (type.isPersonalResource()) {
                personalResources.put(type, 0.0);
            }
        }
        
        this.helpEnabled = true;
    }
    
    /**
     * Add personal resource (gold)
     * @param type Resource type
     * @param amount Amount to add
     */
    public void addPersonalResource(ResourceType type, double amount) {
        if (!type.isPersonalResource()) return;
        personalResources.merge(type, amount, Double::sum);
    }
    
    /**
     * Remove personal resource
     * @param type Resource type
     * @param amount Amount to remove
     * @return true if successful
     */
    public boolean removePersonalResource(ResourceType type, double amount) {
        if (!type.isPersonalResource()) return false;
        
        double current = personalResources.getOrDefault(type, 0.0);
        if (current < amount) return false;
        
        personalResources.put(type, current - amount);
        return true;
    }
    
    /**
     * Get personal resource amount
     * @param type Resource type
     * @return The amount
     */
    public double getPersonalResource(ResourceType type) {
        return personalResources.getOrDefault(type, 0.0);
    }
    
    /**
     * Add a kill
     */
    public void addKill() {
        kills++;
    }
    
    /**
     * Add a death
     */
    public void addDeath() {
        deaths++;
    }
    
    /**
     * Add a forum kill
     */
    public void addForumKill() {
        forumKills++;
    }
    
    // ==================== Getters/Setters ====================
    
    public PlayerRef getPlayerRef() { return playerRef; }
    public int getKills() { return kills; }
    public int getDeaths() { return deaths; }
    public int getForumKills() { return forumKills; }
    public boolean isHelpEnabled() { return helpEnabled; }
    public void setHelpEnabled(boolean helpEnabled) { this.helpEnabled = helpEnabled; }
}
