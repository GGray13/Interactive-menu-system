public class TicketOffice implements Comparable<TicketOffice> {

    private Activity activity;
    private int ticketsBought;

    public TicketOffice(Activity activity, int ticketsBought) {
    }

    //Getters and setters
    public Activity getActivity()
    {
        return activity;
    }
    public int getTicketsBought()
    {
        return ticketsBought;
    }
    public void setTicketsBought(int i)
    {
        ticketsBought = i;
    }


    public int compareTo(TicketOffice t) {
        int activityOrder = activity.compareTo(t.getActivity());
        if (activityOrder != 0)
            return activityOrder;
        return 0;
    }


    @Override
    public String toString() {
        return ticketsBought + "tickets bought for " + activity;
    }
}
