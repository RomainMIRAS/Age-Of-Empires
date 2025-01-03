package com.andrei1058.ageofempire.listeners;

import com.andrei1058.ageofempire.Main;
import com.andrei1058.ageofempire.game.Kits;
import com.andrei1058.ageofempire.game.OreHologram;
import com.andrei1058.ageofempire.game.Status;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

import static com.andrei1058.ageofempire.Main.*;
import static com.andrei1058.ageofempire.configuration.Messages.getMsg;

public class BlockBreakListener implements Listener {

    private static ArrayList<Player> woodAnnounced = new ArrayList();
    private static ArrayList<Player> goldAnnounced = new ArrayList<>();
    private static ArrayList<Player> stoneAnnounced = new ArrayList<>();

    public final static int BASIC_WOOD_GAIN = 5;
    public final static int BASIC_STONE_GAIN = 3;
    public final static int BASIC_GOLD_GAIN = 2;

    @EventHandler
    public void b(BlockBreakEvent e){
        if (SETUP) return;
        if (STATUS == Status.PLAYING)
            switch (e.getBlock().getType()){
                case LEAVES:
                    if (placedBlocks.contains(e.getBlock())){
                        placedBlocks.remove(e.getBlock());
                    }
                    break;
                case LOG:
                    if (e.getBlock().getData() == 13 || e.getBlock().getData() == 1){
                        e.setCancelled(true);
                        e.getBlock().setType(Material.AIR);
                        if (help.contains(e.getPlayer())){
                            if (!(woodAnnounced.contains(e.getPlayer()))){
                                e.getPlayer().sendMessage(getMsg("help.cutting-wood"));
                                woodAnnounced.add(e.getPlayer());
                            }
                            if (!goldAnnounced.contains(e.getPlayer())){
                                e.getPlayer().sendMessage(getMsg("help.gold-stuff"));
                                goldAnnounced.add(e.getPlayer());
                            }
                        }
                        int gold;
                        Random r = new Random();
                        gold = BASIC_GOLD_GAIN;
                        new OreHologram(e.getBlock().getLocation(), gold, false);

                        double job_multiplier = 1;
                        if (kits.get(e.getPlayer()) == Kits.LUMBERJACK){
                            job_multiplier = 1.5;
                        }

                        if (bluePlayers.contains(e.getPlayer())){
                            blue_wood += BASIC_WOOD_GAIN * job_multiplier;
                            Main.gold.replace(e.getPlayer(), Main.gold.get(e.getPlayer())+gold);
                        } else if (greenPlayers.contains(e.getPlayer())){
                            green_wood += BASIC_WOOD_GAIN * job_multiplier;
                            Main.gold.replace(e.getPlayer(), Main.gold.get(e.getPlayer())+gold);
                        } else if (yellowPlayers.contains(e.getPlayer())){
                            yellow_wood += BASIC_WOOD_GAIN * job_multiplier;
                            Main.gold.replace(e.getPlayer(), Main.gold.get(e.getPlayer())+gold);
                        } else if (redPlayers.contains(e.getPlayer())){
                            red_wood += BASIC_WOOD_GAIN * job_multiplier;
                            Main.gold.replace(e.getPlayer(), Main.gold.get(e.getPlayer())+gold);
                        }
                        e.getPlayer().getWorld().playSound(e.getPlayer().getLocation(), nms.levelUp(), 1,1);
                    } else {
                        e.setCancelled(true);
                        e.getPlayer().sendMessage(getMsg("cant-break"));
                    }
                    break;
                case STONE:
                    if (e.getBlock().getData() == 5){
                        if (help.contains(e.getPlayer())){
                            if (!stoneAnnounced.contains(e.getPlayer())){
                                e.getPlayer().sendMessage(getMsg("help.stone"));
                                stoneAnnounced.add(e.getPlayer());
                            }
                            if (!goldAnnounced.contains(e.getPlayer())){
                                e.getPlayer().sendMessage(getMsg("help.gold-stuff"));
                                goldAnnounced.add(e.getPlayer());
                            }
                        }
                        e.getBlock().breakNaturally(new ItemStack(Material.STICK));
                        int gold = 0;
                        Random r = new Random();
                        gold = BASIC_GOLD_GAIN;
                        new OreHologram(e.getBlock().getLocation(), gold, true);

                        double job_multiplier = 1;
                        if (kits.get(e.getPlayer()) == Kits.MINER){
                            job_multiplier = 1.5;
                        }

                        if (bluePlayers.contains(e.getPlayer())){
                            blue_stone += BASIC_STONE_GAIN * job_multiplier;
                            Main.gold.replace(e.getPlayer(), Main.gold.get(e.getPlayer())+gold);
                        } else if (greenPlayers.contains(e.getPlayer())){
                            green_stone += BASIC_STONE_GAIN * job_multiplier;
                            Main.gold.replace(e.getPlayer(), Main.gold.get(e.getPlayer())+gold);
                        } else if (yellowPlayers.contains(e.getPlayer())){
                            yellow_stone += BASIC_STONE_GAIN * job_multiplier;
                            Main.gold.replace(e.getPlayer(), Main.gold.get(e.getPlayer())+gold);
                        } else if (redPlayers.contains(e.getPlayer())){
                            red_stone += BASIC_STONE_GAIN * job_multiplier;
                            Main.gold.replace(e.getPlayer(), Main.gold.get(e.getPlayer())+gold);
                        }
                        e.getPlayer().getWorld().playSound(e.getPlayer().getLocation(), nms.levelUp(), 1,1);
                    } else {
                        e.setCancelled(true);
                        e.getPlayer().sendMessage(getMsg("cant-break"));
                    }
                    break;
                case SEA_LANTERN:
                    if (xp.contains(new Location(Bukkit.getWorld(choosenMap), e.getBlock().getLocation().getBlockX(), e.getBlock().getLocation().getBlockY(), e.getBlock().getLocation().getBlockZ()))){
                        int gold;
                        e.getPlayer().giveExp(1);
                        Random r = new Random();
                        gold = BASIC_GOLD_GAIN;
                        if (bluePlayers.contains(e.getPlayer())){
                            blue_stone += BASIC_STONE_GAIN;
                            Main.gold.replace(e.getPlayer(), Main.gold.get(e.getPlayer())+gold);
                            blue_wood += BASIC_WOOD_GAIN;
                        } else if (greenPlayers.contains(e.getPlayer())){
                            green_stone += BASIC_STONE_GAIN;
                            Main.gold.replace(e.getPlayer(), Main.gold.get(e.getPlayer())+gold);
                            green_wood += BASIC_WOOD_GAIN;
                        } else if (yellowPlayers.contains(e.getPlayer())){
                            yellow_stone += BASIC_STONE_GAIN;
                            Main.gold.replace(e.getPlayer(), Main.gold.get(e.getPlayer())+gold);
                            yellow_wood += BASIC_WOOD_GAIN;
                        } else if (redPlayers.contains(e.getPlayer())){
                            red_stone += BASIC_STONE_GAIN;
                            Main.gold.replace(e.getPlayer(), Main.gold.get(e.getPlayer())+gold);
                            red_wood += BASIC_WOOD_GAIN;
                        }
                        new OreHologram(e.getBlock().getLocation(), gold, true);
                        new OreHologram(e.getBlock().getLocation(), gold, false);
                    }
                    e.setCancelled(true);
                    break;
                case MELON_BLOCK:
                    e.setCancelled(false);
                    break;
                case GRASS:
                case DIRT:
                case WOOD_DOUBLE_STEP:
                case SAND:
                case GRAVEL:
                case SPONGE:
                case GLASS:
                case LAPIS_BLOCK:
                case WOOL:
                case BRICK:
                case MOSSY_COBBLESTONE:
                case LEAVES_2:
                    if (placedBlocks.contains(e.getBlock())){
                        placedBlocks.remove(e.getBlock());
                    } else {
                        e.setCancelled(true);
                        e.getPlayer().sendMessage(getMsg("cant-break"));
                    }
                    break;
                default:
                    e.setCancelled(true);
                    e.getPlayer().sendMessage(getMsg("cant-break"));
                    break;
            }
    }
}
