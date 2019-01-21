import examiner.FlightNetworkExaminer;
import genetic.EcoSystem;
import genetic.gen_types.BooleanGene;
import mir.core.Solution;


public class SandBox {

    private static int citiesCount = 10;



    public static void main(String[] args) throws Exception {
        Double solutionLen = (Math.pow(citiesCount,2) - citiesCount) / 2 ;
        EcoSystem eco = new EcoSystem(solutionLen.intValue(), 10, 100, 0.2, new FlightNetworkExaminer(citiesCount), BooleanGene.class, true);
        Long startTime = System.currentTimeMillis();
        eco.start();
        System.out.println((System.currentTimeMillis() - startTime));
        System.out.println(eco.getSolutionsLists().get(0));
        System.out.println(eco.getSolutionsLists().get(eco.getSolutionsLists().size()-1));
        System.out.println("END");

        Out.printNetwork(((Solution) eco.getSolutionsLists().get(eco.getSolutionsLists().size()-1).get(0)) , citiesCount);

    }
}
