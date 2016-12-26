package com.andrei1058.ageofempire.listeners;

import com.andrei1058.ageofempire.game.Status;
import com.andrei1058.ageofempire.game.Vote;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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
                       stuff(e.getWhoClicked(), forge, 150, 75, true, false, false);
                    break;
                case WHEAT:
                    e.setCancelled(true);
                    stuff(e.getWhoClicked(), mill, 150, 75, true, false, false);
                    break;
                case DIAMOND_PICKAXE:
                    e.setCancelled(true);
                    stuff(e.getWhoClicked(), stone_mine, 150, 75, true, false, false);
                    break;
                case GOLD_ORE:
                    e.setCancelled(true);
                    stuff(e.getWhoClicked(), gold_mine, 150, 100, true, false, false);
                    break;
                case DIAMOND_AXE:
                    e.setCancelled(true);
                    stuff(e.getWhoClicked(), sawmill, 150, 75, true, false, false);
                    break;
                case WORKBENCH:
                    e.setCancelled(true);
                    stuff(e.getWhoClicked(), workshop, 100, 50, true, false, false);
                    break;
                case EMERALD:
                    e.setCancelled(true);
                    stuff(e.getWhoClicked(), market, 100, 50, true, false, false);
                    break;
                case BONE:
                    e.setCancelled(true);
                    stuff(e.getWhoClicked(), kennel, 100, 50, true, false, false);
                    break;
                case TNT:
                    e.setCancelled(true);
                    if (e.getInventory().getName().equalsIgnoreCase("Forum")){
                        stuff(e.getWhoClicked(), sabotage, 100, 50, true, false, false);
                    } else {
                        buy(p, Material.TNT, 1, (short) 0, 5);
                    }
                    break;
                case STONE_PICKAXE:
                    e.setCancelled(true);
                    if (bluePlayers.contains(p.getUniqueId())){
                       if (blue_gold >= 1){
                           blue_gold-=1;
                           p.getInventory().addItem(new ItemStack(Material.STONE_PICKAXE));
                       }
                    } else if (greenPlayers.contains(p.getUniqueId())){
                        if (green_gold >= 1){
                            green_gold-=1;
                            p.getInventory().addItem(new ItemStack(Material.STONE_PICKAXE));
                        }
                    } else if (redPlayers.contains(p.getUniqueId())){
                        if (red_gold >= 1){
                            red_gold-=1;
                            p.getInventory().addItem(new ItemStack(Material.STONE_PICKAXE));
                        }
                    } else if (yellowPlayers.contains(p.getUniqueId())){
                        if (yellow_gold >= 1){
                            yellow_gold-=1;
                            p.getInventory().addItem(new ItemStack(Material.STONE_PICKAXE));
                        }
                    }
                    break;
                case STONE_SWORD:
                    e.setCancelled(true);
                    if (bluePlayers.contains(p.getUniqueId())){
                        if (blue_gold >= 10){
                            blue_gold-=10;
                            p.getInventory().addItem(new ItemStack(Material.STONE_SWORD));
                        }
                    } else if (greenPlayers.contains(p.getUniqueId())){
                        if (green_gold >= 10){
                            green_gold-=10;
                            p.getInventory().addItem(new ItemStack(Material.STONE_SWORD));
                        }
                    } else if (redPlayers.contains(p.getUniqueId())){
                        if (red_gold >= 10){
                            red_gold-=10;
                            p.getInventory().addItem(new ItemStack(Material.STONE_SWORD));
                        }
                    } else if (yellowPlayers.contains(p.getUniqueId())){
                        if (yellow_gold >= 10){
                            yellow_gold-=10;
                            p.getInventory().addItem(new ItemStack(Material.STONE_SWORD));
                        }
                    }
                    break;
                case IRON_SWORD:
                    e.setCancelled(true);
                    if (bluePlayers.contains(p.getUniqueId())){
                        if (blue_gold >= 30){
                            blue_gold-=30;
                            p.getInventory().addItem(new ItemStack(Material.IRON_SWORD));
                        }
                    } else if (greenPlayers.contains(p.getUniqueId())){
                        if (green_gold >= 30){
                            green_gold-=30;
                            p.getInventory().addItem(new ItemStack(Material.IRON_SWORD));
                        }
                    } else if (redPlayers.contains(p.getUniqueId())){
                        if (red_gold >= 30){
                            red_gold-=30;
                            p.getInventory().addItem(new ItemStack(Material.IRON_SWORD));
                        }
                    } else if (yellowPlayers.contains(p.getUniqueId())){
                        if (yellow_gold >= 30){
                            yellow_gold-=30;
                            p.getInventory().addItem(new ItemStack(Material.IRON_SWORD));
                        }
                    }
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
                        if (bluePlayers.contains(p.getUniqueId())) {
                            if (blue_gold >= 30) {
                                blue_gold -= 30;
                                Horse h = (Horse) p.getWorld().spawnEntity(p.getLocation(), EntityType.HORSE);
                                h.setOwner(p);
                                h.getInventory().setSaddle(new ItemStack(Material.SADDLE));
                            } else {
                                p.sendMessage(getMsg("insufficient-gold"));
                            }
                        } else if (greenPlayers.contains(p.getUniqueId())) {
                            if (green_gold >= 30) {
                                green_gold -= 30;
                                Horse h = (Horse) p.getWorld().spawnEntity(p.getLocation(), EntityType.HORSE);
                                h.setOwner(p);
                                h.getInventory().setSaddle(new ItemStack(Material.SADDLE));
                            } else {
                                p.sendMessage(getMsg("insufficient-gold"));
                            }
                        } else if (yellowPlayers.contains(p.getUniqueId())) {
                            if (yellow_gold >= 30) {
                                yellow_gold -= 30;
                                Horse h = (Horse) p.getWorld().spawnEntity(p.getLocation(), EntityType.HORSE);
                                h.setOwner(p);
                                h.getInventory().setSaddle(new ItemStack(Material.SADDLE));
                            } else {
                                p.sendMessage(getMsg("insufficient-gold"));
                            }
                        } else if (redPlayers.contains(p.getUniqueId())) {
                            if (red_gold >= 30) {
                                red_gold -= 30;
                                Horse h = (Horse) p.getWorld().spawnEntity(p.getLocation(), EntityType.HORSE);
                                h.setOwner(p);
                                h.getInventory().setSaddle(new ItemStack(Material.SADDLE));
                            } else {
                                p.sendMessage(getMsg("insufficient-gold"));
                            }
                        }
                    } else {
                        if (bluePlayers.contains(p.getUniqueId())) {
                            if (blue_gold >= 50) {
                                blue_gold -= 50;
                                Wolf w = (Wolf) p.getWorld().spawnEntity(p.getLocation(), EntityType.WOLF);
                                w.setOwner(p);
                                w.setCollarColor(DyeColor.BLUE);
                            } else {
                                p.sendMessage(getMsg("insufficient-gold"));
                            }
                        } else if (greenPlayers.contains(p.getUniqueId())) {
                            if (green_gold >= 50) {
                                green_gold -= 50;
                                Wolf w = (Wolf) p.getWorld().spawnEntity(p.getLocation(), EntityType.WOLF);
                                w.setOwner(p);
                                w.setCollarColor(DyeColor.GREEN);
                            } else {
                                p.sendMessage(getMsg("insufficient-gold"));
                            }
                        } else if (yellowPlayers.contains(p.getUniqueId())) {
                            if (yellow_gold >= 50) {
                                yellow_gold -= 50;
                                Wolf w = (Wolf) p.getWorld().spawnEntity(p.getLocation(), EntityType.WOLF);
                                w.setOwner(p);
                                w.setCollarColor(DyeColor.YELLOW);
                            } else {
                                p.sendMessage(getMsg("insufficient-gold"));
                            }
                        } else if (redPlayers.contains(p.getUniqueId())) {
                            if (red_gold >= 50) {
                                red_gold -= 50;
                                Wolf w = (Wolf) p.getWorld().spawnEntity(p.getLocation(), EntityType.WOLF);
                                w.setOwner(p);
                                w.setCollarColor(DyeColor.RED);
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
                        stuff(e.getWhoClicked(), archery, 300, 150, false, true, false);
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
                        stuff(e.getWhoClicked(), trifarrow, 375, 175, false, true, false);
                    } else if (e.getInventory().getName().equalsIgnoreCase("TrifArrow")) {
                        ItemStack i = new ItemStack(Material.ARROW);
                        ItemMeta im = i.getItemMeta();
                        im.setDisplayName("§cExplosive");
                        i.setItemMeta(im);
                        if (bluePlayers.contains(p.getUniqueId())){
                            if (blue_gold >= 20){
                                blue_gold-=20;
                                p.getInventory().addItem(i);
                            } else {
                                p.sendMessage(getMsg("insufficient-gold"));
                            }
                        } else if (greenPlayers.contains(p.getUniqueId())){
                            if (green_gold >= 20){
                                green_gold-=20;
                                p.getInventory().addItem(i);
                            } else {
                                p.sendMessage(getMsg("insufficient-gold"));
                            }
                        } else if (yellowPlayers.contains(p.getUniqueId())){
                            if (yellow_gold >= 20){
                                yellow_gold-=20;
                                p.getInventory().addItem(new ItemStack(i));
                            } else {
                                p.sendMessage(getMsg("insufficient-gold"));
                            }
                        } else if (redPlayers.contains(p.getUniqueId())){
                            if (red_gold >= 20){
                                red_gold-=20;
                                p.getInventory().addItem(new ItemStack(i));
                            } else {
                                p.sendMessage(getMsg("insufficient-gold"));
                            }
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
                    stuff(e.getWhoClicked(), stable, 200, 100, false, true, false);
                    break;
                case DIAMOND_CHESTPLATE:
                    e.setCancelled(true);
                    stuff(e.getWhoClicked(), armory, 300, 150, false, true, false);
                    break;
                case BREWING_STAND_ITEM:
                    e.setCancelled(true);
                    stuff(e.getWhoClicked(), laboratory, 300, 150, false, true, false);
                    break;
                case ENCHANTED_BOOK:
                    e.setCancelled(true);
                    stuff(e.getWhoClicked(), guild, 600, 300, false, false, true);
                    break;
                case EXP_BOTTLE:
                    e.setCancelled(true);
                    stuff(e.getWhoClicked(), training_center, 500, 250, false, false, true);
                    break;
            }
        }
    }

    private static void buy(Player p, Material material, Integer cantitate, Short sh, Integer price){
        if (bluePlayers.contains(p.getUniqueId())){
            if (blue_gold >= price){
                blue_gold-=price;
                p.getInventory().addItem(new ItemStack(material, cantitate, sh));
            } else {
                p.sendMessage(getMsg("insufficient-gold"));
            }
        } else if (greenPlayers.contains(p.getUniqueId())){
            if (green_gold >= price){
                green_gold-=price;
                p.getInventory().addItem(new ItemStack(material, cantitate, sh));
            } else {
                p.sendMessage(getMsg("insufficient-gold"));
            }
        } else if (yellowPlayers.contains(p.getUniqueId())){
            if (yellow_gold >= price){
                yellow_gold-=price;
                p.getInventory().addItem(new ItemStack(material, cantitate, sh));
            } else {
                p.sendMessage(getMsg("insufficient-gold"));
            }
        } else if (redPlayers.contains(p.getUniqueId())){
            if (red_gold >= price){
                red_gold-=price;
                p.getInventory().addItem(new ItemStack(material, cantitate, sh));
            } else {
                p.sendMessage(getMsg("insufficient-gold"));
            }
        }
    }

    private static void voteAge(HumanEntity p, Integer wood, Integer stone, Integer age){
        if (age > 4) return;
        if (bluePlayers.contains(p.getUniqueId())){
            if (blue_change_age) return;
            if (vote_in_progress.contains(blue_team)) {
                p.sendMessage(getMsg("cant-vote"));
                return;
            }
            if (blue_wood >= wood && blue_stone >= stone) {
                new Vote(bluePlayers, age_string, p.getUniqueId(), blue_team, wood, stone, age_string);
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
        } else if (greenPlayers.contains(p.getUniqueId())){
            if (green_change_age) return;
            if (vote_in_progress.contains(green_team)) {
                p.sendMessage(getMsg("cant-vote"));
                return;
            }
            if (green_wood >= wood && green_stone >= stone) {
                new Vote(greenPlayers, age_string, p.getUniqueId(), green_team, wood, stone, age_string);
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
        } else if (yellowPlayers.contains(p.getUniqueId())){
            if (yellow_change_age) return;
            if (vote_in_progress.contains(yellow_team)) {
                p.sendMessage(getMsg("cant-vote"));
                return;
            }
            if (yellow_wood >= wood && yellow_stone >= stone) {
                new Vote(yellowPlayers, age_string, p.getUniqueId(), yellow_team, wood, stone, age_string);
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
        } else if (redPlayers.contains(p.getUniqueId())){
            if (red_change_age) return;
            if (vote_in_progress.contains(red_team)) {
                p.sendMessage(getMsg("cant-vote"));
                return;
            }
            if (red_wood >= wood && red_stone >= stone) {
                new Vote(redPlayers, age_string, p.getUniqueId(), red_team, wood, stone, age_string);
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

    private static void stuff(HumanEntity p, String building, Integer wood, Integer stone, boolean small, boolean medium, boolean large) {
        if (construct_in_inv.containsKey(p.getUniqueId())) {
            p.sendMessage(getMsg("having-construct"));
            return;
        }
        if (bluePlayers.contains(p.getUniqueId())) {
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
                new Vote(bluePlayers, getMsg("forum." + building + ".displayname"), p.getUniqueId(), blue_team, wood, stone, building);
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
        } else if (greenPlayers.contains(p.getUniqueId())) {
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
                new Vote(greenPlayers, getMsg("forum." + building + ".displayname"), p.getUniqueId(), green_team, wood, stone, building);
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
        } else if (yellowPlayers.contains(p.getUniqueId())) {
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
                new Vote(yellowPlayers, getMsg("forum." + building + ".displayname"), p.getUniqueId(), yellow_team, wood, stone, building);
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
        } else if (redPlayers.contains(p.getUniqueId())) {
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
                new Vote(redPlayers, getMsg("forum." + building + ".displayname"), p.getUniqueId(), red_team, wood, stone, building);
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
