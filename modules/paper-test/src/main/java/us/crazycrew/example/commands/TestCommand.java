package us.crazycrew.example.commands;

import com.destroystokyo.paper.ParticleBuilder;
import io.papermc.paper.event.player.AsyncChatEvent;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import us.crazycrew.crazycore.paper.particles.CustomParticle;
import us.crazycrew.example.CrazyExample;

public class TestCommand implements Listener {

    @EventHandler
    public void onPlayerChat(AsyncChatEvent event) {
        Player player = event.getPlayer();

        CustomParticle customParticle = new CustomParticle();

        ParticleBuilder particleBuilder = customParticle.init(Particle.WATER_BUBBLE).build();

        CrazyExample.getPlugin().getServer().getScheduler().runTaskTimerAsynchronously(CrazyExample.getPlugin(), () -> {
            particleBuilder.location(player.getLocation());
            particleBuilder.count(24);
            particleBuilder.source(player);

            particleBuilder.offset(3, 1, 3);

            particleBuilder.spawn();
        }, 5, 20);
    }
}