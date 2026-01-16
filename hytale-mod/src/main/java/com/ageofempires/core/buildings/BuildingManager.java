package com.ageofempires.core.buildings;

import com.ageofempires.AgeOfEmpiresPlugin;
import com.ageofempires.core.game.GameSession;
import com.ageofempires.core.team.Team;
import com.ageofempires.core.team.TeamData;
import com.ageofempires.core.resources.ResourceType;
import com.hypixel.hytale.server.math.vector.Vector3d;
import com.hypixel.hytale.server.core.Message;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Manages buildings for a game session
 */
public class BuildingManager {

    private final AgeOfEmpiresPlugin plugin;
    private final GameSession gameSession;
    private final Logger logger;
    
    private final Map<UUID, Building> buildings;
    private final Map<Team, List<Building>> buildingsByTeam;
    
    // Production intervals in milliseconds
    private static final long RESOURCE_PRODUCTION_INTERVAL = 30000; // 30 seconds
    
    public BuildingManager(AgeOfEmpiresPlugin plugin, GameSession gameSession) {
        this.plugin = plugin;
        this.gameSession = gameSession;
        this.logger = plugin.getLogger();
        this.buildings = new ConcurrentHashMap<>();
        this.buildingsByTeam = new ConcurrentHashMap<>();
        
        // Initialize team building lists
        for (Team team : Team.values()) {
            buildingsByTeam.put(team, new ArrayList<>());
        }
    }
    
    /**
     * Construct a new building
     */
    public BuildResult constructBuilding(Team team, BuildingType type, Vector3d position) {
        TeamData teamData = gameSession.getTeamData(team);
        
        if (teamData == null) {
            return new BuildResult(false, "Team not found");
        }
        
        // Check if team has enough resources
        Map<ResourceType, Integer> cost = type.getCost();
        if (!teamData.getResources().hasEnough(cost)) {
            return new BuildResult(false, "Not enough resources");
        }
        
        // Check if position is valid (in team territory, not overlapping)
        if (!isValidBuildPosition(team, type, position)) {
            return new BuildResult(false, "Invalid build position");
        }
        
        // Check team level requirement
        if (teamData.getLevel() < type.getRequiredLevel()) {
            return new BuildResult(false, "Team level too low (requires level " + type.getRequiredLevel() + ")");
        }
        
        // Deduct resources
        teamData.getResources().removeAll(cost);
        
        // Create and register building
        Building building = new Building(type, team, position);
        buildings.put(building.getId(), building);
        buildingsByTeam.get(team).add(building);
        
        // Place schematic in world
        placeSchematic(type, position);
        
        logger.info("Building constructed: " + type.getDisplayName() + " for team " + team.name());
        
        return new BuildResult(true, "Building constructed!", building);
    }
    
    /**
     * Upgrade an existing building
     */
    public BuildResult upgradeBuilding(Team team, UUID buildingId) {
        Building building = buildings.get(buildingId);
        
        if (building == null) {
            return new BuildResult(false, "Building not found");
        }
        
        if (building.getOwnerTeam() != team) {
            return new BuildResult(false, "You don't own this building");
        }
        
        if (building.getLevel() >= 3) {
            return new BuildResult(false, "Building is already max level");
        }
        
        TeamData teamData = gameSession.getTeamData(team);
        
        // Calculate upgrade cost
        Map<ResourceType, Integer> baseCost = building.getType().getCost();
        Map<ResourceType, Integer> upgradeCost = new EnumMap<>(ResourceType.class);
        double multiplier = building.getUpgradeCostMultiplier();
        
        for (Map.Entry<ResourceType, Integer> entry : baseCost.entrySet()) {
            upgradeCost.put(entry.getKey(), (int) (entry.getValue() * multiplier));
        }
        
        if (!teamData.getResources().hasEnough(upgradeCost)) {
            return new BuildResult(false, "Not enough resources for upgrade");
        }
        
        // Deduct resources and upgrade
        teamData.getResources().removeAll(upgradeCost);
        building.upgrade();
        
        return new BuildResult(true, "Building upgraded to level " + building.getLevel(), building);
    }
    
    /**
     * Demolish a building (returns partial resources)
     */
    public BuildResult demolishBuilding(Team team, UUID buildingId) {
        Building building = buildings.get(buildingId);
        
        if (building == null) {
            return new BuildResult(false, "Building not found");
        }
        
        if (building.getOwnerTeam() != team) {
            return new BuildResult(false, "You don't own this building");
        }
        
        TeamData teamData = gameSession.getTeamData(team);
        
        // Return 50% of resources
        Map<ResourceType, Integer> cost = building.getType().getCost();
        for (Map.Entry<ResourceType, Integer> entry : cost.entrySet()) {
            teamData.getResources().add(entry.getKey(), entry.getValue() / 2);
        }
        
        // Remove building
        removeBuilding(building);
        
        return new BuildResult(true, "Building demolished, resources partially refunded");
    }
    
