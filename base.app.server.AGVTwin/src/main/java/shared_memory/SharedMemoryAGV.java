package shared_memory;

import eapli.base.ordermanagement.domain.TheTask;
import eapli.base.warehousemanagement.domain.AGV;
import eapli.base.warehousemanagement.domain.AGVPosition;

public class SharedMemoryAGV {
    private AGV currentAGV;
    private String[][] warehousePlant;
    private TheTask currentTask;
    private AGVPosition currentPosition;
    private int leftSensor=0, rightSensor=0, upSensor=0, downSensor=0;

    public SharedMemoryAGV(AGV incomeAGV,
                           String[][] incomePlant,
                           TheTask incomeTask,
                           AGVPosition incomePosition){
        this.currentAGV=incomeAGV;
        this.warehousePlant=incomePlant;
        this.currentTask=incomeTask;
        this.currentPosition=incomePosition;
    }

    public void changeLeftSignal(int incomeLeftSignal){
        this.leftSensor=incomeLeftSignal;
    }

    public void changeRightSignal(int incomeRightSignal){
        this.rightSensor=incomeRightSignal;
    }

    public void changeUpSignal(int incomeUpSignal){
        this.upSensor=incomeUpSignal;
    }

    public void changeDownSignal(int incomeDownSignal){
        this.downSensor=incomeDownSignal;
    }

    public int leftSensor(){
        return this.leftSensor;
    }
    public int rightSensor(){
        return this.rightSensor;
    }
    public int upSensor(){
        return this.upSensor;
    }
    public int downSensor(){
        return this.downSensor;
    }

    public void resetSensors(){
        this.leftSensor=0;
        this.rightSensor=0;
        this.upSensor=0;
        this.downSensor=0;
    }

    public AGV theAGV() {
        return currentAGV;
    }

    public void changeCurrentAGV(AGV currentAGV) {
        this.currentAGV = currentAGV;
    }

    public String[][] theWarehousePlant() {
        return warehousePlant;
    }

    public void changeWarehousePlant(String[][] warehousePlant) {
        this.warehousePlant = warehousePlant;
    }

    public TheTask theCurrentTask() {
        return currentTask;
    }

    public void changeCurrentTask(TheTask currentTask) {
        this.currentTask = currentTask;
    }

    public AGVPosition theCurrentPosition() {
        return currentPosition;
    }

    public void changeCurrentPosition(AGVPosition currentPosition) {
        this.currentPosition = currentPosition;
    }


}
