package positioning;

import eapli.base.warehousemanagement.domain.AGV;
import eapli.base.warehousemanagement.domain.AGVPosition;
import eapli.base.warehousemanagement.domain.Square;

import java.awt.geom.Point2D;
import java.util.LinkedList;

public class AGVCurrentPositions {
    private static Double speed;
    private static String[][] warehousePlant;
    private static AGV currentAGV;
    private static LinkedList<Point2D> thisRoute;
    private static int timeSpent;

    public AGVCurrentPositions (Double currentSpeed, String[][] matrix,
                                AGV agv,
                                LinkedList<Point2D> route, int time) {
        speed=currentSpeed;
        warehousePlant=matrix;
        currentAGV=agv;
        thisRoute = route;
        timeSpent=time;
    }

    public synchronized String[][] calculatePosition() {
        int nextIndex = (int) (speed * timeSpent);
        int index, counter=0;
        Point2D nextPos;
        long wsquare, lsquare;

        for(int i=0; i< warehousePlant.length; i++){
            for (int j=0; j< warehousePlant[0].length; j++){
                if (warehousePlant[i][j].equalsIgnoreCase(String.valueOf(currentAGV.getAgvID()))){
                    index = thisRoute.indexOf(new Point2D.Double((long) i, (long) j));
                    if(index<thisRoute.size() && index >=0){
                        nextPos = thisRoute.get(index+nextIndex);
                        wsquare = Double.valueOf(nextPos.getX()).longValue();
                        lsquare = Double.valueOf(nextPos.getY()).longValue();
                        counter++;

                        if(!(warehousePlant[Math.toIntExact(wsquare)][Math.toIntExact(lsquare)].charAt(0) == 'D')){
                            warehousePlant[i][j]= "X";
                            warehousePlant[Math.toIntExact(wsquare)][Math.toIntExact(lsquare)] = String.valueOf(currentAGV.getAgvID());
                        } else {
                            warehousePlant[i][j]= "X";
                        }
                    }
                }
            }
        }

        if(counter==0){
            if(nextIndex<thisRoute.size()) {
                nextPos = thisRoute.get(nextIndex);
                wsquare = Double.valueOf(nextPos.getX()).longValue();
                lsquare = Double.valueOf(nextPos.getY()).longValue();
                warehousePlant[Math.toIntExact(wsquare)][Math.toIntExact(lsquare)] = String.valueOf(currentAGV.getAgvID());
            }
        }

        return warehousePlant;
    }
}
