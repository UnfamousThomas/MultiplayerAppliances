package us.unfamousthomas.multiplayerappliances.commands.claims;

import org.bukkit.Chunk;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import us.unfamousthomas.multiplayerappliances.MultiplayerAppliances;
import us.unfamousthomas.multiplayerappliances.enums.JokeMessage;
import us.unfamousthomas.multiplayerappliances.enums.PluginMessages;
import us.unfamousthomas.multiplayerappliances.managers.ChunkManager;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ClaimCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(PluginMessages.PLAYER_ONLY.getRealMessage());
            return true;
        }
        Player player = (Player) sender;

        ChunkManager chunkManager = MultiplayerAppliances.getPluginInstance().getChunkManager();
        Chunk chunk = player.getLocation().getChunk();
        String chunkId = chunkManager.generateChunkId(chunk);

        if(chunkManager.isChunk(chunkId)) {
            player.sendMessage("Claimed...");
            return true;
        }
        chunkManager.addChunk(chunk, player.getUniqueId());
        player.sendMessage("Chunk has been claimed, lol.");
        return true;
    }




}
