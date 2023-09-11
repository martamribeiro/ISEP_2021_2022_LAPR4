package eapli.base.warehousemanagement.domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class AGVTest {
    final Square beginSquare = new Square(1L, 1L);
    final Square endSquare = new Square(1L, 6L);
    final Square depthSquare = new Square(2L, 4L);
    final Accessibility accessibility = new Accessibility("right");
    final AgvDock agvDock = new AgvDock("165", beginSquare, endSquare, depthSquare, accessibility);
    final AutonomyStatus autonomyStatus = new AutonomyStatus("2h");
    final TaskStatus taskStatus = new TaskStatus(TaskStatus.TaskStatusEnum.FREE);
    final AGV agv = new AGV(1234L, autonomyStatus, taskStatus, "1h23", "test", 22.3, agvDock);

    @Test
    public void getAgvID() {
        Assert.assertEquals(Long.valueOf(1234), agv.getAgvID());
    }

    @Test
    public void getAutonomyStatus() {
        Assert.assertEquals(new AutonomyStatus("2h"), agv.getAutonomyStatus());
    }

    @Test
    public void getTaskStatus() {
        Assert.assertEquals(new TaskStatus(TaskStatus.TaskStatusEnum.FREE), agv.getTaskStatus());
    }

    @Test
    public void getAgvDock() {
        Assert.assertEquals(new AgvDock("165", beginSquare, endSquare, depthSquare, accessibility), agv.getAgvDock());
    }

    @Test
    public void identity() {
        Assert.assertEquals(Long.valueOf(1234), agv.identity());
    }

    @Test
    public void testToString() {
        Assert.assertEquals("AGV ID: 1234 \n", agv.toString());
    }
}