package com.andrei1058.ageofempire.locations;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.*;
import java.util.*;

import static com.andrei1058.ageofempire.Main.plugin;

public class StructureBuilder {

    public ArrayList<Block> getStructure(Block block, Block block2) {
        ArrayList<Block> blocks = new ArrayList<>();
        int minX, minZ, minY;
        int maxX, maxZ, maxY;

        minX = block.getX() < block2.getX() ? block.getX() : block2.getX();
        minZ = block.getZ() < block2.getZ() ? block.getZ() : block2.getZ();
        minY = block.getY() < block2.getY() ? block.getY() : block2.getY();

        maxX = block.getX() > block2.getX() ? block.getX() : block2.getX();
        maxZ = block.getZ() > block2.getZ() ? block.getZ() : block2.getZ();
        maxY = block.getY() > block2.getY() ? block.getY() : block2.getY();

        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                for (int z = minZ; z <= maxZ; z++) {
                    Block b = block.getWorld().getBlockAt(x, y, z);
                    if (b.getType() != Material.AIR) {
                        blocks.add(b);
                    }
                }
            }
        }
        return blocks;
    }

    public void paste(ArrayList blocks, Location l) {
        final List<Block> orderedLocation = new ArrayList<>();

        orderedLocation.addAll(blocks);

        Collections.sort(orderedLocation, new Comparator<Block>() {
            @Override
            public int compare(Block block1, Block block2) {
                return Double.compare(block1.getY(), block2.getY());
            }
        });

        Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
            @Override
            public void run() {
                final int size = blocks.size();
                final int blocksPerTime = 1;
                final long delay = 0L;

                if (size > 0) {
                    new BukkitRunnable() {
                        int index = 0;

                        @Override
                        public void run() {
                            for (int i = 0; i < blocksPerTime; i++) {
                                if (index <= size) {
                                    Block block = orderedLocation.get(index);
                                    block.getWorld().getBlockAt(block.getLocation()).setType(block.getType());
                                    block.getState().setData(block.getState().getData());
                                    block.getState().update(true);
                                    block.getWorld().playEffect(block.getLocation(), Effect.STEP_SOUND, block.getTypeId());
                                    index += 1;
                                } else {
                                    this.cancel();
                                    return;
                                }
                            }
                        }
                    }.runTaskTimer(plugin, 0, delay);
                }
            }
        }, 100L);
    }

    public int[][][] load(String name) {

        File f = new File(plugin.getDataFolder() + "/schematics/" + name + ".schem");

        int[][][] loaded = new int[0][0][0];

        try {
            FileInputStream streamIn = new FileInputStream(f);
            ObjectInputStream objectinputstream = new ObjectInputStream(streamIn);
            loaded = (int[][][]) objectinputstream.readObject();

            objectinputstream.close();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return loaded;
    }

    /**
     * Some methods I used to test
     */

    public void printArray(int[][][] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.println(toString(a[i]));
        }
    }

    public String toString(int[][] a) {
        String s = "";
        for (int row = 0; row < a.length; ++row) {
            s += Arrays.toString(a[row]) + "\n";
        }
        return s;
    }
}
