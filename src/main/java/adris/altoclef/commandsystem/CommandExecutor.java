package adris.altoclef.commandsystem;

public class CommandExecutor {
    private final Command command;
    private final ArgParser parser;

    public CommandExecutor(Command command, String[] args) {
        this.command = command;
        this.parser = new ArgParser(args);
    }

    public void execute() {
        try {
            command.execute(parser);
        } catch (CommandException e) {
            AltoClef.LOGGER.error("Command error: {}", e.getMessage());
        }
    }
}
