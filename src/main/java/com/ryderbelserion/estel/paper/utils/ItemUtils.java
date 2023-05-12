package com.ryderbelserion.estel.paper.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * Description: Create a skull using url's/base64 or player names.
 */
public class ItemUtils {

    private final Material skull = Material.PLAYER_HEAD;

    /**
     * Blank constructor to create a new item-stack with PLAYER_HEAD.
     *
     * @return the blank PLAYER_HEAD
     */
    public ItemStack skull() {
        return new ItemStack(skull);
    }

    /**
     * Check if the item-stack is a skull or not.
     *
     * @param itemStack the itemstack to check
     * @return true if a skull otherwise false
     */
    public boolean isPlayerSkull(Material itemStack) {
        return itemStack != skull;
    }

    /**
     * Get the version of the server.
     *
     * @return the version number
     */
    private String getVersion() {
        String version = Bukkit.getServer().getClass().getPackage().getName();
        return version.substring(version.lastIndexOf('.') + 1);
    }

    /**
     * Check for the class path to find any method we need.
     *
     * @param name inventory.CraftMetaItem
     * @return The full classpath
     * @throws ClassNotFoundException if we cannot find the class
     */
    public Class<?> craftClass(String name) throws ClassNotFoundException {
        return Class.forName("org.bukkit.craftbukkit." + getVersion() + "." + name);
    }
}