package com.ryderbelserion.stick.paper.items;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Banner;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.*;
import org.bukkit.inventory.meta.trim.ArmorTrim;
import org.bukkit.inventory.meta.trim.TrimMaterial;
import org.bukkit.inventory.meta.trim.TrimPattern;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;
import org.bukkit.util.Consumer;
import com.ryderbelserion.stick.paper.utils.ItemUtils;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Description: A base item builder to build everything we need.
 */
@SuppressWarnings("unchecked")
public class BaseItemBuilder<Base extends BaseItemBuilder<Base>> {

    private final ItemUtils SKULL_CHECKER = new ItemUtils();

    private final GsonComponentSerializer gson = GsonComponentSerializer.gson();

    // Core.
    private ItemStack itemStack;
    private ItemMeta itemMeta;

    private Material material;

    // Custom Lore.
    private Field LORE_FIELD;

    // Custom Model Data.
    private boolean isCustomModelData;

    private int customModelData;

    // Custom Heads.
    private boolean isHead;
    private Field profile;
    private String texture;

    // Potions
    private boolean isPotion;
    private boolean isTippedArrow;
    private Color potionColor;
    private PotionType potionType;

    // Leather.
    private boolean isLeather;
    private boolean isArmor;
    private TrimMaterial trimMaterial;
    private TrimPattern trimPattern;
    private Color armorColor;

    // Banners.
    private boolean isBanner;
    private List<Pattern> patterns;

    // Shields.
    private boolean isShield;

    // Firework.
    private boolean isFirework;
    private boolean isFireworkStar;

    // Enchantments/Flags
    private boolean isDurable;
    private boolean hideFlags;
    private boolean isGlowing;

    /**
     * An empty constructor for when you want to populate options after creation.
     */
    protected BaseItemBuilder() {
        this.itemStack = null;
        this.itemMeta = null;
        this.material = Material.AIR;
        this.LORE_FIELD = null;
        this.isCustomModelData = false;
        this.customModelData = 0;
        this.isHead = false;
        this.profile = null;
        this.texture = "";
        this.isPotion = false;
        this.isTippedArrow = false;
        this.potionColor = Color.WHITE;
        this.potionType = PotionType.MUNDANE;
        this.isLeather = false;
        this.armorColor = Color.WHITE;
        this.isBanner = false;
        this.patterns = Collections.emptyList();
        this.isShield = false;
        this.isFirework = false;
        this.isFireworkStar = false;
        this.isDurable = false;
        this.hideFlags = false;
        this.isGlowing = false;
    }

    /**
     * A protected constructor to start off our item-stack process.
     *
     * @param itemStack the itemstack to utilize when building
     */
    protected BaseItemBuilder(ItemStack itemStack) {
        this.itemStack = itemStack;

        try {
            Class<?> metaClass = SKULL_CHECKER.craftClass("inventory.CraftMetaItem");

            LORE_FIELD = metaClass.getDeclaredField("lore");
            LORE_FIELD.setAccessible(true);
        } catch (NoSuchFieldException | ClassNotFoundException exception) {
            Bukkit.getLogger().warning("Failed to make the lore field accessible as it was not found. Perhaps an invalid item was supplied?");
        }

        this.material = itemStack.getType();

        switch (this.material) {
            case PLAYER_HEAD -> this.isHead = true;
            case POTION, SPLASH_POTION -> this.isPotion = true;
            case TIPPED_ARROW -> this.isTippedArrow = true;
            case LEATHER_HELMET, LEATHER_CHESTPLATE, LEATHER_LEGGINGS, LEATHER_BOOTS, LEATHER_HORSE_ARMOR -> this.isLeather = true;
            case SHIELD -> this.isShield = true;
            case FIREWORK_ROCKET -> this.isFirework = true;
            case FIREWORK_STAR -> this.isFireworkStar = true;
        }

        String name = this.material.name();

        this.isArmor = name.endsWith("_HELMET") || name.endsWith("_CHESTPLATE") || name.endsWith("_LEGGINGS") || name.endsWith("_BOOTS");

        // Accounts for all banners.
        if (this.material.name().contains("BANNER")) this.isBanner = true;

        // if (this.material.name().contains("SPAWN_EGG")) this.isEgg = true;

        this.itemMeta = itemStack.hasItemMeta() ? itemStack.getItemMeta() : Bukkit.getServer().getItemFactory().getItemMeta(material);
    }

    /**
     * Set the display name of the item stack.
     *
     * @param displayName the display-name
     */
    private void setDisplayName(Component displayName) {
        this.itemMeta.displayName(displayName);
    }

