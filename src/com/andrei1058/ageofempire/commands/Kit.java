package com.andrei1058.ageofempire.commands;

import com.andrei1058.ageofempire.game.Kits;
import com.andrei1058.ageofempire.game.Status;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.stream.Collectors;

import static com.andrei1058.ageofempire.Main.STATUS;
import static com.andrei1058.ageofempire.Main.kits;
import static com.andrei1058.ageofempire.configuration.Messages.getMsg;

public class Kit implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be used by players.");
            return true;
        }

        Player player = (Player) sender;

        if (STATUS != Status.LOBBY && STATUS != Status.STARTING) {
            player.sendMessage(getMsg("kit-not-allowed"));
            return true;
        }

        if (args.length == 0) {
            // No arguments provided, show the list of available kits
            String availableKits = Arrays.stream(Kits.values())
                    .map(Enum::name)
                    .collect(Collectors.joining(", "));
            player.sendMessage(getMsg("available-kits").replace("{kits}", availableKits));
            return true;
        }

        String kitName = args[0].toUpperCase();
        System.out.println("Kit name: " + kitName);
        try {
            Kits selectedKit = Kits.valueOf(kitName);
            System.out.println("Selected kit: " + selectedKit);

            String translatedKitName = getMsg("kit." + selectedKit + ".name");

            // Logic to assign the kit to the player
            player.sendMessage(getMsg("kit-selected").replace("{kit}", translatedKitName));
            kits.put(player, selectedKit);
        } catch (IllegalArgumentException e) {
            player.sendMessage(getMsg("invalid-kit"));
        }

        return true;
    }
}
