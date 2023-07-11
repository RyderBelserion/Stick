package com.ryderbelserion.stick.core.registry;

import com.ryderbelserion.stick.core.registry.keys.Key;
import com.ryderbelserion.stick.core.registry.keys.Keyed;
import org.jetbrains.annotations.Nullable;

public class KeyedRegistry<Type extends Keyed> extends Registry<Key, Type> {

    @Nullable
    public Type register(@Nullable Type entry) {
        if (entry == null) return null;

        if (has(entry.getKey())) return null;

        return register(entry.getKey(), entry);
    }

    @Nullable
    public Type unregister(@Nullable Type entry) {
        if (entry == null) return null;

        if (!has(entry.getKey())) return null;

        return unregister(entry.getKey());
    }
}