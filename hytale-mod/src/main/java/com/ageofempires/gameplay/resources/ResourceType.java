package com.ageofempires.gameplay.resources;

/**
 * Represents the different types of resources in the game
 */
public enum ResourceType {
    
    /**
     * Wood resource (Spruce Wood in original)
     * Used for buildings and age advancement
     */
    WOOD("Wood", "§6", 1.0, 0.5),
    
    /**
     * Stone resource (Andesite in original)
     * Used for buildings and age advancement
     */
    STONE("Stone", "§7", 1.0, 0.5),
    
    /**
     * Gold resource
     * Personal currency for buying items
     */
    GOLD("Gold", "§e", 0.5, 1.0);
    
    private final String name;
    private final String colorCode;
    private final double baseGatherRate;
    private final double xpMultiplier;
    
    ResourceType(String name, String colorCode, double baseGatherRate, double xpMultiplier) {
        this.name = name;
        this.colorCode = colorCode;
        this.baseGatherRate = baseGatherRate;
        this.xpMultiplier = xpMultiplier;
    }
    
    /**
     * Get the resource name
     * @return The resource name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Get the resource color code
     * @return Minecraft-style color code
     */
    public String getColorCode() {
        return colorCode;
    }
    
    /**
     * Get the colored name
     * @return Color + name
     */
    public String getColoredName() {
        return colorCode + name;
    }
    
    /**
     * Get the base gathering rate
     * @return Base amount gathered per action
     */
    public double getBaseGatherRate() {
        return baseGatherRate;
    }
    
    /**
     * Get the XP multiplier for gathering this resource
     * @return XP multiplier
     */
    public double getXpMultiplier() {
        return xpMultiplier;
    }
    
    /**
     * Check if this is a team resource (shared by team)
     * @return true if team resource, false if personal
     */
    public boolean isTeamResource() {
        return this == WOOD || this == STONE;
    }
    
    /**
     * Check if this is a personal resource
     * @return true if personal, false if team resource
     */
    public boolean isPersonalResource() {
        return this == GOLD;
    }
}
