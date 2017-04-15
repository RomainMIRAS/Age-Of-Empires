package com.andrei1058.ageofempire.nms.v1_9_R2;

import com.andrei1058.ageofempire.nms.NMS;
import net.minecraft.server.v1_9_R2.IChatBaseComponent;
import net.minecraft.server.v1_9_R2.PacketPlayOutChat;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_9_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

/**
 * Copyright Andrei Dascalu - andrei1058 @spigotmc.org
 * Age-Of-Empire class written on 13/04/2017
 */
public class Main implements NMS {
    @Override
    public void registerVillagers() {
        RegisterNMS.registerEntity("Villager", 120, VillagerNMS.class);
    }

    @Override
    public  void actionMsg(Player p, String message){
        CraftPlayer cPlayer = (CraftPlayer)p;
        String string = ChatColor.translateAlternateColorCodes('&', message);
        IChatBaseComponent cbc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + string + "\"}");
        PacketPlayOutChat ppoc = new PacketPlayOutChat(cbc, (byte)2);
        cPlayer.getHandle().playerConnection.sendPacket(ppoc);
    }

    @Override
    public Sound witherDeath() {
        return Sound.ENTITY_WITHER_DEATH;
    }

    @Override
    public Sound levelUp() {
        return Sound.ENTITY_PLAYER_LEVELUP;
    }

    @Override
    public Sound wolfDeath() {
        return Sound.ENTITY_WOLF_DEATH;
    }

    @Override
    public Sound click() {
        return Sound.UI_BUTTON_CLICK;
    }

    @Override
    public Villager spawnVillager(Location loc, Integer health) {
        return VillagerNMS.spawnVillager(loc, health);
    }
}
