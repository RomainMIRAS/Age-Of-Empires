package com.andrei1058.ageofempire.game;

import com.andrei1058.ageofempire.configuration.Messages;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.stream.Collectors;

import static com.andrei1058.ageofempire.Main.kits;
import static com.andrei1058.ageofempire.configuration.Messages.getArray;

public enum Kits {
    MINER, LUMBERJACK, CUPIDITY;

    private Material iconForKit(Kits k){
        switch (k){
            case MINER:
                return Material.DIAMOND_PICKAXE;
            case LUMBERJACK:
                return Material.DIAMOND_AXE;
            case CUPIDITY:
                return Material.GOLD_INGOT;
            default:
                return Material.CHEST;
        }
    }

    public static Inventory kitSelector(Player p){
        Kits currentKit = kits.get(p);
        String title;
        if (currentKit == null){
            title = Messages.getMsg("current-kit").replace("{kit}", Messages.getMsg("none"));
        } else {
            title = Messages.getMsg("current-kit").replace("{kit}", Messages.getMsg("kit." + currentKit.name() + ".name"));
        }

        Inventory inv = Bukkit.createInventory(null, 9, title);
        for (Kits kit : Kits.values()) {
            ItemStack item = new ItemStack(kit.iconForKit(kit));
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(Messages.getMsg("kit." + kit.name() + ".displayname"));
            meta.setLore(getArray("kit." + kit.name() + ".lore").stream().map(s -> s.replace('&', '§')).collect(Collectors.toList()));
            item.setItemMeta(meta);
            inv.addItem(item);
        }

        return inv;
    }
}
