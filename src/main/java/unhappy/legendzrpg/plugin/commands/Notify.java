package unhappy.legendzrpg.plugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import unhappy.legendzrpg.plugin.Main;
import unhappy.legendzrpg.plugin.utils.Utils;

public class Notify implements CommandExecutor {

    private Main plugin;

    public Notify(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("notify").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if (p.hasMetadata("Notify")) {
            p.removeMetadata("Notify",plugin);
            p.sendMessage(Utils.chat("&6Notification: &aOn"));
        }
        else {
            p.setMetadata("Notify", new FixedMetadataValue(plugin, 1));
            p.sendMessage(Utils.chat("&6Notification: &cOff"));
        }
        return false;
    }
}
