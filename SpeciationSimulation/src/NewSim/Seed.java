package NewSim;

/**
 * @author William Booker
 * Based on work by Kevin and Mark
 * Speciation Simulation v.2.0 - 10/12/16
 * For use in Dr. Hougen's REAL Lab
 */
public class Seed {
	
	public static enum Distribution {Uniform, Bimodal};
	
	protected final int seedmaxenergy = 2; //PA
	//Uniform Seed Distribution
	protected static final double uniformminsize = 1; //P3
	protected static final double uniformmaxsize = 10; //P3
	//Bimodal Seed Distribution
	protected static final double bimodallowmean = 3; //PK
	protected static final double bimodalhighmean = 8; //PK
	protected static final double bimodalstdev = 0.5; //PK
	
	double size;
	double energy;
		
	Seed(Distribution dist) {
		if(dist == Distribution.Uniform)
			this.size = (Math.random() * (uniformmaxsize-uniformminsize)) + uniformminsize; //P3
		if(dist == Distribution.Bimodal) {
			if(Math.random() > 0.5)
				this.size = Random.normal(bimodallowmean, bimodalstdev);
			else
				this.size = Random.normal(bimodalhighmean, bimodalstdev);
		}
		this.energy = Math.random() * seedmaxenergy; //PA
	}

}
