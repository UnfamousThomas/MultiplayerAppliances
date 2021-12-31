package us.unfamousthomas.multiplayerappliances.objects;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import us.unfamousthomas.multiplayerappliances.MultiplayerAppliances;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class TeleportRequest {

    UUID wantsToTeleport;
    UUID teleportTo;
    Long validUntil = System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(5);

    public TeleportRequest(UUID target, UUID teleporter) {
        this.wantsToTeleport = teleporter;
        this.teleportTo = target;
    }

    public void confirmTeleport() {
        if(isExpired()) {
            //todo send message cant tp bc expired
            removeRequest();
            return;
        }

        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(teleportTo);
        if(!offlinePlayer.isOnline()) {
            //todo send message cant tp bc offline
            removeRequest();
        }

        Player playerTarget = Bukkit.getPlayer(teleportTo);
        Player user = Bukkit.getPlayer(wantsToTeleport);

        user.teleport(playerTarget.getLocation());
        user.sendMessage("Yay! You have teleported, I guess...");
        removeRequest();
    }

    public UUID getTeleportTo() {
        return teleportTo;
    }

    public UUID getWantsToTeleport() {
        return wantsToTeleport;
    }

    private void removeRequest() {
        MultiplayerAppliances.getPluginInstance().getTeleportManager().removeRequest(this);
    }

    public boolean isExpired() {
        return validUntil < System.currentTimeMillis();
    }
}
