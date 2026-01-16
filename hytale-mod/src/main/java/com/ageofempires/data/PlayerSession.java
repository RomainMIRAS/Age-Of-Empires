package com.ageofempires.data;

import com.ageofempires.core.kits.Kit;
import com.ageofempires.core.team.Team;
import com.ageofempires.core.resources.ResourceType;
import com.hypixel.hytale.server.core.entity.PlayerRef;

import java.util.EnumMap;
import java.util.Map;
import java.util.UUID;

/**
 * Holds all data for a player during a game session
 */
public class PlayerSession {

    private final PlayerRef playerRef;
    private final GameSession gameSession;
    private final UUID sessionId;
    private final long joinTime;
    
    // Team and kit
    private Team team;
    private Kit selectedKit;
    
    // Stats
    private int kills;
    private int deaths;
    private int assists;
    private double damageDealt;
    private double damageTaken;
    private final Map<ResourceType, Integer> resourcesGathered;
    
    // Cooldowns
    private long lastStuckCommandTime;
    
    // Game state
    private boolean alive;
    private boolean spectating;
    
    public PlayerSession(PlayerRef playerRef, GameSession gameSession) {
        this.playerRef = playerRef;
        this.gameSession = gameSession;
        this.sessionId = UUID.randomUUID();
        this.joinTime = System.currentTimeMillis();
        
        this.kills = 0;
        this.deaths = 0;
        this.assists = 0;
        this.damageDealt = 0;
        this.damageTaken = 0;
        this.resourcesGathered = new EnumMap<>(ResourceType.class);
        
        this.lastStuckCommandTime = 0;
        this.alive = true;
        this.spectating = false;
        
        // Initialize resource tracking
        for (ResourceType type : ResourceType.values()) {
            resourcesGathered.put(type, 0);
        }
    }
    
    // ==================== Getters/Setters ====================
    
    public PlayerRef getPlayerRef() { return playerRef; }
    public GameSession getGameSession() { return gameSession; }
    public UUID getSessionId() { return sessionId; }
    public long getJoinTime() { return joinTime; }
    
    public Team getTeam() { return team; }
    public void setTeam(Team team) { this.team = team; }
    
    public Kit getSelectedKit() { return selectedKit; }
    public void setSelectedKit(Kit kit) { this.selectedKit = kit; }
    
    public boolean isAlive() { return alive; }
    public void setAlive(boolean alive) { this.alive = alive; }
    
    public boolean isSpectating() { return spectating; }
    public void setSpectating(boolean spectating) { this.spectating = spectating; }
    
    // ==================== Stats ====================
    
    public int getKills() { return kills; }
    public void incrementKills() { kills++; }
    public void addKills(int amount) { kills += amount; }
    
    public int getDeaths() { return deaths; }
    public void incrementDeaths() { deaths++; }
    
    public int getAssists() { return assists; }
    public void incrementAssists() { assists++; }
    
    public double getDamageDealt() { return damageDealt; }
    public void addDamageDealt(double amount) { damageDealt += amount; }
    
    public double getDamageTaken() { return damageTaken; }
    public void addDamageTaken(double amount) { damageTaken += amount; }
    
    public double getKDRatio() {
        return deaths == 0 ? kills : (double) kills / deaths;
    }
    
    // ==================== Resources ====================
    
    public int getResourcesGathered(ResourceType type) {
        return resourcesGathered.getOrDefault(type, 0);
    }
    
    public void addResourcesGathered(ResourceType type, int amount) {
        resourcesGathered.merge(type, amount, Integer::sum);
    }
    
    public int getTotalResourcesGathered() {
        return resourcesGathered.values().stream().mapToInt(Integer::intValue).sum();
    }
    
    public Map<ResourceType, Integer> getAllResourcesGathered() {
        return new EnumMap<>(resourcesGathered);
    }
    
    // ==================== Cooldowns ====================
    
    public long getLastStuckCommandTime() { return lastStuckCommandTime; }
    public void setLastStuckCommandTime(long time) { this.lastStuckCommandTime = time; }
    
    // ==================== Utility ====================
    
    /**
     * Get time played in this session (milliseconds)
     */
    public long getTimePlayed() {
        return System.currentTimeMillis() - joinTime;
    }
    
    /**
     * Get formatted time played
     */
    public String getTimePlayedFormatted() {
        long millis = getTimePlayed();
        long seconds = millis / 1000;
        long minutes = seconds / 60;
        seconds = seconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
    
    /**
     * Calculate player score
     */
    public int getScore() {
        int score = 0;
        score += kills * 100;
        score += assists * 25;
        score -= deaths * 50;
        score += getTotalResourcesGathered();
        score += (int) damageDealt;
        return Math.max(0, score);
    }
    
    /**
     * Reset session stats (for respawn, etc.)
     */
    public void resetForRespawn() {
        alive = true;
        spectating = false;
    }
    
    @Override
    public String toString() {
        String playerName = playerRef.getPlayer() != null ? playerRef.getPlayer().getName() : "Unknown";
        return playerName + " - Team: " + (team != null ? team.getDisplayName() : "None") +
               " - K/D: " + kills + "/" + deaths +
               " - Score: " + getScore();
    }
}
