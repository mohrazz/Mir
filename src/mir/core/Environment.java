package mir.core;

import java.util.ArrayList;

abstract public class Environment<SL> {
    protected Integer solutionLen;
    protected Integer solutionListLen;
    protected Integer repetitionCount;
    protected Examiner examiner;
    protected Class cellType;
    protected ArrayList<SolutionsList> solutionsLists = new ArrayList<>();


    public Environment(Integer solutionLen, Integer solutionListLen, Integer repetitionCount, Examiner examiner, Class cellType) {
        this.solutionLen = solutionLen;
        this.solutionListLen = solutionListLen;
        this.repetitionCount = repetitionCount;
        this.examiner = examiner;
        this.cellType = cellType;
    }

    abstract protected void initial() throws Exception;
    abstract protected void rankAllSolutions(SL solutionsList) ;
    abstract protected SolutionsList next() throws Exception;
    abstract protected void start() throws Exception;

    public ArrayList<SolutionsList> getSolutionsLists() {
        return solutionsLists;
    }

    public void setSolutionsLists(ArrayList<SolutionsList> solutionsLists) {
        this.solutionsLists = solutionsLists;
    }
}
