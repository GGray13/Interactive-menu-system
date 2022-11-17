/* Activity class to store the name of the activity and the number of
tickets left for the activity.
 */

public class Activity implements Comparable<Activity>
{
    private final String activityName;
    private int ticketsLeft;

    public Activity(String activityName, int ticketsLeft)
    {
        this.activityName = activityName;
        this.ticketsLeft = ticketsLeft;
    }

    /* Returns activities in order using the sortedArraylist */
    public int compareTo(Activity a)
    {
        return activityName.compareTo(a.activityName);
    }

    /** Getter and setters **/
    public String getActivityName()
    {
        return activityName;
    }
    public int getTicketsLeft() {
        return ticketsLeft;
    }
    public void reduceAvailableTickets(int n) //pass in n from main program
    {
        ticketsLeft = ticketsLeft - n; //decreases tickets by n which is how many the customer buys
    }
    public void increaseTickets(int n)
    {
        this.ticketsLeft = ticketsLeft + n; //increases tickets by n which is how many the customer returns
    }

    /* Only print the Activity name when activity is called */
    @Override
    public String toString()
    {
        return activityName;
    }
}
