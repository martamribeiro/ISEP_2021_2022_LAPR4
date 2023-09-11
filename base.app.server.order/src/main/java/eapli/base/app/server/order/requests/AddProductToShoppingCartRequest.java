package eapli.base.app.server.order.requests;

import eapli.base.shoppingcartmanagement.application.OrderSrvController;

import java.io.*;

public class AddProductToShoppingCartRequest extends OrderServerRequest {

    public AddProductToShoppingCartRequest(final OrderSrvController ctrl,
                                        final byte request,
                                        final ObjectOutputStream sOutObject,
                                        final DataInputStream sIn,
                                        final DataOutputStream sOut,
                                        final byte[] clientMessageUS,
                                           final ObjectInputStream sInObject) {
        super(ctrl, request, sOutObject, sIn, sOut, clientMessageUS, sInObject);
    }

    @Override
    public void execute() {
        try {
            this.orderSrvController.addProductToShoppingCart(clientMessageUS, sIn);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
