package adris.altoclef.trackers;

import adris.altoclef.tracker.Tracker;

public class MiscBlockTracker extends Tracker<String> {
    private String currentBlock;

    public MiscBlockTracker() {
        super("MiscBlockTracker");
    }

    @Override
    public void tick() {
        // Update misc block tracking
    }

    @Override
    public void reset() {
        currentBlock = null;
    }

    public String getCurrentBlock() {
        return currentBlock;
    }

    public void setCurrentBlock(String block) {
        currentBlock = block;
    }
}
