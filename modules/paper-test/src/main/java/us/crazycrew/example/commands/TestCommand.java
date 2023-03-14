package us.crazycrew.example.commands;

import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import us.crazycrew.crazycore.paper.particles.ParticleBuilder;

public class TestCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        ParticleBuilder particleBuilder = new ParticleBuilder();

        Player player = (Player) sender;

        Particle particle = particleBuilder.color(Color.fromRGB(333)).build();

        player.spawnParticle(particle, player.getLocation(), 1);

        return false;
    }
}