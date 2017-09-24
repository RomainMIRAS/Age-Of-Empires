package com.andrei1058.ageofempire.game;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

import static com.andrei1058.ageofempire.Main.*;

public class Buildings {
    public static ArrayList<String> vote_in_progress = new ArrayList<>();
    public static HashMap<Player, String> construct_in_inv = new HashMap<>();
    public static ArrayList blue_built = new ArrayList();
    public static ArrayList green_built = new ArrayList();
    public static ArrayList yellow_built = new ArrayList();
    public static ArrayList red_built = new ArrayList();

    public static final String forge = "FORGE", mill = "MILL", stone_mine = "STONE_MINE", gold_mine = "GOLD_MINE", sawmill = "SAWMILL",
    workshop = "WORKSHOP", market = "MARKET", kennel = "KENNEL", sabotage = "SABOTAGE_WORKSHOP", age_string = "age",
            archery = "ARCHERY_STORE", trifarrow = "TRIFARROW", stable = "STABLE", armory = "ARMORY", laboratory = "LABORATORY",
    guild = "GUILD", training_center = "TRAINING_CENTER";

    public static boolean hasBuild(String build, String team){
        switch (team){
            case blue_team:
                if (blue_built.contains(build)){
                    return true;
                }
                break;
            case green_team:
                if (green_built.contains(build)){
                    return true;
                }
                break;
            case yellow_team:
                if (yellow_built.contains(build)){
                    return true;
                }
                break;
            case red_team:
                if (red_built.contains(build)){
                    return true;
                }
                break;
        }
        return false;
    }

    public static void addBuild(String build, String team){
        switch (team){
            case blue_team:
                blue_built.add(build);
                break;
            case green_team:
                green_built.add(build);
                break;
            case yellow_team:
                yellow_built.add(build);
                break;
            case red_team:
                red_built.add(build);
                break;
        }
    }
}
