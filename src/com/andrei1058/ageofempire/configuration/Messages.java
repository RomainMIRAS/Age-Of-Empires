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
        yml.addDefault("player-join", "{prefix} &7%player% a rejoint le jeu !");
        yml.addDefault("action-player-join", "&e{player} &7a rejoint le jeu !");
        yml.addDefault("action-player-left", "&e{player} &7a quitté le jeu !");
        yml.addDefault("player-died", "{player} &aest mort !");
        yml.addDefault("help-item-on", "&aAide Activée");
        yml.addDefault("help-item-off", "&cAide Désactivée");
        yml.addDefault("leave-item", "&aRetour au Hub");
        yml.addDefault("team-choosing.blue", "&9Bleu");
        yml.addDefault("team-choosing.green", "&aVert");
        yml.addDefault("team-choosing.yellow", "&eJaune");
        yml.addDefault("team-choosing.red", "&cRouge");
        yml.addDefault("team-choosing.green-join", "{prefix} &aVous rejoignez l'équipe Verte");
        yml.addDefault("team-choosing.red-join", "{prefix} &cVous rejoignez l'équipe Rouge");
        yml.addDefault("team-choosing.yellow-join", "{prefix} &eVous rejoignez l'équipe Jaune");
        yml.addDefault("team-choosing.blue-join", "{prefix} &9Vous rejoignez l'équipe Bleue");
        yml.addDefault("team-choosing.unbalanced-teams", "&eVous ne pouvez pas rejoindre cette équipe pour le moment !");
        yml.addDefault("game-start", "&6Le jeu commencera dans &e{time} &6seconde(s).");
        yml.addDefault("help.ison", "{prefix} &9[&eAide&9] &aL'aide est activée, utilisez la commande /h pour &2Activer&e/&cDésactiver &al'aide.");
        yml.addDefault("help.cutting-wood", "{prefix} &9[&eAide&9] &aCouper du &2Bois &apermet d'obtenir des ressources pour vous et votre base.");
        yml.addDefault("help.gold-stuff", "{prefix} &9[&eAide&9] &2L'Or &aest utilisé pour acheter de l'équipement dans les bâtiments. Vous gagnez de l'or en collectant des ressources ou en tuant des joueurs.");
        yml.addDefault("help.stone", "{prefix} &9[&eAide&9] &aMiner de la &2Pierre (Andésite) &apermet d'obtenir des ressources pour vous et votre base.");
        yml.addDefault("help.start-guide", "{prefix} &9[&eAide&9] &aBienvenue dans Age of Empire. Ce jeu consiste à collecter des ressources pour vous et votre royaume. Ces ressources vous permettront de construire des bâtiments.");
        yml.addDefault("help.start-buildings", "{prefix} &9[&eAide&9] &aCes bâtiments sont utilisés pour développer votre royaume et vous permettent d'acheter divers objets (armes, nourriture, etc.).");
        yml.addDefault("help.start-resources", "{prefix} &9[&eAide&9] &aPour commencer, allez miner de la Pierre (Andésite) et du Bois.");
        yml.addDefault("help.enough-res-forum", "{prefix} &9[&eAide&9] &aVotre Royaume a suffisamment de ressources pour que vous puissiez acheter un bâtiment. Retournez à votre &2Forum&a, cliquez sur le PNJ et sélectionnez un bâtiment. Pourquoi ne pas commencer par la &2Forge&a ?");
        yml.addDefault("help.vote", "{prefix} &9[&eAide&9] &aQuelqu'un dans votre équipe a ouvert un vote. Ce vote vous permet d'acheter un nouveau bâtiment ou de changer d'Âge ! Pour valider votre vote, vous devez &2Clic Droit &aavec la &2boule de slime &atrouvée dans le &2dernier emplacement de votre inventaire&a. Si vous n'êtes pas d'accord avec la motion proposée, vous n'avez rien à faire.");
        yml.addDefault("scoreboard.title", "&cAgeOfEmpire");
        yml.addDefault("scoreboard.14", "&6Âge : &f");
        yml.addDefault("scoreboard.13", "§1");
        yml.addDefault("scoreboard.12", "&bBois : &f");
        yml.addDefault("scoreboard.11", "&bPierre : &f");
        yml.addDefault("scoreboard.10", "&eOr : &f");
        yml.addDefault("scoreboard.9", "§2");
        yml.addDefault("scoreboard.8", "&aParcelles");
        yml.addDefault("scoreboard.7", "&2Petite : &f");
        yml.addDefault("scoreboard.6", "&2Moyenne : &f");
        yml.addDefault("scoreboard.5", "&2Grande : &f");
        yml.addDefault("scoreboard.4", "§3");
        yml.addDefault("scoreboard.3", "&7PvP : &f");
        yml.addDefault("scoreboard.3_2", "&7Assaut : &f");
        yml.addDefault("scoreboard.2", "§4");
        yml.addDefault("scoreboard.1", "&7Grimmjoow Server");
        yml.addDefault("villagers.forum", "&c&lForum");
        yml.addDefault("villagers.buy-buildings", "&b&lAcheter des bâtiments pour votre royaume");
        yml.addDefault("pvp-on", "{prefix} &6PvP activé, des minerais rares sont apparus au centre de la carte.");
        yml.addDefault("assaults-on", "{prefix} &6Assauts activés.");
        yml.addDefault("chat.game", "(Équipe) {v_prefix}&f{player} {v_suffix}&f{message}");
        yml.addDefault("chat.lobby", "(Tous) {v_prefix}&f{player} {v_suffix}&f{message}");
        yml.addDefault("cant-break", "{prefix} &cVous ne pouvez pas casser ce bloc dans un royaume");
        yml.addDefault("cant-vote", "{prefix} &cVous ne pouvez pas voter pour le moment (un vote est déjà en cours)");
        yml.addDefault("forum.age1", "&cÂge 1");
        yml.addDefault("forum.age2", "&cÂge 2");
        yml.addDefault("forum.age3", "&cÂge 3");
        yml.addDefault("forum.age4", "&cÂge 4");
        yml.addDefault("forum.age-buldings", "&6Bâtiments pour cet âge ➤");
        yml.addDefault("forum.to-build", "&a&lÀ construire");
        yml.addDefault("forum.built", "&6&lDéjà construit");
        yml.addDefault("villager.forum-attacked", "&e&lForum attaqué");
        yml.addDefault("villager.cant-open", "{prefix} &6Vous ne pouvez pas ouvrir ce menu.");

        yml.addDefault("forum."+forge+".displayname", "&e&lForge");
        yml.addDefault("forum.violence", "{prefix} &eOuch! Vous devez faire un clic droit pour ouvrir mon menu... &cPas besoin de violence ! :C");
        ArrayList forge_lore = new ArrayList();
        forge_lore.add("&3Bois: &f150");
        forge_lore.add("&3Pierre: &f75");
        forge_lore.add("&3Parcelle: &fPetite");
        forge_lore.add("&3Description: &bAcheter des armes de mêlée.");
        yml.addDefault("forum."+forge+".lore", forge_lore);
        yml.addDefault("forum."+forge+".holo", "&bAcheter des armes de mêlée");

        yml.addDefault("forum."+mill+".displayname", "&e&lMoulin");
        ArrayList mill_lore = new ArrayList();
        mill_lore.add("&3Bois: &f150");
        mill_lore.add("&3Pierre: &f75");
        mill_lore.add("&3Parcelle: &fPetite");
        mill_lore.add("&3Description: &bAcheter de la nourriture.");
        yml.addDefault("forum."+mill+".lore", mill_lore);
        yml.addDefault("forum."+mill+".holo", "&bAcheter de la nourriture");

        yml.addDefault("forum."+stone_mine+".displayname", "&e&lMine de Pierre");
        ArrayList stone_lore = new ArrayList();
        stone_lore.add("&3Bois: &f150");
        stone_lore.add("&3Pierre: &f75");
        stone_lore.add("&3Parcelle: &fPetite");
        stone_lore.add("&3Description: &bProduit de la Pierre automatiquement.");
        yml.addDefault("forum."+stone_mine+".lore", stone_lore);
        yml.addDefault("forum."+stone_mine+".holo", "&bProduit de la Pierre automatiquement");

        yml.addDefault("forum."+gold_mine+".displayname", "&e&lMine d'Or");
        ArrayList gold_lore = new ArrayList();
        gold_lore.add("&3Bois: &f150");
        gold_lore.add("&3Pierre: &f100");
        gold_lore.add("&3Parcelle: &fPetite");
        gold_lore.add("&3Description: &bProduit de l'Or automatiquement.");
        yml.addDefault("forum."+gold_mine+".lore", gold_lore);
        yml.addDefault("forum."+gold_mine+".holo", "&bProduit de l'Or automatiquement");

        yml.addDefault("forum."+sawmill+".displayname", "&e&lScierie");
        ArrayList sawmill_lore = new ArrayList();
        sawmill_lore.add("&3Bois: &f150");
        sawmill_lore.add("&3Pierre: &f75");
        sawmill_lore.add("&3Parcelle: &fPetite");
        sawmill_lore.add("&3Description: &bProduit du Bois automatiquement.");
        yml.addDefault("forum."+sawmill+".lore", sawmill_lore);
        yml.addDefault("forum."+sawmill+".holo", "&bProduit du Bois automatiquement");

        yml.addDefault("forum."+market+".displayname", "&e&lMarché");
        ArrayList market_lore = new ArrayList();
        market_lore.add("&3Bois: &f100");
        market_lore.add("&3Pierre: &f50");
        market_lore.add("&3Parcelle: &fPetite");
        market_lore.add("&3Description: &bAcheter divers objets.");
        yml.addDefault("forum."+market+".lore", market_lore);
        yml.addDefault("forum."+market+".holo", "&bAcheter divers objets");

        yml.addDefault("forum."+kennel+".displayname", "&e&lChenil");
        ArrayList kennel_lore = new ArrayList();
        kennel_lore.add("&3Bois: &f100");
        kennel_lore.add("&3Pierre: &f50");
        kennel_lore.add("&3Parcelle: &fPetite");
        kennel_lore.add("&3Description: &bAcheter des chiens.");
        yml.addDefault("forum."+kennel+".lore", kennel_lore);
        yml.addDefault("forum."+kennel+".holo", "&bAcheter des chiens");

        yml.addDefault("forum."+sabotage+".displayname", "&e&lAtelier de Sabotage");
        ArrayList sabotagew_lore = new ArrayList();
        sabotagew_lore.add("&3Bois: &f100");
        sabotagew_lore.add("&3Pierre: &f50");
        sabotagew_lore.add("&3Parcelle: &fPetite");
        sabotagew_lore.add("&3Description: &bAcheter des objets destructeurs.");
        yml.addDefault("forum."+sabotage+".lore", sabotagew_lore);
        yml.addDefault("forum."+sabotage+".holo", "&bAcheter des objets destructeurs");

        yml.addDefault("forum."+workshop+".displayname", "&e&lAtelier");
        ArrayList workshop_lore = new ArrayList();
        workshop_lore.add("&3Bois: &f100");
        workshop_lore.add("&3Pierre: &f50");
        workshop_lore.add("&3Parcelle: &fPetite");
        workshop_lore.add("&3Description: &bAcheter divers blocs.");
        yml.addDefault("forum."+workshop+".lore", workshop_lore);
        yml.addDefault("forum."+workshop+".holo", "&bAcheter divers blocs");

        yml.addDefault("forum."+archery+".displayname", "&e&lMagasin de Tir à l'Arc");
        ArrayList archeryl = new ArrayList();
        archeryl.add("&3Bois: &f300");
        archeryl.add("&3Pierre: &f150");
        archeryl.add("&3Parcelle: &fMoyenne");
        archeryl.add("&3Description: &bAcheter des armes à distance.");
        yml.addDefault("forum."+archery+".lore", archeryl);
        yml.addDefault("forum."+archery+".holo", "&bAcheter des armes à distance");

        yml.addDefault("forum."+trifarrow+".displayname", "&e&lTrifArrow");
        ArrayList trifarrow_lore = new ArrayList();
        trifarrow_lore.add("&3Bois: &f375");
        trifarrow_lore.add("&3Pierre: &f175");
        trifarrow_lore.add("&3Parcelle: &fMoyenne");
        trifarrow_lore.add("&3Description: &bAcheter diverses flèches.");
        yml.addDefault("forum."+trifarrow+".lore", trifarrow_lore);
        yml.addDefault("forum."+trifarrow+".holo", "&bAcheter diverses flèches");

        yml.addDefault("forum."+stable+".displayname", "&e&lÉcurie");
        ArrayList stable_lore = new ArrayList();
        stable_lore.add("&3Bois: &f200");
        stable_lore.add("&3Pierre: &f100");
        stable_lore.add("&3Parcelle: &fMoyenne");
        stable_lore.add("&3Description: &bAcheter des chevaux.");
        yml.addDefault("forum."+stable+".lore", stable_lore);
        yml.addDefault("forum."+stable+".holo", "&bAcheter des chevaux");

        yml.addDefault("forum."+armory+".displayname", "&e&lArmurerie");
        ArrayList armory_lore = new ArrayList();
        armory_lore.add("&3Bois: &f300");
        armory_lore.add("&3Pierre: &f150");
        armory_lore.add("&3Parcelle: &fMoyenne");
        armory_lore.add("&3Description: &bAcheter des armures.");
        yml.addDefault("forum."+armory+".lore", armory_lore);
        yml.addDefault("forum."+armory+".holo", "&bAcheter des armures");

        yml.addDefault("forum."+laboratory+".displayname", "&e&lLaboratoire");
        ArrayList laboratory_lore = new ArrayList();
        laboratory_lore.add("&3Bois: &f300");
        laboratory_lore.add("&3Pierre: &f150");
        laboratory_lore.add("&3Parcelle: &fMoyenne");
        laboratory_lore.add("&3Description: &bAcheter des potions.");
        yml.addDefault("forum."+laboratory+".lore", laboratory_lore);
        yml.addDefault("forum."+laboratory+".holo", "&bAcheter des potions");

        yml.addDefault("forum."+guild+".displayname", "&e&lGuilde");
        ArrayList guild_lore = new ArrayList();
        guild_lore.add("&3Bois: &f600");
        guild_lore.add("&3Pierre: &f300");
        guild_lore.add("&3Parcelle: &fGrande");
        guild_lore.add("&3Description: &bAcheter des enchantements.");
        yml.addDefault("forum."+guild+".lore", guild_lore);
        yml.addDefault("forum."+guild+".holo", "&bAcheter des enchantements");

        yml.addDefault("forum."+training_center+".displayname", "&e&lCentre d'Entraînement");
        ArrayList training_center_lore = new ArrayList();
        training_center_lore.add("&3Bois: &f500");
        training_center_lore.add("&3Pierre: &f250");
        training_center_lore.add("&3Parcelle: &fGrande");
        training_center_lore.add("&3Description: &bProduit de l'XP automatiquement.");
        yml.addDefault("forum."+training_center+".lore", training_center_lore);
        yml.addDefault("forum."+training_center+".holo", "&bProduit de l'XP automatiquement");

        yml.addDefault("new-vote", "&9{player} &1souhaite créer un(e) {building} &f. &2{votes}&f/&4{team}");
        yml.addDefault("vote-denied", "&cLe vote de {player}&4 a été refusé.");
        yml.addDefault("vote-accepted", "&2Le vote de {player}&a a été accepté.");
        yml.addDefault("insufficient-resources", "{prefix} &cVotre équipe n'a pas assez de &eresources &c(Bois manquant&e: &f{wood}&6: Pierres manquantes&e: &f{stone}&6).");
        yml.addDefault("insufficient-gold", "{prefix} &cOr insuffisant !");
        yml.addDefault("locked-slot", "&cEmplacement verrouillé");
        yml.addDefault("forum-paper", "Forum");
        yml.addDefault("validate-vote", "&aValidez votre vote");
        yml.addDefault("constructor.displayname", "&8Constructeur");
        ArrayList<String> constructor_lore = new ArrayList<>();
        constructor_lore.add("&fClique droit");
        constructor_lore.add("&fsur une des parcelles");
        constructor_lore.add("&fde votre équipe");
        constructor_lore.add("&favec cet objet en main");
        constructor_lore.add("&fpour construire le");
        constructor_lore.add("&fbâtiment sélectionné");
        constructor_lore.add("&cJeter pour annuler");
        yml.addDefault("constructor.lore", constructor_lore);
        yml.addDefault("having-construct", "{prefix} &9Vous devez construire un bâtiment avant de pouvoir voter à nouveau");
        yml.addDefault("build-canceled", "{prefix} &6{player} vient d'annuler la construction d'un {building}");
        yml.addDefault("build-started", "{prefix} &2{player} vient de commencer la construction d'un {building}");
        yml.addDefault("already-built", "&c&lVous avez déjà construit ceci !");
        yml.addDefault("vote-age", "&9{player} &1souhaite changer d'âge &f. &2{votes}&f/&4{team}");
        yml.addDefault("blue-changed-age", "{prefix} &fL'équipe &9Bleue &fvient de passer à l'âge &e{age}");
        yml.addDefault("green-changed-age", "{prefix} &fL'équipe &aVerte &fvient de passer à l'âge &e{age}");
        yml.addDefault("yellow-changed-age", "{prefix} &fL'équipe &eJaune &fvient de passer à l'âge &e{age}");
        yml.addDefault("red-changed-age", "{prefix} &fL'équipe &cRouge &fvient de passer à l'âge &e{age}");
        yml.addDefault("cant-construct-outside", "&cVous ne pouvez pas construire de bâtiments en dehors de votre base.");
        yml.addDefault("cant-construct-here", "&cVous ne pouvez pas construire de bâtiments ici.");
        yml.addDefault("cant-place-here", "{prefix} &cVous ne pouvez pas placer de blocs ici !");
        yml.addDefault("cant-construct-size", "&cVous devez être sur une parcelle de la bonne taille pour construire cette structure.");
        yml.addDefault("cant-vote-full", "&cVous ne pouvez pas voter pour ce bâtiment car vous n'avez pas de parcelle libre de ce type");
        yml.addDefault("built-success", "{prefix} &e&l{building} &aconstruit avec succès.");
        yml.addDefault("motd.lobby", "&aLobby");
        yml.addDefault("motd.starting", "&eDémarrage");
        yml.addDefault("motd.playing", "&cEn jeu");
        yml.addDefault("motd.restarting", "&4Redémarrage");
        yml.addDefault("new-kill", "{prefix} {player} &aa été tué par {killer}");
        yml.addDefault("victory.green", "&aVictoire des Verts");
        yml.addDefault("victory.blue", "&9Victoire des Bleus");
        yml.addDefault("victory.red", "&cVictoire des Rouges");
        yml.addDefault("victory.yellow", "&eVictoire des Jaunes");
        yml.addDefault("base-destroyed.blue", "{prefix} &eLa base de l'équipe &9Bleue &2a été détruite par {team}");
        yml.addDefault("base-destroyed.green", "{prefix} &eLa base de l'équipe &aVerte &2a été détruite par {team}");
        yml.addDefault("base-destroyed.yellow", "{prefix} &eLa base de l'équipe Jaune &2a été détruite par {team}");
        yml.addDefault("base-destroyed.red", "{prefix} &eLa base de l'équipe &cRouge &2a été détruite par {team}");
        yml.addDefault("holo.gold", "&eOr &a+{amount}");
        yml.addDefault("holo.stone", "&ePierre &a+{amount}");
        yml.addDefault("holo.wood", "&eBois &a+{amount}");
        yml.addDefault("plot.small", "&2Petite parcelle");
        yml.addDefault("plot.medium", "&2Parcelle moyenne");
        yml.addDefault("plot.large", "&2Grande parcelle");

        yml.addDefault("forge.stonepickaxe.displayname", "&7Pioche en pierre");
        ArrayList<String> stonepickaxe = new ArrayList<>();
        stonepickaxe.add("&6Or: &f1");
        stonepickaxe.add("&2Quantité: &f1");
        yml.addDefault("forge.stonepickaxe.lore", stonepickaxe);

        yml.addDefault("forge.stonesword.displayname", "&7Épée en pierre");
        ArrayList<String> stonesword = new ArrayList<>();
        stonesword.add("&6Or: &f10");
        stonesword.add("&2Quantité: &f1");
        yml.addDefault("forge.stonesword.lore", stonesword);

        yml.addDefault("forge.ironsword.displayname", "&fÉpée en fer");
        ArrayList<String> ironsword = new ArrayList<>();
        ironsword.add("&6Or: &f30");
        ironsword.add("&2Quantité: &f1");
        yml.addDefault("forge.ironsword.lore", ironsword);

        yml.addDefault("forge.stoneaxe.displayname", "&7Hache en pierre");
        ArrayList<String> stoneaxe = new ArrayList<>();
        stoneaxe.add("&6Or: &f5");
        stoneaxe.add("&2Quantité: &f1");
        yml.addDefault("forge.stoneaxe.lore", stoneaxe);

        yml.addDefault("forge.ironaxe.displayname", "&fHache en fer");
        ArrayList<String> ironaxe = new ArrayList<>();
        ironaxe.add("&6Or: &f15");
        ironaxe.add("&2Quantité: &f1");
        yml.addDefault("forge.ironaxe.lore", ironaxe);

        yml.addDefault("forge.diamondsword.displayname", "&9Épée en diamant");
        ArrayList<String> diamondsowrd_l = new ArrayList<>();
        diamondsowrd_l.add("&6Or: &f50");
        diamondsowrd_l.add("&2Quantité: &f1");
        yml.addDefault("forge.diamondsword.lore", diamondsowrd_l);

        yml.addDefault("forge.diamondaxe.displayname", "&9Hache en diamant");
        ArrayList<String> diamondaxe_l = new ArrayList<>();
        diamondaxe_l.add("&6Or: &f25");
        diamondaxe_l.add("&2Quantité: &f1");
        yml.addDefault("forge.diamondaxe.lore", diamondaxe_l);

        yml.addDefault("mill.bread.displayname", "&7Pain");
        ArrayList<String> breadlore = new ArrayList<>();
        breadlore.add("&6Or: &f10");
        breadlore.add("&2Quantité: &f5");
        yml.addDefault("mill.bread.lore", breadlore);

        yml.addDefault("mill.steak.displayname", "&7Steak");
        ArrayList<String> steak = new ArrayList<>();
        steak.add("&6Or: &f22");
        steak.add("&2Quantité: &f5");
        yml.addDefault("mill.steak.lore", steak);

        yml.addDefault("mill.chicken.displayname", "&7Poulet");
        ArrayList<String> chicken = new ArrayList<>();
        chicken.add("&6Or: &f20");
        chicken.add("&2Quantité: &f5");
        yml.addDefault("mill.chicken.lore", chicken);

        yml.addDefault("mill.potato.displayname", "&7Pomme de terre");
        ArrayList<String> potato = new ArrayList<>();
        potato.add("&6Or: &f15");
        potato.add("&2Quantité: &f5");
        yml.addDefault("mill.potato.lore", potato);

