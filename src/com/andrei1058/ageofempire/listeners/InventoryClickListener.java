package com.andrei1058.ageofempire.listeners;

import com.andrei1058.ageofempire.game.Status;
import com.andrei1058.ageofempire.game.Vote;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import static com.andrei1058.ageofempire.Main.*;
import static com.andrei1058.ageofempire.configuration.Messages.getMsg;
import static com.andrei1058.ageofempire.game.Buildings.*;

public class InventoryClickListener implements Listener {

    @EventHandler
    public void i(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        if (SETUP) return;
        if (STATUS != Status.PLAYING) {
            e.setCancelled(true);
        }
        if (e.getCurrentItem() == null) return;
        if (e.getCurrentItem().getType() == Material.SKULL_ITEM){
            e.setCancelled(true);
        }
        if (e.getCurrentItem().getType() == Material.ANVIL){
            e.setCancelled(true);
            if (construct_in_inv.containsKey(e.getWhoClicked().getUniqueId())){
                e.getWhoClicked().sendMessage(getMsg("having-construct"));
                return;
            }
            if (bluePlayers.contains(p.getUniqueId())){
                if (built_forge.contains(blue_team)){
                    p.sendMessage(getMsg("already-built"));
                    return;
                }
                if (hasBuild(vote_in_progress, blue_team)){
                    p.sendMessage(getMsg("cant-vote"));
                    return;
                }
                if (blue_wood >= 150 && blue_stone >= 75 ){
                    new Vote(bluePlayers, getMsg("forum.forge.displayname"), p.getUniqueId(), blue_team, 150, 75, forge);
                } else {
                    p.sendMessage(getMsg("insufficient-resources").replace("{wood}", String.valueOf(blue_wood-150)).replace("{stone}", String.valueOf(blue_stone-75)));
                }
            } else if (greenPlayers.contains(p.getUniqueId())){
                if (built_forge.contains(green_team)){
                    p.sendMessage(getMsg("already-built"));
                    return;
                }
                if (hasBuild(vote_in_progress, green_team)){
                    p.sendMessage(getMsg("cant-vote"));
                    return;
                }
                if (green_wood >= 150 && green_stone >= 75 ){
                    new Vote(greenPlayers, getMsg("forum.forge.displayname"), p.getUniqueId(), green_team, 150, 75, forge);
                } else {
                    p.sendMessage(getMsg("insufficient-resources").replace("{wood}", String.valueOf(green_wood-150)).replace("{stone}", String.valueOf(green_stone-75)));
                }
            } else if (yellowPlayers.contains(p.getUniqueId())){
                if (built_forge.contains(yellow_team)){
                    p.sendMessage(getMsg("already-built"));
                    return;
                }
                if (hasBuild(vote_in_progress, yellow_team)){
                    p.sendMessage(getMsg("cant-vote"));
                    return;
                }
                if (yellow_wood >= 150 && yellow_stone >= 75 ){
                    new Vote(yellowPlayers, getMsg("forum.forge.displayname"), p.getUniqueId(), yellow_team, 150, 75, forge);
                } else {
                    p.sendMessage(getMsg("insufficient-resources").replace("{wood}", String.valueOf(yellow_wood-150)).replace("{stone}", String.valueOf(yellow_stone-75)));
                }
            } else if (redPlayers.contains(p.getUniqueId())){
                if (built_forge.contains(red_team)){
                    p.sendMessage(getMsg("already-built"));
                    return;
                }
                if (hasBuild(vote_in_progress, red_team)){
                    p.sendMessage(getMsg("cant-vote"));
                    return;
                }
                if (red_wood >= 150 && red_stone >= 75 ){
                    new Vote(redPlayers, getMsg("forum.forge.displayname"), p.getUniqueId(), red_team, 150, 75, forge);
                } else {
                    p.sendMessage(getMsg("insufficient-resources").replace("{wood}", String.valueOf(red_wood-150)).replace("{stone}", String.valueOf(red_stone-75)));
                }
            }
        }
        if (e.getCurrentItem().getType() == Material.MOB_SPAWNER){
            if (e.getCurrentItem().getItemMeta().getDisplayName() == null) return;
            if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(getMsg("locked-slot"))){
                e.setCancelled(true);
            }
        }
        if (e.getCurrentItem().getType() == Material.PAPER){
            if (e.getCurrentItem().getItemMeta().getDisplayName() == null) return;
            if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(getMsg("forum-paper"))){
                e.setCancelled(true);
            }
        }
        if (e.getCurrentItem().getType() == Material.SLIME_BALL){
            if (e.getCurrentItem().getItemMeta().getDisplayName() != null){
                if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(getMsg("validate-vote"))){
                    e.setCancelled(true);
                }
            }
        }
    }
}
