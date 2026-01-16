package com.ageofempires.core.buildings;

import com.ageofempires.core.resources.ResourceType;

import java.util.EnumMap;
import java.util.Map;

/**
 * Represents a building type that can be constructed
 */
public enum BuildingType {
    
    // Resource production buildings
    SAWMILL("Sawmill", "Produces wood over time", PlotSize.SMALL, 1,
            createCost(ResourceType.WOOD, 50, ResourceType.STONE, 30)),
    
    STONE_MINE("Stone Mine", "Produces stone over time", PlotSize.SMALL, 1,
            createCost(ResourceType.WOOD, 30, ResourceType.STONE, 20)),
    
    GOLD_MINE("Gold Mine", "Produces gold over time", PlotSize.MEDIUM, 2,
            createCost(ResourceType.WOOD, 80, ResourceType.STONE, 60, ResourceType.IRON, 20)),
    
    MILL("Mill", "Produces food over time", PlotSize.SMALL, 1,
            createCost(ResourceType.WOOD, 60, ResourceType.STONE, 20)),
    
    FORGE("Forge", "Produces iron over time", PlotSize.MEDIUM, 2,
            createCost(ResourceType.WOOD, 100, ResourceType.STONE, 80)),
    
    // Military buildings
    TRAINING_CENTER("Training Center", "Trains soldier units", PlotSize.MEDIUM, 2,
            createCost(ResourceType.WOOD, 100, ResourceType.STONE, 100, ResourceType.GOLD, 50)),
    
    ARCHERY_STORE("Archery Store", "Trains archer units", PlotSize.SMALL, 2,
            createCost(ResourceType.WOOD, 80, ResourceType.STONE, 40, ResourceType.GOLD, 30)),
    
    STABLE("Stable", "Trains mounted units", PlotSize.LARGE, 3,
            createCost(ResourceType.WOOD, 150, ResourceType.STONE, 100, ResourceType.GOLD, 100, ResourceType.FOOD, 50)),
    
    KENNEL("Kennel", "Trains attack dogs", PlotSize.SMALL, 2,
            createCost(ResourceType.WOOD, 60, ResourceType.STONE, 30, ResourceType.FOOD, 40)),
    
    // Utility buildings
    ARMORY("Armory", "Upgrade weapons and armor", PlotSize.MEDIUM, 2,
            createCost(ResourceType.WOOD, 120, ResourceType.STONE, 120, ResourceType.IRON, 60)),
    
    MARKET("Market", "Trade resources", PlotSize.MEDIUM, 2,
            createCost(ResourceType.WOOD, 100, ResourceType.STONE, 80, ResourceType.GOLD, 40)),
    
    GUILD("Guild", "Hire special units", PlotSize.MEDIUM, 3,
            createCost(ResourceType.WOOD, 150, ResourceType.STONE, 150, ResourceType.GOLD, 100)),
    
    // Special buildings
    WORKSHOP("Workshop", "Build siege weapons", PlotSize.LARGE, 3,
            createCost(ResourceType.WOOD, 200, ResourceType.STONE, 150, ResourceType.IRON, 100)),
    
    LABORATORY("Laboratory", "Research upgrades", PlotSize.MEDIUM, 3,
            createCost(ResourceType.WOOD, 180, ResourceType.STONE, 180, ResourceType.GOLD, 150)),
    
    SABOTAGE_WORKSHOP("Sabotage Workshop", "Create traps and sabotage tools", PlotSize.SMALL, 3,
            createCost(ResourceType.WOOD, 80, ResourceType.STONE, 50, ResourceType.IRON, 40, ResourceType.GOLD, 60)),
    
    TRIFARROW("Trifarrow Tower", "Defensive arrow tower", PlotSize.SMALL, 2,
            createCost(ResourceType.WOOD, 100, ResourceType.STONE, 80, ResourceType.IRON, 30));
    
    private final String displayName;
    private final String description;
    private final PlotSize requiredPlotSize;
    private final int requiredLevel;
    private final Map<ResourceType, Integer> cost;
    
    BuildingType(String displayName, String description, PlotSize requiredPlotSize, 
                 int requiredLevel, Map<ResourceType, Integer> cost) {
        this.displayName = displayName;
        this.description = description;
        this.requiredPlotSize = requiredPlotSize;
        this.requiredLevel = requiredLevel;
        this.cost = cost;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    public String getDescription() {
        return description;
    }
    
    public PlotSize getRequiredPlotSize() {
        return requiredPlotSize;
    }
    
    public int getRequiredLevel() {
        return requiredLevel;
    }
    
    public Map<ResourceType, Integer> getCost() {
        return new EnumMap<>(cost);
    }
    
    public String getSchematicName() {
        return name() + ".schematic";
    }
    
    /**
     * Get building type from name
     */
    public static BuildingType fromName(String name) {
        if (name == null) return null;
        
        for (BuildingType type : values()) {
            if (type.name().equalsIgnoreCase(name) ||
                type.displayName.equalsIgnoreCase(name)) {
                return type;
            }
        }
        return null;
    }
    
    // Helper method to create cost maps
    private static Map<ResourceType, Integer> createCost(Object... args) {
        Map<ResourceType, Integer> cost = new EnumMap<>(ResourceType.class);
        for (int i = 0; i < args.length; i += 2) {
            ResourceType type = (ResourceType) args[i];
            Integer amount = (Integer) args[i + 1];
            cost.put(type, amount);
        }
        return cost;
    }
}
