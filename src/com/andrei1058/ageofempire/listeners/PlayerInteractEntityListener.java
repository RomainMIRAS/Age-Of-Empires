package com.andrei1058.ageofempire.listeners;

import com.andrei1058.ageofempire.Misc;
import com.andrei1058.ageofempire.game.Status;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

import static com.andrei1058.ageofempire.Main.*;
import static com.andrei1058.ageofempire.configuration.Messages.getArray;
import static com.andrei1058.ageofempire.configuration.Messages.getMsg;
import static com.andrei1058.ageofempire.game.Buildings.*;

public class PlayerInteractEntityListener implements Listener {

    @EventHandler
    public void i(PlayerInteractEntityEvent e){
        if (SETUP) return;
        if (STATUS == Status.PLAYING){
            if (e.getRightClicked().getType() == EntityType.VILLAGER){
                Villager v = (Villager) e.getRightClicked();
                if (v.getCustomName() == null) return;
                e.setCancelled(true);
                    if (v == blue_villager) {
                        if (bluePlayers.contains(e.getPlayer().getUniqueId())){
                            e.getPlayer().openInventory(forum(blue_team));
                        } else {
                            //can't open this inventory
                        }
                    } else if (v == yellow_villager) {
                        e.setCancelled(true);
                        if (yellowPlayers.contains(e.getPlayer().getUniqueId())){
                            e.getPlayer().openInventory(forum(yellow_team));
                        } else {
                            //can't open this inventory
                        }
                    } else if (v == green_villager) {
                        e.setCancelled(true);
                        if (greenPlayers.contains(e.getPlayer().getUniqueId())){
                            e.getPlayer().openInventory(forum(green_team));
                        } else {
                            //can't open this inventory
                        }
                    } else if (v == red_villager) {
                        e.setCancelled(true);
                        if (redPlayers.contains(e.getPlayer().getUniqueId())){
                            e.getPlayer().openInventory(forum(red_team));
                        } else {
                            //can't open this inventory
                        }
                    }

            }
        }
    }

    public static Inventory forum(String  team){
        Inventory inv = Bukkit.createInventory(null, 54, "Forum");
        inv.setItem(0, Misc.getSkull("http://textures.minecraft.net/texture/71bc2bcfb2bd3759e6b1e86fc7a79585e1127dd357fc202893f9de241bc9e530", getMsg("forum.age1")));
        inv.setItem(1, Misc.getSkull("http://textures.minecraft.net/texture/1a4f68c8fb279e50ab786f9fa54c88ca4ecfe1eb5fd5f0c38c54c9b1c7203d7a", getMsg("forum.age-buldings")));
        inv.setItem(2, item(Material.ANVIL, (short) 0, getMsg("forum."+forge+".displayname"), getArray("forum."+forge+".lore"), true, hasBuild(forge, team)));
        inv.setItem(3, item(Material.WHEAT, (short) 0, getMsg("forum."+mill+".displayname"), getArray("forum."+mill+".lore"), true, hasBuild(mill, team)));
        inv.setItem(4, item(Material.DIAMOND_PICKAXE, (short) 0, getMsg("forum."+stone_mine+".displayname"), getArray("forum."+stone_mine+".lore"), true, hasBuild(stone_mine, team)));
        inv.setItem(5, item(Material.GOLD_ORE, (short) 0, getMsg("forum."+gold_mine+".displayname"), getArray("forum."+gold_mine+".lore"), true, hasBuild(gold_mine, team)));
        inv.setItem(6, item(Material.DIAMOND_AXE, (short) 0, getMsg("forum."+sawmill+".displayname"), getArray("forum."+sawmill+".lore"), true, hasBuild(sawmill, team)));
        inv.setItem(7, item(Material.WORKBENCH, (short) 0, getMsg("forum."+workshop+".displayname"), getArray("forum."+workshop+".lore"), true, hasBuild(workshop, team)));
        inv.setItem(8, item(Material.EMERALD, (short) 0, getMsg("forum."+market+".displayname"), getArray("forum."+market+".lore"), true, hasBuild(market, team)));
        inv.setItem(9, item(Material.BONE, (short) 0, getMsg("forum."+kennel+".displayname"), getArray("forum."+kennel+".lore"), true, hasBuild(kennel, team)));
        inv.setItem(10, item(Material.TNT, (short) 0, getMsg("forum."+sabotage+".displayname"), getArray("forum."+sabotage+".lore"), true, hasBuild(sabotage, team)));
        return inv;
    }
    private static ItemStack item(Material material, short sh, String name, ArrayList<String> lore, boolean enchant, boolean built){
        ItemStack i = new ItemStack(material, 1, sh);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(name);
        ArrayList<String > l = new ArrayList<>();
        for (String s : lore){
            l.add(s.replace('&','§'));
        }
        if (built){
            l.add(getMsg("forum.built"));
        } else {
            l.add(getMsg("forum.to-build"));
        }
        im.setLore(l);
        if (enchant) {
            im.addEnchant(Enchantment.DURABILITY, 1, true);
            im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        i.setItemMeta(im);
        return i;
    }
}
