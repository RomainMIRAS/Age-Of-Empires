package com.andrei1058.ageofempire.listeners;

import com.andrei1058.ageofempire.game.Status;
import com.andrei1058.ageofempire.game.Vote;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

import static com.andrei1058.ageofempire.Main.*;
import static com.andrei1058.ageofempire.configuration.Messages.getMsg;
import static com.andrei1058.ageofempire.game.Buildings.*;
import static com.andrei1058.ageofempire.runnables.Game.*;

public class InventoryClickListener implements Listener {

    @EventHandler
    public void i(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (SETUP) return;
        if (STATUS != Status.PLAYING) {
            e.setCancelled(true);
        }
        if (e.getCurrentItem() == null) return;
        if (e.getInventory().getName().equalsIgnoreCase(getMsg("stats.displayname"))){
            e.setCancelled(true);
            return;
        }
        if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName() != null) {
            switch (e.getCurrentItem().getType()) {
                case SKULL_ITEM:
                    e.setCancelled(true);
                    break;
                case MOB_SPAWNER:
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(getMsg("locked-slot"))) {
                        e.setCancelled(true);
                    }
                    break;
                case PAPER:
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(getMsg("forum-paper"))) {
                        e.setCancelled(true);
                    }
                    break;
                case SLIME_BALL:
                    if (e.getCurrentItem().getItemMeta().getDisplayName() != null) {
                        if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(getMsg("validate-vote"))) {
                            e.setCancelled(true);
                        }
                    }
                    break;
                case ANVIL:
                    e.setCancelled(true);
                       stuff((Player) e.getWhoClicked(), forge, 150, 75, true, false, false);
                    break;
                case WHEAT:
                    e.setCancelled(true);
                    stuff((Player) e.getWhoClicked(), mill, 150, 75, true, false, false);
                    break;
                case DIAMOND_PICKAXE:
                    e.setCancelled(true);
                    stuff((Player) e.getWhoClicked(), stone_mine, 150, 75, true, false, false);
                    break;
                case GOLD_ORE:
                    e.setCancelled(true);
                    stuff((Player) e.getWhoClicked(), gold_mine, 150, 100, true, false, false);
                    break;
                case DIAMOND_AXE:
                    e.setCancelled(true);
                    if (e.getInventory().getName().equalsIgnoreCase("Forum")) {
                        stuff((Player) e.getWhoClicked(), sawmill, 150, 75, true, false, false);
                    } else {
                        buy(p, Material.DIAMOND_AXE, 1, (short) 0, 25);
                    }
                    break;
                case DIAMOND_SWORD:
                    e.setCancelled(true);
                    buy(p, Material.DIAMOND_SWORD, 1, (short) 0, 50);
                    break;
                case WORKBENCH:
                    e.setCancelled(true);
                    stuff((Player) e.getWhoClicked(), workshop, 100, 50, true, false, false);
                    break;
                case EMERALD:
                    e.setCancelled(true);
                    stuff((Player) e.getWhoClicked(), market, 100, 50, true, false, false);
                    break;
                case BONE:
                    e.setCancelled(true);
                    stuff((Player) e.getWhoClicked(), kennel, 100, 50, true, false, false);
                    break;
                case TNT:
                    e.setCancelled(true);
                    if (e.getInventory().getName().equalsIgnoreCase("Forum")){
                        stuff((Player) e.getWhoClicked(), sabotage, 100, 50, true, false, false);
                    } else {
                        buy(p, Material.TNT, 1, (short) 0, 5);
                    }
                    break;
                case STONE_PICKAXE:
                    e.setCancelled(true);
                    buy(p, Material.STONE_PICKAXE, 1, (short) 0,1);
                    break;
                case STONE_SWORD:
                    e.setCancelled(true);
                    buy(p, Material.STONE_SWORD, 1, (short) 0, 10);
                    break;
                case IRON_SWORD:
                    e.setCancelled(true);
                    buy(p, Material.IRON_SWORD, 1, (short) 0, 30);
                    break;
                case STONE_AXE:
                    e.setCancelled(true);
                    buy(p, Material.STONE_AXE, 1, (short) 0, 5);
                    break;
                case IRON_AXE:
                    e.setCancelled(true);
                    buy(p, Material.IRON_AXE, 1, (short) 0, 15);
                    break;
                case BREAD:
                    e.setCancelled(true);
                    buy(p, Material.BREAD, 5, (short) 0, 10);
                    break;
                case POTATO:
                    e.setCancelled(true);
                    buy(p, Material.POTATO, 5, (short) 0, 15);
                    break;
                case COOKED_BEEF:
                    e.setCancelled(true);
                    buy(p, Material.COOKED_BEEF, 5, (short) 0, 22);
                    break;
                case COOKED_CHICKEN:
                    e.setCancelled(true);
                    buy(p, Material.COOKED_CHICKEN, 5, (short) 0, 20);
                    break;
                case GRASS:
                    e.setCancelled(true);
                    buy(p, Material.GRASS, 10, (short) 0, 10);
                    break;
                case DIRT:
                    e.setCancelled(true);
                    buy(p, Material.DIRT, 10, (short) 0, 10);
                    break;
                case WOOD_DOUBLE_STEP:
                    e.setCancelled(true);
                    buy(p, Material.WOOD_DOUBLE_STEP, 5, (short) 0, 10);
                    break;
                case SAND:
                    e.setCancelled(true);
                    buy(p, Material.SAND, 10, (short) 0, 10);
                    break;
                case WOOL:
                    e.setCancelled(true);
                        buy(p, Material.WOOL, 10, (short) e.getCurrentItem().getData().getData(), 10);
                    break;
                case BRICK:
                    e.setCancelled(true);
                    buy(p, Material.BRICK, 10, (short) 0, 15);
                    break;
                case LEAVES:
                    e.setCancelled(true);
                    buy(p, Material.LEAVES, 10, (short) 0, 10);
                    break;
                case MOSSY_COBBLESTONE:
                    e.setCancelled(true);
                    buy(p, Material.MOSSY_COBBLESTONE, 10, (short) 0, 15);
                    break;
                case GRAVEL:
                    e.setCancelled(true);
                    buy(p, Material.GRAVEL, 5, (short) 0, 10);
                    break;
                case GLASS:
                    e.setCancelled(true);
                    buy(p, Material.GRAVEL, 10, (short) 0, 10);
                    break;
                case LAPIS_BLOCK:
                    e.setCancelled(true);
                    buy(p, Material.LAPIS_BLOCK, 5, (short) 0, 26);
                    break;
                case FLINT_AND_STEEL:
                    e.setCancelled(true);
                    buy(p, Material.FLINT_AND_STEEL, 1, (short) 0, 5);
                    break;
                case WEB:
                    e.setCancelled(true);
                    buy(p, Material.WEB, 5, (short) 0, 5);
                    break;
                case TORCH:
                    e.setCancelled(true);
                    buy(p, Material.TORCH, 12, (short) 0, 5);
                    break;
                case BOAT:
                    e.setCancelled(true);
                    buy(p, Material.BOAT, 1, (short) 0, 2);
                    break;
                case MONSTER_EGG:
                    e.setCancelled(true);
                    if (e.getInventory().getName().equalsIgnoreCase("Stable")) {
                        if (gold.get(p) >= 30) {
                            gold.replace(p, gold.get(p) - 30);
                            Horse h = (Horse) p.getWorld().spawnEntity(p.getLocation(), EntityType.HORSE);
                            h.setOwner(p);
                            h.getInventory().setSaddle(new ItemStack(Material.SADDLE));
                        } else {
                            p.sendMessage(getMsg("insufficient-gold"));
                        }
                    } else {
                        if (e.getSlot() == 0) {
                            if (gold.get(p) >= 50) {
                                gold.replace(p, gold.get(p) - 50);
                                Wolf w = (Wolf) p.getWorld().spawnEntity(p.getLocation(), EntityType.WOLF);
                                w.setOwner(p);
                                w.setCollarColor(DyeColor.BLUE);
                            } else {
                                p.sendMessage(getMsg("insufficient-gold"));
                            }
                        } else if (e.getSlot() == 1) {
                            if (gold.get(p) >= 75) {
                                gold.replace(p, gold.get(p) - 75);
                                Wolf w = (Wolf) p.getWorld().spawnEntity(p.getLocation(), EntityType.WOLF);
                                w.setOwner(p);
                                w.setCollarColor(DyeColor.BLUE);
                                Wolf w2 = (Wolf) p.getWorld().spawnEntity(p.getLocation(), EntityType.WOLF);
                                w2.setOwner(p);
                                w2.setCollarColor(DyeColor.BLUE);
                            } else {
                                p.sendMessage(getMsg("insufficient-gold"));
                            }
                        } else if (e.getSlot() == 2) {
                            if (gold.get(p) >= 100) {
                                gold.replace(p, gold.get(p) - 100);
                                Wolf w = (Wolf) p.getWorld().spawnEntity(p.getLocation(), EntityType.WOLF);
                                w.setOwner(p);
                                w.setCollarColor(DyeColor.BLUE);
                                Wolf w2 = (Wolf) p.getWorld().spawnEntity(p.getLocation(), EntityType.WOLF);
                                w2.setOwner(p);
                                w2.setCollarColor(DyeColor.BLUE);
                                Wolf w3 = (Wolf) p.getWorld().spawnEntity(p.getLocation(), EntityType.WOLF);
                                w3.setOwner(p);
                                w3.setCollarColor(DyeColor.BLUE);
                            } else {
                                p.sendMessage(getMsg("insufficient-gold"));
                            }
                        }
                    }
                    break;
                case DARK_OAK_DOOR_ITEM:
                    e.setCancelled(true);
                    voteAge(p, 1250, 750, 2);
                    break;
                case IRON_DOOR:
                    e.setCancelled(true);
                    voteAge(p, 2250, 1250, 3);
                    break;
                case DIAMOND:
                    e.setCancelled(true);
                    voteAge(p, 4250, 3150, 4);
                    break;
                default:
                    e.setCancelled(true);
                    break;
                case BOW:
                    e.setCancelled(true);
                    if (e.getInventory().getName().equalsIgnoreCase("Forum")) {
                        stuff((Player) e.getWhoClicked(), archery, 300, 150, false, true, false);
                    } else {
                        buy(p, Material.BOW, 1, (short) 0, 30);
                    }
                    break;
                case ARROW:
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cExplosive")){
                        e.setCancelled(false);
                        return;
                    }
                    e.setCancelled(true);
                    if (e.getInventory().getName().equalsIgnoreCase("Forum")) {
                        stuff((Player) e.getWhoClicked(), trifarrow, 375, 175, false, true, false);
                    } else if (e.getInventory().getName().equalsIgnoreCase("TrifArrow")) {
                        ItemStack i = new ItemStack(Material.ARROW);
                        ItemMeta im = i.getItemMeta();
                        im.setDisplayName("§cExplosive");
                        i.setItemMeta(im);
                        if (gold.get(p) >= 20) {
                            gold.replace(p, gold.get(p) - 20);
                            p.getInventory().addItem(i);
                        } else {
                            p.sendMessage(getMsg("insufficient-gold"));
                        }
                    } else if (e.getInventory().getName().equalsIgnoreCase("Archery Store")){
                        if (e.getSlot() == 1 && e.getCurrentItem().getType() == Material.ARROW){
                            buy(p, Material.ARROW, 5, (short) 0, 5);
                        } else if (e.getSlot() == 2 && e.getCurrentItem().getType() == Material.ARROW) {
                            buy(p, Material.ARROW, 10, (short) 0, 7);
                        }
                    }
                    break;
                case SADDLE:
                    e.setCancelled(true);
                    stuff((Player) e.getWhoClicked(), stable, 200, 100, false, true, false);
                    break;
                case DIAMOND_CHESTPLATE:
                    if (e.getInventory().getName().equalsIgnoreCase("Forum")) {
                        e.setCancelled(true);
                        stuff((Player) e.getWhoClicked(), armory, 300, 150, false, true, false);
                    } else if (e.getInventory().getName().equalsIgnoreCase("Armory")){
                        e.setCancelled(true);
                        buyDiamondArmor(p);
                    }
                    break;
                case BREWING_STAND_ITEM:
                    e.setCancelled(true);
                    stuff((Player) e.getWhoClicked(), laboratory, 300, 150, false, true, false);
                    break;
                case ENCHANTED_BOOK:
                    if (e.getInventory().getName().equalsIgnoreCase("Forum")) {
                        e.setCancelled(true);
                        stuff((Player) e.getWhoClicked(), guild, 600, 300, false, false, true);
                    } else if (e.getInventory().getName().equalsIgnoreCase("Guild")){
                        e.setCancelled(true);
                        String name = e.getCurrentItem().getItemMeta().getDisplayName();
                        if (name.equalsIgnoreCase(getMsg("guild.sharpness.name"))){
                            enchant(p, Enchantment.DAMAGE_ALL, 20);
                        } else if (name.equalsIgnoreCase(getMsg("guild.knockback.name"))){
                            enchant(p, Enchantment.KNOCKBACK, 10);
                        } else if (name.equalsIgnoreCase(getMsg("guild.protection.name"))){
                            enchant(p, Enchantment.PROTECTION_ENVIRONMENTAL, 20);
                        } else if (name.equalsIgnoreCase(getMsg("guild.thorns.name"))){
                            enchant(p, Enchantment.THORNS, 10);
                        } else if (name.equalsIgnoreCase(getMsg("guild.featherfalling.name"))){
                            enchant(p, Enchantment.LURE, 10);
                        } else if (name.equalsIgnoreCase(getMsg("guild.projectileprotection.name"))){
                            enchant(p, Enchantment.PROTECTION_PROJECTILE, 10);
                        } else if (name.equalsIgnoreCase(getMsg("guild.fireprotection.name"))){
                            enchant(p, Enchantment.PROTECTION_FIRE, 10);
                        } else if (name.equalsIgnoreCase(getMsg("guild.power.name"))){
                            enchant(p, Enchantment.ARROW_DAMAGE, 20);
                        } else if (name.equalsIgnoreCase(getMsg("guild.power.name"))){
                            enchant(p, Enchantment.ARROW_KNOCKBACK, 10);
                        }
                    }
                    break;
                case EXP_BOTTLE:
                    e.setCancelled(true);
                    stuff((Player) e.getWhoClicked(), training_center, 500, 250, false, false, true);
                    break;
                case IRON_CHESTPLATE:
                    if (e.getInventory().getName().equalsIgnoreCase("Armory")){
                        e.setCancelled(true);
                        buyIronArmor(p);
                    }
                    break;
                case IRON_BARDING:
                    if (e.getInventory().getName().equalsIgnoreCase("Armory")){
                        e.setCancelled(true);
                        buy(p, Material.IRON_BARDING, 1, (short) 0, 80);
                    }
                    break;
                case DIAMOND_BARDING:
                    if (e.getInventory().getName().equalsIgnoreCase("Armory")){
                        e.setCancelled(true);
                        buy(p, Material.DIAMOND_BARDING, 1, (short) 0, 80);
                    }
                    break;
                case POTION:
                    if (e.getInventory().getName().equalsIgnoreCase("Laboratory")) {
                        e.setCancelled(true);
                        String name = e.getCurrentItem().getItemMeta().getDisplayName();
                        if (name.equalsIgnoreCase(getMsg("lab.swiftness.name"))){
                            buyPotion(p, 35, PotionType.SPEED, false);
                        } else if (name.equalsIgnoreCase(getMsg("lab.fireresistance.name"))){
                            buyPotion(p, 35, PotionType.FIRE_RESISTANCE, false);
                        } else if (name.equalsIgnoreCase(getMsg("lab.healing.name"))){
                            buyPotion(p, 35, PotionType.INSTANT_HEAL, false);
                        } else if (name.equalsIgnoreCase(getMsg("lab.nightvision.name"))){
                            buyPotion(p, 35, PotionType.NIGHT_VISION, false);
                        } else if (name.equalsIgnoreCase(getMsg("lab.leaping.lore"))){
                            buyPotion(p, 35, PotionType.JUMP, false);
                        } else if (name.equalsIgnoreCase(getMsg("lab.regeneration.lore"))){
                            buyPotion(p, 50, PotionType.REGEN, false);
                        } else if (name.equalsIgnoreCase(getMsg("lab.waterbreathing.name"))){
                            buyPotion(p, 35, PotionType.WATER_BREATHING, false);
                        } else if (name.equalsIgnoreCase(getMsg("lab.splashswiftness.name"))){
                            buyPotion(p, 75, PotionType.SPEED, true);
                        } else if (name.equalsIgnoreCase(getMsg("lab.splashleaping.name"))){
                            buyPotion(p, 75, PotionType.JUMP, true);
                        }
                    }
                    break;
            }
        }
    }

    //personal gold done
    private static void enchant(Player u, Enchantment enchantment, Integer price){
        ItemStack i = new ItemStack(Material.ENCHANTED_BOOK);
        EnchantmentStorageMeta m = (EnchantmentStorageMeta) i.getItemMeta();
        m.addStoredEnchant(enchantment,1,true);
        i.setItemMeta(m);
        if (gold.get(u) > price){
            u.getInventory().addItem(i);
            gold.replace(u, gold.get(u)-price);
        } else {
            u.sendMessage(getMsg("insufficient-gold"));
        }
    }

    private static void buyPotion(Player p, Integer price, PotionType potionType, boolean splash){
            if (gold.get(p) >= price) {
                Potion potion = new Potion(potionType);
                potion.setSplash(splash);
                p.getInventory().addItem(potion.toItemStack(1));
                gold.replace(p, gold.get(p)-price);
            } else {
                p.sendMessage(getMsg("insufficient-gold"));
            }
    }

    private static void buy(Player p, Material material, Integer cantitate, Short sh, Integer price) {
        if (gold.get(p) >= price) {
            p.getInventory().addItem(new ItemStack(material, cantitate, sh));
            gold.replace(p, gold.get(p)-price);
        } else {
            p.sendMessage(getMsg("insufficient-gold"));
        }
    }

    private static void buyIronArmor(Player p) {
        if (gold.get(p) >= 40) {
            gold.replace(p, gold.get(p) - 40);
            buy(p, Material.IRON_HELMET, 1, (short) 0, 0);
            buy(p, Material.IRON_CHESTPLATE, 1, (short) 0, 0);
            buy(p, Material.IRON_LEGGINGS, 1, (short) 0, 0);
            buy(p, Material.IRON_BOOTS, 1, (short) 0, 0);
        } else {
            p.sendMessage(getMsg("insufficient-gold"));
        }
    }

    private static void buyDiamondArmor(Player p) {
        if (gold.get(p) >= 80) {
            gold.replace(p, gold.get(p) - 80);
            buy(p, Material.IRON_HELMET, 1, (short) 0, 0);
            buy(p, Material.DIAMOND_CHESTPLATE, 1, (short) 0, 0);
            buy(p, Material.IRON_LEGGINGS, 1, (short) 0, 0);
            buy(p, Material.DIAMOND_BOOTS, 1, (short) 0, 0);
        } else {
            p.sendMessage(getMsg("insufficient-gold"));
        }
    }


    private static void voteAge(Player p, Integer wood, Integer stone, Integer age){
        if (age > 4) return;
        if (bluePlayers.contains(p)){
            if (blue_change_age) return;
            if (vote_in_progress.contains(blue_team)) {
                p.sendMessage(getMsg("cant-vote"));
                return;
            }
            if (blue_wood >= wood && blue_stone >= stone) {
                new Vote(bluePlayers, age_string, p, blue_team, wood, stone, age_string);
            } else {
                String miss_wood = "0";
                String miss_stone = "0";
                if (wood - blue_wood > 0) {
                    miss_wood = String.valueOf(wood - blue_wood);
                }
                if (stone - blue_stone > 0) {
                    miss_stone = String.valueOf(stone - blue_stone);
                }
                p.sendMessage(getMsg("insufficient-resources").replace("{wood}", miss_wood).replace("{stone}", miss_stone));
            }
        } else if (greenPlayers.contains(p)){
            if (green_change_age) return;
            if (vote_in_progress.contains(green_team)) {
                p.sendMessage(getMsg("cant-vote"));
                return;
            }
            if (green_wood >= wood && green_stone >= stone) {
                new Vote(greenPlayers, age_string, p, green_team, wood, stone, age_string);
            } else {
                String miss_wood = "0";
                String miss_stone = "0";
                if (wood - green_wood > 0) {
                    miss_wood = String.valueOf(wood - green_wood);
                }
                if (stone - green_stone > 0) {
                    miss_stone = String.valueOf(stone - green_stone);
                }
                p.sendMessage(getMsg("insufficient-resources").replace("{wood}", miss_wood).replace("{stone}", miss_stone));
            }
        } else if (yellowPlayers.contains(p)){
            if (yellow_change_age) return;
            if (vote_in_progress.contains(yellow_team)) {
                p.sendMessage(getMsg("cant-vote"));
                return;
            }
            if (yellow_wood >= wood && yellow_stone >= stone) {
                new Vote(yellowPlayers, age_string, p, yellow_team, wood, stone, age_string);
            } else {
                String miss_wood = "0";
                String miss_stone = "0";
                if (wood - yellow_wood > 0) {
                    miss_wood = String.valueOf(wood - yellow_wood);
                }
                if (stone - yellow_stone > 0) {
                    miss_stone = String.valueOf(stone - yellow_stone);
                }
                p.sendMessage(getMsg("insufficient-resources").replace("{wood}", miss_wood).replace("{stone}", miss_stone));
            }
        } else if (redPlayers.contains(p)){
            if (red_change_age) return;
            if (vote_in_progress.contains(red_team)) {
                p.sendMessage(getMsg("cant-vote"));
                return;
            }
            if (red_wood >= wood && red_stone >= stone) {
                new Vote(redPlayers, age_string, p, red_team, wood, stone, age_string);
            } else {
                String miss_wood = "0";
                String miss_stone = "0";
                if (wood - red_wood > 0) {
                    miss_wood = String.valueOf(wood - red_wood);
                }
                if (stone - red_stone > 0) {
                    miss_stone = String.valueOf(stone - red_stone);
                }
                p.sendMessage(getMsg("insufficient-resources").replace("{wood}", miss_wood).replace("{stone}", miss_stone));
            }
        }
    }

    private static void stuff(Player p, String building, Integer wood, Integer stone, boolean small, boolean medium, boolean large) {
        if (construct_in_inv.containsKey(p)) {
            p.sendMessage(getMsg("having-construct"));
            return;
        }
        if (bluePlayers.contains(p)) {
            if (blue_built.contains(building)) {
                p.sendMessage(getMsg("already-built"));
                return;
            }
            if (vote_in_progress.contains(blue_team)) {
                p.sendMessage(getMsg("cant-vote"));
                return;
            }
            if (blue_small_plots == 0 && small) {
                p.sendMessage(getMsg("cant-vote-full"));
                return;
            }
            if (blue_medium_plots == 0 && medium) {
                p.sendMessage(getMsg("cant-vote-full"));
                return;
            }
            if (blue_large_plots == 0 && large) {
                p.sendMessage(getMsg("cant-vote-full"));
                return;
            }
            if (blue_wood >= wood && blue_stone >= stone) {
                new Vote(bluePlayers, getMsg("forum." + building + ".displayname"), p, blue_team, wood, stone, building);
            } else {
                String miss_wood = "0";
                String miss_stone = "0";
                if (wood - blue_wood > 0) {
                    miss_wood = String.valueOf(wood - blue_wood);
                }
                if (stone - blue_stone > 0) {
                    miss_stone = String.valueOf(stone - blue_stone);
                }
                p.sendMessage(getMsg("insufficient-resources").replace("{wood}", miss_wood).replace("{stone}", miss_stone));
            }
        } else if (greenPlayers.contains(p)) {
            if (green_built.contains(building)) {
                p.sendMessage(getMsg("already-built"));
                return;
            }
            if (vote_in_progress.contains(green_team)) {
                p.sendMessage(getMsg("cant-vote"));
                return;
            }
            if (green_small_plots == 0 && small) {
                p.sendMessage(getMsg("cant-vote-full"));
                return;
            }
            if (green_medium_plots == 0 && medium) {
                p.sendMessage(getMsg("cant-vote-full"));
                return;
            }
            if (green_large_plots == 0 && large) {
                p.sendMessage(getMsg("cant-vote-full"));
                return;
            }
            if (green_wood >= wood && green_stone >= stone) {
                new Vote(greenPlayers, getMsg("forum." + building + ".displayname"), p, green_team, wood, stone, building);
            } else {
                String miss_wood = "0";
                String miss_stone = "0";
                if (wood - green_wood > 0) {
                    miss_wood = String.valueOf(wood - green_wood);
                }
                if (stone - green_stone > 0) {
                    miss_stone = String.valueOf(stone - green_stone);
                }
                p.sendMessage(getMsg("insufficient-resources").replace("{wood}", miss_wood).replace("{stone}", miss_stone));
            }
        } else if (yellowPlayers.contains(p)) {
            if (yellow_built.contains(building)) {
                p.sendMessage(getMsg("already-built"));
                return;
            }
            if (vote_in_progress.contains(yellow_team)) {
                p.sendMessage(getMsg("cant-vote"));
                return;
            }
            if (yellow_small_plots == 0 && small) {
                p.sendMessage(getMsg("cant-vote-full"));
                return;
            }
            if (yellow_medium_plots == 0 && medium) {
                p.sendMessage(getMsg("cant-vote-full"));
                return;
            }
            if (yellow_large_plots == 0 && large) {
                p.sendMessage(getMsg("cant-vote-full"));
                return;
            }
            if (yellow_wood >= wood && yellow_stone >= stone) {
                new Vote(yellowPlayers, getMsg("forum." + building + ".displayname"), p, yellow_team, wood, stone, building);
            } else {
                String miss_wood = "0";
                String miss_stone = "0";
                if (wood - yellow_wood > 0) {
                    miss_wood = String.valueOf(wood - yellow_wood);
                }
                if (stone - yellow_stone > 0) {
                    miss_stone = String.valueOf(stone - yellow_stone);
                }
                p.sendMessage(getMsg("insufficient-resources").replace("{wood}", miss_wood).replace("{stone}", miss_stone));
            }
        } else if (redPlayers.contains(p)) {
            if (red_built.contains(building)) {
                p.sendMessage(getMsg("already-built"));
                return;
            }
            if (vote_in_progress.contains(red_team)) {
                p.sendMessage(getMsg("cant-vote"));
                return;
            }
            if (red_small_plots == 0 && small) {
                p.sendMessage(getMsg("cant-vote-full"));
                return;
            }
            if (red_medium_plots == 0 && medium) {
                p.sendMessage(getMsg("cant-vote-full"));
                return;
            }
            if (red_large_plots == 0 && large) {
                p.sendMessage(getMsg("cant-vote-full"));
                return;
            }
            if (red_wood >= wood && red_stone >= stone) {
                new Vote(redPlayers, getMsg("forum." + building + ".displayname"), p, red_team, wood, stone, building);
            } else {
                String miss_wood = "0";
                String miss_stone = "0";
                if (wood - red_wood > 0) {
                    miss_wood = String.valueOf(wood - red_wood);
                }
                if (stone - red_stone > 0) {
                    miss_stone = String.valueOf(stone - red_stone);
                }
                p.sendMessage(getMsg("insufficient-resources").replace("{wood}", miss_wood).replace("{stone}", miss_stone));
            }
        }
    }

}
