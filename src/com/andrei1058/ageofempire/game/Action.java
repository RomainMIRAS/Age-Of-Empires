package com.andrei1058.ageofempire.game;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Action {
    public static void actionMsg(Player p, String message){
        CraftPlayer cPlayer = (CraftPlayer)p;
        String string = ChatColor.translateAlternateColorCodes('&', message);
        IChatBaseComponent cbc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + string + "\"}");
        PacketPlayOutChat ppoc = new PacketPlayOutChat(cbc, (byte)2);
        cPlayer.getHandle().playerConnection.sendPacket(ppoc);
    }
}