//workshop
        yml.addDefault("workshop.grass.displayname", "&7Herbe");
        ArrayList<String> grass = new ArrayList<>();
        grass.add("&6Or: &f10");
        grass.add("&2Quantité: &f10");
        yml.addDefault("workshop.grass.lore", grass);

        yml.addDefault("workshop.dirt.displayname", "&7Terre");
        ArrayList<String> dirt = new ArrayList<>();
        dirt.add("&6Or: &f10");
        dirt.add("&2Quantité: &f10");
        yml.addDefault("workshop.dirt.lore", dirt);

        yml.addDefault("workshop.plank.displayname", "&7Planche");
        ArrayList<String> plank = new ArrayList<>();
        plank.add("&6Or: &f10");
        plank.add("&2Quantité: &f5");
        yml.addDefault("workshop.plank.lore", plank);

        yml.addDefault("workshop.sand.displayname", "&7Sable");
        ArrayList<String> sand = new ArrayList<>();
        sand.add("&6Or: &f10");
        sand.add("&2Quantité: &f5");
        yml.addDefault("workshop.sand.lore", sand);

        yml.addDefault("workshop.gravel.displayname", "&7Gravier");
        ArrayList<String> gravel = new ArrayList<>();
        gravel.add("&6Or: &f10");
        gravel.add("&2Quantité: &f5");
        yml.addDefault("workshop.gravel.lore", gravel);

        yml.addDefault("workshop.sponge.displayname", "&7Éponge");
        ArrayList<String> sponge = new ArrayList<>();
        sponge.add("&6Or: &f20");
        sponge.add("&2Quantité: &f5");
        yml.addDefault("workshop.sponge.lore", sponge);

        yml.addDefault("workshop.glass.displayname", "&7Verre");
        ArrayList<String> glass = new ArrayList<>();
        glass.add("&6Or: &f15");
        glass.add("&2Quantité: &f10");
        yml.addDefault("workshop.glass.lore", glass);

        yml.addDefault("workshop.lapis.displayname", "&7Bloc de Lapis");
        ArrayList<String> lapis = new ArrayList<>();
        lapis.add("&6Or: &f25");
        lapis.add("&2Quantité: &f5");
        yml.addDefault("workshop.lapis.lore", lapis);

        yml.addDefault("workshop.whitewool.displayname", "&7Laine blanche");
        ArrayList<String> whitewool = new ArrayList<>();
        whitewool.add("&6Or: &f10");
        whitewool.add("&2Quantité: &f10");
        yml.addDefault("workshop.whitewool.lore", whitewool);

        yml.addDefault("workshop.orangewool.displayname", "&7Laine orange");
        ArrayList<String> orangewool = new ArrayList<>();
        orangewool.add("&6Or: &f10");
        orangewool.add("&2Quantité: &f10");
        yml.addDefault("workshop.orangewool.lore", orangewool);

        yml.addDefault("workshop.magentawool.displayname", "&7Laine magenta");
        ArrayList<String> magentawool = new ArrayList<>();
        magentawool.add("&6Or: &f10");
        magentawool.add("&2Quantité: &f10");
        yml.addDefault("workshop.magentawool.lore", magentawool);

        yml.addDefault("workshop.lightbluewool.displayname", "&7Laine bleu clair");
        ArrayList<String> lightbluewool = new ArrayList<>();
        lightbluewool.add("&6Or: &f10");
        lightbluewool.add("&2Quantité: &f10");
        yml.addDefault("workshop.lightbluewool.lore", lightbluewool);

        yml.addDefault("workshop.yellowwool.displayname", "&7Laine jaune");
        ArrayList<String> yellowwool = new ArrayList<>();
        yellowwool.add("&6Or: &f10");
        yellowwool.add("&2Quantité: &f10");
        yml.addDefault("workshop.yellowwool.lore", yellowwool);

        yml.addDefault("workshop.lightgreenwool.displayname", "&7Laine vert clair");
        ArrayList<String> lightgreenwool = new ArrayList<>();
        lightgreenwool.add("&6Or: &f10");
        lightgreenwool.add("&2Quantité: &f10");
        yml.addDefault("workshop.lightgreenwool.lore", yellowwool);

        yml.addDefault("workshop.pinkwool.displayname", "&7Laine rose");
        ArrayList<String> pinkwool = new ArrayList<>();
        pinkwool.add("&6Or: &f10");
        pinkwool.add("&2Quantité: &f10");
        yml.addDefault("workshop.pinkwool.lore", pinkwool);

        yml.addDefault("workshop.graywool.displayname", "&7Laine grise");
        ArrayList<String> graywool = new ArrayList<>();
        graywool.add("&6Or: &f10");
        graywool.add("&2Quantité: &f10");
        yml.addDefault("workshop.graywool.lore", graywool);

        yml.addDefault("workshop.cyanwool.displayname", "&7Laine cyan");
        ArrayList<String> cyanwool = new ArrayList<>();
        cyanwool.add("&6Or: &f10");
        cyanwool.add("&2Quantité: &f10");
        yml.addDefault("workshop.cyanwool.lore", cyanwool);

        yml.addDefault("workshop.purplewool.displayname", "&7Laine violette");
        ArrayList<String> purplewool = new ArrayList<>();
        purplewool.add("&6Or: &f10");
        purplewool.add("&2Quantité: &f10");
        yml.addDefault("workshop.purplewool.lore", purplewool);

        yml.addDefault("workshop.bluewool.displayname", "&7Laine bleue");
        ArrayList<String> bluewool = new ArrayList<>();
        bluewool.add("&6Or: &f10");
        bluewool.add("&2Quantité: &f10");
        yml.addDefault("workshop.bluewool.lore", bluewool);

        yml.addDefault("workshop.blackwool.displayname", "&7Laine noire");
        ArrayList<String> blackwool = new ArrayList<>();
        blackwool.add("&6Or: &f10");
        blackwool.add("&2Quantité: &f10");
        yml.addDefault("workshop.blackwool.lore", blackwool);

        yml.addDefault("workshop.greenwool.displayname", "&7Laine verte");
        ArrayList<String> greenwool = new ArrayList<>();
        greenwool.add("&6Or: &f10");
        greenwool.add("&2Quantité: &f10");
        yml.addDefault("workshop.greenwool.lore", greenwool);

        yml.addDefault("workshop.redwool.displayname", "&7Laine rouge");
        ArrayList<String> redwool = new ArrayList<>();
        redwool.add("&6Or: &f10");
        redwool.add("&2Quantité: &f10");
        yml.addDefault("workshop.redwool.lore", redwool);

        yml.addDefault("workshop.brownwool.displayname", "&7Laine marron");
        ArrayList<String> brownwool = new ArrayList<>();
        brownwool.add("&6Or: &f10");
        brownwool.add("&2Quantité: &f10");
        yml.addDefault("workshop.brownwool.lore", brownwool);

        yml.addDefault("workshop.bricks.displayname", "&7Briques");
        ArrayList<String> bricks = new ArrayList<>();
        bricks.add("&6Or: &f15");
        bricks.add("&2Quantité: &f10");
        yml.addDefault("workshop.bricks.lore", bricks);

        yml.addDefault("workshop.mossstone.displayname", "&7Pierre moussue");
        ArrayList<String> mossstone = new ArrayList<>();
        mossstone.add("&6Or: &f15");
        mossstone.add("&2Quantité: &f10");
        yml.addDefault("workshop.mossstone.lore", mossstone);

        yml.addDefault("workshop.leaves.displayname", "&7Feuilles");
        ArrayList<String> leaves = new ArrayList<>();
        leaves.add("&6Or: &f15");
        leaves.add("&2Quantité: &f10");
        yml.addDefault("workshop.leaves.lore", leaves);

