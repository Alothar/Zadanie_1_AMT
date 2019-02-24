/**
 * 
 */
package main;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

import integral.Integral;
import polynomial.Polynomial;
import simulation.Simulation;

/**
 * @author serhii omelchuk
 *
 */

public class Main {

	public static void main(String[] args) {

		init();
	}

	public static void init()
	// Method to initialize values
	{
		try {
			// standard try-catch block for file input
			FileReader fr = new FileReader("input.txt"); // input file
			FileWriter fw = new FileWriter("output.txt"); // output file
			Scanner sc = new Scanner(fr);
			Polynomial polynomial = new Polynomial(sc, sc.nextInt()); // polynomial initialization
			Integral integral = new Integral(polynomial, sc.nextDouble(), sc.nextDouble()); // integral initialization
			Simulation simulation = new Simulation(integral, sc.nextInt()); // simulation initialization
			// simulation.print(); // debugging
			simulation.write(fw); // called to write an answer
			fr.close(); // stream closing
			sc.close();
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
