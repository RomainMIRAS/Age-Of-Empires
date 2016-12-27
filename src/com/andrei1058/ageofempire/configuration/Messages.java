package com.andrei1058.ageofempire.configuration;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.andrei1058.ageofempire.Main.PREFIX;
import static com.andrei1058.ageofempire.game.Buildings.*;
import static com.andrei1058.ageofempire.game.Buildings.archery;

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
        yml.addDefault("action-player-join", "&e{player} &7has joined the game!");
        yml.addDefault("action-player-left", "&e{player} &7has left the game!");
        yml.addDefault("player-died", "{player} &adied!");
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
        yml.addDefault("help.start-guide", "{prefix} &9[&eHelp&9] &aWelcome to Age of Empire. This game consists of collecting resources for you and your kingdom. These resources will allow you to construct buildings");
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
        yml.addDefault("chat.lobby", "(All) {player} &f: {message}");
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
        yml.addDefault("villager.cant-open", "{prefix} &6You can't open this menu.");

        yml.addDefault("forum."+forge+".displayname", "&e&lForge");
        yml.addDefault("forum.violence", "{prefix} &eOuch! You need to right click to open my menu... &cThere's no need for violence! :C");
        ArrayList forge_lore = new ArrayList();
        forge_lore.add("&3Wood: &f150");
        forge_lore.add("&3Stone: &f75");
        forge_lore.add("&3Plot: &fSmall");
        forge_lore.add("&3Description: &bBuy hand-to-hand weapons.");
        yml.addDefault("forum."+forge+".lore", forge_lore);
        yml.addDefault("forum."+forge+".holo", "&bBuy hand-to-hand weapons");

        yml.addDefault("forum."+mill+".displayname", "&e&lMill");
        ArrayList mill_lore = new ArrayList();
        mill_lore.add("&3Wood: &f150");
        mill_lore.add("&3Stone: &f75");
        mill_lore.add("&3Plot: &fSmall");
        mill_lore.add("&3Description: &bBuy food.");
        yml.addDefault("forum."+mill+".lore", mill_lore);
        yml.addDefault("forum."+mill+".holo", "&bBuy food");

        yml.addDefault("forum."+stone_mine+".displayname", "&e&lStone Mine");
        ArrayList stone_lore = new ArrayList();
        stone_lore.add("&3Wood: &f150");
        stone_lore.add("&3Stone: &f75");
        stone_lore.add("&3Plot: &fSmall");
        stone_lore.add("&3Description: &bYields Stone automatically.");
        yml.addDefault("forum."+stone_mine+".lore", stone_lore);
        yml.addDefault("forum."+stone_mine+".holo", "&bYields Stone automatically");

        yml.addDefault("forum."+gold_mine+".displayname", "&e&lGold Mine");
        ArrayList gold_lore = new ArrayList();
        gold_lore.add("&3Wood: &f150");
        gold_lore.add("&3Stone: &f100");
        gold_lore.add("&3Plot: &fSmall");
        gold_lore.add("&3Description: &bYields Gold automatically.");
        yml.addDefault("forum."+gold_mine+".lore", gold_lore);
        yml.addDefault("forum."+gold_mine+".holo", "&bYields Gold automatically");

        yml.addDefault("forum."+sawmill+".displayname", "&e&lSawmill");
        ArrayList sawmill_lore = new ArrayList();
        sawmill_lore.add("&3Wood: &f150");
        sawmill_lore.add("&3Stone: &f75");
        sawmill_lore.add("&3Plot: &fSmall");
        sawmill_lore.add("&3Description: &bYields Wood automatically.");
        yml.addDefault("forum."+sawmill+".lore", sawmill_lore);
        yml.addDefault("forum."+sawmill+".holo", "&bYields Wood automatically");

        yml.addDefault("forum."+market+".displayname", "&e&lMarket");
        ArrayList market_lore = new ArrayList();
        market_lore.add("&3Wood: &f100");
        market_lore.add("&3Stone: &f50");
        market_lore.add("&3Plot: &fSmall");
        market_lore.add("&3Description: &bBuy various different items.");
        yml.addDefault("forum."+market+".lore", market_lore);
        yml.addDefault("forum."+market+".holo", "&bBuy various different items");

        yml.addDefault("forum."+kennel+".displayname", "&e&lKennel");
        ArrayList kennel_lore = new ArrayList();
        kennel_lore.add("&3Wood: &f100");
        kennel_lore.add("&3Stone: &f50");
        kennel_lore.add("&3Plot: &fSmall");
        kennel_lore.add("&3Description: &bBuy dogs.");
        yml.addDefault("forum."+kennel+".lore", kennel_lore);
        yml.addDefault("forum."+kennel+".holo", "&bBuy dogs");

        yml.addDefault("forum."+sabotage+".displayname", "&e&lSabotage Workshop");
        ArrayList sabotagew_lore = new ArrayList();
        sabotagew_lore.add("&3Wood: &f100");
        sabotagew_lore.add("&3Stone: &f50");
        sabotagew_lore.add("&3Plot: &fSmall");
        sabotagew_lore.add("&3Description: &bBuy destructive items.");
        yml.addDefault("forum."+sabotage+".lore", sabotagew_lore);
        yml.addDefault("forum."+sabotage+".holo", "&bBuy destructive items");

        yml.addDefault("forum."+workshop+".displayname", "&e&lWorkshop");
        ArrayList workshop_lore = new ArrayList();
        workshop_lore.add("&3Wood: &f100");
        workshop_lore.add("&3Stone: &f50");
        workshop_lore.add("&3Plot: &fSmall");
        workshop_lore.add("&3Description: &bBuy various blocks.");
        yml.addDefault("forum."+workshop+".lore", workshop_lore);
        yml.addDefault("forum."+workshop+".holo", "&bBuy various blocks");

        yml.addDefault("forum."+archery+".displayname", "&e&lArchery Store");
        ArrayList archeryl = new ArrayList();
        archeryl.add("&3Wood: &f300");
        archeryl.add("&3Stone: &f150");
        archeryl.add("&3Plot: &fMedium");
        archeryl.add("&3Description: &bBuy ranged weapons.");
        yml.addDefault("forum."+archery+".lore", archeryl);
        yml.addDefault("forum."+archery+".holo", "&bBuy ranged weapons");

        yml.addDefault("forum."+trifarrow+".displayname", "&e&lTrifArrow");
        ArrayList trifarrow_lore = new ArrayList();
        trifarrow_lore.add("&3Wood: &f375");
        trifarrow_lore.add("&3Stone: &f175");
        trifarrow_lore.add("&3Plot: &fMedium");
        trifarrow_lore.add("&3Description: &bBuy various different arrows.");
        yml.addDefault("forum."+trifarrow+".lore", trifarrow_lore);
        yml.addDefault("forum."+trifarrow+".holo", "&bBuy various different arrows");

        yml.addDefault("forum."+stable+".displayname", "&e&lStable");
        ArrayList stable_lore = new ArrayList();
        stable_lore.add("&3Wood: &f200");
        stable_lore.add("&3Stone: &f100");
        stable_lore.add("&3Plot: &fMedium");
        stable_lore.add("&3Description: &bBuy horses.");
        yml.addDefault("forum."+stable+".lore", stable_lore);
        yml.addDefault("forum."+stable+".holo", "&bBuy horses");

        yml.addDefault("forum."+armory+".displayname", "&e&lArmory");
        ArrayList armory_lore = new ArrayList();
        armory_lore.add("&3Wood: &f300");
        armory_lore.add("&3Stone: &f150");
        armory_lore.add("&3Plot: &fMedium");
        armory_lore.add("&3Description: &bBuy armor.");
        yml.addDefault("forum."+armory+".lore", armory_lore);
        yml.addDefault("forum."+armory+".holo", "&bBuy armory_lore");

        yml.addDefault("forum."+laboratory+".displayname", "&e&lLaboratory");
        ArrayList laboratory_lore = new ArrayList();
        laboratory_lore.add("&3Wood: &f300");
        laboratory_lore.add("&3Stone: &f150");
        laboratory_lore.add("&3Plot: &fMedium");
        laboratory_lore.add("&3Description: &bBuy potions.");
        yml.addDefault("forum."+laboratory+".lore", laboratory_lore);
        yml.addDefault("forum."+laboratory+".holo", "&bBuy potions");

        yml.addDefault("forum."+guild+".displayname", "&e&lGuild");
        ArrayList guild_lore = new ArrayList();
        guild_lore.add("&3Wood: &f600");
        guild_lore.add("&3Stone: &f300");
        guild_lore.add("&3Plot: &fLarge");
        guild_lore.add("&3Description: &bBuy enchantments.");
        yml.addDefault("forum."+guild+".lore", guild_lore);
        yml.addDefault("forum."+guild+".holo", "&bBuy enchantments");

        yml.addDefault("forum."+training_center+".displayname", "&e&lTraining Center");
        ArrayList training_center_lore = new ArrayList();
        training_center_lore.add("&3Wood: &f500");
        training_center_lore.add("&3Stone: &f250");
        training_center_lore.add("&3Plot: &fLarge");
        training_center_lore.add("&3Description: &bYields XP automatically.");
        yml.addDefault("forum."+training_center+".lore", training_center_lore);
        yml.addDefault("forum."+training_center+".holo", "&bYields XP automatically");

        yml.addDefault("new-vote", "&9{player} &1would like to create a/an {building} &f. &2{votes}&f/&4{team}");
        yml.addDefault("vote-denied", "&c{player}&4's vote has been denied.");
        yml.addDefault("vote-accepted", "&2{player}&a's vote has been accepted.");
        yml.addDefault("insufficient-resources", "{prefix} &cYour team doesn't have enough &eresources &c{&6Wood missing&e: &f{wood}&6: Stones missing&e: &f{stone}&6).");
        yml.addDefault("insufficient-gold", "{prefix} &cInsufficient gold!");
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
        yml.addDefault("blue-changed-age", "{prefix} &fThe &9Blue &fteam has just changed to Age &e{age}");
        yml.addDefault("green-changed-age", "{prefix} &fThe &aGreen &fteam has just changed to Age &e{age}");
        yml.addDefault("yellow-changed-age", "{prefix} &fThe &eYellow &fteam has just changed to Age &e{age}");
        yml.addDefault("red-changed-age", "{prefix} &fThe &cRed &fteam has just changed to Age &e{age}");
        yml.addDefault("cant-construct-outside", "&cYou can't construct buildings outside your base.");
        yml.addDefault("cant-construct-here", "&cYou can't construct buildings right here.");
        yml.addDefault("cant-place-here", "{prefix} &cYou can't place blocks right here!");
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
        yml.addDefault("plot.small", "&2Small Plot");
        yml.addDefault("plot.medium", "&2Medium Plot");
        yml.addDefault("plot.large", "&2Large Plot");

        yml.addDefault("forge.stonepickaxe.displayname", "&7Stone Pickaxe");
        ArrayList<String> stonepickaxe = new ArrayList<>();
        stonepickaxe.add("&6Gold: &f1");
        stonepickaxe.add("&2Quantity: &f1");
        yml.addDefault("forge.stonepickaxe.lore", stonepickaxe);

        yml.addDefault("forge.stonesword.displayname", "&7Stone Sword");
        ArrayList<String> stonesword = new ArrayList<>();
        stonesword.add("&6Gold: &f10");
        stonesword.add("&2Quantity: &f1");
        yml.addDefault("forge.stonesword.lore", stonesword);

        yml.addDefault("forge.ironsword.displayname", "&fIron Sword");
        ArrayList<String> ironsword = new ArrayList<>();
        ironsword.add("&6Gold: &f30");
        ironsword.add("&2Quantity: &f1");
        yml.addDefault("forge.ironsword.lore", ironsword);

        yml.addDefault("forge.stoneaxe.displayname", "&7Stone Axe");
        ArrayList<String> stoneaxe = new ArrayList<>();
        stoneaxe.add("&6Gold: &f5");
        stoneaxe.add("&2Quantity: &f1");
        yml.addDefault("forge.stoneaxe.lore", stoneaxe);

        yml.addDefault("forge.ironaxe.displayname", "&fIron Axe");
        ArrayList<String> ironaxe = new ArrayList<>();
        ironaxe.add("&6Gold: &f15");
        ironaxe.add("&2Quantity: &f1");
        yml.addDefault("forge.ironaxe.lore", ironaxe);

        yml.addDefault("forge.diamondsword.displayname", "&9Diamond Sword");
        ArrayList<String> diamondsowrd_l = new ArrayList<>();
        diamondsowrd_l.add("&6Gold: &f50");
        diamondsowrd_l.add("&2Quantity: &f1");
        yml.addDefault("forge.diamondsword.lore", diamondsowrd_l);

        yml.addDefault("forge.diamondaxe.displayname", "&9Diamond Axe");
        ArrayList<String> diamondaxe_l = new ArrayList<>();
        diamondaxe_l.add("&6Gold: &f25");
        diamondaxe_l.add("&2Quantity: &f1");
        yml.addDefault("forge.diamondaxe.lore", diamondaxe_l);

        yml.addDefault("mill.bread.displayname", "&7Bread");
        ArrayList<String> breadlore = new ArrayList<>();
        breadlore.add("&6Gold: &f10");
        breadlore.add("&2Quantity: &f5");
        yml.addDefault("mill.bread.lore", breadlore);

        yml.addDefault("mill.steak.displayname", "&7Steak");
        ArrayList<String> steak = new ArrayList<>();
        steak.add("&6Gold: &f22");
        steak.add("&2Quantity: &f5");
        yml.addDefault("mill.steak.lore", steak);

        yml.addDefault("mill.chicken.displayname", "&7Chicken");
        ArrayList<String> chicken = new ArrayList<>();
        chicken.add("&6Gold: &f20");
        chicken.add("&2Quantity: &f5");
        yml.addDefault("mill.chicken.lore", chicken);

        yml.addDefault("mill.potato.displayname", "&7Potato");
        ArrayList<String> potato = new ArrayList<>();
        potato.add("&6Gold: &f15");
        potato.add("&2Quantity: &f5");
        yml.addDefault("mill.potato.lore", potato);

        //workshop
        yml.addDefault("workshop.grass.displayname", "&7Grass");
        ArrayList<String> grass = new ArrayList<>();
        grass.add("&6Gold: &f10");
        grass.add("&2Quantity: &f10");
        yml.addDefault("workshop.grass.lore", grass);

        yml.addDefault("workshop.dirt.displayname", "&7Dirt");
        ArrayList<String> dirt = new ArrayList<>();
        dirt.add("&6Gold: &f10");
        dirt.add("&2Quantity: &f10");
        yml.addDefault("workshop.dirt.lore", dirt);

        yml.addDefault("workshop.plank.displayname", "&7Plank");
        ArrayList<String> plank = new ArrayList<>();
        plank.add("&6Gold: &f10");
        plank.add("&2Quantity: &f5");
        yml.addDefault("workshop.plank.lore", plank);

        yml.addDefault("workshop.sand.displayname", "&7Sand");
        ArrayList<String> sand = new ArrayList<>();
        sand.add("&6Gold: &f10");
        sand.add("&2Quantity: &f5");
        yml.addDefault("workshop.sand.lore", sand);

        yml.addDefault("workshop.gravel.displayname", "&7Gravel");
        ArrayList<String> gravel = new ArrayList<>();
        gravel.add("&6Gold: &f10");
        gravel.add("&2Quantity: &f5");
        yml.addDefault("workshop.gravel.lore", gravel);

        yml.addDefault("workshop.sponge.displayname", "&7Sponge");
        ArrayList<String> sponge = new ArrayList<>();
        sponge.add("&6Gold: &f20");
        sponge.add("&2Quantity: &f5");
        yml.addDefault("workshop.sponge.lore", sponge);

        yml.addDefault("workshop.glass.displayname", "&7Glass");
        ArrayList<String> glass = new ArrayList<>();
        glass.add("&6Gold: &f15");
        glass.add("&2Quantity: &f10");
        yml.addDefault("workshop.glass.lore", glass);

        yml.addDefault("workshop.lapis.displayname", "&7Lapis Block");
        ArrayList<String> lapis = new ArrayList<>();
        lapis.add("&6Gold: &f25");
        lapis.add("&2Quantity: &f5");
        yml.addDefault("workshop.lapis.lore", lapis);

        yml.addDefault("workshop.whitewool.displayname", "&7White Wool");
        ArrayList<String> whitewool = new ArrayList<>();
        whitewool.add("&6Gold: &f10");
        whitewool.add("&2Quantity: &f10");
        yml.addDefault("workshop.whitewool.lore", whitewool);

        yml.addDefault("workshop.orangewool.displayname", "&7Orange Wool");
        ArrayList<String> orangewool = new ArrayList<>();
        orangewool.add("&6Gold: &f10");
        orangewool.add("&2Quantity: &f10");
        yml.addDefault("workshop.orangewool.lore", orangewool);

        yml.addDefault("workshop.magentawool.displayname", "&7Magenta Wool");
        ArrayList<String> magentawool = new ArrayList<>();
        magentawool.add("&6Gold: &f10");
        magentawool.add("&2Quantity: &f10");
        yml.addDefault("workshop.magentawool.lore", magentawool);

        yml.addDefault("workshop.lightbluewool.displayname", "&7Light Blue Wool");
        ArrayList<String> lightbluewool = new ArrayList<>();
        lightbluewool.add("&6Gold: &f10");
        lightbluewool.add("&2Quantity: &f10");
        yml.addDefault("workshop.lightbluewool.lore", lightbluewool);

        yml.addDefault("workshop.yellowwool.displayname", "&7Yellow Wool");
        ArrayList<String> yellowwool = new ArrayList<>();
        yellowwool.add("&6Gold: &f10");
        yellowwool.add("&2Quantity: &f10");
        yml.addDefault("workshop.yellowwool.lore", yellowwool);

        yml.addDefault("workshop.lightgreenwool.displayname", "&7Light Green Wool");
        ArrayList<String> lightgreenwool = new ArrayList<>();
        lightgreenwool.add("&6Gold: &f10");
        lightgreenwool.add("&2Quantity: &f10");
        yml.addDefault("workshop.lightgreenwool.lore", yellowwool);

        yml.addDefault("workshop.pinkwool.displayname", "&7Pink Wool");
        ArrayList<String> pinkwool = new ArrayList<>();
        pinkwool.add("&6Gold: &f10");
        pinkwool.add("&2Quantity: &f10");
        yml.addDefault("workshop.pinkwool.lore", pinkwool);

        yml.addDefault("workshop.graywool.displayname", "&7Gray Wool");
        ArrayList<String> graywool = new ArrayList<>();
        graywool.add("&6Gold: &f10");
        graywool.add("&2Quantity: &f10");
        yml.addDefault("workshop.graywool.lore", graywool);

        yml.addDefault("workshop.cyanwool.displayname", "&7Cyan Wool");
        ArrayList<String> cyanwool = new ArrayList<>();
        cyanwool.add("&6Gold: &f10");
        cyanwool.add("&2Quantity: &f10");
        yml.addDefault("workshop.cyanwool.lore", cyanwool);

        yml.addDefault("workshop.purplewool.displayname", "&7Purple Wool");
        ArrayList<String> purplewool = new ArrayList<>();
        purplewool.add("&6Gold: &f10");
        purplewool.add("&2Quantity: &f10");
        yml.addDefault("workshop.purplewool.lore", purplewool);

        yml.addDefault("workshop.bluewool.displayname", "&7Blue Wool");
        ArrayList<String> bluewool = new ArrayList<>();
        bluewool.add("&6Gold: &f10");
        bluewool.add("&2Quantity: &f10");
        yml.addDefault("workshop.bluewool.lore", bluewool);

        yml.addDefault("workshop.blackwool.displayname", "&7Back Wool");
        ArrayList<String> blackwool = new ArrayList<>();
        blackwool.add("&6Gold: &f10");
        blackwool.add("&2Quantity: &f10");
        yml.addDefault("workshop.blackwool.lore", blackwool);

        yml.addDefault("workshop.greenwool.displayname", "&7Green Wool");
        ArrayList<String> greenwool = new ArrayList<>();
        greenwool.add("&6Gold: &f10");
        greenwool.add("&2Quantity: &f10");
        yml.addDefault("workshop.greenwool.lore", greenwool);

        yml.addDefault("workshop.redwool.displayname", "&7Red Wool");
        ArrayList<String> redwool = new ArrayList<>();
        redwool.add("&6Gold: &f10");
        redwool.add("&2Quantity: &f10");
        yml.addDefault("workshop.redwool.lore", redwool);

        yml.addDefault("workshop.brownwool.displayname", "&7Brown Wool");
        ArrayList<String> brownwool = new ArrayList<>();
        brownwool.add("&6Gold: &f10");
        brownwool.add("&2Quantity: &f10");
        yml.addDefault("workshop.brownwool.lore", brownwool);

        yml.addDefault("workshop.bricks.displayname", "&7Bricks");
        ArrayList<String> bricks = new ArrayList<>();
        bricks.add("&6Gold: &f15");
        bricks.add("&2Quantity: &f10");
        yml.addDefault("workshop.bricks.lore", bricks);

        yml.addDefault("workshop.mossstone.displayname", "&7Moss Stone");
        ArrayList<String> mossstone = new ArrayList<>();
        mossstone.add("&6Gold: &f15");
        mossstone.add("&2Quantity: &f10");
        yml.addDefault("workshop.mossstone.lore", mossstone);

        yml.addDefault("workshop.leaves.displayname", "&7Leaves");
        ArrayList<String> leaves = new ArrayList<>();
        leaves.add("&6Gold: &f15");
        leaves.add("&2Quantity: &f10");
        yml.addDefault("workshop.leaves.lore", leaves);

        //market
        yml.addDefault("market.flintandsteel.displayname", "&eFlint and Steel");
        ArrayList<String> flintandsteel = new ArrayList<>();
        flintandsteel.add("&6Gold: &f5");
        flintandsteel.add("&2Quantity: &f1");
        yml.addDefault("workshop.flintandsteel.lore", flintandsteel);

        yml.addDefault("market.cobweb.displayname", "&eCobweb");
        ArrayList<String> cobweb = new ArrayList<>();
        cobweb.add("&6Gold: &f5");
        cobweb.add("&2Quantity: &f5");
        yml.addDefault("workshop.cobweb.lore", cobweb);

        yml.addDefault("market.torches.displayname", "&eTorches");
        ArrayList<String> torches = new ArrayList<>();
        torches.add("&6Gold: &f5");
        torches.add("&2Quantity: &f12");
        yml.addDefault("workshop.torches.lore", torches);

        yml.addDefault("market.boat.displayname", "&eBoat");
        ArrayList<String> boat = new ArrayList<>();
        boat.add("&6Gold: &f2");
        boat.add("&2Quantity: &f1");
        yml.addDefault("workshop.boat.lore", boat);

        yml.addDefault("sabotage.tnt.displayname", "&cTNT");
        ArrayList<String> tnt = new ArrayList<>();
        tnt.add("&6Gold: &f5");
        tnt.add("&2Quantity: &f1");
        yml.addDefault("sabotage.tnt.lore", tnt);

        yml.addDefault("kennel.dog.displayname", "&cDog");
        ArrayList<String> dog = new ArrayList<>();
        dog.add("&6Gold: &f50");
        dog.add("&2Quantity: &f1");
        yml.addDefault("kennel.dog.lore", dog);

        yml.addDefault("kennel.dog2.displayname", "&cDogs");
        ArrayList<String> dog2 = new ArrayList<>();
        dog2.add("&6Gold: &f75");
        dog2.add("&2Quantity: &f2");
        yml.addDefault("kennel.dog2.lore", dog2);

        yml.addDefault("kennel.dog3.displayname", "&cDogs");
        ArrayList<String> dog3 = new ArrayList<>();
        dog3.add("&6Gold: &f100");
        dog3.add("&2Quantity: &f3");
        yml.addDefault("kennel.dog3.lore", dog3);

        yml.addDefault("armory.ironarmor.displayname", "&fIron Armor");
        ArrayList iron_armor = new ArrayList();
        iron_armor.add("&6Gold: &f40");
        iron_armor.add("&2Quantity: &f1");
        yml.addDefault("armory.ironarmor.lore", iron_armor);

        yml.addDefault("armory.ironhorsearmor.displayname", "&fIron Horse Armor");
        ArrayList ironhorsearmor = new ArrayList();
        ironhorsearmor.add("&6Gold: &f80");
        ironhorsearmor.add("&2Quantity: &f1");
        yml.addDefault("armory.ironhorsearmor.lore", ironhorsearmor);

        yml.addDefault("armory.diamondarmor.displayname", "&fDiamond Armor");
        ArrayList diamondarmor = new ArrayList();
        diamondarmor.add("&6Gold: &f80");
        diamondarmor.add("&2Quantity: &f1");
        yml.addDefault("armory.diamondarmor.lore", diamondarmor);

        yml.addDefault("armory.diamondhorsearmor.displayname", "&fDiamond Horse Armor");
        ArrayList diamondhorsearmor = new ArrayList();
        diamondhorsearmor.add("&6Gold: &f80");
        diamondhorsearmor.add("&2Quantity: &f1");
        yml.addDefault("armory.diamondhorsearmor.lore", diamondhorsearmor);

        yml.addDefault("pvp-disabled", "{prefix} &eP'rhaps wait for the PvP, get me? &a:p");
        yml.addDefault("x-attacked", "&e&l{villager} Attacked");
        yml.addDefault("yellow-building-explode", "{prefix} &eThe Yellow Team's {building} &6will explode in 15 seconds!");
        yml.addDefault("blue-building-explode", "{prefix} &eThe &9Blue Team's {building} &6will explode in 15 seconds!");
        yml.addDefault("green-building-explode", "{prefix} &eThe &aGreen Team's {building} &6will explode in 15 seconds!");
        yml.addDefault("red-building-explode", "{prefix} &eThe &cRed Team's {building} &6will explode in 15 seconds!");

        ArrayList age2_lore = new ArrayList();
        age2_lore.add("&eChange to Age 2");
        age2_lore.add("&3Wood: &f1250");
        age2_lore.add("&3Stone: &f750");
        yml.addDefault("change.age2-lore", age2_lore);

        ArrayList age3_lore = new ArrayList();
        age3_lore.add("&eChange to Age 3");
        age3_lore.add("&3Wood: &f2250");
        age3_lore.add("&3Stone: &f1250");
        yml.addDefault("change.age3-lore", age3_lore);

        ArrayList age4_lore = new ArrayList();
        age4_lore.add("&eChange to Age 4");
        age4_lore.add("&3Wood: &f4250");
        age4_lore.add("&3Stone: &f3150");
        yml.addDefault("change.age4-lore", age4_lore);

        yml.addDefault("archery.bow.displayname", "&8Bow");
        ArrayList<String> bow_lore = new ArrayList<>();
        bow_lore.add("&6Gold: &f30");
        bow_lore.add("&2Quantity: &f1");
        yml.addDefault("archery.bow.lore", bow_lore);

        yml.addDefault("archery.arrows5.displayname", "&8Arrows");
        ArrayList<String> a5_lore = new ArrayList<>();
        a5_lore.add("&6Gold: &f5");
        a5_lore.add("&2Quantity: &f5");
        yml.addDefault("archery.arrows5.lore", a5_lore);

        yml.addDefault("archery.arrows10.displayname", "&8Arrows");
        ArrayList<String> a10_lore = new ArrayList<>();
        a10_lore.add("&6Gold: &f7");
        a10_lore.add("&2Quantity: &f10");
        yml.addDefault("archery.arrows10.lore", a10_lore);

        yml.addDefault("stable.horse.displayname", "&8Horse");
        ArrayList<String> horse_lore = new ArrayList<>();
        horse_lore.add("&6Gold: &f30");
        horse_lore.add("&2Quantity: &f1");
        yml.addDefault("stable.horse.lore", horse_lore);

        yml.addDefault("trifarrow.arrow.displayname", "&8Exploding Arrows");
        ArrayList<String> arrow_explode = new ArrayList<>();
        arrow_explode.add("&6Gold: &f20");
        arrow_explode.add("&2Quantity: &f1");
        yml.addDefault("trifarrow.arrow.lore", arrow_explode);

        //laboratory
        yml.addDefault("lab.swiftness.name", "&dPotion of Swiftness");
        yml.addDefault("lab.swiftness.lore", price(35, 1));
        yml.addDefault("lab.fireresistance.name", "&dPotion of Fire Resistance");
        yml.addDefault("lab.fireresistance.lore", price(35, 1));
        yml.addDefault("lab.healing.name", "&dPotion of Healing");
        yml.addDefault("lab.healing.lore",price(35, 1));
        yml.addDefault("lab.nightvision.name", "&dPotion of Night Vision");
        yml.addDefault("lab.nightvision.lore", price(35, 1));
        yml.addDefault("lab.leaping.name", "&dPotion of Leaping");
        yml.addDefault("lab.leaping.lore", price(35, 1));
        yml.addDefault("lab.waterbreathing.name", "&dPotion of Water Breathing");
        yml.addDefault("lab.waterbreathing.lore", price(35, 1));
        yml.addDefault("lab.splashswiftness.name", "&dPotion of Swiftness");
        yml.addDefault("lab.splashswiftness.lore", price(75, 1));
        yml.addDefault("lab.regeneration.name", "&dPotion of Regeneration");
        yml.addDefault("lab.regeneration.lore", price(50, 1));
        yml.addDefault("lab.splashleaping.name", "&dPotion of Leaping");
        yml.addDefault("lab.splashleaping.lore", price(75, 1));

        yml.options().copyDefaults(true);
        try {
            yml.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<String> price(Integer price, Integer quantity){
        ArrayList arrayList = new ArrayList();
        arrayList.add("&6Gold: &f"+price);
        arrayList.add("&2Quantity: &f"+quantity);
        return arrayList;
    }

    public static String getMsg(String string){
        return yml.getString(string).replace("{prefix}", PREFIX).replace('&','§');
    }

    public static ArrayList<String> getArray(String string){
        return (ArrayList<String>) yml.getStringList(string);
    }
}
