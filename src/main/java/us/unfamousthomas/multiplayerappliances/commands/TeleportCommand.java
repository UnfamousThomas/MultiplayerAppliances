package us.unfamousthomas.multiplayerappliances.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import us.unfamousthomas.multiplayerappliances.MultiplayerAppliances;
import us.unfamousthomas.multiplayerappliances.enums.JokeMessage;
import us.unfamousthomas.multiplayerappliances.enums.PluginMessages;

import java.util.*;

public class TeleportCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(PluginMessages.PLAYER_ONLY.getRealMessage());
            return true;
        }
        Player player = (Player) sender;

        if(args.length == 1) {
            Player target = Bukkit.getPlayerExact(args[0]);
            if(target == null) {
                player.sendMessage(PluginMessages.NOT_FOUND.getRealMessage());
                return true;
            }

            MultiplayerAppliances.getPluginInstance().getTeleportManager().addRequest(player, target);
        } else if (args.length == 2 && args[0].equalsIgnoreCase("accept")) {
            String acceptFrom = args[1];
            UUID uuid = Bukkit.getOfflinePlayer(acceptFrom).getUniqueId();

            MultiplayerAppliances.getPluginInstance().getTeleportManager().findRequest(uuid, player.getUniqueId()).confirmTeleport();
            //todo send msg

        } else {
            player.sendMessage(PluginMessages.INVALID_USAGE.getRealMessage());
        }

        return true;
    }



}