//market
        yml.addDefault("market.flintandsteel.displayname", "&eSilex et acier");
        ArrayList<String> flintandsteel = new ArrayList<>();
        flintandsteel.add("&6Or: &f5");
        flintandsteel.add("&2Quantité: &f1");
        yml.addDefault("workshop.flintandsteel.lore", flintandsteel);

        yml.addDefault("market.cobweb.displayname", "&eToile d'araignée");
        ArrayList<String> cobweb = new ArrayList<>();
        cobweb.add("&6Or: &f5");
        cobweb.add("&2Quantité: &f5");
        yml.addDefault("workshop.cobweb.lore", cobweb);

        yml.addDefault("market.torches.displayname", "&eTorches");
        ArrayList<String> torches = new ArrayList<>();
        torches.add("&6Or: &f5");
        torches.add("&2Quantité: &f12");
        yml.addDefault("workshop.torches.lore", torches);

        yml.addDefault("market.boat.displayname", "&eBateau");
        ArrayList<String> boat = new ArrayList<>();
        boat.add("&6Or: &f2");
        boat.add("&2Quantité: &f1");
        yml.addDefault("workshop.boat.lore", boat);

        yml.addDefault("sabotage.tnt.displayname", "&cTNT");
        ArrayList<String> tnt = new ArrayList<>();
        tnt.add("&6Or: &f5");
        tnt.add("&2Quantité: &f1");
        yml.addDefault("sabotage.tnt.lore", tnt);

        yml.addDefault("kennel.dog.displayname", "&cChien");
        ArrayList<String> dog = new ArrayList<>();
        dog.add("&6Or: &f50");
        dog.add("&2Quantité: &f1");
        yml.addDefault("kennel.dog.lore", dog);

        yml.addDefault("kennel.dog2.displayname", "&cChiens");
        ArrayList<String> dog2 = new ArrayList<>();
        dog2.add("&6Or: &f75");
        dog2.add("&2Quantité: &f2");
        yml.addDefault("kennel.dog2.lore", dog2);

        yml.addDefault("kennel.dog3.displayname", "&cChiens");
        ArrayList<String> dog3 = new ArrayList<>();
        dog3.add("&6Or: &f100");
        dog3.add("&2Quantité: &f3");
        yml.addDefault("kennel.dog3.lore", dog3);

        yml.addDefault("armory.ironarmor.displayname", "&fArmure en fer");
        ArrayList iron_armor = new ArrayList();
        iron_armor.add("&6Or: &f40");
        iron_armor.add("&2Quantité: &f1");
        yml.addDefault("armory.ironarmor.lore", iron_armor);

        yml.addDefault("armory.ironhorsearmor.displayname", "&fArmure de cheval en fer");
        ArrayList ironhorsearmor = new ArrayList();
        ironhorsearmor.add("&6Or: &f80");
        ironhorsearmor.add("&2Quantité: &f1");
        yml.addDefault("armory.ironhorsearmor.lore", ironhorsearmor);

        yml.addDefault("armory.diamondarmor.displayname", "&fArmure en diamant");
        ArrayList diamondarmor = new ArrayList();
        diamondarmor.add("&6Or: &f80");
        diamondarmor.add("&2Quantité: &f1");
        yml.addDefault("armory.diamondarmor.lore", diamondarmor);

        yml.addDefault("armory.diamondhorsearmor.displayname", "&fArmure de cheval en diamant");
        ArrayList diamondhorsearmor = new ArrayList();
        diamondhorsearmor.add("&6Or: &f80");
        diamondhorsearmor.add("&2Quantité: &f1");
        yml.addDefault("armory.diamondhorsearmor.lore", diamondhorsearmor);

        yml.addDefault("pvp-disabled", "{prefix} &eLe PvP est désactivé pour le moment, veuillez patienter.");
        yml.addDefault("x-attacked", "&e&l{villager} attaqué");
        yml.addDefault("yellow-building-explode", "{prefix} &eLe {building} &6de l'équipe jaune explosera dans 15 secondes !");
        yml.addDefault("blue-building-explode", "{prefix} &eLe {building} &6de l'équipe bleu explosera dans 15 secondes !");
        yml.addDefault("green-building-explode", "{prefix} &eLe {building} &6de l'équipe vert explosera dans 15 secondes !");
        yml.addDefault("red-building-explode", "{prefix} &eLe {building} &6de l'équipe rouge explosera dans 15 secondes !");

        ArrayList age2_lore = new ArrayList();
        age2_lore.add("&ePasser à l'Âge 2");
        age2_lore.add("&3Bois: &f1250");
        age2_lore.add("&3Pierre: &f750");
        yml.addDefault("change.age2-lore", age2_lore);

        ArrayList age3_lore = new ArrayList();
        age3_lore.add("&ePasser à l'Âge 3");
        age3_lore.add("&3Bois: &f2250");
        age3_lore.add("&3Pierre: &f1250");
        yml.addDefault("change.age3-lore", age3_lore);

        ArrayList age4_lore = new ArrayList();
        age4_lore.add("&ePasser à l'Âge 4");
        age4_lore.add("&3Bois: &f4250");
        age4_lore.add("&3Pierre: &f3150");
        yml.addDefault("change.age4-lore", age4_lore);

        yml.addDefault("archery.bow.displayname", "&8Arc");
        ArrayList<String> bow_lore = new ArrayList<>();
        bow_lore.add("&6Or: &f30");
        bow_lore.add("&2Quantité: &f1");
        yml.addDefault("archery.bow.lore", bow_lore);

        yml.addDefault("archery.arrows5.displayname", "&8Flèches");
        ArrayList<String> a5_lore = new ArrayList<>();
        a5_lore.add("&6Or: &f5");
        a5_lore.add("&2Quantité: &f5");
        yml.addDefault("archery.arrows5.lore", a5_lore);

        yml.addDefault("archery.arrows10.displayname", "&8Flèches");
        ArrayList<String> a10_lore = new ArrayList<>();
        a10_lore.add("&6Or: &f7");
        a10_lore.add("&2Quantité: &f10");
        yml.addDefault("archery.arrows10.lore", a10_lore);

        yml.addDefault("stable.horse.displayname", "&8Cheval");
        ArrayList<String> horse_lore = new ArrayList<>();
        horse_lore.add("&6Or: &f30");
        horse_lore.add("&2Quantité: &f1");
        yml.addDefault("stable.horse.lore", horse_lore);

        yml.addDefault("trifarrow.arrow.displayname", "&8Flèches Explosives");
        ArrayList<String> arrow_explode = new ArrayList<>();
        arrow_explode.add("&6Or: &f20");
        arrow_explode.add("&2Quantité: &f1");
        yml.addDefault("trifarrow.arrow.lore", arrow_explode);

        //laboratory
        yml.addDefault("lab.swiftness.name", "&dPotion de Rapidité");
        yml.addDefault("lab.swiftness.lore", price(35, 1));
        yml.addDefault("lab.fireresistance.name", "&dPotion de Résistance au Feu");
        yml.addDefault("lab.fireresistance.lore", price(35, 1));
        yml.addDefault("lab.healing.name", "&dPotion de Soin");
        yml.addDefault("lab.healing.lore", price(35, 1));
        yml.addDefault("lab.nightvision.name", "&dPotion de Vision Nocturne");
        yml.addDefault("lab.nightvision.lore", price(35, 1));
        yml.addDefault("lab.leaping.name", "&dPotion de Saut");
        yml.addDefault("lab.leaping.lore", price(35, 1));
        yml.addDefault("lab.waterbreathing.name", "&dPotion de Respiration Aquatique");
        yml.addDefault("lab.waterbreathing.lore", price(35, 1));
        yml.addDefault("lab.splashswiftness.name", "&dPotion de Rapidité (Éclaboussante)");
        yml.addDefault("lab.splashswiftness.lore", price(75, 1));
        yml.addDefault("lab.regeneration.name", "&dPotion de Régénération");
        yml.addDefault("lab.regeneration.lore", price(50, 1));
        yml.addDefault("lab.splashleaping.name", "&dPotion de Saut (Éclaboussante)");
        yml.addDefault("lab.splashleaping.lore", price(75, 1));

        //guild
        yml.addDefault("guild.sharpness.name", "&bTranchant 1");
        yml.addDefault("guild.sharpness.lore", price(20, 1));
        yml.addDefault("guild.knockback.name", "&bRecul 1");
        yml.addDefault("guild.knockback.lore", price(10, 1));
        yml.addDefault("guild.protection.name", "&bProtection 1");
        yml.addDefault("guild.protection.lore", price(20, 1));
        yml.addDefault("guild.thorns.name", "&bÉpines 1");
        yml.addDefault("guild.thorns.lore", price(10, 1));
        yml.addDefault("guild.featherfalling.name", "&bChute amortie 1");
        yml.addDefault("guild.featherfalling.lore", price(10, 1));
        yml.addDefault("guild.projectileprotection.name", "&bProtection contre les projectiles 1");
        yml.addDefault("guild.projectileprotection.lore", price(10, 1));
        yml.addDefault("guild.fireprotection.name", "&bProtection contre le feu 1");
        yml.addDefault("guild.fireprotection.lore", price(10, 1));
        yml.addDefault("guild.power.name", "&bPuissance 1");
        yml.addDefault("guild.power.lore", price(20, 1));
        yml.addDefault("guild.punch.name", "&bCoup 1");
        yml.addDefault("guild.punch.lore", price(10, 1));
        yml.addDefault("stats.displayname", "&e&lMes statistiques");
        yml.addDefault("stats.wins", "&3Victoires: &a%wins%");
        yml.addDefault("stats.kills", "&3Tués: &a%kills%");
        yml.addDefault("stats.deaths", "&3Morts: &a%deaths%");
        yml.addDefault("stats.gamesplayed", "&3Parties jouées: &a%games%");
        yml.addDefault("stats.kingskilled", "&3Rois tués: &a%kings%");
        yml.addDefault("vip-kick", "§cVous avez été expulsé car un VIP a rejoint le serveur.");

        yml.addDefault("cantDo", "{prefix} &cVous ne pouvez pas faire cela !");
        yml.addDefault("cantDoNow", "{prefix} &cVous ne pouvez pas faire cela maintenant !");
        yml.addDefault("notInGame", "{prefix} &cVous ne jouez pas !");
        yml.addDefault("stuckTp", "{prefix} &7Téléportation dans 5 secondes. Ne bougez pas !");
        yml.addDefault("stuckMove", "{prefix} &cVous avez bougé ! Téléportation annulée !");
        yml.addDefault("vip-kick", "§cVous avez été expulsé car un VIP a rejoint le serveur.");

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
