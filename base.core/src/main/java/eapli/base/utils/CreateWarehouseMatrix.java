package eapli.base.utils;

import eapli.base.warehousemanagement.domain.*;

public class CreateWarehouseMatrix {

    public static String[][] createAccordingWithSize(WarehousePlant plant) {
        Length warehouseLength = plant.warehouseLength();
        Width warehouseWidth = plant.warehouseWidth();
        String[][] warehouseMatrix = new String[Math.toIntExact(warehouseWidth.width())][Math.toIntExact(warehouseLength.length())];

        for (int i=0; i< warehouseMatrix.length; i++){
            for (int j=0; j<warehouseMatrix[0].length; j++){
                warehouseMatrix[i][j] = "X";
            }
        }

        return warehouseMatrix;
    }

    public static void insertObstacles(String[][] warehouseMatrix, Iterable<AgvDock> docks, Iterable<Aisle> aisles, Iterable<AGVPosition> positions){
        int beginW;
        int beginL;
        int endW;
        int endL;
        int depthW;
        int depthL;

        for (AgvDock dock : docks){
            Square beginSquare = dock.beginSquare();
            Square endSquare = dock.endSquare();
            Square depthSquare = dock.depthSquare();
            beginW = Math.toIntExact(beginSquare.wSquare())-1;
            beginL = Math.toIntExact(beginSquare.lSquare())-1;
            endL = Math.toIntExact(endSquare.lSquare())-1;
            depthW = Math.toIntExact(depthSquare.wSquare())-1;
            for(int i= beginW; i<= depthW; i++){
                for(int j = beginL; j<= endL; j++){
                    warehouseMatrix[i][j] = dock.getAgvDockID();
                }
            }
        }

        for(Aisle aisle: aisles){
            Square beginSquare = aisle.beginSquare();
            Square endSquare = aisle.endSquare();
            Square depthSquare = aisle.depthSquare();
            beginW = Math.toIntExact(beginSquare.wSquare())-1;
            beginL = Math.toIntExact(beginSquare.lSquare())-1;
            endL = Math.toIntExact(endSquare.lSquare())-1;
            depthW = Math.toIntExact(depthSquare.wSquare())-1;
            for(int i= beginW; i<= depthW; i++){
                for(int j = beginL; j<= endL; j++){
                    warehouseMatrix[i][j] = "A" + String.valueOf(aisle.identity());
                }
            }
        }

        for (AGVPosition pos: positions) {
            beginL = Math.toIntExact(pos.lSquare()-1);
            beginW = Math.toIntExact(pos.wSquare()-1);

            warehouseMatrix[beginW][beginL] = String.valueOf(pos.agvID());
        }
    }
}
