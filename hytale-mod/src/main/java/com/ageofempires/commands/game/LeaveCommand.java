package com.ageofempires.commands.game;

import com.ageofempires.AgeOfEmpiresPlugin;
import com.ageofempires.core.game.GameManager;

/**
 * /leave command - Leave the current game
 */
public class LeaveCommand {
    
    private final AgeOfEmpiresPlugin plugin;
    private final GameManager gameManager;
    
    public LeaveCommand(AgeOfEmpiresPlugin plugin, GameManager gameManager) {
        this.plugin = plugin;
        this.gameManager = gameManager;
    }
    
    // TODO: Implement command execution
}
