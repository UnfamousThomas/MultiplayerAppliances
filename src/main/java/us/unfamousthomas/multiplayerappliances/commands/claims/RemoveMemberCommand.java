package us.unfamousthomas.multiplayerappliances.commands.claims;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import us.unfamousthomas.multiplayerappliances.MultiplayerAppliances;
import us.unfamousthomas.multiplayerappliances.enums.PluginMessages;
import us.unfamousthomas.multiplayerappliances.managers.ChunkManager;

import java.util.UUID;

public class RemoveMemberCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(PluginMessages.PLAYER_ONLY.getRealMessage());
            return true;
        }

        Player player = (Player) sender;

        if(args.length != 1) {
            player.sendMessage(PluginMessages.INVALID_USAGE.getRealMessage());
            return true;
        }

        String playerName = args[0];
        Player memberPlayer = Bukkit.getOfflinePlayer(playerName).getPlayer();

        if(memberPlayer == null) {
            player.sendMessage("Could not find that player. Note: This might can be caused by offline players.");
            return true;
        }

        ChunkManager chunkManager = MultiplayerAppliances.getPluginInstance().getChunkManager();
        Chunk chunk = player.getLocation().getChunk();
        String chunkId = chunkManager.generateChunkId(chunk);

        if(!chunkManager.isChunk(chunkId) || chunkManager.getChunk(chunkId).getOwner() != player.getUniqueId()) {
            player.sendMessage("This chunk is not claimed OR is not yours.");
            return true;
        }

        chunkManager.getChunk(chunkId).removeMember(memberPlayer.getUniqueId());

        player.sendMessage(playerName + " has been removed from the chunk as a member.");
        return true;
    }




}
