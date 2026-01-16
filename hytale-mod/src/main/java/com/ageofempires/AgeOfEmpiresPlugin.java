package com.ageofempires;

import com.ageofempires.core.game.GameManager;
import com.ageofempires.core.kits.KitManager;
import com.ageofempires.arena.ArenaManager;
import com.ageofempires.commands.*;
import com.ageofempires.config.AoeConfig;
import com.ageofempires.events.PlayerConnectionHandler;
import com.ageofempires.events.PlayerCombatHandler;
import com.ageofempires.events.BlockInteractionHandler;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import com.hypixel.hytale.server.core.util.Config;
import com.hypixel.hytale.server.core.scheduling.Scheduler;

import javax.annotation.Nonnull;

/**
 * Main plugin class for Age of Empires - Hytale Edition
 * 
 * <p>A team-based PvP minigame where players collect resources,
 * build structures, and battle to destroy the enemy Forums.</p>
 * 
 * @author RomainMiras
 * @version 2.0.0
 */
public class AgeOfEmpiresPlugin extends JavaPlugin {

    private static AgeOfEmpiresPlugin instance;
    
    // Configuration (loaded before setup via withConfig)
    private final Config<AoeConfig> config = this.withConfig("AgeOfEmpires", AoeConfig.CODEC);
    
    // Core managers
    private GameManager gameManager;
    private KitManager kitManager;
    private ArenaManager arenaManager;
    
    // Scheduler for async tasks
    private Scheduler scheduler;
    
    public AgeOfEmpiresPlugin(@Nonnull JavaPluginInit init) {
        super(init);
    }
    
    public static AgeOfEmpiresPlugin get() {
        return instance;
    }
    
    @Override
    protected void setup() {
        instance = this;
        
        getLogger().info("========================================");
        getLogger().info("  Age of Empires - Hytale Edition");
        getLogger().info("  Version: 2.0.0");
        getLogger().info("========================================");
        
        // Initialize scheduler
        this.scheduler = getServer().getScheduler();
        
        // Initialize managers
        initializeManagers();
        
        // Register events
        registerEvents();
        
        // Register commands
        registerCommands();
        
        getLogger().info("✅ Setup complete!");
    }
    
    @Override
    protected void start() {
        getLogger().info("🎮 Age of Empires is now active!");
        
        // Load configuration
        AoeConfig cfg = config.get();
        getLogger().info("📊 Config loaded - Max players per team: " + cfg.getMaxPlayersPerTeam());
        
        getLogger().info("📊 Loaded " + arenaManager.getArenaCount() + " arena(s)");
        getLogger().info("📊 Registered " + kitManager.getKitCount() + " kits");
        
        // Start game tick (process buildings, etc.)
        startGameTick();
    }
    
    @Override
    protected void shutdown() {
        getLogger().info("🛑 Shutting down Age of Empires...");
        
        if (gameManager != null) {
            gameManager.shutdown();
        }
        
        instance = null;
        getLogger().info("✅ Shutdown complete!");
    }
    
    private void initializeManagers() {
        getLogger().info("🔧 Initializing managers...");
        
        arenaManager = new ArenaManager(this);
        kitManager = new KitManager(this);
        gameManager = new GameManager(this);
        
        getLogger().info("✅ Managers initialized");
    }
    
    private void registerEvents() {
        getLogger().info("🎪 Registering event handlers...");
        
        // Player events
        new PlayerConnectionHandler(this).register(getEventRegistry());
        new PlayerCombatHandler(this).register(getEventRegistry());
        
        // World events
        new BlockInteractionHandler(this).register(getEventRegistry());
        
        getLogger().info("✅ Event handlers registered");
    }
    
    private void registerCommands() {
        getLogger().info("🎮 Registering commands...");
        
        // Main command collection /aoe
        getCommandRegistry().registerCommand(new AoeCommandCollection(this));
        
        // Game commands
        getCommandRegistry().registerCommand(new JoinCommand(this));
        getCommandRegistry().registerCommand(new LeaveCommand(this));
        getCommandRegistry().registerCommand(new KitCommand(this));
        getCommandRegistry().registerCommand(new StuckCommand(this));
        getCommandRegistry().registerCommand(new HelpCommand(this));
        getCommandRegistry().registerCommand(new ForceStartCommand(this));
        getCommandRegistry().registerCommand(new VoteCommand(this));
        
        getLogger().info("✅ Commands registered");
    }
    
    /**
     * Start the game tick for processing building production, etc.
     */
    private void startGameTick() {
        scheduler.runRepeating(() -> {
            gameManager.tick();
        }, 20L, 20L); // Every second
    }
    
    // ==================== Getters ====================
    
    public AoeConfig getConfig() { 
        return config.get(); 
    }
    
    public GameManager getGameManager() { 
        return gameManager; 
    }
    
    public KitManager getKitManager() { 
        return kitManager; 
    }
    
    public ArenaManager getArenaManager() { 
        return arenaManager; 
    }
    
    public Scheduler getScheduler() {
        return scheduler;
    }
}
