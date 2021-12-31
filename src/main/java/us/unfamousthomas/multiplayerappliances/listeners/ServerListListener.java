package us.unfamousthomas.multiplayerappliances.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.server.ServerListPingEvent;
import us.unfamousthomas.multiplayerappliances.enums.PluginMessages;
import us.unfamousthomas.multiplayerappliances.managers.AdminSettingsManager;

public class ServerListListener implements Listener {
    @EventHandler
    public void onServerListPingEvent(final ServerListPingEvent event) {
        if(AdminSettingsManager.getInstance().getMaintenance()) {
            String motd = PluginMessages.MAINTENANCE_MOTD.getRealMessage();
            event.setMotd(motd);
            event.setMaxPlayers(1);
        } else {
            event.setMotd(AdminSettingsManager.getInstance().getMOTD());
            event.setMaxPlayers(AdminSettingsManager.getInstance().getCount());
        }
    }

    @EventHandler
    public void onJoinEvent(final PlayerJoinEvent event) {
        if(AdminSettingsManager.getInstance().getMaintenance() && !event.getPlayer().isOp()) {
            String kickMsg = PluginMessages.MAINTENANCE_KICK.getRealMessage();
            event.getPlayer().kickPlayer(kickMsg);
        }
    }
}
