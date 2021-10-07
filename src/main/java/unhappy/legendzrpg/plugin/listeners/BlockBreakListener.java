package unhappy.legendzrpg.plugin.listeners;

import jdk.jshell.execution.Util;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import unhappy.legendzrpg.plugin.Main;
import unhappy.legendzrpg.plugin.mongodb.DataManager;
import unhappy.legendzrpg.plugin.mongodb.Stat;
import unhappy.legendzrpg.plugin.utils.Utils;

import javax.xml.crypto.Data;
import java.util.UUID;

public class BlockBreakListener implements Listener {
    private Main plugin;
    public Stat stat;
    public DataManager data;

    public BlockBreakListener(Main plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        Main.getInstance().stat.changeAmount(uuid,1);
        player.sendMessage(Utils.chat("&a+1"));
    }
}
