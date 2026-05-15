package adris.altoclef.trackers;

import adris.altoclef.tracker.Tracker;
import net.minecraft.util.math.BlockPos;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class BlockTracker extends Tracker<BlockPos> {
    private final List<BlockPos> trackedBlocks = new ArrayList<>();

    public BlockTracker() {
        super("BlockTracker");
    }

    @Override
    public void tick() {
        // Update tracked blocks
    }

    @Override
    public void reset() {
        trackedBlocks.clear();
    }

    public List<BlockPos> getTrackedBlocks() {
        return Collections.unmodifiableList(trackedBlocks);
    }

    public void addBlock(BlockPos pos) {
        trackedBlocks.add(pos);
    }

    public void removeBlock(BlockPos pos) {
        trackedBlocks.remove(pos);
    }
}
