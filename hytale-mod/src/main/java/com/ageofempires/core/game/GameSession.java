package com.ageofempires.core.game;

import com.ageofempires.AgeOfEmpiresPlugin;
import com.ageofempires.core.arena.Arena;
import com.ageofempires.core.team.Team;
import com.ageofempires.core.team.TeamManager;
import com.ageofempires.core.team.TeamData;
import com.ageofempires.data.player.PlayerSession;
import com.hypixel.hytale.server.core.universe.PlayerRef;

import java.util.*;
import java.util.logging.Logger;

/**
 * Represents an active game session
 * 
 * <p>A game session encapsulates:</p>
 * <ul>
 *   <li>Current game state</li>
 *   <li>Arena being played on</li>
 *   <li>Teams and their data</li>
 *   <li>Players and their sessions</li>
 *   <li>Game configuration</li>
 *   <li>Timers and countdowns</li>
 * </ul>
 */
public class GameSession {
    
    private final AgeOfEmpiresPlugin plugin;
    private final Logger logger;
    private final Arena arena;
    private final TeamManager teamManager;
    private final GameConfig config;
    
    // Game state
    private GameState state;
    private long startTime;
    private int countdown;
    
    // Teams
    private final Map<Team, TeamData> teams;
    
    // Players
    private final Map<UUID, PlayerSession> players;
    private final Map<UUID, Team> playerTeams;
    
    public GameSession(AgeOfEmpiresPlugin plugin, Arena arena, TeamManager teamManager) {
        this.plugin = plugin;
        this.logger = plugin.getLogger();
        this.arena = arena;
        this.teamManager = teamManager;
        this.config = new GameConfig(); // Will load from config later
        
        this.state = GameState.WAITING;
        this.teams = new EnumMap<>(Team.class);
        this.players = new HashMap<>();
        this.playerTeams = new HashMap<>();
        
        // Initialize teams
        for (Team team : Team.values()) {
            teams.put(team, new TeamData(team));
        }
    }
    
    /**
     * Tick the game session (called every second)
     */
    public void tick() {
        switch (state) {
            case WAITING:
                tickWaiting();
                break;
            case STARTING:
                tickStarting();
                break;
            case PREPARING:
                tickPreparing();
                break;
            case PLAYING:
                tickPlaying();
                break;
            case ENDING:
                tickEnding();
                break;
        }
    }
    
    private void tickWaiting() {
        // Check if we have enough players to start
        if (players.size() >= config.getMinPlayers()) {
            startCountdown();
        }
    }
    
    private void tickStarting() {
        countdown--;
        
        // Broadcast countdown
        if (countdown == 10 || countdown == 5 || countdown <= 3) {
            broadcastMessage("§eGame starting in §6" + countdown + "§e seconds!");
        }
        
        if (countdown <= 0) {
            startPreparing();
        }
    }
    
    private void tickPreparing() {
        countdown--;
        
        if (countdown <= 0) {
            startGame();
        }
    }
    
    private void tickPlaying() {
        // Game logic ticks here
        long elapsed = System.currentTimeMillis() - startTime;
        
        // Check win conditions
        checkWinConditions();
    }
    
    private void tickEnding() {
        countdown--;
        
        if (countdown <= 0) {
            endGame();
        }
    }
    
    /**
     * Add a player to the session
     * @param player The player to add
     * @return true if added successfully
     */
    public boolean addPlayer(PlayerRef player) {
        if (players.containsKey(player.getUuid())) {
            return false;
        }
        
        if (!state.isJoinable()) {
            return false;
        }
        
        if (players.size() >= config.getMaxPlayers()) {
            // TODO: Check VIP permission for joining full games
            return false;
        }
        
        PlayerSession session = new PlayerSession(player);
        players.put(player.getUuid(), session);
        
        // Auto-assign to smallest team
        Team team = findSmallestTeam();
        assignTeam(player.getUuid(), team);
        
        broadcastMessage("§a" + player.getUsername() + " has joined the game! (" + players.size() + "/" + config.getMaxPlayers() + ")");
        
        return true;
    }
    
