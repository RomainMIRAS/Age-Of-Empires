package com.ageofempires;

import com.ageofempires.core.game.GameManager;
import com.ageofempires.core.team.TeamManager;
import com.ageofempires.core.arena.ArenaManager;
import com.ageofempires.commands.AoeCommand;
import com.ageofempires.commands.game.*;
import com.ageofempires.data.config.ConfigManager;
import com.ageofempires.events.player.PlayerConnectionHandler;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;

import javax.annotation.Nonnull;

/**
 * Main plugin class for Age of Empires - Hytale Edition
 * 
 * <p>Age of Empires is a team-based PvP minigame where players collect resources,
 * build structures, and battle to destroy the enemy Forums.</p>
 * 
 * <p><b>Key Features:</b></p>
 * <ul>
 *   <li>4 teams (Blue, Red, Green, Yellow) with up to customizable max players per team</li>
 *   <li>Resource gathering system (Wood, Stone, Gold)</li>
 *   <li>Building construction on plots</li>
 *   <li>Age progression system (Ages 1-4)</li>
 *   <li>Team voting for purchases</li>
 *   <li>Kit system for equipment</li>
 * </ul>
 * 
 * @author RomainMiras
 * @version 2.0.0
 * @since 2026-01-16
 */
public class AgeOfEmpiresPlugin extends JavaPlugin {

    // Singleton instance for easy access (supports hot-reload)
    private static AgeOfEmpiresPlugin instance;
    
    // Core managers
    private ConfigManager configManager;
    private GameManager gameManager;
    private TeamManager teamManager;
    private ArenaManager arenaManager;
    
    // Event handlers
    private PlayerConnectionHandler playerConnectionHandler;
    
    /**
     * Constructor required by Hytale plugin system
     * @param init Plugin initialization context
     */
    public AgeOfEmpiresPlugin(@Nonnull JavaPluginInit init) {
        super(init);
    }
    
    /**
     * Get the plugin instance
     * @return The plugin singleton instance
     */
    public static AgeOfEmpiresPlugin get() {
        return instance;
    }
    
    /**
     * Setup phase - Called before start()
     * Initialize managers, register events, commands, etc.
     */
    @Override
    protected void setup() {
        // Refresh instance for hot-reload support
        instance = this;
        
        getLogger().info("========================================");
        getLogger().info("  Age of Empires - Hytale Edition");
        getLogger().info("  Version: 2.0.0");
        getLogger().info("  Starting setup...");
        getLogger().info("========================================");
        
        // Initialize configuration
        initializeConfiguration();
        
        // Initialize managers
        initializeManagers();
        
        // Register event handlers
        registerEventHandlers();
        
        // Register commands
        registerCommands();
        
        getLogger().info("✅ Setup complete!");
    }
    
    /**
     * Start phase - Called after setup()
     * Start services, load data, etc.
     */
    @Override
    protected void start() {
        getLogger().info("🎮 Age of Empires is now active!");
        
        // Load arenas from configuration
        arenaManager.loadArenas();
        
        // Log statistics
        getLogger().info("📊 Loaded " + arenaManager.getArenaCount() + " arena(s)");
        
        // Start game tick if needed
        gameManager.startGameTick();
    }
    
    /**
     * Shutdown phase - Called when plugin is disabled
     * Clean up resources, save data, cancel tasks
     */
    @Override
    protected void shutdown() {
        getLogger().info("🛑 Shutting down Age of Empires...");
        
        // Stop all active games
        if (gameManager != null) {
            gameManager.stopAllGames();
        }
        
        // Save data
        if (configManager != null) {
            configManager.saveAll();
        }
        
        // Cleanup
        instance = null;
        
        getLogger().info("✅ Shutdown complete. Goodbye!");
    }
    
    /**
     * Initialize configuration manager and load configs
     */
    private void initializeConfiguration() {
        getLogger().info("📂 Loading configuration...");
        
        configManager = new ConfigManager(this);
        configManager.loadAll();
        
        getLogger().info("✅ Configuration loaded");
    }
    
    /**
     * Initialize core game managers
     */
    private void initializeManagers() {
        getLogger().info("🔧 Initializing managers...");
        
        // Arena manager
        arenaManager = new ArenaManager(this);
        getLogger().info("  ✓ Arena Manager");
        
        // Team manager
        teamManager = new TeamManager(this);
        getLogger().info("  ✓ Team Manager");
        
        // Game manager (depends on other managers)
        gameManager = new GameManager(this, arenaManager, teamManager);
        getLogger().info("  ✓ Game Manager");
        
        getLogger().info("✅ Managers initialized");
    }
    
    /**
     * Register event handlers for game events
     */
    private void registerEventHandlers() {
        getLogger().info("🎪 Registering event handlers...");
        
        // Player connection/disconnection events
        playerConnectionHandler = new PlayerConnectionHandler(this, gameManager);
        playerConnectionHandler.register(getEventRegistry());
        getLogger().info("  ✓ Player Connection Handler");
        
        // TODO: Register other event handlers as we implement them
        // - PlayerMovementHandler
        // - PlayerCombatHandler
        // - BlockBreakHandler
        // - BlockPlaceHandler
        // etc.
        
        getLogger().info("✅ Event handlers registered");
    }
    
    /**
     * Register commands for the plugin
     */
    private void registerCommands() {
        getLogger().info("🎮 Registering commands...");
        
        // Main command /aoe (with subcommands)
        getCommandRegistry().registerCommand(new AoeCommand(this));
        getLogger().info("  ✓ /aoe (main command)");
        
        // Game commands
        getCommandRegistry().registerCommand(new LeaveCommand(this, gameManager));
        getLogger().info("  ✓ /leave");
        
        getCommandRegistry().registerCommand(new KitCommand(this, gameManager));
        getLogger().info("  ✓ /kit");
        
        getCommandRegistry().registerCommand(new StuckCommand(this, gameManager));
        getLogger().info("  ✓ /stuck");
        
        getCommandRegistry().registerCommand(new HelpCommand(this));
        getLogger().info("  ✓ /help");
        
        // Admin commands
        getCommandRegistry().registerCommand(new ForceStartCommand(this, gameManager));
        getLogger().info("  ✓ /forcestart");
        
        getLogger().info("✅ Commands registered");
    }
    
    // ==================== Getters ====================
    
    public ConfigManager getConfigManager() {
        return configManager;
    }
    
    public GameManager getGameManager() {
        return gameManager;
    }
    
    public TeamManager getTeamManager() {
        return teamManager;
    }
    
    public ArenaManager getArenaManager() {
        return arenaManager;
    }
}
