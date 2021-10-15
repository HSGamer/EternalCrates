package xyz.oribuin.eternalcrates.animation;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import xyz.oribuin.eternalcrates.EternalCrates;
import xyz.oribuin.eternalcrates.crate.Crate;
import xyz.oribuin.eternalcrates.particle.ParticleData;
import xyz.oribuin.eternalcrates.util.PluginUtils;

import java.util.List;

public abstract class ParticleAnimation extends Animation {

    private ParticleData particleData = new ParticleData(Particle.FLAME);
    private int speed;
    private int length;

    public ParticleAnimation(String name, int speed) {
        super(name, AnimationType.PARTICLES);
        this.speed = speed;
        this.length = 60;
    }

    public abstract List<Location> particleLocations(Location crateLocation);

    public abstract void updateTimer();

    public ParticleData getParticleData() {
        return particleData;
    }

    /**
     * Spawn a particle at a location.
     *
     * @param loc   The location of the particle
     * @param count the amount of particles being spawned
     */
    public void play(Crate crate, Location loc, int count, Player player) {
        final Location centerLoc = PluginUtils.getBlockLoc(loc).clone().add(0.5, 0.5, 0.5);

        final BukkitTask task = Bukkit.getScheduler().runTaskTimerAsynchronously(EternalCrates.getInstance(), () -> {
            this.updateTimer();
            this.particleLocations(centerLoc).forEach(location -> particleData.spawn(location, count));
        }, 0, this.speed);

        Bukkit.getScheduler().runTaskLater(EternalCrates.getInstance(), x -> {
            task.cancel();
            finishFunction(crate, crate.selectReward(), player);
        }, this.getLength());

    }

    public void setParticleData(ParticleData particleData) {
        this.particleData = particleData;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

}
