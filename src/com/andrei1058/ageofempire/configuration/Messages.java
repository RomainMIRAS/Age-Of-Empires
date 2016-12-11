package com.andrei1058.ageofempire.configuration;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

import static com.andrei1058.ageofempire.Main.PREFIX;

public class Messages {
    private static File file = new File("plugins/Age-Of-Empire/messages.yml");
    private static YamlConfiguration yml = YamlConfiguration.loadConfiguration(file);

    public static void setupMessages(){
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        yml.addDefault("prefix", "&9[&7AOE&9]");
        yml.addDefault("player-join", "{prefix} &7%player% has joined the game!");
        yml.addDefault("help-item-on", "&aHelp On");
        yml.addDefault("help-item-off", "&cHelp Off");
        yml.addDefault("leave-item", "&aBack to Hub");
        yml.addDefault("team-choosing.blue", "&9Blue");
        yml.addDefault("team-choosing.green", "&aGreen");
        yml.addDefault("team-choosing.yellow", "&eYellow");
        yml.addDefault("team-choosing.red", "&cRed");
        yml.addDefault("team-choosing.green-join", "{prefix} &aYou're joining the team Green");
        yml.addDefault("team-choosing.red-join", "{prefix} &cYou're joining the team Red");
        yml.addDefault("team-choosing.yellow-join", "{prefix} &eYou're joining the team Yellow");
        yml.addDefault("team-choosing.blue-join", "{prefix} &9You're joining the team Blue");
        yml.addDefault("team-choosing.unbalanced-teams", "&eYou can't join this team right now!");
        yml.addDefault("game-start", "&6The game will start in &e{time} &6second(s).");
        yml.addDefault("help.start-resources", "{prefix} &9[&eHelp&9] &aYour Kingdom has enough resources for you to buy a building. Go back to your &2Forum&a, click on the NPC and select a building. Why not start with the &2Forge&a?");
        yml.addDefault("help.cutting-wood", "{prefix} &9[&eHelp&9] &aCutting &2Wood &ayields resources for you and your base.");
        yml.addDefault("help.gold-stuff", "{prefix} &9[&eHelp&9] &2Gold &ais used to buy equipment in the buildings. You earn gold by collecting resources or by killing players.");
        yml.addDefault("help.stone", "{prefix} &9[&eHelp&9] &aMining &2Stone (Andesite) &ayields resources for you and your base.");
        yml.addDefault("scoreboard.title", "&cAgeOfEmpire");
        yml.addDefault("scoreboard.14", "&6Age: ");
        yml.addDefault("scoreboard.13", "");
        yml.addDefault("scoreboard.12", "&bWood: ");
        yml.addDefault("scoreboard.11", "&bStone: ");
        yml.addDefault("scoreboard.10", "&eGold: ");
        yml.addDefault("scoreboard.9", "");
        yml.addDefault("scoreboard.8", "&aPlots");
        yml.addDefault("scoreboard.7", "&2Small: ");
        yml.addDefault("scoreboard.6", "&2Medium: ");
        yml.addDefault("scoreboard.5", "&2Large: ");
        yml.addDefault("scoreboard.4", "");
        yml.addDefault("scoreboard.3", "&7PvP: ");
        yml.addDefault("scoreboard.3_2", "&7Assault: ");
        yml.addDefault("scoreboard.2", "");
        yml.addDefault("scoreboard.1", "&9ultramc.eu");
        yml.addDefault("villagers.forum", "&c&lForum");
        yml.addDefault("pvp-on", "{prefix} &6Pvp On, rare ores have appeared in the middle of the map.");
        yml.addDefault("assaults-on", "{prefix} &6Assaults on.");
        yml.options().copyDefaults(true);
        try {
            yml.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getMsg(String string){
        return yml.getString(string).replace("{prefix}", PREFIX).replace('&','§');
    }
}
