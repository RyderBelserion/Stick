package us.crazycrew.crazycore.registry.keys;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * Description: A simple string wrapper used to identify unique strings.
 */
public final class Key {

    private final String key;

    /**
     * Create a new key.
     *
     * @param key unique string
     */
    public Key(@NotNull String key) {
        Pattern VALID_CHARACTERS = Pattern.compile("^[a-zA-Z0-9._-]+[^.]$");
        if (!VALID_CHARACTERS.matcher(key).matches()) throw new IllegalArgumentException(String.format("Non [a-zA-Z0-9._-] character in key '%s'", key));

        this.key = key;
    }

    /**
     * Create a new key.
     *
     * @param key unique string
     * @return a new key
     */
    @NotNull
    public static Key of(@NotNull String key) {
        return new Key(key);
    }

    /**
     * Create a new key.
     *
     * @param uuid uuid
     * @return a new key
     */
    @NotNull
    public static Key of(@NotNull UUID uuid) {
        return Key.of(uuid.toString());
    }

    @Override
    public boolean equals(@Nullable Object instance) {
        if (this == instance) return true;
        if (instance == null) return false;
        if (getClass() != instance.getClass()) return false;

        Key other = (Key) instance;

        return toString().equals(other.toString());
    }

    /**
     * @return the hashcode
     */
    @Override
    public int hashCode() {
        return 31 + toString().hashCode();
    }

    /**
     * @return the string
     */
    @Override
    public String toString() {
        return this.key;
    }
}