import java.util.ArrayList;

/**
 * The Polynomial class deals with storage and calculations of a given polynomial, with coefficients stored in an array.
 * @author Aiden Schroeder
 */
public class Polynomial {

    /** Stores all coefficients of the instance of Polynomial */
    private double[] coefficientArray;

    /** Stores the number of coefficients in the coefficient array */
    private int numberOfCoefficients;

    /** Stores the location of the next open index in the coefficient array */
    private int indexOfNextSpot;

    /**
     * Default constructor for the class. Initializes the array to a maximum of ten coefficients,
     * and sets the number of coefficients and index of next open spot both to zero.
     */
    public Polynomial() {
        coefficientArray = new double[10];
        numberOfCoefficients = 0;
        indexOfNextSpot = 0;
    }

    /**
     * Adds the given coefficient to the coefficient array.
     * If adding the coefficient would cause an indexOutOfBounds exception,
     * does not add coefficient and displays an error message.
     * @param coefficient
     */
    public void addCoefficient(double coefficient) {

        if (numberOfCoefficients < coefficientArray.length + 1) {
            coefficientArray[indexOfNextSpot] = coefficient;
            indexOfNextSpot++;
            numberOfCoefficients++;
        } else {
            System.out.println("Maximum number of coefficients (" + coefficientArray.length + ") reached. " + coefficient + " was not added.");
        }

    }

    /**
     * Returns the reference to the coefficient array.
     * (I was unsure if I was supposed to have it return a copy of the array or the reference,
     * but I decided to stick with the reference.)
     * @return the current coefficient array
     */
    public double[] getPolynomial() {
        return coefficientArray;
    }

    /**
     * Returns the full polynomial including its respective coefficients and their powers of x.
     * Overrides the object.toString() method.
     * @return the polynomial with coefficients and powers of x as a string.
     */
    @Override
    public String toString() {

        String s = "";

        for (int i = numberOfCoefficients - 1; i >= 0; i--) {
            double currentValue = coefficientArray[i];
            if (currentValue != 0) {
                if (i == numberOfCoefficients - 1) {
                    s += (currentValue > 0 ? "" : "- ");
                } else {
                    s += (currentValue > 0 ? "+ " : "- ");
                }
                s += (Math.abs(currentValue) + " x^" + i + " ");
            }
        }
        return s;
    }

    /**
     * Plugs in the given value of x into the polynomial to calculate the p(x) value.
     * @param x the value to be evaluated in the polynomial
     * @return the given value of x evaluated at p(x), the polynomial
     */
    public double getValue(double x) {
        double y = 0;
        for (int i = numberOfCoefficients - 1; i >= 0; i--) {
            y += (coefficientArray[i] * Math.pow(x, i));
        }
        return y;
    }

    /**
     * Finds the location of the roots, if there are any, between the given lower
     * and upper bounds of the polynomial.
     * @param lower the lower bound for evaluation
     * @param upper the upper bound for evaluation
     * @return an integer dependent on where the roots are found:
     * -2: there were no roots found between the given bounds
     * -1: there is a root only at the lower bound
     * 0: the root(s) is(are) somewhere between the lower and upper bound
     * 1: there is a root only at the upper bound
     * 2: there are roots at both the upper and lower bound
     */
    public int findRoot(double lower, double upper) {
        double upperValue = this.getValue(upper);
        double lowerValue = this.getValue(lower);
        if (lower == 0 && upperValue == 0) {
            return 2; //roots at both upper and lower bound
        } else if (lowerValue == 0) {
            return -1; //root at lower bound
        } else if (upperValue == 0) {
            return 1; //root at upper bound
        } else if (lowerValue * upperValue < 0) {
            return 0; //root between lower and upper bound
        } else {
            return -2;  //root not found between bounds
        }
    }


}
