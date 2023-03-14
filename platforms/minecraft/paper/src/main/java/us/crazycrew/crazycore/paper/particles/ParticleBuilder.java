package us.crazycrew.crazycore.paper.particles;

import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

public class ParticleBuilder {

    private com.destroystokyo.paper.ParticleBuilder particleBuilder;

    public ParticleBuilder() {}

    public Particle beep(Player player) {
        player.spawnParticle(
                Particle.ASH,
                player.getLocation(),
                3
        );

        return color(Color.fromRGB(333)).build();
    }

    public Particle build() {
        return particleBuilder.particle();
    }

    public ParticleBuilder color(Color color) {
        particleBuilder.color(color);

        return this;
    }

    public ParticleBuilder color(int r, int g, int b) {
        particleBuilder.color(r, g, b);

        return this;
    }
}