import javax.accessibility.AccessibleAction;
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
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Switch statement
        boolean done = false;
        while (!done) {
            switch (printMenu()) {
                case "f":
                    System.out.println("Program closing... Bye!");
                    done = true;
                    break;
                case "a":
                    activityInfo();
                    break;
                case "c":
                    customerInfo();
                    break;
                case "t":  //ticket purchasing methods
                    Customer customer1 = matchCustomerName(); //Matches name with input file
                    if (customer1 != null) {
                        Activity activityPicked = matchActivity(); //Matches activity name with input file
                        if (activityPicked != null) {
                            int pickedTickets = ticketQuantity(outFile, customer1, activityPicked);
                            if (pickedTickets != -1) {
                                buyTickets(customer1, activityPicked, pickedTickets);
                            }
                        }
                    }
                    break;
                case "r": //Return tickets purchased methods
                    Customer customer2 = matchCustomerName();
                    if (customer2 != null)
                    {
                        Activity activityPicked2 = ticketsBoughtAlready(customer2);
                        if (activityPicked2 != null)
                        {

                        }

                    }


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
            Activity a = new Activity(activityName, activityCapacity);
            activityList.add(a);
        }

        //Reads in total number of customers and customer details
        int totalCustomers = Integer.parseInt(inFile.nextLine());
        for (int i = 0; i < totalCustomers; i++) {
            String[] elements = inFile.nextLine().split(" ");
            Customer c = new Customer(elements[0], elements[1]);
            customerList.add(c);
        }

    }

    //Prints menu
    private String printMenu() {
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

    //Prints activity information
    private void activityInfo() {
        System.out.println("See information about all available activities below:");

        for (Activity activity : activityList) {
            System.out.printf("Activity= %s. " + "Available tickets: %s%n",
                    activity.toString(), activity.getTicketsLeft());
        }
    }

    //Prints customers information
    private void customerInfo() {
        System.out.println("\nHere is all the information about customers and what " +
                "tickets they have bought.");

        for (Customer customer : customerList) {
            System.out.print(customer.toString() + ", ");
            if (customer.getTicketsBought().size() > 0) {
                for (int i = 0; i < customer.getTicketsBought().size(); i++) {
                    System.out.println(customer.getTicketsBought().get(i).toString() + ".");
                }
            } else {
                System.out.println("no ticket purchases available.");
            }
        }
    }

    //Matches customer name and makes sure it is valid
    public Customer matchCustomerName() {
        Scanner in = new Scanner(System.in); //New scanner to take user input of name
        Customer purchasingCustomer = null; //New purchasing customer set up

        boolean valid = false;
        while (!valid) {
            try {
                System.out.println("Input purchasing customer name here.  To quit, enter 'f'");
                String input = in.nextLine();
                if (input.equals("f")) {
                    valid = true;
                } else {
                    String[] names = input.split(" ");
                    Customer customerInput = new Customer(names[0], names[1]);
                    for (int i = 0; i < customerList.size(); i++) {
                        if (customerInput.compareTo(customerList.get(i)) == 0) {
                            purchasingCustomer = customerList.get(i);
                        }
                    }
                    if (purchasingCustomer != null) {
                        System.out.println("Customer found on system.");
                        valid = true;
                    } else {
                        System.out.println("Customer not found. Please input the first and second name of customer.");
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Please input the first and second name of customer.");
            }
        }
        return purchasingCustomer;
    }

    // Matches activity name with input file, follows similar method to storing customer name
    public Activity matchActivity() {
        Scanner in = new Scanner(System.in);// new scanner to take user input

        Activity activityPicked = null; //Return null if ticket not purchased

        boolean valid = false;
        while (!valid) {
            System.out.println("Select the event you would like to buy.  To quit, enter 'f'");

            String input = in.nextLine();

            if (input.equals("f")) {
                valid = true;
            } else {
                for (Activity a : activityList) {
                    if (a.getActivityName().equals(input)) // Returns activity selected if input...
                    {                                      //...matches the correct activity name
                        activityPicked = a;
                        valid = true;
                        System.out.println("Event correctly selected.");
                    }
                }
            }
            if (activityPicked == null) {
                System.out.println("Activity does not match. Please enter correct activity name");
            }
        }
        return activityPicked;
    }

    public int ticketQuantity(PrintWriter outFile, Customer purchasingCustomer, Activity activityPicked) {
        Scanner in = new Scanner(System.in);

        int ticketQuantity = -1;

        boolean valid = false;
        while (!valid) {
            try {
                System.out.println("How many tickets would you like to buy for " + activityPicked.getActivityName() +
                        "?  Or enter 'f' to quit purchasing tickets");

                String input = in.nextLine();

                if (input.equals("f")) {
                    valid = true;
                } else {
                    ticketQuantity = Integer.parseInt(input);
                }
                if (activityPicked.getTicketsLeft() >= ticketQuantity) {
                    valid = true;
                } else {
                    System.out.println("Sorry there are not enough ticket left for " +
                            activityPicked.getActivityName());
                    System.out.println("A letter has been printed informing the customer that there are " +
                            "no tickets available.");
                    printLetter(outFile, purchasingCustomer, activityPicked);
                    System.out.println("Sorry, please try again!");
                    ticketQuantity = -1;

                }
            } catch (NumberFormatException e) {
                System.out.println("Please type in the correct format");
            }
        }
        return ticketQuantity;
    }


    private void printLetter(PrintWriter outFile, Customer c, Activity a) {
        outFile.println("Dear customer, apologies we do not have any tickets left" +
                "for your chosen activity.  Please choose another activity. Sorry!");
    }


    private void buyTickets(Customer c, Activity a, int n) {
        for (int i = 0; i < c.getTicketsBought().size(); i++) {
            if (c.getTicketsBought().get(i).getActivity().equals(a)) {
                //increase the customer tickets bought
                c.getTicketsBought().get(i).setTicketsBought(c.getTicketsBought().get(i).getTicketsBought() + n);

                //Reduce the amount of tickets left for activity
                a.reduceAvailableTickets(n);
                System.out.println("Complete!  Customer has bought tickets for the chosen activity");
                return;
            }
        }
        // print error if customer is holding more than 3 tickets
        if (c.getTicketsBought().size() == 3) {
            System.out.println("Customer has reached the maximum of 3 tickets per customer");
        }
        else //reduce available tickets and confirm tickets are purchased.
        {
            a.reduceAvailableTickets(n);
            TicketOffice ticketsBought = new TicketOffice(a, n);
            c.addTicketsBought(ticketsBought);
            System.out.println("Ticket confirmation.  You have booked tickets for your chosen activity!");
        }
    }

    public Activity ticketsBoughtAlready (Customer c)
    {
        Scanner in = new Scanner(System.in);
        if (c.getTicketsBought().size() == 0)
        {
            System.out.println("Customer has not bought any tickets");
            return null;
        }

        Activity activityPicked = null;
        boolean valid = false;
        while(!valid)
        {
            System.out.println(c + " has tickets for: ");
            for (int i = 0; i < c.getTicketsBought().size(); i++)
            {
                System.out.println(c.getTicketsBought().get(i).getActivity().toString() + " " +
                        c.getTicketsBought().get(i).getTicketsBought() + " tickets bought" );
            }

            System.out.println("Enter the name of the activity you would like to return the ticket for." +
                    "Enter 'f' to exit returns");

            String input = in.nextLine();
            if (input.equals("f"))
            {
                return null;
            }
            else
            {
                for (int i = 0; i < c.getTicketsBought().size(); i++)
                {
                    if (c.getTicketsBought().get(i).getActivity().toString().equals(input))
                    {
                        activityPicked = c.getTicketsBought().get(i).getActivity();
                        valid = true;
                    }
                }
            }

            if (activityPicked == null)
            {
                for (Activity a : activityList)
                {
                    if (a.getActivityName().equals(input))
                    {
                        System.out.println("Customer does not have any activity tickets");
                        break;
                    }
                }
                System.out.println("Input is incorrect, please enter correct format.");
            }
        }
        return activityPicked;
    }

    //Updates the Customer and activity when tickets are returned
    private void returnTickets(Customer c, Activity a)
    {
        Scanner in = new Scanner(System.in);

        int activityIndex = -1;

        for (int i = 0; i < c.getTicketsBought().size(); i++)
        {
            if (c.getTicketsBought().get(i).getActivity().equals(a))
            {
                activityIndex = i;
            }
        }
        if (activityIndex == -1)
        {
            System.out.println("No purchased tickets found for the selected event");
            return;
        }

        int ticketsBought = c.getTicketsBought().get(activityIndex).getTicketsBought();

        boolean valid = false;
        while (!valid)
        {
            try {
                System.out.println("Enter the amount of activity tickets you would you like to return: " +
                        "or enter 'f' to quit returns");

                String input = in.nextLine();
                if (input.equals("f"))
                {
                    valid = true;
                }
                else
                {
                    int ticketsReturned = Integer.parseInt(input);
                    if (ticketsReturned == ticketsBought)
                    {
                        c.getTicketsBought().remove(activityIndex);
                        a.increaseTickets(ticketsReturned);
                        System.out.println("Activity tickets have been successfully returned");
                        valid = true;
                    } else if (ticketsReturned >= 1 && ticketsReturned<ticketsBought)
                    {
                           c.getTicketsBought().get(activityIndex).setTicketsBought(ticketsBought - ticketsReturned);
                           a.increaseTickets(ticketsReturned);
                           System.out.println("Activity tickets have been successfully returned");
                           valid = true;
                    }
                    else
                        System.out.println("Please enter the correct amount of activity tickets to return");
                }
            }
            catch (NumberFormatException e)
            {
                System.out.println("Please make sure the format is entered correctly.");
            }
        }
    }

}










