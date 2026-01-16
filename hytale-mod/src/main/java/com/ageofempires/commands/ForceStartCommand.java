package com.ageofempires.commands;

import com.ageofempires.AgeOfEmpiresPlugin;
import com.ageofempires.core.game.GameSession;
import com.ageofempires.data.PlayerSession;
import com.hypixel.hytale.server.core.command.system.AbstractCommand;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.CommandSender;
import com.hypixel.hytale.server.core.command.system.arguments.system.RequiredArg;
import com.hypixel.hytale.server.core.command.system.arguments.types.ArgTypes;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.Message;

import javax.annotation.Nonnull;
import java.util.concurrent.CompletableFuture;

/**
 * /forcestart - Force start the game (admin)
 */
public class ForceStartCommand extends AbstractCommand {

    private final AgeOfEmpiresPlugin plugin;
    
    public ForceStartCommand(AgeOfEmpiresPlugin plugin) {
        super("forcestart", "Force start a game (admin)");
        this.plugin = plugin;
        
        // Require admin permission
        requirePermission("aoe.admin.forcestart");
        
        withAliases("aoe-start", "startgame");
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
            sender.sendMessage(Message.raw("§cYou are not in a game lobby!"));
            return CompletableFuture.completedFuture(null);
        }
        
        GameSession gameSession = session.getGameSession();
        
        if (gameSession.isRunning()) {
            sender.sendMessage(Message.raw("§cThe game has already started!"));
            return CompletableFuture.completedFuture(null);
        }
        
        int playerCount = gameSession.getPlayerCount();
        int minPlayers = plugin.getConfig().getMinPlayersPerTeam();
        
        if (playerCount < 2) {
            sender.sendMessage(Message.raw("§cNeed at least 2 players to start!"));
            return CompletableFuture.completedFuture(null);
        }
        
        sender.sendMessage(Message.raw("§aForce starting the game..."));
        gameSession.startCountdown(10); // 10 second countdown
        
        return CompletableFuture.completedFuture(null);
    }
}
