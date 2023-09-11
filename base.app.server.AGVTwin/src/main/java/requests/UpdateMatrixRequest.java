package requests;

import BMS.AGVBatteryManagementSystem;
import eapli.base.ordermanagement.domain.TheTask;
import eapli.base.utils.MessageUtils;
import eapli.base.warehousemanagement.application.AGVTwinServerController;
import eapli.base.warehousemanagement.domain.AGV;
import eapli.base.warehousemanagement.domain.AGVPosition;
import eapli.base.warehousemanagement.domain.Square;
import eapli.base.warehousemanagement.domain.TaskStatus;
import positioning.AGVCurrentPositions;
import route_planner.AgvRoute;
import shared_memory.SharedMemoryAGV;
import simulation_engine.SimulationEngine;

import java.awt.geom.Point2D;
import java.io.*;
import java.util.LinkedList;

public class UpdateMatrixRequest extends AGVTwinServerRequest{
    private final double NORMAL_AGV_SPEED = 1.00;
    private final double REDUCED_AGV_SPEED = 0.5;
    private final int TIME_BETWEEN_MOVES= 1;

    public UpdateMatrixRequest(final AGVTwinServerController agvTwinServerController,
                                final byte request,
                                final ObjectOutputStream sOutObject,
                                final DataInputStream sIn,
                                final DataOutputStream sOut,
                                final byte[] clientMessageUS,
                                final ObjectInputStream sInObject,
                                final String[][] receivedMatrix,
                                final AGV agv,
                                final AGVPosition currentPosition,
                                final TheTask currentTask) {
        super(agvTwinServerController, request, sOutObject, sIn, sOut, clientMessageUS, sInObject, receivedMatrix, agv, currentPosition, currentTask);
    }
    @Override
    public void execute() {
        try {
                if(currentTask!=null){
                    AGVPosition currentPosition = searchPosition(receivedMatrix, agv);

                    SharedMemoryAGV sharedMemoryAGV = new SharedMemoryAGV(agv, receivedMatrix, currentTask, currentPosition);

                    AgvRoute getRoute = new AgvRoute(agv, currentPosition, currentTask, receivedMatrix);
                    LinkedList<Point2D> currentRoute = getRoute.computeFinalRoute();

                    SimulationEngine simulationEngine = new SimulationEngine(receivedMatrix, agv, sharedMemoryAGV);
                    simulationEngine.simulateAGV();

                    double speed=0.0;

                    if(sharedMemoryAGV.leftSensor()>=3 || sharedMemoryAGV.rightSensor()>=3 || sharedMemoryAGV.upSensor()>=3 || sharedMemoryAGV.downSensor()>=3){
                        speed = NORMAL_AGV_SPEED;
                    } else if(sharedMemoryAGV.leftSensor()==2 || sharedMemoryAGV.rightSensor()==2 || sharedMemoryAGV.upSensor()==2 || sharedMemoryAGV.downSensor()==2){
                        speed = REDUCED_AGV_SPEED;
                    }

                    sharedMemoryAGV.resetSensors();

                    if(speed!=0.0){
                        AGVCurrentPositions newPosition = new AGVCurrentPositions(speed, receivedMatrix, agv, currentRoute, TIME_BETWEEN_MOVES);
                        receivedMatrix = newPosition.calculatePosition();
                    }

                    sharedMemoryAGV.changeWarehousePlant(receivedMatrix);

                    AGVBatteryManagementSystem battery = new AGVBatteryManagementSystem(agv);
                    battery.agvFinalAutonomyDuration();


                    this.sOutputObject.writeObject(receivedMatrix);
                    this.sOutputObject.flush();
                } else {
                    this.sOutputObject.writeObject(receivedMatrix);
                    this.sOutputObject.flush();
                }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public AGVPosition searchPosition(String[][] receivedMatrix, AGV agv){
        AGVPosition pos = null;
        for(int i=0; i<receivedMatrix.length; i++){
            for(int j=0; j<receivedMatrix[0].length; j++){
                if (receivedMatrix[i][j].equalsIgnoreCase(String.valueOf(agv.getAgvID()))){
                    pos = new AGVPosition(new Square((long) j,(long) i), agv);
                }
            }
        }

        if(pos==null){
            pos = new AGVPosition(agv.getAgvDock().beginSquare(), agv);
        }

        return pos;
    }
}
