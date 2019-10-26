package CustomClasses;

import java.util.ArrayList;

import GeneticsArena.Bird;

public class BirdHaploidMeiotic extends Bird {
	
	/**
	 * Creates a bird with a given beak size
	 */
	public BirdHaploidMeiotic(double beak_size) {
		this.beak_size = beak_size;
	}
	
	/**
	 * Inheritance method that allows the user to refactor the genes in the population
	 * @param survivors - The birds that survived the last dry season
	 * @return new_birds - The population of new birds (must be 2x size of survivors)
	 */
	public ArrayList<Bird> refactorPopulation(ArrayList<Bird> survivors) {
		
		//Getting the size of the initial population
		int POP_SIZE = survivors.size();
		
		//Blending genes to create a new population of birds
		ArrayList<Bird> new_birds = new ArrayList<Bird>();
		for(int i=0; i<2*POP_SIZE; i++) {
			
			//Selecting the parents
			Bird father = survivors.get((int)(Math.random() * POP_SIZE));
			Bird mother = survivors.get((int)(Math.random() * POP_SIZE));
			
			//This is a ternary operator. It's just an if statement on one line.
			double beak_size = Math.random() < 0.5 ? father.beak_size : mother.beak_size;
			
			//Mutating
			beak_size = beak_size + Math.random() - 0.5;
			
			//Adding to the population
			new_birds.add(new BirdHaploidBlended(beak_size));
		}
		
		//Returning the population of new birds
		return new_birds;
	}
	
	/**
	 * Initializes the initial population of birds
	 * @param size -- The size of the initial population
	 * @return Returns the initial population of birds
	 */
	public ArrayList<Bird> initializePopulation(int size) {
		
		//Initializing variables
		final int POP_RANGE = 100;
		ArrayList<Bird> population = new ArrayList<Bird>();
		
		//Creates a population of the given size
		for(int i=0; i<size; i++) {
			population.add(new BirdHaploidBlended(Math.random() * POP_RANGE));}
		return population;
	}

}
