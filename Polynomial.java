package polynomial;

import java.util.ArrayList;
import java.util.Scanner;

public class Polynomial {
	// class to handle and store polynomial

	private ArrayList<Double> polynomial = new ArrayList<Double>(); // array of the x coefficients

	public Polynomial(Scanner sc, int length) { // constructor
		for (int i = 0; i < length; i++)
			polynomial.add(sc.nextDouble());
	}

	// getter & setter
	public ArrayList<Double> getPolynomialArray() {
		return polynomial;
	}

	public void setPolynomialArray(ArrayList<Double> polynomial) {
		this.polynomial = polynomial;
	}

	public double getPolynomialValue(ArrayList<Double> polynomial, double x) { // returns f(x)
		double value = 0;
		for (int i = 0; i < polynomial.size(); i++) {
			value += polynomial.get(i) * Math.pow(x, i);
		}
		return value;
	}

	public void print() { // used for debugging
		for (int i = 0; i < polynomial.size(); i++) {
			System.out.println();
			System.out.println(polynomial.get(i));
			System.out.println();
		}
	}
}
