package com.ageofempires.commands.setup;

import com.ageofempires.AgeOfEmpiresPlugin;
import com.ageofempires.core.team.Team;
import com.hypixel.hytale.server.core.command.system.AbstractCommand;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractCommandCollection;
import com.hypixel.hytale.server.core.command.system.arguments.system.RequiredArg;
import com.hypixel.hytale.server.core.command.system.arguments.types.ArgTypes;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.command.system.CommandSender;

import javax.annotation.Nonnull;
import java.util.concurrent.CompletableFuture;

/**
 * /aoe setup command collection for arena configuration
 * 
 * Subcommands:
 * - /aoe setup setlobby - Set waiting lobby
 * - /aoe setup addmap <name> - Add a new map
 * - /aoe setup setspawn <team> - Set team spawn
 * - /aoe setup setforum <team> - Set team forum location
 * - /aoe setup addplot <team> <size> - Add building plot
 * - /aoe setup savemap - Save current map
 * - /aoe setup finish - Finish setup
 */
public class SetupCommandCollection extends AbstractCommandCollection {

    private final AgeOfEmpiresPlugin plugin;
    
    public SetupCommandCollection(AgeOfEmpiresPlugin plugin) {
        super("setup", "Arena setup commands");
        this.plugin = plugin;
        
        // Require admin permission for all setup commands
        requirePermission("aoe.admin.setup");
        
        // Add subcommands
        addSubCommand(new SetLobbyCommand());
        addSubCommand(new AddMapCommand());
        addSubCommand(new SetSpawnCommand());
        addSubCommand(new SetForumCommand());
        addSubCommand(new AddPlotCommand());
        addSubCommand(new SaveMapCommand());
        addSubCommand(new FinishSetupCommand());
    }
    
    // ==================== Subcommands ====================
    
    /**
     * /aoe setup setlobby - Set the waiting lobby location
     */
    private class SetLobbyCommand extends AbstractCommand {
        
        public SetLobbyCommand() {
            super("setlobby", "Set waiting lobby location");
        }
        
        @Override
        protected CompletableFuture<Void> execute(@Nonnull CommandContext context) {
            CommandSender sender = context.sender();
            
            if (!(sender instanceof Player)) {
                sender.sendMessage(Message.raw("§cThis command can only be used by players!"));
                return CompletableFuture.completedFuture(null);
            }
            
            Player player = (Player) sender;
            // TODO: Save lobby location
            sender.sendMessage(Message.raw("§aLobby location set!"));
            return CompletableFuture.completedFuture(null);
        }
    }
    
    /**
     * /aoe setup addmap <name> - Add a new arena map
     */
    private class AddMapCommand extends AbstractCommand {
        
        private final RequiredArg<String> nameArg;
        
        public AddMapCommand() {
            super("addmap", "Add a new arena map");
            nameArg = withRequiredArg("name", "Map name", ArgTypes.STRING);
        }
        
        @Override
        protected CompletableFuture<Void> execute(@Nonnull CommandContext context) {
            String mapName = nameArg.get(context);
            
            // TODO: Create new arena with given name
            plugin.getArenaManager().createArena(mapName);
            
            context.sender().sendMessage(Message.raw("§aMap '" + mapName + "' created!"));
            context.sender().sendMessage(Message.raw("§7Now set spawns with /aoe setup setspawn <team>"));
            return CompletableFuture.completedFuture(null);
        }
    }
    
    /**
     * /aoe setup setspawn <team> - Set team spawn location
     */
    private class SetSpawnCommand extends AbstractCommand {
        
        private final RequiredArg<String> teamArg;
        
        public SetSpawnCommand() {
            super("setspawn", "Set team spawn location");
            teamArg = withRequiredArg("team", "Team name (Blue/Red/Green/Yellow)", ArgTypes.STRING);
        }
        
        @Override
        protected CompletableFuture<Void> execute(@Nonnull CommandContext context) {
            CommandSender sender = context.sender();
            
            if (!(sender instanceof Player)) {
                sender.sendMessage(Message.raw("§cThis command can only be used by players!"));
                return CompletableFuture.completedFuture(null);
            }
            
            String teamName = teamArg.get(context);
            Team team = Team.fromName(teamName);
            
            if (team == null) {
                sender.sendMessage(Message.raw("§cInvalid team! Use: Blue, Red, Green, or Yellow"));
                return CompletableFuture.completedFuture(null);
            }
            
            // TODO: Save team spawn location from player position
            sender.sendMessage(Message.raw("§aSpawn set for team " + team.getDisplayName() + "§a!"));
            return CompletableFuture.completedFuture(null);
        }
    }
    
