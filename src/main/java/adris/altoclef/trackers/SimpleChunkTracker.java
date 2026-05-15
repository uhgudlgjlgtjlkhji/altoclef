package adris.altoclef.trackers;

import adris.altoclef.tracker.Tracker;

public class SimpleChunkTracker extends Tracker<Integer> {
    private int currentChunkX = 0;
    private int currentChunkZ = 0;

    public SimpleChunkTracker() {
        super("SimpleChunkTracker");
    }

    @Override
    public void tick() {
        // Update chunk tracking
    }

    @Override
    public void reset() {
        currentChunkX = 0;
        currentChunkZ = 0;
    }

    public int getCurrentChunkX() { return currentChunkX; }
    public int getCurrentChunkZ() { return currentChunkZ; }
    public void setCurrentChunk(int x, int z) { 
        currentChunkX = x; 
        currentChunkZ = z; 
    }
}
