package com.ryderbelserion.stick.core.registry.senders.types;

import com.ryderbelserion.stick.core.registry.keys.Key;
import com.ryderbelserion.stick.core.utils.AdventureUtils;
import net.kyori.adventure.text.ComponentLike;
import org.jetbrains.annotations.NotNull;
import java.util.UUID;

public abstract class Console extends Sender {

    public Console() {
        super(Key.of(new UUID(0, 0)));
    }

    @Override
    public void send(boolean prefix, String value, @NotNull ComponentLike message) {
        sendMessage(AdventureUtils.parse(value).append(message));
    }
}