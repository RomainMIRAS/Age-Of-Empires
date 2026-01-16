package com.ageofempires.events;

import com.ageofempires.AgeOfEmpiresPlugin;
import com.ageofempires.data.PlayerSession;
import com.ageofempires.core.team.Team;
import com.hypixel.hytale.server.core.entity.PlayerRef;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.events.EventRegistry;
import com.hypixel.hytale.server.core.events.player.PlayerCombatEvent;
import com.hypixel.hytale.server.core.events.entity.EntityDamageEvent;
import com.hypixel.hytale.server.core.events.entity.EntityDeathEvent;
import com.hypixel.hytale.server.core.Message;

import java.util.logging.Logger;

/**
 * Handles all combat-related events for Age of Empires
 */
public class PlayerCombatHandler {

    private final AgeOfEmpiresPlugin plugin;
    private final Logger logger;
    
    public PlayerCombatHandler(AgeOfEmpiresPlugin plugin) {
        this.plugin = plugin;
        this.logger = plugin.getLogger();
    }
    
    /**
     * Register all combat event handlers
     */
    public void register(EventRegistry eventRegistry) {
        // Player vs Player damage
        eventRegistry.register(PlayerCombatEvent.class, this::onPlayerCombat);
        
        // Entity damage (for NPCs like King)
        eventRegistry.register(EntityDamageEvent.class, this::onEntityDamage);
        
        // Death events
        eventRegistry.register(EntityDeathEvent.class, this::onEntityDeath);
        
        logger.info("Combat event handlers registered");
    }
    
    /**
     * Handle player vs player combat
     */
    private void onPlayerCombat(PlayerCombatEvent event) {
        Player attacker = event.getAttacker();
        Player victim = event.getVictim();
        
        if (attacker == null || victim == null) return;
        
        PlayerSession attackerSession = plugin.getGameManager().getPlayerSession(attacker.getRef());
        PlayerSession victimSession = plugin.getGameManager().getPlayerSession(victim.getRef());
        
        // Check if both players are in the same game
        if (attackerSession == null || victimSession == null) {
            return;
        }
        
        GameSession attackerGame = attackerSession.getGameSession();
        GameSession victimGame = victimSession.getGameSession();
        
        if (attackerGame == null || victimGame == null || attackerGame != victimGame) {
            return;
        }
        
        // Check if game is running
        if (!attackerGame.isRunning()) {
            event.setCancelled(true);
            return;
        }
        
        // Check friendly fire (same team)
        Team attackerTeam = attackerSession.getTeam();
        Team victimTeam = victimSession.getTeam();
        
        if (attackerTeam != null && attackerTeam == victimTeam) {
            // Friendly fire is disabled
            event.setCancelled(true);
            attacker.sendMessage(Message.raw("§cYou cannot attack teammates!"));
            return;
        }
        
        // Apply kit damage multiplier
        if (attackerSession.getSelectedKit() != null) {
            double multiplier = attackerSession.getSelectedKit().getAbilities().getDamageMultiplier();
            double currentDamage = event.getDamage();
            event.setDamage(currentDamage * multiplier);
        }
        
        // Track damage for stats
        attackerSession.addDamageDealt(event.getDamage());
        victimSession.addDamageTaken(event.getDamage());
    }
    
    /**
     * Handle entity damage (for NPCs like villagers, king, etc.)
     */
    private void onEntityDamage(EntityDamageEvent event) {
        // Check if it's a game NPC
        String entityType = event.getEntity().getEntityType();
        
        // TODO: Implement NPC damage handling
        // - King damage tracking
        // - Villager protection in certain scenarios
    }
    
    /**
     * Handle entity death events
     */
    private void onEntityDeath(EntityDeathEvent event) {
        // Check if a player died
        if (event.getEntity() instanceof Player) {
            handlePlayerDeath((Player) event.getEntity(), event);
        }
        
        // Check if King died (game over condition)
        String entityType = event.getEntity().getEntityType();
        if (entityType.equals("king") || entityType.equals("village_king")) {
            handleKingDeath(event);
        }
    }
    
    /**
     * Handle player death
     */
    private void handlePlayerDeath(Player player, EntityDeathEvent event) {
        PlayerSession session = plugin.getGameManager().getPlayerSession(player.getRef());
        
        if (session == null || session.getGameSession() == null) {
            return;
        }
        
        GameSession gameSession = session.getGameSession();
        
        // Increment death counter
        session.incrementDeaths();
        
        // Check killer for kill attribution
        if (event.getKiller() != null && event.getKiller() instanceof Player) {
            Player killer = (Player) event.getKiller();
            PlayerSession killerSession = plugin.getGameManager().getPlayerSession(killer.getRef());
            
            if (killerSession != null) {
                killerSession.incrementKills();
                
                // Broadcast kill message
                gameSession.broadcast(Message.raw(
                    killer.getName() + " §7killed §c" + player.getName()
                ));
            }
        }
        
        // Schedule respawn
        scheduleRespawn(player, session, gameSession);
    }
    
    /**
     * Handle King death (end game condition)
     */
    private void handleKingDeath(EntityDeathEvent event) {
        // Find which team's king died
        // TODO: Implement king-team association
        
        // For now, use entity metadata or position to determine team
        logger.info("A King has been killed! Checking for game end condition...");
        
        // TODO: End game with winning team announcement
    }
    
    /**
     * Schedule player respawn after death
     */
    private void scheduleRespawn(Player player, PlayerSession session, GameSession gameSession) {
        int respawnDelay = plugin.getConfig().getRespawnDelay();
        
        // Send death message
        player.sendMessage(Message.raw("§cYou died! Respawning in " + respawnDelay + " seconds..."));
        
        // Schedule respawn task
        plugin.getScheduler().runDelayed(() -> {
            if (session.getGameSession() == gameSession && gameSession.isRunning()) {
                // Respawn at team spawn
                Team team = session.getTeam();
                if (team != null) {
                    // TODO: Get team spawn position and teleport
                    // TransformComponent transform = player.getComponent(TransformComponent.class);
                    // transform.setPosition(gameSession.getTeamSpawn(team));
                    
                    // Re-apply kit
                    if (session.getSelectedKit() != null) {
                        plugin.getKitManager().applyKit(player, session.getSelectedKit());
                    }
                    
                    player.sendMessage(Message.raw("§aYou have respawned!"));
                }
            }
        }, respawnDelay * 20); // Convert seconds to ticks
    }
}
