package requests;

import eapli.base.ordermanagement.domain.TheTask;
import eapli.base.warehousemanagement.application.AGVTwinServerController;
import eapli.base.warehousemanagement.domain.AGV;
import eapli.base.warehousemanagement.domain.AGVPosition;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public abstract class AGVTwinServerRequest {
    protected final byte request;
    protected final AGVTwinServerController agvTwinServerController;
    protected final ObjectOutputStream sOutputObject;
    protected final DataInputStream sIn;
    protected final DataOutputStream sOut;
    protected byte[] clientMessageUS;
    protected final ObjectInputStream sInObject;
    protected String[][] receivedMatrix=null;
    protected AGV agv=null;
    protected AGVPosition currentPosition=null;
    protected TheTask currentTask=null;

    protected AGVTwinServerRequest(final AGVTwinServerController agvTwinServerController,
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
        this.request = request;
        this.agvTwinServerController = agvTwinServerController;
        this.sOutputObject = sOutObject;
        this.sIn = sIn;
        this.sOut = sOut;
        this.clientMessageUS = clientMessageUS;
        this.sInObject = sInObject;
        this.receivedMatrix=receivedMatrix;
        this.agv=agv;
        this.currentPosition=currentPosition;
        this.currentTask=currentTask;
    }

    protected AGVTwinServerRequest(final AGVTwinServerController agvTwinServerController,
                                   final byte request,
                                   final ObjectOutputStream sOutObject,
                                   final DataInputStream sIn,
                                   final DataOutputStream sOut,
                                   final byte[] clientMessageUS,
                                   final ObjectInputStream sInObject) {
        this.request = request;
        this.agvTwinServerController = agvTwinServerController;
        this.sOutputObject = sOutObject;
        this.sIn = sIn;
        this.sOut = sOut;
        this.clientMessageUS = clientMessageUS;
        this.sInObject = sInObject;
    }

    /**
     * Executes the requested action and builds the response to the client.
     *
     * @return the response to send back to the client
     */
    public abstract void execute();
}
