package theater;

/**
 * Represents a single performance of a play.
 *
 * @null This tag is required by the CheckStyle configuration.
 */
public class Performance {

    private final String playID;
    private final int audience;

    /**
     * Creates a performance with a play ID and the number of audience members.
     *
     * @param playID   the ID of the play performed
     * @param audience the number of seats filled for this performance
     */
    public Performance(String playID, int audience) {
        this.playID = playID;
        this.audience = audience;
    }

    public String getPlayID() {
        return playID;
    }

    public int getAudience() {
        return audience;
    }
}

