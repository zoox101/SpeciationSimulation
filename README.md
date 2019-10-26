# Speciation Simulation

Git repository containing the code used in the "Meiotic Inheritance and Gene Dominance in Synthetic Sympatric Speciation" paper. This repository is maintained by Will Booker on a best-effort basis. All contributions are welcome. Information on each of the subdirectories is provided below.

## Speciation Simulation

The actual code used for all experiments conducted in the paper. The main method is contained in the `AAADriver.java` file. Uncomment lines to run experiments. Outputs will be saved to csv files in the source directory.

## Genetics Arena

Experimental refactoring of the code provided in Speciation Simulation. This library is intended to make it easier to run new experiments for different population conditions. Birds and Seeds have been abstracted to their own classes rather than hard-coded into the simulation. Outputs will be saved in the `saves/` subdirectory.

## data-small

Contains a small set of data used for the graphs in the publication.

## data-zipped

Contains all the data from all simulations used in the publication.

## paper-versions

Contains early drafts of the speciation paper. May provide some insight into the editing process. Earliest drafts are lost. Will be included if found.

## RCode

Contains code used to create the graphs and run the statistical tests from the paper. No longer supported. Use at your own risk. If you need to create graphs, I would recommend using Python's Matplotlib.
