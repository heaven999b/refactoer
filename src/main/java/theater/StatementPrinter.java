package theater;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

/**
 * This class generates a statement for a given invoice of performances.
 */
public class StatementPrinter {
    public Invoice invoice;
    public Map<String, Play> plays;

    public StatementPrinter(Invoice invoice, Map<String, Play> plays) {
        this.invoice = invoice;
        this.plays = plays;
    }

    /**
     * Returns a formatted statement of the invoice associated with this printer.
     *
     * @return the formatted statement
     * @throws RuntimeException if one of the play types is not known
     */
    public String statement() {
        int totalAmount = getTotalAmount();
        int volumeCredits = getTotalVolumeCredits();

        StringBuilder result =
                new StringBuilder("Statement for " + invoice.getCustomer() + System.lineSeparator());

        for (Performance performance : invoice.getPerformances()) {
            Play play = getPlay(performance);
            result.append(String.format(
                    "  %s: %s (%s seats)%n",
                    play.name,
                    usd(getAmount(performance)),
                    performance.audience
            ));
        }

        result.append(String.format("Amount owed is %s%n", usd(totalAmount)));
        result.append(String.format("You earned %s credits%n", volumeCredits));
        return result.toString();
    }

    private Play getPlay(Performance performance) {
        return plays.get(performance.playID);
    }

    private int getAmount(Performance performance) {
        Play play = getPlay(performance);

        int result = 0;
        switch (play.type) {
            case "tragedy":
                result = 40000;
                if (performance.audience > Constants.TRAGEDY_AUDIENCE_THRESHOLD) {
                    result += 1000 * (performance.audience - 30);
                }
                break;
            case "comedy":
                result = Constants.COMEDY_BASE_AMOUNT;
                if (performance.audience > Constants.COMEDY_AUDIENCE_THRESHOLD) {
                    result += Constants.COMEDY_OVER_BASE_CAPACITY_AMOUNT
                            + (Constants.COMEDY_OVER_BASE_CAPACITY_PER_PERSON
                            * (performance.audience - Constants.COMEDY_AUDIENCE_THRESHOLD));
                }
                result += Constants.COMEDY_AMOUNT_PER_AUDIENCE * performance.audience;
                break;
            default:
                throw new RuntimeException(String.format("unknown type: %s", play.type));
        }
        return result;
    }

    private int getVolumeCredits(Performance performance) {
        int result = 0;

        result += Math.max(
                performance.audience - Constants.BASE_VOLUME_CREDIT_THRESHOLD,
                0);

        if ("comedy".equals(getPlay(performance).type)) {
            result += performance.audience / Constants.COMEDY_EXTRA_VOLUME_FACTOR;
        }

        return result;
    }

    private int getTotalVolumeCredits() {
        int result = 0;
        for (Performance performance : invoice.getPerformances()) {
            result += getVolumeCredits(performance);
        }
        return result;
    }

    private int getTotalAmount() {
        int result = 0;
        for (Performance performance : invoice.getPerformances()) {
            result += getAmount(performance);
        }
        return result;
    }

    private String usd(int amount) {
        return NumberFormat
                .getCurrencyInstance(Locale.US)
                .format(amount / (double) Constants.PERCENT_FACTOR);
    }
}

