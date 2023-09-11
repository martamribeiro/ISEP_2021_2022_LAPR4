package requests;

import eapli.base.utils.MessageUtils;
import eapli.base.warehousemanagement.application.AGVTwinServerController;
import eapli.base.warehousemanagement.domain.AGV;
import eapli.base.warehousemanagement.domain.AGVPosition;
import eapli.base.warehousemanagement.domain.TaskStatus;
import positioning.AGVCurrentPositions;
import route_planner.AgvRoute;

import java.awt.geom.Point2D;
import java.io.*;
import java.util.LinkedList;

public class SendAGVStatusRequest extends AGVTwinServerRequest{
    public SendAGVStatusRequest(final AGVTwinServerController agvTwinServerController,
                                   final byte request,
                                   final ObjectOutputStream sOutObject,
                                   final DataInputStream sIn,
                                   final DataOutputStream sOut,
                                   final byte[] clientMessageUS,
                                   final ObjectInputStream sInObject) {
        super(agvTwinServerController, request, sOutObject, sIn, sOut, clientMessageUS, sInObject);
    }

    @Override
    public void execute() {
        try {

            AGV agv = null;
            TaskStatus agvStatus = null;

            try {
                agv = (AGV) this.sInObject.readObject();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            agvStatus = agv.getTaskStatus();

            ObjectOutputStream sendStatus = this.sOutputObject;
            sendStatus.writeObject(agvStatus);
            sendStatus.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
