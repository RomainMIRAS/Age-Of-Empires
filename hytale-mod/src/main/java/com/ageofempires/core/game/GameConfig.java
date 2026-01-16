package com.ageofempires.core.game;

/**
 * Configuration for a game session
 * 
 * <p>Defines game rules, timers, and limits</p>
 */
public class GameConfig {
    
    // Player limits
    private int minPlayers = 2;
    private int maxPlayers = 16;
    private int maxPlayersPerTeam = 4;
    
    // Countdowns (in seconds)
    private int lobbyCountdown = 40;
    private int pregameCountdown = 10;
    private int pvpTime = 600; // 10 minutes
    private int assaultTime = 600; // 10 minutes
    private int restartCountdown = 15;
    
    // Resource settings
    private double startingWood = 100.0;
    private double startingStone = 100.0;
    private double startingGold = 0.0;
    
    // Building settings
    private int forumHealth = 1400;
    private int buildingHealth = 500;
    
    // Getters
    public int getMinPlayers() { return minPlayers; }
    public int getMaxPlayers() { return maxPlayers; }
    public int getMaxPlayersPerTeam() { return maxPlayersPerTeam; }
    public int getLobbyCountdown() { return lobbyCountdown; }
    public int getPregameCountdown() { return pregameCountdown; }
    public int getPvpTime() { return pvpTime; }
    public int getAssaultTime() { return assaultTime; }
    public int getRestartCountdown() { return restartCountdown; }
    public double getStartingWood() { return startingWood; }
    public double getStartingStone() { return startingStone; }
    public double getStartingGold() { return startingGold; }
    public int getForumHealth() { return forumHealth; }
    public int getBuildingHealth() { return buildingHealth; }
    
    // Setters (for configuration loading)
    public void setMinPlayers(int minPlayers) { this.minPlayers = minPlayers; }
    public void setMaxPlayers(int maxPlayers) { this.maxPlayers = maxPlayers; }
    public void setMaxPlayersPerTeam(int maxPlayersPerTeam) { this.maxPlayersPerTeam = maxPlayersPerTeam; }
    public void setLobbyCountdown(int lobbyCountdown) { this.lobbyCountdown = lobbyCountdown; }
    public void setPregameCountdown(int pregameCountdown) { this.pregameCountdown = pregameCountdown; }
    public void setPvpTime(int pvpTime) { this.pvpTime = pvpTime; }
    public void setAssaultTime(int assaultTime) { this.assaultTime = assaultTime; }
    public void setRestartCountdown(int restartCountdown) { this.restartCountdown = restartCountdown; }
    public void setStartingWood(double startingWood) { this.startingWood = startingWood; }
    public void setStartingStone(double startingStone) { this.startingStone = startingStone; }
    public void setStartingGold(double startingGold) { this.startingGold = startingGold; }
    public void setForumHealth(int forumHealth) { this.forumHealth = forumHealth; }
    public void setBuildingHealth(int buildingHealth) { this.buildingHealth = buildingHealth; }
}
