package us.unfamousthomas.multiplayerappliances.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import us.unfamousthomas.multiplayerappliances.enums.PluginMessages;
import us.unfamousthomas.multiplayerappliances.managers.DirectMessageManager;

public class ReplyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(PluginMessages.PLAYER_ONLY.getRealMessage());
        } else {
            if(args.length < 1) {
                sender.sendMessage(PluginMessages.INVALID_USAGE.getRealMessage());
                return true;
            }

            Player player = (Player) sender;

            String message = String.join(" ", args);

            DirectMessageManager.getInstance().sendResponse(player.getUniqueId(), message);
        }
        return true;
    }
}
