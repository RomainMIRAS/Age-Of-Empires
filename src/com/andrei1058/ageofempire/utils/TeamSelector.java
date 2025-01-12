package com.andrei1058.ageofempire.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;

import static com.andrei1058.ageofempire.Main.*;
import static com.andrei1058.ageofempire.Main.redPlayers;
import static com.andrei1058.ageofempire.configuration.Messages.getMsg;

public class TeamSelector {
    public enum Team {
        RED,
        BLUE,
        GREEN,
        YELLOW,
    }

    public static Team getTeam(String team){
        switch (team){
            case "RED":
                return Team.RED;
            case "BLUE":
                return Team.BLUE;
            case "GREEN":
                return Team.GREEN;
            case "YELLOW":
                return Team.YELLOW;
            default:
                return null;
        }
    }

    private static String getMsgJoinTeam(Team team){
        switch (team){
            case RED:
                return getMsg("team-choosing.red-join");
            case BLUE:
                return getMsg("team-choosing.blue-join");
            case GREEN:
                return getMsg("team-choosing.green-join");
            case YELLOW:
                return getMsg("team-choosing.yellow-join");
            default:
                return null;
        }
    }

    private static void changePlayerColor(Player player, Team team){
        switch (team){
            case RED:
                player.setPlayerListName("§c" + player.getName());
                break;
            case BLUE:
                player.setPlayerListName("§9" + player.getName());
                break;
            case GREEN:
                player.setPlayerListName("§a" + player.getName());
                break;
            case YELLOW:
                player.setPlayerListName("§e" + player.getName());
                break;
        }
    }

    private static List<Player> getTeamPlayers(Team team){
        switch (team){
            case RED:
                return redPlayers;
            case BLUE:
                return bluePlayers;
            case GREEN:
                return greenPlayers;
            case YELLOW:
                return yellowPlayers;
            default:
                return null;
        }
    }

    private static void changeTeam(Player player, Team team){
        List<Player> redPlayers = getTeamPlayers(Team.RED);
        List<Player> bluePlayers = getTeamPlayers(Team.BLUE);
        List<Player> greenPlayers = getTeamPlayers(Team.GREEN);
        List<Player> yellowPlayers = getTeamPlayers(Team.YELLOW);

        if (redPlayers.contains(player)){
            redPlayers.remove(player);
        } else if (bluePlayers.contains(player)){
            bluePlayers.remove(player);
        } else if (greenPlayers.contains(player)){
            greenPlayers.remove(player);
        } else if (yellowPlayers.contains(player)){
            yellowPlayers.remove(player);
        }

        List<Player> futureTeam = getTeamPlayers(team);

        if (futureTeam.size() < max_in_team){
            futureTeam.add(player);
            player.sendMessage(getMsgJoinTeam(team));
            changePlayerColor(player, team);
        } else {
            player.sendMessage(getMsg("team-choosing.unbalanced-teams"));
        }
    }

    public static Team getSmallestTeam() {
        Team smallestTeam = Team.RED;
        int smallestSize = getTeamPlayers(Team.RED).size();

        for (Team team : Team.values()) {
            int teamSize = getTeamPlayers(team).size();
            if (teamSize < smallestSize) {
                smallestTeam = team;
                smallestSize = teamSize;
            }
        }

        return smallestTeam;
    }

    public static void joinTeam(Player player, Team team){
        if (getTeamPlayers(team).contains(player)){
            return;
        }
        changeTeam(player, team);
    }


}
