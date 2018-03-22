package com.andrei1058.ageofempire.listeners;

import com.andrei1058.ageofempire.Main;
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
                        gold = r.nextInt(2)+1;
                        new OreHologram(e.getBlock().getLocation(), gold, false);
                        if (bluePlayers.contains(e.getPlayer())){
                            blue_wood += 3;
                            Main.gold.replace(e.getPlayer(), Main.gold.get(e.getPlayer())+gold);
                        } else if (greenPlayers.contains(e.getPlayer())){
                            green_wood += 3;
                            Main.gold.replace(e.getPlayer(), Main.gold.get(e.getPlayer())+gold);
                        } else if (yellowPlayers.contains(e.getPlayer())){
                            yellow_wood += 3;
                            Main.gold.replace(e.getPlayer(), Main.gold.get(e.getPlayer())+gold);
                        } else if (redPlayers.contains(e.getPlayer())){
                            red_wood += 3;
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
                        gold = r.nextInt(2)+1;
                        new OreHologram(e.getBlock().getLocation(), gold, true);
                        if (bluePlayers.contains(e.getPlayer())){
                            blue_stone += 3;
                            Main.gold.replace(e.getPlayer(), Main.gold.get(e.getPlayer())+gold);
                        } else if (greenPlayers.contains(e.getPlayer())){
                            green_stone += 3;
                            Main.gold.replace(e.getPlayer(), Main.gold.get(e.getPlayer())+gold);
                        } else if (yellowPlayers.contains(e.getPlayer())){
                            yellow_stone += 3;
                            Main.gold.replace(e.getPlayer(), Main.gold.get(e.getPlayer())+gold);
                        } else if (redPlayers.contains(e.getPlayer())){
                            red_stone += 3;
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
                        gold = r.nextInt(2)+1;
                        if (bluePlayers.contains(e.getPlayer())){
                            blue_stone += 3;
                            Main.gold.replace(e.getPlayer(), Main.gold.get(e.getPlayer())+gold);
                            blue_wood += 3;
                        } else if (greenPlayers.contains(e.getPlayer())){
                            green_stone += 3;
                            Main.gold.replace(e.getPlayer(), Main.gold.get(e.getPlayer())+gold);
                            green_wood += 3;
                        } else if (yellowPlayers.contains(e.getPlayer())){
                            yellow_stone += 3;
                            Main.gold.replace(e.getPlayer(), Main.gold.get(e.getPlayer())+gold);
                            yellow_wood += 3;
                        } else if (redPlayers.contains(e.getPlayer())){
                            red_stone += 3;
                            Main.gold.replace(e.getPlayer(), Main.gold.get(e.getPlayer())+gold);
                            red_wood += 3;
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

           /* if (e.getBlock().getType() == Material.LEAVES || e.getBlock().getType() == Material.LEAVES_2){
                if (placedBlocks.contains(e.getBlock())){
                    placedBlocks.remove(e.getBlock());
                }
                return;
            }

        if (e.getBlock().getType() == Material.LOG && e.getBlock().getData() == 13 || e.getBlock().getType() == Material.LOG_2 && e.getBlock().getData() == 13
                || e.getBlock().getType() == Material.LOG && e.getBlock().getData() == 1){
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
            int gold = 0;
            Random r = new Random();
            gold = r.nextInt(2);
            new OreHologram(e.getBlock().getLocation(), gold, false);
            if (bluePlayers.contains(e.getPlayer())){
                blue_wood += 5;
                blue_gold += gold;
            } else if (greenPlayers.contains(e.getPlayer())){
                green_wood += 5;
                green_gold += gold;
            } else if (yellowPlayers.contains(e.getPlayer())){
                yellow_wood += 5;
                yellow_gold += gold;
            } else if (redPlayers.contains(e.getPlayer())){
                red_wood += 5;
                red_gold += gold;
            }
            e.getPlayer().getWorld().playSound(e.getPlayer().getLocation(), Sound.LEVEL_UP, 1,1);
        } else if (e.getBlock().getType() == Material.STONE && e.getBlock().getData() == 5){
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
            gold = r.nextInt(2);
            new OreHologram(e.getBlock().getLocation(), gold, true);
            if (bluePlayers.contains(e.getPlayer())){
                blue_stone += 3;
                blue_gold += gold;
            } else if (greenPlayers.contains(e.getPlayer())){
                green_stone += 3;
                green_gold += gold;
            } else if (yellowPlayers.contains(e.getPlayer())){
                yellow_stone += 3;
                yellow_gold += gold;
            } else if (redPlayers.contains(e.getPlayer())){
                red_stone += 3;
                red_gold += gold;
            }
            e.getPlayer().getWorld().playSound(e.getPlayer().getLocation(), Sound.LEVEL_UP, 1,1);
        } else if (e.getBlock().getType() == Material.SEA_LANTERN){
            if (xp.contains(new Location(Bukkit.getWorld(choosenMap), e.getBlock().getLocation().getBlockX(), e.getBlock().getLocation().getBlockY(), e.getBlock().getLocation().getBlockZ()))){
                int gold = 0;
                Random r = new Random();
                gold = r.nextInt(2);
                if (bluePlayers.contains(e.getPlayer())){
                    blue_stone += 3;
                    blue_gold += gold;
                    blue_wood += 3;
                } else if (greenPlayers.contains(e.getPlayer())){
                    green_stone += 3;
                    green_gold += gold;
                    green_wood += 3;
                } else if (yellowPlayers.contains(e.getPlayer())){
                    yellow_stone += 3;
                    yellow_gold += gold;
                    yellow_wood += 3;
                } else if (redPlayers.contains(e.getPlayer())){
                    red_stone += 3;
                    red_gold += gold;
                    red_wood += 3;
                }
                new OreHologram(e.getBlock().getLocation(), gold, true);
                new OreHologram(e.getBlock().getLocation(), gold, false);
            }
            e.setCancelled(true);
        } else {
            if (e.getBlock().getType() == Material.MELON_BLOCK){
                return;
            }
            e.getPlayer().sendMessage(getMsg("cant-break"));
            e.setCancelled(true);
        } */
    }
}
