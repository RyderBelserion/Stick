package us.crazycrew.crazycore.paper.player;

import net.kyori.adventure.audience.MessageType;
import net.kyori.adventure.identity.Identity;
import net.kyori.adventure.text.Component;
import org.bukkit.craftbukkit.v1_19_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import us.crazycrew.crazycore.registry.player.PlayerObject;
import java.net.URL;
import java.util.Objects;
import java.util.UUID;

/**
 * Description: Allows to obtain uuids, names or send messages.
 */
public class PaperPlayerObject extends PlayerObject {

    private final Player player;

    /**
     * Builds a new player.
     *
     * @param player the player
     */
    public PaperPlayerObject(Player player) {
        super(createKey(player.getUniqueId()));

        this.player = player;
    }

    /**
     * @return the player
     */
    public Player getPlayer() {
        return this.player;
    }

    public CraftPlayer getCraftPlayer() {
        return (CraftPlayer) this.player;
    }

    /**
     * @return the player name
     */
    @Override
    public @NotNull String getName() {
        return this.player.getName();
    }

    /**
     * @return the player uuid
     */
    @Override
    public @NotNull UUID getUUID() {
        return this.player.getUniqueId();
    }

    @Override
    @Nullable
    public URL getSkin() {
        return this.player.getPlayerProfile().getTextures().getSkin();
    }

    @Override
    public int getHealth() {
        return (int) Math.round(this.player.getHealth());
    }

    @Override
    public int getPing() {
        return getCraftPlayer().getPing();
    }

    /**
     * Sends a message to a player.
     *
     * @param source the identity of the source of the message
     * @param message a message
     * @param type the type
     */
    @Override
    public void sendMessage(@NotNull Identity source, @NotNull Component message, @NotNull MessageType type) {
        this.player.sendMessage(source, message, type);
    }

    /**
     * @param instance the object to check
     * @return true or false
     */
    @Override
    public boolean equals(@Nullable Object instance) {
        if (this == instance) return true;
        if (instance == null || this.getClass() != instance.getClass()) return false;
        PaperPlayerObject other = (PaperPlayerObject) instance;
        return getKey() == other.getKey() && this.player == other.player;
    }

    /**
     * @return the hash of the key / player
     */
    @Override
    public int hashCode() {
        return Objects.hash(getKey(), this.player);
    }

    /**
     * @return json string of player
     */
    @Override
    public String toString() {
        return "PaperPlayer{" + "key=" + getKey() + ",player=" + getPlayer().getUniqueId() + "}";
    }
}