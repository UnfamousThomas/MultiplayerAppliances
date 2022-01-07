package us.unfamousthomas.multiplayerappliances.commands.claims;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import us.unfamousthomas.multiplayerappliances.MultiplayerAppliances;
import us.unfamousthomas.multiplayerappliances.enums.PluginMessages;
import us.unfamousthomas.multiplayerappliances.managers.ChunkManager;
import us.unfamousthomas.multiplayerappliances.objects.ClaimedChunk;

import java.util.List;

public class AddMemberAllCommand implements CommandExecutor {
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
            Player memberPlayer = Bukkit.getPlayerExact(playerName);

            if(memberPlayer == null) {
                player.sendMessage("Could not find that player.");
                return true;
            }

            ChunkManager chunkManager = MultiplayerAppliances.getPluginInstance().getChunkManager();

            List<ClaimedChunk> chunks = chunkManager.getAllClaimedChunks(player.getUniqueId());

            if(chunks.size() == 0) {
                player.sendMessage("You own 0 chunks.");
                return true;
            }

            chunks.forEach(chunk -> {
                if(!chunk.getMembers().contains(memberPlayer.getUniqueId())) {
                    chunk.removeMember(memberPlayer.getUniqueId());
                }
            });
            player.sendMessage(playerName + " has been added to all chunks as a member.");
            return true;
        }




}
