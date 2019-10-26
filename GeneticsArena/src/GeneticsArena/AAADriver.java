package GeneticsArena;

import java.io.IOException;
import CustomClasses.BimodalSeedGenerator;
import CustomClasses.BirdHaploidBlended;
import CustomClasses.BirdHaploidMeiotic;
import CustomClasses.UniformSeedGenerator;

public class AAADriver {
		
	//Number of simulations to run
	final static int RUNS = 4;
	
	//Main method
	public static void main(String[] args) throws IOException {
				
		//Saves the output to a csv file
		boolean save = true;
		
		//Creating bird generators
		Bird haploid_blended = new BirdHaploidBlended(0.0);
		Bird haploid_meiotic = new BirdHaploidMeiotic(0.0);
		
		//Creating seed generators
		SeedGenerator bimodal_generator = new BimodalSeedGenerator();
		SeedGenerator uniform_generator = new UniformSeedGenerator();
		
		//Running the simulations
		int bimodal = runSims(haploid_blended, bimodal_generator, save);
		int uniform = runSims(haploid_meiotic, bimodal_generator, save);
		
		//Outputting results to the user
		System.out.println("");
		System.out.println("Blended Survived: " + bimodal + "/" + RUNS);
		System.out.println("Meiotic Survived: " + uniform + "/" + RUNS);
	}
	
	//Runs a number of simulations
	static int runSims(Bird bird, SeedGenerator seeds, boolean save) throws IOException {
		int counter = 0;
		for(int i=0; i<RUNS; i++) {
			System.out.print(Simulation.getSimNumber() + " ");
			Simulation sim = new Simulation(bird, seeds, save);
			if(!sim.extinction) {counter++;}
		}
		return counter;
	}

}
