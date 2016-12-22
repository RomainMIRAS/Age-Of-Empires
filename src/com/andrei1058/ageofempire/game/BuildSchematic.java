package com.andrei1058.ageofempire.game;

import com.andrei1058.ageofempire.locations.Schematic;
import com.andrei1058.ageofempire.nms.VillagerNMS;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Villager;
import org.bukkit.scheduler.BukkitRunnable;
import org.jnbt.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

import static com.andrei1058.ageofempire.Main.*;
import static com.andrei1058.ageofempire.configuration.Messages.getMsg;
import static com.andrei1058.ageofempire.game.Buildings.*;

public class BuildSchematic {

    public UUID player;
    public String team;
    public String chat_build_name;
    public String build_cfg_name;
    public World world = Bukkit.getWorld(choosenMap);
    private static HashMap<UUID, BuildSchematic> buildSchematicHashMap = new HashMap<>();
    public static ArrayList<UUID> teaamarray;
    public Location villager;

    public BuildSchematic(UUID Player, String team, String chat_build_name, String build_cfg_name, ArrayList<UUID> teamarray) {
        this.player = Player;
        this.team = team;
        this.chat_build_name = chat_build_name;
        this.build_cfg_name = build_cfg_name;
        this.teaamarray = teamarray;
        buildSchematicHashMap.put(Player, this);
    }

    public void ok(Location center) {
        if (!construct_in_inv.containsKey(player)) return;
        addBuild(build_cfg_name, team);
        for (UUID u : teaamarray) {
            Bukkit.getPlayer(u).sendMessage(getMsg("build-started").replace("{player}", Bukkit.getPlayer(player).getName()).replace("{building}", chat_build_name));
        }
        try {
            pasteSchematic(center, loadSchematic(new File("plugins/Age-Of-Empire/schematics/" + construct_in_inv.get(player) + ".schematic")));
            end();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void end() {
        buildSchematicHashMap.remove(player, this);
        construct_in_inv.remove(player);
    }

    public static BuildSchematic getUUID(UUID player) {
        return buildSchematicHashMap.get(player);
    }

    public void pasteSchematic(Location loc, Schematic schematic) {
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
                        if(blocks[index] == 169){ villager = l.clone().add(0, +1, 0); }
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
        final int size = locatii.size();
        final int blocksPerTime = 2;
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
                            for (UUID u : teaamarray) {
                                Bukkit.getPlayer(u).sendMessage(getMsg("built-success").replace("{building}", chat_build_name));
                            }
                            Villager v = VillagerNMS.spawnVillager(villager, 500);
                            switch (build_cfg_name) {
                                case forge:
                                    new Hologram(v.getLocation(), getMsg("forum."+forge+".displayname"), getMsg("forum."+forge+".holo"), v);
                                    switch (team) {
                                        case blue_team:
                                            blue_forge = v;
                                            break;
                                        case green_team:
                                            green_forge = v;
                                            break;
                                        case yellow_team:
                                            yellow_forge = v;
                                            break;
                                        case red_team:
                                            red_forge = v;
                                            break;
                                    }
                                    break;
                                case stone_mine:
                                    new Hologram(v.getLocation(), getMsg("forum."+stone_mine+".displayname"), getMsg("forum."+stone_mine+".holo"), v);
                                    switch (team) {
                                        case blue_team:
                                            blue_stonemine = true;
                                            blue_smine = v;
                                            break;
                                        case green_team:
                                            green_stonemine = true;
                                            green_smine = v;
                                            break;
                                        case yellow_team:
                                            yellow_stonemine = true;
                                            yellow_smine = v;
                                            break;
                                        case red_team:
                                            red_stonemine = true;
                                            red_smine = v;
                                            break;
                                    }
                                    break;
                                case gold_mine:
                                    new Hologram(v.getLocation(), getMsg("forum."+gold_mine+".displayname"), getMsg("forum."+gold_mine+".holo"), v);
                                    switch (team) {
                                        case blue_team:
                                            blue_goldmine = true;
                                            blue_gmine = v;
                                            break;
                                        case green_team:
                                            green_goldmine = true;
                                            green_gmine = v;
                                            break;
                                        case yellow_team:
                                            yellow_goldmine = true;
                                            yellow_gmine = v;
                                            break;
                                        case red_team:
                                            red_goldmine = true;
                                            red_gmine = v;
                                            break;
                                    }
                                    break;
                                case sawmill:
                                    new Hologram(v.getLocation(), getMsg("forum."+sawmill+".displayname"), getMsg("forum."+sawmill+".holo"), v);
                                    switch (team) {
                                        case blue_team:
                                            blue_sawmill = true;
                                            blue_smill = v;
                                            break;
                                        case green_team:
                                            green_sawmill = true;
                                            green_smill = v;
                                            break;
                                        case yellow_team:
                                            yellow_sawmill = true;
                                            yellow_smill = v;
                                            break;
                                        case red_team:
                                            red_sawmill = true;
                                            red_smill = v;
                                            break;
                                    }
                                    break;
                                case mill:
                                    new Hologram(v.getLocation(), getMsg("forum."+mill+".displayname"), getMsg("forum."+mill+".holo"), v);
                                    switch (team) {
                                        case blue_team:
                                            blue_smill = v;
                                            break;
                                        case green_team:
                                            green_smill = v;
                                            break;
                                        case yellow_team:
                                            yellow_smill = v;
                                            break;
                                        case red_team:
                                            red_smill = v;
                                            break;
                                    }
                                    break;
                            }
                            return;
                        }
                    }
                }
            }.runTaskTimer(plugin, 0, delay);
        }
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
