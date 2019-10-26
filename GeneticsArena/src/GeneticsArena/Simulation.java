package GeneticsArena;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/*
 * Paper Parameters:
 * P1: We developed an artificial island — a square region 100 x 100 units.
 * P2: The birds have ... a maximum energy capacity of two units.
 * P3: The uniform seed distribution (1 to 10) ... [has] no distinction with regard to abundance for any given seed size.
 * P4: Individuals possessing moderately sized beaks (mean 5.5, variance 0.5).
 * P5: The extended dry season ... is modeled as an interval lasting 100 days.
 * P6: We started the dry season with 5,000 seeds.
 * P7: The individuals feed in random order.
 * P8: An individual first picks a random region, 10x10 units in size. 
 * P9: An individual can consume seeds plus or minus one unit from its beak size.
 * PA: The exact amount of energy contained in each seed varies randomly from zero to two units (uniformly random).
 * PB: The cost for search is 0.1 units of energy.
 * PC: The initial population size ... was 400 individuals
 * PD: For assortative mating, the female ... will only choose a male that is plus or minus one unit from her own beak size.
 * PE: If there is more than one acceptable mate, the female chooses one of those males at random.
 * PF: Males are only allowed to mate five times per breeding season.
 * PG: This offspring's beak size is the average of its parents’ plus a small amount of Gaussian noise (mean 0, variance 0.2).
 * PH: We conducted numerous repetitions for each of the four experimental conditions for up to 1000 generations.
 * PI: The birds have ... a lifespan of four years
 * PJ: If [the energy level] falls below zero that individual is removed from the population.
 * PK: The bimodal seed distribution (means 3 and 8, variance 0.5)
 * 
 * Notes: 
 * Possible minor issue with P8. The region at 100x100 is very unlikely to be hit. Should not affect final result.
 */

/**
 * @author Will Booker
 */
public class Simulation {

	//Simulation info
	protected final int islandsize = 100; //P1
	protected final int simdays = 100; //P5
	protected final int simyears = 1000; //PH
	
	//Seed Distribution
	protected final int numseeds = 2000; //P6
	
	//Birds
	protected final int numbirds = 40; //400 - PC
	protected final int searchlength = 10; //P8
	protected final double searchcost = 0.1; //PB
	
	//Miscellaneous
	protected final double diffthresh = 0.1; //P9
	protected final double energylimit = 2.0; //P2

	//Variables
	private static int simnumber = 1;
	public final boolean extinction;

	
	/**
	 * @param generator
	 * @param save_results
	 * @throws IOException
	 */
	public Simulation(Bird bird_generator, SeedGenerator seed_generator, boolean save_results) throws IOException {
						
		//User output
		BufferedWriter writer = null;
		
		//If the user chooses to save the results, open a file
		if(save_results) {
			String filename = "saves" + File.separator + "Output" + simnumber + ".csv";
			writer = new BufferedWriter(new FileWriter(new File(filename)));
			writer.write("Generation,BeakSize\n");}
		
		//Initializing bird populations
		ArrayList<Bird> birds = bird_generator.initializePopulation(numbirds);
		
		//Running the simulation
		for(int year=0; year<simyears; year++) { //PH
			
			//Saving the simulation results
			if(save_results) {
				for(Bird bird: birds) {writer.write(year + "," + bird.beak_size + "\n");}}
			
			//Running the dry season
			birds = dryseason(birds, seed_generator);
			
			//Creating the new population of birds
			birds = bird_generator.refactorPopulation(birds);
		}
		
		//Checking to see if the population went extinct
		this.extinction = birds.size() == 0;
		
		//Closing the file writer
		if(save_results) {writer.close();}
		
		simnumber += 1;
	}
	
	
	//Runs the dry season
	public ArrayList<Bird> dryseason(ArrayList<Bird> birds, SeedGenerator seed_generator) {
		
		//Sets each bird's energy to the energy limit
		for(Bird bird: birds) {bird.setEnergy(energylimit);}

		//Creating island -- P1
		SeedList[][] island = new SeedList[islandsize][islandsize];	
		for(int i=0; i<islandsize; i++)
			for(int j=0; j<islandsize; j++)
				island[i][j] = new SeedList();

		//Adding seeds
		for(int i=0; i<numseeds; i++) { //P6
			int randx = (int) (Math.random() * islandsize); //P1
			int randy = (int) (Math.random() * islandsize); //P1
			island[randx][randy].add(seed_generator.genSeed()); //P3, PA, PK
		}

		//Running dry season
		for(int day=0; day<simdays; day++) { //P5
			birds = shuffle(birds); //P7
			//Running a day of feeding
			for(Bird bird: birds) {
				bird.setEnergy(bird.getEnergy() - searchcost);
				int randx = Random.integer(islandsize-searchlength); //P8
				int randy = Random.integer(islandsize-searchlength); //P8
				boolean found = false;
				for(int x=randx; x<randx+searchlength; x++) //P8
					for(int y=randy; y<randy+searchlength; y++) //P8
						if(!found)
							for(int i=0; i<island[x][y].size(); i++) 
								if(Math.abs(island[x][y].get(i).size-bird.beak_size)<diffthresh) { //P9
									found = true;
									Seed seed = island[x][y].get(i);
									bird.setEnergy(Math.min(energylimit, bird.getEnergy() + seed.energy)); //P2
									island[x][y].remove(i);
								}
			}

			//Removing dead birds -- PJ
			ArrayList<Bird> deadbirds = new ArrayList<Bird>();
			for(Bird bird: birds)
				if(bird.getEnergy() < 0)
					deadbirds.add(bird);
			birds.removeAll(deadbirds);
			
		}
		
		//Returning the birds that survived the dry season
		return birds;
	}

	
	//Shuffles an array of birds -- P7
	public static ArrayList<Bird> shuffle(ArrayList<Bird> array) {
		ArrayList<Bird> newarray = new ArrayList<Bird>();
		while(array.size() != 0) {
			int index = (int)(Math.random()*array.size());
			Bird bird = array.get(index);
			array.remove(index);
			newarray.add(bird);
		}
		return newarray;
	}
	
	
	//Returns the simulation number
	public static int getSimNumber() {return Simulation.simnumber;}

}
