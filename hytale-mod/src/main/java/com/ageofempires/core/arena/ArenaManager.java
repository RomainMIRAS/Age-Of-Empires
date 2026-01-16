package com.ageofempires.core.arena;

import com.ageofempires.AgeOfEmpiresPlugin;
import java.util.*;
import java.util.logging.Logger;

/**
 * Manages arenas for the game
 */
public class ArenaManager {
    
    private final AgeOfEmpiresPlugin plugin;
    private final Logger logger;
    private final Map<UUID, Arena> arenas;
    private final Map<String, Arena> arenasByName;
    
    public ArenaManager(AgeOfEmpiresPlugin plugin) {
        this.plugin = plugin;
        this.logger = plugin.getLogger();
        this.arenas = new HashMap<>();
        this.arenasByName = new HashMap<>();
    }
    
    /**
     * Load arenas from configuration
     */
    public void loadArenas() {
        // TODO: Load from config
        logger.info("Loading arenas...");
        
        // For now, create a default arena for testing
        Arena defaultArena = new Arena(UUID.randomUUID(), "default", true);
        registerArena(defaultArena);
        
        logger.info("✅ Loaded " + arenas.size() + " arena(s)");
    }
    
    /**
     * Register an arena
     * @param arena The arena to register
     */
    public void registerArena(Arena arena) {
        arenas.put(arena.getId(), arena);
        arenasByName.put(arena.getName().toLowerCase(), arena);
    }
    
    /**
     * Get arena by UUID
     * @param id The arena UUID
     * @return The arena, or null
     */
    public Arena getArena(UUID id) {
        return arenas.get(id);
    }
    
    /**
     * Get arena by name
     * @param name The arena name
     * @return The arena, or null
     */
    public Arena getArena(String name) {
        return arenasByName.get(name.toLowerCase());
    }
    
    /**
     * Find an available arena
     * @return An available arena, or null if none
     */
    public Arena findAvailableArena() {
        return arenas.values().stream()
            .filter(Arena::isEnabled)
            .findFirst()
            .orElse(null);
    }
    
    /**
     * Get arena count
     * @return Number of arenas
     */
    public int getArenaCount() {
        return arenas.size();
    }
    
    /**
     * Get all arenas
     * @return Collection of arenas
     */
    public Collection<Arena> getAllArenas() {
        return Collections.unmodifiableCollection(arenas.values());
    }
}
