package com.ryderbelserion.stick.core;

import org.jetbrains.annotations.NotNull;
import java.lang.reflect.Field;

public abstract class StickCore {

    public static @NotNull StickCore api() {
        return Provider.api();
    }

    protected static final class Provider {
        static StickCore api;

        static @NotNull StickCore api() {
            return Provider.api;
        }
    }

    public StickCore() {
        try {
            Field api = Provider.class.getDeclaredField("api");
            api.setAccessible(true);
            api.set(null, this);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public abstract String commandRequirementNotPlayer();

    public abstract String commandRequirementNoPermission();

    public abstract String commandHelpHeader();

    public abstract String commandHelpFooter();

    public abstract String commandInvalidPage();

    public abstract String commandPageFormat();

    public abstract String commandHoverFormat();

    public abstract String commandHoverAction();

    public abstract String commandNavigationText();

    public abstract String commandNavigationNextButton();

    public abstract String commandNavigationBackButton();

}