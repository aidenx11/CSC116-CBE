import java.util.ArrayList;
import java.util.Scanner;
/**
 * The PolynomialUI class runs the polynomial application. Uses the Polynomial class.
 * Contains the main method.
 * @author Aiden Schroeder
 */
public class PolynomialUI {

    private Polynomial p = new Polynomial();
    private double lowerBound = 0;
    private double upperBound = 0;
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

        boolean wantsToRedefine = false;
        String choice = " ";

        Scanner input = new Scanner(System.in);

            setPolynomial();
            printPolynomial();

        do {
            wantsToRedefine = false;
            choice = " ";
            setRange();
            printTable();
            while (!choice.equals("Y") && !choice.equals("N")) {
                System.out.println("Would you like to redefine your table? (Y/N)");
                choice = input.next();
                if (choice.equals("Y")) {
                    wantsToRedefine = true;
                } else if (choice.equals("N")) {
                    System.out.println("Exiting Program...");
                    System.exit(0);
                } else {
                    System.out.println("Invalid input. Please enter only \"Y\" or \"N\" ");
                } // if-else chain
            } // interior while loop
        } while (wantsToRedefine);
        input.close();
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

        while (!(line = input.nextLine()).isEmpty()) {
            try {
                this.p.addCoefficient(Double.parseDouble(line));
            } catch(Exception ex) {
                System.out.println("Invalid input, please try again.");
            }
        }
        input.close();
    }

    /**
     * Prints the current polynomial to system out using the Polynomial class's toString() method.
     */
    public void printPolynomial() {
        System.out.println(this.p.toString());
    }

    /**
     * Prompts user for lower and upper bounds, as well as the number of intervals.
     * If input is invalid, user is notified and re-prompted.
     */
    public void setRange() {

        this.lowerBound = 0;
        this.upperBound = 0;
        this.numberOfIntervals = 0;
        boolean validInput = false;

        Scanner input = new Scanner(System.in);

        while (!validInput) {
            try {
                System.out.print("Type in the range: \nLower Bound (double): ");
                lowerBound = input.nextDouble();

                System.out.print("Upper bound (double): ");
                upperBound = input.nextDouble();
                if (lowerBound > upperBound) throw new Exception();

                System.out.print("Number of Intervals (int): ");
                numberOfIntervals = input.nextInt();
                if (numberOfIntervals <= 0) throw new Exception();

                validInput = true;

            } catch (Exception ex) {

                System.out.println("Invalid input, please try again. ");
                input.nextLine();
            } // try-catch
        } // while loop
    } // setRange() method

    /**
     * Prints a table of all index values along with their p(x) values from the polynomial,
     * and the difference between the previous p(x) and the current one.
     *
     * Determines location of roots using the Polynomial class's findRoot() method with the
     * user given lower and upper bound.
     * Uses a switch statement to determine where to print the location of the root to system out.
     * Root location is printed after its respective index, or between the indices if it is between two.
     */
    public void printTable() {

        System.out.printf("%8s", "index");
        System.out.printf("%10s", "p(index)");
        System.out.printf("%13s %n",  "diff(index)");

        int locationOfRoots = p.findRoot(lowerBound, upperBound);

        double interval = (upperBound - lowerBound) / numberOfIntervals;

        for (double currentInterval = lowerBound; currentInterval <= upperBound; currentInterval += interval) {

            double nextInterval = currentInterval + interval;
            double previousInterval = currentInterval - interval;

            System.out.printf("%8.3f", currentInterval);
            System.out.printf("%10.3f", p.getValue(currentInterval));
            System.out.printf("%10.3f", (p.getValue(currentInterval) - p.getValue(previousInterval)));
            System.out.println();

            switch (locationOfRoots) {

                case -1:
                    if (currentInterval == lowerBound) {
                        System.out.println("Root found at " + currentInterval);
                    }
                    break;

                case 1:
                    if (currentInterval == upperBound) {
                        System.out.println("Root found at " + currentInterval);
                    }
                    break;

                case 0:
                    if (p.findRoot(currentInterval, currentInterval + interval) == 0) {
                        System.out.print("Root found between ");
                        System.out.printf("%.3f", currentInterval);
                        System.out.print(" and ");
                        System.out.printf("%.3f", nextInterval);
                        System.out.println();
                    }
                    break;

                case 2:
                    if (currentInterval == lowerBound) {
                        System.out.println("Root found at " + currentInterval);
                    }
                    if (currentInterval == upperBound) {
                        System.out.println("Root found at " + currentInterval);
                    }
                    break;

                case -2:
                    if (currentInterval == upperBound) {
                        System.out.println("No roots were found between the given bounds.");
                    }
                    break;

            } // switch
        } // for
    }  // printTable() method
} // PolynomialUI class
