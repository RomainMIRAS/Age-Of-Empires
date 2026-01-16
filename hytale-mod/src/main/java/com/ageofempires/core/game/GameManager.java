package com.ageofempires.core.game;

import com.ageofempires.AgeOfEmpiresPlugin;
import com.ageofempires.core.arena.Arena;
import com.ageofempires.core.arena.ArenaManager;
import com.ageofempires.core.team.TeamManager;
import com.hypixel.hytale.server.core.universe.PlayerRef;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * Main manager for game sessions and lifecycle
 * 
 * <p>This manager handles:</p>
 * <ul>
 *   <li>Game session creation and destruction</li>
 *   <li>Player session management</li>
 *   <li>Game state transitions</li>
 *   <li>Game tick updates</li>
 * </ul>
 */
public class GameManager {
    
    private final AgeOfEmpiresPlugin plugin;
    private final ArenaManager arenaManager;
    private final TeamManager teamManager;
    private final Logger logger;
    
    // Active game sessions by arena UUID
    private final Map<UUID, GameSession> activeSessions;
    
    // Player to game session mapping
    private final Map<UUID, GameSession> playerSessions;
    
    // Game tick task
    private ScheduledFuture<?> gameTickTask;
    
    public GameManager(AgeOfEmpiresPlugin plugin, ArenaManager arenaManager, TeamManager teamManager) {
        this.plugin = plugin;
        this.arenaManager = arenaManager;
        this.teamManager = teamManager;
        this.logger = plugin.getLogger();
        
        this.activeSessions = new HashMap<>();
        this.playerSessions = new HashMap<>();
    }
    
    /**
     * Start the game tick for all active sessions
     */
    public void startGameTick() {
        if (gameTickTask != null && !gameTickTask.isDone()) {
            logger.warning("Game tick already running!");
            return;
        }
        
        // Run every second (1000ms)
        gameTickTask = plugin.getServer().getScheduledExecutor().scheduleAtFixedRate(
            this::tick,
            0,
            1,
            TimeUnit.SECONDS
        );
        
        // Register with task registry for auto-cleanup
        plugin.getTaskRegistry().registerTask(CompletableFuture.runAsync(() -> {}));
        
        logger.info("🎮 Game tick started");
    }
    
    /**
     * Tick all active game sessions
     */
    private void tick() {
        activeSessions.values().forEach(session -> {
            try {
                session.tick();
            } catch (Exception e) {
                logger.severe("Error ticking game session: " + e.getMessage());
                e.printStackTrace();
            }
        });
    }
    
    /**
     * Create a new game session for an arena
     * @param arena The arena
     * @return The created session
     */
    public GameSession createSession(Arena arena) {
        if (activeSessions.containsKey(arena.getId())) {
            logger.warning("Session already exists for arena: " + arena.getName());
            return activeSessions.get(arena.getId());
        }
        
        GameSession session = new GameSession(plugin, arena, teamManager);
        activeSessions.put(arena.getId(), session);
        
        logger.info("✅ Created game session for arena: " + arena.getName());
        return session;
    }
    
    /**
     * Get a game session by arena UUID
     * @param arenaId The arena UUID
     * @return The session, or null if not found
     */
    public GameSession getSession(UUID arenaId) {
        return activeSessions.get(arenaId);
    }
    
    /**
     * Get the game session a player is in
     * @param playerUuid The player UUID
     * @return The session, or null if not in any
     */
    public GameSession getPlayerSession(UUID playerUuid) {
        return playerSessions.get(playerUuid);
    }
    
    /**
     * Add a player to a session
     * @param player The player
     * @param session The session
     */
    public void addPlayerToSession(PlayerRef player, GameSession session) {
        playerSessions.put(player.getUuid(), session);
    }
    
    /**
     * Remove a player from their session
     * @param playerUuid The player UUID
     */
    public void removePlayerFromSession(UUID playerUuid) {
        GameSession session = playerSessions.remove(playerUuid);
        if (session != null) {
            session.removePlayer(playerUuid);
        }
    }
    
    /**
     * Handle player join to game
     * @param player The player joining
     */
    public void handlePlayerJoin(PlayerRef player) {
        // Find an available arena
        Arena arena = arenaManager.findAvailableArena();
        if (arena == null) {
            // Send message: no arena available
            logger.warning("No available arena for player: " + player.getUsername());
            return;
        }
        
        // Get or create session
        GameSession session = getSession(arena.getId());
        if (session == null) {
            session = createSession(arena);
        }
        
        // Try to add player to session
        if (session.addPlayer(player)) {
            addPlayerToSession(player, session);
            logger.info("✅ Player " + player.getUsername() + " joined session for arena: " + arena.getName());
        } else {
            logger.warning("❌ Failed to add player " + player.getUsername() + " to session");
        }
    }
    
    /**
     * Handle player leave from game
     * @param playerUuid The player UUID
     */
    public void handlePlayerLeave(UUID playerUuid) {
        removePlayerFromSession(playerUuid);
        logger.info("Player left game session");
    }
    
    /**
     * Stop all active games
     */
    public void stopAllGames() {
        logger.info("Stopping all active game sessions...");
        
        activeSessions.values().forEach(session -> {
            try {
                session.endGame();
            } catch (Exception e) {
                logger.severe("Error stopping session: " + e.getMessage());
            }
        });
        
        activeSessions.clear();
        playerSessions.clear();
        
        if (gameTickTask != null) {
            gameTickTask.cancel(false);
            gameTickTask = null;
        }
        
        logger.info("✅ All sessions stopped");
    }
    
    /**
     * Get count of active sessions
     * @return Number of active sessions
     */
    public int getActiveSessionCount() {
        return activeSessions.size();
    }
    
    /**
     * Get count of players in games
     * @return Number of players
     */
    public int getPlayerCount() {
        return playerSessions.size();
    }
}
