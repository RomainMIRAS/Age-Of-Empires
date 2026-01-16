package com.ageofempires.gameplay.buildings;

/**
 * Represents the size of a building plot
 */
public enum PlotSize {
    
    SMALL("Small", 9, 1),
    MEDIUM("Medium", 12, 2),
    LARGE("Large", 16, 3);
    
    private final String name;
    private final int radius;
    private final int tier;
    
    PlotSize(String name, int radius, int tier) {
        this.name = name;
        this.radius = radius;
        this.tier = tier;
    }
    
    /**
     * Get the plot size name
     * @return The name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Get the plot radius
     * @return The radius in blocks
     */
    public int getRadius() {
        return radius;
    }
    
    /**
     * Get the tier (1=small, 2=medium, 3=large)
     * @return The tier number
     */
    public int getTier() {
        return tier;
    }
    
    /**
     * Get plot size from tier
     * @param tier The tier (1-3)
     * @return The plot size, or null if invalid
     */
    public static PlotSize fromTier(int tier) {
        for (PlotSize size : values()) {
            if (size.tier == tier) {
                return size;
            }
        }
        return null;
    }
}
