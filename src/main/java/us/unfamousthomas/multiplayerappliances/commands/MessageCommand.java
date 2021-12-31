package us.unfamousthomas.multiplayerappliances.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import us.unfamousthomas.multiplayerappliances.enums.PluginMessages;
import us.unfamousthomas.multiplayerappliances.managers.DirectMessageManager;

import java.util.Arrays;

public class MessageCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(PluginMessages.PLAYER_ONLY.getRealMessage());
        } else {
            if(args.length < 2) {
                sender.sendMessage(PluginMessages.INVALID_USAGE.getRealMessage());
                return true;
            }
            Player player = (Player) sender;

            String target = args[0];
            String[] messageArray = Arrays.copyOfRange(args, 1, args.length);

            String message = String.join(" ", messageArray);

            DirectMessageManager.getInstance().sendMessage(player, target, message);
        }
        return true;
    }
}
