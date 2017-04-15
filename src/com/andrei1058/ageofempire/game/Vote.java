package com.andrei1058.ageofempire.game;

import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import static com.andrei1058.ageofempire.Main.*;
import static com.andrei1058.ageofempire.Misc.constructor;
import static com.andrei1058.ageofempire.Misc.slotlocked;
import static com.andrei1058.ageofempire.Misc.voteitem;
import static com.andrei1058.ageofempire.configuration.Messages.getMsg;
import static com.andrei1058.ageofempire.game.Buildings.age_string;
import static com.andrei1058.ageofempire.game.Buildings.construct_in_inv;
import static com.andrei1058.ageofempire.game.Buildings.vote_in_progress;
import static com.andrei1058.ageofempire.game.Scoreboard.*;
import static com.andrei1058.ageofempire.runnables.Game.*;

public class Vote {

    public static ArrayList<Vote> votes = new ArrayList<>();
    public static HashMap<String, Vote> votes_by_team = new HashMap<>();
    public ArrayList<UUID> team;
    public int time = 9;
    public String build;
    public String teamname;
    public UUID requester;
    public int player_votes;
    public int wood;
    public int stone;
    public String BUILD_NAME;

    public Vote(ArrayList<UUID> team, String build, UUID requester, String teamname, int wood, int stone, String BUILD_NAME){
        this.team = team;
        this.build = build;
        this.requester = requester;
        this.wood = wood;
        this.stone = stone;
        this.BUILD_NAME = BUILD_NAME;
        votes.add(this);
        this.teamname = teamname;
        votes_by_team.put(teamname, this);
        vote_in_progress.add(teamname);
        for (UUID u : team){
            if (help.contains(u)){
                Bukkit.getPlayer(u).sendMessage(getMsg("help.vote"));
            }
            Bukkit.getPlayer(u).getInventory().setItem(8, voteitem());
        }
    }

    public void stuff(){
        time--;
        if (time == 0 || time < 0){
            if (player_votes >(team.size()/2)){
                for (UUID u : team){
                    nms.actionMsg(Bukkit.getPlayer(u), getMsg("vote-accepted").replace("{player}", Bukkit.getPlayer(requester).getName()));
                    Bukkit.getPlayer(u).getInventory().setItem(8, slotlocked());
                }
                if (teamname == blue_team){
                    blue_wood-= wood;
                    blue_stone-=stone;
                } else if (teamname == green_team){
                    green_wood-=wood;
                    green_stone-=stone;
                } else if (teamname == yellow_team){
                    yellow_wood-=wood;
                    yellow_stone-=stone;
                } else if (teamname == red_team){
                    red_wood-=wood;
                    red_stone-=stone;
                }
                switch (build){
                    case age_string:
                        switch (teamname){
                            case blue_team:
                                blue_change_age = true;
                                blue_age_long = 60000;
                                break;
                            case green_team:
                                green_change_age = true;
                                green_age_long = 60000;
                                break;
                            case yellow_team:
                                yellow_change_age = true;
                                yellow_age_long = 60000;
                                break;
                            case red_team:
                                red_change_age = true;
                                red_age_long = 60000;
                                break;
                        }
                        break;
                    default:
                        construct_in_inv.put(requester, BUILD_NAME);
                        Bukkit.getPlayer(requester).getInventory().setItem(7, constructor());
                        new BuildSchematic(requester, teamname, build, BUILD_NAME, team);
                        break;
                }
            } else {
                for (UUID u : team){
                    nms.actionMsg(Bukkit.getPlayer(u), getMsg("vote-denied").replace("{player}", Bukkit.getPlayer(requester).getName()));
                    Bukkit.getPlayer(u).getInventory().setItem(8, slotlocked());
                }
            }
            votes_by_team.remove(teamname, this);
            votes.remove(this);
            vote_in_progress.remove(teamname);
            return;
        }
        for (UUID u : team){
            switch (build){
                case age_string:
                    nms.actionMsg(Bukkit.getPlayer(u), getMsg("vote-age").replace("{player}", Bukkit.getPlayer(requester).getName()).replace("{votes}", String.valueOf(player_votes)).replace("{team}", String.valueOf(team.size())));
                    break;
                default:
                    nms.actionMsg(Bukkit.getPlayer(u), getMsg("new-vote").replace("{player}", Bukkit.getPlayer(requester).getName()).replace("{building}", build).replace("{votes}", String.valueOf(player_votes)).replace("{team}", String.valueOf(team.size())));
                    break;
            }
        }
    }

    public static ArrayList<Vote> votes(){
        return votes;
    }

    public static Vote byTeam(String team){
        return votes_by_team.get(team);
    }

    public void addVote(){
        player_votes++;
    }
}
