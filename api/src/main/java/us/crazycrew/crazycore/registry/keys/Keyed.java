package us.crazycrew.crazycore.registry.keys;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Description: Represents a unique object identified by a key.
 */
public abstract class Keyed {

    private final Key key;

    /**
     * Create a new key identified object.
     *
     * @param key key for object
     */
    public Keyed(@NotNull Key key) {
        this.key = key;
    }

    /**
     * Get the identifying key.
     *
     * @return the key
     */
    @NotNull
    public Key getKey() {
        return this.key;
    }

    /**
     * @param instance the object to check
     * @return true if it equals otherwise false
     */
    @Override
    public abstract boolean equals(@Nullable Object instance);

    /**
     * @return the hashcode
     */
    @Override
    public abstract int hashCode();

    /**
     * @return the string
     */
    @Override
    public abstract String toString();
}