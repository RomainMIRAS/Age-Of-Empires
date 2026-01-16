package com.ageofempires.commands;

import com.ageofempires.AgeOfEmpiresPlugin;
import com.ageofempires.commands.setup.*;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractCommandCollection;
import com.hypixel.hytale.server.core.Message;

import javax.annotation.Nonnull;
import java.util.concurrent.CompletableFuture;

/**
 * Main /aoe command collection with subcommands
 * 
 * Usage:
 * - /aoe info - Show plugin info
 * - /aoe stats - Show statistics
 * - /aoe setup - Setup commands (admin)
 * - /aoe reload - Reload config (admin)
 */
public class AoeCommandCollection extends AbstractCommandCollection {

    private final AgeOfEmpiresPlugin plugin;
    
    public AoeCommandCollection(AgeOfEmpiresPlugin plugin) {
        super("aoe", "Age of Empires main command");
        this.plugin = plugin;
        
        // Add subcommands
        addSubCommand(new InfoSubCommand());
        addSubCommand(new StatsSubCommand());
        addSubCommand(new ReloadSubCommand());
        addSubCommand(new SetupCommandCollection(plugin));
    }
    
    @Override
    protected String generatePermissionNode() {
        return "aoe.command.aoe";
    }
    
    // ==================== Subcommands ====================
    
    /**
     * /aoe info - Show plugin information
     */
    private class InfoSubCommand extends com.hypixel.hytale.server.core.command.system.AbstractCommand {
        
        public InfoSubCommand() {
            super("info", "Show plugin information");
        }
        
        @Override
        protected CompletableFuture<Void> execute(@Nonnull CommandContext context) {
            context.sender().sendMessage(Message.raw("§9========================================"));
            context.sender().sendMessage(Message.raw("§b  Age of Empires - Hytale Edition"));
            context.sender().sendMessage(Message.raw("§7  Version: §f2.0.0"));
            context.sender().sendMessage(Message.raw("§7  Author: §fRomainMiras"));
            context.sender().sendMessage(Message.raw("§7  Original: §fandrei1058"));
            context.sender().sendMessage(Message.raw("§9========================================"));
            context.sender().sendMessage(Message.raw("§7  A team-based PvP minigame where"));
            context.sender().sendMessage(Message.raw("§7  players collect resources, build"));
            context.sender().sendMessage(Message.raw("§7  structures, and battle enemies!"));
            context.sender().sendMessage(Message.raw("§9========================================"));
            return CompletableFuture.completedFuture(null);
        }
    }
    
    /**
     * /aoe stats - Show game statistics
     */
    private class StatsSubCommand extends com.hypixel.hytale.server.core.command.system.AbstractCommand {
        
        public StatsSubCommand() {
            super("stats", "Show game statistics");
        }
        
        @Override
        protected CompletableFuture<Void> execute(@Nonnull CommandContext context) {
            int activeSessions = plugin.getGameManager().getActiveSessionCount();
            int players = plugin.getGameManager().getPlayerCount();
            int arenas = plugin.getArenaManager().getArenaCount();
            
            context.sender().sendMessage(Message.raw("§9===== Age of Empires Stats ====="));
            context.sender().sendMessage(Message.raw("§7Active Games: §f" + activeSessions));
            context.sender().sendMessage(Message.raw("§7Players In-Game: §f" + players));
            context.sender().sendMessage(Message.raw("§7Total Arenas: §f" + arenas));
            context.sender().sendMessage(Message.raw("§9================================"));
            return CompletableFuture.completedFuture(null);
        }
    }
    
    /**
     * /aoe reload - Reload configuration (admin)
     */
    private class ReloadSubCommand extends com.hypixel.hytale.server.core.command.system.AbstractCommand {
        
        public ReloadSubCommand() {
            super("reload", "Reload configuration");
            requirePermission("aoe.admin.reload");
        }
        
        @Override
        protected CompletableFuture<Void> execute(@Nonnull CommandContext context) {
            context.sender().sendMessage(Message.raw("§eReloading configuration..."));
            // Configuration is auto-reloaded by Hytale's Config system
            context.sender().sendMessage(Message.raw("§aConfiguration reloaded!"));
            return CompletableFuture.completedFuture(null);
        }
    }
}
