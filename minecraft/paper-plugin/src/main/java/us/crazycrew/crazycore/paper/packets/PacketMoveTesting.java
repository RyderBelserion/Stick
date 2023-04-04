package us.crazycrew.crazycore.paper.packets;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import org.bukkit.craftbukkit.v1_19_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class PacketMoveTesting {

    public void trackPlayerMovement(Player player) {
        CraftPlayer craftPlayer = (CraftPlayer) player;

        ServerPlayer serverPlayer = craftPlayer.getHandle();

        ServerGamePacketListenerImpl playerConnection = serverPlayer.connection;
    }
}