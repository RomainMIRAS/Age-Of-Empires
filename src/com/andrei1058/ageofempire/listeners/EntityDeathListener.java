package com.andrei1058.ageofempire.listeners;

import com.andrei1058.ageofempire.game.Hologram;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.UUID;

import static com.andrei1058.ageofempire.Main.*;
import static com.andrei1058.ageofempire.configuration.Messages.getMsg;
import static com.andrei1058.ageofempire.game.Buildings.*;
import static com.andrei1058.ageofempire.listeners.PlayerQuitListener.checkWinner;

public class EntityDeathListener implements Listener {

    @EventHandler
    public void d(EntityDeathEvent e){
        if (SETUP) return;
        if (e.getEntity() instanceof Villager){
            Villager v = (Villager) e.getEntity();
            String killer = "";
            Player p = null;
            if (e.getEntity().getKiller() instanceof Player){
                p = e.getEntity().getKiller();
            } else if (e.getEntity().getKiller() instanceof Projectile){
                Projectile proj = (Projectile) e.getEntity().getKiller();
                p = (Player) proj.getShooter();
            } else if (e.getEntity().getKiller() instanceof Wolf){
                Wolf w = (Wolf) e.getEntity().getKiller();
                p = (Player) w.getOwner();
            } else {
                return;
            }
                if (bluePlayers.contains(p.getUniqueId())) {
                    killer = "§9Blue";
                } else if (greenPlayers.contains(p.getUniqueId())) {
                    killer = "§aGreen";
                } else if (yellowPlayers.contains(p.getUniqueId())) {
                    killer = "§eYellow";
                } else if (redPlayers.contains(p.getUniqueId())) {
                    killer = "§cRed";
                }
            if (v == blue_villager){
                Bukkit.broadcastMessage(getMsg("base-destroyed.blue").replace("{team}", killer));
                Bukkit.getScheduler().runTaskLater(plugin, ()-> {
                    try {
                        if (!bluePlayers.isEmpty()) {
                            for (UUID u : bluePlayers) {
                                if (u != null) {
                                    players.remove(u);
                                    bluePlayers.remove(u);
                                    Bukkit.getPlayer(u).getInventory().clear();
                                    Bukkit.getPlayer(u).setGameMode(GameMode.SPECTATOR);
                                }
                            }
                        }
                    } catch (Exception ex){}
                    checkWinner();
                }, 10L);
            } else if (v == green_villager){
                Bukkit.broadcastMessage(getMsg("base-destroyed.green").replace("{team}", killer));
                Bukkit.getScheduler().runTaskLater(plugin, ()-> {
                    try {
                        if (!greenPlayers.isEmpty()) {
                            for (UUID u : greenPlayers) {
                                if (u != null) {
                                    Bukkit.getPlayer(u).getInventory().clear();
                                    Bukkit.getPlayer(u).setGameMode(GameMode.SPECTATOR);
                                    players.remove(u);
                                    greenPlayers.remove(u);
                                }
                            }
                        }
                    } catch (Exception ex){}
                    checkWinner();
                }, 10L);
            } else if (v == yellow_villager){
                Bukkit.broadcastMessage(getMsg("base-destroyed.yellow").replace("{team}", killer));
                Bukkit.getScheduler().runTaskLater(plugin, ()-> {
                    try {
                        if (!yellowPlayers.isEmpty()) {
                            for (UUID u : yellowPlayers) {
                                if (u != null) {
                                    players.remove(u);
                                    yellowPlayers.remove(u);
                                    Bukkit.getPlayer(u).getInventory().clear();
                                    Bukkit.getPlayer(u).setGameMode(GameMode.SPECTATOR);
                                }
                            }
                        }
                    } catch (Exception ex){}
                    checkWinner();
                }, 10L);
            } else if (v == red_villager) {
                Bukkit.broadcastMessage(getMsg("base-destroyed.red").replace("{team}", killer));
                Bukkit.getScheduler().runTaskLater(plugin, ()-> {
                    try {
                        if (!redPlayers.isEmpty()) {
                            for (UUID u : redPlayers) {
                                if (u != null) {
                                    players.remove(u);
                                    redPlayers.remove(u);
                                    Bukkit.getPlayer(u).getInventory().clear();
                                    Bukkit.getPlayer(u).setGameMode(GameMode.SPECTATOR);
                                }
                            }
                        }
                    } catch (Exception ex){}
                    checkWinner();
                }, 10L);
            } else {
                if (v == blue_forge) {
                    Bukkit.broadcastMessage(getMsg("blue-building-explode").replace("{building}", getMsg("forum." + forge + ".displayname")));
                } else if (v == green_forge) {
                    Bukkit.broadcastMessage(getMsg("green-building-explode").replace("{building}", getMsg("forum." + forge + ".displayname")));
                } else if (v == yellow_forge) {
                    Bukkit.broadcastMessage(getMsg("yellow-building-explode").replace("{building}", getMsg("forum." + forge + ".displayname")));
                } else if (v == red_forge) {
                    Bukkit.broadcastMessage(getMsg("red-building-explode").replace("{building}", getMsg("forum." + forge + ".displayname")));
                } else if (v == blue_smine) {
                    Bukkit.broadcastMessage(getMsg("blue-building-explode").replace("{building}", getMsg("forum." + stone_mine + ".displayname")));
                } else if (v == green_smine) {
                    Bukkit.broadcastMessage(getMsg("green-building-explode").replace("{building}", getMsg("forum." + stone_mine + ".displayname")));
                } else if (v == yellow_smine) {
                    Bukkit.broadcastMessage(getMsg("yellow-building-explode").replace("{building}", getMsg("forum." + stone_mine + ".displayname")));
                } else if (v == red_smine) {
                    Bukkit.broadcastMessage(getMsg("red-building-explode").replace("{building}", getMsg("forum." + stone_mine + ".displayname")));
                } else if (v == blue_gmine) {
                    Bukkit.broadcastMessage(getMsg("blue-building-explode").replace("{building}", getMsg("forum." + gold_mine + ".displayname")));
                } else if (v == green_gmine) {
                    Bukkit.broadcastMessage(getMsg("green-building-explode").replace("{building}", getMsg("forum." + gold_mine + ".displayname")));
                } else if (v == yellow_gmine) {
                    Bukkit.broadcastMessage(getMsg("yellow-building-explode").replace("{building}", getMsg("forum." + gold_mine + ".displayname")));
                } else if (v == red_gmine) {
                    Bukkit.broadcastMessage(getMsg("red-building-explode").replace("{building}", getMsg("forum." + gold_mine + ".displayname")));
                } else if (v == blue_mill) {
                    Bukkit.broadcastMessage(getMsg("blue-building-explode").replace("{building}", getMsg("forum." + mill + ".displayname")));
                } else if (v == green_mill) {
                    Bukkit.broadcastMessage(getMsg("green-building-explode").replace("{building}", getMsg("forum." + mill + ".displayname")));
                } else if (v == yellow_mill) {
                    Bukkit.broadcastMessage(getMsg("yellow-building-explode").replace("{building}", getMsg("forum." + mill + ".displayname")));
                } else if (v == red_mill) {
                    Bukkit.broadcastMessage(getMsg("red-building-explode").replace("{building}", getMsg("forum." + mill + ".displayname")));
                } else if (v == blue_vsawmill) {
                    Bukkit.broadcastMessage(getMsg("blue-building-explode").replace("{building}", getMsg("forum." + sawmill + ".displayname")));
                } else if (v == green_vsawmill) {
                    Bukkit.broadcastMessage(getMsg("green-building-explode").replace("{building}", getMsg("forum." + sawmill + ".displayname")));
                } else if (v == yellow_vsawmill) {
                    Bukkit.broadcastMessage(getMsg("yellow-building-explode").replace("{building}", getMsg("forum." + sawmill + ".displayname")));
                } else if (v == red_vsawmill) {
                    Bukkit.broadcastMessage(getMsg("red-building-explode").replace("{building}", getMsg("forum." + sawmill + ".displayname")));
                } else if (v == blue_workshop) {
                    Bukkit.broadcastMessage(getMsg("blue-building-explode").replace("{building}", getMsg("forum." + workshop + ".displayname")));
                } else if (v == green_workshop) {
                    Bukkit.broadcastMessage(getMsg("green-building-explode").replace("{building}", getMsg("forum." + workshop + ".displayname")));
                } else if (v == yellow_workshop) {
                    Bukkit.broadcastMessage(getMsg("yellow-building-explode").replace("{building}", getMsg("forum." + workshop + ".displayname")));
                } else if (v == red_workshop) {
                    Bukkit.broadcastMessage(getMsg("red-building-explode").replace("{building}", getMsg("forum." + workshop + ".displayname")));
                } else if (v == blue_market) {
                    Bukkit.broadcastMessage(getMsg("blue-building-explode").replace("{building}", getMsg("forum." + market + ".displayname")));
                } else if (v == green_market) {
                    Bukkit.broadcastMessage(getMsg("green-building-explode").replace("{building}", getMsg("forum." + market + ".displayname")));
                } else if (v == yellow_market) {
                    Bukkit.broadcastMessage(getMsg("yellow-building-explode").replace("{building}", getMsg("forum." + market + ".displayname")));
                } else if (v == red_market) {
                    Bukkit.broadcastMessage(getMsg("red-building-explode").replace("{building}", getMsg("forum." + market + ".displayname")));
                } else if (v == blue_sabotage) {
                    Bukkit.broadcastMessage(getMsg("blue-building-explode").replace("{building}", getMsg("forum." + sabotage + ".displayname")));
                } else if (v == green_sabotage) {
                    Bukkit.broadcastMessage(getMsg("green-building-explode").replace("{building}", getMsg("forum." + sabotage + ".displayname")));
                } else if (v == yellow_sabotage) {
                    Bukkit.broadcastMessage(getMsg("yellow-building-explode").replace("{building}", getMsg("forum." + sabotage + ".displayname")));
                } else if (v == red_sabotage) {
                    Bukkit.broadcastMessage(getMsg("red-building-explode").replace("{building}", getMsg("forum." + sabotage + ".displayname")));
                } else if (v == blue_kennel) {
                    Bukkit.broadcastMessage(getMsg("blue-building-explode").replace("{building}", getMsg("forum." + kennel + ".displayname")));
                } else if (v == green_kennel) {
                    Bukkit.broadcastMessage(getMsg("green-building-explode").replace("{building}", getMsg("forum." + kennel + ".displayname")));
                } else if (v == yellow_kennel) {
                    Bukkit.broadcastMessage(getMsg("yellow-building-explode").replace("{building}", getMsg("forum." + kennel + ".displayname")));
                } else if (v == red_kennel) {
                    Bukkit.broadcastMessage(getMsg("red-building-explode").replace("{building}", getMsg("forum." + kennel + ".displayname")));
                } else if (v == blue_archery) {
                    Bukkit.broadcastMessage(getMsg("blue-building-explode").replace("{building}", getMsg("forum." + archery + ".displayname")));
                } else if (v == green_archery) {
                    Bukkit.broadcastMessage(getMsg("green-building-explode").replace("{building}", getMsg("forum." + archery + ".displayname")));
                } else if (v == red_archery) {
                    Bukkit.broadcastMessage(getMsg("red-building-explode").replace("{building}", getMsg("forum." + archery + ".displayname")));
                } else if (v == yellow_archery) {
                    Bukkit.broadcastMessage(getMsg("yellow-building-explode").replace("{building}", getMsg("forum." + archery + ".displayname")));
                } else if (v == blue_trifarrow) {
                    Bukkit.broadcastMessage(getMsg("blue-building-explode").replace("{building}", getMsg("forum." + trifarrow + ".displayname")));
                } else if (v == green_trifarrow) {
                    Bukkit.broadcastMessage(getMsg("green-building-explode").replace("{building}", getMsg("forum." + trifarrow + ".displayname")));
                } else if (v == yellow_trifarrow) {
                    Bukkit.broadcastMessage(getMsg("yellow-building-explode").replace("{building}", getMsg("forum." + trifarrow + ".displayname")));
                } else if (v == red_trifarrow) {
                    Bukkit.broadcastMessage(getMsg("red-building-explode").replace("{building}", getMsg("forum." + trifarrow + ".displayname")));
                } else if (v == blue_armory) {
                    Bukkit.broadcastMessage(getMsg("blue-building-explode").replace("{building}", getMsg("forum." + armory + ".displayname")));
                } else if (v == green_armory) {
                    Bukkit.broadcastMessage(getMsg("green-building-explode").replace("{building}", getMsg("forum." + armory + ".displayname")));
                } else if (v == yellow_armory) {
                    Bukkit.broadcastMessage(getMsg("yellow-building-explode").replace("{building}", getMsg("forum." + armory + ".displayname")));
                } else if (v == red_armory) {
                    Bukkit.broadcastMessage(getMsg("red-building-explode").replace("{building}", getMsg("forum." + armory + ".displayname")));
                } else if (v == blue_lab) {
                    Bukkit.broadcastMessage(getMsg("blue-building-explode").replace("{building}", getMsg("forum." + laboratory + ".displayname")));
                } else if (v == green_lab) {
                    Bukkit.broadcastMessage(getMsg("green-building-explode").replace("{building}", getMsg("forum." + laboratory + ".displayname")));
                } else if (v == yellow_lab) {
                    Bukkit.broadcastMessage(getMsg("yellow-building-explode").replace("{building}", getMsg("forum." + laboratory + ".displayname")));
                } else if (v == red_lab) {
                    Bukkit.broadcastMessage(getMsg("red-building-explode").replace("{building}", getMsg("forum." + laboratory + ".displayname")));
                } else if (v == blue_guild) {
                    Bukkit.broadcastMessage(getMsg("blue-building-explode").replace("{building}", getMsg("forum." + guild + ".displayname")));
                } else if (v == green_guild) {
                    Bukkit.broadcastMessage(getMsg("green-building-explode").replace("{building}", getMsg("forum." + guild + ".displayname")));
                } else if (v == yellow_guild) {
                    Bukkit.broadcastMessage(getMsg("yellow-building-explode").replace("{building}", getMsg("forum." + guild + ".displayname")));
                } else if (v == red_guild) {
                    Bukkit.broadcastMessage(getMsg("red-building-explode").replace("{building}", getMsg("forum." + guild + ".displayname")));
                } else if (v == blue_tcenter) {
                    Bukkit.broadcastMessage(getMsg("blue-building-explode").replace("{building}", getMsg("forum." + training_center + ".displayname")));
                    blue_xp = false;
                } else if (v == green_tcenter) {
                    Bukkit.broadcastMessage(getMsg("green-building-explode").replace("{building}", getMsg("forum." + training_center + ".displayname")));
                    green_xp = false;
                } else if (v == yellow_tcenter) {
                    Bukkit.broadcastMessage(getMsg("yellow-building-explode").replace("{building}", getMsg("forum." + training_center + ".displayname")));
                    yellow_xp = false;
                } else if (v == red_tcenter) {
                    Bukkit.broadcastMessage(getMsg("red-building-explode").replace("{building}", getMsg("forum." + training_center + ".displayname")));
                    red_xp = false;
                }
                Bukkit.getScheduler().runTaskLater(plugin, () -> Bukkit.getWorld(v.getWorld().getName()).createExplosion(v.getLocation(), 8), 20 * 15);
            }
            try {
                Hologram.get(v).remove();
            } catch (Exception ex){}
            Bukkit.getScheduler().runTaskLater(plugin, () -> checkWinner(), 10L);
        }
    }
}
