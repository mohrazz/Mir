package bbo.core;

import mir.core.SolutionsList;


public class Archipelago extends SolutionsList<Habitat>{
    public Archipelago() {
    }

    public Archipelago(Integer popSize, Integer habitatLen, Class habitatType) throws InstantiationException, IllegalAccessException{
        for(int i = 0  ; i <popSize ; i ++){
            this.add(new Habitat(habitatLen,habitatType));
        }
    }
}