    /**
     * Set the display name of the item stack without italics.
     *
     * @param displayName the display-name
     * @param removeItalics whether to keep or remove italics
     * @return the item builder with updated name
     */
    public Base setDisplayName(Component displayName, boolean removeItalics) {
        if (removeItalics) { this.itemMeta.displayName(displayName.decoration(TextDecoration.ITALIC, false)); } else setDisplayName(displayName);
        return (Base) this;
    }

    /**
     * Set lore on an item.
     *
     * @param lore The lore to set
     * @return the item builder with updated lore's
     */
    public Base setLore(Component ... lore) {
        return setLore(Arrays.asList(lore));
    }

    /**
     * Set a list of lore to the LORE_FIELD
     *
     * @param lore The list to set
     * @return the item builder with updated lore's
     */
    public Base setLore(List<Component> lore) {
        List<String> jsonLore = lore.stream().filter(Objects::nonNull).map(this.gson::serialize).toList();

        try {
            LORE_FIELD.set(this.itemMeta, jsonLore);
        } catch (IllegalAccessException exception) {
            exception.printStackTrace();
        }

        return (Base) this;
    }

    /**
     * Add a line to the current lore of the item.
     *
     * @param lore the new line you wish to add
     * @return the item builder with updated lore
     */
    public Base addLore(Consumer<List<Component>> lore) {
        List<Component> components;

        try {
            List<String> jsonLore = (List<String>) LORE_FIELD.get(this.itemMeta);

            components = (jsonLore == null) ? new ArrayList<>() : jsonLore.stream().map(this.gson::deserialize).collect(Collectors.toList());
        } catch (Exception exception) {
            components = new ArrayList<>();
            exception.printStackTrace();
        }

        lore.accept(components);
        return (Base) this;
    }

    /**
     * Set the amount of the item stack.
     *
     * @param amount the amount
     * @return the item builder with updated amount
     */
    public Base setAmount(int amount) {
        this.itemStack.setAmount(amount);
        return (Base) this;
    }

    /**
     * Adds an enchantment to the item.
     *
     * @param enchantment the enchantment you wish to add
     * @param level the level of the enchantment ( Includes unsafe levels )
     * @param ignoreLevelRestriction whether to ignore level restriction or not
     * @return the item builder with the updated enchantment
     */
    public Base addEnchantment(Enchantment enchantment, int level, boolean ignoreLevelRestriction) {
        this.itemMeta.addEnchant(enchantment, level, ignoreLevelRestriction);
        return (Base) this;
    }

    /**
     * Adds an enchantment to the item.
     *
     * @param enchantment The enchantment you wish to add
     * @return the item builder with the updated enchantment
     */
    public Base removeEnchantment(Enchantment enchantment) {
        this.itemMeta.removeEnchant(enchantment);
        return (Base) this;
    }

    /**
     * It will override any and all enchantments used in:
     * -> addEnchantment Method.
     *
     * @param enchantments the enchantments you wish to add
     * @param ignoreLevelRestriction whether to ignore level restrictions or not
     * @return the item builder with the updated enchantments
     */
    public Base setEnchantments(HashMap<Enchantment, Integer> enchantments, boolean ignoreLevelRestriction) {
        enchantments.forEach((enchantment, integer) -> this.itemMeta.addEnchant(enchantment, integer, ignoreLevelRestriction));
        return (Base) this;
    }

    /**
     * @param patterns the list of Patterns to add
     * @return the item builder with updated patterns
     */
    public Base addPatterns(List<String> patterns) {
        patterns.forEach(this::addPatterns);
        return (Base) this;
    }

    /**
     * @param pattern A pattern to add
     * @return The ItemBuilder with an updated pattern
     */
    public Base addPattern(Pattern pattern) {
        this.patterns.add(pattern);
        return (Base) this;
    }

    /**
     * @param patterns Set a list of Patterns
     * @return The ItemBuilder with an updated list of patterns
     */
    public Base setPattern(List<Pattern> patterns) {
        this.patterns = patterns;
        return (Base) this;
    }

    /**
     * Adds a list of item flags.
     *
     * @param flags the list of item flags to add
     * @return the list of item flags
     */
    public Base addItemFlags(List<String> flags) {
        flags.forEach(flag -> {
            try {
                ItemFlag itemFlag = ItemFlag.valueOf(flag.toUpperCase());

                addItemFlag(itemFlag);
            } catch (Exception exception) {
                Bukkit.getLogger().warning("Failed to add item flag: " + flag + ". The flag is invalid!");
            }
        });

        return (Base) this;
    }

    /**
     * Sets the player texture/player name
     *
     * @param texture the texture/player name
     * @return the item builder
     */
    public Base setTexture(String texture) {
        this.texture = texture;

        return (Base) this;
    }

