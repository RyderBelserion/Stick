package us.crazycrew.crazycore.paper;

import us.crazycrew.crazycore.registry.senders.types.Console;
import net.kyori.adventure.audience.MessageType;
import net.kyori.adventure.identity.Identity;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.Objects;

/**
 * Description: A class that represents the console sender.
 */
public class PaperConsole extends Console {

    private final ConsoleCommandSender consoleCommandSender;

    /**
     * A constructor to build what we need for this class.
     */
    public PaperConsole() {
        this.consoleCommandSender = Bukkit.getConsoleSender();
    }

    /**
     * Send a message to the console command sender.
     *
     * @param source the identity of the source of the message
     * @param message a message
     * @param type the type
     */
    @Override
    public void sendMessage(@NotNull Identity source, @NotNull Component message, @NotNull MessageType type) {
        this.consoleCommandSender.sendMessage(source, message, type);
    }

    /**
     * @param instance the class instance
     * @return the console command sender
     */
    @Override
    public boolean equals(@Nullable Object instance) {
        if (this == instance) return true;
        if (instance == null) return false;
        if (this.getClass() != instance.getClass()) return false;

        PaperConsole other = (PaperConsole) instance;
        return getKey() == other.getKey() && this.consoleCommandSender == other.consoleCommandSender;
    }

    /**
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(getKey(), this.consoleCommandSender);
    }

    /**
     * @return the string
     */
    @Override
    public String toString() {
        return "PaperConsole{" + "key=" + getKey() + ",console=" + this.consoleCommandSender.getName() + "}";
    }
}