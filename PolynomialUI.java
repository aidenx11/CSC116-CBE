import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The PolynomialUI class runs the polynomial application. Uses the Polynomial class.
 * Contains the main method.
 * @author Aiden Schroeder
 */
public class PolynomialUI {

    /** Polynomial to keep track of the user's polynomial throughout the program */
    private Polynomial p = new Polynomial();

    /** Lower bound for p to be evaluated from, set by user in setRange() */
    private double lowerBound = 0;

    /** Upper bound for p to be evaluated to, set by user in setRange() */
    private double upperBound = 0;

    /** The number of intervals in the range, set by user in setRange() */
    private int numberOfIntervals = 0;

    /**
     * Main method. Executes the program by creating a new PolynomialUI
     * @param args command line arguments
     */
    public static void main(String[] args) {
        PolynomialUI p = new PolynomialUI();
    }

    /**
     * Default constructor for the PolynomialUI class. Runs the UI.
     */
    public PolynomialUI() {
        runPolynomial();
    }

    /**
     * Runs the program itself. Always runs setPolynomial() and printPolynomial() only once.
     * Wraps the rest of the program (setting of range and table) in a do-while loop.
     * This loop repeats until the user decides they no longer want to redefine their table.
     */
    public void runPolynomial() {

        //Define variables for use in later loops
        boolean wantsToRedefine = false;
        String choice = " ";

        Scanner input = new Scanner(System.in);

        //Have user set their polynomial and print it to them
            setPolynomial();
            printPolynomial();

        //Loop to reset the range and reprint the table if the user decides to
        do {

            wantsToRedefine = false;
            choice = " ";
            setRange();
            printTable();

            //Loop to check if user would like to redefine the table
            while (!choice.equals("Y")) {
                System.out.println("Would you like to redefine your table? (Y/N)");
                choice = input.next();

                if (choice.equals("Y")) {
                    wantsToRedefine = true;
                } else if (choice.equals("N")) {
                    System.out.println("Exiting Program...");
                    System.exit(0);
                } else {
                    System.out.println("Invalid input. Please enter only \"Y\" or \"N\" ");
                }
            } // interior while loop
        } while (wantsToRedefine); //exterior do-while loop
    }

    /**
     * Prompts the user to enter their polynomial in increasing powers.
     * Stores user input in the instance variable p, which is an instance of the Polynomial class.
     * Input ends when user enters a blank line.
     */
    public void setPolynomial() {

        Scanner input = new Scanner(System.in);
        System.out.println("Type in the polynomials in increasing powers:");
        String line;

        //Adds coefficients as long as the next line is not empty

            while (!(line = input.nextLine()).isEmpty()) {

                try {
                    this.p.addCoefficient(Double.parseDouble(line));
                } catch(NumberFormatException ex) {
                    System.out.println("Invalid input, please try again.");
                }
            }
    }

    /**
     * Prints the current polynomial to system out
     * using the Polynomial class's toString() method.
     */
    public void printPolynomial() {
        System.out.println(this.p.toString());
    }

    /**
     * Prompts user for lower and upper bounds, as well as the number of intervals.
     * If input is invalid, user is notified and re-prompted.
     */
    public void setRange() {

        //Set bounds to zero in case setRange() is used to redefine bounds
        this.lowerBound = 0;
        this.upperBound = 0;
        this.numberOfIntervals = 0;
        boolean validInput = false;
        boolean validBounds = false;

        Scanner input = new Scanner(System.in);
        System.out.println("Type in the range: ");

        //Loop to verify the bounds are valid (lower < upper)
        while (!validBounds) {

            validInput = false;
            //Loop to verify user enters a double value
            while (!validInput) {

                System.out.print("Lower Bound (double): ");
                if (input.hasNextDouble()) {
                    lowerBound = input.nextDouble();
                    validInput = true;
                } else {
                    System.out.println("Invalid input. Please try again.");
                }
            }

            validInput = false;
            //Loop to verify user enters a double value
            while (!validInput) {

                System.out.print("Upper bound (double): ");
                if (input.hasNextDouble()) {
                    upperBound = input.nextDouble();
                    validInput = true;
                } else {
                    System.out.println("Invalid input. Please try again.");
                }
            }

            //Checks if bounds are valid, allows loop to exit if so
            if (upperBound > lowerBound) {
                validBounds = true;
            } else {
                System.out.println("Invalid Bounds. Ensure that your lower bound is not higher than the upper bound.");
            }
        }

        validInput = false;
        //Loop to verify user enters an integer value
        while (!validInput) {

                System.out.print("Number of Intervals (int): ");

                //Outer if statement verifies integer, nested if verifies that interval > 0
                if (input.hasNextInt()) {
                    numberOfIntervals = input.nextInt();
                    if (numberOfIntervals > 0) {
                        validInput = true;
                    } else {
                        System.out.println("Invalid input. Please try again.");
                    }
                } else {
                    System.out.println("Invalid input. Please try again.");
                }
        }
    }

    /**
     * Prints a table of all index values along with their p(x) values from the polynomial,
     * and the difference between the previous p(x) and the current one.
     * Determines location of roots using the Polynomial class's findRoot() method with the
     * user given lower and upper bound.
     * Uses a switch statement to determine where to print the location of the root to system out.
     * Root location is printed after its respective index, or between the indices if it is between two.
     */
    public void printTable() {

        //Print table header
        System.out.printf("%8s", "index");
        System.out.printf("%10s", "p(index)");
        System.out.printf("%13s %n",  "diff(index)");

        //Determine where roots are located
        int locationOfRoots = p.findRoot(lowerBound, upperBound);
        double interval = (upperBound - lowerBound) / numberOfIntervals;

        //Print the rest of the table, including root locations
        for (double currentInterval = lowerBound; currentInterval <= upperBound; currentInterval += interval) {

            double nextInterval = currentInterval + interval;
            double previousInterval = currentInterval - interval;

            //Prints line of values
            System.out.printf("%8.3f", currentInterval);
            System.out.printf("%10.3f", p.getValue(currentInterval));
            System.out.printf("%10.3f", (p.getValue(currentInterval) - p.getValue(previousInterval)));
            System.out.println();

            //Prints location of a root
            switch (locationOfRoots) {

                case -1: //root only found at lower bound
                    if (currentInterval == lowerBound) {
                        System.out.println("Root found at " + currentInterval);
                    }
                    break;

                case 1: //root only found at upper bound
                    if (currentInterval == upperBound) {
                        System.out.println("Root found at " + currentInterval);
                    }
                    break;

                case 0: //root found between two bounds
                    if (p.findRoot(currentInterval, nextInterval) == 0) {
                        System.out.print("Root found between ");
                        System.out.printf("%.3f", currentInterval);
                        System.out.print(" and ");
                        System.out.printf("%.3f", nextInterval);
                        System.out.println();
                    }
                    break;

                case 2: //root at upper and lower bound
                    if (currentInterval == lowerBound) {
                        System.out.println("Root found at " + currentInterval);
                    }
                    if (currentInterval == upperBound) {
                        System.out.println("Root found at " + currentInterval);
                    }
                    break;

                case -2: //no roots found between bounds
                    if (currentInterval == upperBound) {
                        System.out.println("No roots were found between the given bounds.");
                    }
                    break;

            } // switch
        } // for
    }  // printTable() method
} // PolynomialUI class
