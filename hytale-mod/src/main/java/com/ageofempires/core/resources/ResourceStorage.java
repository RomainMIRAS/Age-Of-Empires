package com.ageofempires.core.resources;

import java.util.EnumMap;
import java.util.Map;

/**
 * Holds resource amounts for a team
 */
public class ResourceStorage {

    private final Map<ResourceType, Integer> resources;
    
    public ResourceStorage() {
        this.resources = new EnumMap<>(ResourceType.class);
        
        // Initialize all resources to 0
        for (ResourceType type : ResourceType.values()) {
            resources.put(type, 0);
        }
    }
    
    /**
     * Get amount of a specific resource
     */
    public int getAmount(ResourceType type) {
        return resources.getOrDefault(type, 0);
    }
    
    /**
     * Add resources
     */
    public void add(ResourceType type, int amount) {
        int current = getAmount(type);
        resources.put(type, current + amount);
    }
    
    /**
     * Remove resources (returns true if successful)
     */
    public boolean remove(ResourceType type, int amount) {
        int current = getAmount(type);
        if (current < amount) {
            return false;
        }
        resources.put(type, current - amount);
        return true;
    }
    
    /**
     * Set resource amount directly
     */
    public void set(ResourceType type, int amount) {
        resources.put(type, Math.max(0, amount));
    }
    
    /**
     * Check if has enough of a resource
     */
    public boolean hasEnough(ResourceType type, int amount) {
        return getAmount(type) >= amount;
    }
    
    /**
     * Check if has enough of multiple resources (cost map)
     */
    public boolean hasEnough(Map<ResourceType, Integer> costs) {
        for (Map.Entry<ResourceType, Integer> entry : costs.entrySet()) {
            if (!hasEnough(entry.getKey(), entry.getValue())) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Remove multiple resources at once (returns true if successful)
     */
    public boolean removeAll(Map<ResourceType, Integer> costs) {
        if (!hasEnough(costs)) {
            return false;
        }
        
        for (Map.Entry<ResourceType, Integer> entry : costs.entrySet()) {
            remove(entry.getKey(), entry.getValue());
        }
        return true;
    }
    
    /**
     * Get all resources as a map
     */
    public Map<ResourceType, Integer> getAll() {
        return new EnumMap<>(resources);
    }
    
    /**
     * Reset all resources to 0
     */
    public void reset() {
        for (ResourceType type : ResourceType.values()) {
            resources.put(type, 0);
        }
    }
    
    /**
     * Get total resource value (for scoring)
     */
    public int getTotalValue() {
        int total = 0;
        for (Map.Entry<ResourceType, Integer> entry : resources.entrySet()) {
            total += entry.getValue();
        }
        return total;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Resources: ");
        for (ResourceType type : ResourceType.values()) {
            sb.append(type.getColoredName())
              .append(": §f")
              .append(getAmount(type))
              .append("§7, ");
        }
        return sb.toString();
    }
}
