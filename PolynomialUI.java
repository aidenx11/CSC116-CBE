import java.util.ArrayList;
import java.util.Scanner;

public class PolynomialUI {

    Polynomial p;


    public static void main(String[] args) {

        PolynomialUI p = new PolynomialUI();

    }

    public PolynomialUI() {
        this.p = new Polynomial();
        runPolynomial();
    }

    public void runPolynomial() {
        setPolynomial();
        printPolynomial();
        setRange();
        printTable();

    }

    public void setPolynomial() {
        Scanner input = new Scanner(System.in);
        System.out.println("Type in the polynomials in increasing powers:");
        int index = 0;
        while (input.hasNextLine()) {
            if (input.hasNextDouble()) {

            } else {
                System.out.println("Invalid input, please try again.");
            }
        }
    }

    public void printPolynomial() {

    }

    public void setRange() {

    }

    public void printTable() {

    }

}
