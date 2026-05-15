package adris.altoclef.trackers;

import adris.altoclef.tracker.Tracker;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class TrackerManager {
    private final List<Tracker<?>> trackers = new ArrayList<>();

    public <T> void register(Tracker<T> tracker) {
        trackers.add(tracker);
    }

    public void tickAll() {
        trackers.forEach(Tracker::tick);
    }

    public void resetAll() {
        trackers.forEach(Tracker::reset);
    }

    public List<Tracker<?>> getTrackers() {
        return Collections.unmodifiableList(trackers);
    }
}
