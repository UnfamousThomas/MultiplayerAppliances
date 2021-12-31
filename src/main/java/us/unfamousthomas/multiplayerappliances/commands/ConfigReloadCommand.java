package us.unfamousthomas.multiplayerappliances.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import us.unfamousthomas.multiplayerappliances.configuration.AdminSettingsConfig;
import us.unfamousthomas.multiplayerappliances.enums.PluginMessages;
import us.unfamousthomas.multiplayerappliances.managers.AdminSettingsManager;

public class ConfigReloadCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(PluginMessages.PLAYER_ONLY.getRealMessage());
        } else {
            Player player = (Player) sender;
            if (player.isOp()) {
                AdminSettingsConfig.reload();
                AdminSettingsManager.getInstance().load();
                String reloadedList = "config.yml & adminSettings.yml";
                player.sendMessage(PluginMessages.RELOADED.getRealMessage().replace("%list%", reloadedList));
            } else {
                player.sendMessage(PluginMessages.OP_NEEDED.getRealMessage());
            }
        }
        return true;
    }
}
