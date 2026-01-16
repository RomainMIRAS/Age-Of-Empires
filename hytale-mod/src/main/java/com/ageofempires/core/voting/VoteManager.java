package com.ageofempires.core.voting;

import com.ageofempires.core.game.GameSession;
import com.hypixel.hytale.server.core.entity.PlayerRef;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Manages voting during the pre-game lobby phase
 */
public class VoteManager {

    private final GameSession gameSession;
    
    // Map votes
    private final Map<PlayerRef, Integer> mapVotes;
    private final String[] mapOptions;
    
    // Time votes
    private final Map<PlayerRef, Boolean> timeVotes; // true = day, false = night
    
    // Team count votes
    private final Map<PlayerRef, Integer> teamVotes;
    
    public VoteManager(GameSession gameSession) {
        this.gameSession = gameSession;
        this.mapVotes = new ConcurrentHashMap<>();
        this.timeVotes = new ConcurrentHashMap<>();
        this.teamVotes = new ConcurrentHashMap<>();
        this.mapOptions = new String[]{"Classic Arena", "Desert Fortress", "Forest Valley"};
    }
    
    // ==================== Map Voting ====================
    
    /**
     * Vote for a map (1-3)
     */
    public void voteMap(PlayerRef player, int mapNumber) {
        if (mapNumber >= 1 && mapNumber <= mapOptions.length) {
            mapVotes.put(player, mapNumber);
        }
    }
    
    /**
     * Get winning map index (1-based)
     */
    public int getWinningMap() {
        Map<Integer, Integer> counts = new HashMap<>();
        
        for (int vote : mapVotes.values()) {
            counts.merge(vote, 1, Integer::sum);
        }
        
        return counts.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(1); // Default to map 1
    }
    
    /**
     * Get map name by index
     */
    public String getMapName(int index) {
        if (index >= 1 && index <= mapOptions.length) {
            return mapOptions[index - 1];
        }
        return mapOptions[0];
    }
    
    /**
     * Get votes for each map
     */
    public Map<Integer, Integer> getMapVoteCounts() {
        Map<Integer, Integer> counts = new HashMap<>();
        for (int i = 1; i <= mapOptions.length; i++) {
            counts.put(i, 0);
        }
        
        for (int vote : mapVotes.values()) {
            counts.merge(vote, 1, Integer::sum);
        }
        
        return counts;
    }
    
    // ==================== Time Voting ====================
    
    /**
     * Vote for day/night time
     */
    public void voteTime(PlayerRef player, boolean isDay) {
        timeVotes.put(player, isDay);
    }
    
    /**
     * Get winning time option
     * @return true for day, false for night
     */
    public boolean getWinningTime() {
        long dayVotes = timeVotes.values().stream().filter(v -> v).count();
        long nightVotes = timeVotes.values().stream().filter(v -> !v).count();
        
        return dayVotes >= nightVotes; // Day wins ties
    }
    
    /**
     * Get time vote counts
     */
    public int[] getTimeVoteCounts() {
        int dayVotes = (int) timeVotes.values().stream().filter(v -> v).count();
        int nightVotes = (int) timeVotes.values().stream().filter(v -> !v).count();
        return new int[]{dayVotes, nightVotes};
    }
    
    // ==================== Team Count Voting ====================
    
    /**
     * Vote for number of teams (2 or 4)
     */
    public void voteTeams(PlayerRef player, int teamCount) {
        if (teamCount == 2 || teamCount == 4) {
            teamVotes.put(player, teamCount);
        }
    }
    
    /**
     * Get winning team count
     */
    public int getWinningTeamCount() {
        long twoTeamVotes = teamVotes.values().stream().filter(v -> v == 2).count();
        long fourTeamVotes = teamVotes.values().stream().filter(v -> v == 4).count();
        
        return twoTeamVotes >= fourTeamVotes ? 2 : 4;
    }
    
    /**
     * Get team count vote counts
     */
    public int[] getTeamVoteCounts() {
        int twoVotes = (int) teamVotes.values().stream().filter(v -> v == 2).count();
        int fourVotes = (int) teamVotes.values().stream().filter(v -> v == 4).count();
        return new int[]{twoVotes, fourVotes};
    }
    
    // ==================== Utility Methods ====================
    
    /**
     * Remove a player's votes (when they leave)
     */
    public void removeVotes(PlayerRef player) {
        mapVotes.remove(player);
        timeVotes.remove(player);
        teamVotes.remove(player);
    }
    
    /**
     * Reset all votes
     */
    public void reset() {
        mapVotes.clear();
        timeVotes.clear();
        teamVotes.clear();
    }
    
    /**
     * Get total number of voters
     */
    public int getTotalVoters() {
        Set<PlayerRef> allVoters = new HashSet<>();
        allVoters.addAll(mapVotes.keySet());
        allVoters.addAll(timeVotes.keySet());
        allVoters.addAll(teamVotes.keySet());
        return allVoters.size();
    }
    
    /**
     * Check if a player has voted for anything
     */
    public boolean hasVoted(PlayerRef player) {
        return mapVotes.containsKey(player) || 
               timeVotes.containsKey(player) || 
               teamVotes.containsKey(player);
    }
    
    /**
     * Get vote summary for display
     */
    public VoteSummary getSummary() {
        return new VoteSummary(
            getWinningMap(),
            getMapName(getWinningMap()),
            getWinningTime(),
            getWinningTeamCount()
        );
    }
    
    /**
     * Vote summary data class
     */
    public static class VoteSummary {
        private final int mapIndex;
        private final String mapName;
        private final boolean isDayTime;
        private final int teamCount;
        
        public VoteSummary(int mapIndex, String mapName, boolean isDayTime, int teamCount) {
            this.mapIndex = mapIndex;
            this.mapName = mapName;
            this.isDayTime = isDayTime;
            this.teamCount = teamCount;
        }
        
        public int getMapIndex() { return mapIndex; }
        public String getMapName() { return mapName; }
        public boolean isDayTime() { return isDayTime; }
        public int getTeamCount() { return teamCount; }
        
        @Override
        public String toString() {
            return String.format("Map: %s, Time: %s, Teams: %d",
                mapName, isDayTime ? "Day" : "Night", teamCount);
        }
    }
}
