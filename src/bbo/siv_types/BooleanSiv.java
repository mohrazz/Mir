package bbo.siv_types;

import bbo.core.Siv;

import java.util.Random;


public class BooleanSiv extends Siv<Boolean> {
    public BooleanSiv() {
    }

    public BooleanSiv(Boolean value) {
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
