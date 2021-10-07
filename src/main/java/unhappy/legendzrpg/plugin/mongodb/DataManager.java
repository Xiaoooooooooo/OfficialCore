package unhappy.legendzrpg.plugin.mongodb;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import unhappy.legendzrpg.plugin.Main;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

public class DataManager {
    private Main plugin;
    private FileConfiguration dataConfig = null;
    private File configfile = null;

    public DataManager(Main plugin) {
        this.plugin = plugin;
        saveDefaultConfig();
    }

    public void reloadConfig() {
        if (this.configfile == null)
            this.configfile = new File(this.plugin.getDataFolder(), "data.yml");
        this.dataConfig = YamlConfiguration.loadConfiguration(this.configfile);
        InputStream defaultStream = this.plugin.getResource("data.yml");
        if (defaultStream != null) {
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            this.dataConfig.setDefaults(defaultConfig);
        }
    }

    public FileConfiguration getConfig() {
        if (this.dataConfig == null)
            reloadConfig();
        return this.dataConfig;
    }

    public void saveConfig() {
        if (this.dataConfig == null || this.configfile == null)
            return;
        try {
            this.getConfig().save(this.configfile);
        } catch (IOException e) {
            plugin.getLogger().log(Level.SEVERE, "Could not save config to " + this.configfile, e);
        }
    }

    public void saveDefaultConfig() {
        if (this.configfile == null)
            this.configfile = new File(this.plugin.getDataFolder(), "data.yml");
        if (!this.configfile.exists()) {
            this.plugin.saveResource("data.yml", false);
        }
    }
}




































