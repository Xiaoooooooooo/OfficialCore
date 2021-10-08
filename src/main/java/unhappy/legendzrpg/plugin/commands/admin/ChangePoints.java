package unhappy.legendzrpg.plugin.commands.admin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import unhappy.legendzrpg.plugin.Main;

public class ChangePoints implements CommandExecutor {

    private Main plugin;

    public ChangePoints(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("givept").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String amount, String[] strings) {
        if (sender.hasPermission("is.op")) {
            Player p = (Player) sender;
            Main.getInstance().stat.changeAmount(p.getUniqueId(), Integer.parseInt(strings[1]));
        }
        else {
        }
        return false;
    }
}
