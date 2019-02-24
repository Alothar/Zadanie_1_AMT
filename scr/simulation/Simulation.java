package simulation;

import java.io.FileWriter;
import java.util.Random;

import integral.Integral;

public class Simulation {
	// Class to store and perform Monte-Carlo simulation

	private Integral integral; // used for simulation integral
	private double attempts; // simulation attempts number
	private double integral_approximate_area; // approximate area of the integral
	private double hits; // number of successful hits during simulation

	public Simulation(Integral integral, int attempts) { // constructor
		this.integral = integral;
		this.attempts = attempts;
		checkSplitIntegrals();
	}

	// getters & setters
	public double getIntegral_approximate_volume() {
		return integral_approximate_area;
	}

	public void setIntegral_approximate_volume(double integral_approximate_volume) {
		this.integral_approximate_area = integral_approximate_volume;
	}

	public void setHits(int hits) {
		this.hits = hits;
	}

	public void checkSplitIntegrals() { // checks if integral interval should be splitted into two - negative and
										// positive and calculated separately
		if (integral.getInterval_start() < 0 && integral.getInterval_end() > 0) {
			Integral neg_integral = new Integral(integral.getPolynomial(), integral.getInterval_start(), 0); // negative integral
			Integral pos_integral = new Integral(integral.getPolynomial(), 0, integral.getInterval_end()); // positive integral
			setIntegral_approximate_volume(simulate(neg_integral) + simulate(pos_integral));
		} else
			setIntegral_approximate_volume(simulate(integral)); // if split is not required
	}

	public double simulate(Integral integral) {
		double x = 0;
		double y = 0;
		int counter = 0;
		double integral_approximate_volume = 0;
		Random random = new Random(); // new instance of Random class to get pseudo randomised values
		for (int i = 0; i < attempts; i++) {
			x = random.nextDouble() * integral.getInterval_length(); // getting x value є [0; integral interval length}
																		// (p.s. i know that values for x = integral interval length
																		// will never be counted, but this happens due to
																		// util.Random class restrictions for nextDouble() realization)
			y = random.nextDouble() * integral.getInterval_height(); // getting y value є [0; integral interval height}
			double y_polynomial_value = integral.getPolynomial().getPolynomialValue(integral.getPolynomial().getPolynomialArray(), x); // getting f(x) for polynomial
			if (y > 0 && y_polynomial_value > 0) // checking hit for positive integral values
				if (y <= y_polynomial_value)
					counter++;
			if (y < 0 && y_polynomial_value < 0) // checking hit for negative integral values
				if (y >= y_polynomial_value)
					counter++;
		}
		setHits(counter); // set number of hits
		integral_approximate_volume = (integral.getInterval_length() * integral.getInterval_height()) * (hits / attempts); // count approximate volume
		return integral_approximate_volume;
	}

	public void print() { // used for debugging
		integral.print();
		System.out.println();
		System.out.println("Attempts = " + attempts);
		System.out.println();
		System.out.println("Hits = " + hits);
		System.out.println();
		System.out.printf("Volume = %f\n", integral_approximate_area);
	}

	public void write(FileWriter fw) {
		// used to write the answer to the file
		try {
			fw.write("Approximate integral volume = " + Double.toString(integral_approximate_area) + "\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
