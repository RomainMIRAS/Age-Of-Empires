package com.ageofempires.commands.game;

import com.ageofempires.AgeOfEmpiresPlugin;
import com.ageofempires.core.game.GameManager;

/**
 * /forcestart command - Force start the game (admin only)
 */
public class ForceStartCommand {
    
    private final AgeOfEmpiresPlugin plugin;
    private final GameManager gameManager;
    
    public ForceStartCommand(AgeOfEmpiresPlugin plugin, GameManager gameManager) {
        this.plugin = plugin;
        this.gameManager = gameManager;
    }
    
    // TODO: Implement command execution
}
