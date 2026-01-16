package com.ageofempires.arena;

import com.ageofempires.AgeOfEmpiresPlugin;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

/**
 * Manages all arena configurations
 */
public class ArenaManager {

    private final AgeOfEmpiresPlugin plugin;
    private final Logger logger;
    private final Map<String, Arena> arenas;
    
    public ArenaManager(AgeOfEmpiresPlugin plugin) {
        this.plugin = plugin;
        this.logger = plugin.getLogger();
        this.arenas = new ConcurrentHashMap<>();
        
        // Load arenas from config
        loadArenas();
    }
    
    /**
     * Load arenas from configuration
     */
    private void loadArenas() {
        // TODO: Load from config file
        // For now, create a default arena
        Arena defaultArena = new Arena("default");
        defaultArena.setEnabled(true);
        arenas.put("default", defaultArena);
        
        logger.info("Loaded " + arenas.size() + " arenas");
    }
    
    /**
     * Create a new arena
     */
    public Arena createArena(String name) {
        if (arenas.containsKey(name.toLowerCase())) {
            return arenas.get(name.toLowerCase());
        }
        
        Arena arena = new Arena(name);
        arenas.put(name.toLowerCase(), arena);
        logger.info("Created new arena: " + name);
        return arena;
    }
    
    /**
     * Get arena by name
     */
    public Arena getArena(String name) {
        return arenas.get(name.toLowerCase());
    }
    
    /**
     * Get all arenas
     */
    public Collection<Arena> getAllArenas() {
        return Collections.unmodifiableCollection(arenas.values());
    }
    
    /**
     * Get enabled arenas
     */
    public Collection<Arena> getEnabledArenas() {
        List<Arena> enabled = new ArrayList<>();
        for (Arena arena : arenas.values()) {
            if (arena.isEnabled() && arena.isSetupComplete()) {
                enabled.add(arena);
            }
        }
        return enabled;
    }
    
    /**
     * Get best available arena for joining
     */
    public Arena getBestAvailableArena() {
        // Find arena with most players but not full
        // For now, just return first enabled arena
        return getEnabledArenas().stream().findFirst().orElse(null);
    }
    
    /**
     * Get arena count
     */
    public int getArenaCount() {
        return arenas.size();
    }
    
    /**
     * Delete an arena
     */
    public boolean deleteArena(String name) {
        Arena arena = arenas.remove(name.toLowerCase());
        if (arena != null) {
            logger.info("Deleted arena: " + name);
            return true;
        }
        return false;
    }
    
    /**
     * Save all arenas to config
     */
    public void saveArenas() {
        // TODO: Implement saving to config
        logger.info("Saved " + arenas.size() + " arenas");
    }
    
    /**
     * Reload arenas from config
     */
    public void reloadArenas() {
        arenas.clear();
        loadArenas();
    }
}
