package us.crazycrew.example;

import org.bukkit.Bukkit;
import us.crazycrew.crazycore.CrazyLogger;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class TestUpdater {

    protected TestUpdater() {}

    private static URL apiPage;

    public static void Test() throws IOException {
        try {
            apiPage = new URL("https://api.modrinth.com/v2/project/crazycrates/version");
        } catch (Exception exception) {
            CrazyLogger.info(exception.getMessage());
        }

        HttpURLConnection connection = (HttpURLConnection) apiPage.openConnection();

        connection.setRequestMethod("GET");
        connection.addRequestProperty("User-Agent", "CrazyCrates");

        int responseCode = connection.getResponseCode();

        if (responseCode != 200) {
            Bukkit.getLogger().warning("Cannot read the modrinth page.");
            return;
        }
    }
}