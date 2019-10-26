package GeneticsArena;

/**
 * @author William Booker
 * Based on work by Kevin and Mark
 * Speciation Simulation v.2.0 - 10/12/16
 * For use in Dr. Hougen's REAL Lab
 */
public class Random {
	
	static java.util.Random random = new java.util.Random();
	
	//Returns a random double
	public static double random() {
		return random.nextDouble();}
	
	//Returns a random normal value
	public static double normal(double mean, double sd) {
		return random.nextGaussian()*sd + mean;}
	
	//Returns a random integer
	public static int integer(int bound) {
		return random.nextInt(bound);}

}
