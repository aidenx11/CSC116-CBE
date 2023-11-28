import java.util.ArrayList;

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

        StringBuilder sb = new StringBuilder();

        for (int i = numberOfCoefficients - 1; i >= 0; i--) {
            sb.append(coefficientArray[i] + " x^" + i + " ");
        }
        String s = sb.toString();
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

        return 0;
    }


}
