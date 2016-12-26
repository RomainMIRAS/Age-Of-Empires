package com.andrei1058.ageofempire.game;

import com.andrei1058.ageofempire.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Team;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import static com.andrei1058.ageofempire.Main.*;
import static com.andrei1058.ageofempire.Main.green_stone;
import static com.andrei1058.ageofempire.configuration.Messages.getMsg;
import static com.andrei1058.ageofempire.runnables.Game.*;

public class Scoreboard {
    private static Team blue, green, yellow, red, age, wood, stone, gold, small, medium, large, pvp_assault;
    public static org.bukkit.scoreboard.Scoreboard board;
    public static Objective objective;
    public static SimpleDateFormat df = new SimpleDateFormat("mm:ss");
    public static long blue_age_long = 0, green_age_long = 0, yellow_age_long = 0, red_age_long = 0;

    public static void register() {
        if (SETUP) return;
        for (UUID u : players) {
            board = Bukkit.getScoreboardManager().getNewScoreboard();
            objective = board.registerNewObjective("Test", "Test2");
            objective.setDisplayName(getMsg("scoreboard.title").replace('&', '§'));
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);

            blue = board.registerNewTeam("blue");
            blue.setPrefix(ChatColor.BLUE.toString());

            green = board.registerNewTeam("green");
            green.setPrefix(ChatColor.GREEN.toString());

            yellow = board.registerNewTeam("yellow");
            yellow.setPrefix(ChatColor.YELLOW.toString());

            red = board.registerNewTeam("red");
            red.setPrefix(ChatColor.RED.toString());

            age = board.registerNewTeam("age");
            age.addEntry(getMsg("scoreboard.14"));
            age.setPrefix(ChatColor.DARK_GRAY.toString());
            objective.getScore(getMsg("scoreboard.14")).setScore(14);

            objective.getScore(getMsg("scoreboard.13")).setScore(13);

            wood = board.registerNewTeam("wood");
            wood.addEntry(getMsg("scoreboard.12"));
            wood.setPrefix(ChatColor.DARK_BLUE.toString());
            objective.getScore(getMsg("scoreboard.12")).setScore(12);

            stone = board.registerNewTeam("stone");
            stone.addEntry(getMsg("scoreboard.11"));
            stone.setPrefix(ChatColor.DARK_GREEN.toString());
            objective.getScore(getMsg("scoreboard.11")).setScore(11);


            gold = board.registerNewTeam("gold");
            gold.addEntry(getMsg("scoreboard.10"));
            gold.setPrefix(ChatColor.DARK_RED.toString());
            objective.getScore(getMsg("scoreboard.10")).setScore(10);

            objective.getScore(getMsg("scoreboard.9")).setScore(9);
            objective.getScore(getMsg("scoreboard.8")).setScore(8);

            small = board.registerNewTeam("small");
            small.addEntry(getMsg("scoreboard.7"));
            small.setPrefix(ChatColor.GREEN.toString());
            objective.getScore(getMsg("scoreboard.7")).setScore(7);

            medium = board.registerNewTeam("medium");
            medium.addEntry(getMsg("scoreboard.6"));
            medium.setPrefix(ChatColor.GOLD.toString());
            objective.getScore(getMsg("scoreboard.6")).setScore(6);

            large = board.registerNewTeam("large");
            large.addEntry(getMsg("scoreboard.5"));
            large.setPrefix(ChatColor.GRAY.toString());
            objective.getScore(getMsg("scoreboard.5")).setScore(5);

            objective.getScore(getMsg("scoreboard.4")).setScore(4);

            pvp_assault = board.registerNewTeam("pvp_assault");
            pvp_assault.addEntry(ChatColor.MAGIC.toString()+ChatColor.RESET.toString());
            pvp_assault.setPrefix(getMsg("scoreboard.3"));
            objective.getScore(ChatColor.MAGIC.toString()+ChatColor.RESET.toString()).setScore(3);

            objective.getScore(getMsg("scoreboard.2")).setScore(2);
            objective.getScore(getMsg("scoreboard.1")).setScore(1);

            for (UUID blue2 : bluePlayers) {
                blue.addEntry(Bukkit.getPlayer(blue2).getName());
            }
            for (UUID green2 : greenPlayers) {
                green.addEntry(Bukkit.getPlayer(green2).getName());
            }
            for (UUID yellow2 : yellowPlayers) {
                yellow.addEntry(Bukkit.getPlayer(yellow2).getName());
            }
            for (UUID red2 : redPlayers) {
                red.addEntry(Bukkit.getPlayer(red2).getName());
            }
            Bukkit.getPlayer(u).setScoreboard(board);
        }
    }

    public static void Refresh(){
        for (UUID p : players){
            Team a = Bukkit.getPlayer(p).getScoreboard().getTeam("age");
            Team w = Bukkit.getPlayer(p).getScoreboard().getTeam("wood");
            Team s = Bukkit.getPlayer(p).getScoreboard().getTeam("stone");
            Team g = Bukkit.getPlayer(p).getScoreboard().getTeam("gold");
            Team sm = Bukkit.getPlayer(p).getScoreboard().getTeam("small");
            Team m = Bukkit.getPlayer(p).getScoreboard().getTeam("medium");
            Team l = Bukkit.getPlayer(p).getScoreboard().getTeam("large");
            Team pvp = Bukkit.getPlayer(p).getScoreboard().getTeam("pvp_assault");
            if (bluePlayers.contains(p)){
                if (blue_change_age){
                    a.setSuffix(String.valueOf(df.format(new Date(blue_age_long))));
                    blue_age_long -=1000;
                    if (blue_age_long == 0){
                        blue_age++;
                        blue_change_age = false;
                        Bukkit.broadcastMessage(getMsg("blue-changed-age").replace("{age}", String.valueOf(blue_age)));
                        for (Player on : Bukkit.getOnlinePlayers()){
                            on.getWorld().playSound(on.getLocation(), Sound.WITHER_DEATH, 1, 1);
                        }
                    }
                } else {
                    a.setSuffix("§e"+String.valueOf(blue_age));
                }
                w.setSuffix(String.valueOf(blue_wood));
                s.setSuffix(String.valueOf(blue_stone));
                g.setSuffix(String.valueOf(blue_gold));
                sm.setSuffix(String.valueOf(blue_small_plots));
                m.setSuffix(String.valueOf(blue_medium_plots));
                l.setSuffix(String.valueOf(blue_large_plots));
            } else if (greenPlayers.contains(p)){
                if (green_change_age){
                    a.setSuffix(String.valueOf(df.format(new Date(green_age_long))));
                    green_age_long -=1000;
                    if (green_age_long == 0){
                        green_age++;
                        green_change_age = false;
                        Bukkit.broadcastMessage(getMsg("green-changed-age").replace("{age}", String.valueOf(green_age)));
                        for (Player on : Bukkit.getOnlinePlayers()){
                            on.getWorld().playSound(on.getLocation(), Sound.WITHER_DEATH, 1, 1);
                        }
                    }
                } else {
                    a.setSuffix("§e"+String.valueOf(green_age));
                }
                w.setSuffix(String.valueOf(green_wood));
                s.setSuffix(String.valueOf(green_stone));
                g.setSuffix(String.valueOf(green_gold));
                sm.setSuffix(String.valueOf(green_small_plots));
                m.setSuffix(String.valueOf(green_medium_plots));
                l.setSuffix(String.valueOf(green_large_plots));
            } else if (yellowPlayers.contains(p)){
                if (yellow_change_age){
                    a.setPrefix(String.valueOf(df.format(new Date(yellow_age_long))));
                    yellow_age_long -=1000;
                    if (green_age_long == 0){
                        yellow_age++;
                        yellow_change_age = false;
                        Bukkit.broadcastMessage(getMsg("yellow-changed-age").replace("{age}", String.valueOf(yellow_age)));
                        for (Player on : Bukkit.getOnlinePlayers()){
                            on.getWorld().playSound(on.getLocation(), Sound.WITHER_DEATH, 1, 1);
                        }
                    }
                } else {
                    a.setSuffix("§e"+String.valueOf(yellow_age));
                }
                w.setSuffix(String.valueOf(yellow_wood));
                s.setSuffix(String.valueOf(yellow_stone));
                g.setSuffix(String.valueOf(yellow_gold));
                sm.setSuffix(String.valueOf(yellow_small_plots));
                m.setSuffix(String.valueOf(yellow_medium_plots));
                l.setSuffix(String.valueOf(yellow_large_plots));
            } else if (redPlayers.contains(p)){
                if (red_change_age){
                    a.setPrefix(String.valueOf(df.format(new Date(red_age_long))));
                    red_age_long -=1000;
                    if (red_age_long == 0){
                        red_age++;
                        red_change_age = false;
                        Bukkit.broadcastMessage(getMsg("red-changed-age").replace("{age}", String.valueOf(red_age)));
                        for (Player on : Bukkit.getOnlinePlayers()){
                            on.getWorld().playSound(on.getLocation(), Sound.WITHER_DEATH, 1, 1);
                        }
                    }
                } else {
                    a.setSuffix("§e"+String.valueOf(red_age));
                }
                w.setSuffix(String.valueOf(red_wood));
                s.setSuffix(String.valueOf(red_stone));
                g.setSuffix(String.valueOf(red_gold));
                sm.setSuffix(String.valueOf(red_small_plots));
                m.setSuffix(String.valueOf(red_medium_plots));
                l.setSuffix(String.valueOf(red_large_plots));
            }
            pvp.setSuffix(String.valueOf(df.format(new Date(Main.pvp_assault))));
        }
    }

}
