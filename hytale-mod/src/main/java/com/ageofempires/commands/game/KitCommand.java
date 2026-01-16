package com.ageofempires.commands.game;

import com.ageofempires.AgeOfEmpiresPlugin;
import com.ageofempires.core.game.GameManager;

/**
 * /kit command - Select a kit
 */
public class KitCommand {
    
    private final AgeOfEmpiresPlugin plugin;
    private final GameManager gameManager;
    
    public KitCommand(AgeOfEmpiresPlugin plugin, GameManager gameManager) {
        this.plugin = plugin;
        this.gameManager = gameManager;
    }
    
    // TODO: Implement command execution
}
