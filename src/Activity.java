import java.io.*;

public class Activity implements Comparable<Activity>
{
    private String activityName;
    private int ticketsLeft;

    public Activity(String activityName, int ticketsLeft)
    {
        this.activityName = activityName;
        this.ticketsLeft = ticketsLeft;
    }

    public int compareTo(Activity a)
    {
        int compActivity = activityName.compareTo(a.activityName);
        return compActivity;
    }
    public String GetActivityName()
    {
        return activityName;
    }
    public int GetTicketsLeft() {
        return ticketsLeft;
    }
    public void reduceAvailableTickets(int n)
    {
        ticketsLeft = ticketsLeft - n;
    }
    public void IncreaseTickets(int n)
    {
        this.ticketsLeft = ticketsLeft + n;
    }

    @Override
    public String toString()
    {
        return activityName;
    }
}
