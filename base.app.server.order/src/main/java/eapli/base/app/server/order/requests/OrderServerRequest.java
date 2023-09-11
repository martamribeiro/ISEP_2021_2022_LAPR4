package eapli.base.app.server.order.requests;

import eapli.base.shoppingcartmanagement.application.OrderSrvController;

import java.io.*;

public abstract class OrderServerRequest {
    protected final byte request;
    protected final OrderSrvController orderSrvController;
    protected final ObjectOutputStream sOutputObject;
    protected final DataInputStream sIn;
    protected final DataOutputStream sOut;
    protected byte[] clientMessageUS;
    protected final ObjectInputStream sInObject;

    protected OrderServerRequest(final OrderSrvController orderSrvController,
                                 final byte request,
                                 final ObjectOutputStream sOutObject,
                                 final DataInputStream sIn,
                                 final DataOutputStream sOut,
                                 final byte[] clientMessageUS,
                                 final ObjectInputStream sInObject) {
        this.request = request;
        this.orderSrvController = orderSrvController;
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
