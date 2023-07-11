package com.ryderbelserion.stick.core.registry;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Registry<Key, Value> {

    protected final Map<Key, Value> entries = new ConcurrentHashMap<>();

    @NotNull
    public Value getOrRegister(@NotNull Key key, @NotNull Value value) {
        Value existingKey = get(key);

        if (existingKey == null) {
            register(key, value);
            return value;
        }

        return existingKey;
    }

    @Nullable
    public Value register(@NotNull Key key, @NotNull Value value) {
        if (has(key)) return null;

        this.entries.put(key, value);

        return value;
    }

    @Nullable
    public Value unregister(@NotNull Key key) {
        return this.entries.remove(key);
    }

    public void unregister() {
        getEntries().keySet().forEach(this::unregister);
    }

    public boolean has(@NotNull Key key) {
        return getEntries().containsKey(key);
    }

    @Nullable
    public Value get(@NotNull Key key) {
        return getEntries().get(key);
    }

    @NotNull
    public Map<Key, Value> getEntries() {
        return Collections.unmodifiableMap(this.entries);
    }

    public int size() {
        return getEntries().size();
    }
}