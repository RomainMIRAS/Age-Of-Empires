package com.ageofempires.commands;

import com.ageofempires.AgeOfEmpiresPlugin;
import com.ageofempires.core.kits.Kit;
import com.ageofempires.core.kits.KitManager;
import com.ageofempires.data.PlayerSession;
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
 * /kit [kitName] - Select or view kits
 */
public class KitCommand extends AbstractCommand {

    private final AgeOfEmpiresPlugin plugin;
    private final OptionalArg<String> kitNameArg;
    
    public KitCommand(AgeOfEmpiresPlugin plugin) {
        super("kit", "Select or view available kits");
        this.plugin = plugin;
        
        // Optional kit name argument
        kitNameArg = withOptionalArg("kitName", "Name of the kit to select", ArgTypes.STRING);
        
        withAliases("kits", "class", "aoe-kit");
    }
    
    @Override
    protected String generatePermissionNode() {
        return "aoe.command.kit";
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
        
        // Get kit name if provided
        String kitName = kitNameArg.get(context);
        
        KitManager kitManager = plugin.getKitManager();
        
        if (kitName == null || kitName.isEmpty()) {
            // Show available kits
            showKitList(sender, kitManager, session);
        } else {
            // Select kit
            selectKit(sender, player, session, kitManager, kitName);
        }
        
        return CompletableFuture.completedFuture(null);
    }
    
    private void showKitList(CommandSender sender, KitManager kitManager, PlayerSession session) {
        sender.sendMessage(Message.raw("§9===== Available Kits ====="));
        
        for (Kit kit : kitManager.getAvailableKits()) {
            String status = "";
            if (session != null && kit.equals(session.getSelectedKit())) {
                status = " §a[SELECTED]";
            }
            
            sender.sendMessage(Message.raw(
                "§7- §f" + kit.getName() + status
            ));
            sender.sendMessage(Message.raw(
                "  §7" + kit.getDescription()
            ));
        }
        
        sender.sendMessage(Message.raw("§9=========================="));
        sender.sendMessage(Message.raw("§7Use §f/kit <name>§7 to select a kit"));
    }
    
    private void selectKit(CommandSender sender, Player player, PlayerSession session, 
                           KitManager kitManager, String kitName) {
        Kit kit = kitManager.getKit(kitName);
        
        if (kit == null) {
            sender.sendMessage(Message.raw("§cKit '" + kitName + "' not found!"));
            sender.sendMessage(Message.raw("§7Use §f/kit§7 to see available kits"));
            return;
        }
        
        // Check permission for this kit
        if (kit.requiresPermission() && !player.hasPermission(kit.getPermission())) {
            sender.sendMessage(Message.raw("§cYou don't have permission to use this kit!"));
            return;
        }
        
        // Set kit for session
        if (session != null) {
            session.setSelectedKit(kit);
            sender.sendMessage(Message.raw("§aYou have selected the §f" + kit.getName() + "§a kit!"));
            
            // If in-game, give kit items
            if (session.getGameSession() != null && session.getGameSession().isRunning()) {
                kitManager.applyKit(player, kit);
                sender.sendMessage(Message.raw("§7Kit items have been given!"));
            }
        } else {
            // Store preference for next game
            sender.sendMessage(Message.raw("§aKit §f" + kit.getName() + "§a will be used in your next game!"));
        }
    }
}
