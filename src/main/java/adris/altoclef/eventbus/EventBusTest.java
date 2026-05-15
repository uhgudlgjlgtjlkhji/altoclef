package adris.altoclef.eventbus;

public class EventBusTest {
    public static void main(String[] args) {
        // Test EventBus functionality
        EventBus.getInstance().register(String.class, (event) -> {
            System.out.println("Received: " + event);
        });
        
        EventBus.getInstance().post("Test message", String.class);
    }
}
