package adris.altoclef.commandsystem;

public class ArgParser {
    private final String[] args;
    private int currentIndex = 0;

    public ArgParser(String[] args) {
        this.args = args;
    }

    public String getNextArg() {
        if (currentIndex < args.length) {
            return args[currentIndex++];
        }
        return null;
    }

    public String getArg(int index) {
        if (index >= 0 && index < args.length) {
            return args[index];
        }
        return null;
    }

    public int getRemainingArgs() {
        return args.length - currentIndex;
    }

    public boolean hasMoreArgs() {
        return currentIndex < args.length;
    }
}
