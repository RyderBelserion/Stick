package us.crazycrew.crazycore.paper.utils;

public class PaperUtils {

    private PaperUtils() {}

    private static boolean hasClass(String className) {
        try {
            Class.forName(className);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean isSpigot() {
        return hasClass("org.spigotmc.SpigotConfig");
    }

    public static boolean isPaper() {
        return hasClass("io.papermc.paper.configuration.Configuration") || hasClass("com.destroystokyo.paper.PaperConfig");
    }

    public static boolean isFolia() {
        return hasClass("io.papermc.paper.threadedregions.RegionizedServer");
    }
}