    /**
     * Remove a building from the game
     */
    private void removeBuilding(Building building) {
        buildings.remove(building.getId());
        buildingsByTeam.get(building.getOwnerTeam()).remove(building);
        building.destroy();
        
        // TODO: Clear schematic from world
    }
    
    /**
     * Check if a position is valid for building
     */
    private boolean isValidBuildPosition(Team team, BuildingType type, Vector3d position) {
        // TODO: Implement proper collision detection
        // - Check if in team territory/plot
        // - Check if not overlapping other buildings
        // - Check if terrain is suitable
        return true;
    }
    
    /**
     * Place building schematic in the world
     */
    private void placeSchematic(BuildingType type, Vector3d position) {
        // TODO: Implement schematic placement using Hytale's PrefabStore
        // String schematicName = type.getSchematicName();
        // PrefabStore.loadAndPlace(schematicName, position);
    }
    
    /**
     * Process resource production for all buildings
     */
    public void processProduction() {
        for (Building building : buildings.values()) {
            if (!building.isActive() || !building.isResourceProducer()) {
                continue;
            }
            
            if (building.canProduce(RESOURCE_PRODUCTION_INTERVAL)) {
                produceResources(building);
            }
        }
    }
    
    /**
     * Produce resources from a building
     */
    private void produceResources(Building building) {
        TeamData teamData = gameSession.getTeamData(building.getOwnerTeam());
        if (teamData == null) return;
        
        ResourceType resourceType = getProducedResource(building.getType());
        if (resourceType == null) return;
        
        int amount = building.getProductionAmount(5); // Base 5 per tick
        teamData.getResources().add(resourceType, amount);
        building.markProduction();
        
        // Notify team members
        // TODO: Send notification to team players
    }
    
    /**
     * Get the resource type produced by a building
     */
    private ResourceType getProducedResource(BuildingType type) {
        switch (type) {
            case SAWMILL: return ResourceType.WOOD;
            case STONE_MINE: return ResourceType.STONE;
            case GOLD_MINE: return ResourceType.GOLD;
            case FORGE: return ResourceType.IRON;
            case MILL: return ResourceType.FOOD;
            default: return null;
        }
    }
    
    /**
     * Get all buildings for a team
     */
    public List<Building> getTeamBuildings(Team team) {
        return new ArrayList<>(buildingsByTeam.getOrDefault(team, Collections.emptyList()));
    }
    
    /**
     * Get building by ID
     */
    public Building getBuilding(UUID id) {
        return buildings.get(id);
    }
    
    /**
     * Get building count for a team
     */
    public int getBuildingCount(Team team) {
        return buildingsByTeam.getOrDefault(team, Collections.emptyList()).size();
    }
    
    /**
     * Get building count by type for a team
     */
    public int getBuildingCount(Team team, BuildingType type) {
        return (int) buildingsByTeam.getOrDefault(team, Collections.emptyList())
                .stream()
                .filter(b -> b.getType() == type && b.isActive())
                .count();
    }
    
    /**
     * Get total building count
     */
    public int getTotalBuildingCount() {
        return buildings.size();
    }
    
    /**
     * Clean up destroyed buildings
     */
    public void cleanupDestroyedBuildings() {
        List<Building> toRemove = buildings.values().stream()
                .filter(b -> !b.isActive())
                .collect(Collectors.toList());
        
        for (Building building : toRemove) {
            removeBuilding(building);
        }
    }
    
    /**
     * Reset all buildings (end of game)
     */
    public void reset() {
        buildings.clear();
        for (Team team : Team.values()) {
            buildingsByTeam.put(team, new ArrayList<>());
        }
    }
    
    /**
     * Result of a build operation
     */
    public static class BuildResult {
        private final boolean success;
        private final String message;
        private final Building building;
        
        public BuildResult(boolean success, String message) {
            this(success, message, null);
        }
        
        public BuildResult(boolean success, String message, Building building) {
            this.success = success;
            this.message = message;
            this.building = building;
        }
        
        public boolean isSuccess() { return success; }
        public String getMessage() { return message; }
        public Building getBuilding() { return building; }
    }
}
