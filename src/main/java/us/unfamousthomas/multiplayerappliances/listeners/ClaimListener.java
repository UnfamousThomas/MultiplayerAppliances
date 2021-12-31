package us.unfamousthomas.multiplayerappliances.listeners;

import org.bukkit.Chunk;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import us.unfamousthomas.multiplayerappliances.MultiplayerAppliances;
import us.unfamousthomas.multiplayerappliances.managers.ChunkManager;
import us.unfamousthomas.multiplayerappliances.objects.ClaimedChunk;

public class ClaimListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if(e.getClickedBlock() == null) {
            return;
        }
        ChunkManager chunkManager = MultiplayerAppliances.getPluginInstance().getChunkManager();
        String id = chunkManager.generateChunkId(e.getClickedBlock().getChunk());

        if(chunkManager.isChunk(id)) {
            Player player = e.getPlayer();
            ClaimedChunk chunk = chunkManager.getChunk(id);
            if(!(player.getUniqueId().equals(chunk.getOwner())) && !(chunk.getMembers().contains(player.getUniqueId()))) {
                e.setCancelled(true);
                player.sendMessage("You cannot build here!");
                //You cannot build here
                //TODO: Admin bypass
            } else {
                //You can build here so...
            }
         }
    }
}
