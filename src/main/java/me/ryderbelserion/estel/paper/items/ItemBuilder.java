package me.ryderbelserion.estel.paper.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * Description: The parent item builder that extends BaseItemBuilder
 */
public class ItemBuilder extends BaseItemBuilder<ItemBuilder> {

    /**
     * Empty item builder constructor.
     */
    public ItemBuilder() {
        super();
    }

    /**
     * Blank constructor to create an empty item-stack.
     *
     * @param itemStack the blank item-stack
     */
    public ItemBuilder(ItemStack itemStack) {
        super(itemStack);
    }

    /**
     * The main method to create {@link ItemBuilder}
     * Specifically modified for configurations, so it incorporates all sub builders like FireworkBuilder.
     *
     * @param itemStack The {@link ItemStack} that you wish to edit
     * @return A new {@link ItemBuilder}
     */
    public static ItemBuilder setStack(ItemStack itemStack) {
        return new ItemBuilder(itemStack);
    }

    /**
     * Alternative method to create a {@link ItemBuilder}
     * Specifically modified for configurations, so it incorporates all sub builders like FireworkBuilder.
     *
     * @param material The {@link Material} that you want to create an item from
     * @return A new {@link ItemBuilder}
     */
    public static ItemBuilder setMaterial(Material material) {
        return new ItemBuilder(new ItemStack(material));
    }
}