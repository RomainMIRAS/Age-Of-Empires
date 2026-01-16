package com.ageofempires.core.kits;

import com.ageofempires.AgeOfEmpiresPlugin;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.item.ItemStack;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

/**
 * Manages all available kits and their application to players
 */
public class KitManager {

    private final AgeOfEmpiresPlugin plugin;
    private final Logger logger;
    private final Map<String, Kit> kits;
    private Kit defaultKit;
    
    public KitManager(AgeOfEmpiresPlugin plugin) {
        this.plugin = plugin;
        this.logger = plugin.getLogger();
        this.kits = new ConcurrentHashMap<>();
        
        // Register default kits
        registerDefaultKits();
    }
    
    /**
     * Register all default kits
     */
    private void registerDefaultKits() {
        // Warrior Kit - Balanced combat
        Kit warrior = Kit.builder("warrior")
            .name("Warrior")
            .description("Balanced fighter with sword and shield")
            .abilities(new Kit.KitAbilities()
                .setDamageMultiplier(1.0)
                .setHealthBonus(2.0))
            .build();
        
        // Archer Kit - Ranged combat
        Kit archer = Kit.builder("archer")
            .name("Archer")
            .description("Ranged fighter with bow and arrows")
            .abilities(new Kit.KitAbilities()
                .setDamageMultiplier(0.8)
                .setSpeedMultiplier(1.1))
            .build();
        
        // Builder Kit - Building focused
        Kit builder = Kit.builder("builder")
            .name("Builder")
            .description("Expert builder with faster construction")
            .abilities(new Kit.KitAbilities()
                .setDamageMultiplier(0.7)
                .setExtraBuildRange(2)
                .setResourceGatherBonus(25))
            .build();
        
        // Miner Kit - Resource focused
        Kit miner = Kit.builder("miner")
            .name("Miner")
            .description("Resource gatherer with bonus yields")
            .abilities(new Kit.KitAbilities()
                .setResourceGatherBonus(50)
                .setCanUseBow(false))
            .build();
        
        // Knight Kit - Tank role (premium)
        Kit knight = Kit.builder("knight")
            .name("Knight")
            .description("Heavy armor tank with high health")
            .permission("aoe.kit.knight")
            .abilities(new Kit.KitAbilities()
                .setDamageMultiplier(1.2)
                .setSpeedMultiplier(0.9)
                .setHealthBonus(6.0))
            .build();
        
        // Assassin Kit - High damage (premium)
        Kit assassin = Kit.builder("assassin")
            .name("Assassin")
            .description("Fast striker with high damage but low health")
            .permission("aoe.kit.assassin")
            .abilities(new Kit.KitAbilities()
                .setDamageMultiplier(1.5)
                .setSpeedMultiplier(1.3)
                .setHealthBonus(-4.0)
                .setCanBuild(false))
            .build();
        
        // Register kits
        registerKit(warrior);
        registerKit(archer);
        registerKit(builder);
        registerKit(miner);
        registerKit(knight);
        registerKit(assassin);
        
        // Set default kit
        this.defaultKit = warrior;
        
        logger.info("Registered " + kits.size() + " kits");
    }
    
    /**
     * Register a kit
     */
    public void registerKit(Kit kit) {
        kits.put(kit.getId().toLowerCase(), kit);
        logger.info("Registered kit: " + kit.getName());
    }
    
    /**
     * Unregister a kit
     */
    public void unregisterKit(String id) {
        kits.remove(id.toLowerCase());
    }
    
    /**
     * Get a kit by ID
     */
    public Kit getKit(String id) {
        if (id == null) return null;
        return kits.get(id.toLowerCase());
    }
    
    /**
     * Get the default kit
     */
    public Kit getDefaultKit() {
        return defaultKit;
    }
    
    /**
     * Set the default kit
     */
    public void setDefaultKit(Kit kit) {
        if (kit != null && kits.containsKey(kit.getId().toLowerCase())) {
            this.defaultKit = kit;
        }
    }
    
    /**
     * Get all registered kits
     */
    public Collection<Kit> getAllKits() {
        return Collections.unmodifiableCollection(kits.values());
    }
    
    /**
     * Get kits available to a player
     */
    public Collection<Kit> getAvailableKits(Player player) {
        List<Kit> available = new ArrayList<>();
        for (Kit kit : kits.values()) {
            if (kit.isAvailableTo(player)) {
                available.add(kit);
            }
        }
        return available;
    }
    
    /**
     * Get all available kits (no permission check)
     */
    public Collection<Kit> getAvailableKits() {
        return Collections.unmodifiableCollection(kits.values());
    }
    
    /**
     * Apply a kit to a player
     */
    public void applyKit(Player player, Kit kit) {
        if (player == null || kit == null) return;
        
        // Clear current inventory
        clearInventory(player);
        
        // Give kit items
        for (ItemStack item : kit.getItems()) {
            giveItem(player, item);
        }
        
        // Apply kit abilities
        applyAbilities(player, kit.getAbilities());
        
        logger.fine("Applied kit " + kit.getName() + " to player " + player.getName());
    }
    
    /**
     * Clear a player's inventory
     */
    private void clearInventory(Player player) {
        // TODO: Implement using Hytale inventory API
        // player.getInventory().clear();
    }
    
    /**
     * Give an item to a player
     */
    private void giveItem(Player player, ItemStack item) {
        // TODO: Implement using Hytale inventory API
        // player.getInventory().addItem(item);
    }
    
    /**
     * Apply kit abilities to a player
     */
    private void applyAbilities(Player player, Kit.KitAbilities abilities) {
        // TODO: Implement ability application using Hytale API
        // - Set health modifier
        // - Set speed modifier
        // - Apply damage multiplier through combat handler
    }
    
    /**
     * Reset a player's kit effects
     */
    public void resetPlayer(Player player) {
        clearInventory(player);
        // TODO: Reset any applied modifiers
    }
    
    /**
     * Get the number of registered kits
     */
    public int getKitCount() {
        return kits.size();
    }
}
