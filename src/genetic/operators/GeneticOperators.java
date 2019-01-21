package genetic.operators;

import genetic.core.Chromosome;
import genetic.core.Gene;
import mir.core.Operators;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeneticOperators implements Operators {

    //FixPoint
    public static ArrayList<Chromosome> crossOver(Chromosome parentOne, Chromosome parentTwo, int crossPoint) {
        parentOne = (Chromosome) parentOne.clone();
        parentTwo = (Chromosome) parentTwo.clone();
        ArrayList<Chromosome> childes = new ArrayList<>();
        Chromosome childOne = new Chromosome();
        childOne.addAll(parentOne.subList(0, crossPoint));
        childOne.addAll(parentTwo.subList(crossPoint, parentTwo.size()));
        childOne.setRank(0.0);
        childes.add(childOne);
        Chromosome childTwo = new Chromosome();
        childTwo.addAll(parentTwo.subList(0, crossPoint));
        childTwo.addAll(parentOne.subList(crossPoint, parentOne.size()));
        childTwo.setRank(0.0);
        childes.add(childTwo);
        return childes;
    }

    //RandomPoint
    public static ArrayList<Chromosome> crossOver(Chromosome parentOne, Chromosome parentTwo) {
        Random rand = new Random();
        int crossPoint = rand.nextInt(parentOne.size() + 1);
        return crossOver(parentOne, parentTwo, crossPoint);
    }

    public static Chromosome mutation(Chromosome chromosome, int mutationPoint) {
        chromosome = (Chromosome) chromosome.clone();
        chromosome.get(mutationPoint).mutate();
        return chromosome;
    }

    public static Chromosome mutation(Chromosome chromosome, double probability) {
        chromosome = (Chromosome) chromosome.clone();
        for (Gene g : chromosome) {
            if (Math.random() < probability)
                g.mutate();
        }
        chromosome.setRank(0.0);
        return chromosome;
    }


}
