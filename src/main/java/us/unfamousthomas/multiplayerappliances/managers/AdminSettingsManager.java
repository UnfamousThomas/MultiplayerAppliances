package us.unfamousthomas.multiplayerappliances.managers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import us.unfamousthomas.multiplayerappliances.configuration.AdminSettingsConfig;
import us.unfamousthomas.multiplayerappliances.enums.PluginMessages;

public class AdminSettingsManager {
    private static AdminSettingsManager instance;

    AdminSettingsManager() {
        load();
    }

    private String motd;
    private boolean maintenanceMode;
    private int count;
    private boolean pvp;

    public void setPvP(boolean pvp) {
        FileConfiguration fileConfiguration = AdminSettingsConfig.getFile();
        fileConfiguration.set("PvP", pvp);
        AdminSettingsConfig.saveFile();

        for(World world : Bukkit.getWorlds()) {
            if(world.getPVP() != pvp) {
                world.setPVP(pvp);
            }
        }

        Bukkit.getServer().getOnlinePlayers().forEach(player -> {
            String state;
            if(pvp) {
                state = "on";
            } else {
                state = "off";
            }
            String msg = PluginMessages.PVP_WARNING.getRealMessage().replace("%state%", state);

            player.sendMessage(msg);
        });

        this.pvp = pvp;
    }

    public void setMaintenance(boolean maintenance) {
        FileConfiguration fileConfiguration = AdminSettingsConfig.getFile();
        fileConfiguration.set("Maintenance", maintenance);
        AdminSettingsConfig.saveFile();
        if (maintenance) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (!player.isOp()) {
                    player.kickPlayer( PluginMessages.MAINTENANCE_KICK.getRealMessage());
                } else {
                    String msg = PluginMessages.NOT_KICKED_MAINTENANCE.getRealMessage();
                    player.sendMessage(msg);
                }
            }
        }

        this.maintenanceMode = maintenance;
    }


    public void setPlayerCount(int count) {
        FileConfiguration fileConfiguration = AdminSettingsConfig.getFile();
        fileConfiguration.set("Player Count", count);
        AdminSettingsConfig.saveFile();
        this.count = count;
    }

    public void setMotd(String msg) {
        FileConfiguration fileConfiguration = AdminSettingsConfig.getFile();
        fileConfiguration.set("MOTD", msg);
        AdminSettingsConfig.saveFile();
        this.motd = msg;
    }

    public static AdminSettingsManager getInstance() {
        if(instance == null) {
            instance = new AdminSettingsManager();
        }
        return instance;
    }

    public void load() {
        try {
            FileConfiguration fileConfiguration = AdminSettingsConfig.getFile();
            this.pvp = (Boolean) fileConfiguration.get("PvP");
            this.maintenanceMode = (Boolean) fileConfiguration.get("Maintenance");
            this.motd = (String) fileConfiguration.get("MOTD");
            this.count = (Integer) fileConfiguration.get("Player Count");
        } catch (Exception ex) {
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Something went wrong loadind data..."));
            ex.printStackTrace();
        }
    }

    public boolean getPvP() {
        return this.pvp;
    }

    public boolean getMaintenance() {
        return this.maintenanceMode;
    }

    public String getMOTD() {
        return this.motd;
    }

    public int getCount() {
        return this.count;
    }



}
