package NewSim;
import java.io.IOException;

/**
 * @author William Booker
 * Based on work by Kevin and Mark
 * Speciation Simulation v.2.0 - 10/12/16
 */
public class AAADriver {
	
	final static Bird.SelectionType sexual = Bird.SelectionType.SexualSelection;
	final static Bird.SelectionType random = Bird.SelectionType.RandomSelection;
	final static Seed.Distribution bimodal = Seed.Distribution.Bimodal;
	final static Seed.Distribution uniform = Seed.Distribution.Uniform;
	final static Bird.InheritanceMethod avg = Bird.InheritanceMethod.Averaging;
	final static Bird.InheritanceMethod pseudo = Bird.InheritanceMethod.Pseudomeiosis;
	final static Bird.InheritanceMethod meiosis = Bird.InheritanceMethod.Meiosis;
	final static Gene.Type incomplete = Gene.Type.IncompleDominance;
	final static Gene.Type complete = Gene.Type.CompleteDominance;

	public static void main(String[] args) throws IOException {
		
		System.out.println("Starting New Simulation");
		
		/* Single Simulation */
		//for(int i=0; i<10; i++) new Simulation(random, bimodal, meiosis, complete);
		/* Single Simulation */
		
		/* Simulation *
		System.out.println("40 Birds -- 4x Starting Pop -- 0 Mutation Rate");
		for(int i=0; i<48; i++) new Simulation(random, bimodal, pseudo, incomplete);
		for(int i=0; i<48; i++) new Simulation(random, bimodal, meiosis, incomplete);
		for(int i=0; i<48; i++) new Simulation(random, bimodal, meiosis, complete);
		/* Simulation */
		
		/* Simulation Testing */
		//new Simulation(random, bimodal, meiosis, complete);
		//new Simulation(random, bimodal, meiosis, complete);
		//new Simulation(random, bimodal, meiosis, complete);
		//new Simulation(random, bimodal, meiosis, incomplete);
		/* Simulation Testing */
		
		/* Mutation Testing */
		//new Simulation(sexual, bimodal, meiosis, incomplete);
		//new Simulation(sexual, uniform, pseudo, incomplete);
		//new Simulation(sexual, bimodal, avg, incomplete);
		//new Simulation(sexual, uniform, avg, incomplete);
		/* Mutation Testing */
		
		System.out.println("Finishing Simulation");
	}
	
}











