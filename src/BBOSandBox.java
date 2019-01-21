import bbo.BioGeography;
import bbo.siv_types.BooleanSiv;
import examiner.FlightNetworkExaminer;
import mir.core.Solution;


public class BBOSandBox {

    private static int citiesCount = 10;

    public static void main(String[] args) throws Exception {
        Double solutionLen = (Math.pow(citiesCount,2) - citiesCount) / 2 ;
        BioGeography bbo = new BioGeography(solutionLen.intValue(), 10,100, 1, 1, 0.02, new FlightNetworkExaminer(citiesCount), BooleanSiv.class, false);
        Long startTime = System.currentTimeMillis();
        bbo.start();
        System.out.println((System.currentTimeMillis() - startTime));
        System.out.println(bbo.getSolutionsLists().get(0));
        System.out.println(bbo.getSolutionsLists().get(bbo.getSolutionsLists().size()-1));
        System.out.println("END");
        Out.printNetwork(((Solution) bbo.getSolutionsLists().get(bbo.getSolutionsLists().size()-1).get(0)) , citiesCount);

    }
}
