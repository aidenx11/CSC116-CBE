import java.util.ArrayList;

/**
 *
 */
public class Polynomial {

    private double[] coefficientArray;
    private int numberOfCoefficients;
    private int indexOfNextSpot;


    public Polynomial() {
        coefficientArray = new double[10];
        numberOfCoefficients = 0;
        indexOfNextSpot = 0;
    }

    public void addCoefficient(double coefficient) {

        if (numberOfCoefficients < 11) {
            coefficientArray[indexOfNextSpot] = coefficient;
            indexOfNextSpot++;
            numberOfCoefficients++;
        } else {
            System.out.println("Maximum number of coefficients (10) reached. " + coefficient + " was not added.");
        }

    }

    public double[] getPolynomial() {
        return coefficientArray;
    }

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

    public double getValue(double x) {
        double y = 0;
        for (int i = numberOfCoefficients - 1; i >= 0; i--) {
            y += (coefficientArray[i] * Math.pow(x, i));
        }
        return y;
    }

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
