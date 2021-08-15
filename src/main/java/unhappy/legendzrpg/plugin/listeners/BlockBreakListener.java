package unhappy.legendzrpg.plugin.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import unhappy.legendzrpg.plugin.Main;
import unhappy.legendzrpg.plugin.mongodb.Profile;
import unhappy.legendzrpg.plugin.mongodb.ProfileManager;
import unhappy.legendzrpg.plugin.utils.Utils;

public class BlockBreakListener implements Listener {
    private static Main plugin;
    private ProfileManager profileManager;
    public  BlockBreakListener(Main plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Player player = e.getPlayer();
        Profile profile = profileManager.getProfile(player.getUniqueId());
        profile.getData().getBlocksMined().increaseAmount(1);
        profile.getData().save();
        Bukkit.broadcastMessage("Saved " + player.getName() + " data!");
    }
}
