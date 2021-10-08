package unhappy.legendzrpg.plugin.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.metadata.MetadataValue;
import unhappy.legendzrpg.plugin.Main;
import unhappy.legendzrpg.plugin.utils.Utils;

import java.util.UUID;

public class EntityDeathListener implements Listener {
    private Main plugin;

    public EntityDeathListener(Main plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onMobKill(EntityDeathEvent event) {
        if (event.getEntity().getKiller() instanceof Player) {
            Player player = (Player) event.getEntity().getKiller();
            UUID uuid = player.getUniqueId();
            Main.getInstance().stat.changeAmount(uuid, 5);
            if (player.hasMetadata("Notify")) {
                return;
            }
            else {
                player.sendMessage(Utils.chat("&a+5"));
            }
        }
    }
}
