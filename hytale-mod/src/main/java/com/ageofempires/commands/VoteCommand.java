package com.ageofempires.commands;

import com.ageofempires.AgeOfEmpiresPlugin;
import com.ageofempires.data.PlayerSession;
import com.hypixel.hytale.server.core.command.system.AbstractCommand;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.CommandSender;
import com.hypixel.hytale.server.core.command.system.arguments.system.RequiredArg;
import com.hypixel.hytale.server.core.command.system.arguments.types.ArgTypes;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.Message;

import javax.annotation.Nonnull;
import java.util.concurrent.CompletableFuture;

/**
 * /vote <option> - Vote for game options
 */
public class VoteCommand extends AbstractCommand {

    private final AgeOfEmpiresPlugin plugin;
    private final RequiredArg<String> optionArg;
    
    public VoteCommand(AgeOfEmpiresPlugin plugin) {
        super("vote", "Vote for game options");
        this.plugin = plugin;
        
        optionArg = withRequiredArg("option", "Option to vote for (map/time/team)", ArgTypes.STRING);
    }
    
    @Override
    protected String generatePermissionNode() {
        return "aoe.command.vote";
    }
    
    @Override
    protected CompletableFuture<Void> execute(@Nonnull CommandContext context) {
        CommandSender sender = context.sender();
        
        if (!(sender instanceof Player)) {
            sender.sendMessage(Message.raw("§cThis command can only be used by players!"));
            return CompletableFuture.completedFuture(null);
        }
        
        Player player = (Player) sender;
        PlayerSession session = plugin.getGameManager().getPlayerSession(player.getRef());
        
        if (session == null || session.getGameSession() == null) {
            sender.sendMessage(Message.raw("§cYou must be in a game lobby to vote!"));
            return CompletableFuture.completedFuture(null);
        }
        
        GameSession gameSession = session.getGameSession();
        
        if (gameSession.isRunning()) {
            sender.sendMessage(Message.raw("§cVoting is only available before the game starts!"));
            return CompletableFuture.completedFuture(null);
        }
        
        String option = optionArg.get(context).toLowerCase();
        
        switch (option) {
            case "map1":
            case "map2":
            case "map3":
                handleMapVote(sender, gameSession, player, option);
                break;
                
            case "day":
            case "night":
                handleTimeVote(sender, gameSession, player, option);
                break;
                
            case "teams2":
            case "teams4":
                handleTeamVote(sender, gameSession, player, option);
                break;
                
            default:
                showVoteOptions(sender);
                break;
        }
        
        return CompletableFuture.completedFuture(null);
    }
    
    private void handleMapVote(CommandSender sender, GameSession session, Player player, String option) {
        int mapNumber = Integer.parseInt(option.replace("map", ""));
        session.getVoteManager().voteMap(player.getRef(), mapNumber);
        sender.sendMessage(Message.raw("§aVoted for Map " + mapNumber + "!"));
    }
    
    private void handleTimeVote(CommandSender sender, GameSession session, Player player, String option) {
        session.getVoteManager().voteTime(player.getRef(), option.equals("day"));
        sender.sendMessage(Message.raw("§aVoted for " + (option.equals("day") ? "Day" : "Night") + " time!"));
    }
    
    private void handleTeamVote(CommandSender sender, GameSession session, Player player, String option) {
        int teams = option.equals("teams2") ? 2 : 4;
        session.getVoteManager().voteTeams(player.getRef(), teams);
        sender.sendMessage(Message.raw("§aVoted for " + teams + " teams!"));
    }
    
    private void showVoteOptions(CommandSender sender) {
        sender.sendMessage(Message.raw("§9===== Vote Options ====="));
        sender.sendMessage(Message.raw("§6Map:"));
        sender.sendMessage(Message.raw("§f  /vote map1§7 - Vote for map 1"));
        sender.sendMessage(Message.raw("§f  /vote map2§7 - Vote for map 2"));
        sender.sendMessage(Message.raw("§f  /vote map3§7 - Vote for map 3"));
        sender.sendMessage(Message.raw("§6Time:"));
        sender.sendMessage(Message.raw("§f  /vote day§7 - Vote for daytime"));
        sender.sendMessage(Message.raw("§f  /vote night§7 - Vote for nighttime"));
        sender.sendMessage(Message.raw("§6Teams:"));
        sender.sendMessage(Message.raw("§f  /vote teams2§7 - Vote for 2 teams"));
        sender.sendMessage(Message.raw("§f  /vote teams4§7 - Vote for 4 teams"));
        sender.sendMessage(Message.raw("§9========================"));
    }
}
