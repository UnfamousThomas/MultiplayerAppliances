package us.unfamousthomas.multiplayerappliances.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import us.unfamousthomas.multiplayerappliances.enums.JokeMessage;
import us.unfamousthomas.multiplayerappliances.enums.PluginMessages;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class JokeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(PluginMessages.PLAYER_ONLY.getRealMessage());
        } else {
            if(args.length != 0) {
                sender.sendMessage(PluginMessages.INVALID_USAGE.getRealMessage());
                return true;
            }
            sender.sendMessage(getRandomJokeMessage());
        }
        return true;
    }


    private String getRandomJoke() {
        final List<JokeMessage> messageList = Collections.unmodifiableList(Arrays.asList(JokeMessage.values()));
        final int listSize = messageList.size();
        final Random randomGenerator = new Random();
        return messageList.get(randomGenerator.nextInt(listSize)).getColorMessage();
    }

    private String getRandomJokeMessage() {
        return PluginMessages.RANDOM_JOKE.getRealMessage().replace("%joke%", getRandomJoke());
    }

}
