package us.crazycrew.crazycore.registry;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description: Represents a key-value pair registry.
 */
public class Registry<Key, Value> {

    /**
     * A concurrent hashmap to hold values like player uuids.
     */
    protected final Map<Key, Value> entries = new ConcurrentHashMap<>();

    /**
     * Gets existing value by key, or register new value if no value already registered.
     *
     * @param key   key
     * @param value value to register
     * @return existing value, or newly registered value
     */
    @NotNull
    public Value getOrRegister(@NotNull Key key, @NotNull Value value) {
        Value existingKey = get(key);

        if (existingKey == null) {
            register(key, value);
            return value;
        }

        return existingKey;
    }

    /**
     * Register a new value with key.
     * <p>
     * Will return null if the key is already registered.
     *
     * @param key   key
     * @param value value
     * @return registered value or null
     */
    @Nullable
    public Value register(@NotNull Key key, @NotNull Value value) {
        if (has(key)) return null;

        this.entries.put(key, value);

        return value;
    }

    /**
     * Unregister the value for the provided key.
     * <p>
     * Will return null if no value registered with provided key.
     *
     * @param key key
     * @return unregistered value or null
     */
    @Nullable
    public Value unregister(@NotNull Key key) {
        return this.entries.remove(key);
    }

    /**
     * Unregister all entries.
     */
    public void unregister() {
        getEntries().keySet().forEach(this::unregister);
    }

    /**
     * Check if an entry is present for the provided key.
     *
     * @param key key
     * @return true if entry is present
     */
    public boolean has(@NotNull Key key) {
        return getEntries().containsKey(key);
    }

    /**
     * Get the registered value for the provided key.
     * <p>
     * Will return null if no value registered with provided key.
     *
     * @param key key
     * @return registered value or null
     */
    @Nullable
    public Value get(@NotNull Key key) {
        return getEntries().get(key);
    }

    /**
     * Get the registered entries.
     *
     * @return map of registered entries
     */
    @NotNull
    public Map<Key, Value> getEntries() {
        return Collections.unmodifiableMap(this.entries);
    }

    /**
     * Get the number of registered entries.
     *
     * @return number of entries
     */
    public int size() {
        return getEntries().size();
    }
}