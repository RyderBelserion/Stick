package us.crazycrew.crazycore.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.jetbrains.annotations.NotNull;

/**
 * Description: A wrapper around MiniMessage/Kyori API
 */
public class AdventureUtils {

    /**
     * Parse a message and replace placeholders if need be.
     *
     * @param message message to parse
     * @param placeholders message placeholders
     * @return a deserialized component
     */
    public static Component parse(@NotNull String message, boolean hasItalics, @NotNull TagResolver.Single... placeholders) {
        return MiniMessage.miniMessage().deserialize(message, placeholders).decoration(TextDecoration.ITALIC, hasItalics);
    }

    /**
     * Replaces a single placeholder.
     *
     * @param placeholder the placeholder
     * @param value the value for the placeholder
     * @return the replaced placeholder
     */
    public static TagResolver.@NotNull Single replacePlaceholder(@NotNull String placeholder, @NotNull String value) {
        return Placeholder.unparsed(placeholder, value);
    }
}