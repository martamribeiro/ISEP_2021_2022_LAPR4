package BMS;

import eapli.base.warehousemanagement.domain.AGV;
import eapli.base.warehousemanagement.domain.AutonomyStatus;
import eapli.base.warehousemanagement.domain.TaskStatus;

public class AGVBatteryManagementSystem {
    private static final int HOURS_PER_DAY = 24;
    private static final int MINUTES_PER_HOUR = 60;
    private static final int SECONDS_PER_MINUTE = 60;
    private static final double BATTERY_REDUCTION_WHILE_MOVING = 0.2;
    private static final double BATTERY_REDUCTION_WHILE_STANDBY = 0.05;
    private static final double BATTERY_INCREASE_WHILE_CHARGING = 1.25;
    private static AGV agv;

    public AGVBatteryManagementSystem(AGV theAgv){
        agv = theAgv;
    }

    private static Long agvInitialAutonomyDuration(){
        long batteryDuration = 0L;
        String autonomyStatus = agv.getAutonomyStatus().toString();

        if(autonomyStatus.contains("d") || autonomyStatus.contains("D")){
            batteryDuration = (long) autonomyStatus.charAt(0) * HOURS_PER_DAY * MINUTES_PER_HOUR * SECONDS_PER_MINUTE;
        } else if(autonomyStatus.contains("h") || autonomyStatus.contains("H")) {
            batteryDuration = (long) autonomyStatus.charAt(0) * MINUTES_PER_HOUR * SECONDS_PER_MINUTE;
        }else if(autonomyStatus.contains("m") || autonomyStatus.contains("M")){
            batteryDuration = (long) autonomyStatus.charAt(0) * SECONDS_PER_MINUTE;
        }

        return batteryDuration;
    }

    public synchronized void agvFinalAutonomyDuration(){
        Long initialBatteryDuration = agvInitialAutonomyDuration();
        double finalBatteryDuration = initialBatteryDuration;

        TaskStatus currentStatus = agv.getTaskStatus();

        if(currentStatus.equals(TaskStatus.valueOf(TaskStatus.TaskStatusEnum.OCCUPIED))){
            finalBatteryDuration = initialBatteryDuration * BATTERY_REDUCTION_WHILE_MOVING;
        }else if(currentStatus.equals(TaskStatus.valueOf(TaskStatus.TaskStatusEnum.FREE))){
            finalBatteryDuration = initialBatteryDuration * BATTERY_REDUCTION_WHILE_STANDBY;
        }else if(currentStatus.equals(TaskStatus.valueOf(TaskStatus.TaskStatusEnum.CHARGING))){
            finalBatteryDuration = initialBatteryDuration * BATTERY_INCREASE_WHILE_CHARGING;
        }



        agv.changeBattery(new AutonomyStatus(convertFinalAutonomyDuration(finalBatteryDuration)));

        System.out.println("############## The AGV has a battery of " + agv.getAutonomyStatus().toString() + " at this moment! ##############");
    }

    private static String convertFinalAutonomyDuration(double finalBatteryDuration){
        double auxToMinutes = finalBatteryDuration / SECONDS_PER_MINUTE, auxToHours, auxToDays;
        String finalAutonomyDuration;

        if(auxToMinutes >= 60){
            auxToHours = auxToMinutes / MINUTES_PER_HOUR;

            if(auxToHours >= 60){
                auxToDays = auxToHours / HOURS_PER_DAY;

                finalAutonomyDuration = (int) auxToDays+ "d";
            }else{
                finalAutonomyDuration = (int) auxToHours + "h";
            }
        }else {
            finalAutonomyDuration = (int) auxToMinutes + "m";
        }

        return finalAutonomyDuration;
    }
}
