package com.ageofempires.commands;

import com.ageofempires.AgeOfEmpiresPlugin;
import com.ageofempires.data.PlayerSession;
import com.hypixel.hytale.server.core.command.system.AbstractCommand;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.CommandSender;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.Message;

import javax.annotation.Nonnull;
import java.util.concurrent.CompletableFuture;

/**
 * /stuck - Teleport to spawn when stuck
 */
public class StuckCommand extends AbstractCommand {

    private final AgeOfEmpiresPlugin plugin;
    
    // Cooldown in milliseconds (30 seconds)
    private static final long COOLDOWN_MS = 30000;
    
    public StuckCommand(AgeOfEmpiresPlugin plugin) {
        super("stuck", "Teleport to spawn when stuck (30s cooldown)");
        this.plugin = plugin;
        
        withAliases("spawn", "unstuck");
    }
    
    @Override
    protected String generatePermissionNode() {
        return "aoe.command.stuck";
    }
    
    @Override
    protected CompletableFuture<Void> execute(@Nonnull CommandContext context) {
        CommandSender sender = context.sender();
        
        if (!(sender instanceof Player)) {
            sender.sendMessage(Message.raw("§cThis command can only be used by players!"));
            return CompletableFuture.completedFuture(null);
        }
        
        Player player = (Player) sender;
        PlayerSession session = plugin.getGameManager().getPlayerSession(player.getRef());
        
        if (session == null || session.getGameSession() == null) {
            sender.sendMessage(Message.raw("§cYou are not in a game!"));
            return CompletableFuture.completedFuture(null);
        }
        
        GameSession gameSession = session.getGameSession();
        
        if (!gameSession.isRunning()) {
            sender.sendMessage(Message.raw("§cThe game has not started yet!"));
            return CompletableFuture.completedFuture(null);
        }
        
        // Check cooldown
        long lastUsed = session.getLastStuckCommandTime();
        long now = System.currentTimeMillis();
        long remaining = (lastUsed + COOLDOWN_MS) - now;
        
        if (remaining > 0) {
            long secondsRemaining = remaining / 1000;
            sender.sendMessage(Message.raw("§cYou must wait §f" + secondsRemaining + "§c seconds before using this again!"));
            return CompletableFuture.completedFuture(null);
        }
        
        // Teleport to team spawn
        if (session.getTeam() != null) {
            // TODO: Get team spawn location and teleport
            sender.sendMessage(Message.raw("§aTeleporting to spawn..."));
            session.setLastStuckCommandTime(now);
            
            // Actual teleport would happen here
            // TransformComponent transform = player.getComponent(TransformComponent.class);
            // transform.setPosition(gameSession.getTeamSpawn(session.getTeam()));
        } else {
            sender.sendMessage(Message.raw("§cYou are not on a team!"));
        }
        
        return CompletableFuture.completedFuture(null);
    }
}
