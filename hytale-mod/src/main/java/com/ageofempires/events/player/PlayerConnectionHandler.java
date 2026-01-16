package com.ageofempires.events.player;

import com.ageofempires.AgeOfEmpiresPlugin;
import com.ageofempires.core.game.GameManager;
import com.hypixel.hytale.server.core.event.EventRegistry;
import com.hypixel.hytale.server.core.event.events.player.PlayerConnectEvent;
import com.hypixel.hytale.server.core.event.events.player.PlayerDisconnectEvent;
import com.hypixel.hytale.server.core.universe.PlayerRef;

import java.util.logging.Logger;

/**
 * Handles player connection and disconnection events
 */
public class PlayerConnectionHandler {
    
    private final AgeOfEmpiresPlugin plugin;
    private final GameManager gameManager;
    private final Logger logger;
    
    public PlayerConnectionHandler(AgeOfEmpiresPlugin plugin, GameManager gameManager) {
        this.plugin = plugin;
        this.gameManager = gameManager;
        this.logger = plugin.getLogger();
    }
    
    /**
     * Register event listeners
     * @param events The event registry
     */
    public void register(EventRegistry events) {
        events.register(PlayerConnectEvent.class, this::onPlayerConnect);
        events.register(PlayerDisconnectEvent.class, this::onPlayerDisconnect);
    }
    
    /**
     * Handle player connection
     * @param event The connection event
     */
    private void onPlayerConnect(PlayerConnectEvent event) {
        PlayerRef player = event.getPlayerRef();
        logger.info("Player connected: " + player.getUsername());
        
        // Add player to game
        gameManager.handlePlayerJoin(player);
    }
    
    /**
     * Handle player disconnection
     * @param event The disconnection event
     */
    private void onPlayerDisconnect(PlayerDisconnectEvent event) {
        PlayerRef player = event.getPlayerRef();
        logger.info("Player disconnected: " + player.getUsername());
        
        // Remove player from game
        gameManager.handlePlayerLeave(player.getUuid());
    }
}
