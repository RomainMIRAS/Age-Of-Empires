package com.andrei1058.ageofempire.game;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import java.lang.reflect.Constructor;

public class Titles {
    @Deprecated
    public static void sendTitle(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String message)
    {
        sendTitle(player, fadeIn, stay, fadeOut, message, null);
    }

    public static void sendSubtitle(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String message)
    {
        sendTitle(player, fadeIn, stay, fadeOut, null, message);
    }
    public static void sendFullTitle(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String title, String subtitle)
    {
        sendTitle(player, fadeIn, stay, fadeOut, title, subtitle);
    }

    @Deprecated
    public static Integer getPlayerProtocol(Player player)
    {
        return Integer.valueOf(47);
    }

    public static void sendPacket(Player player, Object packet)
    {
        try
        {
            Object handle = player.getClass().getMethod("getHandle").invoke(player);
            Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
            playerConnection.getClass().getMethod("sendPacket", getNMSClass("Packet")).invoke(playerConnection, packet);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static Class<?> getNMSClass(String name)
    {
        String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
        try
        {
            return Class.forName("net.minecraft.server." + version + "." + name);
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static void sendTitle(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String title, String subtitle)
    {
        try
        {
            if (title != null)
            {
                Object enumTitle = getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("TITLE").get(null);
                Object chatTitle = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + title + "\"}");
                Constructor<?> titleConstructor = getNMSClass("PacketPlayOutTitle").getConstructor(getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], getNMSClass("IChatBaseComponent"), Integer.TYPE, Integer.TYPE, Integer.TYPE);
                Object titlePacket = titleConstructor.newInstance(enumTitle, chatTitle, fadeIn, stay, fadeOut);
                sendPacket(player, titlePacket);
            }
            if (subtitle != null)
            {
                Object enumSubtitle = getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("SUBTITLE").get(null);
                Object chatSubtitle = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + subtitle + "\"}");
                Constructor<?> subtitleConstructor = getNMSClass("PacketPlayOutTitle").getConstructor(getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], getNMSClass("IChatBaseComponent"), Integer.TYPE, Integer.TYPE, Integer.TYPE);
                Object subtitlePacket = subtitleConstructor.newInstance(enumSubtitle, chatSubtitle, fadeIn, stay, fadeOut);
                sendPacket(player, subtitlePacket);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}

