package com.ageofempires.core.resources;

/**
 * Represents the different types of resources that can be gathered
 */
public enum ResourceType {
    
    WOOD("Wood", "§6", 10, "wood_log"),
    STONE("Stone", "§7", 8, "stone"),
    GOLD("Gold", "§e", 5, "gold_ore"),
    IRON("Iron", "§f", 6, "iron_ore"),
    FOOD("Food", "§a", 15, "wheat");
    
    private final String displayName;
    private final String colorCode;
    private final int baseAmount;
    private final String blockIdentifier;
    
    ResourceType(String displayName, String colorCode, int baseAmount, String blockIdentifier) {
        this.displayName = displayName;
        this.colorCode = colorCode;
        this.baseAmount = baseAmount;
        this.blockIdentifier = blockIdentifier;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    public String getColorCode() {
        return colorCode;
    }
    
    public String getColoredName() {
        return colorCode + displayName;
    }
    
    public int getBaseAmount() {
        return baseAmount;
    }
    
    public String getBlockIdentifier() {
        return blockIdentifier;
    }
    
    /**
     * Get resource type from name
     */
    public static ResourceType fromName(String name) {
        if (name == null) return null;
        
        for (ResourceType type : values()) {
            if (type.name().equalsIgnoreCase(name) || 
                type.displayName.equalsIgnoreCase(name)) {
                return type;
            }
        }
        return null;
    }
    
    /**
     * Get resource type from block identifier
     */
    public static ResourceType fromBlock(String blockType) {
        if (blockType == null) return null;
        
        String lower = blockType.toLowerCase();
        for (ResourceType type : values()) {
            if (lower.contains(type.blockIdentifier)) {
                return type;
            }
        }
        return null;
    }
}
