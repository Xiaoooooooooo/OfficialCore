package unhappy.legendzrpg.plugin.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPreLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import unhappy.legendzrpg.plugin.Main;

import java.util.UUID;

public class LeaveListener implements Listener {
    private static Main plugin;

    public LeaveListener(Main plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        Main.getInstance().data.getConfig().set("players." + uuid + ".points", null);
        if (Main.getInstance().stat.getAmount(uuid) >= 1) {
            Main.getInstance().stat.savePoints(Main.getInstance().stat.getAmount(uuid),uuid, player);
        }
    }
}
