package com.andrei1058.ageofempire.locations;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;
import org.jnbt.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

import static com.andrei1058.ageofempire.Main.plugin;

public class SchematicMethods {

    public static void pasteSchematic(World world, Location loc, Schematic schematic) {
        short[] blocks = schematic.getBlocks();
        byte[] blockData = schematic.getData();

        short length = schematic.getLenght();
        short width = schematic.getWidth();
        short height = schematic.getHeight();

        ArrayList<Location> locatii = new ArrayList<>();
        HashMap<Location, Short> iduri = new HashMap<>();
        HashMap<Location, Byte> data = new HashMap<>();

        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                for (int z = 0; z < length; ++z) {
                    int index = y * width * length + z * width + x;
                    if (blocks[index] != 0) {
                        Location l = new Location(world, x + loc.getX(), y + loc.getY(), z + loc.getZ());
                        locatii.add(l);
                        iduri.put(l, blocks[index]);
                        data.put(l, blockData[index]);
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
                                    Location loc = orderedLocation.get(index);
                                    Block block = loc.getBlock();
                                    block.setType(Material.getMaterial(iduri.get(loc)));
                                    block.setData(data.get(loc));
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

    public static Schematic loadSchematic(File file) throws IOException {
        FileInputStream stream = new FileInputStream(file);
        NBTInputStream nbtStream = new NBTInputStream(stream);

        CompoundTag schematicTag = (CompoundTag) nbtStream.readTag();
        if (!schematicTag.getName().equals("Schematic")) {
            throw new IllegalArgumentException("Tag \"Schematic\" does not exist or is not first");
        }

        Map<String, Tag> schematic = schematicTag.getValue();
        if (!schematic.containsKey("Blocks")) {
            throw new IllegalArgumentException("Schematic file is missing a \"Blocks\" tag");
        }

        short width = getChildTag(schematic, "Width", ShortTag.class).getValue();
        short length = getChildTag(schematic, "Length", ShortTag.class).getValue();
        short height = getChildTag(schematic, "Height", ShortTag.class).getValue();

        byte[] blockId = getChildTag(schematic, "Blocks", ByteArrayTag.class).getValue();
        byte[] blockData = getChildTag(schematic, "Data", ByteArrayTag.class).getValue();
        byte[] addId = new byte[0];
        short[] blocks = new short[blockId.length]; // Have to later combine IDs

        if (schematic.containsKey("AddBlocks")) {
            addId = getChildTag(schematic, "AddBlocks", ByteArrayTag.class).getValue();
        }

        for (int index = 0; index < blockId.length; index++) {
            if ((index >> 1) >= addId.length) {
                blocks[index] = (short) (blockId[index] & 0xFF);
            } else {
                if ((index & 1) == 0) {
                    blocks[index] = (short) (((addId[index >> 1] & 0x0F) << 8) + (blockId[index] & 0xFF));
                } else {
                    blocks[index] = (short) (((addId[index >> 1] & 0xF0) << 4) + (blockId[index] & 0xFF));
                }
            }
        }

        return new Schematic(blocks, blockData, width, length, height);
    }

    private static <T extends Tag> T getChildTag(Map<String, Tag> items, String key, Class<T> expected) throws IllegalArgumentException
    {
        if (!items.containsKey(key)) {
            throw new IllegalArgumentException("Schematic file is missing a \"" + key + "\" tag");
        }
        Tag tag = items.get(key);
        if (!expected.isInstance(tag)) {
            throw new IllegalArgumentException(key + " tag is not of tag type " + expected.getName());
        }
        return expected.cast(tag);
    }
}