    /**
     * Remove a player from the session
     * @param playerUuid The player UUID
     */
    public void removePlayer(UUID playerUuid) {
        PlayerSession session = players.remove(playerUuid);
        if (session == null) return;
        
        Team team = playerTeams.remove(playerUuid);
        if (team != null) {
            teams.get(team).removePlayer(playerUuid);
        }
        
        broadcastMessage("§c" + session.getPlayerRef().getUsername() + " has left the game!");
        
        // Check if game should end
        if (state == GameState.PLAYING && players.size() < config.getMinPlayers()) {
            endGame();
        }
    }
    
    /**
     * Assign a player to a team
     * @param playerUuid The player UUID
     * @param team The team
     */
    private void assignTeam(UUID playerUuid, Team team) {
        playerTeams.put(playerUuid, team);
        teams.get(team).addPlayer(playerUuid);
    }
    
    /**
     * Find the team with the least players
     * @return The smallest team
     */
    private Team findSmallestTeam() {
        return teams.entrySet().stream()
            .min(Comparator.comparingInt(e -> e.getValue().getPlayerCount()))
            .map(Map.Entry::getKey)
            .orElse(Team.BLUE);
    }
    
    /**
     * Start the countdown
     */
    private void startCountdown() {
        state = GameState.STARTING;
        countdown = config.getLobbyCountdown();
        broadcastMessage("§aGame starting soon!");
    }
    
    /**
     * Start the preparing phase
     */
    private void startPreparing() {
        state = GameState.PREPARING;
        countdown = config.getPregameCountdown();
        broadcastMessage("§6Prepare for battle!");
        
        // Teleport players to spawns, etc.
    }
    
    /**
     * Start the game
     */
    private void startGame() {
        state = GameState.PLAYING;
        startTime = System.currentTimeMillis();
        broadcastMessage("§a§lGAME STARTED!");
        
        // Initialize resources, spawn NPCs, etc.
        for (TeamData teamData : teams.values()) {
            teamData.initializeResources(config);
        }
    }
    
    /**
     * Check win conditions
     */
    private void checkWinConditions() {
        // Count alive teams (teams with Forum intact)
        long aliveTeams = teams.values().stream()
            .filter(TeamData::isForumAlive)
            .count();
        
        if (aliveTeams <= 1) {
            // Find winner
            TeamData winner = teams.values().stream()
                .filter(TeamData::isForumAlive)
                .findFirst()
                .orElse(null);
            
            endGameWithWinner(winner);
        }
    }
    
    /**
     * End the game with a winner
     * @param winner The winning team (can be null for tie)
     */
    private void endGameWithWinner(TeamData winner) {
        state = GameState.ENDING;
        countdown = config.getRestartCountdown();
        
        if (winner != null) {
            broadcastMessage("§a§l" + winner.getTeam().getDisplayName() + " §a§lhas won the game!");
        } else {
            broadcastMessage("§e§lGame ended in a tie!");
        }
    }
    
    /**
     * End the game
     */
    public void endGame() {
        // Cleanup, save stats, etc.
        players.clear();
        playerTeams.clear();
        teams.values().forEach(TeamData::reset);
        
        state = GameState.WAITING;
        logger.info("Game session ended for arena: " + arena.getName());
    }
    
    /**
     * Broadcast a message to all players in the session
     * @param message The message to broadcast
     */
    private void broadcastMessage(String message) {
        // TODO: Implement actual message sending via Hytale API
        logger.info("[" + arena.getName() + "] " + message);
    }
    
    // ==================== Getters ====================
    
    public GameState getState() {
        return state;
    }
    
    public Arena getArena() {
        return arena;
    }
    
    public int getPlayerCount() {
        return players.size();
    }
    
    public TeamData getTeamData(Team team) {
        return teams.get(team);
    }
    
    public Team getPlayerTeam(UUID playerUuid) {
        return playerTeams.get(playerUuid);
    }
    
    public PlayerSession getPlayerSession(UUID playerUuid) {
        return players.get(playerUuid);
    }
}
