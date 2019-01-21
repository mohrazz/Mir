package bbo.core;

import mir.core.Cell;


abstract public class Siv<S> extends Cell<S> {
    public Siv() {
    }

    public Siv(S value) {
        super(value);
    }

    abstract public void mutate();
}
