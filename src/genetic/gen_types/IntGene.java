package genetic.gen_types;

import genetic.core.Gene;

import java.util.Random;


public class IntGene extends Gene<Integer> {
    public IntGene() {
    }

    public IntGene(Integer value) {
        super(value);
    }

    @Override
    public Integer getValue() {
        return super.getValue();
    }

    @Override
    public void setValue(Integer value) {
        super.setValue(value);
    }

    @Override
    public void mutate() {
        this.setValue((new Random()).nextInt());
    }

    @Override
    public void fillRandom() {
        this.setValue((new Random()).nextInt());
    }
}
