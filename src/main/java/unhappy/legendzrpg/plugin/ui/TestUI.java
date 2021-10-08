package unhappy.legendzrpg.plugin.ui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import unhappy.legendzrpg.plugin.Main;
import unhappy.legendzrpg.plugin.utils.Utils;

import java.util.Locale;
import java.util.UUID;

public class TestUI {

    public static Inventory inv;
    public static String inventory_name;
    public static int inv_rows = 4*9;
    public static void initialize() {
        inventory_name = Utils.chat("&bTest GUI");
        inv = Bukkit.createInventory(null, inv_rows);
    }
    public static Inventory GUI (Player p) {
        Inventory toReturn = Bukkit.createInventory(null, inv_rows, inventory_name);
        for (int i = 1; i < 37; i++) {
            Utils.createItemByte(inv,"STAINED_GLASS_PANE",14, 1, i,"&c&lNot Available");
        }
        Utils.createItem(inv,"DIAMOND",1,1, "&b1x Diamond", "&aBuy: 1000 Points", "&aSell: 600 Points");
        Utils.createItem(inv,"GOLD_INGOT",1,2, "&e1x Gold", "&aBuy: 500 Points", "&aSell: 300 Points");
        Utils.createItem(inv,"COOKED_BEEF",1,10, "&61x Steak", "&aBuy: 5 Points", "&aSell: 3 Points");
        Utils.createItem(inv, "PAPER", 1, 32, "&6Instructions", "&aLeft click to Buy", "&aRight click to Sell");
        toReturn.setContents(inv.getContents());
        return toReturn;
    }
    public static void clicked(Player p, int slot, ItemStack clicked, Inventory inv, Boolean buy) {
        UUID uuid = p.getUniqueId();
        if (clicked.getItemMeta().getDisplayName().toLowerCase().contains("diamond")) {
            if (buy) {
                if (Main.getInstance().stat.getAmount(uuid) >= 1000) {
                    Main.getInstance().stat.changeAmount(uuid, -1000);
                    p.getInventory().addItem(new ItemStack(Material.DIAMOND, 1));
                    p.sendMessage(Utils.chat("&aPoints: " + Main.getInstance().stat.getAmount(uuid)));
                }
                else {
                    p.sendMessage(Utils.chat("&cNot Enough Points"));
                }
            }
            else {
                if (p.getInventory().contains(Material.DIAMOND, 1)) {
                    p.getInventory().removeItem(new ItemStack(Material.DIAMOND,1));
                    Main.getInstance().stat.changeAmount(uuid, 600);
                    p.sendMessage(Utils.chat("&aPoints: " + Main.getInstance().stat.getAmount(uuid)));
                }
            }
        }
        else if (clicked.getItemMeta().getDisplayName().toLowerCase().contains("gold")) {
            if (buy) {
                if (Main.getInstance().stat.getAmount(uuid) >= 500) {
                    Main.getInstance().stat.changeAmount(uuid, -500);
                    p.getInventory().addItem(new ItemStack(Material.GOLD_INGOT, 1));
                    p.sendMessage(Utils.chat("&aPoints: " + Main.getInstance().stat.getAmount(uuid)));
                }
                else {
                    p.sendMessage(Utils.chat("&cNot Enough Points"));
                }
            }
            else {
                if (p.getInventory().contains(Material.GOLD_INGOT, 1)) {
                    p.getInventory().removeItem(new ItemStack(Material.GOLD_INGOT,1));
                    Main.getInstance().stat.changeAmount(uuid, 300);
                    p.sendMessage(Utils.chat("&aPoints: " + Main.getInstance().stat.getAmount(uuid)));
                }
            }
        }
        else if (clicked.getItemMeta().getDisplayName().toLowerCase().contains("steak")) {
            if (buy) {
                if (Main.getInstance().stat.getAmount(uuid) >= 5) {
                    Main.getInstance().stat.changeAmount(uuid, -5);
                    p.getInventory().addItem(new ItemStack(Material.COOKED_BEEF, 1));
                    p.sendMessage(Utils.chat("&aPoints: " + Main.getInstance().stat.getAmount(uuid)));
                }
                else {
                    p.sendMessage(Utils.chat("&cNot Enough Points"));
                }
            }
            else {
                if (p.getInventory().contains(Material.COOKED_BEEF, 1)) {
                    p.getInventory().removeItem(new ItemStack(Material.COOKED_BEEF,1));
                    Main.getInstance().stat.changeAmount(uuid, 3);
                    p.sendMessage(Utils.chat("&aPoints: " + Main.getInstance().stat.getAmount(uuid)));
                }
            }
        }
    }
}

















