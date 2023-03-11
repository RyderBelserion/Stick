package us.crazycrew.crazycore.paper.items;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Description: Modify the item nbt.
 */
public class ItemNbt {

    private final JavaPlugin plugin;

    /**
     * A constructor to build a JavaPlugin variable.
     *
     * @param plugin the java plugin instance
     */
    public ItemNbt(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    /**
     * Set a persistent data type string to the item-meta's container.
     *
     * @param itemStack the itemstack to add to
     * @param key the identifier for the NamespacedKey
     * @param value to store in the PersistentDataContainer
     * @return the completed item-stack
     */
    public ItemStack setString(ItemStack itemStack, String key, String value) {
        ItemMeta meta = itemStack.getItemMeta();

        if (meta == null) return null;

        meta.getPersistentDataContainer().set(new NamespacedKey(this.plugin, key), PersistentDataType.STRING, value);
        itemStack.setItemMeta(meta);

        return itemStack;
    }

    /**
     * Retrieve the persistent data container values.
     *
     * @param itemStack the itemstack to check
     * @param key the identifier to check
     * @return the value
     */
    public String getString(ItemStack itemStack, String key) {
        ItemMeta meta = itemStack.getItemMeta();

        if (meta == null) return null;

        return meta.getPersistentDataContainer().get(new NamespacedKey(this.plugin, key), PersistentDataType.STRING);
    }

    /**
     * Set a persistent data type boolean to the item-meta's container.
     *
     * @param itemStack the itemstack to add to
     * @param key the identifier for the NamespacedKey
     * @param value to store in the PersistentDataContainer
     * @return the completed item-stack
     */
    public ItemStack setBoolean(ItemStack itemStack, String key, boolean value) {
        ItemMeta meta = itemStack.getItemMeta();

        if (meta == null) return null;

        meta.getPersistentDataContainer().set(new NamespacedKey(this.plugin, key), PersistentDataType.BYTE, value ? (byte) 1 : 0);
        itemStack.setItemMeta(meta);

        return itemStack;
    }

    /**
     * Remove a persistent data tag.
     *
     * @param itemStack the itemstack to remove from
     * @param key the identifier for the NamespacedKey
     * @return the completed item-stack
     */
    public ItemStack removeTag(ItemStack itemStack, String key) {
        ItemMeta meta = itemStack.getItemMeta();

        if (meta == null) return null;

        meta.getPersistentDataContainer().remove(new NamespacedKey(this.plugin, key));
        itemStack.setItemMeta(meta);

        return itemStack;
    }
}