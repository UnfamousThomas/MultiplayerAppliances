package us.unfamousthomas.multiplayerappliances.managers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import us.unfamousthomas.multiplayerappliances.enums.PluginMessages;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DirectMessageManager {
    private static DirectMessageManager instance;
//    1st UUID = User whose message history we are checking
// 2nd UUID = User who last sent a message to 1st UUID
    private Map<UUID, UUID> lastReceivedMessage = new HashMap<>();
    public static DirectMessageManager getInstance() {
        if(instance == null) {
            instance = new DirectMessageManager();
        }
        return instance;
    }

    public void sendMessage(Player sender, String target, String message) {
        Player receiver = Bukkit.getPlayer(target);
        if(receiver == null) {
            sender.sendMessage(PluginMessages.NOT_FOUND.getRealMessage());
        } else {
            sendMessages(sender, receiver, message);
        }
    }
    public void sendResponse(UUID user, String message) {
        UUID userToSendTo = lastReceivedMessage.get(user);

        if(userToSendTo == null) {
            Bukkit.getPlayer(user).sendMessage(PluginMessages.MESSAGES_NOT_RECEIVED.getRealMessage());
        } else {
        Player playerToSendTo = Bukkit.getPlayer(userToSendTo);
        Player playerSentFrom = Bukkit.getPlayer(user);
        if(playerToSendTo == null) {
            Bukkit.getPlayer(user).sendMessage(PluginMessages.USER_LAST_RECEIVED_FROM_CANNOT_BE_FOUND.getRealMessage());
        } else {
            sendMessages(playerSentFrom, playerToSendTo, message);
        }
        }
    }

    private void sendMessages(Player sender, Player receiver, String msg) {
        if(sender.getUniqueId() == receiver.getUniqueId()) {
            sender.sendMessage(PluginMessages.YOURSELF_MSG.getRealMessage());
            return;
        }
        String senderName = sender.getName();
        String receiverName = receiver.getName();
        String message = PluginMessages.MESSAGE.getRealMessage().replace("%sender%", senderName).replace("%msg%", msg).replace("%receiver%", receiverName);
        sender.sendMessage(message);
        receiver.sendMessage(message);

        lastReceivedMessage.put(receiver.getUniqueId(), sender.getUniqueId());
    }
}
