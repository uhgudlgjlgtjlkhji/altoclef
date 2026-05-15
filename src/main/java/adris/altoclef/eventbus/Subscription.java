package adris.altoclef.eventbus;

@FunctionalInterface
public interface Subscription<T> {
    void handle(T event);
}
