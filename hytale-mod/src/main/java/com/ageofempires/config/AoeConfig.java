package com.ageofempires.config;

import com.hypixel.hytale.codec.Codec;
import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;

/**
 * Main configuration for Age of Empires plugin
 * Loaded from JSON via Hytale's Config system
 */
public class AoeConfig {
    
    public static final BuilderCodec<AoeConfig> CODEC = 
        ((BuilderCodec.Builder) BuilderCodec.builder(AoeConfig.class, AoeConfig::new)
            // Game settings
            .append(new KeyedCodec<>("MinPlayersPerTeam", Codec.INTEGER),
                (c, v) -> c.minPlayersPerTeam = v, c -> c.minPlayersPerTeam)
            .add())
            .append(new KeyedCodec<>("MaxPlayersPerTeam", Codec.INTEGER),
                (c, v) -> c.maxPlayersPerTeam = v, c -> c.maxPlayersPerTeam)
            .add()
            // Countdowns
            .append(new KeyedCodec<>("LobbyCountdown", Codec.INTEGER),
                (c, v) -> c.lobbyCountdown = v, c -> c.lobbyCountdown)
            .add()
            .append(new KeyedCodec<>("PregameCountdown", Codec.INTEGER),
                (c, v) -> c.pregameCountdown = v, c -> c.pregameCountdown)
            .add()
            .append(new KeyedCodec<>("PvpTime", Codec.INTEGER),
                (c, v) -> c.pvpTime = v, c -> c.pvpTime)
            .add()
            .append(new KeyedCodec<>("RestartCountdown", Codec.INTEGER),
                (c, v) -> c.restartCountdown = v, c -> c.restartCountdown)
            .add()
            .append(new KeyedCodec<>("RespawnDelay", Codec.INTEGER),
                (c, v) -> c.respawnDelay = v, c -> c.respawnDelay)
            .add()
            // Resources
            .append(new KeyedCodec<>("StartingWood", Codec.INTEGER),
                (c, v) -> c.startingWood = v, c -> c.startingWood)
            .add()
            .append(new KeyedCodec<>("StartingStone", Codec.INTEGER),
                (c, v) -> c.startingStone = v, c -> c.startingStone)
            .add()
            .append(new KeyedCodec<>("StartingGold", Codec.INTEGER),
                (c, v) -> c.startingGold = v, c -> c.startingGold)
            .add()
            // Buildings
            .append(new KeyedCodec<>("KingHealth", Codec.INTEGER),
                (c, v) -> c.kingHealth = v, c -> c.kingHealth)
            .add()
            // Setup mode
            .append(new KeyedCodec<>("SetupMode", Codec.BOOLEAN),
                (c, v) -> c.setupMode = v, c -> c.setupMode)
            .add()
            .build();
    
    // Game settings
    private int minPlayersPerTeam = 1;
    private int maxPlayersPerTeam = 4;
    
    // Countdowns (seconds)
    private int lobbyCountdown = 30;
    private int pregameCountdown = 10;
    private int pvpTime = 600;
    private int restartCountdown = 15;
    private int respawnDelay = 5;
    
    // Resources
    private int startingWood = 100;
    private int startingStone = 100;
    private int startingGold = 50;
    
    // Buildings
    private int kingHealth = 500;
    
    // Setup
    private boolean setupMode = false;
    
    // Getters
    public int getMinPlayersPerTeam() { return minPlayersPerTeam; }
    public int getMaxPlayersPerTeam() { return maxPlayersPerTeam; }
    public int getLobbyCountdown() { return lobbyCountdown; }
    public int getPregameCountdown() { return pregameCountdown; }
    public int getPvpTime() { return pvpTime; }
    public int getRestartCountdown() { return restartCountdown; }
    public int getRespawnDelay() { return respawnDelay; }
    public int getStartingWood() { return startingWood; }
    public int getStartingStone() { return startingStone; }
    public int getStartingGold() { return startingGold; }
    public int getKingHealth() { return kingHealth; }
    public boolean isSetupMode() { return setupMode; }
}
