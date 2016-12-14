package com.andrei1058.ageofempire.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Buildings {
    public static ArrayList<String> vote_in_progress = new ArrayList<>();
    public static ArrayList<String> built_forge = new ArrayList<>();
    public static HashMap<UUID, String> construct_in_inv = new HashMap<>();
    public static final String forge = "FORGE", mill = "MILL", stone_mine = "STONE_MINE", gold_mine = "GOLDMINE", sawmill = "SAWMILL",
    workshop = "workshop", market = "MARKET", kennel = "KENNEL", sabotage = "SABOTAGE", age_string = "age";

    public static boolean hasBuild(ArrayList<String> build, String team){
        if (build.contains(team)){
            return true;
        }
        return false;
    }
}
