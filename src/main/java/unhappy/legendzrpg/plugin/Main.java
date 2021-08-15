package unhappy.legendzrpg.plugin;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.Getter;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import unhappy.legendzrpg.plugin.listeners.*;
import unhappy.legendzrpg.plugin.mongodb.Profile;
import unhappy.legendzrpg.plugin.mongodb.ProfileManager;
import unhappy.legendzrpg.plugin.ui.TestUI;
import unhappy.legendzrpg.plugin.commands.Fly;
import unhappy.legendzrpg.plugin.commands.GUI;
import unhappy.legendzrpg.plugin.utils.Utils;

import java.util.Collections;

@Getter
public class Main extends JavaPlugin implements Listener {

    @Getter
    public static Main instance;

    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;
    private MongoCollection<Document> serverCollection;
    private ProfileManager profileManager;

    @Override
    public void onEnable() {
        instance = this;
        connect(); //Connecting to MongoDB
        saveDefaultConfig();
        //Commands
        new GUI(this);
        new Fly(this);
        //Listeners
        new PlayerDeathListener(this);
        new InventoryClickListener(this);
        new EntityDeathListener(this);
        new BlockBreakListener(this);
        new JoinListener(this);
        TestUI.initialize();

        Bukkit.broadcastMessage(Utils.chat("&bSuccessfully Reloaded!"));
    }

    @Override
    public void onDisable() {
    }

    public void connect() {
        MongoCredential mongoCredential = MongoCredential.createCredential("upoq4jhng7zsqotq1ryx", "bmnxn6c8zeatvnq", "WsVkzSfnNRGgVzvWtSfh".toCharArray());
        mongoClient = new MongoClient(new ServerAddress("bmnxn6c8zeatvnq-mongodb.services.clever-cloud.com", 27017), Collections.singletonList(mongoCredential));
        mongoDatabase = mongoClient.getDatabase("bmnxn6c8zeatvnq");
        serverCollection = mongoDatabase.getCollection("Server");
        Bukkit.getServer().broadcastMessage(Utils.chat("&aConnected to MongoDB"));

    }

    /*@EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        getProfileManager().handleProfileCreation(player.getUniqueId(), player.getName());
        Profile profile = getProfileManager().getProfile(player.getUniqueId());
        profile.getData().load();
        Bukkit.broadcastMessage("Loaded " + player.getName() + " data!");
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Profile profile = getProfileManager().getProfile(player.getUniqueId());
        profile.getData().getBlocksMined().increaseAmount(1);
        profile.getData().save();
        Bukkit.broadcastMessage("Saved " + player.getName() + " data!");
    }*/
}
