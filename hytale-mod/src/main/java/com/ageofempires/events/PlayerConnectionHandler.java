package com.ageofempires.events;

import com.ageofempires.AgeOfEmpiresPlugin;
import com.ageofempires.data.PlayerSession;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.events.EventRegistry;
import com.hypixel.hytale.server.core.events.player.PlayerConnectEvent;
import com.hypixel.hytale.server.core.events.player.PlayerDisconnectEvent;
import com.hypixel.hytale.server.core.events.player.PlayerReadyEvent;
import com.hypixel.hytale.server.core.Message;

import java.util.logging.Logger;

/**
 * Handles player connection events
 */
public class PlayerConnectionHandler {

    private final AgeOfEmpiresPlugin plugin;
    private final Logger logger;
    
    public PlayerConnectionHandler(AgeOfEmpiresPlugin plugin) {
        this.plugin = plugin;
        this.logger = plugin.getLogger();
    }
    
    /**
     * Register all connection event handlers
     */
    public void register(EventRegistry eventRegistry) {
        eventRegistry.register(PlayerConnectEvent.class, this::onPlayerConnect);
        eventRegistry.register(PlayerReadyEvent.class, this::onPlayerReady);
        eventRegistry.register(PlayerDisconnectEvent.class, this::onPlayerDisconnect);
        
        logger.info("Connection event handlers registered");
    }
    
    /**
     * Handle player connecting
     */
    private void onPlayerConnect(PlayerConnectEvent event) {
        Player player = event.getPlayer();
        logger.info("Player connecting: " + player.getName());
    }
    
    /**
     * Handle player ready (fully joined)
     */
    private void onPlayerReady(PlayerReadyEvent event) {
        Player player = event.getPlayer();
        
        // Send welcome message
        player.sendMessage(Message.raw("§9§l===================================="));
        player.sendMessage(Message.raw("§b  Welcome to Age of Empires!"));
        player.sendMessage(Message.raw("§9§l===================================="));
        player.sendMessage(Message.raw(""));
        player.sendMessage(Message.raw("§7Use §f/join§7 to join a game"));
        player.sendMessage(Message.raw("§7Use §f/aoehelp§7 for commands"));
        player.sendMessage(Message.raw(""));
        
        logger.info("Player ready: " + player.getName());
    }
    
    /**
     * Handle player disconnecting
     */
    private void onPlayerDisconnect(PlayerDisconnectEvent event) {
        Player player = event.getPlayer();
        
        // Clean up player session if they were in a game
        PlayerSession session = plugin.getGameManager().getPlayerSession(player.getRef());
        
        if (session != null) {
            // Remove from game session
            if (session.getGameSession() != null) {
                session.getGameSession().removePlayer(player.getRef());
            }
            
            // Remove session
            plugin.getGameManager().removePlayerSession(player.getRef());
            
            logger.info("Cleaned up session for player: " + player.getName());
        }
        
        logger.info("Player disconnected: " + player.getName());
    }
}
