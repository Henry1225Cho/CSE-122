import java.util.*;

// Yunho Cho
// TA: Ben Wang
// CSE 122 AF
// June 29 2023
// Census
// This class is Census. It records all of the people's age and then find
// an average of the system we recorded of people's age to conclude.
public class Census {
    public static void main(String[] args) {
        Scanner console = new Scanner (System.in);
        System.out.println("Welcome to the Census!");
        System.out.println("Input the ages of the population and " + 
                           "we will compute the average age");
        findAverage(console);
    }

    // Calculates total people's age and then total people to find an average
    // also only calculates average as long input system is greater than 0.
    // no exception
    // no return
    // Parameters:
    //  - console: the user put in each people's age in the system
    public static void findAverage(Scanner console) { 
        int totalPeople = 0;
        int totalAge = 0;
        double avg = 0.0;
        int age;
        do {
            System.out.print("Next person's age (negative to quit)? ");
            age = console.nextInt();
                if(age >= 0) {
                    totalAge += age;
                    totalPeople++;
                }
        }
        while(age >= 0);
            avg = (double) totalAge/totalPeople;
            System.out.println("The average age is " + avg);
    }
}
