package bbo.core;

import mir.core.Solution;


public class Habitat extends Solution<Siv> {

    private Double lambda;
    private Double mu;

    public Habitat() {
    }

    public Habitat(Integer length, Class cellType) throws InstantiationException, IllegalAccessException {
        super(length, cellType);
    }

    public Double getMu() {
        return mu;
    }

    public void setMu(Double mu) {
        this.mu = mu;
    }

    public Double getLambda() {
        return lambda;
    }

    public void setLambda(Double lambda) {
        this.lambda = lambda;
    }

    @Override
    public String toString() {
        return super.toString() + " <lambda: " +this.getLambda() +" > <mu: "+ this.getMu() + ">";
    }
}
