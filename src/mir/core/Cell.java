package mir.core;

abstract public class Cell <T> implements Cloneable{
    protected Cell() {
    }

    private T value;

    public Cell(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    abstract public void fillRandom();

    @Override
    public String toString() {
        return  String.valueOf(value);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
