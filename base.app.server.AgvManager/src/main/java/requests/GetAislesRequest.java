package requests;

import eapli.base.warehousemanagement.application.AGVManagerServerController;
import eapli.base.warehousemanagement.domain.Aisle;

import java.io.*;
import java.util.List;

public class GetAislesRequest extends AGVManagerServerRequest{


    public GetAislesRequest(final AGVManagerServerController ctrl,
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
        List<Aisle> aisles = (List<Aisle>) this.agvManagerServerController.aisles();

        try {
            this.sOutputObject.writeObject(aisles);
            this.sOutputObject.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
