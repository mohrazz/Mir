package genetic;

import genetic.core.Chromosome;
import genetic.core.Population;
import genetic.operators.GeneticOperators;
import mir.core.Environment;
import mir.core.Examiner;
import mir.core.SolutionsList;

import java.util.Collections;
import java.util.List;


public class EcoSystem extends Environment<Population> {

    private Boolean elitism;
    private double mutationProbability;

    public EcoSystem(Integer solutionLen,
                     Integer solutionListLen,
                     Integer repetitionCount,
                     double mutationProbability,
                     Examiner examiner,
                     Class cellType,
                     Boolean elitism) {
        super(solutionLen, solutionListLen, repetitionCount, examiner, cellType);
        this.elitism = elitism;
        this.mutationProbability = mutationProbability;
    }

    @Override
    protected void initial() throws IllegalAccessException, InstantiationException {
        Population population = new Population(this.solutionListLen, solutionLen, this.cellType);
        population.fillRandom();
        this.solutionsLists.add(population);
    }

    @Override
    protected void rankAllSolutions(Population population) {
        for (Chromosome chromosome : population) {
            chromosome.setRank(this.examiner.exam(chromosome));
        }
    }

    @Override
    protected SolutionsList next() throws IllegalAccessException, InstantiationException {
        Population newPop = new Population();
        newPop.addAll((Population)(this.solutionsLists.get(this.solutionsLists.size() - 1)).clone());
        Collections.shuffle(newPop);
        for (int i = 0; i < this.solutionListLen; i += 2) {
            List<Chromosome> childes = GeneticOperators.crossOver(newPop.get(i), newPop.get(i + 1));
            childes.set(0,GeneticOperators.mutation(childes.get(0), mutationProbability)) ;
            childes.set(1,GeneticOperators.mutation(childes.get(1), mutationProbability)) ;
            newPop.addAll(childes);
        }
//        System.out.println(newPop);
        return newPop;
    }

    @Override
    public void start() throws IllegalAccessException, InstantiationException {
        Population newPop;
        initial();
        rankAllSolutions((Population) this.solutionsLists.get(0));
        this.solutionsLists.get(0).sortDesc();
        for (int counter = 0; counter < this.repetitionCount; counter++) {
            newPop = new Population();
            newPop.addAll(next());
            rankAllSolutions(newPop);
            newPop.sortDesc();
            Population selectedPop = new Population();
            selectedPop.addAll(newPop.subList(0, this.solutionListLen));
            this.solutionsLists.add(selectedPop);
        }

    }



}

