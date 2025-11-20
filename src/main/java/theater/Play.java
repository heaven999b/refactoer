package theater;

/**
 * Represents a play in the theater system.
 *
 * @null This tag is required by the CheckStyle configuration.
 */
public class Play {

    private final String name;
    private final String type;

    /**
     * Constructs a play with a name and type.
     *
     * @param name the name of the play
     * @param type the type of the play (e.g., tragedy or comedy)
     */
    public Play(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
