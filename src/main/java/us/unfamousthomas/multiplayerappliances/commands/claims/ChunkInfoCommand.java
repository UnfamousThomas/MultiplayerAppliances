package us.unfamousthomas.multiplayerappliances.commands.claims;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import us.unfamousthomas.multiplayerappliances.MultiplayerAppliances;
import us.unfamousthomas.multiplayerappliances.enums.PluginMessages;
import us.unfamousthomas.multiplayerappliances.managers.ChunkManager;
import us.unfamousthomas.multiplayerappliances.objects.ClaimedChunk;

public class ChunkInfoCommand implements CommandExecutor {
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
        boolean claimed = false;
        ClaimedChunk claimedChunk = null;
        if(chunkManager.isChunk(chunkId)) {
            claimed = true;
            claimedChunk = chunkManager.getChunk(chunkId);
        }
        StringBuilder stringBuilder = new StringBuilder()
                .append(translate("&cChunk Info:")).append("\n");
        stringBuilder.append(translate("&4ID: ")).append(chunkId).append("\n");
        if(claimed) {
            stringBuilder.append(translate("&bThis chunk is claimed.\n"));
        } else {
            stringBuilder.append(translate("&cThis chunk is not claimed.\n"));
        }

        if(claimed && claimedChunk != null) {
            String owner = Bukkit.getOfflinePlayer(claimedChunk.getOwner()).getName();
            stringBuilder.append(translate("&bOwner: ")).append(owner);
            stringBuilder.append(translate("\n&bMembers:\n"));
            claimedChunk.getMembers().forEach(member -> {
                stringBuilder.append(translate("&c-"));
                stringBuilder.append(Bukkit.getOfflinePlayer(member).getName());
                stringBuilder.append("\n");
            });
        }
        player.sendMessage(stringBuilder.toString());
        return true;
    }


public String translate(String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
}

}
