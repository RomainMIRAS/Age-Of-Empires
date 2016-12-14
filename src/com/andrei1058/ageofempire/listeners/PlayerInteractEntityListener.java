package com.andrei1058.ageofempire.listeners;

import com.andrei1058.ageofempire.Main;
import com.andrei1058.ageofempire.Misc;
import com.andrei1058.ageofempire.game.Status;
import com.andrei1058.ageofempire.locations.Locations;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
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
import static com.andrei1058.ageofempire.game.Buildings.built_forge;
import static com.andrei1058.ageofempire.game.Buildings.forge;
import static com.andrei1058.ageofempire.game.Buildings.hasBuild;

public class PlayerInteractEntityListener implements Listener {

    @EventHandler
    public void i(PlayerInteractEntityEvent e){
        if (SETUP) return;
        if (STATUS == Status.PLAYING){
            if (e.getRightClicked().getType() == EntityType.VILLAGER){
                Villager v = (Villager) e.getRightClicked();
                if (v.getCustomName() == null) return;
                e.setCancelled(true);
                if (v.getCustomName().equalsIgnoreCase(getMsg("villagers.forum"))) {
                    if (v.getLocation().getBlockX() == Locations.getLoc("Forums." + choosenMap + ".Blue").getBlockX() && v.getLocation().getBlockY() == Locations.getLoc("Forums." + choosenMap + ".Blue").getBlockY()) {
                        if (bluePlayers.contains(e.getPlayer().getUniqueId())){
                            //open inventory
                            e.getPlayer().openInventory(forum(blue_team));
                        } else {
                            //can't open this inventory
                        }
                    } else if (v.getLocation().getBlockX() == Locations.getLoc("Forums." + choosenMap + ".Yellow").getBlockX() && v.getLocation().getBlockZ() == Locations.getLoc("Forums." + choosenMap + ".Yellow").getBlockZ()) {
                        e.setCancelled(true);
                        if (yellowPlayers.contains(e.getPlayer().getUniqueId())){
                            //open inventory
                            e.getPlayer().openInventory(forum(yellow_team));
                        } else {
                            //can't open this inventory
                        }
                    } else if (v.getLocation().getBlockX() == Locations.getLoc("Forums." + choosenMap + ".Green").getBlockX() && v.getLocation().getBlockZ() == Locations.getLoc("Forums." + choosenMap + ".Green").getBlockZ()) {
                        e.setCancelled(true);
                        if (greenPlayers.contains(e.getPlayer().getUniqueId())){
                            //open inventory
                            e.getPlayer().openInventory(forum(green_team));
                        } else {
                            //can't open this inventory
                        }
                    } else if (v.getLocation().getBlockX() == Locations.getLoc("Forums." + choosenMap + ".Red").getBlockX() && v.getLocation().getBlockZ() == Locations.getLoc("Forums." + choosenMap + ".Red").getBlockZ()) {
                        e.setCancelled(true);
                        if (redPlayers.contains(e.getPlayer().getUniqueId())){
                            //open inventory
                            e.getPlayer().openInventory(forum(red_team));
                        } else {
                            //can't open this inventory
                        }
                    }
                }
            }
        }
    }

    public static Inventory forum(String  team){
        Inventory inv = Bukkit.createInventory(null, 54, "Forum");
        inv.setItem(0, Misc.getSkull("http://textures.minecraft.net/texture/71bc2bcfb2bd3759e6b1e86fc7a79585e1127dd357fc202893f9de241bc9e530", getMsg("forum.age1")));
        inv.setItem(1, Misc.getSkull("http://textures.minecraft.net/texture/1a4f68c8fb279e50ab786f9fa54c88ca4ecfe1eb5fd5f0c38c54c9b1c7203d7a", getMsg("forum.age-buldings")));
        inv.setItem(2, item(Material.ANVIL, (short) 0, getMsg("forum.forge.displayname"), getArray("forum.forge.lore"), true, hasBuild(built_forge, team)));
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
