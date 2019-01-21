import mir.core.Cell;
import mir.core.Solution;


public class Out {

    public static void printNetwork(Solution solution, int nodesCount){
        int index = 0;
        for(int i = 1 ; i < nodesCount ; i++){
            for (int j = 0 ; j< i ; j++){
                if(((Cell) solution.get(index)).getValue().equals(true)){
                    System.out.println("["+i+"] ---> ["+j+"]" );
                }
                index++;
            }
        }
    }
}
