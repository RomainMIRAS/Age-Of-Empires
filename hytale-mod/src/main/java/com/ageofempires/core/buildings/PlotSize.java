package com.ageofempires.core.buildings;

/**
 * Represents plot sizes for buildings
 */
public enum PlotSize {
    SMALL("Small", 5, 5),
    MEDIUM("Medium", 9, 9),
    LARGE("Large", 13, 13);
    
    private final String displayName;
    private final int width;
    private final int depth;
    
    PlotSize(String displayName, int width, int depth) {
        this.displayName = displayName;
        this.width = width;
        this.depth = depth;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    public int getWidth() {
        return width;
    }
    
    public int getDepth() {
        return depth;
    }
    
    public int getArea() {
        return width * depth;
    }
    
    /**
     * Check if this plot can fit a building of given size
     */
    public boolean canFit(PlotSize required) {
        return this.ordinal() >= required.ordinal();
    }
    
    /**
     * Get plot size from name
     */
    public static PlotSize fromName(String name) {
        if (name == null) return null;
        
        for (PlotSize size : values()) {
            if (size.name().equalsIgnoreCase(name) ||
                size.displayName.equalsIgnoreCase(name)) {
                return size;
            }
        }
        return null;
    }
}
