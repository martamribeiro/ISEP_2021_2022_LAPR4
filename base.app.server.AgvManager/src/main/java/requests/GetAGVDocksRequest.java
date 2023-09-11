package requests;

import eapli.base.warehousemanagement.application.AGVManagerServerController;
import eapli.base.warehousemanagement.domain.AgvDock;

import java.io.*;
import java.util.List;

public class GetAGVDocksRequest extends AGVManagerServerRequest{

    public GetAGVDocksRequest(final AGVManagerServerController ctrl,
                            final byte request,
                            final ObjectOutputStream sOutObject,
                            final DataInputStream sIn,
                            final DataOutputStream sOut,
                            final byte[] clientMessageUS,
                            final ObjectInputStream sInObject){
        super(ctrl, request, sOutObject, sIn, sOut, clientMessageUS, sInObject);
    }
    @Override
    public void execute() {
        List<AgvDock> agvDocks = (List<AgvDock>) this.agvManagerServerController.agvDocks();

        try {
            this.sOutputObject.writeObject(agvDocks);
            this.sOutputObject.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
