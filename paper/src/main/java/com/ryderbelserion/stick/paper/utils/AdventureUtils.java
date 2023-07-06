package com.ryderbelserion.stick.paper.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.jetbrains.annotations.NotNull;

public class AdventureUtils {

    public static Component parse(@NotNull String message, boolean hasItalics, @NotNull TagResolver.Single... placeholders) {
        return MiniMessage.miniMessage().deserialize(message, placeholders).decoration(TextDecoration.ITALIC, hasItalics);
    }
}