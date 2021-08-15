package unhappy.legendzrpg.plugin.mongodb;

import lombok.Getter;
import lombok.Setter;
import unhappy.legendzrpg.plugin.Main;

import java.util.UUID;

@Getter
@Setter
public class Profile {

    private Main plugin = Main.getInstance();

    private PlayerData data;
    private UUID UUID;
    private String playerName;

    public Profile(UUID uuid, String name) {
        this.UUID = uuid;
        this.playerName = name;
        this.data = new PlayerData(uuid, name);
    }
}
