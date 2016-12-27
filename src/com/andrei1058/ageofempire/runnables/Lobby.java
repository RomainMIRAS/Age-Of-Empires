package com.andrei1058.ageofempire.runnables;

import com.andrei1058.ageofempire.configuration.Settings;
import com.andrei1058.ageofempire.game.Status;
import com.andrei1058.ageofempire.locations.Locations;
import com.andrei1058.ageofempire.locations.Region;
import com.andrei1058.ageofempire.nms.RegisterNMS;
import com.andrei1058.ageofempire.nms.VillagerNMS;
import net.minecraft.server.v1_8_R3.EntityVillager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

import static com.andrei1058.ageofempire.Main.*;
import static com.andrei1058.ageofempire.configuration.Messages.getMsg;

public class Lobby extends BukkitRunnable {
    private static int last = lobby_time;
    @Override
    public void run() {
        if (lobby_time != 0){
            lobby_time--;
        }
        if (min_players >= Bukkit.getOnlinePlayers().size()){
            this.cancel();
            STATUS = Status.LOBBY;
            lobby_time = Settings.load().getInt("countdowns.lobby");
            return;
        }
        if (lobby_time != 0 && (last == lobby_time || lobby_time == last-10 || lobby_time < 6)){
            last-=10;
            Bukkit.broadcastMessage(getMsg("game-start").replace("{time}", String.valueOf(lobby_time)));
            for (Player p : Bukkit.getOnlinePlayers()){
                p.getWorld().playSound(p.getLocation(), Sound.CLICK, 1, 1);
            }
        }
        if (lobby_time == 0){
            cancel();
            STATUS = Status.PRE_GAME;

            ItemStack blue = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 11);
            ItemMeta bluemeta = blue.getItemMeta();
            bluemeta.setDisplayName(getMsg("team-choosing.blue"));
            blue.setItemMeta(bluemeta);

            ItemStack green = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5);
            ItemMeta greenmeta = green.getItemMeta();
            greenmeta.setDisplayName(getMsg("team-choosing.green"));
            green.setItemMeta(greenmeta);

            ItemStack yellow = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 4);
            ItemMeta yellowmeta = yellow.getItemMeta();
            yellowmeta.setDisplayName(getMsg("team-choosing.yellow"));
            yellow.setItemMeta(yellowmeta);

            ItemStack red = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
            ItemMeta redmeta = red.getItemMeta();
            redmeta.setDisplayName(getMsg("team-choosing.red"));
            red.setItemMeta(redmeta);

            for (Player p : Bukkit.getOnlinePlayers()){
                p.getInventory().clear();
                if (Bukkit.getOnlinePlayers().size() > max_in_team*3){
                    p.getInventory().setItem(0, blue);
                    p.getInventory().setItem(1, green);
                    p.getInventory().setItem(2, yellow);
                    p.getInventory().setItem(3, red);
                } else if (Bukkit.getOnlinePlayers().size() > max_in_team*2){
                    p.getInventory().setItem(0, blue);
                    p.getInventory().setItem(1, green);
                    p.getInventory().setItem(2, yellow);
                } else if (Bukkit.getOnlinePlayers().size() > max_in_team){
                    p.getInventory().setItem(0, blue);
                    p.getInventory().setItem(1, green);
                }
            }
            new PreGame().runTaskTimer(plugin, 0, 20);
        }
    }
}
