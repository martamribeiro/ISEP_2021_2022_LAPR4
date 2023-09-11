package sensor;

import shared_memory.SharedMemoryAGV;
import simulation_engine.SimulationEngine;

public class TheSensor extends Thread {
    private int signal;
    private static int sensorSide, i, j;
    private static SharedMemoryAGV sharedMemoryAGV;

    public TheSensor (int agvSide, SharedMemoryAGV sharedMemory, int incomeI, int incomeJ){
        sensorSide=agvSide;
        sharedMemoryAGV=sharedMemory;
        i=incomeI;
        j=incomeJ;
    }

    @Override
    public void run(){
        if(sensorSide==1){
            sendLeftSide();
        }else if(sensorSide==2){
            sendRightSide();
        }else if(sensorSide==3) {
            sendUpSide();
        }else if(sensorSide==4) {
            sendDownSide();
        }
    }


    public static synchronized void sendSignal(int incomeSignal){
        if(incomeSignal>=3){
            sideSignal(incomeSignal);
        }else if(incomeSignal==2){
            sideSignal(incomeSignal);
        }else if(incomeSignal==1){
            sideSignal(incomeSignal);
        }
    }

    private static void sideSignal(int incomeSignal){
        switch (sensorSide) {
            case 1:
                sharedMemoryAGV.changeLeftSignal(incomeSignal);
                System.out.println("#################### Left sensor detected an obstacle " + incomeSignal + " squares away! ####################");
                break;
            case 2:
                sharedMemoryAGV.changeRightSignal(incomeSignal);
                System.out.println("#################### Right sensor detected an obstacle " + incomeSignal + " squares away! ####################");
                break;
            case 3:
                sharedMemoryAGV.changeUpSignal(incomeSignal);
                System.out.println("#################### Up sensor detected an obstacle " + incomeSignal + " squares away! ####################");
                break;
            case 4:
                sharedMemoryAGV.changeDownSignal(incomeSignal);
                System.out.println("#################### Down sensor detected an obstacle " + incomeSignal + " squares away! ####################");
                break;
        }
    }

    private void sendLeftSide(){
        if(SimulationEngine.checkLeft(i, j)){
            sendSignal(1);
        }else if(SimulationEngine.checkLeft(i, j - 1)){
            sendSignal(2);
        } else {
            sendSignal(3);
        }
    }

    private void sendRightSide(){
        if(SimulationEngine.checkRight(i, j)){
            sendSignal(1);
        }else if(SimulationEngine.checkRight(i, j + 1)){
            sendSignal(2);
        } else {
            sendSignal(3);
        }
    }

    private void sendUpSide(){
        if(SimulationEngine.checkUp(i, j)){
            sendSignal(1);
        }else if(SimulationEngine.checkUp(i-1, j)){
            sendSignal(2);
        } else {
            sendSignal(3);
        }
    }

    private void sendDownSide(){
        if(SimulationEngine.checkDown(i, j)){
            sendSignal(1);
        }else if(SimulationEngine.checkDown(i+1, j)){
            sendSignal(2);
        } else {
            sendSignal(3);
        }
    }
}