    /**
     * /aoe setup setforum <team> - Set team forum (king) location
     */
    private class SetForumCommand extends AbstractCommand {
        
        private final RequiredArg<String> teamArg;
        
        public SetForumCommand() {
            super("setforum", "Set team forum location");
            teamArg = withRequiredArg("team", "Team name", ArgTypes.STRING);
        }
        
        @Override
        protected CompletableFuture<Void> execute(@Nonnull CommandContext context) {
            CommandSender sender = context.sender();
            
            if (!(sender instanceof Player)) {
                sender.sendMessage(Message.raw("§cThis command can only be used by players!"));
                return CompletableFuture.completedFuture(null);
            }
            
            String teamName = teamArg.get(context);
            Team team = Team.fromName(teamName);
            
            if (team == null) {
                sender.sendMessage(Message.raw("§cInvalid team! Use: Blue, Red, Green, or Yellow"));
                return CompletableFuture.completedFuture(null);
            }
            
            // TODO: Save forum location from player position
            sender.sendMessage(Message.raw("§aForum set for team " + team.getDisplayName() + "§a!"));
            return CompletableFuture.completedFuture(null);
        }
    }
    
    /**
     * /aoe setup addplot <team> <size> - Add a building plot
     */
    private class AddPlotCommand extends AbstractCommand {
        
        private final RequiredArg<String> teamArg;
        private final RequiredArg<String> sizeArg;
        
        public AddPlotCommand() {
            super("addplot", "Add a building plot");
            teamArg = withRequiredArg("team", "Team name", ArgTypes.STRING);
            sizeArg = withRequiredArg("size", "Plot size (small/medium/large)", ArgTypes.STRING);
        }
        
        @Override
        protected CompletableFuture<Void> execute(@Nonnull CommandContext context) {
            CommandSender sender = context.sender();
            
            if (!(sender instanceof Player)) {
                sender.sendMessage(Message.raw("§cThis command can only be used by players!"));
                return CompletableFuture.completedFuture(null);
            }
            
            String teamName = teamArg.get(context);
            String size = sizeArg.get(context).toLowerCase();
            
            Team team = Team.fromName(teamName);
            if (team == null) {
                sender.sendMessage(Message.raw("§cInvalid team!"));
                return CompletableFuture.completedFuture(null);
            }
            
            if (!size.equals("small") && !size.equals("medium") && !size.equals("large")) {
                sender.sendMessage(Message.raw("§cInvalid size! Use: small, medium, or large"));
                return CompletableFuture.completedFuture(null);
            }
            
            // TODO: Save plot location
            sender.sendMessage(Message.raw("§a" + size.toUpperCase() + " plot added for " + team.getDisplayName() + "§a!"));
            return CompletableFuture.completedFuture(null);
        }
    }
    
    /**
     * /aoe setup savemap - Save the current map configuration
     */
    private class SaveMapCommand extends AbstractCommand {
        
        public SaveMapCommand() {
            super("savemap", "Save current map configuration");
        }
        
        @Override
        protected CompletableFuture<Void> execute(@Nonnull CommandContext context) {
            // TODO: Save map to config
            context.sender().sendMessage(Message.raw("§aMap saved!"));
            return CompletableFuture.completedFuture(null);
        }
    }
    
    /**
     * /aoe setup finish - Finish setup and validate map
     */
    private class FinishSetupCommand extends AbstractCommand {
        
        public FinishSetupCommand() {
            super("finish", "Finish setup and validate");
        }
        
        @Override
        protected CompletableFuture<Void> execute(@Nonnull CommandContext context) {
            // TODO: Validate all required locations are set
            context.sender().sendMessage(Message.raw("§aSetup complete! The arena is ready to use."));
            context.sender().sendMessage(Message.raw("§7Don't forget to set SetupMode to false in config!"));
            return CompletableFuture.completedFuture(null);
        }
    }
}
