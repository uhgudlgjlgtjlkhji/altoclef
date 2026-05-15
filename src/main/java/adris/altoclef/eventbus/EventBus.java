package adris.altoclef.eventbus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EventBus {
    private static EventBus instance;
    private final Map<Class<?>, List<Subscription>> subscribers = new ConcurrentHashMap<>();

    public static EventBus getInstance() {
        if (instance == null) {
            instance = new EventBus();
        }
        return instance;
    }

    public <T> void register(Class<T> eventType, Subscription<T> subscription) {
        subscribers.computeIfAbsent(eventType, k -> new ArrayList<>()).add(subscription);
    }

    public <T> void unregister(Class<T> eventType, Subscription<T> subscription) {
        List<Subscription> list = subscribers.get(eventType);
        if (list != null) {
            list.remove(subscription);
        }
    }

    @SuppressWarnings("unchecked")
    public <T> void post(Object event, Class<T> eventType) {
        List<Subscription> list = subscribers.get(eventType);
        if (list != null) {
            for (Subscription<T> subscription : list) {
                try {
                    subscription.handle(event);
                } catch (Exception e) {
                    AltoClef.LOGGER.error("Error handling event", e);
                }
            }
        }
    }
}