    /**
     * Set the type of item and its metadata in the builder.
     *
     * @param material the string must be in this form: %Material% or %Material%:%MetaData%
     * @return the item builder with updated material
     */
    public Base setValue(String material) {
        if (material == null || material.isEmpty()) {
            Bukkit.getLogger().warning("Material cannot be null or empty, Output: " + material + ".");
            Bukkit.getLogger().warning("Please take a screenshot of this before asking for support.");

            return (Base) this;
        }

        String metaData;

        if (isPotion || isTippedArrow) {
            if (material.contains(";")) {
                String[] section = material.split(";");

                String[] sectionOne = section[0].split(":");
                String[] sectionTwo = section[1].split(":");

                try {
                    this.potionType = PotionType.valueOf(sectionOne[1]);
                } catch (Exception exception) {
                    Bukkit.getLogger().warning("Failed to set potion type " + sectionOne[1] + ". The potion type inputted is invalid.");
                }

                this.potionColor = getColor(sectionTwo[1]);
            }

            return (Base) this;
        }

        if (material.contains(":")) { // Sets the durability or another value option.
            String[] materialSplit = material.split(":");

            material = materialSplit[0];
            metaData = materialSplit[1];

            if (metaData.contains("#")) { // <ID>:<Durability>#<CustomModelData>
                String modelData = metaData.split("#")[1];

                if (isValidInteger(modelData)) {
                    this.isCustomModelData = true;
                    this.customModelData = Integer.parseInt(modelData);
                }
            }

            metaData = metaData.replace("#" + customModelData, "");

            if (isValidInteger(metaData)) { // Value is durability.
                int damage = Integer.parseInt(metaData);

                if (this.itemMeta instanceof Damageable) ((Damageable) this.itemMeta).setDamage(damage);
            } else { // Value is something else.
                if (isPotion) {
                    this.potionType = PotionType.valueOf(metaData);

                    if (getColor(metaData) != null) this.potionColor = getColor(metaData);
                }

                if (isLeather) this.armorColor = getColor(metaData);
            }
        } else if (material.contains("#")) {
            String[] materialSplit = material.split("#");
            material = materialSplit[0];

            if (isValidInteger(materialSplit[1])) { // Value is a number.
                this.isCustomModelData = true;
                this.customModelData = Integer.parseInt(materialSplit[1]);
            }
        }

        Material matchedMaterial = Material.matchMaterial(material);

        if (matchedMaterial != null) this.material = matchedMaterial;

        if (this.isArmor) ((ArmorMeta) itemMeta).setTrim(new ArmorTrim(this.trimMaterial, this.trimPattern));

        this.itemStack.setType(this.material);

        setItemMeta(this.itemStack.getItemMeta());

        return (Base) this;
    }

    /**
     * @param hideFlags hide item flags based on a boolean
     * @return the item builder with an updated boolean
     */
    public Base hideFlags(boolean hideFlags) {
        this.hideFlags = hideFlags;
        return (Base) this;
    }

    /**
     * @param isGlowing sets whether to make an item glow or not
     * @return the item builder with an updated boolean
     */
    public Base setGlow(boolean isGlowing) {
        this.isGlowing = isGlowing;
        return (Base) this;
    }

    public Base setTrim(TrimMaterial trimMaterial, TrimPattern trimPattern) {
        this.trimMaterial = trimMaterial;
        this.trimPattern = trimPattern;

        return (Base) this;
    }

    /**
     * @param isDurable whether the item is durable or not
     * @return the item builder with an updated boolean
     */
    public Base setDurable(boolean isDurable) {
        this.isDurable = isDurable;
        return (Base) this;
    }

    /**
     * Set an array of effects.
     *
     * @param effects the effects to set
     * @return the item builder with updated firework effects
     */
    public Base setEffect(FireworkEffect... effects) {
        return setEffect(Arrays.asList(effects));
    }

    /**
     * Set a list of firework effects to the fireworks
     *
     * @param effects the effects to add
     * @return the item builder with updated firework effects
     */
    public Base setEffect(List<FireworkEffect> effects) {
        if (effects.isEmpty()) return (Base) this;

        if (this.isFireworkStar) {
            FireworkEffectMeta effectMeta = (FireworkEffectMeta) this.getItemMeta();

            effectMeta.setEffect(effects.get(0));
            this.setItemMeta(effectMeta);
        }

        if (this.isFirework) {
            FireworkMeta fireworkMeta = (FireworkMeta) this.getItemMeta();

            fireworkMeta.addEffects(effects);
            this.setItemMeta(fireworkMeta);
        }

        return (Base) this;
    }

