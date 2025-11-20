package theater;

import java.util.List;

/**
 * Class representing an invoice for a customer.
 *
 * @null This tag is required by the CheckStyle configuration.
 */
public class Invoice {

    private final String customer;
    private final List<Performance> performances;

    /**
     * Creates an invoice with the customer name and a list of performances.
     *
     * @param customer     the name of the customer
     * @param performances the list of performances in this invoice
     */
    public Invoice(String customer, List<Performance> performances) {
        this.customer = customer;
        this.performances = performances;
    }

    public String getCustomer() {
        return customer;
    }

    public List<Performance> getPerformances() {
        return performances;
    }
}
