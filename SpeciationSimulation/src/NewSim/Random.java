package NewSim;

/**
 * @author William Booker
 * Based on work by Kevin and Mark
 * Speciation Simulation v.2.0 - 10/12/16
 * For use in Dr. Hougen's REAL Lab
 */
public class Random {
	
	static java.util.Random random = new java.util.Random();
	
	public static double normal(double mean, double sd) {
		return random.nextGaussian()*sd + mean;
	}
	
	public static int integer(int bound) {
		return random.nextInt(bound);
	}

}
