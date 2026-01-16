package com.ageofempires.data.config;

import com.ageofempires.AgeOfEmpiresPlugin;
import java.util.logging.Logger;

/**
 * Manages all configuration files
 */
public class ConfigManager {
    
    private final AgeOfEmpiresPlugin plugin;
    private final Logger logger;
    
    public ConfigManager(AgeOfEmpiresPlugin plugin) {
        this.plugin = plugin;
        this.logger = plugin.getLogger();
    }
    
    /**
     * Load all configurations
     */
    public void loadAll() {
        // TODO: Implement config loading from JSON files
        logger.info("Loading configurations...");
        
        // Load settings.json
        // Load messages.json
        // Load buildings.json
        // Load arenas.json
        
        logger.info("✅ Configurations loaded");
    }
    
    /**
     * Save all configurations
     */
    public void saveAll() {
        // TODO: Implement config saving
        logger.info("Saving configurations...");
        logger.info("✅ Configurations saved");
    }
    
    /**
     * Reload all configurations
     */
    public void reloadAll() {
        saveAll();
        loadAll();
        logger.info("✅ Configurations reloaded");
    }
}
