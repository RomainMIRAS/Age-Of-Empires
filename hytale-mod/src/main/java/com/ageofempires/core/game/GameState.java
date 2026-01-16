package com.ageofempires.core.game;

/**
 * Represents the different states a game can be in
 * 
 * <p>Game state flow:</p>
 * <pre>
 * WAITING → STARTING → PREPARING → PLAYING → ENDING
 *    ↓                                          ↓
 *    ←──────────────← DISABLED ←───────────────←
 * </pre>
 */
public enum GameState {
    
    /**
     * Game is disabled or not ready
     */
    DISABLED("Disabled", "§c"),
    
    /**
     * Waiting for players to join
     */
    WAITING("Waiting", "§e"),
    
    /**
     * Countdown started, game will begin soon
     */
    STARTING("Starting", "§6"),
    
    /**
     * Pre-game phase (team selection, kit selection)
     */
    PREPARING("Preparing", "§d"),
    
    /**
     * Game is active (resource gathering, building, combat)
     */
    PLAYING("Playing", "§a"),
    
    /**
     * Game has ended, showing results
     */
    ENDING("Ending", "§b");
    
    private final String displayName;
    private final String color;
    
    GameState(String displayName, String color) {
        this.displayName = displayName;
        this.color = color;
    }
    
    /**
     * Get the display name of this state
     * @return The human-readable name
     */
    public String getDisplayName() {
        return displayName;
    }
    
    /**
     * Get the color code for this state
     * @return Minecraft-style color code
     */
    public String getColor() {
        return color;
    }
    
    /**
     * Get the colored display name
     * @return Color + display name
     */
    public String getColoredName() {
        return color + displayName;
    }
    
    /**
     * Check if players can join in this state
     * @return true if joinable
     */
    public boolean isJoinable() {
        return this == WAITING || this == STARTING;
    }
    
    /**
     * Check if the game is active (playing)
     * @return true if playing
     */
    public boolean isActive() {
        return this == PLAYING;
    }
    
    /**
     * Check if the game is in a transition state
     * @return true if starting, preparing, or ending
     */
    public boolean isTransitioning() {
        return this == STARTING || this == PREPARING || this == ENDING;
    }
}
