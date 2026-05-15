package adris.altoclef.commandsystem;

public abstract class Command {
    protected final String name;
    protected final String[] aliases;

    public Command(String name, String... aliases) {
        this.name = name;
        this.aliases = aliases;
    }

    public String getName() {
        return name;
    }

    public String[] getAliases() {
        return aliases;
    }

    public abstract void execute(ArgParser parser);
}
