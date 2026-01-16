package com.ageofempires.commands;

import com.ageofempires.AgeOfEmpiresPlugin;
import com.hypixel.hytale.server.core.command.system.AbstractCommand;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.CommandSender;
import com.hypixel.hytale.server.core.Message;

import javax.annotation.Nonnull;
import java.util.concurrent.CompletableFuture;

/**
 * /aoehelp - Show help for Age of Empires commands
 */
public class HelpCommand extends AbstractCommand {

    private final AgeOfEmpiresPlugin plugin;
    
    public HelpCommand(AgeOfEmpiresPlugin plugin) {
        super("aoehelp", "Show Age of Empires help");
        this.plugin = plugin;
        
        withAliases("aoe-help", "ageofempires");
    }
    
    @Override
    protected String generatePermissionNode() {
        return "aoe.command.help";
    }
    
    @Override
    protected CompletableFuture<Void> execute(@Nonnull CommandContext context) {
        CommandSender sender = context.sender();
        
        sender.sendMessage(Message.raw("§9======================================"));
        sender.sendMessage(Message.raw("§b  Age of Empires - Help"));
        sender.sendMessage(Message.raw("§9======================================"));
        sender.sendMessage(Message.raw(""));
        sender.sendMessage(Message.raw("§6Player Commands:"));
        sender.sendMessage(Message.raw("§f  /join§7 - Join a game"));
        sender.sendMessage(Message.raw("§f  /leave§7 - Leave the current game"));
        sender.sendMessage(Message.raw("§f  /kit [name]§7 - View/select kits"));
        sender.sendMessage(Message.raw("§f  /stuck§7 - Teleport to spawn (cooldown)"));
        sender.sendMessage(Message.raw("§f  /vote <option>§7 - Vote for game options"));
        sender.sendMessage(Message.raw(""));
        sender.sendMessage(Message.raw("§6Information Commands:"));
        sender.sendMessage(Message.raw("§f  /aoe info§7 - Show plugin info"));
        sender.sendMessage(Message.raw("§f  /aoe stats§7 - Show statistics"));
        sender.sendMessage(Message.raw("§f  /aoehelp§7 - Show this help"));
        sender.sendMessage(Message.raw(""));
        
        // Show admin commands if player has permission
        if (sender.hasPermission("aoe.admin")) {
            sender.sendMessage(Message.raw("§cAdmin Commands:"));
            sender.sendMessage(Message.raw("§f  /aoe setup§7 - Arena setup commands"));
            sender.sendMessage(Message.raw("§f  /aoe reload§7 - Reload configuration"));
            sender.sendMessage(Message.raw("§f  /forcestart§7 - Force start a game"));
            sender.sendMessage(Message.raw(""));
        }
        
        sender.sendMessage(Message.raw("§9======================================"));
        sender.sendMessage(Message.raw("§7Tip: Collect resources, build structures,"));
        sender.sendMessage(Message.raw("§7and defeat the enemy team's King to win!"));
        sender.sendMessage(Message.raw("§9======================================"));
        
        return CompletableFuture.completedFuture(null);
    }
}
