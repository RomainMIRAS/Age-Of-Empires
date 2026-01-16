package com.ageofempires.commands.game;

import com.ageofempires.AgeOfEmpiresPlugin;
import com.ageofempires.core.game.GameManager;

/**
 * /stuck command - Teleport to spawn if stuck
 */
public class StuckCommand {
    
    private final AgeOfEmpiresPlugin plugin;
    private final GameManager gameManager;
    
    public StuckCommand(AgeOfEmpiresPlugin plugin, GameManager gameManager) {
        this.plugin = plugin;
        this.gameManager = gameManager;
    }
    
    // TODO: Implement command execution
}
