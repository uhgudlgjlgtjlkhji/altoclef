package adris.altoclef;

import adris.altoclef.butler.Butler;
import adris.altoclef.chains.*;
import adris.altoclef.commandsystem.CommandExecutor;
import adris.altoclef.control.InputControls;
import adris.altoclef.control.PlayerExtraController;
import adris.altoclef.control.SlotHandler;
import adris.altoclef.eventbus.EventBus;
import adris.altoclef.eventbus.events.ClientRenderEvent;
import adris.altoclef.eventbus.events.ClientTickEvent;
import adris.altoclef.eventbus.events.SendChatEvent;
import adris.altoclef.eventbus.events.TitleScreenEntryEvent;
import adris.altoclef.tasksystem.Task;
import adris.altoclef.tasksystem.TaskRunner;
import adris.altoclef.trackers.*;
import adris.altoclef.trackers.storage.ContainerSubTracker;
import adris.altoclef.trackers.storage.ItemStorageTracker;
import adris.altoclef.ui.CommandStatusOverlay;
import adris.altoclef.ui.MessagePriority;
import adris.altoclef.ui.MessageSender;
import adris.altoclef.util.helpers.InputHelper;
import baritone.Baritone;
import baritone.altoclef.AltoClefSettings;
import baritone.api.BaritoneAPI;
import baritone.api.Settings;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Central entry point and coordination hub for AltoClef mod.
 * 
 * This class implements the Fabric ModInitializer interface and serves as:
 * - The primary access point for mod functionality
 * - A coordinator for various subsystems (tasks, trackers, controls, etc.)
 * - A message hub for logging and communication
 * 
 * @author drmcbride12
 * @see ModInitializer
 */
public class AltoClef implements ModInitializer {

    // ========================================================================
    // Static Configuration
    // ========================================================================
    
    /** Queue for deferred post-initialization callbacks from external mods */
    private static final Queue<Consumer<AltoClef>> _postInitQueue = new ArrayDeque<>();

    // ========================================================================
    // Core Subsystems
    // ========================================================================
    
    // Central Managers
    private static CommandExecutor _commandExecutor;
    private TaskRunner _taskRunner;
    private TrackerManager _trackerManager;
    private BotBehaviour _botBehaviour;
    private PlayerExtraController _extraController;
    
    // Task chains (execution pipelines)
    private UserTaskChain _userTaskChain;
    private FoodChain _foodChain;
    private MobDefenseChain _mobDefenseChain;
    private MLGBucketFallChain _mlgBucketChain;
    
    // Trackers (state monitoring systems)
    private ItemStorageTracker _storageTracker;
    private ContainerSubTracker _containerSubTracker;
    private EntityTracker _entityTracker;
    private BlockTracker _blockTracker;
    private SimpleChunkTracker _chunkTracker;
    private MiscBlockTracker _miscBlockTracker;
    
    // UI and Rendering
    private CommandStatusOverlay _commandStatusOverlay;
    
    // Configuration and Settings
    private adris.altoclef.Settings _settings;
    
    // Input and Control
    private MessageSender _messageSender;
    private InputControls _inputControls;
    private SlotHandler _slotHandler;
    
    // Butler (multiplayer coordination)
    private Butler _butler;

    // ========================================================================
    // Public API
    // ========================================================================

    /**
     * Checks if the mod is currently running in-game (player and network handler exist)
     * 
     * @return true if in an active game session, false otherwise
     */
    public static boolean inGame() {
        return MinecraftClient.getInstance().player != null && MinecraftClient.getInstance().getNetworkHandler() != null;
    }

    /**
     * Entry point called by Fabric when the mod is loaded.
     * Subscribes to the title screen entry event for deferred initialization.
     */
    @Override
    public void onInitialize() {
        // Defer actual initialization until Minecraft is fully loaded
        EventBus.subscribe(TitleScreenEntryEvent.class, evt -> onInitializeLoad());
    }

    /**
     * Executes after Minecraft has fully loaded all resources.
     * This is the true initialization point, triggered by a mixin when the game is ready.
     */
    public void onInitializeLoad() {
        // Initialize Baritone navigation settings
        initializeBaritoneSettings();

        // Initialize core managers
        initializeCoreManagers();

        // Initialize task chains
        initializeTaskChains();

        // Initialize tracking systems
        initializeTrackers();

        // Initialize UI components
        initializeUI();

        // Initialize utility systems
        initializeUtilities();

        // Register command handlers
        initializeCommands();

        // Load and apply user settings
        loadUserSettings();

        // Setup event handlers
        setupEventHandlers();

        // Register debug reference
        Debug.jankModInstance = this;

        // Subscribe to tick and render cycles
        setupGameLoops();

        // Initialize playground/testing environment
        Playground.IDLE_TEST_INIT_FUNCTION(this);

        // Process any deferred initialization from external mods
        runEnqueuedPostInits();
    }

