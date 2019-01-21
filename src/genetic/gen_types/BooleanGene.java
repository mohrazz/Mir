package genetic.gen_types;

import genetic.core.Gene;

import java.util.Random;


public class BooleanGene extends Gene<Boolean> {
    public BooleanGene() {
    }

    public BooleanGene(Boolean value) {
        super(value);
    }


    @Override
    public void mutate() {
        this.setValue(!this.getValue());
    }

    @Override
    public void fillRandom() {
        this.setValue((new Random()).nextBoolean());
    }
}
