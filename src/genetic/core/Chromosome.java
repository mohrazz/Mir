package genetic.core;

import mir.core.Solution;


public class Chromosome extends Solution<Gene> {
    public Chromosome() {
    }

    public Chromosome(Integer length, Class cellType) throws InstantiationException, IllegalAccessException {
        super(length, cellType);
    }

}
