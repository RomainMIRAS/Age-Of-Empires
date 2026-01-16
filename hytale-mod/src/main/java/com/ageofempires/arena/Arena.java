package com.ageofempires.arena;

import com.ageofempires.core.team.Team;
import com.ageofempires.core.buildings.PlotSize;
import com.hypixel.hytale.server.math.vector.Vector3d;

import java.util.*;

/**
 * Represents a game arena with all location configurations
 */
public class Arena {

    private final String name;
    private final String displayName;
    private final String worldName;
    
    private boolean enabled;
    private boolean setupComplete;
    
    // Lobby
    private Vector3d lobbySpawn;
    
    // Team spawns and locations
    private final Map<Team, TeamLocations> teamLocations;
    
    // Game area bounds
    private Vector3d minBound;
    private Vector3d maxBound;
    
    public Arena(String name) {
        this.name = name;
        this.displayName = name;
        this.worldName = name;
        this.enabled = false;
        this.setupComplete = false;
        this.teamLocations = new EnumMap<>(Team.class);
        
        // Initialize team locations
        for (Team team : Team.values()) {
            teamLocations.put(team, new TeamLocations(team));
        }
    }
    
    // ==================== Basic Info ====================
    
    public String getName() { return name; }
    public String getDisplayName() { return displayName; }
    public String getWorldName() { return worldName; }
    public boolean isEnabled() { return enabled; }
    public void setEnabled(boolean enabled) { this.enabled = enabled; }
    public boolean isSetupComplete() { return setupComplete; }
    
    // ==================== Lobby ====================
    
    public Vector3d getLobbySpawn() { return lobbySpawn; }
    public void setLobbySpawn(Vector3d location) { this.lobbySpawn = location; }
    
    // ==================== Team Locations ====================
    
    public TeamLocations getTeamLocations(Team team) {
        return teamLocations.get(team);
    }
    
    public void setTeamSpawn(Team team, Vector3d location) {
        teamLocations.get(team).setSpawnLocation(location);
    }
    
    public void setTeamForum(Team team, Vector3d location) {
        teamLocations.get(team).setForumLocation(location);
    }
    
    public void addTeamPlot(Team team, Vector3d location, PlotSize size) {
        teamLocations.get(team).addPlot(new Plot(location, size));
    }
    
    // ==================== Bounds ====================
    
    public Vector3d getMinBound() { return minBound; }
    public Vector3d getMaxBound() { return maxBound; }
    
    public void setBounds(Vector3d min, Vector3d max) {
        this.minBound = min;
        this.maxBound = max;
    }
    
    public boolean isInBounds(Vector3d position) {
        if (minBound == null || maxBound == null) return true;
        
        return position.x() >= minBound.x() && position.x() <= maxBound.x() &&
               position.y() >= minBound.y() && position.y() <= maxBound.y() &&
               position.z() >= minBound.z() && position.z() <= maxBound.z();
    }
    
    // ==================== Validation ====================
    
    /**
     * Validate arena setup is complete
     */
    public ValidationResult validate() {
        List<String> errors = new ArrayList<>();
        
        if (lobbySpawn == null) {
            errors.add("Lobby spawn not set");
        }
        
        // Check at least 2 teams have required locations
        int validTeams = 0;
        for (Team team : new Team[]{Team.BLUE, Team.RED}) {
            TeamLocations loc = teamLocations.get(team);
            boolean valid = true;
            
            if (loc.getSpawnLocation() == null) {
                errors.add(team.getDisplayName() + " spawn not set");
                valid = false;
            }
            
            if (loc.getForumLocation() == null) {
                errors.add(team.getDisplayName() + " forum not set");
                valid = false;
            }
            
            if (loc.getPlots().isEmpty()) {
                errors.add(team.getDisplayName() + " has no building plots");
                valid = false;
            }
            
            if (valid) validTeams++;
        }
        
        if (validTeams < 2) {
            errors.add("At least 2 teams must be fully configured");
        }
        
        setupComplete = errors.isEmpty();
        return new ValidationResult(setupComplete, errors);
    }
    
    /**
     * Team-specific locations container
     */
    public static class TeamLocations {
        private final Team team;
        private Vector3d spawnLocation;
        private Vector3d forumLocation;
        private Vector3d kingLocation;
        private final List<Plot> plots;
        
        public TeamLocations(Team team) {
            this.team = team;
            this.plots = new ArrayList<>();
        }
        
        public Team getTeam() { return team; }
        
        public Vector3d getSpawnLocation() { return spawnLocation; }
        public void setSpawnLocation(Vector3d location) { this.spawnLocation = location; }
        
        public Vector3d getForumLocation() { return forumLocation; }
        public void setForumLocation(Vector3d location) { this.forumLocation = location; }
        
        public Vector3d getKingLocation() { return kingLocation; }
        public void setKingLocation(Vector3d location) { this.kingLocation = location; }
        
        public List<Plot> getPlots() { return new ArrayList<>(plots); }
        public void addPlot(Plot plot) { plots.add(plot); }
        
        public int getPlotCount() { return plots.size(); }
        public int getPlotCount(PlotSize size) {
            return (int) plots.stream().filter(p -> p.getSize() == size).count();
        }
    }
    
    /**
     * Building plot
     */
    public static class Plot {
        private final Vector3d location;
        private final PlotSize size;
        private boolean occupied;
        
        public Plot(Vector3d location, PlotSize size) {
            this.location = location;
            this.size = size;
            this.occupied = false;
        }
        
        public Vector3d getLocation() { return location; }
        public PlotSize getSize() { return size; }
        public boolean isOccupied() { return occupied; }
        public void setOccupied(boolean occupied) { this.occupied = occupied; }
    }
    
    /**
     * Validation result
     */
    public static class ValidationResult {
        private final boolean valid;
        private final List<String> errors;
        
        public ValidationResult(boolean valid, List<String> errors) {
            this.valid = valid;
            this.errors = errors;
        }
        
        public boolean isValid() { return valid; }
        public List<String> getErrors() { return errors; }
    }
}
