package com.andrei1058.ageofempire.listeners;

import com.andrei1058.ageofempire.game.Status;
import com.andrei1058.ageofempire.game.Vote;

import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
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
                    stuff(e.getWhoClicked(), sabotage, 100, 50);
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
                    if (bluePlayers.contains(p.getUniqueId())){
                        if (blue_gold >= 5){
                            blue_gold-=5;
                            p.getInventory().addItem(new ItemStack(Material.STONE_AXE));
                        }
                    } else if (greenPlayers.contains(p.getUniqueId())){
                        if (green_gold >= 5){
                            green_gold-=5;
                            p.getInventory().addItem(new ItemStack(Material.STONE_AXE));
                        }
                    } else if (redPlayers.contains(p.getUniqueId())){
                        if (red_gold >= 5){
                            red_gold-=5;
                            p.getInventory().addItem(new ItemStack(Material.STONE_AXE));
                        }
                    } else if (yellowPlayers.contains(p.getUniqueId())){
                        if (yellow_gold >= 5){
                            yellow_gold-=5;
                            p.getInventory().addItem(new ItemStack(Material.STONE_AXE));
                        }
                    }
                    break;
                case IRON_AXE:
                    e.setCancelled(true);
                    if (bluePlayers.contains(p.getUniqueId())){
                        if (blue_gold >= 15){
                            blue_gold-=15;
                            p.getInventory().addItem(new ItemStack(Material.IRON_AXE));
                        }
                    } else if (greenPlayers.contains(p.getUniqueId())){
                        if (green_gold >= 15){
                            green_gold-=15;
                            p.getInventory().addItem(new ItemStack(Material.IRON_AXE));
                        }
                    } else if (redPlayers.contains(p.getUniqueId())){
                        if (red_gold >= 15){
                            red_gold-=15;
                            p.getInventory().addItem(new ItemStack(Material.IRON_AXE));
                        }
                    } else if (yellowPlayers.contains(p.getUniqueId())){
                        if (yellow_gold >= 15){
                            yellow_gold-=15;
                            p.getInventory().addItem(new ItemStack(Material.IRON_AXE));
                        }
                    }
                    break;
                case BREAD:
                    e.setCancelled(true);
                    if (bluePlayers.contains(p.getUniqueId())){
                        if (blue_gold >= 10){
                            blue_gold-=10;
                            p.getInventory().addItem(new ItemStack(Material.BREAD, 5));
                        }
                    } else if (greenPlayers.contains(p.getUniqueId())){
                        if (green_gold >= 10){
                            green_gold-=10;
                            p.getInventory().addItem(new ItemStack(Material.BREAD, 5));
                        }
                    } else if (redPlayers.contains(p.getUniqueId())){
                        if (red_gold >= 10){
                            red_gold-=10;
                            p.getInventory().addItem(new ItemStack(Material.BREAD, 5));
                        }
                    } else if (yellowPlayers.contains(p.getUniqueId())){
                        if (yellow_gold >= 10){
                            yellow_gold-=10;
                            p.getInventory().addItem(new ItemStack(Material.BREAD, 5));
                        }
                    }
                    break;
                case POTATO:
                    e.setCancelled(true);
                    if (bluePlayers.contains(p.getUniqueId())){
                        if (blue_gold >= 15){
                            blue_gold-=15;
                            p.getInventory().addItem(new ItemStack(Material.POTATO, 5));
                        }
                    } else if (greenPlayers.contains(p.getUniqueId())){
                        if (green_gold >= 15){
                            green_gold-=15;
                            p.getInventory().addItem(new ItemStack(Material.POTATO, 5));
                        }
                    } else if (redPlayers.contains(p.getUniqueId())){
                        if (red_gold >= 15){
                            red_gold-=15;
                            p.getInventory().addItem(new ItemStack(Material.POTATO, 5));
                        }
                    } else if (yellowPlayers.contains(p.getUniqueId())){
                        if (yellow_gold >= 15){
                            yellow_gold-=15;
                            p.getInventory().addItem(new ItemStack(Material.POTATO, 5));
                        }
                    }
                    break;
                case COOKED_BEEF:
                    e.setCancelled(true);
                    if (bluePlayers.contains(p.getUniqueId())){
                        if (blue_gold >= 22){
                            blue_gold-=22;
                            p.getInventory().addItem(new ItemStack(Material.COOKED_BEEF, 5));
                        }
                    } else if (greenPlayers.contains(p.getUniqueId())){
                        if (green_gold >= 22){
                            green_gold-=22;
                            p.getInventory().addItem(new ItemStack(Material.COOKED_BEEF, 5));
                        }
                    } else if (redPlayers.contains(p.getUniqueId())){
                        if (red_gold >= 22){
                            red_gold-=22;
                            p.getInventory().addItem(new ItemStack(Material.COOKED_BEEF, 5));
                        }
                    } else if (yellowPlayers.contains(p.getUniqueId())){
                        if (yellow_gold >= 22){
                            yellow_gold-=22;
                            p.getInventory().addItem(new ItemStack(Material.COOKED_BEEF, 5));
                        }
                    }
                    break;
                case COOKED_CHICKEN:
                    e.setCancelled(true);
                    if (bluePlayers.contains(p.getUniqueId())){
                        if (blue_gold >= 20){
                            blue_gold-=20;
                            p.getInventory().addItem(new ItemStack(Material.COOKED_CHICKEN, 5));
                        }
                    } else if (greenPlayers.contains(p.getUniqueId())){
                        if (green_gold >= 20){
                            green_gold-=20;
                            p.getInventory().addItem(new ItemStack(Material.COOKED_CHICKEN, 5));
                        }
                    } else if (redPlayers.contains(p.getUniqueId())){
                        if (red_gold >= 20){
                            red_gold-=20;
                            p.getInventory().addItem(new ItemStack(Material.COOKED_CHICKEN, 5));
                        }
                    } else if (yellowPlayers.contains(p.getUniqueId())){
                        if (yellow_gold >= 20){
                            yellow_gold-=20;
                            p.getInventory().addItem(new ItemStack(Material.COOKED_CHICKEN, 5));
                        }
                    }
                    break;

                default:
                    e.setCancelled(true);
                    break;
            }
        }
        /*if (e.getCurrentItem().getType() == ANVIL){
            e.setCancelled(true);
            if (construct_in_inv.containsKey(e.getWhoClicked().getUniqueId())){
                e.getWhoClicked().sendMessage(getMsg("having-construct"));
                return;
            }
            if (bluePlayers.contains(p.getUniqueId())){
                if (blue_built.contains(forge)){
                    p.sendMessage(getMsg("already-built"));
                    return;
                }
                if (vote_in_progress.contains(blue_team)){
                    p.sendMessage(getMsg("cant-vote"));
                    return;
                }
                if (blue_small_plots == 0){
                    p.sendMessage(getMsg("cant-vote-full"));
                    return;
                }
                if (blue_wood >= 150 && blue_stone >= 75 ){
                    new Vote(bluePlayers, getMsg("forum.forge.displayname"), p.getUniqueId(), blue_team, 150, 75, forge);
                } else {
                    p.sendMessage(getMsg("insufficient-resources").replace("{wood}", String.valueOf(blue_wood-150)).replace("{stone}", String.valueOf(blue_stone-75)));
                }
            } else if (greenPlayers.contains(p.getUniqueId())){
                if (green_built.contains(forge)){
                    p.sendMessage(getMsg("already-built"));
                    return;
                }
                if (vote_in_progress.contains(green_team)){
                    p.sendMessage(getMsg("cant-vote"));
                    return;
                }
                if (green_small_plots == 0){
                    p.sendMessage(getMsg("cant-vote-full"));
                    return;
                }
                if (green_wood >= 150 && green_stone >= 75 ){
                    new Vote(greenPlayers, getMsg("forum.forge.displayname"), p.getUniqueId(), green_team, 150, 75, forge);
                } else {
                    p.sendMessage(getMsg("insufficient-resources").replace("{wood}", String.valueOf(green_wood-150)).replace("{stone}", String.valueOf(green_stone-75)));
                }
            } else if (yellowPlayers.contains(p.getUniqueId())){
                if (yellow_built.contains(forge)){
                    p.sendMessage(getMsg("already-built"));
                    return;
                }
                if (vote_in_progress.contains(yellow_team)){
                    p.sendMessage(getMsg("cant-vote"));
                    return;
                }
                if (yellow_small_plots == 0){
                    p.sendMessage(getMsg("cant-vote-full"));
                    return;
                }
                if (yellow_wood >= 150 && yellow_stone >= 75 ){
                    new Vote(yellowPlayers, getMsg("forum.forge.displayname"), p.getUniqueId(), yellow_team, 150, 75, forge);
                } else {
                    p.sendMessage(getMsg("insufficient-resources").replace("{wood}", String.valueOf(yellow_wood-150)).replace("{stone}", String.valueOf(yellow_stone-75)));
                }
            } else if (redPlayers.contains(p.getUniqueId())){
                if (red_built.contains(forge)){
                    p.sendMessage(getMsg("already-built"));
                    return;
                }
                if (vote_in_progress.contains(red_team)){
                    p.sendMessage(getMsg("cant-vote"));
                    return;
                }
                if (red_small_plots == 0){
                    p.sendMessage(getMsg("cant-vote-full"));
                    return;
                }
                if (red_wood >= 150 && red_stone >= 75 ){
                    new Vote(redPlayers, getMsg("forum.forge.displayname"), p.getUniqueId(), red_team, 150, 75, forge);
                } else {
                    p.sendMessage(getMsg("insufficient-resources").replace("{wood}", String.valueOf(red_wood-150)).replace("{stone}", String.valueOf(red_stone-75)));
                }
            }
        }*/

        /*if (e.getCurrentItem().getType() == MOB_SPAWNER){
            if (e.getCurrentItem().getItemMeta().getDisplayName() == null) return;
            if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(getMsg("locked-slot"))){
                e.setCancelled(true);
            }
        }
        if (e.getCurrentItem().getType() == PAPER){
            if (e.getCurrentItem().getItemMeta().getDisplayName() == null) return;
            if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(getMsg("forum-paper"))){
                e.setCancelled(true);
            }
        }
        if (e.getCurrentItem().getType() == SLIME_BALL){
            if (e.getCurrentItem().getItemMeta().getDisplayName() != null){
                if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(getMsg("validate-vote"))){
                    e.setCancelled(true);
                }
            }
        }*/
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
