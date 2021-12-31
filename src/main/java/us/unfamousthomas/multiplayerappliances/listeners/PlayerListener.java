package us.unfamousthomas.multiplayerappliances.listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import us.unfamousthomas.multiplayerappliances.managers.AdminSettingsManager;

public class PlayerListener implements Listener {
    @EventHandler
    public void onPlayerDamageEvent(EntityDamageByEntityEvent event) {
        if (event.getDamager().getType() == EntityType.PLAYER && event.getEntity().getType() == EntityType.PLAYER) {
            if (!AdminSettingsManager.getInstance().getPvP()) {
                event.setCancelled(true);
            }

        }
    }
}
