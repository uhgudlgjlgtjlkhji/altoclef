package adris.altoclef;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AltoClef implements ModInitializer {
    public static final String MOD_ID = "altoclef";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    
    private static AltoClef instance;
    private final BotBehaviour botBehaviour;

    public AltoClef() {
        instance = this;
        this.botBehaviour = new BotBehaviour();
    }

    public static AltoClef getInstance() {
        return instance;
    }

    @Override
    public void onInitialize() {
        LOGGER.info("Alto Clef for Minecraft 26.1.2 initialized!");
    }

    public BotBehaviour getBotBehaviour() {
        return botBehaviour;
    }
}
