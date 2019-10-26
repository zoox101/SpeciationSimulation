package CustomClasses;

import GeneticsArena.Random;
import GeneticsArena.Seed;
import GeneticsArena.SeedGenerator;

public class UniformSeedGenerator extends SeedGenerator {
	
	//Initializing constants
	final double MAX_ENERGY = 2; //PA
	static final double UNIFORM_MIN_SIZE = 1; //P3
	final double UNIFORM_MAX_SIZE = 10; //P3

	@Override
	public Seed genSeed() {
		double seed_size = (Math.random() * (UNIFORM_MAX_SIZE-UNIFORM_MIN_SIZE)) + UNIFORM_MIN_SIZE;		
		double seed_energy = Random.random() * MAX_ENERGY; //PA
		return new Seed(seed_size, seed_energy);
	}

}
