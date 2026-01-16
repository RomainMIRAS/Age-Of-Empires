package com.ageofempires.events;

import com.ageofempires.AgeOfEmpiresPlugin;
import com.ageofempires.data.PlayerSession;
import com.ageofempires.core.team.Team;
import com.ageofempires.core.resources.ResourceType;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.events.EventRegistry;
import com.hypixel.hytale.server.core.events.block.BlockBreakEvent;
import com.hypixel.hytale.server.core.events.block.BlockPlaceEvent;
import com.hypixel.hytale.server.core.events.player.PlayerInteractEvent;
import com.hypixel.hytale.server.core.world.block.Block;
import com.hypixel.hytale.server.core.Message;

import java.util.logging.Logger;

/**
 * Handles all block interaction events for Age of Empires
 */
public class BlockInteractionHandler {

    private final AgeOfEmpiresPlugin plugin;
    private final Logger logger;
    
    public BlockInteractionHandler(AgeOfEmpiresPlugin plugin) {
        this.plugin = plugin;
        this.logger = plugin.getLogger();
    }
    
    /**
     * Register all block interaction event handlers
     */
    public void register(EventRegistry eventRegistry) {
        // Block breaking (resource gathering)
        eventRegistry.register(BlockBreakEvent.class, this::onBlockBreak);
        
        // Block placing (building)
        eventRegistry.register(BlockPlaceEvent.class, this::onBlockPlace);
        
        // Player interaction (for buildings, NPCs)
        eventRegistry.register(PlayerInteractEvent.class, this::onPlayerInteract);
        
        logger.info("Block interaction event handlers registered");
    }
    
    /**
     * Handle block breaking - resource gathering
     */
    private void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        
        if (player == null) return;
        
        PlayerSession session = plugin.getGameManager().getPlayerSession(player.getRef());
        
        // Check if player is in a game
        if (session == null || session.getGameSession() == null) {
            return;
        }
        
        GameSession gameSession = session.getGameSession();
        
        // Only allow breaking during active game
        if (!gameSession.isRunning()) {
            event.setCancelled(true);
            return;
        }
        
        // Check for resource blocks
        ResourceType resourceType = getResourceTypeFromBlock(block);
        
        if (resourceType != null) {
            handleResourceGathering(player, session, gameSession, resourceType, event);
        } else {
            // Check if breaking is allowed in this area
            if (!isBreakAllowed(player, session, block)) {
                event.setCancelled(true);
                player.sendMessage(Message.raw("§cYou cannot break blocks here!"));
            }
        }
    }
    
    /**
     * Handle resource gathering from block
     */
    private void handleResourceGathering(Player player, PlayerSession session, 
                                          GameSession gameSession, ResourceType resource,
                                          BlockBreakEvent event) {
        Team team = session.getTeam();
        if (team == null) return;
        
        // Calculate resource amount with kit bonus
        int baseAmount = resource.getBaseAmount();
        int bonus = 0;
        
        if (session.getSelectedKit() != null) {
            bonus = session.getSelectedKit().getAbilities().getResourceGatherBonus();
        }
        
        int totalAmount = baseAmount + (baseAmount * bonus / 100);
        
        // Add resources to team
        gameSession.getTeamData(team).addResource(resource, totalAmount);
        
        // Notify player
        player.sendMessage(Message.raw("§a+" + totalAmount + " " + resource.getDisplayName()));
        
        // Update scoreboard/HUD
        // TODO: Update player HUD with new resource totals
        
        // Track for stats
        session.addResourcesGathered(resource, totalAmount);
    }
    
    /**
     * Determine resource type from block type
     */
    private ResourceType getResourceTypeFromBlock(Block block) {
        String blockType = block.getBlockType().toLowerCase();
        
        // Wood resources
        if (blockType.contains("log") || blockType.contains("wood") || blockType.contains("tree")) {
            return ResourceType.WOOD;
        }
        
        // Stone resources
        if (blockType.contains("stone") || blockType.contains("cobble") || blockType.contains("rock")) {
            return ResourceType.STONE;
        }
        
        // Gold ore
        if (blockType.contains("gold_ore") || blockType.contains("gold")) {
            return ResourceType.GOLD;
        }
        
        // Iron ore
        if (blockType.contains("iron_ore") || blockType.contains("iron")) {
            return ResourceType.IRON;
        }
        
        // Food sources
        if (blockType.contains("wheat") || blockType.contains("crop") || blockType.contains("farm")) {
            return ResourceType.FOOD;
        }
        
        return null;
    }
    
    /**
     * Check if breaking is allowed at this location
     */
    private boolean isBreakAllowed(Player player, PlayerSession session, Block block) {
        GameSession gameSession = session.getGameSession();
        if (gameSession == null) return false;
        
        Team team = session.getTeam();
        if (team == null) return false;
        
        // Check if it's in team territory or neutral zone
        // TODO: Implement territory system
        
        // For now, allow breaking in game area
        return true;
    }
    
    /**
     * Handle block placing - building
     */
    private void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        
        if (player == null) return;
        
        PlayerSession session = plugin.getGameManager().getPlayerSession(player.getRef());
        
        // Check if player is in a game
        if (session == null || session.getGameSession() == null) {
            return;
        }
        
        GameSession gameSession = session.getGameSession();
        
        // Only allow placing during active game
        if (!gameSession.isRunning()) {
            event.setCancelled(true);
            return;
        }
        
        // Check if player's kit allows building
        if (session.getSelectedKit() != null && !session.getSelectedKit().getAbilities().canBuild()) {
            event.setCancelled(true);
            player.sendMessage(Message.raw("§cYour kit cannot place blocks!"));
            return;
        }
        
        // Check if placing is allowed at this location
        if (!isPlaceAllowed(player, session, event.getBlockPosition())) {
            event.setCancelled(true);
            player.sendMessage(Message.raw("§cYou cannot place blocks here!"));
        }
    }
    
    /**
     * Check if placing is allowed at this location
     */
    private boolean isPlaceAllowed(Player player, PlayerSession session, Object position) {
        GameSession gameSession = session.getGameSession();
        if (gameSession == null) return false;
        
        Team team = session.getTeam();
        if (team == null) return false;
        
        // Check if it's in team territory
        // TODO: Implement territory/plot system
        
        return true;
    }
    
    /**
     * Handle player interactions (right-click on blocks/entities)
     */
    private void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        
        if (player == null) return;
        
        PlayerSession session = plugin.getGameManager().getPlayerSession(player.getRef());
        
        if (session == null || session.getGameSession() == null) {
            return;
        }
        
        // Check for building interactions
        // TODO: Implement building menu/upgrade system
        
        // Check for villager/NPC interactions
        // TODO: Implement NPC interaction dialogs
    }
}
