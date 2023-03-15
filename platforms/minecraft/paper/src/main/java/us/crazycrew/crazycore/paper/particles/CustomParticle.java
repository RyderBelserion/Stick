package us.crazycrew.crazycore.paper.particles;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;

public class CustomParticle {

    private com.destroystokyo.paper.ParticleBuilder particleBuilder;

    public CustomParticle() {}

    public com.destroystokyo.paper.ParticleBuilder build() {
        return particleBuilder;
    }

    /**
     * Creates the particle builder instance.
     *
     * @param particle the particle to spawn
     */
    public CustomParticle init(Particle particle) {
        particleBuilder = new com.destroystokyo.paper.ParticleBuilder(particle);
        return this;
    }

    /**
     * Move an existing particle.
     *
     * @param location the position of the particle
     * @param x the position on the x-axis to offset it
     * @param y the position of the y-axis to offset it
     * @param z the position of the z-axis to offset it
     */
    public CustomParticle move(Location location, int x, int y, int z) {
        particleBuilder.location(location.add(x, y, z));
        return this;
    }
}