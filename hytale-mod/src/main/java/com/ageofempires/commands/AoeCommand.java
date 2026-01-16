package com.ageofempires.commands;

import com.ageofempires.AgeOfEmpiresPlugin;
import javax.annotation.Nonnull;

/**
 * Main /aoe command (with subcommands)
 * 
 * <p>Subcommands:</p>
 * <ul>
 *   <li>/aoe setup - Setup mode commands</li>
 *   <li>/aoe stats - View statistics</li>
 *   <li>/aoe info - Show plugin info</li>
 * </ul>
 */
public class AoeCommand {
    
    private final AgeOfEmpiresPlugin plugin;
    
    public AoeCommand(AgeOfEmpiresPlugin plugin) {
        this.plugin = plugin;
    }
    
    // TODO: Implement command execution using Hytale's AbstractCommand
    // This is a placeholder - actual implementation requires Hytale Command API
}
