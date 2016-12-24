package com.andrei1058.ageofempire.listeners;

import com.andrei1058.ageofempire.Misc;
import com.andrei1058.ageofempire.game.Status;
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
import java.util.stream.Collectors;

import static com.andrei1058.ageofempire.Main.*;
import static com.andrei1058.ageofempire.configuration.Messages.getArray;
import static com.andrei1058.ageofempire.configuration.Messages.getMsg;
import static com.andrei1058.ageofempire.game.Buildings.*;

public class PlayerInteractEntityListener implements Listener {

    @EventHandler
    public void i(PlayerInteractEntityEvent e){
        if (SETUP) return;
        if (STATUS == Status.PLAYING){
            Player p  = e.getPlayer();
            if (e.getRightClicked().getType() == EntityType.VILLAGER) {
                Villager v = (Villager) e.getRightClicked();
                if (v.getCustomName() == null) return;
                e.setCancelled(true);
                if (v == blue_villager) {
                    if (bluePlayers.contains(e.getPlayer().getUniqueId())) {
                        e.getPlayer().openInventory(forum(blue_team));
                    }
                } else if (v == yellow_villager) {
                    if (yellowPlayers.contains(e.getPlayer().getUniqueId())) {
                        e.getPlayer().openInventory(forum(yellow_team));
                    }
                } else if (v == green_villager) {
                    if (greenPlayers.contains(e.getPlayer().getUniqueId())) {
                        e.getPlayer().openInventory(forum(green_team));
                    }
                } else if (v == red_villager) {
                    if (redPlayers.contains(e.getPlayer().getUniqueId())) {
                        e.getPlayer().openInventory(forum(red_team));
                    }
                } else if (blue_forge != null && v == blue_forge){
                    if (bluePlayers.contains(p.getUniqueId())) {
                        p.openInventory(forge());
                    }
                } else if (green_forge != null && v == green_forge){
                    if (greenPlayers.contains(p.getUniqueId())) {
                        p.openInventory(forge());
                    }
                } else if (yellow_forge != null && v == yellow_forge){
                    if (yellowPlayers.contains(p.getUniqueId())){
                        p.openInventory(forge());
                    }
                } else if (red_forge != null && v == red_forge) {
                    if (redPlayers.contains(p.getUniqueId())) {
                        p.openInventory(forge());
                    }
                } else if (v == blue_mill){
                    if (bluePlayers.contains(p.getUniqueId())){
                        p.openInventory(mill());
                    }
                } else if (v == green_mill){
                    if (greenPlayers.contains(p.getUniqueId())){
                        p.openInventory(mill());
                    }
                } else if (v == yellow_mill){
                    if (yellowPlayers.contains(p.getUniqueId())){
                        p.openInventory(mill());
                    }
                } else if (v == red_mill){
                    if (redPlayers.contains(p.getUniqueId())){
                        p.openInventory(mill());
                    }
                } else if (v == blue_workshop){
                    if (bluePlayers.contains(p.getUniqueId())){
                        p.openInventory(workshop());
                    }
                } else if (v == green_workshop){
                    if (greenPlayers.contains(p.getUniqueId())){
                        p.openInventory(workshop());
                    }
                } else if (v == yellow_workshop){
                    if (yellowPlayers.contains(p.getUniqueId())){
                        p.openInventory(workshop());
                    }
                } else if (v == red_workshop){
                    if (redPlayers.contains(p.getUniqueId())){
                        p.openInventory(workshop());
                    }
                } else if (v == blue_market){
                    if (bluePlayers.contains(p.getUniqueId())){
                        p.openInventory(market());
                    }
                } else if (v == green_market){
                    if (greenPlayers.contains(p.getUniqueId())){
                        p.openInventory(market());
                    }
                } else if (v == yellow_market){
                    if (yellowPlayers.contains(p.getUniqueId())){
                        p.openInventory(market());
                    }
                } else if (v == red_market){
                    if (redPlayers.contains(p.getUniqueId())){
                        p.openInventory(market());
                    }
                } else if (v == blue_sabotage){
                    if (bluePlayers.contains(p.getUniqueId())){
                        p.openInventory(sabotage());
                    }
                } else if (v == green_sabotage){
                    if (greenPlayers.contains(p.getUniqueId())){
                        p.openInventory(sabotage());
                    }
                } else if (v == yellow_sabotage){
                    if (yellowPlayers.contains(p.getUniqueId())){
                        p.openInventory(sabotage());
                    }
                } else if (v == red_sabotage){
                    if (redPlayers.contains(p.getUniqueId())){
                        p.openInventory(sabotage());
                    }
                } else if (v == blue_kennel){
                    if (bluePlayers.contains(p.getUniqueId())){
                        p.openInventory(kennel());
                    }
                } else if (v == green_kennel){
                    if (greenPlayers.contains(p.getUniqueId())){
                        p.openInventory(kennel());
                    }
                } else if (v == yellow_kennel){
                    if (yellowPlayers.contains(p.getUniqueId())){
                        p.openInventory(kennel());
                    }
                } else if (v == red_kennel){
                    if (redPlayers.contains(p.getUniqueId())){
                        p.openInventory(kennel());
                    }
                } else {
                    e.setCancelled(true);
                    e.getPlayer().sendMessage(getMsg("villager.cant-open"));
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
        inv.setItem(18, Misc.getSkull("http://textures.minecraft.net/texture/4cd9eeee883468881d83848a46bf3012485c23f75753b8fbe8487341419847", getMsg("forum.age2")));
        inv.setItem(19, Misc.getSkull("http://textures.minecraft.net/texture/1a4f68c8fb279e50ab786f9fa54c88ca4ecfe1eb5fd5f0c38c54c9b1c7203d7a", getMsg("forum.age-buldings")));
        return inv;
    }
    private static ItemStack item(Material material, short sh, String name, ArrayList<String> lore, boolean enchant, boolean built){
        ItemStack i = new ItemStack(material, 1, sh);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(name);
        ArrayList<String > l = lore.stream().map(s -> s.replace('&', '§')).collect(Collectors.toCollection(ArrayList::new));
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

    public static Inventory forge (){
        Inventory inv = Bukkit.createInventory(null, 54, "Forge");
        inv.addItem(forgeItem(Material.STONE_PICKAXE, getMsg("forge.stonepickaxe.displayname"), getArray("forge.stonepickaxe.lore")));
        inv.addItem(forgeItem(Material.STONE_SWORD, getMsg("forge.stonesword.displayname"), getArray("forge.stonesword.lore")));
        inv.addItem(forgeItem(Material.IRON_SWORD, getMsg("forge.ironsword.displayname"), getArray("forge.ironsword.lore")));
        inv.addItem(forgeItem(Material.STONE_AXE,getMsg("forge.stoneaxe.displayname"), getArray("forge.stoneaxe.lore")));
        inv.addItem(forgeItem(Material.IRON_AXE, getMsg("forge.ironaxe.displayname"), getArray("forge.ironaxe.lore")));
        return inv;
    }

    public static Inventory mill(){
        Inventory inv = Bukkit.createInventory(null, 54, "Mill");
        inv.addItem(forgeItem(Material.BREAD, getMsg("mill.bread.displayname"), getArray("mill.bread.lore")));
        inv.addItem(forgeItem(Material.POTATO, getMsg("mill.potato.displayname"), getArray("mill.potato.lore")));
        inv.addItem(forgeItem(Material.COOKED_BEEF, getMsg("mill.steak.displayname"), getArray("mill.steak.lore")));
        inv.addItem(forgeItem(Material.COOKED_CHICKEN, getMsg("mill.chicken.displayname"), getArray("mill.chicken.lore")));
        return inv;
    }

    public static Inventory sabotage(){
        Inventory inv = Bukkit.createInventory(null, 54, "Sabotage");
        inv.addItem(forgeItem(Material.TNT, getMsg("sabotage.tnt.displayname"), getArray("sabotage.tnt.lore")));
        return inv;
    }

    public static Inventory kennel(){
        Inventory inv = Bukkit.createInventory(null, 54, "Kennel");
        inv.addItem(forgeItem(Material.MONSTER_EGG, getMsg("kennel.dog.displayname"), getArray("kennel.dog.lore")));
        return inv;
    }

    public static Inventory market(){
        Inventory inv = Bukkit.createInventory(null, 54, "Market");
        inv.addItem(forgeItem(Material.FLINT_AND_STEEL, getMsg("market.flintandsteel.displayname"), getArray("market.flintandsteel.lore")));
        inv.addItem(forgeItem(Material.WEB, getMsg("market.cobweb.displayname"), getArray("market.cobweb.lore")));
        inv.addItem(forgeItem(Material.TORCH, getMsg("market.torches.displayname"), getArray("market.torches.lore")));
        inv.addItem(forgeItem(Material.BOAT, getMsg("market.boat.displayname"), getArray("market.boat.lore")));
        return inv;
    }

    public static Inventory workshop(){
        Inventory inv = Bukkit.createInventory(null, 54, "Workshop");
        inv.addItem(forgeItem(Material.GRASS, getMsg("workshop.grass.displayname"), getArray("workshop.grass.lore")));
        inv.addItem(forgeItem(Material.DIRT, getMsg("workshop.dirt.displayname"), getArray("workshop.dirt.lore")));
        inv.addItem(forgeItem(Material.WOOD_DOUBLE_STEP, getMsg("workshop.plank.displayname"), getArray("workshop.plank.lore")));
        inv.addItem(forgeItem(Material.SAND, getMsg("workshop.sand.displayname"), getArray("workshop.sand.lore")));
        inv.addItem(forgeItem(Material.GRAVEL, getMsg("workshop.gravel.displayname"), getArray("workshop.gravel.lore")));
        inv.addItem(forgeItem(Material.SPONGE, getMsg("workshop.sponge.displayname"), getArray("workshop.sponge.lore")));
        inv.addItem(forgeItem(Material.GLASS, getMsg("workshop.glass.displayname"), getArray("workshop.glass.lore")));
        inv.addItem(forgeItem(Material.LAPIS_BLOCK, getMsg("workshop.lapis.displayname"), getArray("workshop.lapis.lore")));
        inv.addItem(WOOLItem((short) 0, getMsg("workshop.whitewool.displayname"), getArray("workshop.whitewool.lore")));
        inv.addItem(WOOLItem((short) 1, getMsg("workshop.orangewool.displayname"), getArray("workshop.orangewool.lore")));
        inv.addItem(WOOLItem((short) 2, getMsg("workshop.magentawool.displayname"), getArray("workshop.magentawool.lore")));
        inv.addItem(WOOLItem((short) 3, getMsg("workshop.lightbluewool.displayname"), getArray("workshop.lightbluewool.lore")));
        inv.addItem(WOOLItem((short) 4, getMsg("workshop.yellowwool.displayname"), getArray("workshop.yellowwool.lore")));
        inv.addItem(WOOLItem((short) 5, getMsg("workshop.lightgreenwool.displayname"), getArray("workshop.lightgreenwool.lore")));
        inv.addItem(WOOLItem((short) 6, getMsg("workshop.pinkwool.displayname"), getArray("workshop.pinkwool.lore")));
        inv.addItem(WOOLItem((short) 7, getMsg("workshop.graywool.displayname"), getArray("workshop.graywool.lore")));
        inv.addItem(WOOLItem((short) 9, getMsg("workshop.cyanwool.displayname"), getArray("workshop.cyanwool.lore")));
        inv.addItem(WOOLItem((short) 10, getMsg("workshop.purplewool.displayname"), getArray("workshop.purplewool.lore")));
        inv.addItem(WOOLItem((short) 11, getMsg("workshop.bluewool.displayname"), getArray("workshop.bluewool.lore")));
        inv.addItem(WOOLItem((short) 15, getMsg("workshop.blackwool.displayname"), getArray("workshop.blackwool.lore")));
        inv.addItem(WOOLItem((short) 13, getMsg("workshop.greenwool.displayname"), getArray("workshop.greenwool.lore")));
        inv.addItem(WOOLItem((short) 14, getMsg("workshop.redwool.displayname"), getArray("workshop.redwool.lore")));
        inv.addItem(WOOLItem((short) 12, getMsg("workshop.brownwool.displayname"), getArray("workshop.brownwool.lore")));
        inv.addItem(forgeItem(Material.BRICK, getMsg("workshop.bricks.displayname"), getArray("workshop.bricks.lore")));
        inv.addItem(forgeItem(Material.MOSSY_COBBLESTONE, getMsg("workshop.mossstone.displayname"), getArray("workshop.mossstone.lore")));
        inv.addItem(forgeItem(Material.LEAVES, getMsg("workshop.leaves.displayname"), getArray("workshop.leaves.lore")));
        return inv;
    }

    private static ItemStack forgeItem(Material material, String name, ArrayList<String> lore){
        ItemStack i = new ItemStack(material);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(name);
        ArrayList<String> list = lore.stream().map(s -> s.replace('&', '§')).collect(Collectors.toCollection(ArrayList::new));
        im.setLore(list);
        i.setItemMeta(im);
        return i;
    }

    private static ItemStack WOOLItem(short sh, String name, ArrayList<String> lore){
        ItemStack i = new ItemStack(Material.WOOL, 1, sh);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(name);
        ArrayList<String> list = lore.stream().map(s -> s.replace('&', '§')).collect(Collectors.toCollection(ArrayList::new));
        im.setLore(list);
        i.setItemMeta(im);
        return i;
    }
}
