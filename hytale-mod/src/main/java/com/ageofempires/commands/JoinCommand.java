package com.ageofempires.commands;

import com.ageofempires.AgeOfEmpiresPlugin;
import com.ageofempires.arena.Arena;
import com.ageofempires.core.game.GameSession;
import com.hypixel.hytale.server.core.command.system.AbstractCommand;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.CommandSender;
import com.hypixel.hytale.server.core.command.system.arguments.system.OptionalArg;
import com.hypixel.hytale.server.core.command.system.arguments.types.ArgTypes;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.Message;

import javax.annotation.Nonnull;
import java.util.concurrent.CompletableFuture;

/**
 * /join [arenaName] - Join a game
 */
public class JoinCommand extends AbstractCommand {

    private final AgeOfEmpiresPlugin plugin;
    private final OptionalArg<String> arenaArg;
    
    public JoinCommand(AgeOfEmpiresPlugin plugin) {
        super("join", "Join an Age of Empires game");
        this.plugin = plugin;
        
        arenaArg = withOptionalArg("arena", "Arena name to join (optional)", ArgTypes.STRING);
        
        withAliases("play", "aoe-join");
    }
    
    @Override
    protected String generatePermissionNode() {
        return "aoe.command.join";
    }
    
    @Override
    protected CompletableFuture<Void> execute(@Nonnull CommandContext context) {
        CommandSender sender = context.sender();
        
        if (!(sender instanceof Player)) {
            sender.sendMessage(Message.raw("§cThis command can only be used by players!"));
            return CompletableFuture.completedFuture(null);
        }
        
        Player player = (Player) sender;
        
        // Check if already in a game
        if (plugin.getGameManager().getPlayerSession(player.getRef()) != null) {
            sender.sendMessage(Message.raw("§cYou are already in a game! Use /leave first."));
            return CompletableFuture.completedFuture(null);
        }
        
        String arenaName = arenaArg.get(context);
        Arena arena = null;
        
        if (arenaName != null && !arenaName.isEmpty()) {
            // Join specific arena
            arena = plugin.getArenaManager().getArena(arenaName);
            if (arena == null) {
                sender.sendMessage(Message.raw("§cArena '" + arenaName + "' not found!"));
                return CompletableFuture.completedFuture(null);
            }
        } else {
            // Auto-select best arena
            arena = plugin.getArenaManager().getBestAvailableArena();
            if (arena == null) {
                sender.sendMessage(Message.raw("§cNo available arenas! Please try again later."));
                return CompletableFuture.completedFuture(null);
            }
        }
        
        // Get or create game session for arena
        GameSession gameSession = plugin.getGameManager().getOrCreateSession(arena);
        
        if (gameSession.isFull()) {
            sender.sendMessage(Message.raw("§cThis arena is full! Try another one."));
            return CompletableFuture.completedFuture(null);
        }
        
        if (gameSession.isRunning()) {
            sender.sendMessage(Message.raw("§cThis game has already started! Try another one."));
            return CompletableFuture.completedFuture(null);
        }
        
        // Add player to session
        boolean success = gameSession.addPlayer(player.getRef());
        
        if (success) {
            sender.sendMessage(Message.raw("§aYou have joined §f" + arena.getName() + "§a!"));
            sender.sendMessage(Message.raw("§7Players: §f" + gameSession.getPlayerCount() + "/" + gameSession.getMaxPlayers()));
            sender.sendMessage(Message.raw("§7Use §f/kit§7 to select a kit!"));
            
            // Broadcast to other players
            gameSession.broadcast(Message.raw("§e" + player.getName() + "§7 has joined the game! §f(" 
                + gameSession.getPlayerCount() + "/" + gameSession.getMaxPlayers() + ")"));
        } else {
            sender.sendMessage(Message.raw("§cFailed to join the game. Please try again."));
        }
        
        return CompletableFuture.completedFuture(null);
    }
}
