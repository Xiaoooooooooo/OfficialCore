package unhappy.legendzrpg.plugin.listeners;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import lombok.Getter;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import unhappy.legendzrpg.plugin.Main;
import unhappy.legendzrpg.plugin.mongodb.Profile;
import unhappy.legendzrpg.plugin.mongodb.ProfileManager;
import unhappy.legendzrpg.plugin.utils.Utils;

@Getter
public class JoinListener implements Listener {
    private static Main plugin;
    private ProfileManager profileManager;

    public JoinListener(Main plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Document Document = new Document();
        String joinMessage = Utils.chat("&a&lJOIN &7[&a+&7] " + " &7" + player.getName());
        if (!player.hasPlayedBefore()) {
            joinMessage = Utils.chat("&a&lWELCOME&r &7[&a+&7] " + " &7" + player.getName());
        }
        event.setJoinMessage(joinMessage);
        profileManager.handleProfileCreation(player.getUniqueId(), player.getName());
        Profile profile = getProfileManager().getProfile(player.getUniqueId());
        profile.getData().load();
        Bukkit.broadcastMessage("Loaded " + player.getName() + " data!");
    }

}







