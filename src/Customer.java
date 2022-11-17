import java.util.ArrayList;

/**
 * Purpose of this class is to store information on customers and their tickets bought
 *  Reference:
 *             Code inspired by Dr Konrad Dabrowski's CSC8012 lecture slides. Edited by
 *              Gregor Gray (myself) to fit the programs needs.
 */
public class Customer implements Comparable<Customer> {
    private final String firstName; //final as assuming names cant be changed and data lost after using program
    private final String lastName;

    private final SortedArrayList<TicketOffice> ticketsArray; //new tickets array

    public Customer(String firstName, String lastName)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ticketsArray = new SortedArrayList<>();
    }

    public void addTicketsBought(TicketOffice ticketsBought)
    {
        this.ticketsArray.add(ticketsBought);
    }

    public ArrayList<TicketOffice> getTicketsBought()// second array list for tickets
    {
        return this.ticketsArray;
    }

    @Override
    public String toString()
    {
        return firstName + " " + lastName;
    }

    @Override
    public int compareTo(Customer c)
    {
        int fnCmp = firstName.compareTo(c.firstName);
        if (fnCmp != 0) return fnCmp;
        int lnCmp = lastName.compareTo(c.lastName);
        if (lnCmp != 0) return lnCmp;
        else return 0;
    }
}
