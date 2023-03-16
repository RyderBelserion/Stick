package us.crazycrew.example;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.bukkit.Bukkit;
import us.crazycrew.crazycore.CrazyLogger;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TestUpdater {

    private static URL apiPage;

    public static void Test() throws IOException {
        try {
            apiPage = new URL("https://api.modrinth.com/v2/project/crazycrates/version");
        } catch (Exception exception) {
            CrazyLogger.info(exception.getMessage());
        }

        HttpURLConnection connection = (HttpURLConnection) apiPage.openConnection();

        connection.addRequestProperty("User-Agent", "CrazyCrates");

        InputStream inputStream = connection.getInputStream();
        InputStreamReader reader = new InputStreamReader(inputStream);

        Gson gson = new Gson();

        String value = gson.fromJson(reader, JsonObject.class).get("version_number").getAsString();

        Bukkit.getLogger().warning(value);
    }
}