    /**
     * Set the power of a firework which increases Flight Time.
     *
     * @param power the power value
     * @return the item builder with updated firework power
     */
    public Base setPower(int power) {
        if (this.isFirework) {
            FireworkMeta fireworkMeta = (FireworkMeta) this.getItemMeta();

            fireworkMeta.setPower(power);

            this.setItemMeta(fireworkMeta);
        }

        return (Base) this;
    }

    /**
     * @return the completed itemstack
     */
    public ItemStack build() {
        if (this.material != Material.AIR) {
            if (this.isHead) {
                // Set the field to accessible.
                exposeField();

                setPlayerTexture(this.texture);
            }

            if (this.isPotion || this.isTippedArrow && (this.potionType != null || this.potionColor != null)) {
                PotionMeta potionMeta = (PotionMeta) this.itemMeta;

                if (this.potionType != null) potionMeta.setBasePotionData(new PotionData(this.potionType));

                if (this.potionColor != null) potionMeta.setColor(this.potionColor);

                this.setItemMeta(potionMeta);
            }

            if (this.isLeather && this.armorColor != null) {
                LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) this.itemMeta;
                leatherArmorMeta.setColor(this.armorColor);
            }

            if (this.isBanner && !this.patterns.isEmpty()) {
                BannerMeta bannerMeta = (BannerMeta) this.itemMeta;
                bannerMeta.setPatterns(this.patterns);
            }

            if (this.isShield && !this.patterns.isEmpty()) {
                BlockStateMeta shieldMeta = (BlockStateMeta) this.itemMeta;
                Banner banner = (Banner) shieldMeta.getBlockState();

                banner.setPatterns(this.patterns);
                banner.update();

                shieldMeta.setBlockState(banner);
            }

            if (this.isCustomModelData) this.itemMeta.setCustomModelData(this.customModelData);

            if (this.hideFlags) this.itemMeta.addItemFlags(ItemFlag.values());

            this.itemMeta.setUnbreakable(this.isDurable);

            this.addGlow();
        } else {
            Bukkit.getLogger().warning("Material cannot be AIR.");
        }

        this.itemStack.setItemMeta(this.itemMeta);

