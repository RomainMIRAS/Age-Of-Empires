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
 * /leave - Leave the current game session
 */
public class LeaveCommand extends AbstractCommand {

    private final AgeOfEmpiresPlugin plugin;
    
    public LeaveCommand(AgeOfEmpiresPlugin plugin) {
        super("leave", "Leave the current Age of Empires game");
        this.plugin = plugin;
        
        // Add aliases
        withAliases("quit", "exit", "aoe-leave");
    }
    
    @Override
    protected String generatePermissionNode() {
        return "aoe.command.leave";
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
        
        if (session == null) {
            sender.sendMessage(Message.raw("§cYou are not in a game!"));
            return CompletableFuture.completedFuture(null);
        }
        
        GameSession gameSession = session.getGameSession();
        if (gameSession == null) {
            sender.sendMessage(Message.raw("§cYou are not in a game!"));
            return CompletableFuture.completedFuture(null);
        }
        
        // Remove player from game
        gameSession.removePlayer(player.getRef());
        plugin.getGameManager().removePlayerSession(player.getRef());
        
        sender.sendMessage(Message.raw("§aYou have left the game!"));
        
        // Teleport to lobby if configured
        // TODO: Teleport player to lobby
        
        return CompletableFuture.completedFuture(null);
    }
}
