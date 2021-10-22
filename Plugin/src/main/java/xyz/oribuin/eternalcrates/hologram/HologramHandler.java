package xyz.oribuin.eternalcrates.hologram;

import org.bukkit.Location;
import org.bukkit.entity.Entity;

import java.util.List;

public interface HologramHandler {

    /**
     * Creates or updates a hologram at the given location
     *
     * @param location The location of the hologram
     * @param text     The text for the hologram
     */
    void createOrUpdateHologram(Location location, List<String> text);

    /**
     * Deletes a hologram at a given location if one exists
     *
     * @param location The location of the hologram
     */
    void deleteHologram(Location location);

    /**
     * Deletes all holograms.
     */
    void deleteAllHolograms();

    /**
     * Checks if the given Entity is part of a hologram
     *
     * @param entity The Entity to check
     * @return true if the Entity is a hologram, otherwise false
     */
    boolean isHologram(Entity entity);

    /**
     * @return true if this hologram handler plugin is enabled, false otherwise
     */
    boolean isEnabled();
}
