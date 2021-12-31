package us.unfamousthomas.multiplayerappliances.commands;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import us.unfamousthomas.multiplayerappliances.enums.PluginMessages;
import us.unfamousthomas.multiplayerappliances.managers.AdminSettingsManager;
import us.unfamousthomas.multiplayerappliances.utils.ItemStackBuilder;

public class AdminCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(PluginMessages.PLAYER_ONLY.getRealMessage());
        } else {
            Player player = (Player) sender;
            if(!(player.isOp())) {
                player.sendMessage(PluginMessages.OP_NEEDED.getRealMessage());
            } else {
                createGUI().show(player);
            }
        }
        return true;
    }

    private ChestGui createGUI() {
        AdminSettingsManager manager = AdminSettingsManager.getInstance();
        ChestGui gui = new ChestGui(1, ChatColor.translateAlternateColorCodes('&', "&cAdmin GUI"));

        StaticPane pane = new StaticPane(0,0,9,1);
        ItemStack maintenance;

        if(manager.getMaintenance()) {
            maintenance = new ItemStackBuilder(Material.RED_WOOL).withName(ChatColor.translateAlternateColorCodes('&', "&cDisable maintenance!")).buildStack();
        } else {
            maintenance = new ItemStackBuilder(Material.GREEN_WOOL).withName(ChatColor.translateAlternateColorCodes('&', "&cEnable maintenance!")).buildStack();
        }

        GuiItem maintenanceItem = new GuiItem(maintenance, event -> {
            AdminSettingsManager.getInstance().setMaintenance(!manager.getMaintenance());
            String state;
            if(manager.getMaintenance()) {
                state = "on";
            } else {
                state = "off";
            }
            String message = PluginMessages.MAINTENANCE_MODE_STATE.getRealMessage().replace("%state%", state);
            event.getWhoClicked().sendMessage(message);
            event.getWhoClicked().closeInventory();
        });


        ItemStack pvp;

        if(manager.getPvP()) {
            pvp = new ItemStackBuilder(Material.PAPER).withName(ChatColor.translateAlternateColorCodes('&', "&cDisable PvP!")).buildStack();
        } else {
            pvp = new ItemStackBuilder(Material.DIAMOND_SWORD).withName(ChatColor.translateAlternateColorCodes('&', "&cEnable PvP!")).buildStack();
        }

        GuiItem pvpItem = new GuiItem(pvp, event -> {
            AdminSettingsManager.getInstance().setPvP(!manager.getPvP());
            String state;
            if(manager.getPvP()) {
                state = "on";
            } else {
                state = "off";
            }
            String message = PluginMessages.PVP_STATE.getRealMessage().replace("%state%", state);
            event.getWhoClicked().sendMessage(message);
            event.getWhoClicked().closeInventory();
        });

        pane.addItem(pvpItem, 0,0);
        pane.addItem(maintenanceItem, 8,0);

        gui.addPane(pane);

        return gui;
    }




}
