package com.andrei1058.ageofempire;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.apache.commons.codec.binary.Base64;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.andrei1058.ageofempire.configuration.Messages.getArray;
import static com.andrei1058.ageofempire.configuration.Messages.getMsg;

public class Misc {
    public static ItemStack getSkull(String url, String name) {
        ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short)3);

        if(url.isEmpty())return head;

        SkullMeta headMeta = (SkullMeta) head.getItemMeta();
        headMeta.setDisplayName(name);
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        byte[] encodedData = Base64.encodeBase64(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
        profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
        Field profileField = null;
        try {
            profileField = headMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(headMeta, profile);
        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e1) {
            e1.printStackTrace();
        }
        head.setItemMeta(headMeta);
        return head;
    }
    public static ItemStack leatherArmor(Material material, Color color){
        ItemStack i = new ItemStack(material);
        LeatherArmorMeta meta = (LeatherArmorMeta) i.getItemMeta();
        meta.setColor(color);
        i.setItemMeta(meta);
        return i;
    }

    public static ItemStack slotlocked(){
        ItemStack i = new ItemStack(Material.MOB_SPAWNER);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(getMsg("locked-slot"));
        i.setItemMeta(im);
        return i;
    }

    public static ItemStack forumPaper(){
        ItemStack i = new ItemStack(Material.PAPER);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(getMsg("forum-paper"));
        i.setItemMeta(im);
        return i;
    }

    public static ItemStack voteitem(){
        ItemStack i = new ItemStack(Material.SLIME_BALL);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(getMsg("validate-vote"));
        i.setItemMeta(im);
        return i;
    }
    public static ItemStack constructor(){
        ItemStack i = new ItemStack(Material.SPRUCE_DOOR_ITEM);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(getMsg("constructor.displayname"));
        im.setLore(getArray("constructor.lore").stream().map(s -> s.replace('&', '§')).collect(Collectors.toCollection(ArrayList::new)));
        i.setItemMeta(im);
        return i;
    }

    public static ItemStack statsItem(Player p){
        ItemStack i = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta sm = (SkullMeta) i.getItemMeta();
        sm.setDisplayName(getMsg("stats.displayname"));
        sm.setOwner(p.getName());
        i.setItemMeta(sm);
        return i;
    }
}
