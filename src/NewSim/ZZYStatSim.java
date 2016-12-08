package NewSim;
import java.util.ArrayList;

/**
 * @author William Booker
 * Based on work by Kevin and Mark
 * Statistics Simulation v.1.0 - 11/30/16
 */
public class ZZYStatSim extends ArrayList<Bird>{
	private static final long serialVersionUID = -2747187877450060945L;
	
	//Simulation info
	protected final int islandsize = 100; //P1
	protected final int simdays = 100; //P5
	protected final int simyears = 100; //PH
	//Seed Distribution
	protected int numseeds = 5000; //P6
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
	Bird.SelectionType selectiontype;
	Seed.Distribution dist; //P3, PK
	Bird.InheritanceMethod method; //P4
	Gene.Type genetype;

	public ZZYStatSim(int numseeds) {
		
		this.numseeds = numseeds;
				
		this.selectiontype = Bird.SelectionType.RandomSelection;
		this.dist = Seed.Distribution.Uniform;
		this.method = Bird.InheritanceMethod.Meiosis;
		this.genetype = Gene.Type.IncompleDominance;
		
		ArrayList<Bird> birds = genpop();
		
		for(int year=0; year<simyears; year++) { //PH
			birds = dryseason(birds);
			birds = matingseason(birds);
			birds = killoldbirds(birds);
		}
		
		this.addAll(birds);
	}
	
	public static void main(String[] args) {
		
		/*
		for(int i=10000; i<100000; i+=2000) {
			ZZYStatSim simulation = new ZZYStatSim(i);
			System.out.println(i + "," + simulation.size());
		}
		*/
		
		int[] array = {2050, 4050, 8050};
		
		for(int i=0; i<array.length; i++) {
			ZZYStatSim simulation = new ZZYStatSim(array[i]);
			System.out.println(array[i] + "," + simulation.size());
		}
		
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
