package unhappy.legendzrpg.plugin.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import unhappy.legendzrpg.plugin.Main;
import unhappy.legendzrpg.plugin.mongodb.DataManager;
import unhappy.legendzrpg.plugin.mongodb.Stat;

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
        Bukkit.broadcastMessage(player.getDisplayName());
        int amount = 0;
        if (this.data.getConfig().contains("players." + uuid + ".points"))
            amount = this.data.getConfig().getInt("player." + uuid + ".points");
        data.getConfig().set("players." + uuid + ".points", (amount + 1));
        data.saveConfig();
        Bukkit.broadcastMessage("praying Works");
    }
}
