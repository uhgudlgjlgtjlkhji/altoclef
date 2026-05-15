package adris.altoclef.control;

public class KillAura {
    private boolean enabled = false;
    private double range = 4.0;

    public void tick() {
        if (!enabled) return;
        // Kill aura logic stub
    }

    public void enable() { enabled = true; }
    public void disable() { enabled = false; }
    public boolean isEnabled() { return enabled; }
    public double getRange() { return range; }
    public void setRange(double range) { this.range = range; }
}
