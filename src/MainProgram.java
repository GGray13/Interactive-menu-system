import java.io.*;
import java.util.Scanner;

/* Inspired by Konrads work, lecture 1 slides 31 -49 */

public class MainProgram {
    SortedArrayList<Activity> activityList = new SortedArrayList<>();
    SortedArrayList<Customer> customerList = new SortedArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {
        new MainProgram().setup();
    }

    private void setup() throws FileNotFoundException {

        PrintWriter outFile = new PrintWriter("src//letters.txt");

        try {
            readFile();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        //Switch statement
        boolean done = false;
        while (!done) {
            switch (printMenu()) {
                case "f":
                    done = true;
                    break;
                case "a":
                    activityInfo();

            }
        }
    }
//Reads in data from input file
    private void readFile() throws IOException {
        Scanner inFile = new Scanner(new FileReader("src//input.txt"));

        //Reads in total number of activities, creates activity name and adds event capacity
        int totalActivities = Integer.parseInt(inFile.nextLine());
        for (int i = 0; i < totalActivities; i++) {
            String activityName = inFile.nextLine();
            int activityCapacity = Integer.parseInt(inFile.nextLine());
            Activity e = new Activity(activityName, activityCapacity);
            activityList.add(e);
        }

        //Reads in total number of customers and customer details
        int totalCustomers = Integer.parseInt(inFile.nextLine());
        for (int i = 0; i < totalCustomers; i++)
        {
            String[] elements = inFile.nextLine().split(" ");
            Customer c = new Customer(elements[0],elements[1]);
            customerList.add(c);
        }

    }

    //Print menu
    private String printMenu()
    {
        Scanner in = new Scanner(System.in);

        System.out.println("---------------------------------------");
        System.out.println("HOLIDAY RESORT MENU");
        System.out.println("f - to finish running the program.");
        System.out.println("a - to display on the screen information about all the activities.");
        System.out.println("c - to display on the screen information about all the customers.");
        System.out.println("t - to update the stored data when tickets are bought by one of the registered customers.");
        System.out.println("r - to update the stored data when a registered customer cancels tickets for a booking.");
        System.out.println("---------------------------------------");
        System.out.println("Type a letter and press enter");
        return in.nextLine();
    }
}
