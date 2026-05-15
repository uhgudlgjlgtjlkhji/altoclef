package adris.altoclef.trackers;

import adris.altoclef.tracker.Tracker;
import net.minecraft.entity.Entity;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class EntityTracker extends Tracker<Entity> {
    private final List<Entity> trackedEntities = new ArrayList<>();

    public EntityTracker() {
        super("EntityTracker");
    }

    @Override
    public void tick() {
        // Update tracked entities
    }

    @Override
    public void reset() {
        trackedEntities.clear();
    }

    public List<Entity> getTrackedEntities() {
        return Collections.unmodifiableList(trackedEntities);
    }

    public void addEntity(Entity entity) {
        trackedEntities.add(entity);
    }

    public void removeEntity(Entity entity) {
        trackedEntities.remove(entity);
    }
}
