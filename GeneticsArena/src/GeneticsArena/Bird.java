package GeneticsArena;

import java.util.ArrayList;

public abstract class Bird {
	
	//The bird's beak size
	public double beak_size;
	
	/**
	 * Inheritance method that allows the user to refactor the genes in the population
	 * @param survivors - The birds that survived the last dry season
	 * @return new_birds - The population of new birds (must be 2x size of survivors)
	 */
	public abstract ArrayList<Bird> refactorPopulation(ArrayList<Bird> survivors);
	
	/**
	 * Initializes the initial population of birds
	 * @param size -- The size of the initial population
	 * @return Returns the initial population of birds
	 */
	public abstract ArrayList<Bird> initializePopulation(int size);
	
	//Getters and setters for the birds energy -- used by the simulation
	private double energy;
	public double getEnergy() {return energy;}
	public void setEnergy(double energy) {this.energy = energy;}

}
