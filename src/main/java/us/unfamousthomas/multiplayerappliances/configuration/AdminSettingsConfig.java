package us.unfamousthomas.multiplayerappliances.configuration;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import us.unfamousthomas.multiplayerappliances.lang.FinalMessages;

import java.io.File;
import java.io.IOException;

public class AdminSettingsConfig {

    private static File file;
    private static FileConfiguration customFile;

    //Finds or generates the custom file
    public static void setup() {
        file = new File(Bukkit.getServer().getPluginManager().getPlugin(FinalMessages.pluginName).getDataFolder(), FinalMessages.adminSettingsFile);

        if(!(file.exists())) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&4SOMETHING IS WRONG (with creating)!!"));
            }
        }
        customFile = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration getFile() {
        return customFile;
    }

    public static void saveFile() {
        try {
            customFile.save(file);
        } catch (IOException e) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&4SOMETHING IS WRONG (with saving)!!"));
        }
    }

    public static void reload() {
        customFile = YamlConfiguration.loadConfiguration(file);
    }
}
