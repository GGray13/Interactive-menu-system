import java.io.*;

public class Activity implements Comparable<Activity>
{
    private final String activityName;
    private int ticketsLeft;

    public Activity(String activityName, int ticketsLeft)
    {
        this.activityName = activityName;
        this.ticketsLeft = ticketsLeft;
    }

    public int compareTo(Activity a)
    {
        return activityName.compareTo(a.activityName);
    }
    public String getActivityName()
    {
        return activityName;
    }
    public int getTicketsLeft() {
        return ticketsLeft;
    }
    public void reduceAvailableTickets(int n)
    {
        ticketsLeft = ticketsLeft - n;
    }
    public void increaseTickets(int n)
    {
        this.ticketsLeft = ticketsLeft + n;
    }

    @Override
    public String toString()
    {
        return activityName;
    }
}
