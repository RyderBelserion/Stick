package us.crazycrew.crazycore.paper.files.types.adapters;

import com.google.gson.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import java.lang.reflect.Type;

public class LocationTypeAdapter implements JsonSerializer<Location>, JsonDeserializer<Location> {

    @Override
    public JsonElement serialize(Location location, Type type, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();

        try {
            jsonObject.add("x", new JsonPrimitive(location.getX()));
            jsonObject.add("y", new JsonPrimitive(location.getY()));
            jsonObject.add("z", new JsonPrimitive(location.getZ()));
            jsonObject.add("yaw", new JsonPrimitive(location.getYaw()));
            jsonObject.add("pitch", new JsonPrimitive(location.getPitch()));
            jsonObject.add("world", new JsonPrimitive(location.getWorld().getName()));
        } catch (Exception exception) {
            exception.printStackTrace();

            return jsonObject;
        }

        return null;
    }

    @Override
    public Location deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = element.getAsJsonObject();

        try {
            return new Location(Bukkit.getWorld(jsonObject.get("world").getAsString()), jsonObject.get("x").getAsDouble(), jsonObject.get("y").getAsDouble(), jsonObject.get("z").getAsDouble(), jsonObject.get("yaw").getAsFloat(), jsonObject.get("pitch").getAsFloat());
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }
}