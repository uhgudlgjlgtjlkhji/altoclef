package adris.altoclef.util.time;

import net.minecraft.client.Minecraft;
import net.minecraft.network.Connection;

public class TimerGame {
    private Connection _lastConnection;
    private double _lastTime;

    public TimerGame(double initialTime) {
        reset();
    }

    public TimerGame() {
        reset();
    }

    public void reset() {
        Minecraft client = Minecraft.getInstance();
        if (client.getConnection() != null) {
            _lastConnection = client.getConnection().getConnection();
        }
        _lastTime = getTime(_lastConnection);
    }

    public boolean elapsed(double seconds) {
        return getElapsedTime() >= seconds;
    }

    public double getElapsedTime() {
        return getTime(getCurrentConnection()) - _lastTime;
    }

    private Connection getCurrentConnection() {
        Minecraft client = Minecraft.getInstance();
        if (client.getConnection() != null) {
            return client.getConnection().getConnection();
        }
        return _lastConnection;
    }

    private static double getTime(Connection connection) {
        return System.currentTimeMillis() / 1000.0;
    }
}
