package us.unfamousthomas.multiplayerappliances.commands.claims;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import us.unfamousthomas.multiplayerappliances.MultiplayerAppliances;
import us.unfamousthomas.multiplayerappliances.enums.JokeMessage;
import us.unfamousthomas.multiplayerappliances.enums.PluginMessages;
import us.unfamousthomas.multiplayerappliances.managers.ChunkManager;
import us.unfamousthomas.multiplayerappliances.utils.ItemStackBuilder;

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

        //todo config option for this
        if(!player.getInventory().containsAtLeast(new ItemStackBuilder(Material.IRON_INGOT).buildStack(), 8)) {
            player.sendMessage("You do not have the necessary items (8 iron).");
            return true;
        }

        if(chunkManager.isChunk(chunkId)) {
            player.sendMessage("Claimed...");
            return true;
        }

        if(chunkManager.getChunkCount(player.getUniqueId()) >= 64) {
            player.sendMessage("Max limit of chunks reached, lol.");
            return true;
        }

        //todo same as above lol
        player.getInventory().removeItem(new ItemStackBuilder(Material.IRON_INGOT).withAmount(8).buildStack());


        player.updateInventory();

        chunkManager.addChunk(chunk, player.getUniqueId());
        chunkManager.addChunksClaimedCount(player.getUniqueId(), 1);
        player.sendMessage("Chunk has been claimed, lol.");
        return true;
    }




}
