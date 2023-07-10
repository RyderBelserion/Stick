package com.ryderbelserion.stick.paper.commands.sender;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public interface CommandArgs {

    int getArgAsInt(int index, boolean notifySender, String invalidArg, String placeholder);

    long getArgAsLong(int index, boolean notifySender, String invalidArg, String placeholder);

    double getArgAsDouble(int index, boolean notifySender, String invalidArg, String placeholder);

    boolean getArgAsBoolean(int index, boolean notifySender, String invalidArg, String placeholder);

    float getArgAsFloat(int index, boolean notifySender, String invalidArg, String placeholder);

    Player getArgAsPlayer(int index, boolean notifySender, String invalidArg, String placeholder);

    OfflinePlayer getArgAsOfflinePlayer(int index, boolean notifySender, String invalidArg, String placeholder);

}