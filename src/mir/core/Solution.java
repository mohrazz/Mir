package mir.core;

import java.util.ArrayList;

abstract public class Solution<C> extends ArrayList<C> implements Comparable<Solution>, Cloneable {
    private Double rank;

    public Solution() {
    }

    public Solution(Integer length, Class cellType) throws InstantiationException, IllegalAccessException {
        for (int i = 0; i < length; i++) {
            this.add((C) cellType.newInstance());
        }
    }

    public Double getRank() {
        return rank;
    }

    public void setRank(Double rank) {
        this.rank = rank;
    }

    public void fillRandom() {
        for (C c : this)
            ((Cell) c).fillRandom();
    }

    @Override
    public int compareTo(Solution s) {
//         return (this.getRank() < s.getRank()) ? -1 : ((this.getRank() == s.getRank()) ? 0 : 1);
        return this.getRank().compareTo(s.getRank());
    }

    @Override
    public Object clone() {
        Solution clone = (Solution) super.clone();
        clone.removeRange(0, this.size());
        try {
            for (C c : this) {
                Cell cell = (Cell) c;
                clone.add((Cell) cell.clone());
            }
        }catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
        return clone;
    }
}