    /**
     * Handles the main client tick cycle, coordinating all subsystem updates.
     */
    private void onClientTick() {
        runEnqueuedPostInits();

        // Pre-tick input processing
        _inputControls.onTickPre();

        // Handle input shortcuts
        handleInputShortcuts();

        // Update tracking systems
        updateTrackers();

        // Process task execution
        executeTasks();

        // Update auxiliary systems
        updateAuxiliarySystems();

        // Post-tick input processing
        _inputControls.onTickPost();
    }

    /**
     * Renders the UI overlay for the current tick.
     */
    private void onClientRenderOverlay(MatrixStack matrixStack) {
        _commandStatusOverlay.render(this, matrixStack);
    }

    // ========================================================================
    // Initialization Methods
    // ========================================================================

    /**
     * Configures Baritone navigation settings for optimal AltoClef performance.
     */
    private void initializeBaritoneSettings() {
        // Enable inventory management and diagonal movement for better pathfinding
        getClientBaritoneSettings().allowInventory.value = true;
        getClientBaritoneSettings().allowDiagonalAscend.value = true;
        
        // Optimize rendering performance
        getClientBaritoneSettings().fadePath.value = true;
        
        // Disable redundant scanning (we handle drops ourselves)
        getClientBaritoneSettings().mineScanDroppedItems.value = false;
        getClientBaritoneSettings().mineDropLoiterDurationMSThanksLouca.value = 0L;

        // Aggressive mob avoidance for safety
        getClientBaritoneSettings().mobAvoidanceCoefficient.value = 2.0;
        getClientBaritoneSettings().mobAvoidanceRadius.value = 12;

        // Exclusive water bucket handling by AltoClef
        getExtraBaritoneSettings().configurePlaceBucketButDontFall(true);

        // Extended planning timeouts for complex paths
        getClientBaritoneSettings().failureTimeoutMS.value = 6000L;      // Was: 2000L
        getClientBaritoneSettings().planAheadFailureTimeoutMS.value = 10000L; // Was: 5000L
        getClientBaritoneSettings().movementTimeoutTicks.value = 200;    // Was: 100
    }

