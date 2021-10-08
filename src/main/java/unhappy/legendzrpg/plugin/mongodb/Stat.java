package unhappy.legendzrpg.plugin.mongodb;

import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import unhappy.legendzrpg.plugin.Main;

import java.util.UUID;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.*;

public class Stat {

    private final Main plugin;

    public Stat(Main plugin) {
        this.plugin = plugin;
    }

    public void changeAmount(UUID uuid, int i) {
        Main.getInstance().data.getConfig().set("players." + uuid + ".points", (getAmount(uuid) + i));
        Main.getInstance().data.saveConfig();
    }
    public void setAmount(UUID uuid, int i) {
        Main.getInstance().data.getConfig().set("players." + uuid + ".points", (i));
        Main.getInstance().data.saveConfig();
    }
    public int getAmount(UUID uuid) {
        int amount = 0;
        if (Main.getInstance().data.getConfig().contains("players." + uuid + ".points")) {
            amount = Main.getInstance().data.getConfig().getInt("players." + uuid + ".points");
        }
        return amount;
    }

    public int loadPoints(UUID uuid) {
        Document document = plugin.getCollection().find(eq("uuid", uuid)).first();
        return (int) document.get("points");
    }

    public void savePoints(int amount, UUID uuid, OfflinePlayer player) {
        if (Main.getInstance().getCollection().find(eq("uuid", uuid)).first() == null) {
            Document document = new Document("uuid", uuid)
                    .append("name", player.getName().toLowerCase())
                    .append("realName", player.getName())
                    .append("points", amount);
            Main.getInstance().getCollection().insertOne(document);
            Main.getInstance().data.getConfig().set("players." + uuid, null);
        }
        else {
            Main.getInstance().getCollection().updateOne(
                    eq("uuid", uuid),
                    combine(set("points", amount), currentDate("lastModified")));
            Main.getInstance().data.getConfig().set("players." + uuid, null);
        }
    }
}
