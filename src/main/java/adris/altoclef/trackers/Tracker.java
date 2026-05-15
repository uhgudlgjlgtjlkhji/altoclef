package adris.altoclef.tracker;

import net.minecraft.util.math.BlockPos;

public abstract class Tracker<T> {
    protected final String name;
    protected boolean enabled = true;

    public Tracker(String name) {
        this.name = name;
    }

    public abstract void tick();
    public abstract void reset();
    
    public String getName() { return name; }
    public boolean isEnabled() { return enabled; }
    public void setEnabled(boolean enabled) { this.enabled = enabled; }
}
