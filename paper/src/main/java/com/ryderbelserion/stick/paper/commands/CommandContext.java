package com.ryderbelserion.stick.paper.commands;

import com.ryderbelserion.stick.paper.commands.sender.CommandActor;
import com.ryderbelserion.stick.paper.commands.sender.CommandArgs;
import com.ryderbelserion.stick.paper.utils.AdventureUtils;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class CommandContext implements CommandActor, CommandArgs {

    private final CommandSender sender;
    private String alias;
    private final ArrayList<String> args;

    private Player player;

    public CommandContext(CommandSender sender, String alias, ArrayList<String> args) {
        this.sender = sender;

        if (sender instanceof Player) {
            this.player = (Player) sender;
        }

        this.alias = alias;
        this.args = args;
    }

    @Override
    public void reply(String message) {
        if (message.isBlank() || message.isEmpty()) return;

        Component component = AdventureUtils.parse(message);

        this.sender.sendMessage(component);
    }

    @Override
    public void reply(Component component) {
        this.sender.sendMessage(component);
    }

    @Override
    public void send(Audience audience, String message) {
        if (message.isBlank() || message.isEmpty()) return;

        Component component = AdventureUtils.parse(message);

        audience.sendMessage(component);
    }

    @Override
    public void send(Audience audience, Component component) {
        audience.sendMessage(component);
    }

    @Override
    public boolean hasPermission(Permission permission) {
        return this.player.hasPermission(permission);
    }

    @Override
    public boolean hasPermission(String rawPermission) {
        return this.player.hasPermission(rawPermission);
    }

    @Override
    public CommandSender getSender() {
        return this.sender;
    }

    @Override
    public boolean isPlayer() {
        return this.player != null;
    }

    @Override
    public Player getPlayer() {
        return this.player;
    }

    @Override
    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Override
    public String getAlias() {
        return this.alias;
    }

    @Override
    public List<String> getArgs() {
        return Collections.unmodifiableList(this.args);
    }

    @Override
    public void removeArgs(int arg) {
        this.args.remove(arg);
    }

    @Override
    public int getArgAsInt(int index, boolean notifySender, String invalidArg, String placeholder) {
        Integer value = null;

        try {
            value = Integer.parseInt(this.args.get(index));
        } catch (NumberFormatException exception) {
            if (notifySender) reply(invalidArg.replaceAll(placeholder, this.args.get(index)));
        }

        if (value != null) return value;

        return 1;
    }

    @Override
    public long getArgAsLong(int index, boolean notifySender, String invalidArg, String placeholder) {
        Long value = null;

        try {
            value = Long.parseLong(this.args.get(index));
        } catch (NumberFormatException exception) {
            if (notifySender) reply(invalidArg.replaceAll(placeholder, this.args.get(index)));
        }

        if (value != null) return value;

        return 1L;
    }

    @Override
    public double getArgAsDouble(int index, boolean notifySender, String invalidArg, String placeholder) {
        Double value = null;

        try {
            value = Double.parseDouble(this.args.get(index));
        } catch (NumberFormatException exception) {
            if (notifySender) reply(invalidArg.replaceAll(placeholder, this.args.get(index)));
        }

        if (value != null) return value;

        return 0.1;
    }

    @Override
    public boolean getArgAsBoolean(int index, boolean notifySender, String invalidArg, String placeholder) {
        String lowercase = this.args.get(index).toLowerCase();

        switch (lowercase) {
            case "true", "on", "1" -> {
                return true;
            }
            case "false", "off", "0" -> {
                return false;
            }
            default -> {
                if (notifySender) reply(invalidArg.replaceAll(placeholder, this.args.get(index).toLowerCase()));
                return false;
            }
        }
    }

    @Override
    public float getArgAsFloat(int index, boolean notifySender, String invalidArg, String placeholder) {
        Float value = null;

        try {
            value = Float.parseFloat(this.args.get(index));
        } catch (NumberFormatException exception) {
            if (notifySender) reply(invalidArg.replaceAll(placeholder, this.args.get(index)));
        }

        if (value != null) return value;

        return 1F;
    }

    @Override
    public Player getArgAsPlayer(int index, boolean notifySender, String invalidArg, String placeholder) {
        Player player = Bukkit.getServer().getPlayer(this.args.get(index));

        if (player == null) {
            if (notifySender) reply(invalidArg.replaceAll(placeholder, this.args.get(index)));

            return null;
        }

        return player;
    }

    @Override
    public OfflinePlayer getArgAsOfflinePlayer(int index, boolean notifySender, String invalidArg, String placeholder) {
        CompletableFuture<UUID> future = CompletableFuture.supplyAsync(() -> Bukkit.getServer().getOfflinePlayer(this.args.get(index))).thenApply(OfflinePlayer::getUniqueId);

        return Bukkit.getServer().getOfflinePlayer(future.join());
    }
}