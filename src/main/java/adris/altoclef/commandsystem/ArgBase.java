package adris.altoclef.commandsystem;

public class ArgBase {
    protected final String name;
    protected final boolean required;
    protected final String description;

    public ArgBase(String name, boolean required, String description) {
        this.name = name;
        this.required = required;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public boolean isRequired() {
        return required;
    }

    public String getDescription() {
        return description;
    }
}
