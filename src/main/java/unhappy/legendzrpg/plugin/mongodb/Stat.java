package unhappy.legendzrpg.plugin.mongodb;

import org.bson.Document;
import unhappy.legendzrpg.plugin.Main;

import java.util.UUID;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.*;

public class Stat {

    private Main plugin;
    public DataManager data;

    public void changeAmount(UUID uuid, int i) {
        data.getConfig().set("players." + uuid.toString() + ".points", (getAmount(uuid) + i));
        data.saveConfig();
    }
    public void setAmount(UUID uuid, int i) {
        data.getConfig().set("players." + uuid.toString() + ".points", (i));
        data.saveConfig();
    }
    public int getAmount(UUID uuid) {
        int amount = 0;
        if (this.data.getConfig().contains("players." + uuid.toString() + ".points"))
            amount = this.data.getConfig().getInt("player." + uuid.toString() + ".points");
        return amount;
    }

    public int loadPoints(String uuid) {
        Document document = plugin.getCollection().find(eq("uuid", uuid)).first();
        int i = (int) document.get("points");
        return i;
    }

    public void savePoints(int amount, String uuid) {
        plugin.getCollection().updateOne(
                eq("uuid", uuid),
                combine(set("points", amount), currentDate("lastModified")));
    }
}
