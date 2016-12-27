package com.andrei1058.ageofempire.listeners;

import com.andrei1058.ageofempire.configuration.Settings;
import com.andrei1058.ageofempire.game.Status;
import com.andrei1058.ageofempire.game.Vote;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static com.andrei1058.ageofempire.Main.*;
import static com.andrei1058.ageofempire.Misc.slotlocked;
import static com.andrei1058.ageofempire.configuration.Messages.getMsg;
import static com.andrei1058.ageofempire.game.Buildings.vote_in_progress;

public class PlayerInteractListener implements Listener {
    @EventHandler
    public void i(PlayerInteractEvent e){
        if (SETUP) return;
        if (STATUS == Status.STARTING || STATUS == Status.LOBBY) {
            if (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) {
                if (e.getPlayer().getItemInHand().getType() == Material.STAINED_GLASS_PANE){
                    if (help.contains(e.getPlayer().getUniqueId())) {
                        help.remove(e.getPlayer().getUniqueId());
                        ItemStack i = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)14);
                        ItemMeta itemMeta = i.getItemMeta();
                        itemMeta.setDisplayName(getMsg("help-item-off"));
                        i.setItemMeta(itemMeta);
                        e.getPlayer().getInventory().setItem(4, i);
                        e.getPlayer().sendMessage(PREFIX+" "+getMsg("help-item-off"));
                    } else {
                        help.add(e.getPlayer().getUniqueId());
                        ItemStack i = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)5);
                        ItemMeta itemMeta = i.getItemMeta();
                        itemMeta.setDisplayName(getMsg("help-item-on"));
                        i.setItemMeta(itemMeta);
                        e.getPlayer().getInventory().setItem(4, i);
                        e.getPlayer().sendMessage(PREFIX+" "+getMsg("help-item-on"));
                    }
                }
                if (e.getPlayer().getItemInHand().getType() == Material.BED){
                    ByteArrayDataOutput out = ByteStreams.newDataOutput();
                    out.writeUTF("Connect");
                    out.writeUTF(Settings.load().getString("lobby-server"));
                    e.getPlayer().sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
                }
            }
        } else if (STATUS == Status.PRE_GAME){
            if (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) {
                if (e.getPlayer().getItemInHand().getType() == Material.STAINED_GLASS_PANE){
                    if (e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(getMsg("team-choosing.blue"))){
                        if (bluePlayers.size() < max_in_team){
                            if (Bukkit.getOnlinePlayers().size() > max_in_team*3){
                                if (bluePlayers.size() <= greenPlayers.size() || bluePlayers.size() <= yellowPlayers.size() || bluePlayers.size() <= redPlayers.size()){
                                    if (redPlayers.contains(e.getPlayer().getUniqueId())){
                                        redPlayers.remove(e.getPlayer().getUniqueId());
                                    } else if (yellowPlayers.contains(e.getPlayer().getUniqueId())){
                                        yellowPlayers.remove(e.getPlayer().getUniqueId());
                                    } else if (greenPlayers.contains(e.getPlayer().getUniqueId())){
                                        greenPlayers.remove(e.getPlayer().getUniqueId());
                                    }
                                    bluePlayers.add(e.getPlayer().getUniqueId());
                                    e.getPlayer().sendMessage(getMsg("team-choosing.blue-join"));
                                } else {
                                    e.getPlayer().sendMessage(getMsg("team-choosing.unbalanced-teams"));
                                }
                            } else if (Bukkit.getOnlinePlayers().size() > max_in_team*2){
                                if (bluePlayers.size() <= greenPlayers.size() || bluePlayers.size() <= yellowPlayers.size()){
                                    if (yellowPlayers.contains(e.getPlayer().getUniqueId())){
                                        yellowPlayers.remove(e.getPlayer().getUniqueId());
                                    } else if (greenPlayers.contains(e.getPlayer().getUniqueId())){
                                        greenPlayers.remove(e.getPlayer().getUniqueId());
                                    }
                                    bluePlayers.add(e.getPlayer().getUniqueId());
                                    e.getPlayer().sendMessage(getMsg("team-choosing.blue-join"));
                                } else {
                                    e.getPlayer().sendMessage(getMsg("team-choosing.unbalanced-teams"));
                                }
                            } else if (Bukkit.getOnlinePlayers().size() >= max_in_team){
                                if (bluePlayers.size() <= greenPlayers.size()){
                                    if (redPlayers.contains(e.getPlayer().getUniqueId())){
                                        redPlayers.remove(e.getPlayer().getUniqueId());
                                    } else if (yellowPlayers.contains(e.getPlayer().getUniqueId())){
                                        yellowPlayers.remove(e.getPlayer().getUniqueId());
                                    } else if (greenPlayers.contains(e.getPlayer().getUniqueId())){
                                        greenPlayers.remove(e.getPlayer().getUniqueId());
                                    }
                                    bluePlayers.add(e.getPlayer().getUniqueId());
                                    e.getPlayer().sendMessage(getMsg("team-choosing.blue-join"));
                                } else {
                                    e.getPlayer().sendMessage(getMsg("team-choosing.unbalanced-teams"));
                                }
                            }
                        } else {
                            e.getPlayer().sendMessage(getMsg("team-choosing.unbalanced-teams"));
                        }
                    } else if (e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(getMsg("team-choosing.red"))){
                        if (redPlayers.size() < max_in_team){
                            if (Bukkit.getOnlinePlayers().size() > max_in_team*3){
                                if (redPlayers.size() <= greenPlayers.size() || redPlayers.size() <= yellowPlayers.size() || redPlayers.size() <= bluePlayers.size()){
                                    if (bluePlayers.contains(e.getPlayer().getUniqueId())){
                                        bluePlayers.remove(e.getPlayer().getUniqueId());
                                    } else if (yellowPlayers.contains(e.getPlayer().getUniqueId())){
                                        yellowPlayers.remove(e.getPlayer().getUniqueId());
                                    } else if (greenPlayers.contains(e.getPlayer().getUniqueId())){
                                        greenPlayers.remove(e.getPlayer().getUniqueId());
                                    }
                                    redPlayers.add(e.getPlayer().getUniqueId());
                                    e.getPlayer().sendMessage(getMsg("team-choosing.red-join"));
                                } else {
                                    e.getPlayer().sendMessage(getMsg("team-choosing.unbalanced-teams"));
                                }
                            }
                        } else {
                            e.getPlayer().sendMessage(getMsg("team-choosing.unbalanced-teams"));
                        }
                    } else if (e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(getMsg("team-choosing.yellow"))){
                        if (yellowPlayers.size() < max_in_team){
                            if (Bukkit.getOnlinePlayers().size() > max_in_team*3){
                                if (yellowPlayers.size() <= greenPlayers.size() || yellowPlayers.size() <= bluePlayers.size() || yellowPlayers.size() <= redPlayers.size()){
                                    if (redPlayers.contains(e.getPlayer().getUniqueId())){
                                        redPlayers.remove(e.getPlayer().getUniqueId());
                                    } else if (bluePlayers.contains(e.getPlayer().getUniqueId())){
                                        bluePlayers.remove(e.getPlayer().getUniqueId());
                                    } else if (greenPlayers.contains(e.getPlayer().getUniqueId())){
                                        greenPlayers.remove(e.getPlayer().getUniqueId());
                                    }
                                    yellowPlayers.add(e.getPlayer().getUniqueId());
                                    e.getPlayer().sendMessage(getMsg("team-choosing.yellow-join"));
                                } else {
                                    e.getPlayer().sendMessage(getMsg("team-choosing.unbalanced-teams"));
                                }
                            } else if (Bukkit.getOnlinePlayers().size() > max_in_team*2){
                                if (yellowPlayers.size() <= greenPlayers.size() || yellowPlayers.size() <= bluePlayers.size()){
                                    if (bluePlayers.contains(e.getPlayer().getUniqueId())){
                                        bluePlayers.remove(e.getPlayer().getUniqueId());
                                    } else if (greenPlayers.contains(e.getPlayer().getUniqueId())){
                                        greenPlayers.remove(e.getPlayer().getUniqueId());
                                    }
                                    yellowPlayers.add(e.getPlayer().getUniqueId());
                                    e.getPlayer().sendMessage(getMsg("team-choosing.yellow-join"));
                                } else {
                                    e.getPlayer().sendMessage(getMsg("team-choosing.unbalanced-teams"));
                                }
                            }
                        } else {
                            e.getPlayer().sendMessage(getMsg("team-choosing.unbalanced-teams"));
                        }
                    } else if (e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(getMsg("team-choosing.green"))){
                        if (greenPlayers.size() < max_in_team){
                            if (Bukkit.getOnlinePlayers().size() > max_in_team*3){
                                if (greenPlayers.size() <= bluePlayers.size() || greenPlayers.size() <= yellowPlayers.size() || greenPlayers.size() <= redPlayers.size()){
                                    if (redPlayers.contains(e.getPlayer().getUniqueId())){
                                        redPlayers.remove(e.getPlayer().getUniqueId());
                                    } else if (yellowPlayers.contains(e.getPlayer().getUniqueId())){
                                        yellowPlayers.remove(e.getPlayer().getUniqueId());
                                    } else if (bluePlayers.contains(e.getPlayer().getUniqueId())){
                                        bluePlayers.remove(e.getPlayer().getUniqueId());
                                    }
                                    greenPlayers.add(e.getPlayer().getUniqueId());
                                    e.getPlayer().sendMessage(getMsg("team-choosing.green-join"));
                                } else {
                                    e.getPlayer().sendMessage(getMsg("team-choosing.unbalanced-teams"));
                                }
                            } else if (Bukkit.getOnlinePlayers().size() > max_in_team*2){
                                if (greenPlayers.size() <= bluePlayers.size() || greenPlayers.size() <= yellowPlayers.size()){
                                    if (bluePlayers.contains(e.getPlayer().getUniqueId())){
                                        bluePlayers.remove(e.getPlayer().getUniqueId());
                                    } else if (yellowPlayers.contains(e.getPlayer().getUniqueId())) {
                                        yellowPlayers.remove(e.getPlayer().getUniqueId());
                                    }
                                    greenPlayers.add(e.getPlayer().getUniqueId());
                                    e.getPlayer().sendMessage(getMsg("team-choosing.green-join"));
                                } else {
                                    e.getPlayer().sendMessage(getMsg("team-choosing.unbalanced-teams"));
                                }
                            } else if (Bukkit.getOnlinePlayers().size() >= max_in_team){
                                if (greenPlayers.size() <= bluePlayers.size()){
                                    if (bluePlayers.contains(e.getPlayer().getUniqueId())){
                                        bluePlayers.remove(e.getPlayer().getUniqueId());
                                    }
                                    greenPlayers.add(e.getPlayer().getUniqueId());
                                    e.getPlayer().sendMessage(getMsg("team-choosing.green-join"));
                                } else {
                                    e.getPlayer().sendMessage(getMsg("team-choosing.unbalanced-teams"));
                                }
                            }
                        } else {
                            e.getPlayer().sendMessage(getMsg("team-choosing.unbalanced-teams"));
                        }
                    }
                }
            }
        } else if (STATUS == Status.PLAYING){
            if ((e.getAction() == Action.RIGHT_CLICK_AIR) || (e.getAction() == Action.RIGHT_CLICK_BLOCK)){
                if (e.getPlayer().getItemInHand().getType() == Material.PAPER){
                    if (e.getPlayer().getItemInHand().getItemMeta().getDisplayName() != null){
                        if (e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(getMsg("forum-paper"))){
                            if (bluePlayers.contains(e.getPlayer().getUniqueId())){
                                e.getPlayer().openInventory(PlayerInteractEntityListener.forum(blue_team));
                            } else if (greenPlayers.contains(e.getPlayer().getUniqueId())){
                                e.getPlayer().openInventory(PlayerInteractEntityListener.forum(green_team));
                            } else if (yellowPlayers.contains(e.getPlayer().getUniqueId())){
                                e.getPlayer().openInventory(PlayerInteractEntityListener.forum(yellow_team));
                            } else if (redPlayers.contains(e.getPlayer().getUniqueId())){
                                e.getPlayer().openInventory(PlayerInteractEntityListener.forum(red_team));
                            }
                        }
                    }
                } else if (e.getPlayer().getItemInHand().getType() == Material.SLIME_BALL){
                    if (e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(getMsg("validate-vote"))) {
                        if (bluePlayers.contains(e.getPlayer().getUniqueId())){
                            if (vote_in_progress.contains(blue_team)){
                                Vote.byTeam(blue_team).addVote();
                                e.getPlayer().getInventory().setItem(8, slotlocked());
                            }
                        } else if (greenPlayers.contains(e.getPlayer().getUniqueId())){
                            if (vote_in_progress.contains(green_team)){
                                Vote.byTeam(green_team).addVote();
                                e.getPlayer().getInventory().setItem(8, slotlocked());
                            }
                        } else if (yellowPlayers.contains(e.getPlayer().getUniqueId())){
                            if (vote_in_progress.contains(yellow_team)){
                                Vote.byTeam(yellow_team).addVote();
                                e.getPlayer().getInventory().setItem(8, slotlocked());
                            }
                        } else if (redPlayers.contains(e.getPlayer().getUniqueId())){
                            if (vote_in_progress.contains(red_team)){
                                Vote.byTeam(red_team).addVote();
                                e.getPlayer().getInventory().setItem(8, slotlocked());
                            }
                        }
                    }
                }
            }
        }
    }
}
