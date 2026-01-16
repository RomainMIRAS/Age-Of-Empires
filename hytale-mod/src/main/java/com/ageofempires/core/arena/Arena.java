package com.ageofempires.core.arena;

import java.util.UUID;

/**
 * Represents a game arena
 */
public class Arena {
    
    private final UUID id;
    private final String name;
    private boolean enabled;
    
    // TODO: Add spawn locations, plot locations, etc.
    
    public Arena(UUID id, String name, boolean enabled) {
        this.id = id;
        this.name = name;
        this.enabled = enabled;
    }
    
    public UUID getId() { return id; }
    public String getName() { return name; }
    public boolean isEnabled() { return enabled; }
    public void setEnabled(boolean enabled) { this.enabled = enabled; }
}
