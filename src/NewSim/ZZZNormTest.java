package NewSim;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author William Booker
 * Based on work by Kevin and Mark
 * Speciation Simulation v.2.0 - 10/12/16
 * For use in Dr. Hougen's REAL Lab
 */
public class ZZZNormTest {
	
	public static void main(String[] args) throws IOException {
		test(5, Math.pow(0.5,1), 5000);
	}
	
	public static void test(double mean, double variance, int reps) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File("NormTest.csv")));
		for(int i=0; i<reps; i++) writer.write(Random.normal(mean, variance) + "\n");
		writer.close();
	}

}
