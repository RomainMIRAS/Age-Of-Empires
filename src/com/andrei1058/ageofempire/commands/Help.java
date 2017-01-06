package com.andrei1058.ageofempire.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static com.andrei1058.ageofempire.Main.PREFIX;
import static com.andrei1058.ageofempire.Main.help;
import static com.andrei1058.ageofempire.configuration.Messages.getMsg;

public class Help implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender s, Command c, String st, String[] args) {
        if (c.getName().equalsIgnoreCase("help")){
            Player p = (Player) s;
            if (help.contains(p.getUniqueId())){
                help.remove(p.getUniqueId());
                p.sendMessage(PREFIX+" "+getMsg("help-item-off"));
                ItemStack i = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)14);
                ItemMeta itemMeta = i.getItemMeta();
                itemMeta.setDisplayName(getMsg("help-item-off"));
                i.setItemMeta(itemMeta);
                p.getInventory().setItem(4, i);
            } else {
                help.add(p.getUniqueId());
                p.sendMessage(PREFIX+" "+getMsg("help-item-on"));
                ItemStack i = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)5);
                ItemMeta itemMeta = i.getItemMeta();
                itemMeta.setDisplayName(getMsg("help-item-on"));
                i.setItemMeta(itemMeta);
                p.getInventory().setItem(4, i);
            }
        }
        return false;
    }
}
