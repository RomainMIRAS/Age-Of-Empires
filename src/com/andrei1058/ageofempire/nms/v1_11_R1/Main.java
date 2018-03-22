package com.andrei1058.ageofempire.nms.v1_11_R1;

import com.andrei1058.ageofempire.nms.NMS;
import net.minecraft.server.v1_11_R1.IChatBaseComponent;
import net.minecraft.server.v1_11_R1.PacketPlayOutChat;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.ItemStack;

/**
 * Copyright Andrei Dascalu - andrei1058 @spigotmc.org
 * Age-Of-Empire class written on 13/04/2017
 */
public class Main implements NMS {
    @Override
    public void registerVillagers() {
        RegisterNMS.registerEntity("Villager_AOE", 120, VillagerNMS.class);
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
        return Sound.valueOf("ENTITY_WITHER_DEATH");
    }

    @Override
    public Sound levelUp() {
        return Sound.valueOf("ENTITY_PLAYER_LEVELUP");
    }

    @Override
    public Sound wolfDeath() {
        return Sound.valueOf("ENTITY_WOLF_DEATH");
    }

    @Override
    public Sound click() {
        return Sound.valueOf("UI_BUTTON_CLICK");
    }

    @Override
    public Villager spawnVillager(Location loc, Integer health) {
        return VillagerNMS.spawnVillager(loc, health);
    }

    @Override
    public ItemStack itemInHand(Player p) {
        return p.getInventory().getItemInMainHand();
    }
}
