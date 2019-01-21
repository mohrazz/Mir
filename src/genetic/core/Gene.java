package genetic.core;

import mir.core.Cell;

abstract public class Gene<G> extends Cell<G> {
    public Gene() {
    }

    public Gene(G value) {
        super(value);
    }

    abstract public void mutate();
}
