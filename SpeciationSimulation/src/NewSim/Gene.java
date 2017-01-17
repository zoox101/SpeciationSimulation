package NewSim;
import java.util.ArrayList;

/**
 * @author William Booker
 * Based on work by Kevin and Mark
 * Speciation Simulation v.2.0 - 10/12/16
 * For use in Dr. Hougen's REAL Lab
 */
@SuppressWarnings("serial")
public class Gene extends ArrayList<Double> {
	
	//Meiosis
	protected final double initialmean = 0.055; // 0.055 - Given in paper
	protected final double initialmutationrate = 0.245; // 0.245 - Mathematically determined - 0.244949;
	protected final double runningmutationrate = 0.310; // 0.310 - Empirically determined
	
	public static enum Type {CompleteDominance, IncompleDominance}
	private final static double mutationchance = 0.1;
	
	public Gene(int size) {
		super(size);
		for(int i=0; i<size; i++)
			this.add(initialmean + initialmutationrate * (Math.random()-0.5));
	}
	
	//Should this be adding or multiplying?
	public double phenotype() {
		double total = 0.0;
		for(Double subgene : this)
			total = total + subgene;
		return total;
	}
	
	//Copies a gene with mutations
	public Gene copy() {
		Gene genecopy = new Gene(this.size());
		for(int i=0; i<this.size(); i++) {
			if(mutationchance < Math.random()) genecopy.set(i, this.get(i));
			else {
				double currentsubgene = this.get(i);
				genecopy.set(i, currentsubgene + runningmutationrate*(Math.random()-0.5)); //0.32
			}
		}
		return genecopy;
	}

}

