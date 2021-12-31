package us.unfamousthomas.multiplayerappliances;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import us.unfamousthomas.multiplayerappliances.commands.*;
import us.unfamousthomas.multiplayerappliances.commands.claims.AddMemberCommand;
import us.unfamousthomas.multiplayerappliances.commands.claims.ChunkInfoCommand;
import us.unfamousthomas.multiplayerappliances.commands.claims.ClaimCommand;
import us.unfamousthomas.multiplayerappliances.commands.claims.RemoveMemberCommand;
import us.unfamousthomas.multiplayerappliances.configuration.AdminSettingsConfig;
import us.unfamousthomas.multiplayerappliances.enums.PluginMessages;
import us.unfamousthomas.multiplayerappliances.listeners.ClaimListener;
import us.unfamousthomas.multiplayerappliances.listeners.PlayerListener;
import us.unfamousthomas.multiplayerappliances.listeners.ServerListListener;
import us.unfamousthomas.multiplayerappliances.managers.ChunkManager;

public final class MultiplayerAppliances extends JavaPlugin {
    private static MultiplayerAppliances pluginInstance;
    private static ChunkManager chunkManager;

    @Override
    public void onEnable() {
        pluginInstance = this;
        chunkManager = new ChunkManager(pluginInstance);

        setupAdminSettings();

        this.getCommand("message").setExecutor(new MessageCommand());
        this.getCommand("reply").setExecutor(new ReplyCommand());
        this.getCommand("joke").setExecutor(new JokeCommand());
        this.getCommand("admin").setExecutor(new AdminCommand());
        this.getCommand("claim").setExecutor(new ClaimCommand());
        this.getCommand("unclaim").setExecutor(new UnClaimCommand());
        this.getCommand("addmember").setExecutor(new AddMemberCommand());
        this.getCommand("removemember").setExecutor(new RemoveMemberCommand());
        this.getCommand("config-reload").setExecutor(new ConfigReloadCommand());
        this.getCommand("chunkinfo").setExecutor(new ChunkInfoCommand());

        getServer().getPluginManager().registerEvents(new ServerListListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
        getServer().getPluginManager().registerEvents(new ClaimListener(), this);


        consoleMsg(PluginMessages.START.getRealMessage());
    }

    @Override
    public void onDisable() {
        consoleMsg(PluginMessages.END.getRealMessage());
    }

    private void consoleMsg(String message) {
        Bukkit.getConsoleSender().sendMessage(message);
    }

    private void setupAdminSettings() {
        saveDefaultConfig();
        AdminSettingsConfig.setup();
        FileConfiguration fileConfiguration = AdminSettingsConfig.getFile();
        fileConfiguration.addDefault("Maintenance", false);
        fileConfiguration.addDefault("PvP", false);
        fileConfiguration.addDefault("MOTD", Bukkit.getServer().getMotd());
        fileConfiguration.addDefault("Player Count", Bukkit.getServer().getMaxPlayers());

        fileConfiguration.options().copyDefaults(true);
        AdminSettingsConfig.saveFile();
    }

    public static MultiplayerAppliances getPluginInstance() {
        return pluginInstance;
    }

    public ChunkManager getChunkManager() {
        return chunkManager;
    }
}


