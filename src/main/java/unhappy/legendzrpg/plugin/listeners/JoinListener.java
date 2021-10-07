package unhappy.legendzrpg.plugin.listeners;

import com.mongodb.client.FindIterable;
import lombok.Getter;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import unhappy.legendzrpg.plugin.Main;
import unhappy.legendzrpg.plugin.utils.Utils;

import java.util.UUID;
import java.util.function.Consumer;

import static com.mongodb.client.model.Filters.eq;

@Getter
public class JoinListener implements Listener {
    @Getter
    private static Main plugin;

    public JoinListener(Main plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        Main.getInstance().stat.setAmount(uuid,Main.getInstance().stat.loadPoints(uuid));
        String joinMessage = Utils.chat("&a&lJOIN &7[&a+&7] " + " &7" + player.getName());
        if (!player.hasPlayedBefore()) {
            joinMessage = Utils.chat("&a&lWELCOME&r &7[&a+&7] " + " &7" + player.getName());
        }
        event.setJoinMessage(joinMessage);
    }
}







