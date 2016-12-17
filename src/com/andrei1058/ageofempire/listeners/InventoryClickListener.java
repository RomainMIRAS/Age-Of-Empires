package com.andrei1058.ageofempire.listeners;

import com.andrei1058.ageofempire.game.Status;
import com.andrei1058.ageofempire.game.Vote;

import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

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
        if (e.getCurrentItem().getItemMeta().getDisplayName() != null) {
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
