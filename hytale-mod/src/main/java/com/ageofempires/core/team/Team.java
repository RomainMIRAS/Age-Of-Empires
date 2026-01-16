package com.ageofempires.core.team;

/**
 * Represents the four teams in Age of Empires
 * 
 * <p>Each team has a unique color, display name, and format code
 * for consistent visual representation throughout the game.</p>
 */
public enum Team {
    
    BLUE("Blue", "§9", "§9§lBLUE"),
    RED("Red", "§c", "§c§lRED"),
    GREEN("Green", "§a", "§a§lGREEN"),
    YELLOW("Yellow", "§e", "§e§lYELLOW");
    
    private final String name;
    private final String colorCode;
    private final String displayName;
    
    Team(String name, String colorCode, String displayName) {
        this.name = name;
        this.colorCode = colorCode;
        this.displayName = displayName;
    }
    
    /**
     * Get the team name
     * @return The team name (e.g., "Blue")
     */
    public String getName() {
        return name;
    }
    
    /**
     * Get the team's color code
     * @return Minecraft-style color code (e.g., "§9")
     */
    public String getColorCode() {
        return colorCode;
    }
    
    /**
     * Get the formatted display name
     * @return Colored and bold team name (e.g., "§9§lBLUE")
     */
    public String getDisplayName() {
        return displayName;
    }
    
    /**
     * Get a colored version of any text
     * @param text The text to color
     * @return The text with team color applied
     */
    public String colored(String text) {
        return colorCode + text;
    }
    
    /**
     * Get team by name (case-insensitive)
     * @param name The team name to search for
     * @return The team, or null if not found
     */
    public static Team fromName(String name) {
        if (name == null) return null;
        
        for (Team team : values()) {
            if (team.name.equalsIgnoreCase(name)) {
                return team;
            }
        }
        return null;
    }
    
    /**
     * Get a random team
     * @return A random team
     */
    public static Team random() {
        Team[] teams = values();
        return teams[(int) (Math.random() * teams.length)];
    }
}
