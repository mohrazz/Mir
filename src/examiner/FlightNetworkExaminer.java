package examiner;

import mir.core.Cell;
import mir.core.Examiner;
import mir.core.Solution;


public class FlightNetworkExaminer implements Examiner<Solution> {

    private Integer citiesCount;
    private double[][] passengers = {
            {0,     1,      1,      0.7,    0.7,    0.7,    0.75,   0.70,   1,      1   },
            {0.8,   0,      0.7,    1,      0.5,    1,      0.7,    0.6,    0.5,    0.8 },
            {1,     1,      0,      0.9,    0.7,    0.9,    0.8,    0.6,    0.7,    0.9 },
            {0.8,   1,      0.9,    0,      0.5,    1,      0.9,    0.5,    0.8,    0.9 },
            {0.9,   0.8,    0.8,    0.7,    0,      0.7,    0.7,    0.5,    0.9,    0.6 },
            {1,     0.8,    0.8,    1,      0.6,    0,      0.7,    0.6,    0.5,    1   },
            {1,     0.8,    0.7,    0.9,    0.6,    0.7,    0,      0.6,    0.4,    0.7 },
            {0.7,   0.9,    0.6,    0.8,    0.6,    0.5,    0.5,    0,      0.6,    0.7 },
            {1,     0.6,    0.6,    0.8,    0.9,    0.5,    0.3,    0.6,    0,      0.5 },
            {1,     0.5,    0.9,    0.9,    0.8,    0.9,    0.6,    0.7,    0.6,    0   }
    };

    private double[][] distances = {
            {0,     1.5,    1.5,    1.25,   1,      1.25,   1,      1,      0.75,   1.75},
            {1.5,   0,      1.75,   1.5,    1,      1.6,    1,      1.25,   1.5,    2   },
            {1.5,   1.75,   0,      0.75,   1.6,    0.5,    1,      1.2,    2,      0.5 },
            {1.25,  1.5,    0.75,   0,      1.55,   0.6,    0.75,   0.5,    1,      0.75},
            {1,     1,      1.6,    1.55,   0,      2,      1.25,   1.40,   1,      2.4 },
            {1.25,  1.6,    0.5,    0.6,    2,      0,      1,      1.3,    1.9,    0.5 },
            {1,     1,      1,      0.75,   1.25,   1,      0,      0.6,    0.9,    0.6 },
            {1,     1.25,   1.2,    0.5,    1.40,   1.3,    0.6,    0,      1,      1   },
            {0.75,  1.5,    2,      1,      1,      1.9,    0.9,    1,      0,      1.5 },
            {1.75,  2,      0.5,    0.75,   2.4,    0.5,    0.6,    1,      1.5,    0   }
    };

    public FlightNetworkExaminer(Integer citiesCount) {
        this.citiesCount = citiesCount;
    }

    @Override
    public Double exam(Solution solution) {
        double fitness = 0;
        double sumPassengers = 0;
        double sumDistance = 0;
        int index = 0;
        for(int i = 1 ; i < this.citiesCount ; i++){
            for (int j = 0 ; j< i ; j++){
                if(((Cell) solution.get(index)).getValue().equals(true)){
                    sumDistance += this.distances[i][j] + this.distances[j][i];
                    sumPassengers += this.passengers[i][j] + this.passengers[j][i];
                }
                index++;
            }
        }
        fitness += sumPassengers / sumDistance;
        return fitness;
    }
}
