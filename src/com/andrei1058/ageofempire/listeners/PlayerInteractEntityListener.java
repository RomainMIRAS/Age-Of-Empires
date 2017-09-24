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
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

import java.util.ArrayList;
import java.util.UUID;
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
                    if (bluePlayers.contains(e.getPlayer())) {
                        e.getPlayer().openInventory(forum(blue_team));
                    }
                } else if (v == yellow_villager) {
                    if (yellowPlayers.contains(e.getPlayer())) {
                        e.getPlayer().openInventory(forum(yellow_team));
                    }
                } else if (v == green_villager) {
                    if (greenPlayers.contains(e.getPlayer())) {
                        e.getPlayer().openInventory(forum(green_team));
                    }
                } else if (v == red_villager) {
                    if (redPlayers.contains(e.getPlayer())) {
                        e.getPlayer().openInventory(forum(red_team));
                    }
                } else if (blue_forge != null && v == blue_forge){
                    if (bluePlayers.contains(p)) {
                        p.openInventory(forge(p));
                    }
                } else if (green_forge != null && v == green_forge){
                    if (greenPlayers.contains(p)) {
                        p.openInventory(forge(p));
                    }
                } else if (yellow_forge != null && v == yellow_forge){
                    if (yellowPlayers.contains(p)){
                        p.openInventory(forge(p));
                    }
                } else if (red_forge != null && v == red_forge) {
                    if (redPlayers.contains(p)) {
                        p.openInventory(forge(p));
                    }
                } else if (v == blue_mill){
                    if (bluePlayers.contains(p)){
                        p.openInventory(mill());
                    }
                } else if (v == green_mill){
                    if (greenPlayers.contains(p)){
                        p.openInventory(mill());
                    }
                } else if (v == yellow_mill){
                    if (yellowPlayers.contains(p)){
                        p.openInventory(mill());
                    }
                } else if (v == red_mill){
                    if (redPlayers.contains(p)){
                        p.openInventory(mill());
                    }
                } else if (v == blue_workshop){
                    if (bluePlayers.contains(p)){
                        p.openInventory(workshop());
                    }
                } else if (v == green_workshop){
                    if (greenPlayers.contains(p)){
                        p.openInventory(workshop());
                    }
                } else if (v == yellow_workshop){
                    if (yellowPlayers.contains(p)){
                        p.openInventory(workshop());
                    }
                } else if (v == red_workshop){
                    if (redPlayers.contains(p)){
                        p.openInventory(workshop());
                    }
                } else if (v == blue_market){
                    if (bluePlayers.contains(p)){
                        p.openInventory(market());
                    }
                } else if (v == green_market){
                    if (greenPlayers.contains(p)){
                        p.openInventory(market());
                    }
                } else if (v == yellow_market){
                    if (yellowPlayers.contains(p)){
                        p.openInventory(market());
                    }
                } else if (v == red_market){
                    if (redPlayers.contains(p)){
                        p.openInventory(market());
                    }
                } else if (v == blue_sabotage){
                    if (bluePlayers.contains(p)){
                        p.openInventory(sabotage());
                    }
                } else if (v == green_sabotage){
                    if (greenPlayers.contains(p)){
                        p.openInventory(sabotage());
                    }
                } else if (v == yellow_sabotage){
                    if (yellowPlayers.contains(p)){
                        p.openInventory(sabotage());
                    }
                } else if (v == red_sabotage){
                    if (redPlayers.contains(p)){
                        p.openInventory(sabotage());
                    }
                } else if (v == blue_kennel){
                    if (bluePlayers.contains(p)){
                        p.openInventory(kennel(p));
                    }
                } else if (v == green_kennel){
                    if (greenPlayers.contains(p)){
                        p.openInventory(kennel(p));
                    }
                } else if (v == yellow_kennel){
                    if (yellowPlayers.contains(p)){
                        p.openInventory(kennel(p));
                    }
                } else if (v == red_kennel){
                    if (redPlayers.contains(p)){
                        p.openInventory(kennel(p));
                    }
                } else if (v == blue_archery){
                    if (bluePlayers.contains(p)){
                        p.openInventory(archeryInv());
                    }
                } else if (v == green_archery){
                    if (greenPlayers.contains(p)){
                        p.openInventory(archeryInv());
                    }
                } else if (v == yellow_archery){
                    if (yellowPlayers.contains(p)){
                        p.openInventory(archeryInv());
                    }
                } else if (v == red_archery){
                    if (redPlayers.contains(p)){
                        p.openInventory(archeryInv());
                    }
                } else if (v == blue_trifarrow){
                    if (bluePlayers.contains(p)){
                        p.openInventory(trifarrowInv());
                    }
                } else if (v == green_trifarrow){
                    if (greenPlayers.contains(p)){
                        p.openInventory(trifarrowInv());
                    }
                } else if (v == yellow_trifarrow){
                    if (yellowPlayers.contains(p)){
                        p.openInventory(trifarrowInv());
                    }
                } else if (v == red_trifarrow){
                    if (redPlayers.contains(p)){
                        p.openInventory(trifarrowInv());
                    }
                } else if (v == blue_stable){
                    if (bluePlayers.contains(p)){
                        p.openInventory(stableInv());
                    }
                } else if (v == green_stable){
                    if (greenPlayers.contains(p)){
                        p.openInventory(stableInv());
                    }
                } else if (v == yellow_stable){
                    if (yellowPlayers.contains(p)){
                        p.openInventory(stableInv());
                    }
                } else if (v == red_stable){
                    if (redPlayers.contains(p)){
                        p.openInventory(stableInv());
                    }
                } else if (v == blue_armory){
                    if (bluePlayers.contains(p)){
                        p.openInventory(armoryInv(p));
                    }
                } else if (v == green_armory){
                    if (greenPlayers.contains(p)){
                        p.openInventory(armoryInv(p));
                    }
                } else if (v == yellow_armory){
                    if (yellowPlayers.contains(p)){
                        p.openInventory(armoryInv(p));
                    }
                } else if (v == red_armory){
                    if (redPlayers.contains(p)){
                        p.openInventory(armoryInv(p));
                    }
                } else if (v == blue_lab){
                    if (bluePlayers.contains(p)){
                        p.openInventory(lab());
                    }
                } else if (v == green_lab){
                    if (greenPlayers.contains(p)){
                        p.openInventory(lab());
                    }
                } else if (v == yellow_lab){
                    if (yellowPlayers.contains(p)){
                        p.openInventory(lab());
                    }
                } else if (v == red_lab){
                    if (redPlayers.contains(p)){
                        p.openInventory(lab());
                    }
                } else if (v == blue_guild){
                    if (bluePlayers.contains(p)){
                        p.openInventory(guild());
                    }
                } else if (v == green_guild){
                    if (greenPlayers.contains(p)){
                        p.openInventory(guild());
                    }
                } else if (v == yellow_guild){
                    if (yellowPlayers.contains(p)){
                        p.openInventory(guild());
                    }
                } else if (v == red_guild){
                    if (redPlayers.contains(p)){
                        p.openInventory(guild());
                    }
                } else {
                    e.setCancelled(true);
                    e.getPlayer().sendMessage(getMsg("villager.cant-open"));
                }
            }
        }
    }

    private static Inventory guild(){
        Inventory inv = Bukkit.createInventory(null, 54, "Guild");
        inv.addItem(enchant(Enchantment.DAMAGE_ALL, "guild.sharpness.name", "guild.sharpness.lore"));
        inv.addItem(enchant(Enchantment.KNOCKBACK, "guild.knockback.name", "guild.knockback.lore"));
        inv.addItem(enchant(Enchantment.PROTECTION_ENVIRONMENTAL, "guild.protection.name", "guild.protection.lore"));
        inv.addItem(enchant(Enchantment.THORNS, "guild.thorns.name", "guild.thorns.lore"));
        inv.addItem(enchant(Enchantment.LURE, "guild.featherfalling.name", "guild.featherfalling.lore"));
        inv.addItem(enchant(Enchantment.PROTECTION_PROJECTILE, "guild.projectileprotection.name", "guild.projectileprotection.lore"));
        inv.addItem(enchant(Enchantment.PROTECTION_FIRE, "guild.fireprotection.name", "guild.fireprotection.lore"));
        inv.addItem(enchant(Enchantment.ARROW_DAMAGE, "guild.power.name", "guild.power.lore"));
        inv.addItem(enchant(Enchantment.ARROW_KNOCKBACK, "guild.punch.name", "guild.punch.lore"));
        return inv;
    }

    private static ItemStack enchant(Enchantment enchantment, String name, String lore){

        ItemStack i = new ItemStack(Material.ENCHANTED_BOOK);
        EnchantmentStorageMeta m = (EnchantmentStorageMeta) i.getItemMeta();
        m.setDisplayName(getMsg(name));
        m.setLore(getArray(lore).stream().map(s -> s.replace('&', '§')).collect(Collectors.toCollection(ArrayList::new)));
        m.addEnchant(enchantment,1,true);
        i.setItemMeta(m);
        return i;
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
        inv.setItem(20, ageBuilding(team, Material.BOW, getMsg("forum."+archery+".displayname"), getArray("forum."+archery+".lore"), hasBuild(archery, team), true, 2));
        inv.setItem(21, ageBuilding(team, Material.ARROW, getMsg("forum."+trifarrow+".displayname"), getArray("forum."+trifarrow+".lore"), hasBuild(trifarrow, team), true, 2));
        inv.setItem(22, ageBuilding(team, Material.SADDLE, getMsg("forum."+stable+".displayname"), getArray("forum."+stable+".lore"), hasBuild(stable, team), true, 2));
        inv.setItem(23, ageBuilding(team, Material.DIAMOND_CHESTPLATE, getMsg("forum."+armory+".displayname"), getArray("forum."+armory+".lore"), hasBuild(armory, team), true, 2));
        inv.setItem(24, ageBuilding(team, Material.BREWING_STAND_ITEM, getMsg("forum."+laboratory+".displayname"), getArray("forum."+laboratory+".lore"), hasBuild(laboratory, team), true, 2));
        inv.setItem(27, Misc.getSkull("http://textures.minecraft.net/texture/1d4eae13933860a6df5e8e955693b95a8c3b15c36b8b587532ac0996bc37e5", getMsg("forum.age3")));
        inv.setItem(28, Misc.getSkull("http://textures.minecraft.net/texture/1a4f68c8fb279e50ab786f9fa54c88ca4ecfe1eb5fd5f0c38c54c9b1c7203d7a", getMsg("forum.age-buldings")));
        inv.setItem(29, ageBuilding(team, Material.ENCHANTED_BOOK, getMsg("forum."+guild+".displayname"), getArray("forum."+guild+".lore"), hasBuild(guild, team), true, 3));
        inv.setItem(30, ageBuilding(team, Material.EXP_BOTTLE, getMsg("forum."+training_center+".displayname"), getArray("forum."+training_center+".lore"), hasBuild(training_center, team), true, 3));

        inv.setItem(48, Misc.getSkull("http://textures.minecraft.net/texture/1a4f68c8fb279e50ab786f9fa54c88ca4ecfe1eb5fd5f0c38c54c9b1c7203d7a", "->"));
        inv.setItem(49, ageDoor(team));
        inv.setItem(50, Misc.getSkull("http://textures.minecraft.net/texture/737648ae7a564a5287792b05fac79c6b6bd47f616a559ce8b543e6947235bce", "<-"));
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

    public static ItemStack ageDoor(String team){
        ItemStack i = new ItemStack(Material.BEDROCK);
        ItemMeta im = i.getItemMeta();
        switch (team){
            case blue_team:
                switch (blue_age){
                    case 1:
                        i = new ItemStack(Material.DARK_OAK_DOOR_ITEM);
                        im.setDisplayName(getMsg("forum.age2"));
                        im.setLore(getArray("change.age2-lore").stream().map(s -> s.replace('&', '§')).collect(Collectors.toCollection(ArrayList::new)));
                        break;
                    case 2:
                        i = new ItemStack(Material.IRON_DOOR);
                        im.setDisplayName(getMsg("forum.age3"));
                        im.setLore(getArray("change.age3-lore").stream().map(s -> s.replace('&', '§')).collect(Collectors.toCollection(ArrayList::new)));
                        break;
                    case 3:
                        i = new ItemStack(Material.DIAMOND);
                        im.setDisplayName(getMsg("forum.age4"));
                        im.setLore(getArray("change.age4-lore").stream().map(s -> s.replace('&', '§')).collect(Collectors.toCollection(ArrayList::new)));
                        break;
                    case 4:
                        im.setDisplayName("-");
                        break;
                }
                break;
            case green_team:
                switch (green_age){
                    case 1:
                        i = new ItemStack(Material.DARK_OAK_DOOR_ITEM);
                        im.setDisplayName(getMsg("forum.age2"));
                        im.setLore(getArray("change.age2-lore").stream().map(s -> s.replace('&', '§')).collect(Collectors.toCollection(ArrayList::new)));
                        break;
                    case 2:
                        i = new ItemStack(Material.IRON_DOOR);
                        im.setDisplayName(getMsg("forum.age3"));
                        im.setLore(getArray("change.age3-lore").stream().map(s -> s.replace('&', '§')).collect(Collectors.toCollection(ArrayList::new)));
                        break;
                    case 3:
                        i = new ItemStack(Material.DIAMOND);
                        im.setDisplayName(getMsg("forum.age4"));
                        im.setLore(getArray("change.age4-lore").stream().map(s -> s.replace('&', '§')).collect(Collectors.toCollection(ArrayList::new)));
                        break;
                    case 4:
                        im.setDisplayName("-");
                        break;
                }
                break;
            case red_team:
                switch (red_age){
                    case 1:
                        i = new ItemStack(Material.DARK_OAK_DOOR_ITEM);
                        im.setDisplayName(getMsg("forum.age2"));
                        im.setLore(getArray("change.age2-lore").stream().map(s -> s.replace('&', '§')).collect(Collectors.toCollection(ArrayList::new)));
                        break;
                    case 2:
                        i = new ItemStack(Material.IRON_DOOR);
                        im.setDisplayName(getMsg("forum.age3"));
                        im.setLore(getArray("change.age3-lore").stream().map(s -> s.replace('&', '§')).collect(Collectors.toCollection(ArrayList::new)));
                        break;
                    case 3:
                        i = new ItemStack(Material.DIAMOND);
                        im.setDisplayName(getMsg("forum.age4"));
                        im.setLore(getArray("change.age4-lore").stream().map(s -> s.replace('&', '§')).collect(Collectors.toCollection(ArrayList::new)));
                        break;
                    case 4:
                        im.setDisplayName("-");
                        break;
                }
                break;
            case yellow_team:
                switch (yellow_age){
                    case 1:
                        i = new ItemStack(Material.DARK_OAK_DOOR_ITEM);
                        im.setDisplayName(getMsg("forum.age2"));
                        im.setLore(getArray("change.age2-lore").stream().map(s -> s.replace('&', '§')).collect(Collectors.toCollection(ArrayList::new)));
                        break;
                    case 2:
                        i = new ItemStack(Material.IRON_DOOR);
                        im.setDisplayName(getMsg("forum.age3"));
                        im.setLore(getArray("change.age3-lore").stream().map(s -> s.replace('&', '§')).collect(Collectors.toCollection(ArrayList::new)));
                        break;
                    case 3:
                        i = new ItemStack(Material.DIAMOND);
                        im.setDisplayName(getMsg("forum.age4"));
                        im.setLore(getArray("change.age4-lore").stream().map(s -> s.replace('&', '§')).collect(Collectors.toCollection(ArrayList::new)));
                        break;
                    case 4:
                        im.setDisplayName("-");
                        break;
                }
                break;
        }
        i.setItemMeta(im);
        return i;
    }

    public static ItemStack ageBuilding(String team, Material material, String name, ArrayList<String> lore, boolean built, boolean enchant, Integer age){

        ItemStack i = new ItemStack(material);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(name);
        ArrayList<String > l = lore.stream().map(s -> s.replace('&', '§')).collect(Collectors.toCollection(ArrayList::new));
        if (built){
            l.add(getMsg("forum.built"));
        } else {
            l.add(getMsg("forum.to-build"));
        }
        if (enchant) {
            im.addEnchant(Enchantment.DURABILITY, 1, true);
            im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        im.setLore(l);
        i.setItemMeta(im);

        ItemStack i2 = new ItemStack(Material.BARRIER);
        ItemMeta im2 = i2.getItemMeta();
        im2.setDisplayName(name);
        im2.setLore(l);
        i2.setItemMeta(im2);

        switch (team){
            case blue_team:
                if (blue_age >= age){
                    return i;
                }
                break;
            case green_team:
                if (green_age >= age){
                    return i;
                }
                break;
            case yellow_team:
                if (yellow_age >= age){
                    return i;
                }
                break;
            case red_team:
                if (red_age >= age){
                    return i;
                }
                break;
        }
        return i2;
    }

    public static Inventory forge (Player p){
        Inventory inv = Bukkit.createInventory(null, 54, "Forge");
        inv.addItem(forgeItem(Material.STONE_PICKAXE, getMsg("forge.stonepickaxe.displayname"), getArray("forge.stonepickaxe.lore")));
        inv.addItem(forgeItem(Material.STONE_SWORD, getMsg("forge.stonesword.displayname"), getArray("forge.stonesword.lore")));
        inv.addItem(forgeItem(Material.IRON_SWORD, getMsg("forge.ironsword.displayname"), getArray("forge.ironsword.lore")));
        inv.addItem(forgeItem(Material.STONE_AXE,getMsg("forge.stoneaxe.displayname"), getArray("forge.stoneaxe.lore")));
        inv.addItem(forgeItem(Material.IRON_AXE, getMsg("forge.ironaxe.displayname"), getArray("forge.ironaxe.lore")));
        if (bluePlayers.contains(p)) {
            if (blue_age > 2) {
                inv.addItem(forgeItem(Material.DIAMOND_SWORD, getMsg("forge.diamondsword.displayname"), getArray("forge.diamondsword.lore")));
                inv.addItem(forgeItem(Material.DIAMOND_AXE, getMsg("forge.diamondaxe.displayname"), getArray("forge.diamondaxe.lore")));
            }
        } else if (greenPlayers.contains(p)){
            if (green_age > 2){
                inv.addItem(forgeItem(Material.DIAMOND_SWORD, getMsg("forge.diamondsword.displayname"), getArray("forge.diamondsword.lore")));
                inv.addItem(forgeItem(Material.DIAMOND_AXE, getMsg("forge.diamondaxe.displayname"), getArray("forge.diamondaxe.lore")));
            }
        } else if (yellowPlayers.contains(p)){
            if (yellow_age > 2){
                inv.addItem(forgeItem(Material.DIAMOND_SWORD, getMsg("forge.diamondsword.displayname"), getArray("forge.diamondsword.lore")));
                inv.addItem(forgeItem(Material.DIAMOND_AXE, getMsg("forge.diamondaxe.displayname"), getArray("forge.diamondaxe.lore")));
            }
        } else if (redPlayers.contains(p)){
            if (red_age > 2){
                inv.addItem(forgeItem(Material.DIAMOND_SWORD, getMsg("forge.diamondsword.displayname"), getArray("forge.diamondsword.lore")));
                inv.addItem(forgeItem(Material.DIAMOND_AXE, getMsg("forge.diamondaxe.displayname"), getArray("forge.diamondaxe.lore")));
            }
        }
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

    public static Inventory armoryInv(Player u){
        Inventory inv = Bukkit.createInventory(null, 54, "Armory");
        ItemStack ia = new ItemStack(Material.IRON_CHESTPLATE);
        ItemMeta iam = ia.getItemMeta();
        iam.setDisplayName(getMsg("armory.ironarmor.displayname"));
        iam.setLore(getArray("armory.ironarmor.lore").stream().map(s -> s.replace('&', '§')).collect(Collectors.toCollection(ArrayList::new)));
        ia.setItemMeta(iam);
        inv.addItem(ia);
        ItemStack iha = new ItemStack(Material.IRON_BARDING);
        ItemMeta iham = iha.getItemMeta();
        iham.setDisplayName(getMsg("armory.ironhorsearmor.displayname"));
        iham.setLore(getArray("armory.ironhorsearmor.lore").stream().map(s -> s.replace('&','§')).collect(Collectors.toCollection(ArrayList::new)));
        iha.setItemMeta(iham);
        inv.addItem(iha);
        if (bluePlayers.contains(u)){
            if (blue_age > 2) {
                inv.addItem(forgeItem(Material.DIAMOND_CHESTPLATE, getMsg("armory.diamondarmor.displayname"), getArray("armory.diamondarmor.lore")));
                inv.addItem(forgeItem(Material.DIAMOND_BARDING, getMsg("armory.diamondhorsearmor.displayname"), getArray("armory.diamondhorsearmor.lore")));
            }
        } else if (redPlayers.contains(u)){
            if (red_age > 2) {
                inv.addItem(forgeItem(Material.DIAMOND_CHESTPLATE, getMsg("armory.diamondarmor.displayname"), getArray("armory.diamondarmor.lore")));
                inv.addItem(forgeItem(Material.DIAMOND_BARDING, getMsg("armory.diamondhorsearmor.displayname"), getArray("armory.diamondhorsearmor.lore")));
            }
        } else if (greenPlayers.contains(u)){
            if (green_age > 2) {
                inv.addItem(forgeItem(Material.DIAMOND_CHESTPLATE, getMsg("armory.diamondarmor.displayname"), getArray("armory.diamondarmor.lore")));
                inv.addItem(forgeItem(Material.DIAMOND_BARDING, getMsg("armory.diamondhorsearmor.displayname"), getArray("armory.diamondhorsearmor.lore")));
            }
        } else if (yellowPlayers.contains(u)){
            if (yellow_age > 2) {
                inv.addItem(forgeItem(Material.DIAMOND_CHESTPLATE, getMsg("armory.diamondarmor.displayname"), getArray("armory.diamondarmor.lore")));
                inv.addItem(forgeItem(Material.DIAMOND_BARDING, getMsg("armory.diamondhorsearmor.displayname"), getArray("armory.diamondhorsearmor.lore")));
            }
        }
        return inv;
    }

    public static Inventory sabotage(){
        Inventory inv = Bukkit.createInventory(null, 54, "Sabotage");
        inv.addItem(forgeItem(Material.TNT, getMsg("sabotage.tnt.displayname"), getArray("sabotage.tnt.lore")));
        return inv;
    }

    public static Inventory kennel(Player u){
        Inventory inv = Bukkit.createInventory(null, 54, "Kennel");
        inv.addItem(forgeItem(Material.MONSTER_EGG, getMsg("kennel.dog.displayname"), getArray("kennel.dog.lore")));
        if (bluePlayers.contains(u)){
            if (blue_age > 2){
                inv.addItem(forgeItem(Material.MONSTER_EGG, getMsg("kennel.dog2.displayname"), getArray("kennel.dog2.lore")));
                inv.addItem(forgeItem(Material.MONSTER_EGG, getMsg("kennel.dog3.displayname"), getArray("kennel.dog3.lore")));
            }
        } else if (greenPlayers.contains(u)){
            if (green_age > 2){
                inv.addItem(forgeItem(Material.MONSTER_EGG, getMsg("kennel.dog2.displayname"), getArray("kennel.dog2.lore")));
                inv.addItem(forgeItem(Material.MONSTER_EGG, getMsg("kennel.dog3.displayname"), getArray("kennel.dog3.lore")));
            }
        } else if (yellowPlayers.contains(u)){
            if (yellow_age > 2){
                inv.addItem(forgeItem(Material.MONSTER_EGG, getMsg("kennel.dog2.displayname"), getArray("kennel.dog2.lore")));
                inv.addItem(forgeItem(Material.MONSTER_EGG, getMsg("kennel.dog3.displayname"), getArray("kennel.dog3.lore")));
            }
        } else if (redPlayers.contains(u)){
            if (red_age > 2){
                inv.addItem(forgeItem(Material.MONSTER_EGG, getMsg("kennel.dog2.displayname"), getArray("kennel.dog2.lore")));
                inv.addItem(forgeItem(Material.MONSTER_EGG, getMsg("kennel.dog3.displayname"), getArray("kennel.dog3.lore")));
            }
        }
        return inv;
    }

    private static Inventory trifarrowInv(){
        Inventory inv = Bukkit.createInventory(null, 54, "TrifArrow");
        ItemStack i = new ItemStack(Material.ARROW);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(getMsg("trifarrow.arrow.displayname"));
        im.setLore(getArray("trifarrow.arrow.lore").stream().map(s -> s.replace('&','§')).collect(Collectors.toCollection(ArrayList::new)));
        i.setItemMeta(im);
        inv.addItem(i);

        return inv;
    }

    private static Inventory lab(){
        Inventory inv = Bukkit.createInventory(null, 54, "Laboratory");
        inv.addItem(labItem(PotionType.SPEED, "lab.swiftness.name", "lab.swiftness.lore", false));
        inv.addItem(labItem(PotionType.FIRE_RESISTANCE, "lab.fireresistance.name", "lab.fireresistance.lore", false));
        inv.addItem(labItem(PotionType.INSTANT_HEAL, "lab.healing.name", "lab.healing.lore", false));
        inv.addItem(labItem(PotionType.NIGHT_VISION, "lab.nightvision.name", "lab.nightvision.lore", false));
        inv.addItem(labItem(PotionType.JUMP, "lab.leaping.name", "lab.leaping.lore", false));
        inv.addItem(labItem(PotionType.WATER_BREATHING, "lab.waterbreathing.name", "lab.waterbreathing.lore", false));
        inv.addItem(labItem(PotionType.SPEED, "lab.splashswiftness.name", "lab.splashswiftness.lore", true));
        inv.addItem(labItem(PotionType.REGEN, "lab.regeneration.name", "lab.regeneration.lore", false));
        inv.addItem(labItem(PotionType.JUMP, "lab.splashleaping.name", "lab.splashleaping.lore", true));
        return inv;
    }

    private static ItemStack labItem(PotionType potionType, String name, String lore, boolean splash){
        Potion p = new Potion(potionType);
        p.setSplash(splash);
        ItemStack i = p.toItemStack(1);
        ItemMeta itemMeta = i.getItemMeta();
        itemMeta.setDisplayName(getMsg(name));
        itemMeta.setLore(getArray(lore).stream().map(s -> s.replace('&', '§')).collect(Collectors.toCollection(ArrayList::new)));
        i.setItemMeta(itemMeta);
        return i;
    }

    public static Inventory market(){
        Inventory inv = Bukkit.createInventory(null, 54, "Market");
        inv.addItem(forgeItem(Material.FLINT_AND_STEEL, getMsg("market.flintandsteel.displayname"), getArray("market.flintandsteel.lore")));
        inv.addItem(forgeItem(Material.WEB, getMsg("market.cobweb.displayname"), getArray("market.cobweb.lore")));
        inv.addItem(forgeItem(Material.TORCH, getMsg("market.torches.displayname"), getArray("market.torches.lore")));
        inv.addItem(forgeItem(Material.BOAT, getMsg("market.boat.displayname"), getArray("market.boat.lore")));
        return inv;
    }

    private static Inventory stableInv(){
        Inventory inv = Bukkit.createInventory(null, 54, "Stable");
        ItemStack i = new ItemStack(Material.MONSTER_EGG, 1, (short) 100);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(getMsg("stable.horse.displayname"));
        im.setLore(getArray("stable.horse.lore").stream().map( s -> s.replace('&','§')).collect(Collectors.toCollection(ArrayList::new)));
        i.setItemMeta(im);
        inv.addItem(i);
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

    private static Inventory archeryInv(){
        Inventory inv = Bukkit.createInventory(null, 54, "Archery Store");

        ItemStack bow = new ItemStack(Material.BOW);
        ItemMeta bowMeta = bow.getItemMeta();
        bowMeta.setDisplayName(getMsg("archery.bow.displayname"));
        bowMeta.setLore(getArray("archery.bow.lore").stream().map(s -> s.replace('&', '§')).collect(Collectors.toCollection(ArrayList::new)));
        bow.setItemMeta(bowMeta);
        inv.addItem(bow);

        ItemStack arrows5 = new ItemStack(Material.ARROW);
        ItemMeta aroows5Meta = arrows5.getItemMeta();
        aroows5Meta.setDisplayName(getMsg("archery.arrows5.displayname"));
        aroows5Meta.setLore(getArray("archery.arrows5.lore").stream().map(s -> s.replace('&', '§')).collect(Collectors.toCollection(ArrayList::new)));
        arrows5.setItemMeta(aroows5Meta);
        inv.addItem(arrows5);

        ItemStack a10 = new ItemStack(Material.ARROW);
        ItemMeta aMeta = arrows5.getItemMeta();
        aMeta.setDisplayName(getMsg("archery.arrows10.displayname"));
        aMeta.setLore(getArray("archery.arrows10.lore").stream().map(s -> s.replace('&', '§')).collect(Collectors.toCollection(ArrayList::new)));
        a10.setItemMeta(aMeta);
        inv.addItem(a10);

        return inv;
    }
}
