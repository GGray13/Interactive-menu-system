/*
**Class purpose is to store tickets that are purchased for each activity
*/

public class TicketOffice implements Comparable<TicketOffice> {

    private Activity activity;
    private int ticketsBought;

    public TicketOffice(Activity activity, int ticketsBought)
    {
        this.activity = activity;
        this.ticketsBought = ticketsBought;

    }

    @Override
    public String toString()
    {
        return ticketsBought + "tickets bought for " + activity;
    }

    public int compareTo(TicketOffice t)
    {
        int activityOrder = activity.compareTo(t.getActivity());
        if (activityOrder != 0)
            return activityOrder;
        return 0;
    }

    //Getters and setters
    public Activity getActivity()
    {
        return this.activity;
    }

    public int getTicketsBought()
    {
        return this.ticketsBought;
    }

    public void setTicketsBought(int i)
    {
        this.ticketsBought = i;
    }
}