        return this.itemStack;
    }

    /**
     * Add an item flag to the item.
     *
     * @param itemFlag the item flag to add
     */
    private void addItemFlag(ItemFlag itemFlag) {
        this.itemMeta.addItemFlags(itemFlag);
    }

    /**
     * Set a player texture.
     *
     * @param texture the player, base64 or texture
     */
    private void setPlayerTexture(String texture) {
        this.texture = texture;

        Player player = Bukkit.getServer().getPlayer(this.texture);

        if (player != null) {
            setOwner(player);
            return;
        }

        if (this.texture.startsWith("http")) {
            setTexture(convert(this.texture), UUID.randomUUID());
            return;
        }

        setTexture(this.texture, UUID.randomUUID());
    }

    /**
     * Set the skull owner
     *
     * @param player the player the skull will look like
     */
    private void setOwner(OfflinePlayer player) {
        if (this.SKULL_CHECKER.isPlayerSkull(this.material)) return;

        SkullMeta skullMeta = (SkullMeta) this.getItemMeta();

        skullMeta.setOwningPlayer(player);

        this.setItemMeta(skullMeta);
    }

    /**
     * Add glow to an item.
     */
    private void addGlow() {
        if (this.isGlowing) {
            if (this.itemMeta.hasEnchants()) return;

            this.itemMeta.addEnchant(Enchantment.LUCK, 1, false);

            this.setItemMeta(this.itemMeta);
        }
    }

    /**
     * Expose the skull meta profile field.
     */
    private void exposeField() {
        if (this.SKULL_CHECKER.isPlayerSkull(this.material)) return;

        Field field;

        try {
            SkullMeta skullMeta = (SkullMeta) this.SKULL_CHECKER.skull().getItemMeta();
            field = skullMeta.getClass().getDeclaredField("profile");

            field.setAccessible(true);
        } catch (NoSuchFieldException exception) {
            Bukkit.getLogger().warning("Failed to make the meta field for profile accessible as it was not found. Perhaps an invalid item meta or field supplied?");

            field = null;
        }

        this.profile = field;
    }

    /**
     * Checks if an integer is valid.
     *
     * @param value The string to check
     * @return true if it is valid otherwise false
     */
    private boolean isValidInteger(String value) {
        try {
            Integer.parseInt(value);
        } catch (NumberFormatException exception) {
            return false;
        }

        return true;
    }

    /**
     * Get the color from a string.
     *
     * @param color the string of the color
     * @return the color from the string
     */
    private Color getColor(String color) {
        if (color != null) {
            switch (color.toUpperCase()) {
                case "AQUA" -> {
                    return Color.AQUA;
                }
                case "BLACK" -> {
                    return Color.BLACK;
                }
                case "BLUE" -> {
                    return Color.BLUE;
                }
                case "FUCHSIA" -> {
                    return Color.FUCHSIA;
                }
                case "GRAY" -> {
                    return Color.GRAY;
                }
                case "GREEN" -> {
                    return Color.GREEN;
                }
                case "LIME" -> {
                    return Color.LIME;
                }
                case "MAROON" -> {
                    return Color.MAROON;
                }
                case "NAVY" -> {
                    return Color.NAVY;
                }
                case "OLIVE" -> {
                    return Color.OLIVE;
                }
                case "ORANGE" -> {
                    return Color.ORANGE;
                }
                case "PURPLE" -> {
                    return Color.PURPLE;
                }
                case "RED" -> {
                    return Color.RED;
                }
                case "SILVER" -> {
                    return Color.SILVER;
                }
                case "TEAL" -> {
                    return Color.TEAL;
                }
                case "WHITE" -> {
                    return Color.WHITE;
                }
                case "YELLOW" -> {
                    return Color.YELLOW;
                }
            }

            try {
                String[] rgb = color.split(",");
                return Color.fromRGB(Integer.parseInt(rgb[0]), Integer.parseInt(rgb[1]), Integer.parseInt(rgb[2]));
            } catch (Exception ignore) {}
        }

        return null;
    }

    /**
     * Add patterns to the item.
     *
     * @param stringPattern the pattern you wish to add
     */
    private void addPatterns(String stringPattern) {
        try {
            String[] split = stringPattern.split(":");

            for (PatternType pattern : PatternType.values()) {

                if (split[0].equalsIgnoreCase(pattern.name()) || split[0].equalsIgnoreCase(pattern.getIdentifier())) {
                    DyeColor color = getDyeColor(split[1]);

                    if (color != null) this.addPattern(new Pattern(color, pattern));

                    break;
                }
            }
        } catch (Exception ignored) {}
    }

    /**
     * Get the dye color from a string.
     *
     * @param color the string of the color
     * @return the dye color from the string
     */
    public DyeColor getDyeColor(String color) {
        if (color != null) {
            try {
                return DyeColor.valueOf(color.toUpperCase());
            } catch (Exception exception) {
                try {
                    String[] rgb = color.split(",");
                    return DyeColor.getByColor(Color.fromRGB(Integer.parseInt(rgb[0]), Integer.parseInt(rgb[1]), Integer.parseInt(rgb[2])));
                } catch (Exception ignore) {}
            }
        }

        return null;
    }

    /**
     * Set the texture using the game profiles.
     *
     * @param texture the texture url, player or base64
     * @param uuid a random uuid unless a player is supplied
     */
    private void setTexture(String texture, UUID uuid) {
        if (this.SKULL_CHECKER.isPlayerSkull(this.material)) return;

        if (this.profile == null) return;

        SkullMeta skullMeta = (SkullMeta) this.itemMeta;
        GameProfile gameProfile = new GameProfile(uuid, null);

        gameProfile.getProperties().put("textures", new Property("textures", texture));

        try {
            this.profile.set(skullMeta, gameProfile);
        } catch (Exception exception) {
            Bukkit.getLogger().warning("Failed to set the meta & game profile. Perhaps an invalid texture?");
            Bukkit.getLogger().warning("Your Input: " + texture + ".");
        }

        setItemMeta(skullMeta);
    }

    /**
     * Convert an url to base64.
     *
     * @param url the url to convert
     * @return the converted url
     */
    private String convert(String url) {
        URL actualLink;

        try {
            actualLink = new URL(url);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        String encode = "{\"textures\":{\"SKIN\":{\"url\":\"" + actualLink + "\"}}}";

        return Base64.getEncoder().encodeToString(encode.getBytes());
    }

    // Protected getters for extended builders.
    /**
     * @return the itemstack
     */
    protected ItemStack getItemStack() {
        return this.itemStack;
    }

    /**
     * Updates the item stack.
     *
     * @param itemStack the itemstack
     */
    protected void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    /**
     * @return the itemmeta
     */
    protected ItemMeta getItemMeta() {
        return this.itemMeta;
    }

    /**
     * Updates the item meta.
     *
     * @param itemMeta the itemmeta
     */
    protected void setItemMeta(ItemMeta itemMeta) {
        this.itemMeta = itemMeta;
    }
}