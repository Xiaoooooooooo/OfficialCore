package unhappy.legendzrpg.plugin.NPC;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import unhappy.legendzrpg.plugin.Main;
import unhappy.legendzrpg.plugin.utils.Utils;

import java.util.ArrayList;
import java.util.Objects;

public class Shop implements Listener {

    public static ArrayList<ItemStack> weaponItems = new ArrayList<>();
    public static ArrayList<ItemStack> shopWeapons = new ArrayList<>();
    public static ArrayList<ItemStack> armorItems = new ArrayList<>();
    public static ArrayList<ItemStack> shopArmor = new ArrayList<>();
    private static int addVal = -10;

}