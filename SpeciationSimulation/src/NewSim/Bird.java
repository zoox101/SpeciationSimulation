package NewSim;

/**
 * @author William Booker
 * Based on work by Kevin and Mark
 * Speciation Simulation v.2.0 - 10/12/16
 * For use in Dr. Hougen's REAL Lab
 */
public class Bird {
	
	public static enum InheritanceMethod {Meiosis, Pseudomeiosis, Averaging}; 
	public static enum SelectionType {RandomSelection, SexualSelection};
	
	//Initial Population
	protected final double birdbeakmean = 5.5; //P4 
	protected final double birdbeakstdev = 0.5; //P4
	//Averaging
	protected final double beaksizestdev = 0.2; //PG
	//Meiosis
	protected final int genesize = 100;

	double energy;
	double beak;
	boolean male = false;
	int matecount = 0;
	int age = 0;
	Gene fathergene;
	Gene mothergene;
	
	//Inital Population
	Bird(InheritanceMethod method, Gene.Type type) {
		
		//Setting gender
		if(Math.random() > 0.5) male = true; //PH
		
		//Setting beak sizes
		if(method == InheritanceMethod.Averaging)
			this.beak = Random.normal(birdbeakmean, birdbeakstdev);
		if(method == InheritanceMethod.Meiosis || method == InheritanceMethod.Pseudomeiosis) {
			fathergene = new Gene(100);
			mothergene = new Gene(100);
			this.beak = phenotype(type);
		}
	}
	
	Bird(Bird father, Bird mother, InheritanceMethod method, Gene.Type type) {
		
		//Setting genders
		if(Math.random() > 0.5) male = true; //PH
		
		//Setting beak size of the child
		if(method == InheritanceMethod.Averaging)
			this.beak = (father.beak+mother.beak)/2 + Random.normal(0, beaksizestdev);
		if(method == InheritanceMethod.Meiosis) {
			this.fathergene = meiosis(father.fathergene, father.mothergene);
			this.mothergene = meiosis(mother.fathergene, mother.mothergene);
			this.beak = phenotype(type);
		}
		if(method == InheritanceMethod.Pseudomeiosis) {
			this.fathergene = pseudomeiosis(father.fathergene, father.mothergene);
			this.mothergene = pseudomeiosis(mother.fathergene, mother.mothergene);
			this.beak = phenotype(type);
		}
	}
	
	private double phenotype(Gene.Type type) {
		if(type == Gene.Type.IncompleDominance)
			return (mothergene.phenotype() + fathergene.phenotype())/2;
		if(type == Gene.Type.CompleteDominance)
			return Math.max(mothergene.phenotype(), fathergene.phenotype());
		return -1;
	}
	
	private Gene meiosis(Gene a, Gene b) {
		if(Math.random() > 0.5) return a.copy();
		else return b.copy();
	}
	
	private Gene pseudomeiosis(Gene a, Gene b) {
		Gene gene = new Gene(genesize);
		for(int i=0; i<genesize; i++)
			gene.set(i, (a.get(i) + b.get(i))/2);
		return gene.copy();
	}

}
