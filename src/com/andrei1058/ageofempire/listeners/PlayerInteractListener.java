package com.andrei1058.ageofempire.listeners;

import com.andrei1058.ageofempire.configuration.MySQL;
import com.andrei1058.ageofempire.configuration.Settings;
import com.andrei1058.ageofempire.game.Status;
import com.andrei1058.ageofempire.game.Vote;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.mysql.fabric.xmlrpc.base.Array;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

import static com.andrei1058.ageofempire.Main.*;
import static com.andrei1058.ageofempire.Misc.slotlocked;
import static com.andrei1058.ageofempire.Misc.statsItem;
import static com.andrei1058.ageofempire.configuration.Messages.getMsg;
import static com.andrei1058.ageofempire.game.Buildings.vote_in_progress;

public class PlayerInteractListener implements Listener {
    @EventHandler
    public void i(PlayerInteractEvent e){
        if (SETUP) return;
        if (STATUS == Status.STARTING || STATUS == Status.LOBBY) {
            if (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) {
                if (nms.itemInHand(e.getPlayer()).getType() == Material.STAINED_GLASS_PANE){
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
                    return;
                }
                if (nms.itemInHand(e.getPlayer()).getType() == Material.BED){
                    ByteArrayDataOutput out = ByteStreams.newDataOutput();
                    out.writeUTF("Connect");
                    out.writeUTF(Settings.load().getString("lobby-server"));
                    e.getPlayer().sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
                    return;
                }
                if (nms.itemInHand(e.getPlayer()).equals(statsItem(e.getPlayer()))){
                    Inventory inv = Bukkit.createInventory(null, InventoryType.HOPPER, getMsg("stats.displayname"));
                    ArrayList stats = new MySQL().getStats(e.getPlayer().getUniqueId());
                    ItemStack i = new ItemStack(Material.GOLDEN_APPLE);
                    ItemMeta im = i.getItemMeta();
                    im.setDisplayName(getMsg("stats.wins").replace("%wins%", String.valueOf(stats.get(0))));
                    i.setItemMeta(im);
                    inv.setItem(0, i);

                    ItemStack i2 = new ItemStack(Material.IRON_SWORD);
                    ItemMeta im2 = i2.getItemMeta();
                    im2.setDisplayName(getMsg("stats.kills").replace("%kills%", String.valueOf(stats.get(2))));
                    i2.setItemMeta(im2);
                    inv.setItem(1, i2);

                    ItemStack i3 = new ItemStack(Material.YELLOW_FLOWER);
                    ItemMeta im3 = i3.getItemMeta();
                    im3.setDisplayName(getMsg("stats.deaths").replace("%deaths%", String.valueOf(stats.get(3))));
                    i3.setItemMeta(im3);
                    inv.setItem(2, i3);

                    ItemStack i4 = new ItemStack(Material.WATER_LILY);
                    ItemMeta im4 = i4.getItemMeta();
                    im4.setDisplayName(getMsg("stats.gamesplayed").replace("%games%", String.valueOf(stats.get(1))));
                    i4.setItemMeta(im4);
                    inv.setItem(3, i4);

                    ItemStack i5 = new ItemStack(Material.DIAMOND_SWORD);
                    ItemMeta im5 = i5.getItemMeta();
                    im5.setDisplayName(getMsg("stats.kingskilled").replace("%kings%", String.valueOf(stats.get(4))));
                    i5.setItemMeta(im5);
                    inv.setItem(4, i5);

                    e.getPlayer().openInventory(inv);
                }
            }
        } else if (STATUS == Status.PRE_GAME){
            if (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) {
                if (nms.itemInHand(e.getPlayer()).getType() == Material.STAINED_GLASS_PANE){
                    if (teamchoose.contains(e.getPlayer().getUniqueId())){
                        return;
                    }
                    if (nms.itemInHand(e.getPlayer()).getItemMeta().getDisplayName().equalsIgnoreCase(getMsg("team-choosing.blue"))){
                        if (bluePlayers.contains(e.getPlayer().getUniqueId())) return;
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
                                    teamchoose.add(e.getPlayer().getUniqueId());
                                    Bukkit.getScheduler().runTaskLater(plugin, () -> {teamchoose.remove(e.getPlayer().getUniqueId());}, 100L);
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
                                    teamchoose.add(e.getPlayer().getUniqueId());
                                    Bukkit.getScheduler().runTaskLater(plugin, () -> {teamchoose.remove(e.getPlayer().getUniqueId());}, 100L);
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
                                    teamchoose.add(e.getPlayer().getUniqueId());
                                    Bukkit.getScheduler().runTaskLater(plugin, () -> {teamchoose.remove(e.getPlayer().getUniqueId());}, 100L);
                                    bluePlayers.add(e.getPlayer().getUniqueId());
                                    e.getPlayer().sendMessage(getMsg("team-choosing.blue-join"));
                                } else {
                                    e.getPlayer().sendMessage(getMsg("team-choosing.unbalanced-teams"));
                                }
                            }
                        } else {
                            e.getPlayer().sendMessage(getMsg("team-choosing.unbalanced-teams"));
                        }
                    } else if (nms.itemInHand(e.getPlayer()).getItemMeta().getDisplayName().equalsIgnoreCase(getMsg("team-choosing.red"))){
                        if (redPlayers.contains(e.getPlayer().getUniqueId())) return;
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
                                    teamchoose.add(e.getPlayer().getUniqueId());
                                    Bukkit.getScheduler().runTaskLater(plugin, () -> {teamchoose.remove(e.getPlayer().getUniqueId());}, 100L);
                                    redPlayers.add(e.getPlayer().getUniqueId());
                                    e.getPlayer().sendMessage(getMsg("team-choosing.red-join"));
                                } else {
                                    e.getPlayer().sendMessage(getMsg("team-choosing.unbalanced-teams"));
                                }
                            }
                        } else {
                            e.getPlayer().sendMessage(getMsg("team-choosing.unbalanced-teams"));
                        }
                    } else if (nms.itemInHand(e.getPlayer()).getItemMeta().getDisplayName().equalsIgnoreCase(getMsg("team-choosing.yellow"))){
                        if (yellowPlayers.contains(e.getPlayer().getUniqueId())) return;
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
                                    teamchoose.add(e.getPlayer().getUniqueId());
                                    Bukkit.getScheduler().runTaskLater(plugin, () -> teamchoose.remove(e.getPlayer().getUniqueId()), 100L);
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
                                    teamchoose.add(e.getPlayer().getUniqueId());
                                    Bukkit.getScheduler().runTaskLater(plugin, () -> teamchoose.remove(e.getPlayer().getUniqueId()), 100L);
                                    yellowPlayers.add(e.getPlayer().getUniqueId());
                                    e.getPlayer().sendMessage(getMsg("team-choosing.yellow-join"));
                                } else {
                                    e.getPlayer().sendMessage(getMsg("team-choosing.unbalanced-teams"));
                                }
                            }
                        } else {
                            e.getPlayer().sendMessage(getMsg("team-choosing.unbalanced-teams"));
                        }
                    } else if (nms.itemInHand(e.getPlayer()).getItemMeta().getDisplayName().equalsIgnoreCase(getMsg("team-choosing.green"))){
                        if (greenPlayers.contains(e.getPlayer().getUniqueId())) return;
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
                                    teamchoose.add(e.getPlayer().getUniqueId());
                                    Bukkit.getScheduler().runTaskLater(plugin, () -> teamchoose.remove(e.getPlayer().getUniqueId()), 100L);
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
                                    teamchoose.add(e.getPlayer().getUniqueId());
                                    Bukkit.getScheduler().runTaskLater(plugin, () -> teamchoose.remove(e.getPlayer().getUniqueId()), 100L);
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
                                    teamchoose.add(e.getPlayer().getUniqueId());
                                    Bukkit.getScheduler().runTaskLater(plugin, () -> teamchoose.remove(e.getPlayer().getUniqueId()), 100L);
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
                if (nms.itemInHand(e.getPlayer()).getType() == Material.PAPER){
                    if (nms.itemInHand(e.getPlayer()).getItemMeta().getDisplayName() != null){
                        if (nms.itemInHand(e.getPlayer()).getItemMeta().getDisplayName().equalsIgnoreCase(getMsg("forum-paper"))){
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
                } else if (nms.itemInHand(e.getPlayer()).getType() == Material.SLIME_BALL){
                    if (nms.itemInHand(e.getPlayer()).getItemMeta().getDisplayName().equalsIgnoreCase(getMsg("validate-vote"))) {
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
