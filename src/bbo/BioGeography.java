package bbo;

import bbo.core.Archipelago;
import bbo.core.Habitat;
import mir.core.Environment;
import mir.core.Examiner;
import mir.core.Solution;
import mir.core.SolutionsList;
import selectionutils.RouletteWheel;

import java.util.HashMap;
import java.util.Random;


public class BioGeography extends Environment<Archipelago> {
    private Boolean elitism;
    private double mutationProbability;
    private double maxMu;
    private double maxLambda;


    public BioGeography(Integer solutionLen,
                        Integer solutionListLen,
                        Integer repetitionCount,
                        double maxLambda,
                        double maxMu,
                        double mutationProbability,
                        Examiner examiner,
                        Class cellType,
                        Boolean elitism) {
        super(solutionLen, solutionListLen, repetitionCount, examiner, cellType);
        this.elitism = elitism;
        this.mutationProbability = mutationProbability;
        this.maxLambda = maxLambda;
        this.maxMu = maxMu;
    }

    @Override
    protected void initial() throws Exception {
        Archipelago archipelago = new Archipelago(this.solutionListLen, solutionLen, this.cellType);
        archipelago.fillRandom();
        this.solutionsLists.add(archipelago);
    }

    @Override
    protected void rankAllSolutions(Archipelago archipelago) {
        for (Habitat habitat : archipelago) {
            habitat.setRank(this.examiner.exam(habitat));
        }
    }

    @Override
    protected SolutionsList next() throws Exception {
        Random rand = new Random();
        RouletteWheel rouletteWheel = new RouletteWheel();
        Archipelago archipelago = new Archipelago();
        archipelago.addAll((Archipelago)(this.solutionsLists.get(this.solutionsLists.size() - 1)).clone());
        for(int hCounter = 0 ; hCounter < archipelago.size() ; hCounter++){
            Habitat habitat = archipelago.get(hCounter);
            for (int sivCounter = 0; sivCounter < habitat.size() ; sivCounter++) {
                if(rand.nextDouble() < habitat.getLambda()){
                    Habitat selected = (Habitat)rouletteWheel.spin(this.createRouletWheelMap(archipelago),1).get(0);
                    habitat.set(sivCounter, selected.get(sivCounter));
                }
                if( rand.nextDouble() < this.mutationProbability){
                    habitat.get(sivCounter).mutate();
                }
            }
            rankAllSolutions(archipelago);
            calculateMu(archipelago);
            calculateLambda(archipelago);
        }
        return archipelago;
    }

    @Override
    public void start() throws Exception {
        initial();
        rankAllSolutions((Archipelago) this.solutionsLists.get(0));
        calculateMu((Archipelago) this.solutionsLists.get(0));
        calculateLambda((Archipelago) this.solutionsLists.get(0));
        this.solutionsLists.get(0).sortDesc();

        for (int repeatCounter = 0; repeatCounter < this.repetitionCount; repeatCounter++) {
            Archipelago newArchipelago = new Archipelago();
            newArchipelago.addAll(this.next());
            rankAllSolutions(newArchipelago);
            calculateMu(newArchipelago);
            calculateLambda(newArchipelago);
            newArchipelago.sortDesc();
            this.solutionsLists.add(newArchipelago);
        }

    }

    private HashMap<Solution, Double> createRouletWheelMap(Archipelago archipelago){
        HashMap<Solution, Double> map = new HashMap<>();
        for(Habitat habitat : archipelago){
            map.put(habitat, habitat.getMu());
        }
        return map;

    }
    public void calculateMu(Archipelago archipelago) {
        archipelago.sortAsc();
        for (int ki = 0; ki < archipelago.size(); ki++) {
            archipelago.get(ki).setMu(this.maxMu * (new Double(ki) / this.solutionListLen));
        }
    }

    public void calculateLambda(Archipelago archipelago) {
        archipelago.sortDesc();
        for (int ki = 0; ki < archipelago.size(); ki++) {
            archipelago.get(ki).setLambda( this.maxLambda * (new Double(ki) / this.solutionListLen));
        }
    }

}
