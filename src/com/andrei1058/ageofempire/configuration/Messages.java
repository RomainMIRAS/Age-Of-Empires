package com.andrei1058.ageofempire.configuration;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.andrei1058.ageofempire.Main.PREFIX;
import static com.andrei1058.ageofempire.game.Buildings.*;

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
        yml.addDefault("help.ison", "{prefix} &9[&eHelp&9] &aHelp is on, use the /h command to &2Turn On&e/&cTurn Off &athe help.");
        yml.addDefault("help.cutting-wood", "{prefix} &9[&eHelp&9] &aCutting &2Wood &ayields resources for you and your base.");
        yml.addDefault("help.gold-stuff", "{prefix} &9[&eHelp&9] &2Gold &ais used to buy equipment in the buildings. You earn gold by collecting resources or by killing players.");
        yml.addDefault("help.stone", "{prefix} &9[&eHelp&9] &aMining &2Stone (Andesite) &ayields resources for you and your base.");
        yml.addDefault("help.start-guide", "{prefix} &9[&eHelp&9] &aWelcome to Age of Empire. This game consists of collecting ressources for you and your kingdom. These ressources will allow you to construct buildings");
        yml.addDefault("help.start-buildings", "{prefix} &9[&eHelp&9] &aThese buildings are used to develop your kingdom and allow you to buy various different items (weapons, food etc.).");
        yml.addDefault("help.start-resources", "{prefix} &9[&eHelp&9] &aTo start, go mine some Stone (Andesite) and Wood.");
        yml.addDefault("help.enough-res-forum", "{prefix} &9[&eHelp&9] &aYour Kingdom has enough resources for you to buy a building. Go back to your &2Forum&a, click on the NPC and select a building. Why not start with the &2Forge&a?");
        yml.addDefault("help.vote", "{prefix} &9[&eHelp&9] &aSomeone in your team has opened a vote. This vote lets you buy a new building or change Age! To validate your vote, you have to &2Right Click &awith the &2withe the &2Slimeball &afound in the &2last slot in your inventory&a. If you don't agree with the proposed motion, you don't have to do anything.");
        yml.addDefault("scoreboard.title", "&cAgeOfEmpire");
        yml.addDefault("scoreboard.14", "&6Age: &f");
        yml.addDefault("scoreboard.13", "§1");
        yml.addDefault("scoreboard.12", "&bWood: &f");
        yml.addDefault("scoreboard.11", "&bStone: &f");
        yml.addDefault("scoreboard.10", "&eGold: &f");
        yml.addDefault("scoreboard.9", "§2");
        yml.addDefault("scoreboard.8", "&aPlots");
        yml.addDefault("scoreboard.7", "&2Small: &f");
        yml.addDefault("scoreboard.6", "&2Medium: &f");
        yml.addDefault("scoreboard.5", "&2Large: &f");
        yml.addDefault("scoreboard.4", "§3");
        yml.addDefault("scoreboard.3", "&7PvP: &f");
        yml.addDefault("scoreboard.3_2", "&7Assault: &f");
        yml.addDefault("scoreboard.2", "§4");
        yml.addDefault("scoreboard.1", "&6mc.parkergames.it");
        yml.addDefault("villagers.forum", "&c&lForum");
        yml.addDefault("villagers.buy-buildings", "&b&lBuy buildings for your kingdom");
        yml.addDefault("pvp-on", "{prefix} &6Pvp On, rare ores have appeared in the middle of the map.");
        yml.addDefault("assaults-on", "{prefix} &6Assaults on.");
        yml.addDefault("chat.game", "(Team) {player} &f: {message}");
        yml.addDefault("cant-break", "{prefix} &cYou can't break this block in a kingdom");
        yml.addDefault("cant-vote", "{prefix} &cYou can't vote at the moment (a vote is already in progress)");
        yml.addDefault("forum.age1", "&cAge 1");
        yml.addDefault("forum.age2", "&cAge 2");
        yml.addDefault("forum.age3", "&cAge 3");
        yml.addDefault("forum.age4", "&cAge 4");
        yml.addDefault("forum.age-buldings", "&6Buildings for this age ➤");
        yml.addDefault("forum.to-build", "&a&lTo build");
        yml.addDefault("forum.built", "&6&lAlready built");
        yml.addDefault("villager.forum-attacked", "&e&lForum Attacked");

        yml.addDefault("forum."+forge+".displayname", "&e&lForge");
        yml.addDefault("forum.violence", "{prefix} &eOuch! You need to right click to open my menu... &cThere's no need for violence! :C");
        ArrayList forge_lore = new ArrayList();
        forge_lore.add("&3Wood: &f150");
        forge_lore.add("&3Stone: &f75");
        forge_lore.add("&3Plot: &fSmall");
        forge_lore.add("&3Description: &bBuy hand-to-hand weapons.");
        yml.addDefault("forum."+forge+".lore", forge_lore);

        yml.addDefault("forum."+mill+".displayname", "&e&lMill");
        ArrayList mill_lore = new ArrayList();
        mill_lore.add("&3Wood: &f150");
        mill_lore.add("&3Stone: &f75");
        mill_lore.add("&3Plot: &fSmall");
        mill_lore.add("&3Description: &bBuy food.");
        yml.addDefault("forum."+mill+".lore", mill_lore);

        yml.addDefault("forum."+stone_mine+".displayname", "&e&lStone Mine");
        ArrayList stone_lore = new ArrayList();
        stone_lore.add("&3Wood: &f150");
        stone_lore.add("&3Stone: &f75");
        stone_lore.add("&3Plot: &fSmall");
        stone_lore.add("&3Description: &bYields Stone automatically.");
        yml.addDefault("forum."+stone_mine+".lore", stone_lore);

        yml.addDefault("forum."+gold_mine+".displayname", "&e&lGold Mine");
        ArrayList gold_lore = new ArrayList();
        gold_lore.add("&3Wood: &f150");
        gold_lore.add("&3Stone: &f100");
        gold_lore.add("&3Plot: &fSmall");
        gold_lore.add("&3Description: &bYields Gold automatically.");
        yml.addDefault("forum."+gold_mine+".lore", gold_lore);

        yml.addDefault("forum."+sawmill+".displayname", "&e&lSawmill");
        ArrayList sawmill_lore = new ArrayList();
        sawmill_lore.add("&3Wood: &f150");
        sawmill_lore.add("&3Stone: &f75");
        sawmill_lore.add("&3Plot: &fSmall");
        sawmill_lore.add("&3Description: &bYields Wood automatically.");
        yml.addDefault("forum."+sawmill+".lore", sawmill_lore);

        yml.addDefault("forum."+market+".displayname", "&e&lMarket");
        ArrayList market_lore = new ArrayList();
        market_lore.add("&3Wood: &f100");
        market_lore.add("&3Stone: &f50");
        market_lore.add("&3Plot: &fSmall");
        market_lore.add("&3Description: &bBuy various different items.");
        yml.addDefault("forum."+market+".lore", market_lore);

        yml.addDefault("forum."+kennel+".displayname", "&e&lKennel");
        ArrayList kennel_lore = new ArrayList();
        kennel_lore.add("&3Wood: &f100");
        kennel_lore.add("&3Stone: &f50");
        kennel_lore.add("&3Plot: &fSmall");
        kennel_lore.add("&3Description: &bBuy dogs.");
        yml.addDefault("forum."+kennel+".lore", kennel_lore);

        yml.addDefault("forum."+sabotage+".displayname", "&e&lSabotage Workshop");
        ArrayList sabotagew_lore = new ArrayList();
        sabotagew_lore.add("&3Wood: &f100");
        sabotagew_lore.add("&3Stone: &f50");
        sabotagew_lore.add("&3Plot: &fSmall");
        sabotagew_lore.add("&3Description: &bBuy destructive items.");
        yml.addDefault("forum."+sabotage+".lore", sabotagew_lore);

        yml.addDefault("forum."+workshop+".displayname", "&e&lWorkshop");
        ArrayList workshop_lore = new ArrayList();
        workshop_lore.add("&3Wood: &f100");
        workshop_lore.add("&3Stone: &f50");
        workshop_lore.add("&3Plot: &fSmall");
        workshop_lore.add("&3Description: &bBuy various blocks.");
        yml.addDefault("forum."+workshop+".lore", workshop_lore);

        yml.addDefault("new-vote", "&9{player} &1would like to create a/an {building} &f. &2{votes}&f/&4{team}");
        yml.addDefault("vote-denied", "&c{player}&4's vote has been denied.");
        yml.addDefault("vote-accepted", "&2{player}&a's vote has been accepted.");
        yml.addDefault("insufficient-resources", "{prefix} &cYour team doesn't have enough &eressources &c{&6Wood missing&e: &f{wood}&6: Stones missing&e: &f{stone}&6).");
        yml.addDefault("locked-slot", "&cSlot Locked");
        yml.addDefault("forum-paper", "Forum");
        yml.addDefault("validate-vote", "&aValidate your Vote");
        yml.addDefault("constructor.displayname", "&8Constructor");
        ArrayList<String> constructor_lore = new ArrayList<>();
        constructor_lore.add("&fRight click");
        constructor_lore.add("&fon one of your team's");
        constructor_lore.add("&fplots");
        constructor_lore.add("&fwith this item in your hand");
        constructor_lore.add("&fto build the");
        constructor_lore.add("&fselected building");
        constructor_lore.add("&cThrow to cancel");
        yml.addDefault("constructor.lore", constructor_lore);
        yml.addDefault("having-construct", "{prefix} &9You must construct a building before you can vote again");
        yml.addDefault("build-canceled", "{prefix} &6{player} has just canceled a building {building}");
        yml.addDefault("build-started", "{prefix} &2{player} has just started building {building}");
        yml.addDefault("already-built", "&c&lYou already built this!");
        yml.addDefault("vote-age", "&9{player} &1would like to change Age &f. &2{votes}&f/&4{team}");
        yml.addDefault("cant-construct-outside", "&cYou can't construct buildings outside your base.");
        yml.addDefault("cant-construct-here", "&cYou can't construct buildings right here.");
        yml.addDefault("cant-construct-size", "&cYou must be in the right size plot to build this structure.");
        yml.addDefault("cant-vote-full", "&cYou can't vote for this building because you don't have any free plot of this type");
        yml.addDefault("built-success", "{prefix} &e&l{building} &abuilt successfully.");
        yml.addDefault("motd.lobby", "&aLobby");
        yml.addDefault("motd.starting", "&eStarting");
        yml.addDefault("motd.playing", "&cPlaying");
        yml.addDefault("motd.restarting", "&4Restarting");
        yml.addDefault("new-kill", "{prefix} {player} &awas killed by {killer}");
        yml.addDefault("victory.green", "&aGreen's Victory");
        yml.addDefault("victory.blue", "&9Blue's Victory");
        yml.addDefault("vicotry.red", "&cRed's Vicotory");
        yml.addDefault("victory.yellow", "&eYellow's Victory");
        yml.addDefault("base-destroyed.blue", "{prefix} &eThe &9Blue team's Base &2has been destroyed by the {team}");
        yml.addDefault("base-destroyed.green", "{prefix} &eThe &aGreen team's Base &2has been destroyed by the {team}");
        yml.addDefault("base-destroyed.yellow", "{prefix} &eThe Yellow team's Base &2has been destroyed by the {team}");
        yml.addDefault("base-destroyed.red", "{prefix} &eThe &cRed team's Base &2has been destroyed by the {team}");
        yml.addDefault("holo.gold", "&eGold &a+{amount}");
        yml.addDefault("holo.stone", "&eStone &a+{amount}");
        yml.addDefault("holo.wood", "&eWood &a+{amount}");
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

    public static ArrayList<String> getArray(String string){
        return (ArrayList<String>) yml.getStringList(string);
    }
}
