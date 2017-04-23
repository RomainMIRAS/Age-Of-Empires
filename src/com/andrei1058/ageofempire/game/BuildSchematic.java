package com.andrei1058.ageofempire.game;

import com.andrei1058.ageofempire.locations.Schematic;
import com.andrei1058.ageofempire.nms.v1_8_R3.VillagerNMS;
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
    public ArrayList<UUID> teaamarray;
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

        Collections.sort(orderedLocation, Comparator.comparingDouble(Location::getY));
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
                                            blue_vsawmill = v;
                                            break;
                                        case green_team:
                                            green_sawmill = true;
                                            green_vsawmill = v;
                                            break;
                                        case yellow_team:
                                            yellow_sawmill = true;
                                            yellow_vsawmill = v;
                                            break;
                                        case red_team:
                                            red_sawmill = true;
                                            red_vsawmill = v;
                                            break;
                                    }
                                    break;
                                case mill:
                                    new Hologram(v.getLocation(), getMsg("forum."+mill+".displayname"), getMsg("forum."+mill+".holo"), v);
                                    switch (team) {
                                        case blue_team:
                                            blue_mill = v;
                                            break;
                                        case green_team:
                                            green_mill = v;
                                            break;
                                        case yellow_team:
                                            yellow_mill = v;
                                            break;
                                        case red_team:
                                            red_mill = v;
                                            break;
                                    }
                                    break;
                                case workshop:
                                    new Hologram(v.getLocation(), getMsg("forum."+workshop+".displayname"), getMsg("forum."+workshop+".holo"), v);
                                    switch (team) {
                                        case blue_team:
                                            blue_workshop = v;
                                            break;
                                        case green_team:
                                            green_workshop = v;
                                            break;
                                        case yellow_team:
                                            yellow_workshop = v;
                                            break;
                                        case red_team:
                                            red_workshop = v;
                                            break;
                                    }
                                    break;
                                case market:
                                    new Hologram(v.getLocation(), getMsg("forum."+market+".displayname"), getMsg("forum."+market+".holo"), v);
                                    switch (team) {
                                        case blue_team:
                                            blue_market = v;
                                            break;
                                        case green_team:
                                            green_market = v;
                                            break;
                                        case yellow_team:
                                            yellow_market = v;
                                            break;
                                        case red_team:
                                            red_market = v;
                                            break;
                                    }
                                    break;
                                case sabotage:
                                    new Hologram(v.getLocation(), getMsg("forum."+sabotage+".displayname"), getMsg("forum."+sabotage+".holo"), v);
                                    switch (team) {
                                        case blue_team:
                                            blue_sabotage = v;
                                            break;
                                        case green_team:
                                            green_sabotage = v;
                                            break;
                                        case yellow_team:
                                            yellow_sabotage = v;
                                            break;
                                        case red_team:
                                            red_sabotage = v;
                                            break;
                                    }
                                    break;
                                case kennel:
                                    new Hologram(v.getLocation(), getMsg("forum."+kennel+".displayname"), getMsg("forum."+kennel+".holo"), v);
                                    switch (team) {
                                        case blue_team:
                                            blue_kennel = v;
                                            break;
                                        case green_team:
                                            green_kennel = v;
                                            break;
                                        case yellow_team:
                                            yellow_kennel = v;
                                            break;
                                        case red_team:
                                            red_kennel = v;
                                            break;
                                    }
                                    break;
                                case archery:
                                    new Hologram(v.getLocation(), getMsg("forum."+archery+".displayname"), getMsg("forum."+archery+".holo"), v);
                                    switch (team) {
                                        case blue_team:
                                            blue_archery = v;
                                            break;
                                        case green_team:
                                            green_archery = v;
                                            break;
                                        case yellow_team:
                                            yellow_archery = v;
                                            break;
                                        case red_team:
                                            red_archery = v;
                                            break;
                                    }
                                    break;
                                case trifarrow:
                                    new Hologram(v.getLocation(), getMsg("forum."+trifarrow+".displayname"), getMsg("forum."+trifarrow+".holo"), v);
                                    switch (team) {
                                        case blue_team:
                                            blue_trifarrow = v;
                                            break;
                                        case green_team:
                                            green_trifarrow = v;
                                            break;
                                        case yellow_team:
                                            yellow_trifarrow = v;
                                            break;
                                        case red_team:
                                            red_trifarrow = v;
                                            break;
                                    }
                                    break;
                                case stable:
                                    new Hologram(v.getLocation(), getMsg("forum."+stable+".displayname"), getMsg("forum."+stable+".holo"), v);
                                    switch (team) {
                                        case blue_team:
                                            blue_stable = v;
                                            break;
                                        case green_team:
                                            green_stable = v;
                                            break;
                                        case yellow_team:
                                            yellow_stable = v;
                                            break;
                                        case red_team:
                                            red_stable = v;
                                            break;
                                    }
                                    break;
                                case armory:
                                    new Hologram(v.getLocation(), getMsg("forum."+armory+".displayname"), getMsg("forum."+armory+".holo"), v);
                                    switch (team) {
                                        case blue_team:
                                            blue_armory = v;
                                            break;
                                        case green_team:
                                            green_armory = v;
                                            break;
                                        case yellow_team:
                                            yellow_armory = v;
                                            break;
                                        case red_team:
                                            red_armory = v;
                                            break;
                                    }
                                    break;
                                case laboratory:
                                    new Hologram(v.getLocation(), getMsg("forum."+laboratory+".displayname"), getMsg("forum."+laboratory+".holo"), v);
                                    switch (team) {
                                        case blue_team:
                                            blue_lab = v;
                                            break;
                                        case green_team:
                                            green_lab = v;
                                            break;
                                        case yellow_team:
                                            yellow_lab = v;
                                            break;
                                        case red_team:
                                            red_lab = v;
                                            break;
                                    }
                                    break;
                                case guild:
                                    new Hologram(v.getLocation(), getMsg("forum."+guild+".displayname"), getMsg("forum."+guild+".holo"), v);
                                    switch (team) {
                                        case blue_team:
                                            blue_guild = v;
                                            break;
                                        case green_team:
                                            green_guild = v;
                                            break;
                                        case yellow_team:
                                            yellow_guild = v;
                                            break;
                                        case red_team:
                                            red_guild = v;
                                            break;
                                    }
                                    break;
                                case training_center:
                                    new Hologram(v.getLocation(), getMsg("forum."+training_center+".displayname"), getMsg("forum."+training_center+".holo"), v);
                                    switch (team) {
                                        case blue_team:
                                            blue_tcenter = v;
                                            blue_xp = true;
                                            break;
                                        case green_team:
                                            green_tcenter = v;
                                            green_xp = true;
                                            break;
                                        case yellow_team:
                                            yellow_tcenter = v;
                                            yellow_xp = true;
                                            break;
                                        case red_team:
                                            red_tcenter = v;
                                            red_xp = true;
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
