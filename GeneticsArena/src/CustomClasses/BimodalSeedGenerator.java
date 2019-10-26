package CustomClasses;

import GeneticsArena.Random;
import GeneticsArena.Seed;
import GeneticsArena.SeedGenerator;

public class BimodalSeedGenerator extends SeedGenerator {
	
	//Initializing constants
	final double MAX_ENERGY = 2; //PA
	final double BIMODAL_LOW_MEAN = 3; //PK
	final double BIMODAL_HIGH_MEAN = 8; //PK
	final double BIMODAL_STDEV = 0.5; //PK

	@Override
	public Seed genSeed() {
		
		//Getting seed size
		double seed_size;
		if(Math.random() > 0.5) {
			seed_size = Random.normal(BIMODAL_LOW_MEAN, BIMODAL_STDEV);}
		else {
			seed_size = Random.normal(BIMODAL_HIGH_MEAN, BIMODAL_STDEV);}
		
		//Getting seed energy
		double seed_energy = Random.random() * MAX_ENERGY; //PA
		
		//Returning a new seed
		return new Seed(seed_size, seed_energy);
	}

}
