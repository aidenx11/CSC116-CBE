import java.util.ArrayList;
import java.util.Scanner;

public class PolynomialUI {

    private Polynomial p = new Polynomial();
    private double lowerBound = 0;
    private double upperBound = 0;
    private int numberOfIntervals = 0;



    public static void main(String[] args) {
        PolynomialUI p = new PolynomialUI();
    }

    public PolynomialUI() {
        runPolynomial();
    }

    public void runPolynomial() {
        setPolynomial();
        printPolynomial();
        setRange();
        printTable();

    }

    public void setPolynomial() {

        Polynomial p = new Polynomial();
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
    }

    public void printPolynomial() {
        System.out.println(this.p.toString());
    }

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
                validInput = true;
            } catch (Exception ex) {
                System.out.println("Invalid input, please try again. ");
                input.nextLine();
            }
        }
    }

    public void printTable() {

    }

}
