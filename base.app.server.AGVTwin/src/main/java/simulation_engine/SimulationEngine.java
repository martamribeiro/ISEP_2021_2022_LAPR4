package simulation_engine;

import eapli.base.warehousemanagement.domain.AGV;
import sensor.TheSensor;
import shared_memory.SharedMemoryAGV;

public class SimulationEngine {
    private static String[][] matrix;
    private static AGV currentAGV;
    private static SharedMemoryAGV sharedMemoryAGV;

    public SimulationEngine(String[][] incomeMatrix, AGV agv, SharedMemoryAGV sharedMemory){
        matrix=incomeMatrix;
        currentAGV=agv;
        sharedMemoryAGV=sharedMemory;
    }

    public synchronized void simulateAGV(){
        int counter=0;

        for (int i=0; i< matrix.length; i++){
            for(int j=0; j<matrix[0].length; j++){
                if (matrix[i][j].equalsIgnoreCase(String.valueOf(currentAGV.getAgvID()))){
                    counter++;
                    createSensorThreads(i, j);
                }
            }
        }

        if(counter==0){
            int i = Math.toIntExact(currentAGV.getAgvDock().beginSquare().wSquare())-1;
            int j = Math.toIntExact(currentAGV.getAgvDock().beginSquare().lSquare())-1;

            createSensorThreads(i, j);
        }
    }

    private void createSensorThreads(int i, int j){
        for(int k=0; k<8; k++){
            if (k<2) {
                TheSensor sensor = new TheSensor(1, sharedMemoryAGV, i, j);
                sensor.start();
            }else if(k<4){
                TheSensor sensor = new TheSensor(2, sharedMemoryAGV, i, j);
                sensor.start();
            }else if(k<6){
                TheSensor sensor = new TheSensor(3, sharedMemoryAGV, i, j);
                sensor.start();
            } else {
                TheSensor sensor = new TheSensor(4, sharedMemoryAGV, i, j);
                sensor.start();
            }

        }
    }

    public static boolean checkLeft(int x, int y){
        if((y-1)< matrix.length && (y-1)>=0){
            if( !matrix[x][y - 1].equalsIgnoreCase("X") && !matrix[x][y-1].equalsIgnoreCase(currentAGV.getAgvDock().getAgvDockID())){
                return true;
            }
        }

        return false;
    }

    public static boolean checkRight(int x, int y){
        if((y+1)<= matrix.length && (y+1)>=0){
            if(!matrix[x][y + 1].equalsIgnoreCase("X") && !matrix[x][y + 1].equalsIgnoreCase(currentAGV.getAgvDock().getAgvDockID())){
                return true;
            }
        }

        return false;
    }

    public static boolean checkUp(int x, int y){
        if((x-1)< matrix[0].length && (x-1)>=0){
            if(!matrix[x - 1][y].equalsIgnoreCase("X") && !matrix[x - 1][y].equalsIgnoreCase(currentAGV.getAgvDock().getAgvDockID())) {
                return true;
            }
        }

        return false;
    }

    public static boolean checkDown(int x, int y){
        if((x+1)<= matrix[0].length && (x+1)>=0){
            if(!matrix[x + 1][y].equalsIgnoreCase("X") && !matrix[x+1][y].equalsIgnoreCase(currentAGV.getAgvDock().getAgvDockID())){
                return true;
            }
        }

        return false;
    }

}
