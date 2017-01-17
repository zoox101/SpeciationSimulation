package NewSim;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/*
 * Known Parameters:
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
 * @author William Booker
 * Based on work by Kevin and Mark
 * Speciation Simulation v.2.0 - 10/12/16
 * For use in Dr. Hougen's REAL Lab
 */
public class Simulation {

	//Simulation info
	protected final int islandsize = 100; //P1
	protected final int simdays = 100; //P5
	protected final int simyears = 1000; //PH
	//Seed Distribution
	protected final int numseeds = 5000; //P6
	//Birds
	protected final int numbirds = 40; //400 - PC
	protected final int searchlength = 10; //P8
	protected final double searchcost = 0.1; //PB
	protected final double chooseythreshold = 1.0; //PD
	protected final int maxage = 4; //PI
	//Misc
	protected final double diffthresh = 1.0; //P9
	protected final double energylimit = 2.0; //P2

	//Variables
	static int simnumber = 1;
	Bird.SelectionType selectiontype;
	Seed.Distribution dist; //P3, PK
	Bird.InheritanceMethod method; //P4
	Gene.Type genetype;

	public Simulation(Bird.SelectionType selectiontype, Seed.Distribution dist, 
			Bird.InheritanceMethod method, Gene.Type genetype) throws IOException {
		
		System.out.println(simnumber);
		
		this.selectiontype = selectiontype;
		this.dist = dist; //P3, PK
		this.method = method;
		this.genetype = genetype;
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File("Output" + simnumber++ + ".csv")));
		writer.write("Generation,BeakSize\n");
		ArrayList<Bird> birds = genpop();
		
		for(int year=0; year<simyears; year++) { //PH
			for(Bird bird: birds) writer.write(year + "," + bird.beak + "\n");
			birds = dryseason(birds);
			birds = matingseason(birds);
			birds = killoldbirds(birds);
		}	
		writer.close();
	}

	public ArrayList<Bird> genpop() {
		ArrayList<Bird> birds = new ArrayList<Bird>();
		//Generating birds
		for(int i=0; i<numbirds; i++) { //PC
			Bird bird = new Bird(method, genetype); //P4
			bird.age = (int)(Math.random()*maxage);
			birds.add(bird); //P4
		}
		return birds;
	}

	public ArrayList<Bird> dryseason(ArrayList<Bird> birds) {
		
		for(Bird bird: birds) bird.energy = 0;

		//Creating island -- P1
		SeedList[][] island = new SeedList[islandsize][islandsize];	
		for(int i=0; i<islandsize; i++)
			for(int j=0; j<islandsize; j++)
				island[i][j] = new SeedList();

		//Adding seeds
		for(int i=0; i<numseeds; i++) { //P6
			int randx = (int) (Math.random() * islandsize); //P1
			int randy = (int) (Math.random() * islandsize); //P1
			island[randx][randy].add(new Seed(dist)); //P3, PA, PK
		}

		//Running dry season
		for(int day=0; day<simdays; day++) { //P5
			birds = shuffle(birds); //P7
			//Running a day of feeding
			for(Bird bird: birds) {
				bird.energy -= searchcost; //PB
				int randx = Random.integer(islandsize-searchlength); //P8
				int randy = Random.integer(islandsize-searchlength); //P8
				boolean found = false;
				for(int x=randx; x<randx+searchlength; x++) //P8
					for(int y=randy; y<randy+searchlength; y++) //P8
						if(!found)
							for(int i=0; i<island[x][y].size(); i++) 
								if(Math.abs(island[x][y].get(i).size-bird.beak)<diffthresh) { //P9
									found = true;
									Seed seed = island[x][y].get(i);
									bird.energy = Math.min(energylimit, bird.energy+seed.energy); //P2
									island[x][y].remove(i);
								}
			}

			//Removing dead birds -- PJ
			ArrayList<Bird> deadbirds = new ArrayList<Bird>();
			for(Bird bird: birds)
				if(bird.energy < 0)
					deadbirds.add(bird);
			birds.removeAll(deadbirds);
			
		}

		return birds;
	}

	public ArrayList<Bird> matingseason(ArrayList<Bird> birds) {
		
		//Creating arrays to hold males, females, and children
		birds = shuffle(birds);
		ArrayList<Bird> children = new ArrayList<Bird>();
		ArrayList<Bird> males = new ArrayList<Bird>();
		ArrayList<Bird> females = new ArrayList<Bird>();

		//Adding each bird to the appropriate array
		for(Bird bird: birds) {
			if(bird.male) males.add(bird);
			else females.add(bird);
		}

		//Setting the mating length
		double matinglength; 
		if(selectiontype == Bird.SelectionType.SexualSelection) matinglength = chooseythreshold; //PD
		else matinglength = Double.MAX_VALUE;

		//Selecting an appropriate mate
		ArrayList<Bird> selections = new ArrayList<Bird>();
		for(Bird bird: males) bird.matecount = 0;
		for(Bird female: females) {
			for(Bird male: males) 
				if(Math.abs(male.beak-female.beak) < matinglength) //PD
					selections.add(male);
			if(selections.size() != 0) {
				Bird male = selections.get((int)(Math.random()*selections.size())); //PE
				children.add(new Bird(male, female, method, genetype)); //PG
				male.matecount++; //PF
				if(male.matecount>5) males.remove(male); //PF
			}
			selections.clear();
		}
		
		birds.addAll(children);
		return birds;

	}

	public ArrayList<Bird> killoldbirds(ArrayList<Bird> birds) {
		ArrayList<Bird> oldbirds = new ArrayList<Bird>();
		for(Bird bird: birds) 
			if(bird.age++ >= maxage) oldbirds.add(bird); //PI
		birds.removeAll(oldbirds);
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

}
