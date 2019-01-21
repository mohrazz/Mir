package mir.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


abstract public class SolutionsList<S> extends ArrayList<S> implements Cloneable{

    public S getFirst() {
        return this.get(0);
    }

    public S getLast() {
        return this.get(this.size() - 1);
    }

    public void printRanks() {
        for (Object o : this) {
            System.out.println(((Solution) o).getRank());
        }
    }

    public List<Double> getRanks() {
        ArrayList<Double> result = new ArrayList<>();
        for (Object o : this) {
            result.add(((Solution) o).getRank());
        }
        return result;
    }

    public void fillRandom() {
        for (S s : this)
            ((Solution) s).fillRandom();
    }

    public void sortAsc() {
        Collections.sort((List<Solution>) this);
    }

    public void sortDesc() {
        Collections.sort((List<Solution>) this);
        Collections.reverse(this);
    }

    @Override
    public String toString() {
        String out = "[\n";
        for (Object o : this) {
            Solution s = (Solution) o;
            out = out.concat("<" + String.valueOf(s.getRank()) + ">");
            out = out.concat(s.toString());
            out = out.concat("\n");
        }
        out = out.concat("]");
        return out;
    }

    @Override
    public Object clone() {
        SolutionsList clone = (SolutionsList) super.clone();
        clone.removeRange(0, this.size());
        for (S c : this) {
            Solution cell = (Solution) c;
            clone.add((Solution) cell.clone());
        }
        return clone;
    }
}
