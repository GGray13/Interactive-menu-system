public class Customer implements Comparable<Customer>
{
    private String firstName;

    private String lastName;

    private SortedArrayList<TicketOffice> ticketsArray;



    private String activityBooked;

    private int ticketsBought;

    public Customer(String firstName, String lastName)
    {
        firstName = firstName;
        lastName = lastName;
        activityBooked = activityBooked;
        ticketsBought = ticketsBought;
    }

    @Override
    public int compareTo(Customer c)
    {
        int fnCmp = firstName.compareTo(c.firstName);
        if (fnCmp !=0) return fnCmp;
        int lnCmp = lastName.compareTo(c.lastName);
        if (lnCmp !=0) return lnCmp;
        else return 0;
    }

    public String GetCustomerName()
    {
        return firstName + " " + lastName;
    }

    public String GetActivityName()
    {
        return activityBooked;
    }
}
