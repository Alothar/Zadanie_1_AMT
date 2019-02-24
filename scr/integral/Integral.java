package integral;

import java.math.BigDecimal;

import polynomial.Polynomial;

public class Integral {
	// class to handle and store integral

	private Polynomial polynomial; // polynomial to be integrated
	private double interval_start; // integration interval start point
	private double interval_end; // integration interval end point
	private double interval_length; // integration interval length
	private double interval_height; // integration interval height
	private double polynomial_maximum; // polynomial maximum for this interval
	private double polynomial_minimum; // polynomial minimum for this interval

	public Integral(Polynomial polynomial, double interval_start, double interval_end) { // constructor
		this.polynomial = polynomial;
		this.interval_start = interval_start;
		this.interval_end = interval_end;
		this.interval_length = interval_end - interval_start;
		getFunctionValues();
		this.interval_height = polynomial_maximum - polynomial_minimum;
	}

	// getters & setters
	public Polynomial getPolynomial() {
		return polynomial;
	}

	public void setPolynomial(Polynomial polynomial) {
		this.polynomial = polynomial;
	}

	public double getInterval_start() {
		return interval_start;
	}

	public double getInterval_end() {
		return interval_end;
	}

	public double getInterval_length() {
		return interval_length;
	}

	public double getInterval_height() {
		return interval_height;
	}

	public double getPolynomial_maximum() {
		return polynomial_maximum;
	}

	public void setPolynomial_maximum(double polynomial_maximum) {
		this.polynomial_maximum = polynomial_maximum;
	}

	public void setPolynomial_minimum(double polynomial_minimum) {
		this.polynomial_minimum = polynomial_minimum;
	}

	public double getPolynomial_minimum() {
		return polynomial_minimum;
	}

	public void getFunctionValues() {
		// function to set minimum & maximum for f(x) є [intervals_start; interval_end]
		double maximum = polynomial.getPolynomialValue(polynomial.getPolynomialArray(), interval_start); // set the initial min/max values to the f(interval_start)
		double minimum = polynomial.getPolynomialValue(polynomial.getPolynomialArray(), interval_start);
		int exponent = 0; // exponent used to scale graph
		BigDecimal tmp_interval_length = BigDecimal.valueOf(interval_length).abs(); // used to separate values [0;1] from other double values
		if (tmp_interval_length.doubleValue() > 1) { // calculations for values !є [0;1]
			int tmp_length = tmp_interval_length.intValue(); // calculate exponent to scale the graph
			while (tmp_length != 0) {
				tmp_length /= 10;
				exponent++;
			}
			for (double i = Math.pow(interval_start, exponent); i <= Math.pow(interval_end, exponent); i++) { // calculations for f(x) min/max for values !є [-1;1]
				double value = polynomial.getPolynomialValue(polynomial.getPolynomialArray(), i); //
				if (value > maximum)
					maximum = value;
				if (value < minimum)
					minimum = value;
			}
		}

		else if (tmp_interval_length.doubleValue() <= 1) { // calculations for values є [0;1]
			exponent = BigDecimal.valueOf(interval_length).scale(); // calculate exponent to scale the graph
			for (double i = Math.pow(10 * exponent, interval_start); i <= Math.pow(10 * exponent, interval_end) + 1; i++) { // calculations for f(x) min/max for values !є [-1;1]
																															// +1 added due to non-precise data
																															// convertion from double to int
				double value = polynomial.getPolynomialValue(polynomial.getPolynomialArray(), i / 10 * exponent);
				if (value > maximum)
					maximum = value;
				if (value < minimum)
					minimum = value;
			}
		}

		setPolynomial_maximum(maximum);
		setPolynomial_minimum(minimum);

	}

	public void print() { // used for debugging
		polynomial.print();
		System.out.println();
		System.out.printf("Interval = %f - %f\n", interval_start, interval_end);
		System.out.println();
		System.out.printf("Interval length = %f\n", interval_length);
		System.out.println();
		System.out.printf("Interval height = %f\n", interval_height);
		System.out.println();
		System.out.printf("Polynomial maximum = %f\n", polynomial_maximum);
		System.out.println();
		System.out.printf("Polynomial minimum = %f\n", polynomial_minimum);
		System.out.println();
	}
}
