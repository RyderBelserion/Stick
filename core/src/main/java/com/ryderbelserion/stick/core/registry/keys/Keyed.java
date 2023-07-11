package com.ryderbelserion.stick.core.registry.keys;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class Keyed {

    private final Key key;

    public Keyed(@NotNull Key key) {
        this.key = key;
    }

    @NotNull
    public Key getKey() {
        return this.key;
    }

    @Override
    public abstract boolean equals(@Nullable Object instance);

    @Override
    public abstract int hashCode();

    @Override
    public abstract String toString();
}