package selectionutils;

import mir.core.Solution;

import java.util.*;


public class RouletteWheel {

    private LinkedHashMap<Solution, Double> sortHashTableByValues(
            HashMap<Solution, Double> passedMap) {
        List<Solution> mapKeys = new ArrayList<>(passedMap.keySet());
        List<Double> mapValues = new ArrayList<>(passedMap.values());
        Collections.sort(mapValues);
        LinkedHashMap<Solution, Double> sortedMap = new LinkedHashMap<>();
        Iterator<Double> valueIt = mapValues.iterator();
        while (valueIt.hasNext()) {
            Double val = valueIt.next();
            Iterator<Solution> keyIt = mapKeys.iterator();
            while (keyIt.hasNext()) {
                Solution key = keyIt.next();
                Double comp1 = passedMap.get(key);
                Double comp2 = val;
                if (comp1.equals(comp2)) {
                    keyIt.remove();
                    sortedMap.put(key, val);
                    break;
                }
            }
        }
        return sortedMap;
    }

    public ArrayList<Solution> spin(HashMap<Solution, Double> solutionRanks, int count) {
        ArrayList<Solution> selectedList = new ArrayList<>();

        ArrayList<Double> sortedRanks = new ArrayList<>();
        ArrayList<Solution> sortedSolutions = new ArrayList<>();
        Random rand = new Random();
        Double[] wheel = new Double[solutionRanks.size()];
        solutionRanks = this.sortHashTableByValues(solutionRanks);
        sortedRanks.addAll(solutionRanks.values());
        sortedSolutions.addAll(solutionRanks.keySet());

        wheel[0] = sortedRanks.get(0);

        for (int i = 1; i < sortedRanks.size(); i++) {
            wheel[i] = wheel[i - 1] + sortedRanks.get(i);
        }

        for(int i = 0 ; i< count; i++){
            Double randomRank = rand.nextDouble() * wheel[sortedRanks.size() - 1];

            int index = Arrays.binarySearch(wheel, randomRank);
            if(index < 0)
                index = Math.abs(index+1);
            selectedList.add(sortedSolutions.get(index));
//            System.out.println(sortedSolutions.get(index).getRank());
        }



        return selectedList;
    }


}
