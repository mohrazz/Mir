package genetic.core;

import mir.core.SolutionsList;


public class Population extends SolutionsList<Chromosome> {
    public Population() {
    }

    public Population(Integer popSize, Integer chromosomeLen, Class geneType) throws InstantiationException, IllegalAccessException{
        for(int i = 0  ; i <popSize ; i ++){
            this.add(new Chromosome(chromosomeLen,geneType));
        }
    }

}
