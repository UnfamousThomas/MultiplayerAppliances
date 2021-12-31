package us.unfamousthomas.multiplayerappliances.enums;

import org.bukkit.ChatColor;
import us.unfamousthomas.multiplayerappliances.lang.FinalMessages;

public enum PluginMessages {

    START(FinalMessages.prefix + " &cPlugin has been enabled... Yay!"),
    END(FinalMessages.prefix + " &cPlugin has been disabled. Yeet!"),
    PLAYER_ONLY(FinalMessages.prefix + " &4&lThis command can only be used by players."),
    MESSAGES_NOT_RECEIVED(FinalMessages.prefix + " &cYou have not received any messages."),
    USER_LAST_RECEIVED_FROM_CANNOT_BE_FOUND(FinalMessages.prefix + " &cThe user you last received a message from is not on the server."),
    NOT_FOUND(FinalMessages.prefix + " &cUser not found!"),
    INVALID_USAGE(FinalMessages.prefix + " &cInvalid usage for this command."),
    RANDOM_JOKE(FinalMessages.prefix + " &cYour random joke is: &6%joke%&c."),
    YOURSELF_MSG(FinalMessages.prefix + " &cYou crazy person, stop messaging yourself... Just stop!"),
    MESSAGE("&f&l%sender% &7» &f&l%receiver%&e: &f&o%msg%"),
    OP_NEEDED(FinalMessages.prefix + " &cYou need to be a &4&loperator &cto use that command."),
    MAINTENANCE_MOTD("&cServer is currently in maintenance mode. OPs only!"),
    MAINTENANCE_KICK("&cServer is currently in maintenance mode, please try again later."),
    MAINTENANCE_MODE_STATE(FinalMessages.prefix + " &cmaintenance mode is now &6%state%"),
    PVP_STATE(FinalMessages.prefix + " &cPvP is now &6%state%"),
    PVP_WARNING("&4WARNING! &c&lPvP &chas been turned %state%!"),
    NOT_KICKED_MAINTENANCE("&cMaintenance has begun, however, you have not been kicked due to being a operator."),
    RELOADED(FinalMessages.prefix + " &cConfigs have been reloaded (%list%).");

    private String message;
    PluginMessages(String message) {
        this.message = message;
    }

    private String getMessage() {
        return this.message;
    }

    public String getRealMessage() {
        return ChatColor.translateAlternateColorCodes('&', getMessage());
    }
}
