package com.ryderbelserion.stick.paper.commands.sender;

public interface CommandArgs {

    int getArgAsInt(int index, boolean notifySender, String invalidArg, String... placeholder);

    long getArgAsLong(int index, boolean notifySender, String invalidArg, String... placeholder);

    double getArgAsDouble(int index, boolean notifySender, String invalidArg, String... placeholder);

    boolean getArgAsBoolean(int index, boolean notifySender, String invalidArg, String... placeholder);

    float getArgAsFloat(int index, boolean notifySender, String invalidArg, String... placeholder);

}