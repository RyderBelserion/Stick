package com.ryderbelserion.stick.paper.commands.sender;

import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import java.util.List;

public interface CommandActor {

    void reply(String message);

    void reply(Component component);

    boolean hasPermission(Permission permission);

    boolean hasPermission(String rawPermission);

    CommandSender getSender();

    boolean isPlayer();

    Player getPlayer();

    void setAlias(String alias);

    String getAlias();

    List<String> getArgs();

    void removeArgs(int arg);

}