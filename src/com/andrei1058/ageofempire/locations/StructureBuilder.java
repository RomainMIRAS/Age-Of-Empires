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

    public ArrayList<Location> locatii = new ArrayList<>();
    public HashMap<Location, Material> materiale = new HashMap<>();

    /**
     * Get all blocks between two points and return a 3d array
     */

    public int[][][] getStructure(Block block, Block block2) {
        int minX, minZ, minY;
        int maxX, maxZ, maxY;


        //Couldv'e used Math.min()/Math.max(), but that didn't occur to me until after I finished this :D
        minX = block.getX() < block2.getX() ? block.getX() : block2.getX();
        minZ = block.getZ() < block2.getZ() ? block.getZ() : block2.getZ();
        minY = block.getY() < block2.getY() ? block.getY() : block2.getY();

        maxX = block.getX() > block2.getX() ? block.getX() : block2.getX();
        maxZ = block.getZ() > block2.getZ() ? block.getZ() : block2.getZ();
        maxY = block.getY() > block2.getY() ? block.getY() : block2.getY();

        int[][][] blocks = new int[maxX - minX + 1][maxY - minY + 1][maxZ - minZ + 1];

        for (int x = minX; x <= maxX; x++) {

            for (int y = minY; y <= maxY; y++) {

                for (int z = minZ; z <= maxZ; z++) {

                    Block b = block.getWorld().getBlockAt(x, y, z);
                    blocks[x - minX][y - minY][z - minZ] = b.getTypeId();
                }
            }
        }

        return blocks;

    }


    /**
     * Pastes a structure to a desired location
     */

    public void paste(int[][][] blocks, Location l) {
        for (int x = 0; x < blocks.length; x++) {
            for (int y = 0; y < blocks[x].length; y++) {
                for (int z = 0; z < blocks[x][y].length; z++) {
                    Location neww = l.clone();
                    neww.add(x, y, z);
                    if (blocks[x][y][z] != 0) {
                        if (Material.getMaterial(blocks[x][y][z]) != Material.AIR) {
                            locatii.add(neww);
                            materiale.put(neww, Material.getMaterial(blocks[x][y][z]));
                        }
                    }
                }

            }
        }
        final List<Location> orderedLocation = new ArrayList<>();

        orderedLocation.addAll(locatii);

        Collections.sort(orderedLocation, new Comparator<Location>() {
            @Override
            public int compare(Location block1, Location block2) {
                return Double.compare(block1.getY(), block2.getY());
            }
        });

        Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
            @Override
            public void run() {
                final int size = locatii.size();
                final int blocksPerTime = 1;
                final long delay = 0L;

                if (size > 0) {
                    new BukkitRunnable() {
                        int index = 0;

                        @Override
                        public void run() {
                            for (int i = 0; i < blocksPerTime; i++) {
                                if (index < size) {
                                    Block block = orderedLocation.get(index).getBlock();
                                    Material mat = materiale.get(orderedLocation.get(index));
                                    if (mat != Material.AIR) {
                                        block.setType(mat);
                                        block.getState().update(true);
                                        block.getWorld().playEffect(block.getLocation(), Effect.STEP_SOUND, block.getTypeId());
                                    }
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

    /**
     * Save a structure with a desired name
     */

    public void save(String name, int[][][] b) {
        ObjectOutputStream oos = null;
        FileOutputStream fout = null;

        File f = new File(plugin.getDataFolder() + "/schematics/" + name + ".schem");
        File dir = new File(plugin.getDataFolder() + "/schematics");

        try {
            dir.mkdirs();
            f.createNewFile();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        try {
            fout = new FileOutputStream(f);
            oos = new ObjectOutputStream(fout);
            oos.writeObject(b);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Load structure from file
     */

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