    /**
     * Registers all command handlers for the mod.
     */
    private void initializeCommands() {
        try {
            new AltoClefCommands();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates and initializes all core manager instances.
     */
    private void initializeCoreManagers() {
        _commandExecutor = new CommandExecutor(this);
        _taskRunner = new TaskRunner(this);
        _trackerManager = new TrackerManager(this);
        _botBehaviour = new BotBehaviour(this);
        _extraController = new PlayerExtraController(this);
    }

    /**
     * Instantiates all task chains that define mod behaviors.
     */
    private void initializeTaskChains() {
        _userTaskChain = new UserTaskChain(_taskRunner);
        _mobDefenseChain = new MobDefenseChain(_taskRunner);
        new DeathMenuChain(_taskRunner);
        new PlayerInteractionFixChain(_taskRunner);
        _mlgBucketChain = new MLGBucketFallChain(_taskRunner);
        new WorldSurvivalChain(_taskRunner);
        _foodChain = new FoodChain(_taskRunner);
    }

    /**
     * Sets up all tracking systems for monitoring game state.
     */
    private void initializeTrackers() {
        _storageTracker = new ItemStorageTracker(this, _trackerManager, container -> _containerSubTracker = container);
        _entityTracker = new EntityTracker(_trackerManager);
        _blockTracker = new BlockTracker(this, _trackerManager);
        _chunkTracker = new SimpleChunkTracker(this);
        _miscBlockTracker = new MiscBlockTracker(this);
    }

    /**
     * Initializes UI components and overlays.
     */
    private void initializeUI() {
        _commandStatusOverlay = new CommandStatusOverlay();
    }

    /**
     * Creates utility instances for messaging, input handling, and slot management.
     */
    private void initializeUtilities() {
        _messageSender = new MessageSender();
        _inputControls = new InputControls();
        _slotHandler = new SlotHandler(this);
        _butler = new Butler(this);
    }

    /**
     * Loads user settings and applies them to the mod.
     */
    private void loadUserSettings() {
        adris.altoclef.Settings.load(newSettings -> {
            _settings = newSettings;
            
            // Sync Baritone's throwaway items with our configuration
            syncBaritoneThrowawayItems(newSettings);
            
            // Configure idle command execution
            configureIdleCommand();
            
            // Set up protected block positions
            configureProtectedBlocks();
        });
    }

    /**
     * Synchronizes Baritone's acceptable throwaway items with mod settings.
     */
    private void syncBaritoneThrowawayItems(adris.altoclef.Settings settings) {
        List<Item> baritoneCanPlace = Arrays.stream(settings.getThrowawayItems(this, true))
                .filter(item -> item != Items.SOUL_SAND && item != Items.MAGMA_BLOCK)
                .collect(Collectors.toList());
        getClientBaritoneSettings().acceptableThrowawayItems.value.addAll(baritoneCanPlace);
    }

    /**
     * Configures automatic idle command execution when no tasks are active.
     */
    private void configureIdleCommand() {
        if ((!getUserTaskChain().isActive() || getUserTaskChain().isRunningIdleTask()) 
                && getModSettings().shouldRunIdleCommandWhenNotActive()) {
            getUserTaskChain().signalNextTaskToBeIdleTask();
            getCommandExecutor().executeWithPrefix(getModSettings().getIdleCommand());
        }
    }

    /**
     * Configures block protection to prevent Baritone from mining/placing in protected areas.
     */
    private void configureProtectedBlocks() {
        getExtraBaritoneSettings().avoidBlockBreak(blockPos -> _settings.isPositionExplicitelyProtected(blockPos));
        getExtraBaritoneSettings().avoidBlockPlace(blockPos -> _settings.isPositionExplicitelyProtected(blockPos));
    }

    /**
     * Registers event handlers for game events.
     */
    private void setupEventHandlers() {
        // Intercept and handle chat messages for commands
        EventBus.subscribe(SendChatEvent.class, evt -> {
            String line = evt.message;
            if (getCommandExecutor().isClientCommand(line)) {
                evt.cancel();
                getCommandExecutor().execute(line);
            }
        });

        // Subscribe to game loops
        EventBus.subscribe(ClientTickEvent.class, evt -> onClientTick());
        EventBus.subscribe(ClientRenderEvent.class, evt -> onClientRenderOverlay(evt.stack));
    }

    /**
     * Sets up the game loop subscriptions.
     */
    private void setupGameLoops() {
        // Already handled in setupEventHandlers
    }

    // ========================================================================
    // Update Methods
    // ========================================================================

    /**
     * Processes input shortcuts during tick (e.g., Ctrl+K to cancel).
     */
    private void handleInputShortcuts() {
        if (InputHelper.isKeyPressed(GLFW.GLFW_KEY_LEFT_CONTROL) && InputHelper.isKeyPressed(GLFW.GLFW_KEY_K)) {
            _userTaskChain.cancel(this);
            if (_taskRunner.getCurrentTaskChain() != null) {
                _taskRunner.getCurrentTaskChain().stop(this);
            }
        }
    }

    /**
     * Updates all tracking systems with current game state.
     */
    private void updateTrackers() {
        // TODO: Consider moving storage dirty flag logic to a dedicated update method
        _storageTracker.setDirty();
        _containerSubTracker.onServerTick();
        _miscBlockTracker.tick();
        _trackerManager.tick();
        _blockTracker.preTickTask();
        _blockTracker.postTickTask();
    }

    /**
     * Executes the task runner and auxiliary systems.
     */
    private void executeTasks() {
        _taskRunner.tick();
    }

    /**
     * Updates auxiliary systems (butler, messaging).
     */
    private void updateAuxiliarySystems() {
        _butler.tick();
        _messageSender.tick();
    }

    // ========================================================================
    // Getters and Setters (Public API)
    // ========================================================================

    /**
     * Returns the command executor for parsing and running mod commands.
     */
    public static CommandExecutor getCommandExecutor() {
        return _commandExecutor;
    }

    /**
     * Returns the task runner responsible for executing task chains.
     */
    public TaskRunner getTaskRunner() {
        return _taskRunner;
    }

    /**
     * Returns the user task chain that executes player-initiated commands.
     */
    public UserTaskChain getUserTaskChain() {
        return _userTaskChain;
    }

    /**
     * Returns the bot behaviour controller for temporary block/item protection.
     */
    public BotBehaviour getBehaviour() {
        return _botBehaviour;
    }

    /**
     * Returns the item storage tracker for inventory and container management.
     */
    public ItemStorageTracker getItemStorage() {
        return _storageTracker;
    }

    /**
     * Returns the entity tracker for monitoring nearby entities.
     */
    public EntityTracker getEntityTracker() {
        return _entityTracker;
    }

    /**
     * Returns the block tracker for monitoring block positions and states.
     */
    public BlockTracker getBlockTracker() {
        return _blockTracker;
    }

    /**
     * Returns the container sub-tracker for internal container state management.
     */
    ContainerSubTracker getContainerSubTracker() {
        return _containerSubTracker;
    }

    /**
     * Returns the chunk tracker for monitoring loaded chunk status.
     */
    public SimpleChunkTracker getChunkTracker() {
        return _chunkTracker;
    }

    /**
     * Returns the miscellaneous block tracker for special block states (e.g., nether portals).
     */
    public MiscBlockTracker getMiscBlockTracker() {
        return _miscBlockTracker;
    }

    /**
     * Returns the Baritone instance for navigation control.
     */
    public Baritone getClientBaritone() {
        if (getPlayer() == null) {
            return (Baritone) BaritoneAPI.getProvider().getPrimaryBaritone();
        }
        return (Baritone) BaritoneAPI.getProvider().getBaritoneForPlayer(getPlayer());
    }

    /**
     * Returns Baritone's settings object.
     */
    public Settings getClientBaritoneSettings() {
        return Baritone.settings();
    }

    /**
     * Returns AltoClef-specific Baritone settings.
     */
    public AltoClefSettings getExtraBaritoneSettings() {
        return AltoClefSettings.getInstance();
    }

    /**
     * Returns the mod's configuration settings.
     */
    public adris.altoclef.Settings getModSettings() {
        return _settings;
    }

    /**
     * Returns the Butler controller for multiplayer coordination.
     */
    public Butler getButler() {
        return _butler;
    }

    /**
     * Returns the message sender for chat communication.
     */
    public MessageSender getMessageSender() {
        return _messageSender;
    }

    /**
     * Returns the slot handler for inventory and container operations.
     */
    public SlotHandler getSlotHandler() {
        return _slotHandler;
    }

    /**
     * Returns the current Minecraft player entity.
     */
    public ClientPlayerEntity getPlayer() {
        return MinecraftClient.getInstance().player;
    }

    /**
     * Returns the current Minecraft world.
     */
    public ClientWorld getWorld() {
        return MinecraftClient.getInstance().world;
    }

    /**
     * Returns the Minecraft client interaction manager.
     */
    public ClientPlayerInteractionManager getController() {
        return MinecraftClient.getInstance().interactionManager;
    }

    /**
     * Returns the player extra controller for advanced control operations.
     */
    public PlayerExtraController getControllerExtras() {
        return _extraController;
    }

    /**
     * Returns the input controls manager for manual input simulation.
     */
    public InputControls getInputControls() {
        return _inputControls;
    }

    // ========================================================================
    // Task Execution API
    // ========================================================================

    /**
     * Executes a user-defined task.
     * 
     * @param task The task to execute
     */
    public void runUserTask(Task task) {
        runUserTask(task, () -> { });
    }

    /**
     * Executes a user-defined task with a completion callback.
     * 
     * @param task The task to execute
     * @param onFinish Callback executed when the task completes
     */
    public void runUserTask(Task task, Runnable onFinish) {
        _userTaskChain.runTask(this, task, onFinish);
    }

    /**
     * Cancels the currently running user task.
     */
    public void cancelUserTask() {
        _userTaskChain.cancel(this);
    }

    /**
     * Returns the food chain that temporarily takes control for eating.
     */
    public FoodChain getFoodChain() {
        return _foodChain;
    }

    /**
     * Returns the mob defense chain that temporarily takes control for combat.
     */
    public MobDefenseChain getMobDefenseChain() {
        return _mobDefenseChain;
    }

    /**
     * Returns the MLG bucket chain that temporarily takes control for falling saves.
     */
    public MLGBucketFallChain getMLGBucketChain() {
        return _mlgBucketChain;
    }

    // ========================================================================
    // Logging API
    // ========================================================================

    /**
     * Logs a message with default priority.
     * 
     * @param message The message to log
     */
    public void log(String message) {
        log(message, MessagePriority.TIMELY);
    }

    /**
     * Logs a message to console and Butler clients.
     * 
     * @param message The message to log
     * @param priority The priority level for the message
     */
    public void log(String message, MessagePriority priority) {
        Debug.logMessage(message);
        _butler.onLog(message, priority);
    }

    /**
     * Logs a warning message with default priority.
     * 
     * @param message The warning message
     */
    public void logWarning(String message) {
        logWarning(message, MessagePriority.TIMELY);
    }

    /**
     * Logs a warning message to console and alerts Butler clients.
     * 
     * @param message The warning message
     * @param priority The priority level for the message
     */
    public void logWarning(String message, MessagePriority priority) {
        Debug.logWarning(message);
        _butler.onLogWarning(message, priority);
    }

    // ========================================================================
    // Internal Methods
    // ========================================================================

    /**
     * Processes any queued post-initialization callbacks from external mods.
     */
    private void runEnqueuedPostInits() {
        synchronized (_postInitQueue) {
            while (!_postInitQueue.isEmpty()) {
                _postInitQueue.poll().accept(this);
            }
        }
    }

    /**
     * Allows external mods to subscribe to initialization events.
     * 
     * @param onPostInit Callback to execute after AltoClef initialization
     */
    public static void subscribeToPostInit(Consumer<AltoClef> onPostInit) {
        synchronized (_postInitQueue) {
            _postInitQueue.add(onPostInit);
        }
    }
}
