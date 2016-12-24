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

import static com.andrei1058.ageofempire.Main.*;
import static com.andrei1058.ageofempire.configuration.Messages.getMsg;
import static com.andrei1058.ageofempire.game.Buildings.*;

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
                       stuff(e.getWhoClicked(), forge, 150, 75);
                    break;
                case WHEAT:
                    e.setCancelled(true);
                    stuff(e.getWhoClicked(), mill, 150, 75);
                    break;
                case DIAMOND_PICKAXE:
                    e.setCancelled(true);
                    stuff(e.getWhoClicked(), stone_mine, 150, 75);
                    break;
                case GOLD_ORE:
                    e.setCancelled(true);
                    stuff(e.getWhoClicked(), gold_mine, 150, 100);
                    break;
                case DIAMOND_AXE:
                    e.setCancelled(true);
                    stuff(e.getWhoClicked(), sawmill, 150, 75);
                    break;
                case WORKBENCH:
                    e.setCancelled(true);
                    stuff(e.getWhoClicked(), workshop, 100, 50);
                    break;
                case EMERALD:
                    e.setCancelled(true);
                    stuff(e.getWhoClicked(), market, 100, 50);
                    break;
                case BONE:
                    e.setCancelled(true);
                    stuff(e.getWhoClicked(), kennel, 100, 50);
                    break;
                case TNT:
                    e.setCancelled(true);
                    if (e.getInventory().getName().equalsIgnoreCase("Forum")){
                        stuff(e.getWhoClicked(), sabotage, 100, 50);
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
                    if (bluePlayers.contains(p.getUniqueId())){
                        if (blue_gold >= 50){
                            blue_gold-=50;
                            Wolf w = (Wolf) p.getWorld().spawnEntity(p.getLocation(), EntityType.WOLF);
                            w.setOwner(p);
                            w.setCollarColor(DyeColor.BLUE);
                        } else {
                            p.sendMessage(getMsg("insufficient-gold"));
                        }
                    } else if (greenPlayers.contains(p.getUniqueId())){
                        if (green_gold >= 50){
                            green_gold-=50;
                            Wolf w = (Wolf) p.getWorld().spawnEntity(p.getLocation(), EntityType.WOLF);
                            w.setOwner(p);
                            w.setCollarColor(DyeColor.GREEN);
                        } else {
                            p.sendMessage(getMsg("insufficient-gold"));
                        }
                    } else if (yellowPlayers.contains(p.getUniqueId())){
                        if (yellow_gold >= 50){
                            yellow_gold-=50;
                            Wolf w = (Wolf) p.getWorld().spawnEntity(p.getLocation(), EntityType.WOLF);
                            w.setOwner(p);
                            w.setCollarColor(DyeColor.YELLOW);
                        } else {
                            p.sendMessage(getMsg("insufficient-gold"));
                        }
                    } else if (redPlayers.contains(p.getUniqueId())){
                        if (red_gold >= 50){
                            red_gold-=50;
                            Wolf w = (Wolf) p.getWorld().spawnEntity(p.getLocation(), EntityType.WOLF);
                            w.setOwner(p);
                            w.setCollarColor(DyeColor.RED);
                        } else {
                            p.sendMessage(getMsg("insufficient-gold"));
                        }
                    }
                    break;
                default:
                    e.setCancelled(true);
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

    private static void stuff(HumanEntity p, String building, Integer wood, Integer stone) {
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
            if (blue_small_plots == 0) {
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
            if (green_small_plots == 0) {
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
            if (yellow_small_plots == 0) {
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
            if (red_small_plots == 0) {
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
