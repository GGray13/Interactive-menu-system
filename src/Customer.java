import java.util.ArrayList;

/**
 * Purpose of this class is to store information on customers and their tickets bought
 *
 *  Reference:
 *              Methods inspired by Dr Konrad Dabrowski's CSC8012 lecture slides.
 */
public class Customer implements Comparable<Customer> {
    private final String firstName;

    private final String lastName;

    private final SortedArrayList<TicketOffice> ticketsArray;

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ticketsArray = new SortedArrayList<>();
    }

    public void addTicketsBought(TicketOffice ticketsBought) {
        this.ticketsArray.add(ticketsBought);
    }

    public ArrayList<TicketOffice> getTicketsBought()
    {
        return this.ticketsArray;
    }

    @Override
    public String toString()
    {
        return firstName + " " + lastName;
    }

    @Override
    public int compareTo(Customer c) {
        int fnCmp = firstName.compareTo(c.firstName);
        if (fnCmp != 0) return fnCmp;
        int lnCmp = lastName.compareTo(c.lastName);
        if (lnCmp != 0) return lnCmp;
        else return 0;
    }
}
