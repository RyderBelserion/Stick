package us.crazycrew.crazycore.registry;

import us.crazycrew.crazycore.registry.keys.Key;
import us.crazycrew.crazycore.registry.keys.Keyed;
import org.jetbrains.annotations.Nullable;

/**
 * Description: Represents a registry of keyed objects.
 */
public class KeyedRegistry<Type extends Keyed> extends Registry<Key, Type> {

    /**
     * Register a new entry.
     * <p>
     * Will return null if the entry is already registered.
     *
     * @param entry entry to register
     * @return registered entry or null
     */
    @Nullable
    public Type register(@Nullable Type entry) {
        if (entry == null) return null;

        if (has(entry.getKey())) return null;

        return register(entry.getKey(), entry);
    }

    /**
     * Unregister the specified entry.
     * <p>
     * Will return null if entry is not registered.
     *
     * @param entry entry to unregister
     * @return unregistered entry or null
     */
    @Nullable
    public Type unregister(@Nullable Type entry) {
        if (entry == null) return null;

        if (!has(entry.getKey())) return null;

        return unregister(entry.getKey());
    }
}